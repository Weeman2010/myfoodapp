package com.wehrmann.myfood.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wehrmann.myfood.R;
import com.wehrmann.myfood.Util.IO_Helper;
import com.wehrmann.myfood.Util.MySharedPreferences;

public class Welcome extends AppCompatActivity {
    EditText user_name;
    Button btn_ok;
    MySharedPreferences mySPR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        user_name=findViewById(R.id.user_name);
        btn_ok=findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                if(user_name.length()<3){
                    Toast.makeText(Welcome.this,getText(R.string.toshort),Toast.LENGTH_SHORT).show();
                }else{
                    mySPR=new MySharedPreferences(Welcome.this,user_name.getText().toString());
                    if(IO_Helper.server_available()){
                          if(opendialog()){
                              IO_Helper.loadRecipefromServer(mySPR);
                          }else{
                              finish();

                          }

                    }

                    finish();
                }
            }
        });

    }

    private boolean opendialog() {
        return true;
    }
}