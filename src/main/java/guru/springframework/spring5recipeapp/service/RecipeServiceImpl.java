package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Set;

public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getAllRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        //recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        //return recipeRepository.getAll();
        return null;
    }
}
