package com.example.quanlm.mangaoffline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.example.quanlm.mangaoffline.adapter.AdtManga;
import com.example.quanlm.mangaoffline.config.Constants;
import com.example.quanlm.mangaoffline.logic.LogicManga;
import com.example.quanlm.mangaoffline.model.Model_Manga;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener, MangaListFragment.OnFragmentInteractionListener {
    private static final String OFFLINE_MANAGA_SHARED = "offlinemanga_shared";
    private static final String IS_FIRST = "is_first";

    LogicManga mangaLogic = new LogicManga(this);
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sharedPreferences = getSharedPreferences(OFFLINE_MANAGA_SHARED, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        boolean isFirst = sharedPreferences.getBoolean(IS_FIRST, true);
        if(isFirst) {
            initEnvironment();
        }
        // Init controls
        initControls();
    }

    private void initEnvironment() {
        mangaLogic.initDatabase();
        editor.putBoolean(IS_FIRST, false);
        editor.commit();

        File directory = new File(Constants.SD_CARD_PATH);
        directory.mkdirs();
    }

    private void initControls() {
        MangaListFragment mangaListFragment = MangaListFragment.newInstance(Constants.ALL);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mangaListFragment).commit();
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

        if (id == R.id.nav_all) {
            MangaListFragment mangaListFragment = MangaListFragment.newInstance(Constants.ALL);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mangaListFragment).commit();

        } else if (id == R.id.nav_favorite) {
            MangaListFragment mangaListFragment = MangaListFragment.newInstance(Constants.FAVORITE);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mangaListFragment).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
