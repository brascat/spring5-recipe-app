package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController recipeController;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
        recipeController = new RecipeController(recipeService);
    }

    @Test
    void showByIdTest() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipes/show/1")).andExpect(status().isOk()).andExpect(view().name("recipes/show"));

    }
}