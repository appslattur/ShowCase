package com.special;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.special.ServiceImp.ServiceHandler.AppService;
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
                setUpService();
            }
        });
        

        
        
    }

    private void setUpService() {

        ActivityManager manager = (ActivityManager) getActivity().getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if(AppService.class.getName().equals(service.service.getClassName())) {
                return;
            }
        }

        getActivity().getApplicationContext().startService(new Intent(getActivity().getApplicationContext(), AppService.class));
    }

}
