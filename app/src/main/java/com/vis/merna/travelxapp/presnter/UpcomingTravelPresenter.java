package com.vis.merna.travelxapp.presnter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.vis.merna.travelxapp.utils.Constants;

public class UpcomingTravelPresenter implements IUpcomingTravelPresenter {

    private DatabaseReference databaseReference;
    private Query upcomingTravelQuery;


    public UpcomingTravelPresenter() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(Constants.REFERENCE_PATH);
        upcomingTravelQuery = databaseReference.orderByChild(Constants.STATUS_KEY).
                equalTo(Constants.STATUS_UPCOMING_VALUE);
    }

    @Override
    public Query getUpcomingTravels() {
        if (upcomingTravelQuery != null) return upcomingTravelQuery;
        else return databaseReference.orderByChild(Constants.STATUS_KEY).
                equalTo(Constants.STATUS_UPCOMING_VALUE);
    }
}
