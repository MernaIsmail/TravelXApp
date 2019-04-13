package com.vis.merna.travelxapp.view.main;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.vis.merna.travelxapp.R;
import com.vis.merna.travelxapp.model.Travel;
import com.vis.merna.travelxapp.utils.Constants;
import com.vis.merna.travelxapp.utils.DateParserUtil;
import com.vis.merna.travelxapp.utils.SharedPreferencesHelper;
import com.vis.merna.travelxapp.view.details.TravelDetailActivity;

public class TravelFirebaseAdapter extends FirebaseRecyclerAdapter<Travel, UpcomingViewHolder> {

    private static final String TYPE_UPCOMING = "upcoming";
    private Context context;
    boolean flag = false;


    public TravelFirebaseAdapter(Context context, Class<Travel> modelClass,
                                 int modelLayout, Class<UpcomingViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.context = context;
    }


    @NonNull
    @Override
    public UpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new UpcomingViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_upcoming, viewGroup, false));
    }


    @Override
    protected void populateViewHolder(UpcomingViewHolder viewHolder, final Travel model, int position) {
        if (!flag && model.getStatus().equals(Constants.STATUS_UPCOMING_VALUE)) {
            //for default widget
            SharedPreferencesHelper.saveTravel(context, model);
            flag = true;
        }
        viewHolder.travelNameTextView.setText(model.getName());
        viewHolder.travelStartPointTextView.setText(model.getStartPoint());
        viewHolder.travelEndPointTextView.setText(model.getEndPoint());

        String[] arrDate = DateParserUtil.parseLongDateToStrings(model.getDate());
        viewHolder.travelDateTextView.setText(arrDate[0] + " " + arrDate[1]);

        if (model.getStatus().equals(TYPE_UPCOMING)) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, TravelDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(Constants.ARG_TRAVEL, model);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        } else {
            handleHistoryUI(viewHolder);
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    void handleHistoryUI(UpcomingViewHolder holder) {
        holder.travelDateTextView.setTextColor(context.getResources().getColor(R.color.text_color));
        holder.travelNameTextView.setTextColor(context.getResources().getColor(R.color.text_color));
        holder.travelStartPointTextView.setTextColor(context.getResources().getColor(R.color.text_color));
        holder.travelEndPointTextView.setTextColor(context.getResources().getColor(R.color.text_color));
        holder.travelPinImageView.setBackground(context.getResources().getDrawable(R.drawable.pin_history));
    }
}
