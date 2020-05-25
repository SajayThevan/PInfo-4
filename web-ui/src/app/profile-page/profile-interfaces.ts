export interface Profile {
    id: string;
    pseudo: string;
    email: string;
    firstName: string;
    lastName: string;
    score: string;
    fridgeContents: Array<FridgeContents>;
    favouriteRecipes: Array<FavouriteRecipes>;
}

export interface FridgeContents {
    id: string;
    ingredientId: string;
    quantity: string;
}

export interface FavouriteRecipes {
    id: string;
    recipeId: string;
}

export interface Recipe {
    id: string;
    recipeId: string;
}