package com.vis.merna.travelxapp.service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vis.merna.travelxapp.model.Travel;
import com.vis.merna.travelxapp.utils.Constants;

public class ReminderTask {
    public static final String ACTION_DONE_TRAVEL = "action_done_travel";
    public static final String ARG_DONE_TRAVEL = "ARG_done_travel";

    public static void executeTask(Travel travel, String action) {
        if (ACTION_DONE_TRAVEL.equals(action)) {
            updateTravelInDB(travel);
        }
    }

    private static void updateTravelInDB(Travel travel) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference(Constants.REFERENCE_PATH).child(travel.getId()).child(Constants.STATUS_KEY);
        databaseReference.setValue("#Done");
    }
}
