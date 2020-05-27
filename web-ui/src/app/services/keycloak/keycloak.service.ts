import { Injectable } from '@angular/core';
import * as Keycloak from 'keycloak-js';
import { environment } from '../../../environments/environment';

@Injectable({
    providedIn: 'root',
})
export class KeycloakService {

    static auth: any = {};

    init(): Promise<any> {
        const keycloakAuth: Keycloak.KeycloakInstance = Keycloak({
            url: environment.keycloak.url,
            realm: environment.keycloak.realm,
            clientId: environment.keycloak.clientId,
            'ssl-required': 'external',
            'public-client': true,
        });
        KeycloakService.auth.loggedIn = false;
        return new Promise((resolve, reject) => {
            keycloakAuth.init({ onLoad: 'check-sso', checkLoginIframe: false })
                .success(() => {
                    console.log('Finished init')
                    KeycloakService.auth.loggedIn = false;
                    KeycloakService.auth.authz = keycloakAuth;
                    resolve();
                })
                .error(() => {
                    reject();
                });
        });
    }

    constructor() { }
    login(): void {
        KeycloakService.auth.authz.login().success(
            () => {
                KeycloakService.auth.loggedIn = true;
            },
        );
    }
    getToken(): Promise<string> {
        return new Promise<string>((resolve, reject) => {
             if (KeycloakService.auth.authz.token) {
                KeycloakService.auth.authz
                    .updateToken(5)
                    .success(() => {
                        resolve(<string>KeycloakService.auth.authz.token);
                    })
                    .error(() => {
                        reject('Failed to refresh token');
                    });
            } else {
                reject('Not logged in');
            }
        });
    }
    isLoggedIn(): boolean {
        return KeycloakService.auth.authz.authenticated;
    }

    getID(): string {
        if (this.isLoggedIn()) {
            return KeycloakService.auth.authz.tokenParsed.sub;
        } else return 'guest';
    }

    getUsername(): string {
        if (this.isLoggedIn()) {
            return KeycloakService.auth.authz.tokenParsed.preferred_username;
        } else return 'guest';
    }

    getEmail(): string {
        if (this.isLoggedIn()) {
            return KeycloakService.auth.authz.tokenParsed.email;
        } else return 'guest';
    }

    getFullName(): string {
        if (this.isLoggedIn()) {
            return KeycloakService.auth.authz.tokenParsed.name;
        } else return 'guest';
    }

    getFirstName(): string {
        if (this.isLoggedIn()) {
            return KeycloakService.auth.authz.tokenParsed.family_name;
        } else return 'guest';
    }

    getLastName(): string {
        if (this.isLoggedIn()) {
            return KeycloakService.auth.authz.tokenParsed.given_name;
        } else return 'guest';
    }

    getKeycloakAuth() {
        return KeycloakService.auth.authz;
    }

    logout(): void {
        KeycloakService.auth.authz.logout({ redirectUri: document.baseURI }).success(() => {
            KeycloakService.auth.loggedIn = false;
            KeycloakService.auth.authz = null;
        });
    }
}
