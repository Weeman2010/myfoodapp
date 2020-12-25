package com.wehrmann.myfood.Activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager2.widget.ViewPager2;


import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.wehrmann.myfood.Data.Recipe;
import com.wehrmann.myfood.Fragments.FoodFragment;
import com.wehrmann.myfood.R;
import com.wehrmann.myfood.Util.Category;
import com.wehrmann.myfood.Util.IO_Helper;
import com.wehrmann.myfood.Util.MySharedPreferences;
import com.wehrmann.myfood.Util.ViewPager2Adapter_Main;
import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity  {
    private MySharedPreferences mySPR;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ArrayList<FoodFragment> myFragmentArrayList;
    private String username;
    private BottomNavigationView bottomNavigationView;
    private ArrayList<Recipe> recipe=new ArrayList<>();
    private ArrayList<Recipe> list_vegetarisch,list_rind,list_huhn,list_nachtisch,list_vorspeise,list_fisch,list_pork;
   private SearchView searchView;
    private final int REQUEST_ADD=1234;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myFragmentArrayList=new ArrayList<>();
        list_vegetarisch=new ArrayList<>();
        list_fisch=new ArrayList<>();
        list_huhn=new ArrayList<>();
        list_nachtisch=new ArrayList<>();
        list_rind=new ArrayList<>();
        list_vorspeise=new ArrayList<>();
        list_pork=new ArrayList<>();
        checkuser();
        loadrecipes();
        initFragments();
        initTabs(Category.VORSPEISE);
        initNavbar();
    }

    private void initTabs(final Category cat) {
        tabLayout=findViewById(R.id.tab_layout);
        viewPager2=findViewById(R.id.viewPager2);
        ViewPager2Adapter_Main adapter=new ViewPager2Adapter_Main(MainActivity.this,myFragmentArrayList);
        viewPager2.setAdapter(adapter);
        TabLayoutMediator mediator=new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy( ) {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(myFragmentArrayList.get(position).getName());
                if(tab.getText().toString()==cat.toString()){
                viewPager2.setCurrentItem(position);
                System.out.println(cat.toString());}
            }
        });

        mediator.attach();
    }

    private void initNavbar() {
        searchView=findViewById(R.id.search_view);
        searchView.setVisibility(View.GONE);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener( ) {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search:
                        searchView.setVisibility(View.VISIBLE);
                        searchView.setIconified(false);

                        return true;
                    case R.id.add:
                        Intent intent=new Intent(MainActivity.this, AddIngredientsActivity.class);
                        startActivityForResult(intent,REQUEST_ADD);
                        return true;
                }
                return false;
            }

        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener( ) {
            @Override
            public boolean onClose() {
                searchView.setVisibility(View.INVISIBLE);
                return true;
            }
        });

    }

    private void loadrecipes() {
        ArrayList<Recipe> temp= (ArrayList<Recipe>) checkServerforUpdates();
        if(temp != null)
        {recipe.addAll(checkServerforUpdates());}
        temp=IO_Helper.loadRecipe(mySPR);
        if(temp != null)
        {recipe.addAll(temp);}

    }

    private Collection<? extends Recipe> checkServerforUpdates() {
        IO_Helper.loadRecipefromServer(mySPR);

        return null;
    }


    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }
    private void initFragments() {

        if(!recipe.isEmpty()){
        for(int i=0;i<recipe.size();i++){
            Category cat=recipe.get(i).getCategory();
            switch (cat){
                case VEGETARISCH:
                    list_vegetarisch.add(recipe.get(i));
                    break;
                case RIND:
                    list_rind.add(recipe.get(i));
                    break;
                case HUHN:
                    list_huhn.add(recipe.get(i));
                    break;
                case FISCH:
                    list_fisch.add(recipe.get(i));
                    break;
                case NACHTISCH:
                    list_nachtisch.add(recipe.get(i));
                    break;
                case VORSPEISE:
                    list_vorspeise.add(recipe.get(i));
                    break;

            }}

        }

        myFragmentArrayList.add(new FoodFragment(Category.VORSPEISE.toString(),list_vorspeise));
        myFragmentArrayList.add(new FoodFragment(Category.VEGETARISCH.toString(),list_vegetarisch));
        myFragmentArrayList.add(new FoodFragment(Category.FISCH.toString(),list_fisch));
        myFragmentArrayList.add(new FoodFragment(Category.HUHN.toString(),list_huhn));
        myFragmentArrayList.add(new FoodFragment(Category.RIND.toString(),list_rind));
        myFragmentArrayList.add(new FoodFragment(Category.SCHWEIN.toString(),list_pork));
        myFragmentArrayList.add(new FoodFragment(Category.NACHTISCH.toString(),list_nachtisch));

    }

    private void checkuser() {
        mySPR=new MySharedPreferences(MainActivity.this);
        if(mySPR.getSPR().equals("")){
            Intent intent=new Intent(MainActivity.this,Welcome.class);
            startActivity(intent);
        }else{
            username=mySPR.getSPR();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Recipe new_recipe=(Recipe) data.getExtras().getSerializable("RECIPE");

            recipe.add(new_recipe);
            Category temp=new_recipe.getCategory();
            IO_Helper.saveRecipe(getApplicationContext(),recipe,mySPR);

            switch (temp){
                case VEGETARISCH:
                    list_vegetarisch.add(new_recipe);
                    initTabs(temp);
                    break;
                case RIND:
                    list_rind.add(new_recipe);
                    initTabs(temp);
                    break;
                case HUHN:
                    list_huhn.add(new_recipe);
                    initTabs(temp);
                    break;
                case FISCH:
                    list_fisch.add(new_recipe);
                    initTabs(temp);
                    break;
                case NACHTISCH:
                    list_nachtisch.add(new_recipe);
                    initTabs(temp);
                    break;
                case VORSPEISE:
                    list_vorspeise.add(new_recipe);
                    initTabs(temp);
                    break;

            }
        }
    }

}