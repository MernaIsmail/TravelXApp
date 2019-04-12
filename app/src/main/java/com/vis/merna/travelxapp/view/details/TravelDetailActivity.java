package com.vis.merna.travelxapp.view.details;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vis.merna.travelxapp.R;
import com.vis.merna.travelxapp.model.Travel;
import com.vis.merna.travelxapp.utils.Constants;
import com.vis.merna.travelxapp.widget.TravelWidgetService;

public class TravelDetailActivity extends AppCompatActivity {

    private Travel travel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(Constants.ARG_TRAVEL)) {
            travel = bundle.getParcelable(Constants.ARG_TRAVEL);
        }
        setContentView(R.layout.activity_travel_detail);

        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            if (savedInstanceState == null & travel != null) {
                Bundle arguments = new Bundle();
                arguments.putParcelable(Constants.ARG_TRAVEL, travel);
                MapFragment fragment = new MapFragment();
                fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.travel_map_container, fragment)
                        .commit();
            }
        }
    }

    public Travel getTravelData() {
        return travel;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.travel_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_widget) {
            TravelWidgetService.updateWidget(this, travel);
            Toast.makeText(getApplicationContext(),
                    String.format("Travel %s added to widget", travel.getName()), Toast.LENGTH_LONG).
                    show();
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            onDeleteAction();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void onDeleteAction() {
        // TODO: 4/12/2019 move hardcoded text to strings later 
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete");
        alert.setMessage("Are you sure you want to delete this travel ?");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference(Constants.REFERENCE_PATH).child(travel.getId());
                databaseReference.removeValue();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
        alert.show();
    }
}
