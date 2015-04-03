package com.special;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;

import com.special.appslattur.DatabaseHelper.TestHandler;
import com.special.menu.ResideMenu;
import com.special.menu.ResideMenuItem;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private ResideMenu resideMenu;
    private ResideMenuItem itemHome;
    private ResideMenuItem afslaettirList;
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpMenu();
        changeFragment(new HomeFragment());
        TestHandler.initHandler(getBaseContext());
    }

    private void setUpMenu() {
    	
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.hi_background);
        resideMenu.attachToActivity(this);
        resideMenu.setShadowVisible(true);
        resideMenu.setHeaderView(findViewById(R.id.actionbar));
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip. 
        resideMenu.setScaleValue(0.6f);

        itemHome     = new ResideMenuItem(this, R.drawable.ic_home,     "Home");

        //itemElements  = new ResideMenuItem(this, R.drawable.ic_elements_alternative,  "Elements");
        //itemList1 = new ResideMenuItem(this, R.drawable.ic_list_2, "List 1");

        //Þetta verður listi með okkar afsláttum
        afslaettirList = new ResideMenuItem(this, R.drawable.ic_list_1, "Afslættir");

        itemHome.setOnClickListener(this);
        //itemElements.setOnClickListener(this);
        //itemList1.setOnClickListener(this);

        afslaettirList.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome);
        //resideMenu.addMenuItem(itemElements);
        //resideMenu.addMenuItem(itemList1);
        resideMenu.addMenuItem(afslaettirList);
        
        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu();
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == itemHome){
            changeFragment(new HomeFragment());
        }
        /*
        else if (view == itemElements){
            changeFragment(new ElementsFragment());
        }

        else if (view == itemList1){
            changeFragment(new ListFragment());
        }
        */
        else if (view == afslaettirList){
            changeFragment(new TransitionListFragment());
        }

        resideMenu.closeMenu();
    }

    //Example of menuListener
    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() { }

        @Override
        public void closeMenu() { }
    };

    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    //return the residemenu to fragments
    public ResideMenu getResideMenu(){
        return resideMenu;
    }
    
    @Override
    public void onBackPressed() {
    	if (resideMenu.isOpened()){
    		resideMenu.closeMenu();
    	} else {
    		resideMenu.openMenu();
    	}
    }
}
