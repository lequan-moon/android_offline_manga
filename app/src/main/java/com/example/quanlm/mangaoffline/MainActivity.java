package com.example.quanlm.mangaoffline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.quanlm.mangaoffline.adapter.AdtManga;
import com.example.quanlm.mangaoffline.config.Constants;
import com.example.quanlm.mangaoffline.logic.LogicManga;
import com.example.quanlm.mangaoffline.model.Model_Manga;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdtManga.OnMangaSelect{
    public static final String SELECTED_MANGA_ID = "com.example.quanlm.mangaoffline.selected_manga_id";
    private static final String OFFLINE_MANAGA_SHARED = "offlinemanga_shared";
    private static final String IS_FIRST = "is_first";
    LogicManga mangaLogic = new LogicManga(this);
    RecyclerView rcvListMangas;
    List<Model_Manga> lstMangas = new ArrayList<>();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        rcvListMangas = (RecyclerView) findViewById(R.id.lstMangas);

        // Init Recyclerview list manga
        lstMangas = mangaLogic.getListManga();
        AdtManga adtManga = new AdtManga(this, lstMangas, this);
        RecyclerView.LayoutManager layoutMng = new LinearLayoutManager(this);
        rcvListMangas.setLayoutManager(layoutMng);
        rcvListMangas.setItemAnimator(new DefaultItemAnimator());
        rcvListMangas.setAdapter(adtManga);
    }

    @Override
    public void onSelect(int mangaID) {
        Toast.makeText(this, "Select " + mangaID, Toast.LENGTH_SHORT).show();
        Intent itMangaDetail = new Intent(this, ActMangaDetail.class);
        Bundle args = new Bundle();
        args.putInt(SELECTED_MANGA_ID, mangaID);
        itMangaDetail.putExtra("param", args);
        startActivity(itMangaDetail);
    }
}
