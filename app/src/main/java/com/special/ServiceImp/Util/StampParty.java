package com.special.ServiceImp.Util;

import android.content.Context;
import android.widget.Toast;

import com.special.DataBaseHandler.AsyncTasks.ValueTask;
import com.special.DataStorage.Instances.ValueStamp;
import com.special.DataStorage.Messages.ValueMessage;
import com.special.DataStorage.Objects.DataStamp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by arnarjons on 10.5.2015.
 */
public class StampParty implements Serializable {

    private Context context;
    private ArrayList<StampExplainer> stampList = new ArrayList<StampExplainer>();

    public StampParty(Context context) {

        this.context = context;

        fetchViewIterables();
    }

    public StampParty(DataStamp stamp) {

        addStamp(new StampExplainer(stamp));

    }

    public StampParty(DataStamp[] stamps) {

        for(DataStamp stamp : stamps) {
            addStamp(new StampExplainer(stamp));
        }

    }

    public ArrayList<StampExplainer> getStampList() {
        return stampList;
    }

    private void addStamp(StampExplainer stamp) {
        stampList.add(stamp);
    }

    private void fetchViewIterables() {

        try {
            ValueMessage message = new ValueTask(context).
                    execute(new ValueMessage()).get();
            if(message.getStamps() == null){
                //Toast.makeText(context, "How many :" + message.getStamps().length, Toast.LENGTH_LONG).show();
            }else{
                //Toast.makeText(context, "How many :" + message.getStamps().length, Toast.LENGTH_LONG).show();
            }


            for(ValueStamp stamp : message.getStamps()) {
                addStamp(new StampExplainer(stamp));
            }
        }
        catch (Exception e) {
            Toast.makeText(context, "It broke", Toast.LENGTH_LONG).show();
        }

    }
}
