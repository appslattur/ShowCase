package com.special;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private View parentView;
    private ListView listView;
    private ListAdapter mAdapter;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_list, container, false);
        listView   = (ListView) parentView.findViewById(R.id.listView);
        initView();
        return parentView;
    }

    @SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	private void initView(){
    	//Getting width of display, could be usefull for scaling bitmaps
    	Display display = getActivity().getWindowManager().getDefaultDisplay();
    	int width;
    	if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2){
    		Point size = new Point();
    		display.getSize(size);
    		width = size.x;
    	} else{
        	width = display.getWidth();
    	}
    	
    	mAdapter = new ListAdapter(getActivity(), getListData(), width);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                
                ListItem item = (ListItem) listView.getAdapter().getItem(i);
                
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("img", item.getImageId());
                intent.putExtra("descr", item.getDesc());
                startActivity(intent);
            }
        });
    }

    private ArrayList<ListItem> getListData(){
        ArrayList<ListItem> listData = new ArrayList<ListItem>();

        return listData;
    }
}
