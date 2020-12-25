package com.wehrmann.myfood.Activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.wehrmann.myfood.Data.Ingredients;
import com.wehrmann.myfood.Data.Method;
import com.wehrmann.myfood.Data.Recipe;
import com.wehrmann.myfood.R;
import com.wehrmann.myfood.Util.Category;
import com.wehrmann.myfood.Util.IO_Helper;
import com.wehrmann.myfood.Util.OnClickListener_view;
import com.wehrmann.myfood.Util.RecyclerViewAdapter_addingredients;
import com.wehrmann.myfood.Util.RecyclerViewAdapter_addmethod;
import com.wehrmann.myfood.Util.SquareImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class AddIngredientsActivity extends AppCompatActivity implements OnClickListener_view {

    private Spinner dropdown;
    private Category category;
    private SquareImageView imageView;
    private Recipe recipe;

    private Bitmap bitmap;
    private int new_ingredient=1;
    private RecyclerView recyclerView_ingredients,recyclerView_method;
    private ArrayList<Ingredients> ingredientsArrayList=new ArrayList<>( );
    private RecyclerViewAdapter_addingredients RV_adapter;
    private RecyclerViewAdapter_addmethod RV_adapter_method;
    private Button method,btnload;
    private final int CHOOSE_PICTURE=1234;
    private boolean doubleBackToExitPressedOnce = false;
    private long back_pressed;
    private final int LOADTEXTFILE=132;
    private BottomNavigationView bottomNavigationView;
    private boolean first_time=true;
    private ArrayList<Method> methodArrayList=new ArrayList<>(  );



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecipe);
        bottomNavigationView=findViewById(R.id.menu_addingredients);
        recyclerView_ingredients=findViewById(R.id.recyclerview_ingredients_add);
        recyclerView_method=findViewById(R.id.recyclerview_method_add);
        RV_adapter_method=new RecyclerViewAdapter_addmethod(methodArrayList,this,this);
        recyclerView_method.setAdapter(RV_adapter_method);
        RV_adapter=new RecyclerViewAdapter_addingredients(this,this,ingredientsArrayList);
        recyclerView_ingredients.setAdapter(RV_adapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener( ) {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.ingredients_recyclerview:
                        initIngredients(first_time);

                        break;
                    case R.id.method_recyclerview:
                        initMethod(first_time);
                        break;
                }
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.ingredients_recyclerview);



    }

    private void initMethod(boolean first_time) {
        if (true) {
            methodArrayList.add(new Method(1,""));
            RV_adapter_method.refresh();
            this.first_time=false;
        }
        recyclerView_ingredients.setVisibility(View.INVISIBLE);
        recyclerView_method.setVisibility(View.VISIBLE);
    }

    private void initIngredients(boolean first_time) {
        recyclerView_ingredients.setVisibility(View.VISIBLE);
        recyclerView_method.setVisibility(View.INVISIBLE);
        btnload=findViewById(R.id.btn_load);
        btnload.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("text/*");
                startActivityForResult(intent,LOADTEXTFILE);
            }
        });
        method=findViewById(R.id.btn_method_add);
        method.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
               endActivity(new Recipe(Category.VORSPEISE,"TEST","Tomaten,3,stk","ist doch klar",null));
            }
        });


        if(first_time){
            ingredientsArrayList.add(new Ingredients("",0,""));
            RV_adapter.refresh(true);
        this.first_time=false;
        };

        imageView=findViewById(R.id.squareimage_add);
        imageView.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {

                Intent gallery = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
                startActivityForResult(gallery,CHOOSE_PICTURE);
            }
        });

        dropdown = findViewById(R.id.spinner_category);
        String[] items = new String[]{"Kategorie Vorspeise","Kategorie Vegetarisch","Kategorie Fisch","Kategorie Huhn","Kategorie Rind","Kategorie Schwein","Kategorie Nachtisch"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener( ) {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        category=Category.VORSPEISE ;
                    break;
                    case 1:
                        category=Category.VEGETARISCH ;
                        break;
                    case 2:
                        category=Category.FISCH ;
                        break;
                    case 3:
                        category=Category.HUHN ;
                        break;
                    case 4:
                        category=Category.RIND;
                        break;
                    case 5:
                        category=Category.SCHWEIN ;
                        break;
                    case 6:
                        category=Category.NACHTISCH ;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void endActivity(Recipe recipe) {
        Intent intent=new Intent();
        intent.putExtra("RECIPE",recipe);
        setResult(RESULT_OK,intent);
        finish();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CHOOSE_PICTURE && resultCode==RESULT_OK ){
            try {
               InputStream inputStream = getContentResolver( ).openInputStream(Objects.requireNonNull(data.getData( )));
               bitmap = BitmapFactory.decodeStream(inputStream);
               rotateBitmap(bitmap);
           }
           catch(Exception e){
           }
        }

        if(requestCode==LOADTEXTFILE && resultCode==RESULT_OK){
            ingredientsArrayList.clear();
            ingredientsArrayList.addAll(IO_Helper.load_textfile(this,data));
            RV_adapter.refresh(false);


        }
    }

    private Recipe buildrecipe() {
        return null;
    }

    @Override
    public void onBackPressed() {
                if (back_pressed + 3000 > System.currentTimeMillis()){
                    Intent intent=new Intent();
                    setResult(RESULT_CANCELED,intent);
                    finish();
                }
                else{
                    Snackbar.make(recyclerView_ingredients,"Zum beenden ohne Speichern erneut drücken",Snackbar.LENGTH_SHORT).show();
                }
                back_pressed = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy( );
    }
    @Override
    protected void onStop () {
        super.onStop();
    }

    private void rotateBitmap(Bitmap bitmap) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String timestamp = simpleDateFormat.format(new Date( ));
        String url="test"+timestamp+".jpg";

        File file=new File(getExternalFilesDir("MyFood"),url);
        FileOutputStream outputStream= new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        outputStream.flush();
        outputStream.close();

        ExifInterface exifInterface=null;
            exifInterface = new ExifInterface(file.getAbsolutePath());

            int orientation=exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_UNDEFINED);
        Matrix matrix=new Matrix(  );
            switch (orientation){
                case ExifInterface.ORIENTATION_ROTATE_90:
                    matrix.setRotate(90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    matrix.setRotate(180);
                    break;
                default:
                    matrix.setRotate(0);
            }
            Bitmap rotatedBitmap= Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
            if(rotatedBitmap==null){
                imageView.setImageBitmap(bitmap);
            }else{
            imageView.setImageBitmap(rotatedBitmap);}


    }

    @Override
    public void myOnClick(int position, View view) {
        if(view.getId()==R.id.new_add){

            if(RV_adapter.savedata()){
                ingredientsArrayList.add(new Ingredients());
                RV_adapter.refresh(true);
        }}
        if(view.getId()==R.id.delete_add){
            ingredientsArrayList.remove(RV_adapter.reverselist(position,ingredientsArrayList.size()));
            RV_adapter.refresh(true);
        }
        if(view.getId()==R.id.delete_method){

        }
        if(view.getId()==R.id.new_method){

        }
    }
}
