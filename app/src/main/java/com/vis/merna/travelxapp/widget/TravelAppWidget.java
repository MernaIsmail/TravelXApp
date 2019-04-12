package com.vis.merna.travelxapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.vis.merna.travelxapp.MainActivity;
import com.vis.merna.travelxapp.R;
import com.vis.merna.travelxapp.model.Travel;
import com.vis.merna.travelxapp.utils.DateParserUtil;
import com.vis.merna.travelxapp.utils.SharedPreferencesHelper;
import com.vis.merna.travelxapp.view.details.TravelDetailActivity;

/**
 * Implementation of App Widget functionality.
 */
public class TravelAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Travel travel = SharedPreferencesHelper.loadRecipe(context);
        if (travel != null) {
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.travel_app_widget);

            views.setTextViewText(R.id.travel_widget_name_text_view, travel.getName());

            String[] arrDate = DateParserUtil.parseLongDateToStrings(travel.getDate());
            views.setTextViewText(R.id.travel_widget_date_text_view, arrDate[0] + " " + arrDate[1]);

            views.setOnClickPendingIntent(R.id.travel_widget_name_text_view, pendingIntent);

            Intent intent = new Intent(context, TravelWidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            views.setRemoteAdapter(R.id.travel_widget_list_view, intent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.travel_widget_list_view);
        }
    }

    public static void updateAppWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

