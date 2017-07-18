package com.example.quanlm.mangaoffline.logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quanlm.mangaoffline.config.Constants;
import com.example.quanlm.mangaoffline.model.Model_Chapter;
import com.example.quanlm.mangaoffline.model.Model_Manga;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QuanLM on 7/17/2017.
 */

public class SQLiteHandler extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "offlinemanga.db";
    private SQLiteDatabase database;

    public SQLiteHandler(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Table Manga
        String createManga = "CREATE TABLE " + Constants.TB_MANGA + "("
                + Constants.MANGA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.MANGA_NAME + " TEXT, "
                + Constants.MANGA_DESCRIPTION + " TEXT, "
                + Constants.MANGA_IS_DOWNLOADED + " INTEGER, "
                + Constants.MANGA_TOTAL_CHAPS + " INTEGER, "
                + Constants.MANGA_CURRENT_CHAP + " INTEGER);";
        db.execSQL(createManga);

        // Table Chapters
        String createChapters = "CREATE TABLE " + Constants.TB_CHAPTER + "("
                + Constants.CHAPTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.CHAPTER_MANGA_ID + " INTEGER, "
                + Constants.CHAPTER_NAME + " TEXT, "
                + Constants.CHAPTER_IMAGE_PATH + " TEXT);";
        db.execSQL(createChapters);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void open() {
        database = this.getWritableDatabase();
    }

    public List<Model_Manga> getMangas() {
        List<Model_Manga> lstManga = new ArrayList<>();
        open();

        String selectAllManga = "SELECT "
                + Constants.MANGA_ID + ", "
                + Constants.MANGA_NAME + ", "
                + Constants.MANGA_DESCRIPTION + ", "
                + Constants.MANGA_TOTAL_CHAPS + ", "
                + Constants.MANGA_CURRENT_CHAP + ", "
                + Constants.MANGA_IS_DOWNLOADED
                + " FROM " + Constants.TB_MANGA;
        Cursor mangas = database.rawQuery(selectAllManga, null);

        if (mangas.moveToFirst()) {
            while (!mangas.isAfterLast()) {
                // Get all fields
                int id = mangas.getInt(mangas.getColumnIndex(Constants.MANGA_ID));
                String mangaName = mangas.getString(mangas.getColumnIndex(Constants.MANGA_NAME));
                String mangaDescription = mangas.getString(mangas.getColumnIndex(Constants.MANGA_DESCRIPTION));
                int mangaTotalChap = mangas.getInt(mangas.getColumnIndex(Constants.MANGA_TOTAL_CHAPS));
                int mangaCurrentChap = mangas.getInt(mangas.getColumnIndex(Constants.MANGA_CURRENT_CHAP));
                boolean mangaIsDownloaded = (mangas.getInt(mangas.getColumnIndex(Constants.MANGA_IS_DOWNLOADED)) == 1);

                // Create and add manga into list
                Model_Manga manga = new Model_Manga(id, mangaName, mangaDescription, mangaIsDownloaded, mangaTotalChap, mangaCurrentChap);
                lstManga.add(manga);

                mangas.moveToNext();
            }
        }
        return lstManga;
    }

    public Model_Manga getManga(int mangaId) {
        open();
        Model_Manga manga = null;
        String selectManga = "SELECT "
                + Constants.MANGA_ID + ", "
                + Constants.MANGA_NAME + ", "
                + Constants.MANGA_DESCRIPTION + ", "
                + Constants.MANGA_TOTAL_CHAPS + ", "
                + Constants.MANGA_CURRENT_CHAP + ", "
                + Constants.MANGA_IS_DOWNLOADED
                + " FROM " + Constants.TB_MANGA
                + " WHERE MANGA_ID = " + mangaId;
        Cursor selectedManga = database.rawQuery(selectManga, null);
        if(selectedManga.moveToFirst()){
            // Get all fields
            int id = selectedManga.getInt(selectedManga.getColumnIndex(Constants.MANGA_ID));
            String mangaName = selectedManga.getString(selectedManga.getColumnIndex(Constants.MANGA_NAME));
            String mangaDescription = selectedManga.getString(selectedManga.getColumnIndex(Constants.MANGA_DESCRIPTION));
            int mangaTotalChap = selectedManga.getInt(selectedManga.getColumnIndex(Constants.MANGA_TOTAL_CHAPS));
            int mangaCurrentChap = selectedManga.getInt(selectedManga.getColumnIndex(Constants.MANGA_CURRENT_CHAP));
            boolean mangaIsDownloaded = (selectedManga.getInt(selectedManga.getColumnIndex(Constants.MANGA_IS_DOWNLOADED)) == 1);

            // Create object manga
            manga = new Model_Manga(id, mangaName, mangaDescription, mangaIsDownloaded, mangaTotalChap, mangaCurrentChap);
        }
        return manga;
    }

    public boolean addManga(Model_Manga manga) {
        open();
        ContentValues values = new ContentValues();
        values.put(Constants.MANGA_NAME, manga.getMangaName());
        values.put(Constants.MANGA_DESCRIPTION, manga.getMangaDescription());
        values.put(Constants.MANGA_TOTAL_CHAPS, manga.getTotalChaps());
        values.put(Constants.MANGA_CURRENT_CHAP, manga.getCurrentChap());
        values.put(Constants.MANGA_IS_DOWNLOADED, manga.isDownloaded());
        long result = database.insert(Constants.TB_MANGA, null, values);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Model_Chapter> getChapters(int mangaId) {
        List<Model_Chapter> lstChapter = new ArrayList<>();
        open();

        String selectAllChapterOfManga = "SELECT "
                + Constants.CHAPTER_ID + ", "
                + Constants.CHAPTER_MANGA_ID + ", "
                + Constants.CHAPTER_NAME + ", "
                + Constants.CHAPTER_IMAGE_PATH
                + " FROM " + Constants.TB_CHAPTER
                + " WHERE " + Constants.CHAPTER_MANGA_ID + " = " + mangaId;
        Cursor chapters = database.rawQuery(selectAllChapterOfManga, null);

        if (chapters.moveToFirst()) {
            while (!chapters.isAfterLast()) {
                // Get all fields
                int id = chapters.getInt(chapters.getColumnIndex(Constants.CHAPTER_ID));
                int chapterMangaId = chapters.getInt(chapters.getColumnIndex(Constants.CHAPTER_MANGA_ID));
                String chapterName = chapters.getString(chapters.getColumnIndex(Constants.CHAPTER_NAME));
                String chapterImgPath = chapters.getString(chapters.getColumnIndex(Constants.CHAPTER_IMAGE_PATH));

                // Create and add chapters into list
                Model_Chapter chapter = new Model_Chapter(id, chapterMangaId, chapterName, chapterImgPath);
                lstChapter.add(chapter);
                chapters.moveToNext();
            }
        }
        return lstChapter;
    }

    public boolean addChapter(Model_Chapter model_chapter) {
        open();
        ContentValues values = new ContentValues();
        values.put(Constants.CHAPTER_MANGA_ID, model_chapter.getMangaid());
        values.put(Constants.CHAPTER_NAME, model_chapter.getChapterName());
        values.put(Constants.CHAPTER_IMAGE_PATH, model_chapter.getImagePath());
        long result = database.insert(Constants.TB_CHAPTER, null, values);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }
}
