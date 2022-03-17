package com.arkvis.recommender;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTest {

    @Test
    void should_createIngredientWithName_when_creatingIngredient() {
        String name = "TEST_NAME";
        Ingredient ingredient = new Ingredient(name, 1, Unit.EACH);
        assertEquals(name, ingredient.getName());
    }

    @Test
    void should_createIngredientWithQuantity_when_creatingIngredient() {
        int quantity = 100;
        Ingredient ingredient = new Ingredient("TEST_NAME", quantity, Unit.GRAMS);
        assertEquals(quantity, ingredient.getQuantity());
    }

    @Test
    void should_createIngredientWithUnitOfMeasurement_when_creatingIngredient() {
        Unit unit = Unit.GRAMS;
        Ingredient ingredient = new Ingredient("TEST_NAME", 100, Unit.GRAMS);
        assertEquals(unit, ingredient.getUnit());
    }
}
