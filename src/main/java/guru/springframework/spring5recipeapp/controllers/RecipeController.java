package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.exceptions.NotFoundException;
import guru.springframework.spring5recipeapp.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static java.lang.Long.parseLong;

@Controller
public class RecipeController {

    private static final String RECIPE_RECIPEFORM_URL = "recipes/recipeform";
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipes/{id}/show")
    public String showById(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipes/show";
    }

    @GetMapping
    @RequestMapping("/recipes/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return RECIPE_RECIPEFORM_URL;
    }

    @PostMapping
    @RequestMapping("/recipes/")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult){
        if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(objectError -> {
                System.out.println(objectError.toString());
            });

            return RECIPE_RECIPEFORM_URL;
        }
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipes/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("/recipes/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return  RECIPE_RECIPEFORM_URL;
    }

    @GetMapping
    @RequestMapping("/recipes/{id}/delete")
    public String deleteById(@PathVariable String id) {
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/all";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception e){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", e);

        return modelAndView;
    }

}
