package com.special.DataBaseHandler.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.special.DataBaseHandler.DataBaseController;
import com.special.DataStorage.Messages.ValueMessage;

import java.sql.SQLException;

/**
 * ValueTask
 * Handles simple extractions
 */
public class ValueTask  extends AsyncTask<ValueMessage, Void, ValueMessage> {

    ///
    // ValueTask Variables
    //
    ///
    private Context context;
    private DataBaseController dataBaseController;

    private boolean DATABASE_CONN = false;

    public ValueTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        try {
            dataBaseController = new DataBaseController(context);
            dataBaseController.open();
            DATABASE_CONN = true;
        }
        catch (SQLException e) {
            // Do Nothing
        }
    }

    @Override
    protected ValueMessage doInBackground(ValueMessage... args) {
        if(!DATABASE_CONN) return new ValueMessage("Failed to open writable database - ValueTask", true);

        return dataBaseController.extractEntries(args[0]);
    }

    @Override
    protected void onPostExecute(ValueMessage message) {
        dataBaseController.close();
    }
}
