package com.vis.merna.travelxapp.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.vis.merna.travelxapp.model.Travel;
import com.vis.merna.travelxapp.utils.Constants;
import com.vis.merna.travelxapp.utils.DateParserUtil;
import com.vis.merna.travelxapp.utils.SHA;

import static android.content.Context.ALARM_SERVICE;

public class Alarm {
    public static void setAlarm(Context context, Travel travel, Long dateTime) {
        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);

        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.REMINDER_BUNDLE, travel);
        intent.putExtras(bundle);

        PendingIntent pendingIntent = PendingIntent
                .getBroadcast(context, SHA.getIntegerID(travel.getId()), intent, 0);


        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, dateTime, pendingIntent);

        Toast.makeText(context, "Alarm set on " + DateParserUtil.
                        parseLongDateToStrings(travel.getDate())[0] + " ",
                Toast.LENGTH_LONG).show();

    }

    public static void cancelAlarm(Context context, int alarmID) {
        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, alarmID, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.cancel(sender);
        Log.i("MY_TAG", "Cancel alarm >> " + alarmID);
    }
}
