interface Profile {
    id: string;
    pseudo: string;
    email: string;
    firstName: string;
    lastName: string;
    score: string;
    fridgeContents: Array<FridgeContents>;
    favouriteRecipes: Array<FavouriteRecipes>;
}

interface FridgeContents {
    id: string;
    ingredientId: string;
    quantity: string;
}

interface FavouriteRecipes {
    id: string;
    recipeId: string;
}

