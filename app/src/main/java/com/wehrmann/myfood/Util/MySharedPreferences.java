package com.wehrmann.myfood.Util;

import android.content.Context;
import android.content.SharedPreferences;




public class MySharedPreferences {

    SharedPreferences mySPR;
    private String count;

    public MySharedPreferences(Context context, String username) {
        mySPR = context.getSharedPreferences("MyFood", 0);
        setMySPR(username);
    }

    public MySharedPreferences(Context context,String key, String recipe) {
        mySPR = context.getSharedPreferences("MyFood", 0);
        setMySPR(key,recipe);
    }



    public MySharedPreferences(Context context) {
        mySPR = context.getSharedPreferences("MyFood", 0);

    }

    private void setMySPR(String username) {

        SharedPreferences.Editor editor=mySPR.edit();
        editor.putString("username",username);
        editor.commit();

    }

    private void setMySPR(String key, String recipe) {

        SharedPreferences.Editor editor=mySPR.edit();
        editor.putString(key,recipe);
        editor.commit();

    }
    public String getSPR(){
        SharedPreferences.Editor editor=mySPR.edit();
        return mySPR.getString("username","");
    }
    public String getSPR_Recipe(int i){
        return mySPR.getString("RECIPE"+1,"");
    }
    public void setCount(Integer count){
        SharedPreferences.Editor editor=mySPR.edit();
        editor.putString("count",count.toString());
        editor.commit();
    }
    public String getCount() {
        count=mySPR.getString("count", String.valueOf(-1));

        return count;
    }
}
