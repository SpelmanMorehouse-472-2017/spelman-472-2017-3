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
    
    public RecipesClass (String recipes, ArrayList<String> recipeListReturned) {
		this.recipeList= recipeListReturned;
		this.recipeName = recipes;
	}
    public RecipesClass (ArrayList<String> recipeListReturned) {
		this.recipeList= recipeListReturned;
	}
    
    public String returnRecipeName() {
    		return recipeName;
    }
    public ArrayList<String> returnRecipes() {
		return recipeList;
    } 

    public String getMessage() {
        return message;
    }
    
}
