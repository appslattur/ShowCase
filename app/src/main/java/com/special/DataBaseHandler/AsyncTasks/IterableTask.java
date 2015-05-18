package com.special.DataBaseHandler.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.special.DataBaseHandler.DataBaseController;
import com.special.DataStorage.Messages.IterableMessage;

import java.sql.SQLException;

/**
 * IterableTask
 * Handles data destined for viewing (front end?)
 */
public class IterableTask extends AsyncTask<IterableMessage, Void, IterableMessage> {

    ///
    // IterableTask Variables
    //
    ///
    private Context context;
    private DataBaseController dataBaseController;

    private boolean DATABASE_CONN = false;

    public IterableTask(Context context) {
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
    protected IterableMessage doInBackground(IterableMessage... args) {
        if(!DATABASE_CONN) return new IterableMessage("Failed to open writable database - IterableTask", true);

        return dataBaseController.extractIterables(args[0]);
    }

    @Override
    protected void onPostExecute(IterableMessage message) {
        dataBaseController.close();
    }
}
