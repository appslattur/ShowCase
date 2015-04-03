package com.special;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

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


                //Setja id í bundle!

                Bundle bundle = new Bundle();

                /*bundle.putString("title", item.getTitle());

                bundle.putString("descr", item.getDesc());
                */
                bundle.putInt("img", item.getImageId());
                bundle.putInt("dbId", item.getId());
                
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
        //LogoMatcher matcher = new LogoMatcher();
        for(LocationChain lc : lChain){
            listData.add(new ListItem(
                    LogoMatcher.getLogoResourceByName(lc.getName()),
                    lc.getName(),
                    TestHandler.getShortDescById(lc.getID()),
                    lc.getID()));
        }

       /*
        listData.add(new ListItem(R.drawable.ph_1, "Henry Smith", "Vacation!", null, null));
        listData.add(new ListItem(R.drawable.ph_2, "Martinez", "Still exited from my trip last week!", null, null));
        listData.add(new ListItem(R.drawable.ph_3, "Olivier Smith", "Visiting Canada next week!", null, null));
        listData.add(new ListItem(R.drawable.ph_4, "Aria Thompson", "Can not go shopping tomorrow :(", null, null));
        listData.add(new ListItem(R.drawable.ph_5, "Sophie Hill", "Live every day like it is the last one!", null, null));
        listData.add(new ListItem(R.drawable.ph_6, "Addison Adams", "Not available, working...", null, null));
        listData.add(new ListItem(R.drawable.ph_7, "Harper Clark", "Whats up?", null, null));
        listData.add(new ListItem(R.drawable.ph_8, "Micheal Green", "Guess who has to work? Pfff..", null, null));
        listData.add(new ListItem(R.drawable.ph_9, "Benjamin Lewis", "Playing games all week", null, null));
        listData.add(new ListItem(R.drawable.ph_10, "Luke Wilson", "Anybody got any plans for this weekend?", null, null));
        listData.add(new ListItem(R.drawable.ph_11, "Daniel Moore", "Going to the movies, so do not call me :)", null, null));
        listData.add(new ListItem(R.drawable.ph_12, "Ella Smith", "Going on a trip with the family next week!", null, null));
        */
        return listData;
    }
}
