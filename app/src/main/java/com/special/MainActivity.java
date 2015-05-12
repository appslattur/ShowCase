package com.special;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.special.DataBaseHandler.AsyncTasks.EntryTask;
import com.special.DataBaseHandler.AsyncTasks.UpdateTask;
import com.special.DataStorage.Messages.EntryMessage;
import com.special.DataStorage.Messages.UpdateMessage;
import com.special.ServiceImp.ServiceHandler.AppService;
import com.special.ServiceImp.Util.DataStreamTest;
import com.special.ServiceImp.Util.HardCodedTestEntries;
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

        setUpDataBase();
        setUpService();

        boolean isSpecial = false;
        try{
            Bundle b = getIntent().getExtras();
            isSpecial = b.getBoolean("isNotification");
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Bundle from Notification is a bit blurry", Toast.LENGTH_SHORT).show();
        }


        if(isSpecial){
            changeFragment(new TransitionListFragment());
        }
        else {
            changeFragment(new HomeFragment());

        }

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

    ///
    // DataBase Initialization
    //
    ///

    private void createTestEntries() {

        try {
            EntryMessage message =
                    new EntryTask(getApplicationContext())
                    .execute(new EntryMessage(new HardCodedTestEntries().getEntries(false)))
                    .get();

            if(!message.isError()) {
                //Toast.makeText(getApplicationContext(), "DataBase has been initialized", Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "DataBase initialization failed", Toast.LENGTH_SHORT).show();
        }


    }

    private void setUpDataBase() {

        try {
            UpdateMessage updateMessage =
                    new UpdateTask(getApplicationContext())
                    .execute(new UpdateMessage(UpdateMessage.UPDATEMESSAGE_UTILTYPE_COUNT)).get();

            if(!updateMessage.isError()) {
                //Toast.makeText(getApplicationContext(), "DataBase is Empty", Toast.LENGTH_SHORT).show();
                createTestEntries();
                return;
            }

            //Toast.makeText(getApplicationContext(), "DataBase is not Empty", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "setUpDataBase Async task failed", Toast.LENGTH_LONG).show();
        }

    }

    private void setUpService() {

        ActivityManager manager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if(AppService.class.getName().equals(service.service.getClassName())) {
                return;
            }
        }

        startService(new Intent(this, AppService.class));
    }

}
