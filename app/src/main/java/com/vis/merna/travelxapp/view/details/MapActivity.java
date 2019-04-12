package com.vis.merna.travelxapp.view.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vis.merna.travelxapp.R;
import com.vis.merna.travelxapp.model.Travel;
import com.vis.merna.travelxapp.utils.Constants;

public class MapActivity extends AppCompatActivity {

    private Travel travel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(Constants.ARG_TRAVEL)) {
            travel = bundle.getParcelable(Constants.ARG_TRAVEL);
        }
        setContentView(R.layout.activity_map);
    }

    public Travel getTravelData() {
        return travel;
    }
}
