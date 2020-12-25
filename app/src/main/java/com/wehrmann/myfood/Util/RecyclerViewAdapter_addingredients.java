package com.wehrmann.myfood.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wehrmann.myfood.Data.Ingredients;
import com.wehrmann.myfood.R;

import java.util.ArrayList;

public class RecyclerViewAdapter_addingredients extends RecyclerView.Adapter<Holder_addingredients> {
    private static final String TAG = "ADDINGREDIENTS";
    private ArrayList<Ingredients> ingredientsArrayList;
    private OnClickListener_view onClickListener_view;
    private Context context;
    private Spinner unit;
    private EditText ingredients,quantity;
    private boolean reverse=true;


    private ArrayAdapter<String> arrayAdapter;
    private String[] units;
    public RecyclerViewAdapter_addingredients(Context context, OnClickListener_view onClickListener_view, ArrayList<Ingredients> ingredientsArrayList) {
        this.ingredientsArrayList = ingredientsArrayList;
        this.onClickListener_view = onClickListener_view;
        this.context = context;
        units=new String[]{"ml","g","stk","El","Tl"};
        this.arrayAdapter=new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item,units);

    }

    @NonNull
    @Override
    public Holder_addingredients onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_ingredients_add,parent,false);
        return new Holder_addingredients(view, onClickListener_view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder_addingredients holder, int position) {
        holder.unit.setAdapter(arrayAdapter);


        if(reverse){
        if(position==0){

            holder.btn_add.setVisibility(View.VISIBLE);
            holder.btn_remove.setVisibility(View.INVISIBLE);

            ingredients=holder.ingredients;
            quantity=holder.quantity;
            unit=holder.unit;
            ingredients.setText("");
            quantity.setText("");
            unit.setSelection(0);
            holder.ingredients.setEnabled(true);
            holder.quantity.setEnabled(true);
            holder.unit.setEnabled(true);
            if(ingredientsArrayList.size()!=1){
                ingredients.requestFocus();
            }

        }
        if(position!=0){

            int reverse=reverselist(position,ingredientsArrayList.size());
            holder.quantity.setText(ingredientsArrayList.get(reverse).getQuantity());
            holder.ingredients.setText(ingredientsArrayList.get(reverse).getName());
            holder.unit.setSelection(ingredientsArrayList.get(reverse).getUnit());
            holder.btn_add.setVisibility(View.INVISIBLE);
            holder.btn_remove.setVisibility(View.VISIBLE);
            holder.ingredients.setEnabled(false);
            holder.quantity.setEnabled(false);
            holder.unit.setEnabled(false);
        }
        }else{

            holder.quantity.setText(ingredientsArrayList.get(position).getQuantity());
            holder.ingredients.setText(ingredientsArrayList.get(position).getName());
            holder.unit.setSelection(ingredientsArrayList.get(position).getUnit());
            holder.btn_add.setVisibility(View.INVISIBLE);
            holder.btn_remove.setVisibility(View.INVISIBLE);
            holder.ingredients.setEnabled(false);
            holder.quantity.setEnabled(false);
            holder.unit.setEnabled(false);

        }

    }

    public int reverselist(int position, int size) {

        int i=0;
        i=size-position-1;

        return i;

    }

    public void refresh(boolean reverse){

        this.reverse=reverse;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ingredientsArrayList.size();
    }

    public boolean savedata() {
        if((ingredients.getText().toString().trim().length() > 0) && (quantity.getText().toString().trim().length() > 0)) {
            ingredientsArrayList.get(ingredientsArrayList.size()-1).setName(ingredients.getText( ).toString( ));
            ingredientsArrayList.get(ingredientsArrayList.size()-1).setQuantity(quantity.getText( ).toString( ));
            ingredientsArrayList.get(ingredientsArrayList.size()-1).setUnit((int)unit.getSelectedItemId());
            return true;
        }
        return false;

    }



}

