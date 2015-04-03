package com.special;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.special.menu.ResideMenu;

public class HomeFragment extends Fragment {

    private View parentView;
    private ResideMenu resideMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_home, container, false);
        setUpViews();
        return parentView;
    }

    private void setUpViews() {
        MainActivity parentActivity = (MainActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();

        parentView.findViewById(R.id.gangsetja_takki).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Byrja Skanna!
            }
        });
        

        
        
    }

}
