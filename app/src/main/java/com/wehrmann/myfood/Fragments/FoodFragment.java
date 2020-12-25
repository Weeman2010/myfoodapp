package com.wehrmann.myfood.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wehrmann.myfood.Activitys.ShowRecipeActivity;
import com.wehrmann.myfood.Data.Recipe;
import com.wehrmann.myfood.R;
import com.wehrmann.myfood.Util.MyOnClickListener;
import com.wehrmann.myfood.Util.RecyclerViewAdapter_Recipe;

import java.io.Serializable;
import java.util.ArrayList;


public class FoodFragment extends Fragment implements MyOnClickListener{
    String name;

    ArrayList<Recipe> recipeArrayList;
    RecyclerView recyclerView;
    public FoodFragment(String name,ArrayList<Recipe> recipeArrayList) {
        this.name=name;
        this.recipeArrayList=recipeArrayList;
    }
    public FoodFragment(){
        this.name="Error";
        this.recipeArrayList=new ArrayList<>(  );
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_food, container, false);
        TextView tv=view.findViewById(R.id.textFood);
        tv.setText(name);
        recyclerView=view.findViewById(R.id.recyclerview_food);
        recyclerView.setAdapter(new RecyclerViewAdapter_Recipe(getContext(),this,recipeArrayList));
        return view;
    }

    public String getName() {
        return name;
    }

    @Override
    public void myOnClick(int position) {
        Intent intent = new Intent(getContext(), ShowRecipeActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)recipeArrayList.get(position));
        intent.putExtra("BUNDLE",args);
        startActivity(intent);
    }
}