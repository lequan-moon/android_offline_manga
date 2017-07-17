package com.example.quanlm.mangaoffline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.quanlm.mangaoffline.adapter.AdtManga;
import com.example.quanlm.mangaoffline.logic.LogicManga;
import com.example.quanlm.mangaoffline.model.Model_Manga;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdtManga.OnMangaSelect{
    RecyclerView rcvListMangas;
    List<Model_Manga> lstMangas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init controls
        initControls();

        // Init Recyclerview list manga
        lstMangas = LogicManga.getListManga();
        AdtManga adtManga = new AdtManga(this, lstMangas, this);
        RecyclerView.LayoutManager layoutMng = new LinearLayoutManager(this);
        rcvListMangas.setLayoutManager(layoutMng);
        rcvListMangas.setItemAnimator(new DefaultItemAnimator());
        rcvListMangas.setAdapter(adtManga);
    }

    private void initControls() {
        rcvListMangas = (RecyclerView) findViewById(R.id.lstMangas);
    }

    @Override
    public void onSelect(String mangaID) {
        Toast.makeText(this, "Select " + mangaID, Toast.LENGTH_SHORT).show();
    }
}
