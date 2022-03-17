package com.arkvis.recommender;

import java.util.List;

public interface RecipeRepository {

    List<Recipe> findByIngredients(List<Ingredient> ingredients);
}
