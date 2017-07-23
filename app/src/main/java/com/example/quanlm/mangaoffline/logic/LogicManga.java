package com.example.quanlm.mangaoffline.logic;

import android.content.Context;

import com.example.quanlm.mangaoffline.model.Model_Chapter;
import com.example.quanlm.mangaoffline.model.Model_Manga;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by QuanLM on 7/17/2017.
 */

public class LogicManga implements Serializable{
    Context mContext;

    public LogicManga(Context mContext) {
        this.mContext = mContext;
    }

    /*
        * Get list manga from both online and offline source
        * online source is webservice
        * offline source is sqlite
        */
    public List<Model_Manga> getListManga(){
        SQLiteHandler dbHandler = new SQLiteHandler(mContext);
        List<Model_Manga> lstMangas = dbHandler.getMangas();
        return lstMangas;
    }

    public List<Model_Chapter> getListChapter(int mangaId) {
        SQLiteHandler dbHandler = new SQLiteHandler(mContext);
        List<Model_Chapter> lstChapter = dbHandler.getChapters(mangaId);
        return lstChapter;
    }

    public Model_Manga getManga(int mangaid){
        SQLiteHandler dbHandler = new SQLiteHandler(mContext);
        Model_Manga manga = dbHandler.getManga(mangaid);
        return manga;
    }

    public void initDatabase() {
        SQLiteHandler dbHandler = new SQLiteHandler(mContext);
        dbHandler.addManga(new Model_Manga(1, "manga 1", "manga 1 description", true, 10, 1));
        dbHandler.addManga(new Model_Manga(2, "manga 2", "manga 3 description", true, 10, 1));
        dbHandler.addManga(new Model_Manga(3, "manga 3", "manga 4 description", true, 10, 1));
        dbHandler.addManga(new Model_Manga(4, "manga 4", "manga 4 description", true, 10, 1));

        dbHandler.addChapter(new Model_Chapter(1, "chap 1", "/manga1/chapter1"));
        dbHandler.addChapter(new Model_Chapter(1, "chap 2", "/manga1/chapter2"));
        dbHandler.addChapter(new Model_Chapter(1, "chap 3", "/manga1/chapter3"));
        dbHandler.addChapter(new Model_Chapter(1, "chap 4", "/manga1/chapter4"));
        dbHandler.addChapter(new Model_Chapter(1, "chap 5", "/manga1/chapter5"));
        dbHandler.addChapter(new Model_Chapter(1, "chap 6", "/manga1/chapter6"));
        dbHandler.addChapter(new Model_Chapter(1, "chap 7", "/manga1/chapter7"));
        dbHandler.addChapter(new Model_Chapter(1, "chap 8", "/manga1/chapter8"));
        dbHandler.addChapter(new Model_Chapter(1, "chap 9", "/manga1/chapter9"));
        dbHandler.addChapter(new Model_Chapter(1, "chap 10-end", "/manga1/chapter10"));
        dbHandler.addChapter(new Model_Chapter(2, "chap 1", "/manga2/chapter1"));
        dbHandler.addChapter(new Model_Chapter(2, "chap 2", "/manga2/chapter2"));
        dbHandler.addChapter(new Model_Chapter(2, "chap 3", "/manga2/chapter3"));
        dbHandler.addChapter(new Model_Chapter(2, "chap 4", "/manga2/chapter4"));
        dbHandler.addChapter(new Model_Chapter(3, "chap 1", "/manga3/chapter1"));
        dbHandler.addChapter(new Model_Chapter(3, "chap 2", "/manga3/chapter2"));
        dbHandler.addChapter(new Model_Chapter(3, "chap 3", "/manga3/chapter3"));
        dbHandler.addChapter(new Model_Chapter(3, "chap 4", "/manga3/chapter4"));
        dbHandler.addChapter(new Model_Chapter(3, "chap 5", "/manga3/chapter5"));
        dbHandler.addChapter(new Model_Chapter(3, "chap 6", "/manga3/chapter6"));
        dbHandler.addChapter(new Model_Chapter(3, "chap 7", "/manga3/chapter7"));
        dbHandler.addChapter(new Model_Chapter(3, "chap 8", "/manga3/chapter8"));
        dbHandler.addChapter(new Model_Chapter(4, "chap 1", "/manga4/chapter1"));
        dbHandler.addChapter(new Model_Chapter(4, "chap 2", "/manga4/chapter2"));
        dbHandler.addChapter(new Model_Chapter(4, "chap 3", "/manga4/chapter3"));
        dbHandler.addChapter(new Model_Chapter(4, "chap 4", "/manga4/chapter4"));
        dbHandler.addChapter(new Model_Chapter(4, "chap 5", "/manga4/chapter5"));
        dbHandler.addChapter(new Model_Chapter(4, "chap 6", "/manga4/chapter6"));
        dbHandler.addChapter(new Model_Chapter(4, "chap 7", "/manga4/chapter7"));
        dbHandler.addChapter(new Model_Chapter(4, "chap 8", "/manga4/chapter8"));
    }
}
