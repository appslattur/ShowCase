package com.special.ServiceImp.Util;

import android.content.Context;
import android.widget.Toast;

import com.special.DataBaseHandler.AsyncTasks.IterableTask;
import com.special.DataBaseHandler.AsyncTasks.ValueTask;
import com.special.DataStorage.Instances.IterableStamp;
import com.special.DataStorage.Instances.ValueStamp;
import com.special.DataStorage.Messages.IterableMessage;
import com.special.DataStorage.Messages.ValueMessage;
import com.special.DataStorage.Objects.DataStamp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Mani Elmarsson
 * @version 0.1
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
            IterableMessage message = new IterableTask(context)
                    .execute(new IterableMessage()).get();

            if(message.isError()) {
                //Toast.makeText(context, "Something went horribly wrong", Toast.LENGTH_LONG).show();
            }

            if(message.getStamps() == null) {
                //Toast.makeText(context, "Stamps are null", Toast.LENGTH_LONG).show();
            }

            for(IterableStamp stamp : message.getStamps()) {
                addStamp(new StampExplainer(stamp));
            }

            //Toast.makeText(context, "Everything went right", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(context, "It broke", Toast.LENGTH_LONG).show();
        }

    }
}
