package com.arkvis.recommender;

import java.util.List;
import java.util.stream.Collectors;

public class TestRecipeRepository implements RecipeRepository {
    private final List<Recipe> recipes;

    public TestRecipeRepository(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public List<Recipe> findByIngredients(List<Ingredient> queryIngredients) {
        return recipes.stream()
                .filter(recipe -> matchesAnyIngredients(recipe, queryIngredients))
                .collect(Collectors.toList());
    }

    private boolean matchesAnyIngredients(Recipe recipe, List<Ingredient> queryIngredients) {
        List<String> recipeIngredientNames = recipe.getIngredients().stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());

        return queryIngredients.stream()
                .map(Ingredient::getName)
                .anyMatch(recipeIngredientNames::contains);
    }
}
