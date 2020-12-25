package com.wehrmann.myfood.Data;


import android.media.Image;
import android.net.Uri;
import com.wehrmann.myfood.Util.Category;

import java.io.Serializable;

public class Recipe implements Serializable {

    private static String[] method_split;
    private static String recipe_method;
    private static String[] ingredients_split;
    private static String recipe_ingredients;
    private static String recipe_name;




    private Category category;
    private String imagePath;


    public Recipe(Category category,String recipe_name, String recipe_ingredients, String recipe_method, String imagePath) {
        this.recipe_name = recipe_name;
        this.recipe_ingredients = recipe_ingredients;
        this.recipe_method = recipe_method;
        if(imagePath==null){
            this.imagePath="";
        }else{
        this.imagePath=imagePath;}
        this.category=category;
        split_ingredients();
        split_method();
    }


    private static void  split_ingredients() {
        ingredients_split = recipe_ingredients.split(",|;");
    }

    public Category getCategory() {
        return category;
    }

    public static void split_method(){
        method_split=recipe_method.split(".");
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public String getRecipe_ingredients() {
        return recipe_ingredients;
    }

    public String getRecipe_method() {

        return recipe_method;
    }

    public String getImagePath() {
        return imagePath;
    }

}
