package com.special.DataBaseHandler.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.special.DataBaseHandler.DataBaseController;
import com.special.DataStorage.Messages.UpdateMessage;

import java.sql.SQLException;

/**
 * @author Arnar Jonsson
 * @version 0.2
 * @since ..
 * Originally created on ..
 */
public class UpdateTask extends AsyncTask<UpdateMessage, Void, UpdateMessage> {
    //
    // EntryTask Variables
    //
    ///
    private Context context;
    private DataBaseController dataBaseController;

    private boolean DATABASE_CONN = false;

    public UpdateTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        try {
            dataBaseController = new DataBaseController(context);
            dataBaseController.open();
            DATABASE_CONN = true;
        } catch (SQLException e) {
            // Do Nothing
        }
    }

    @Override
    protected UpdateMessage doInBackground(UpdateMessage... args) {
        if (!DATABASE_CONN) return new UpdateMessage("Failed to reach in-app database", true);

        return dataBaseController.executeUpdates(args[0]);
    }

    @Override
    protected void onPostExecute(UpdateMessage message) {
        dataBaseController.close();
    }
}