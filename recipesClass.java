package com.google.devrel.training.conference;


public class recipesClass {
    public String message = "Hello World";

    public recipesClass () {
    }

    public recipesClass (String recipe) {
        this.message = "User Recipe Selection: " + recipe + "!";
    }
    

    public String getMessage() {
        return message;
    }
}