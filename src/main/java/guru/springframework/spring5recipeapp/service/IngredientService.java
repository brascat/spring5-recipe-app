package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndId(Long recipeId, Long id);

    IngredientCommand saveIngredientCommand(IngredientCommand command);
}
