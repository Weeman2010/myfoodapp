package com.wehrmann.myfood.Util;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.wehrmann.myfood.Fragments.FoodFragment;

import java.util.ArrayList;

public class ViewPager2Adapter_Main extends FragmentStateAdapter {

    ArrayList<FoodFragment> fragmentArrayList;
    public ViewPager2Adapter_Main(@NonNull FragmentActivity fragmentActivity, ArrayList<FoodFragment> fragmentArrayList) {
        super(fragmentActivity);
        this.fragmentArrayList=fragmentArrayList;
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentArrayList.size();
    }
}
