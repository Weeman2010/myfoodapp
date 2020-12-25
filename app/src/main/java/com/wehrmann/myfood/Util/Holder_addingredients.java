package com.wehrmann.myfood.Util;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wehrmann.myfood.R;

public class Holder_addingredients extends RecyclerView.ViewHolder implements View.OnClickListener {
     OnClickListener_view onClickListener_view;
     Button btn_add,btn_remove;
     EditText ingredients,quantity;
     Spinner unit;
     LinearLayout linearLayout;

    public Holder_addingredients(@NonNull View itemView, OnClickListener_view onClickListener_view) {
        super(itemView);
        this.onClickListener_view = onClickListener_view;
        btn_add=itemView.findViewById(R.id.new_add);
        btn_remove=itemView.findViewById(R.id.delete_add);
        ingredients=itemView.findViewById(R.id.ingredient_add);
        quantity=itemView.findViewById(R.id.quantity_add);
        unit=itemView.findViewById(R.id.spinner_unit);
        btn_remove.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        linearLayout=itemView.findViewById(R.id.linear_layout_add);
    }

    @Override
    public void onClick(View v) {
        onClickListener_view.myOnClick(getAdapterPosition(),v);

    }
}
