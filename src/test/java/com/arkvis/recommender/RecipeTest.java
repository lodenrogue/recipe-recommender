package com.arkvis.recommender;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeTest {

    @Test
    void should_createRecipeWithName_when_creatingRecipe() {
        String name = "TEST_NAME";
        Recipe recipe = new Recipe(name, Collections.emptyList());
        assertEquals(name, recipe.getName());
    }

    @Test
    void should_createRecipeWithIngredients_when_creatingRecipe() {
        List<Ingredient> ingredients = List.of(
                new Ingredient("butter", 100, Unit.GRAMS),
                new Ingredient("egg", 1, Unit.EACH));

        Recipe recipe = new Recipe("TEST_NAME", ingredients);
        assertEquals(ingredients, recipe.getIngredients());
    }
}
