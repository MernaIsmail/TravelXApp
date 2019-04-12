package com.vis.merna.travelxapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.vis.merna.travelxapp.model.Travel;

public class SharedPreferencesHelper {
    public static final String PREFS_NAME = "prefs";

    public static void saveRecipe(Context context, Travel travel) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(travel);
        prefs.putString(Constants.WIDGET_TRAVEL_KEY, json);
        prefs.apply();
    }

    public static Travel loadRecipe(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(Constants.WIDGET_TRAVEL_KEY, "");
        return gson.fromJson(json, Travel.class);
    }
}
