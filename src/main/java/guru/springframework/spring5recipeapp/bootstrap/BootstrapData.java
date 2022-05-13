package guru.springframework.spring5recipeapp.bootstrap;

import guru.springframework.spring5recipeapp.domain.*;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class BootstrapData implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public BootstrapData(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Object initialization
        recipeRepository.save(getTacoRecipe());
        // SQL initialization
        //recipeRepository.save(getGuacRecipe());
    }

    private Recipe getTacoRecipe() {
        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setDescription("Spicy Grilled Chicken Tacos");
        tacoRecipe.setCookTime(15);
        tacoRecipe.setPrepTime(20);
        tacoRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacoRecipe.setDifficulty(Difficulty.MODERATE);
        tacoRecipe.setDirections("1. Prepare a gas or charcoal grill for medium-high, direct heat\n" +
                "Make the marinade and coat the chicken:\n" +
                "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.");
        tacoRecipe.setServings(6);
        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
        tacoNotes.setRecipe(tacoRecipe);
        tacoRecipe.setNotes(tacoNotes);

        Optional<UnitOfMeasure> uomTblOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        tacoRecipe.getIngredients().add(new Ingredient("ancho chilli powder", new BigDecimal(2), uomTblOptional.get(), tacoRecipe));

        return tacoRecipe;
    }

    private Recipe getGuacRecipe() {

        Optional<Recipe> guacRecipeOptional = recipeRepository.findByDescription("How to Make the Best Guacamole");
        Recipe guacRecipe = guacRecipeOptional.get();
        guacRecipe.setDifficulty(Difficulty.EASY);
        return guacRecipe;
    }
}
