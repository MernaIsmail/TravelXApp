package com.vis.merna.travelxapp.view.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vis.merna.travelxapp.R;
import com.vis.merna.travelxapp.model.Travel;
import com.vis.merna.travelxapp.presnter.HistoryPresenter;
import com.vis.merna.travelxapp.presnter.IHistoryPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {


    @BindView(R.id.history_recycler_view)
    RecyclerView travelsRecyclerView;

    private IHistoryPresenter presenter;
    private TravelFirebaseAdapter travelFirebaseAdapter;

    public HistoryFragment() {
        // Required empty public constructor
        presenter = new HistoryPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, rootView);
        setupUpcomingRecycleView();
        return rootView;
    }

    private void setupUpcomingRecycleView() {
        travelFirebaseAdapter = new TravelFirebaseAdapter(getContext(),
                Travel.class, R.layout.item_upcoming, UpcomingViewHolder.class, presenter.getPastTravels());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        travelsRecyclerView.setLayoutManager(linearLayoutManager);

        //this line of code to add line divider between items  in recycle view ..
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                linearLayoutManager.getOrientation());
        travelsRecyclerView.addItemDecoration(dividerItemDecoration);

        travelsRecyclerView.setAdapter(travelFirebaseAdapter);
    }


}
