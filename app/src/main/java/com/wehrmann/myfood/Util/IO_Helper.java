package com.wehrmann.myfood.Util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.Gson;
import com.wehrmann.myfood.Data.Ingredients;
import com.wehrmann.myfood.Data.Recipe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

public class IO_Helper {
    private static ArrayList<Ingredients> temp=new ArrayList<>(  );

    public static void saveRecipe(Context context, ArrayList<Recipe> recipe,MySharedPreferences mySharedPreferences){
        Gson gson = new Gson();


        Integer count=0;
        for(int i=0;i<recipe.size();i++){
            String json = gson.toJson(recipe.get(i));
            mySharedPreferences=new MySharedPreferences(context,"RECIPE"+i,json);
            count=i;
        }
        mySharedPreferences.setCount(count);
    }
    public static ArrayList<Recipe> loadRecipe(MySharedPreferences mySharedPreferences){
        ArrayList<Recipe> recipe_list=new ArrayList<>();
        int i=Integer.parseInt(mySharedPreferences.getCount());
        Gson gson = new Gson();
        for(int j=0;j<=i;j++){
        String json = mySharedPreferences.getSPR_Recipe(j);
        Recipe recipe = gson.fromJson(json, Recipe.class);
        recipe_list.add(recipe);}
        if (recipe_list.isEmpty()){
            return null;
        }else{
        return recipe_list;}
    }
    public static void saveImage(Context context,Bitmap bitmap) throws IOException {
        File file=new File(context.getExternalFilesDir("MyFood"),"TEST"+".jpg");
        FileOutputStream outputStream= new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        outputStream.flush();
        outputStream.close();
    }

    public static Bitmap loadImage(Context context,String filePath){
        if(filePath==""){ return null;}
        File file = new File(context.getExternalFilesDir("MyProfiles"), filePath + ".jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(String.valueOf(file));
        return bitmap;
    }


    public static Collection<? extends Ingredients> load_textfile(Context context,Intent data) {
            temp.clear();
            try {
                InputStream inputStream = context.getContentResolver( ).openInputStream(data.getData( ));
                String url = "temp.txt";
                File file = new File(context.getExternalFilesDir("MyFood"), url);
                FileOutputStream out = new FileOutputStream(file);
                byte[] buffer = new byte[8 * 1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                out.close( );
                inputStream = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder text = new StringBuilder( );


                String line;
                int i = 0;
                while ((line = br.readLine( )) != null) {
                    temp.add(Ingredients_Helper.split_input(line));
                }
                br.close( );
                //file.delete();
            }
            catch(Exception e){
                e.printStackTrace();
            }



        
        return temp;
    }

    public static void loadRecipefromServer(MySharedPreferences mySPR) {

    }

    public static boolean server_available() {
        return true;
    }
}
