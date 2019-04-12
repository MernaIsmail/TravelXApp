package com.vis.merna.travelxapp.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vis.merna.travelxapp.model.Travel;
import com.vis.merna.travelxapp.utils.Constants;

public class TravelReminderIntentService extends IntentService {

    public TravelReminderIntentService() {
        super("TravelReminderIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();
        Bundle bundle = intent.getExtras();
        Travel travel = bundle.getParcelable(Constants.REMINDER_BUNDLE);
        ReminderTask.executeTask(travel, action);

    }
}
