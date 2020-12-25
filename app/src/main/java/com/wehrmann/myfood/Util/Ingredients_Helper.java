package com.wehrmann.myfood.Util;

import com.wehrmann.myfood.Data.Ingredients;

public class Ingredients_Helper {

    public static Ingredients split_input(String input){
        Ingredients ingredients=new Ingredients(  );
        String[] temp=input.split(",");
        ingredients.setName(temp[0]);
        ingredients.setQuantity(temp[1]);
        switch (temp[2]){
            case "ml":
                ingredients.setUnit(0);
                break;
            case "g":
                ingredients.setUnit(1);
                break;
            case "stk":
                ingredients.setUnit(2);
                break;
            case "El":
                ingredients.setUnit(3);
                break;
            case "Tl":
                ingredients.setUnit(4);
                break;
            default:
                ingredients.setUnit(1);
        }
        return ingredients;

    }


}
