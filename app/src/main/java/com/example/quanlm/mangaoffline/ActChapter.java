package com.example.quanlm.mangaoffline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.quanlm.mangaoffline.adapter.AdtChapter;
import com.example.quanlm.mangaoffline.config.Constants;

public class ActChapter extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_chapter);
        Bundle args = getIntent().getBundleExtra("params");
        int mangaId = args.getInt(Constants.MANGA_ID);
        int chapterId = args.getInt(Constants.CHAPTER_ID);
        Log.d("ActChapter", "onCreate: " + args.toString());
    }
}
