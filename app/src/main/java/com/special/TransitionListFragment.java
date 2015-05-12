package com.special;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.special.DataBaseHandler.AsyncTasks.ValueTask;
import com.special.DataStorage.Messages.ValueMessage;
import com.special.ServiceImp.Util.StampExplainer;
import com.special.ServiceImp.Util.StampParty;
import com.special.appslattur.DatabaseHelper.DataBaseHelper;
import com.special.appslattur.DatabaseHelper.TestHandler;
import com.special.appslattur.LocationChainStructure.LocationChain;
import com.special.appslattur.Tools.LogoMatcher;
import com.special.menu.ResideMenu;
import com.special.utils.UISwipableList;

import java.util.ArrayList;

public class TransitionListFragment extends Fragment {

	//Views & Widgets
    private View parentView;
    private UISwipableList listView;
    private TransitionListAdapter mAdapter;
    private ResideMenu resideMenu;
    private DataBaseHelper dbHelper;
    private Bundle bundle;
    private boolean isNotification;
    private boolean isMall;
    
    //Vars
    private String PACKAGE = "IDENTIFY";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //dbHelper = new DataBaseHelper(this.getActivity().getBaseContext());
        //dbHelper.refreshTable();
        parentView = inflater.inflate(R.layout.fragment_list_transition, container, false);
        listView   = (UISwipableList) parentView.findViewById(R.id.listView);
        MainActivity parentActivity = (MainActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();

        try{
            bundle = parentActivity.getIntent().getExtras();
            isNotification = bundle.getBoolean("isNotification");
            isMall = bundle.getBoolean("isMall");

        }
        catch(Exception e){
            isNotification = false;
            isMall = false;
        }

        initView();
        return parentView;
    }

    private void initView(){
    	mAdapter = new TransitionListAdapter(getActivity(), getListData());
        listView.setActionLayout(R.id.hidden_view);
        listView.setItemLayout(R.id.front_layout);
        listView.setAdapter(mAdapter);
        listView.setIgnoredViewHandler(resideMenu);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View viewa, int i, long l) { 
                ListItem item = (ListItem) listView.getAdapter().getItem(i);
                Intent intent = new Intent(getActivity(), TransitionDetailActivity.class);

                Bundle bundle = new Bundle();


                bundle.putInt("img", item.getImageId());
                bundle.putSerializable("Stamp", item.getStamp());
                int[] screen_location = new int[2];
                View view = viewa.findViewById(R.id.item_image);
                view.getLocationOnScreen(screen_location);
                
                bundle.putInt(PACKAGE + ".left", screen_location[0]);
                bundle.putInt(PACKAGE + ".top", screen_location[1]);
                bundle.putInt(PACKAGE + ".width", view.getWidth());
                bundle.putInt(PACKAGE + ".height", view.getHeight());
                
                intent.putExtras(bundle);

                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            }
        });
    }

    private ArrayList<ListItem> getListData(){
        //Hér þarf að setja inn DataBaseHelper
        ArrayList<LocationChain> lChain = TestHandler.getLocations();
        ArrayList<ListItem> listData = new ArrayList<ListItem>();

        try{
            Context c = this.getActivity().getBaseContext();
            //EntryMessage yolo = new EntryTask(c).execute(new EntryMessage(new HardCodedTestEntries().getEntries(false))).get();

            if(isNotification){

                StampParty party = (StampParty) bundle.getSerializable("Stamp");

             
                for(StampExplainer info : party.getStampList()){
                    listData.add(new ListItem(
                            LogoMatcher.getLogoResourceByName(info.getName()),
                            info.getName(),
                            info.getShortDescription(),
                            info.getID(),
                            info));
                }
            }else
            {

                StampParty party = new StampParty(c);


                for(StampExplainer info : party.getStampList()){
                    listData.add(new ListItem(
                            LogoMatcher.getLogoResourceByName(info.getName()),
                            info.getName(),
                            info.getShortDescription(),
                            info.getID(),
                            info));
                }
            }


            //Fá alla staði úr database


        }catch(Exception e){

        }

        return listData;
    }
}
