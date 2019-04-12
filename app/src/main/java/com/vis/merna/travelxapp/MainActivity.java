package com.vis.merna.travelxapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vis.merna.travelxapp.view.AddTravelDetailsActivity;
import com.vis.merna.travelxapp.view.main.HistoryFragment;
import com.vis.merna.travelxapp.view.main.TravelViewPagerAdapter;
import com.vis.merna.travelxapp.view.main.UpcomingTravelsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_travel_tab_layout)
    TabLayout travelTabLayout;
    @BindView(R.id.main_travel_viewpager)
    ViewPager travelViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        setupViewPager(travelViewPager);
        travelTabLayout.setupWithViewPager(travelViewPager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddTravelDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        TravelViewPagerAdapter travelViewPagerAdapter = new TravelViewPagerAdapter(getSupportFragmentManager());
        travelViewPagerAdapter.addFragment(new UpcomingTravelsFragment(), getString(R.string.upcoming_page_title));
        travelViewPagerAdapter.addFragment(new HistoryFragment(), getString(R.string.history_page_title));
        viewPager.setAdapter(travelViewPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
