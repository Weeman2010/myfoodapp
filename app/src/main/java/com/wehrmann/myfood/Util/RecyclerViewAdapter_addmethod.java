package com.wehrmann.myfood.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wehrmann.myfood.Data.Method;
import com.wehrmann.myfood.R;

import java.util.ArrayList;

public class RecyclerViewAdapter_addmethod extends RecyclerView.Adapter<Holder_addmethod> {
    private ArrayList<Method> methodArrayList;
    private OnClickListener_view myOnClickListener;
    private Context context;

    public RecyclerViewAdapter_addmethod(ArrayList<Method> methodArrayList, OnClickListener_view myOnClickListener, Context context) {
        this.methodArrayList = methodArrayList;
        this.myOnClickListener = myOnClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder_addmethod onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_method_add,parent,false);

        return new Holder_addmethod(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder_addmethod holder, int position) {
        if(position==0){
            holder.btn_remove.setVisibility(View.INVISIBLE);
        }
        if(position>0){
            holder.step.setText(position+1+".");
            holder.btn_add.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return methodArrayList.size();
    }

    public void refresh() {
        notifyDataSetChanged();
    }
}
