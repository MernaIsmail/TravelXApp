package com.vis.merna.travelxapp.view.details;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.vis.merna.travelxapp.R;
import com.vis.merna.travelxapp.model.Travel;
import com.vis.merna.travelxapp.utils.Constants;
import com.vis.merna.travelxapp.utils.DateParserUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TravelDetailsFragment extends Fragment {

    @BindView(R.id.travel_details_date_text_view)
    TextView dateTextView;
    @BindView(R.id.travel_details_time_text_view)
    TextView timeTextView;
    @BindView(R.id.travel_details_from_text_view)
    TextView fromTextView;
    @BindView(R.id.travel_details_to_text_view)
    TextView toTextView;
    @BindView(R.id.travel_details_notes_recycler_view)
    RecyclerView notesRecycleView;
    @BindView(R.id.travel_details_start_button)
    Button startTravelButton;

    private Travel travel;


    public TravelDetailsFragment() {
        // Required empty public constructor
    }

    public static TravelDetailsFragment newInstance(Travel travel) {
        TravelDetailsFragment fragment = new TravelDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.ARG_TRAVEL, travel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            travel = getArguments().getParcelable(Constants.ARG_TRAVEL);
        } else {
            travel = ((TravelDetailActivity) getActivity()).getTravelData();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_travel_details, container, false);
        ButterKnife.bind(this, rootView);
        setContent();
        setOnStartButtonAction();
        return rootView;
    }

    private void setOnStartButtonAction() {
        startTravelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// TODO: 4/12/2019  handle two pane
                Intent intent = new Intent(getContext(), MapActivity.class);
                intent.putExtra(Constants.ARG_TRAVEL, travel);
                startActivity(intent);
            }
        });
    }

    private void setContent() {
        if (travel != null) {
            getActivity().setTitle(travel.getName());
            fromTextView.setText(travel.getStartPoint());
            toTextView.setText(travel.getEndPoint());
            String date[] = DateParserUtil.parseLongDateToStrings(travel.getDate());
            dateTextView.setText(date[0]);
            timeTextView.setText(date[1]);
        }
    }
}
