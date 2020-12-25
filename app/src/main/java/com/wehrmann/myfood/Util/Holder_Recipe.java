package com.wehrmann.myfood.Util;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wehrmann.myfood.R;

public class Holder_Recipe extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView image;
    TextView textview;
    private MyOnClickListener myOnClickListener;
    public Holder_Recipe(@NonNull View itemView, MyOnClickListener myOnClickListener) {
        super(itemView);
        image=itemView.findViewById(R.id.imageView);
        textview=itemView.findViewById(R.id.description);
        itemView.setOnClickListener(this);
        this.myOnClickListener=myOnClickListener;
    }

    @Override
    public void onClick(View v) {
        myOnClickListener.myOnClick(getAdapterPosition());
    }
}
