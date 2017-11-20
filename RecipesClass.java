package com.google.training.helloworld;
import java.util.*;
public class RecipesClass {
    public String message = "Hello World";
    public ArrayList<String> recipeList;
    public String recipeName;

    public RecipesClass () {
    }

    public RecipesClass (String name) {
        this.message = "Hello " + name + "!";
    }
    
    public RecipesClass (ArrayList<String> recipeListReturned) {
		this.recipeList= recipeListReturned;
	}
    
    public ArrayList<String> returnRecipes() {
		return recipeList;
    } 

    public String getMessage() {
        return message;
    }
    
}