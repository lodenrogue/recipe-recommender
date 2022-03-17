package com.arkvis.recommender;

import java.util.List;
import java.util.stream.Collectors;

public class RecipeRecommender {

    private final RecipeRepository recipeRepository;

    public RecipeRecommender(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> recommend(List<Ingredient> ingredients) {
        return recipeRepository.findByIngredients(ingredients).stream()
                .filter(recipe -> recipe.canMakeWithIngredients(ingredients))
                .collect(Collectors.toList());
    }
}
