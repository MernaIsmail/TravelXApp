package com.vis.merna.travelxapp.presnter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.vis.merna.travelxapp.utils.Constants;

public class HistoryPresenter implements IHistoryPresenter {
    private DatabaseReference databaseReference;
    private Query pastTravelQuery;

    public HistoryPresenter() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(Constants.REFERENCE_PATH);
        pastTravelQuery = databaseReference.orderByChild(Constants.STATUS_KEY).
                startAt("#").endAt("#" + "\uf8ff");
    }

    @Override
    public Query getPastTravels() {
        if (pastTravelQuery != null) return pastTravelQuery;
        else return databaseReference.orderByChild(Constants.STATUS_KEY).
                startAt("#").endAt("#" + "\uf8ff");
    }
}
