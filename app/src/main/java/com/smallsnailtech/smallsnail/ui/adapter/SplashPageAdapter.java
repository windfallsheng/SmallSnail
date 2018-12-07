package com.smallsnailtech.smallsnail.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smallsnailtech.smallsnail.R;
import com.smallsnailtech.smallsnail.command.Constants;

import java.util.List;

/**
 * @author luzhaosheng
 * @date 2018年11月06日
 */
public class SplashPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> mData;
    private final int DATA_TYPE_SPLASH_IMAGE = Constants.DATA_TYPE_SPLASH_IMAGE;   //
    private final int DATA_TYPE_SPLASH_MEDIA = Constants.DATA_TYPE_SPLASH_MEDIA;   //

    public SplashPageAdapter(Context context, List<String> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getItemViewType(int position) {
//        if (position % 2 == 0) {
        return DATA_TYPE_SPLASH_IMAGE;
//        } else {
//            return DATA_TYPE_SPLASH_MEDIA;
//        }
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case DATA_TYPE_SPLASH_IMAGE:
                return new ViewHolderImage(
                        LayoutInflater.from(mContext).inflate(R.layout.view_holder_splash_type_image, parent, false));
            case DATA_TYPE_SPLASH_MEDIA:
                return new ViewHolderMedia(
                        LayoutInflater.from(mContext).inflate(R.layout.view_holder_splash_type_media, parent, false));
            default:
                return new ViewHolderImage(
                        LayoutInflater.from(mContext).inflate(R.layout.view_holder_splash_type_image, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolderImage) {
//            Glide.with(mContext).load(R.drawable.f).into(((ViewHolderImage) holder).ivImage);
//            ((ViewHolderImage) holder).ivImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.f));
        } else if (holder instanceof ViewHolderMedia) {
            Glide.with(mContext).load(R.drawable.cb).into(((ViewHolderMedia) holder).ivMedia);
//            ((ViewHolderMedia) holder).ivMedia.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_logo));
        } else {
            Glide.with(mContext).load(R.drawable.f).into(((ViewHolderImage) holder).ivImage);
//            ((ViewHolderImage) holder).ivImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.f));
        }
    }

    @Override
    public int getItemCount() {
        if (mData != null && mData.size() > 0) {
            return mData.size();
//            return Integer.MAX_VALUE;
        }
        return 0;
    }

    class ViewHolderImage extends RecyclerView.ViewHolder {

        ImageView ivImage;

        public ViewHolderImage(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.imageview_splash_image);
        }
    }

    class ViewHolderMedia extends RecyclerView.ViewHolder {

        ImageView ivMedia;

        public ViewHolderMedia(View itemView) {
            super(itemView);
            ivMedia = itemView.findViewById(R.id.imageview_splash_media);
        }
    }

}
