package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.lang.Long.parseLong;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipes/show/{id}")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(parseLong(id)));
        return "recipes/show";
    }
}
