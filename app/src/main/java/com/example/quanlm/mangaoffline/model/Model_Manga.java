package com.example.quanlm.mangaoffline.model;

/**
 * Created by QuanLM on 7/17/2017.
 */

public class Model_Manga {
    String id, mangaName, mangaDescription;
    boolean isDownloaded;

    public Model_Manga(String id, String mangaName, String mangaDescription, boolean isDownloaded) {
        this.id = id;
        this.mangaName = mangaName;
        this.mangaDescription = mangaDescription;
        this.isDownloaded = isDownloaded;
    }

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public void setDownloaded(boolean downloaded) {
        isDownloaded = downloaded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMangaName() {
        return mangaName;
    }

    public void setMangaName(String mangaName) {
        this.mangaName = mangaName;
    }

    public String getMangaDescription() {
        return mangaDescription;
    }

    public void setMangaDescription(String mangaDescription) {
        this.mangaDescription = mangaDescription;
    }
}
