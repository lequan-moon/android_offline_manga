package com.example.quanlm.mangaoffline.model;

/**
 * Created by QuanLM on 7/17/2017.
 */

public class Model_Manga {
    int id;
    String mangaName, mangaDescription;
    boolean isDownloaded;
    int totalChaps;
    int currentChap;

    public Model_Manga(int id, String mangaName, String mangaDescription, boolean isDownloaded, int totalChaps, int currentChap) {
        this.id = id;
        this.mangaName = mangaName;
        this.mangaDescription = mangaDescription;
        this.isDownloaded = isDownloaded;
        this.totalChaps = totalChaps;
        this.currentChap = currentChap;
    }

    public int getTotalChaps() {
        return totalChaps;
    }

    public void setTotalChaps(int totalChaps) {
        this.totalChaps = totalChaps;
    }

    public int getCurrentChap() {
        return currentChap;
    }

    public void setCurrentChap(int currentChap) {
        this.currentChap = currentChap;
    }

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public void setDownloaded(boolean downloaded) {
        isDownloaded = downloaded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
