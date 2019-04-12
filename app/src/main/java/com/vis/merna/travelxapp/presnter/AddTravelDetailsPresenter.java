package com.vis.merna.travelxapp.presnter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vis.merna.travelxapp.model.Travel;
import com.vis.merna.travelxapp.utils.Constants;

public class AddTravelDetailsPresenter implements IAddTravelDetailsPresenter {

    private DatabaseReference databaseReference;


    public AddTravelDetailsPresenter() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(Constants.REFERENCE_PATH);
    }

    @Override
    public void saveTravelAction(Travel travel) {
        String key = databaseReference.push().getKey();
        travel.setId(key);
        databaseReference.child(key).setValue(travel);
    }

}
