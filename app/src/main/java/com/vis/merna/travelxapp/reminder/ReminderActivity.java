package com.vis.merna.travelxapp.reminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vis.merna.travelxapp.R;
import com.vis.merna.travelxapp.model.Travel;
import com.vis.merna.travelxapp.service.ReminderTask;
import com.vis.merna.travelxapp.service.TravelReminderIntentService;
import com.vis.merna.travelxapp.utils.Constants;
import com.vis.merna.travelxapp.view.details.MapActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReminderActivity extends Activity {

    @BindView(R.id.dialog_title_text_view)
    TextView dialogTitle;
    @BindView(R.id.start_button)
    Button startButton;
    @BindView(R.id.cancel_button)
    Button cancelButton;
    @BindView(R.id.done_button)
    Button doneButton;

    private Travel travel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        travel = bundle.getParcelable(Constants.REMINDER_BUNDLE);
        if (travel != null)
            dialogTitle.setText(TextUtils.isEmpty(travel.getName()) ? "" : travel.getName());

        setListeners();
    }

    private void setListeners() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                intent.putExtra(Constants.ARG_TRAVEL, travel);
                startActivity(intent);
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateTravel = new Intent(getApplicationContext(), TravelReminderIntentService.class);
                updateTravel.setAction(ReminderTask.ACTION_DONE_TRAVEL);
                Bundle bundle = new Bundle();
                bundle.putParcelable(ReminderTask.ARG_DONE_TRAVEL, travel);
                updateTravel.putExtras(bundle);
                startService(updateTravel);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
