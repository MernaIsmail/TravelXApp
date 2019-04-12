package com.vis.merna.travelxapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViewsService;

import com.vis.merna.travelxapp.model.Travel;
import com.vis.merna.travelxapp.utils.SharedPreferencesHelper;

public class TravelWidgetService extends RemoteViewsService {
    public static void updateWidget(Context context, Travel travel) {
        SharedPreferencesHelper.saveTravel(context, travel);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, TravelAppWidget.class));
        TravelAppWidget.updateAppWidgets(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        return new ListRemoteViewsFactory(getApplicationContext());
    }
}
