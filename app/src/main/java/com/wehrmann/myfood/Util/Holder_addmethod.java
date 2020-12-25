package com.wehrmann.myfood.Util;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wehrmann.myfood.R;


public class Holder_addmethod extends RecyclerView.ViewHolder implements View.OnClickListener {
    OnClickListener_view onClickListener_view;
    TextView step;
    Button btn_add,btn_remove;
    EditText method;
    public Holder_addmethod(@NonNull View itemView) {
        super(itemView);
        step=itemView.findViewById(R.id.step_method);
        btn_add=itemView.findViewById(R.id.new_method);
        btn_remove=itemView.findViewById(R.id.delete_method);
        method=itemView.findViewById(R.id.method_add);
    }

    @Override
    public void onClick(View v) {
        onClickListener_view.myOnClick(getAdapterPosition(),v);

    }
}
