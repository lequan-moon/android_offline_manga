package com.example.quanlm.mangaoffline.logic;

import com.example.quanlm.mangaoffline.model.Model_Manga;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QuanLM on 7/17/2017.
 */

public class LogicManga {
    /*
    * Get list manga from both online and offline source
    * online source is webservice
    * offline source is sqlite
    */
    public static List<Model_Manga> getListManga(){
        List<Model_Manga> lstMangas = new ArrayList<>();
        // Get from online source

        // Get from DB
        lstMangas.add(new Model_Manga("1", "manga 1", "manga1 description", true));
        lstMangas.add(new Model_Manga("2", "manga 2", "manga2 description", true));
        lstMangas.add(new Model_Manga("3", "manga 3", "manga3 description", true));
        lstMangas.add(new Model_Manga("4", "manga 4", "manga4 description", true));
        lstMangas.add(new Model_Manga("5", "manga 5", "manga5 description", true));
        lstMangas.add(new Model_Manga("6", "manga 6", "manga6 description", true));
        lstMangas.add(new Model_Manga("7", "manga 7", "manga7 description", true));
        lstMangas.add(new Model_Manga("8", "manga 8", "manga8 description", true));
        lstMangas.add(new Model_Manga("9", "manga 9", "manga9 description", true));
        lstMangas.add(new Model_Manga("10", "manga 10", "manga10 description", true));
        lstMangas.add(new Model_Manga("11", "manga 11", "manga11 description", true));

        return lstMangas;
    }
}
