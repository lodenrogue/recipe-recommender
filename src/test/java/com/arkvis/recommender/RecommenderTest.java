package com.arkvis.recommender;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecommenderTest {

    @Test
    void should_returnNoRecipes_when_askingForRecommendationsForNoIngredients() {
        List<Ingredient> ingredients = Collections.emptyList();
        RecipeRecommender recommender = new RecipeRecommender(new TestRecipeRepository(Collections.emptyList()));

        List<Recipe> recipes = recommender.recommend(ingredients);
        assertTrue(recipes.isEmpty());
    }

    @Test
    void should_returnNoRecipes_when_askingForRecommendationsForIngredientsWithNoRecipes() {
        List<Ingredient> ingredients = List.of(
                new Ingredient("egg", 1, Unit.EACH),
                new Ingredient("butter", 1, Unit.GRAMS));

        RecipeRecommender recommender = new RecipeRecommender(new TestRecipeRepository(Collections.emptyList()));
        List<Recipe> recipes = recommender.recommend(ingredients);
        assertTrue(recipes.isEmpty());
    }

    @Test
    void should_returnMatchingRecipe_when_askingForRecommendationsWithOneIngredient() {
        String recipeName = "omelette";
        List<Ingredient> recipeIngredients = List.of(new Ingredient("egg", 1, Unit.EACH));
        Recipe expectedRecipe = new Recipe(recipeName, recipeIngredients);
        RecipeRepository recipeRepository = new TestRecipeRepository(List.of(expectedRecipe));

        List<Ingredient> queryIngredients = List.of(new Ingredient("egg", 1, Unit.EACH));
        List<Recipe> recommendedRecipes = new RecipeRecommender(recipeRepository).recommend(queryIngredients);

        assertEquals(1, recommendedRecipes.size());
        assertEquals(expectedRecipe, recommendedRecipes.get(0));
    }

    @Test
    void should_returnMatchingRecipes_when_askingForRecommendationsWithMultipleIngredients() {
        Ingredient egg = new Ingredient("egg", 1, Unit.EACH);
        Ingredient flour = new Ingredient("flour", 1, Unit.GRAMS);

        Recipe pastryRecipe = new Recipe("pastry", List.of(egg, flour));
        Recipe pancakeRecipe = new Recipe("pancakes", List.of(egg, flour));
        RecipeRepository recipeRepository = new TestRecipeRepository(List.of(pastryRecipe, pancakeRecipe));

        List<Ingredient> queryIngredients = List.of(egg, flour);
        List<Recipe> recommendedRecipes = new RecipeRecommender(recipeRepository).recommend(queryIngredients);

        assertEquals(2, recommendedRecipes.size());
        assertTrue(recommendedRecipes.contains(pastryRecipe));
        assertTrue(recommendedRecipes.contains(pancakeRecipe));
    }

    @Test
    void should_returnMatchingRecipe_when_recipeOnlyMatchesOneIngredient() {
        Ingredient egg = new Ingredient("egg", 1, Unit.EACH);
        Ingredient flour = new Ingredient("flour", 100, Unit.GRAMS);

        Recipe recipe = new Recipe("omelette", List.of(egg));
        RecipeRepository recipeRepository = new TestRecipeRepository(List.of(recipe));

        List<Ingredient> queryIngredients = List.of(egg, flour);
        List<Recipe> recommendedRecipes = new RecipeRecommender(recipeRepository).recommend(queryIngredients);

        assertEquals(1, recommendedRecipes.size());
        assertEquals(recipe, recommendedRecipes.get(0));
    }

    @Test
    void should_returnMatchingRecipes_when_differentRecipesHaveDifferentIngredients() {
        Ingredient egg = new Ingredient("egg", 1, Unit.EACH);
        Ingredient flour = new Ingredient("flour", 1, Unit.GRAMS);

        Recipe omeletteRecipe = new Recipe("omelette", List.of(egg));
        Recipe crepeRecipe = new Recipe("crepe", List.of(flour));
        RecipeRepository recipeRepository = new TestRecipeRepository(List.of(omeletteRecipe, crepeRecipe));

        List<Ingredient> queryIngredients = List.of(egg, flour);
        List<Recipe> recommendedRecipes = new RecipeRecommender(recipeRepository).recommend(queryIngredients);

        assertEquals(2, recommendedRecipes.size());
        assertTrue(recommendedRecipes.contains(omeletteRecipe));
        assertTrue(recommendedRecipes.contains(crepeRecipe));
    }

    @Test
    void should_returnNoRecipes_when_recipeIngredientQuantiyIsMoreThanQueryQuantity() {
        Recipe recipe = new Recipe("ghee", List.of(new Ingredient("butter", 200, Unit.GRAMS)));
        RecipeRepository recipeRepository = new TestRecipeRepository(List.of(recipe));

        List<Ingredient> queryIngredients = List.of(new Ingredient("butter", 100, Unit.GRAMS));
        List<Recipe> recipes = new RecipeRecommender(recipeRepository).recommend(queryIngredients);
        assertTrue(recipes.isEmpty());
    }

    @Test
    void should_returnMatchingRecipe_when_recipeIngredientQuantityIsLessThanQueryQuantity() {
        Recipe recipe = new Recipe("ghee", List.of(new Ingredient("butter", 100, Unit.GRAMS)));
        RecipeRepository recipeRepository = new TestRecipeRepository(List.of(recipe));

        List<Ingredient> queryIngredients = List.of(new Ingredient("butter", 200, Unit.GRAMS));
        List<Recipe> recipes = new RecipeRecommender(recipeRepository).recommend(queryIngredients);
        assertEquals(1, recipes.size());
    }

    @Test
    void should_returnMatchingRecipe_when_recipeIngredientQuantityEqualToQueryQuantity() {
        Recipe recipe = new Recipe("ghee", List.of(new Ingredient("butter", 100, Unit.GRAMS)));
        RecipeRepository recipeRepository = new TestRecipeRepository(List.of(recipe));

        List<Ingredient> queryIngredients = List.of(new Ingredient("butter", 100, Unit.GRAMS));
        List<Recipe> recipes = new RecipeRecommender(recipeRepository).recommend(queryIngredients);
        assertEquals(1, recipes.size());
    }
}
