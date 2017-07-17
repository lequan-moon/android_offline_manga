package com.example.quanlm.mangaoffline.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlm.mangaoffline.R;
import com.example.quanlm.mangaoffline.model.Model_Manga;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QuanLM on 7/17/2017.
 */

public class AdtManga extends RecyclerView.Adapter<AdtManga.MangaViewHolder> {
    Context mContext;
    List<Model_Manga> lstManga;
    List<OnMangaSelect> lstMangaSelectsListeners = new ArrayList<>();

    public AdtManga(Context mContext, List<Model_Manga> lstManga, OnMangaSelect listener) {
        this.mContext = mContext;
        this.lstManga = lstManga;
        lstMangaSelectsListeners.add(listener);
    }

    @Override
    public MangaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vMangaItem = LayoutInflater.from(mContext).inflate(R.layout.manga_item, parent, false);
        MangaViewHolder vhManga = new MangaViewHolder(vMangaItem);
        return vhManga;
    }

    @Override
    public void onBindViewHolder(MangaViewHolder holder, int position) {
        Model_Manga manga = lstManga.get(position);
        holder.txtMangaId.setText(manga.getId());
        holder.txtMangaName.setText(manga.getMangaName());
        holder.txtMangaDescription.setText(manga.getMangaDescription());
        if (manga.isDownloaded()) {
            holder.txtIsDownloaded.setBackground(mContext.getDrawable(R.drawable.ic_star_black_24dp));
        } else {
            holder.txtIsDownloaded.setBackground(mContext.getDrawable(R.drawable.ic_star_border_black_24dp));
        }
    }

    @Override
    public int getItemCount() {
        return lstManga.size();
    }

    class MangaViewHolder extends RecyclerView.ViewHolder {
        TextView txtMangaName;
        TextView txtMangaDescription;
        ImageView imgMangaThumb;
        TextView txtIsDownloaded;
        TextView txtMangaId;

        public MangaViewHolder(View itemView) {
            super(itemView);
            txtMangaName = (TextView) itemView.findViewById(R.id.txtMangaName);
            txtMangaDescription = (TextView) itemView.findViewById(R.id.txtMangaDescription);
            imgMangaThumb = (ImageView) itemView.findViewById(R.id.imgMangaThumb);
            txtIsDownloaded = (TextView) itemView.findViewById(R.id.txtIsDownloaded);
            txtMangaId = (TextView) itemView.findViewById(R.id.txtMangaId);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (OnMangaSelect listener : lstMangaSelectsListeners) {
                        listener.onSelect(txtMangaId.getText().toString());
                    }
                }
            });
        }
    }

    public interface OnMangaSelect {
        void onSelect(String mangaID);
    }
}
