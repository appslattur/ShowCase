package com.special.DataBaseHandler.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.special.DataBaseHandler.DataBaseController;
import com.special.DataStorage.Messages.EntryMessage;

import java.sql.SQLException;

/**
 * @author Arnar Jonsson
 * @version 0.5
 *
 * EntryTask
 * Handles insertion of DataStamps into the in-app Database
 */
public class EntryTask extends AsyncTask<EntryMessage, Void, EntryMessage> {

    ///
    // EntryTask Variables
    //
    ///
    private Context context;
    private DataBaseController dataBaseController;

    private boolean DATABASE_CONN = false;

    public EntryTask(Context context) {
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
    protected EntryMessage doInBackground(EntryMessage... args) {
        if(!DATABASE_CONN) return new EntryMessage("Failed to open writable database - EntryTask", true);

        return dataBaseController.insertEntries(args[0]);
    }

    @Override
    protected void onPostExecute(EntryMessage message) {
        dataBaseController.close();
    }
}
