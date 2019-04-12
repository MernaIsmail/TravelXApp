package com.vis.merna.travelxapp.view.details;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vis.merna.travelxapp.R;
import com.vis.merna.travelxapp.model.Travel;
import com.vis.merna.travelxapp.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {

    private Travel travel;

    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance(Travel travel) {
        MapFragment fragment = new MapFragment();
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
            travel = ((MapActivity) getActivity()).getTravelData();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers

                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(Double.parseDouble(travel.getStartLat()), Double.parseDouble(travel.getStartLong())))
                        .zoom(10)
                        .bearing(0)
                        .tilt(45)
                        .build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);


                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(Double.parseDouble(travel.getStartLat()), Double.parseDouble(travel.getStartLong())))
                        .title(travel.getStartPoint()));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(Double.parseDouble(travel.getEndLat()), Double.parseDouble(travel.getEndLong())))
                        .title(travel.getEndLong()));
            }
        });


        return rootView;
    }

}
