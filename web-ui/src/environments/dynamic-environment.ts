declare var window: any;

export class DynamicEnvironment {
  public get environment() {
    return window.config && window.config.environment;
  }
  public get production() {
    return window.config && window.config.production;
  }

  public get apiUrl() {
    return window.config && window.config.apiUrl;
  }

  public get keycloak() {
    return window.config && window.config.keycloak;
  }

  public get ingredientService() {
    return window.config && window.config.ingredientService;
  }

  public get profileService() {
    return window.config && window.config.profileService;
  }

  public get recipeService() {
    return window.config && window.config.recipeService;
  }

  public get challengeService() {
    return window.config && window.config.challengeService;
  }

}
