package com.example.quanlm.mangaoffline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlm.mangaoffline.adapter.AdtChapter;
import com.example.quanlm.mangaoffline.config.Constants;
import com.example.quanlm.mangaoffline.logic.LogicManga;
import com.example.quanlm.mangaoffline.model.Model_Chapter;
import com.example.quanlm.mangaoffline.model.Model_Manga;

import java.util.List;

public class ActMangaDetail extends AppCompatActivity {
    RecyclerView rcvChapterList;
    TextView txtMangaName;
    TextView txtMangaDescription;
    ImageView imgMangaThumb;
    TextView txtIsDownloaded;
    LogicManga logicManga = new LogicManga(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_manga_detail);

        initControls();
    }

    private void initControls() {

        txtMangaName = (TextView) findViewById(R.id.txtMangaName);
        txtMangaDescription = (TextView) findViewById(R.id.txtMangaDescription);
        imgMangaThumb = (ImageView) findViewById(R.id.imgMangaThumb);
        txtIsDownloaded = (TextView) findViewById(R.id.txtIsDownloaded);

        Bundle param = getIntent().getBundleExtra("param");
        int selectedMangaId = param.getInt(Constants.SELECTED_MANGA_ID);
        Model_Manga manga = logicManga.getManga(selectedMangaId);

        txtMangaName.setText(manga.getMangaName());
        txtMangaDescription.setText(manga.getMangaDescription());
        if (manga.isDownloaded()) {
            txtIsDownloaded.setBackground(getDrawable(R.drawable.ic_star_black_24dp));
        } else {
            txtIsDownloaded.setBackground(getDrawable(R.drawable.ic_star_border_black_24dp));
        }

        List<Model_Chapter> lstChapter = logicManga.getListChapter(selectedMangaId);

        rcvChapterList = (RecyclerView) findViewById(R.id.lstChapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 4);
        rcvChapterList.setLayoutManager(layoutManager);
        rcvChapterList.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.Adapter adtChapter = new AdtChapter(this, lstChapter);
        rcvChapterList.setAdapter(adtChapter);
    }
}
