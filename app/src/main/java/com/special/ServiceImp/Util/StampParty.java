package com.special.ServiceImp.Util;

import android.content.Context;

import com.special.DataBaseHandler.AsyncTasks.ValueTask;
import com.special.DataStorage.Instances.ValueStamp;
import com.special.DataStorage.Messages.ValueMessage;
import com.special.DataStorage.Objects.DataStamp;

import java.util.ArrayList;

/**
 * Created by arnarjons on 10.5.2015.
 */
public class StampParty {

    private Context context;
    private ArrayList<StampExplainer> stampList;

    public StampParty(Context context) {

        this.context = context;

        fetchViewIterables();
    }

    public ArrayList<StampExplainer> getStampList() {
        return stampList;
    }

    private void addStamp(DataStamp stamp) {
        stampList.add(new StampExplainer(stamp));
    }

    private void fetchViewIterables() {

        try {
            ValueMessage message = new ValueTask(context).
                    execute(new ValueMessage()).get();

            for(ValueStamp stamp : message.getStamps()) {
                addStamp(stamp);
            }
        }
        catch (Exception e) {

        }

    }
}
