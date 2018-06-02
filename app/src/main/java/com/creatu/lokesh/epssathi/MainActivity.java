package com.creatu.lokesh.epssathi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.os.Handler;

import com.creatu.lokesh.epssathi.side_menu.AboutUs;
import com.creatu.lokesh.epssathi.side_menu.Disclaimer;
import com.creatu.lokesh.epssathi.side_menu.EPSProcess;
import com.creatu.lokesh.epssathi.side_menu.Feedback;
import com.creatu.lokesh.epssathi.side_menu.Home;
import com.creatu.lokesh.epssathi.side_menu.InvestorsFromKorea;
import com.creatu.lokesh.epssathi.side_menu.KoreaReturnees;
import com.creatu.lokesh.epssathi.side_menu.LearnKorean;
import com.creatu.lokesh.epssathi.side_menu.News;
import com.creatu.lokesh.epssathi.side_menu.PracticeKorean;
import com.creatu.lokesh.epssathi.side_menu.Remittance;
import com.creatu.lokesh.epssathi.side_menu.RestaurantsFromKorea;
import com.creatu.lokesh.epssathi.side_menu.SheltersInKorea;
import com.creatu.lokesh.epssathi.side_menu.ShoppingInKorea;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    android.support.v4.app.FragmentManager fragmentManager;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.main_content,new Home()).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



//    @Override
//    public void onBackPressed() {
//        finish();
//        super.onBackPressed();
//    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        if (id == R.id.nav_home) {
            fragmentManager.beginTransaction().replace(R.id.main_content,new Home()).commit();
        } else if (id == R.id.nav_learn_korean) {
            fragmentManager.beginTransaction().replace(R.id.main_content,new LearnKorean()).addToBackStack(null).commit();
        }else if (id == R.id.nav_practice_korean){
            fragmentManager.beginTransaction().replace(R.id.main_content,new PracticeKorean()).addToBackStack(null).commit();
        }else if (id == R.id.nav_news) {
            fragmentManager.beginTransaction().replace(R.id.main_content,new News()).addToBackStack(null).commit();
        } else if (id == R.id.nav_eps_process) {
            fragmentManager.beginTransaction().replace(R.id.main_content,new EPSProcess()).addToBackStack(null).commit();
        } else if (id == R.id.nav_shelters_in_korea) {
            fragmentManager.beginTransaction().replace(R.id.main_content,new SheltersInKorea()).addToBackStack(null).commit();
        } else if (id == R.id.nav_investors_from_korea) {
            fragmentManager.beginTransaction().replace(R.id.main_content,new InvestorsFromKorea()).addToBackStack(null).commit();
        }else if (id == R.id.nav_restaurants_from_korea) {
            fragmentManager.beginTransaction().replace(R.id.main_content,new RestaurantsFromKorea()).addToBackStack(null).commit();
        }else if (id == R.id.nav_remittance) {
            fragmentManager.beginTransaction().replace(R.id.main_content,new Remittance()).addToBackStack(null).commit();
        }else if (id == R.id.nav_shopping_in_korea) {
            fragmentManager.beginTransaction().replace(R.id.main_content,new ShoppingInKorea()).addToBackStack(null).commit();
        }else if (id == R.id.nav_korea_returnees) {
            fragmentManager.beginTransaction().replace(R.id.main_content,new KoreaReturnees()).addToBackStack(null).commit();
        }else if (id == R.id.nav_disclaimer) {
            fragmentManager.beginTransaction().replace(R.id.main_content,new Disclaimer()).addToBackStack(null).commit();
        }else if (id == R.id.nav_about_us) {
            fragmentManager.beginTransaction().replace(R.id.main_content,new AboutUs()).addToBackStack(null).commit();
        }else if (id == R.id.nav_feedback) {
            fragmentManager.beginTransaction().replace(R.id.main_content,new Feedback()).addToBackStack(null).commit();
        }else if (id == R.id.nav_share) {

        }else if (id == R.id.nav_rate) {
            Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getApplicationContext().getPackageName()));
            startActivity(rateIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,"Touch again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }, 2000);
        } else {
            super.onBackPressed();
            return;
        }
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
