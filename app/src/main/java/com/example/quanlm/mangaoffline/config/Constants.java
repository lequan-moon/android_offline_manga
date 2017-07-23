package com.example.quanlm.mangaoffline.config;

import android.os.Environment;

/**
 * Created by MyPC on 18/07/2017.
 */

public class Constants {
    public static String SD_CARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/offline_manga/";

    public static final String SELECTED_MANGA_ID = "com.example.quanlm.mangaoffline.selected_manga_id";

    // Table manga
    public static final String TB_MANGA = "tblManga";
    public static final String MANGA_ID = "manga_id";
    public static final String MANGA_NAME = "manga_name";
    public static final String MANGA_DESCRIPTION = "manga_description";
    public static final String MANGA_IS_DOWNLOADED = "manga_is_downloaded";
    public static final String MANGA_TOTAL_CHAPS = "manga_total_chaps";
    public static final String MANGA_CURRENT_CHAP = "manga_current_chap";

    // Table chapter
    public static final String TB_CHAPTER = "tblChapter";
    public static final String CHAPTER_ID = "chapter_id";
    public static final String CHAPTER_MANGA_ID = "manga_id";
    public static final String CHAPTER_NAME = "chapter_name";
    public static final String CHAPTER_IMAGE_PATH = "chapter_img_path";
}
