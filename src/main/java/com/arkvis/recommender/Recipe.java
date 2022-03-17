package com.arkvis.recommender;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Recipe {
    private final String name;
    private final Map<String, Ingredient> ingredients;

    public Recipe(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = createIngredientsMap(ingredients);
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return List.copyOf(ingredients.values());
    }

    public boolean canMakeWithIngredients(List<Ingredient> other) {
        Map<String, Ingredient> givenIngredients = createIngredientsMap(other);
        return isSubsetOfIngredients(givenIngredients) && hasCorrectQuantities(givenIngredients);
    }

    private boolean isSubsetOfIngredients(Map<String, Ingredient> givenIngredients) {
        return givenIngredients.size() >= ingredients.size() && givenIngredients.keySet().containsAll(ingredients.keySet());
    }

    private boolean hasCorrectQuantities(Map<String, Ingredient> givenIngredients) {
        return givenIngredients.keySet().stream()
                .filter(ingredients::containsKey)
                .noneMatch(name -> ingredients.get(name).getQuantity() > givenIngredients.get(name).getQuantity());
    }

    private Map<String, Ingredient> createIngredientsMap(List<Ingredient> ingredients) {
        return ingredients.stream()
                .collect(Collectors.toMap(Ingredient::getName, Function.identity()));
    }
}
