package com.special.ServiceImp.Util;

import android.content.Context;

import com.special.DataBaseHandler.AsyncTasks.IterableTask;
import com.special.DataBaseHandler.AsyncTasks.ValueTask;
import com.special.DataStorage.Messages.IterableMessage;
import com.special.DataStorage.Messages.ValueMessage;
import com.special.DataStorage.Objects.DataStamp;

import java.io.Serializable;

/**
 *
 */
public class StampExplainer implements Stamp, Serializable {

    ///
    // StampExplainer Variables
    //
    ///

    private DataStamp stamp;

    ///
    // StampExplainer Constructors
    //
    ///

    public StampExplainer(DataStamp stamp) {
        this.stamp = stamp;
    }

    ///
    // Stamp Interface Implementation
    //
    ///

    @Override
    public String getLongDescription() {
        
        return stamp.getLongDescription();
    }

    @Override
    public String getShortDescription() {
        return stamp.getShortDescription();
    }

    @Override
    public int getID() {
        return stamp.getId();

    }

    @Override
    public String getName() {
        return stamp.getName();
    }

}
