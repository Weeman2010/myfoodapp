package com.wehrmann.myfood.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.wehrmann.myfood.Data.Recipe;
import com.wehrmann.myfood.R;



public class ShowRecipeActivity extends AppCompatActivity {

    private Recipe recipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        recipe = (Recipe) args.getSerializable("ARRAYLIST");
        TextView tv=findViewById(R.id.textView3);
        tv.setText(recipe.getRecipe_name());

    }
}