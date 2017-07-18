package com.example.quanlm.mangaoffline.model;

/**
 * Created by MyPC on 18/07/2017.
 */

public class Model_Chapter {
    int mangaid, chapterid;
    String chapterName, imagePath;

    public Model_Chapter(int chapterid, int mangaid, String chapterName, String imagePath) {
        this.chapterid = chapterid;
        this.mangaid = mangaid;
        this.chapterName = chapterName;
        this.imagePath = imagePath;
    }

    public Model_Chapter(int mangaid, String chapterName, String imagePath) {
        this.mangaid = mangaid;
        this.chapterName = chapterName;
        this.imagePath = imagePath;
    }

    public int getChapterid() {
        return chapterid;
    }

    public void setChapterid(int chapterid) {
        this.chapterid = chapterid;
    }

    public int getMangaid() {
        return mangaid;
    }

    public void setMangaid(int mangaid) {
        this.mangaid = mangaid;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
