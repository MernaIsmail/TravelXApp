package com.vis.merna.travelxapp.view;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.vis.merna.travelxapp.R;
import com.vis.merna.travelxapp.model.Travel;
import com.vis.merna.travelxapp.presnter.AddTravelDetailsPresenter;
import com.vis.merna.travelxapp.presnter.IAddTravelDetailsPresenter;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTravelDetailsFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.travel_details_travel_name)
    EditText travelNameEditText;
    PlaceAutocompleteFragment travelStartPointFragment, travelEndPointFragment;
    @BindView(R.id.travel_details_travel_time)
    EditText travelTimeEditText;
    @BindView(R.id.travel_details_travel_date)
    EditText travelDateEditText;
    //    @BindView(R.id.notes_recycler_view)
//    RecyclerView notesRecycleView;
    @BindView(R.id.travel_details_fixed_button)
    Button saveUpdateButton;
    private EditText travelStartPointEditText, travelEndPointEditText;

    private IAddTravelDetailsPresenter addTravelDetailsPresenter;
    private String startPoint, startLong, startLat;
    private String endPoint, endLong, endLat;
    private Calendar calendar;


    public AddTravelDetailsFragment() {
        // Required empty public constructor
        addTravelDetailsPresenter = new AddTravelDetailsPresenter();
        calendar = Calendar.getInstance();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_travel_details, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        travelStartPointFragment = (PlaceAutocompleteFragment) getActivity().getFragmentManager().
                findFragmentById(R.id.travel_details_travel_start_point);
        travelEndPointFragment = (PlaceAutocompleteFragment) getActivity().getFragmentManager().
                findFragmentById(R.id.travel_details_travel_end_point);
        travelStartPointEditText = travelStartPointFragment.getView()
                .findViewById(R.id.place_autocomplete_search_input);
        travelEndPointEditText = travelEndPointFragment.getView()
                .findViewById(R.id.place_autocomplete_search_input);

        handlePlacesFields();
        setListeners();

        return rootView;
    }

    private void setListeners() {
        setupRXListener(travelNameEditText);
        setupRXListener(travelStartPointEditText);
        setupRXListener(travelEndPointEditText);
        setupRXListener(travelTimeEditText);
        setupRXListener(travelDateEditText);

        travelDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDateAction(v);
            }
        });

        travelTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTimeAction(v);
            }
        });
        saveUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Travel travel = new Travel(travelNameEditText.getText().toString(),
                        startPoint,
                        endPoint,
                        startLong,
                        endLong,
                        startLat,
                        endLat,
                        "upcoming", calendar.getTimeInMillis());
                addTravelDetailsPresenter.saveTravelAction(travel);
            }
        });
    }

    private void handlePlacesFields() {
        travelStartPointFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                startPoint = place.getAddress().toString();
                startLong = String.valueOf(place.getLatLng().longitude);
                startLat = String.valueOf(place.getLatLng().latitude);
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("place_tag", "An error occurred: " + status);
            }
        });

        travelEndPointFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                endPoint = place.getAddress().toString();
                endLong = String.valueOf(place.getLatLng().longitude);
                endLat = String.valueOf(place.getLatLng().latitude);
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("place_tag", "An error occurred: " + status);
            }
        });
    }

    private void setupRXListener(EditText editText) {
        RxTextView.textChanges(editText).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence text) {
                if (isValidFields()) {
                    saveUpdateButton.setEnabled(true);
                } else {
                    saveUpdateButton.setEnabled(false);
                }
            }
        });
    }

    private boolean isValidFields() {
        boolean name = travelNameEditText.getText().toString().trim().isEmpty();
        boolean date = travelDateEditText.getText().toString().trim().isEmpty();
        boolean time = travelTimeEditText.getText().toString().trim().isEmpty();
        boolean startPoint = travelStartPointEditText.getText().toString().trim().isEmpty();
        boolean endPoint = travelEndPointEditText.getText().toString().trim().isEmpty();

        return !(name || date || time || startPoint || endPoint);
    }

    public void onDateAction(View v) {

        final Calendar currentDate = Calendar.getInstance();
        int mYear = currentDate.get(Calendar.YEAR);
        int mMonth = currentDate.get(Calendar.MONTH);
        int mDay = currentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        calendar.set(Calendar.YEAR, selectedyear);
                        calendar.set(Calendar.MONTH, selectedmonth);
                        calendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        travelDateEditText.setText(selectedday + "-" + selectedmonth + "-" + selectedyear);
                    }
                }, mYear, mMonth, mDay);
        mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        mDatePicker.setTitle(getString(R.string.travel_details_date_picker));
        mDatePicker.show();
    }

    public void onTimeAction(View v) {

        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        calendar.set(Calendar.SECOND, 00);
                        travelTimeEditText.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);

        timePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
                    travelTimeEditText.setText("");
                    travelTimeEditText.setHint(getString(R.string.travel_details_time));
                    Toast.makeText(getContext(), getString(R.string.travel_details_time_picker), Toast.LENGTH_LONG).show();
                }
            }
        });

        timePickerDialog.show();
    }
}
