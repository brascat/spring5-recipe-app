package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import guru.springframework.spring5recipeapp.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class indexController {

    private final RecipeService recipeService;

    public indexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/", "", "/index"})
    public String getIndexPage() {

        //Optional<Category> categoryOptional = categoryRepository.findByCategoryName("American");
        //Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        //System.out.println("Cat ID is " + categoryOptional.get().getId());
        //System.out.printf("UoM ID is " + unitOfMeasureOptional.get().getId());

        return "index";
    }

    @RequestMapping("/recipes")
    public String getRecipesPage(Model model) {

        model.addAttribute("recipes", recipeService.getAllRecipes());

        return "recipes";
    }
}
