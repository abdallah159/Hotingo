package solutions.hamza.hotelorders.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import solutions.hamza.hotelorders.R;
import solutions.hamza.hotelorders.model.UserResponce;
import solutions.hamza.hotelorders.utils.MyApplication;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    UserResponce userResponce = MyApplication.getPrefManager(this).getUser();
    MyApplication myApplication = new MyApplication();
    private LinearLayout linearProfile;

    private TextView publisherNameTV, publisherEmailTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.cointaner, HotelServicesFragment.newInstance())
                .addToBackStack(null)
                .commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        linearProfile = (LinearLayout) navigationView.getHeaderView(0);
        publisherNameTV = linearProfile.findViewById(R.id.publisher_name);
        publisherEmailTV = linearProfile.findViewById(R.id.publisher_mail);

        publisherNameTV.setText(userResponce.getUser().getName());
        publisherEmailTV.setText(userResponce.getUser().getEmail());

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_service) {
            toolbar.setTitle("Services");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.cointaner, HotelServicesFragment.newInstance())
                    .commit();
        } else if (id == R.id.nav_rooms) {
            toolbar.setTitle("Rooms");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.cointaner, RoomsFragment.newInstance())
                    .commit();

        } else if (id == R.id.nav_myRoom) {
            toolbar.setTitle("My Rooms");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.cointaner, MyRoomsFragment.newInstance())
                    .commit();

        } else if (id == R.id.nav_about) {
            toolbar.setTitle("About");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.cointaner, AboutFragment.newInstance())
                    .commit();

        } else if (id == R.id.nav_logout) {
            myApplication.logout();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            this.finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
