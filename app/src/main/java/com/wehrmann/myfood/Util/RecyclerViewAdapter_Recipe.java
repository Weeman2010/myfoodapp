package com.wehrmann.myfood.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wehrmann.myfood.Data.Recipe;
import com.wehrmann.myfood.R;

import java.util.ArrayList;

public class RecyclerViewAdapter_Recipe extends RecyclerView.Adapter<Holder_Recipe> {

    private Context context;
    MyOnClickListener myOnClickListener;
    ArrayList<Recipe> recipeArrayList;

    public RecyclerViewAdapter_Recipe(Context context, MyOnClickListener myOnClickListener, ArrayList<Recipe> previewArrayList) {
        this.context = context;
        this.myOnClickListener = myOnClickListener;
        this.recipeArrayList = previewArrayList;
    }

    @NonNull
    @Override
    public Holder_Recipe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_recipe,parent,false);
        return new Holder_Recipe(view,myOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder_Recipe holder, int position) {
        Bitmap bitmap=IO_Helper.loadImage(context,recipeArrayList.get(position).getImagePath());
        String description=recipeArrayList.get(position).getRecipe_name();
        if(bitmap!=null){
        holder.image.setImageBitmap(bitmap);}
        else{holder.image.setImageResource(R.drawable.default_image);}
        holder.textview.setText(description);



    }

    @Override
    public int getItemCount() {
        return recipeArrayList.size();
    }
}
