package com.example.quanlm.mangaoffline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.quanlm.mangaoffline.adapter.AdtChapter;

public class ActChapter extends AppCompatActivity implements AdtChapter.OnChapterSelect{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_chapter);
    }

    @Override
    public void onSelect(int chapterID) {
        Toast.makeText(this, "SELECT CHAPTER " + chapterID, Toast.LENGTH_SHORT).show();
    }
}
