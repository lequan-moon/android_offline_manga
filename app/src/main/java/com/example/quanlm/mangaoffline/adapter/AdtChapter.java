package com.example.quanlm.mangaoffline.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quanlm.mangaoffline.R;
import com.example.quanlm.mangaoffline.model.Model_Chapter;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by MyPC on 18/07/2017.
 */

public class AdtChapter extends RecyclerView.Adapter<AdtChapter.ChapterViewHolder> {
    Context mContext;
    List<Model_Chapter> lstChapter;
    List<OnChapterSelect> lstChapterSelectListener;

    public AdtChapter(Context mContext, List<Model_Chapter> lstChapter) {
        this.mContext = mContext;
        this.lstChapter = lstChapter;
    }

    @Override
    public ChapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.chapter_item, parent, false);
        ChapterViewHolder chapterViewHolder = new ChapterViewHolder(itemView);
        return chapterViewHolder;
    }

    @Override
    public void onBindViewHolder(ChapterViewHolder holder, int position) {
        Model_Chapter chapter = lstChapter.get(position);
        holder.txtChapterId.setText(String.valueOf(chapter.getChapterid()));
        holder.txtChapterName.setText(chapter.getChapterName());
    }

    @Override
    public int getItemCount() {
        return lstChapter.size();
    }

    class ChapterViewHolder extends RecyclerView.ViewHolder{
        TextView txtChapterName;
        TextView txtChapterId;

        public ChapterViewHolder(View itemView) {
            super(itemView);
            txtChapterId = (TextView) itemView.findViewById(R.id.txtChapterId);
            txtChapterName = (TextView) itemView.findViewById(R.id.txtChapterName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (OnChapterSelect listener : lstChapterSelectListener) {
                        listener.onSelect(Integer.parseInt(txtChapterId.getText().toString()));
                    }
                }
            });
        }
    }

    public interface OnChapterSelect {
        void onSelect(int chapterID);
    }
}
