package com.vis.merna.travelxapp.view.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vis.merna.travelxapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class UpcomingViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.name_text_view)
    TextView travelNameTextView;
    @BindView(R.id.date_text_view)
    TextView travelDateTextView;
    @BindView(R.id.start_point_text_view)
    TextView travelStartPointTextView;
    @BindView(R.id.end_point_text_view)
    TextView travelEndPointTextView;
    @BindView(R.id.pin_travel_Image_view)
    ImageView travelPinImageView;

    public UpcomingViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }
}
