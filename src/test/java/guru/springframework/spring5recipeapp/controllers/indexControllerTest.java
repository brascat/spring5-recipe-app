package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.service.RecipeService;
import guru.springframework.spring5recipeapp.controllers.indexController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class indexControllerTest {

    indexController indexController;
    @Mock
    RecipeService recipeService;

    @Mock
    Model model;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        indexController = new indexController(recipeService);
    }

    @Test
    void getRecipesPage() {

        assertEquals("recipes", indexController.getRecipesPage(model));
        verify(recipeService, times(1)).getAllRecipes();
        verify(model, times(1)).addAttribute("recipes", recipeService.getAllRecipes());
    }
}