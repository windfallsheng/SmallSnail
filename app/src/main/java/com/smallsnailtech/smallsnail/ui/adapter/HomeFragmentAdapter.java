package com.smallsnailtech.smallsnail.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.smallsnailtech.smallsnail.R;
import com.smallsnailtech.smallsnail.command.Constants;
import com.smallsnailtech.smallsnail.manager.GlideImageLoader;
import com.smallsnailtech.smallsnail.minterface.OnRecyclerViewItemClick;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;

import static com.smallsnailtech.smallsnail.command.Constants.DATA_TYPE_SPLASH_IMAGE;
import static com.smallsnailtech.smallsnail.command.Constants.DATA_TYPE_SPLASH_MEDIA;

/**
 * @author luzhaosheng
 * @date 2018年11月06日
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> mData;
    private OnRecyclerViewItemClick onRecyclerViewItemClick;
    private final int DATA_TYPE_BANNER_LOOPER = Constants.DATA_TYPE_HOME_BANNER_LOOPER;   //
    private final int DATA_TYPE_IMAGE_TOP = Constants.DATA_TYPE_HOME_IMAGE_TOP;   //
    private final int DATA_TYPE_IMAGE_RIGHT = Constants.DATA_TYPE_HOME_IMAGE_RIGHT;   //
    private final int DATA_TYPE_HOME_IMAGE_MORE = Constants.DATA_TYPE_HOME_IMAGE_MORE;   //

    public HomeFragmentAdapter(Context context, List<String> data) {
        mContext = context;
        mData = data;
    }

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClick onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return DATA_TYPE_BANNER_LOOPER;
        } else if (position % 2 == 0) {
            return DATA_TYPE_IMAGE_TOP;
        } else if (position % 3 == 0) {
            return DATA_TYPE_IMAGE_RIGHT;
        } else if (position % 5 == 0) {
            return DATA_TYPE_HOME_IMAGE_MORE;
        } else {
            return DATA_TYPE_IMAGE_TOP;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case DATA_TYPE_BANNER_LOOPER:
                return new ViewHolderBannerLooper(
                        LayoutInflater.from(mContext).inflate(R.layout.view_holder_home_fragment_card_banner_looper, parent, false));
            case DATA_TYPE_IMAGE_TOP:
                return new ViewHolderImageTop(
                        LayoutInflater.from(mContext).inflate(R.layout.view_holder_home_fragment_card_image_top, parent,
                                false));
            case DATA_TYPE_IMAGE_RIGHT:
                return new ViewHolderImageTop(
                        LayoutInflater.from(mContext).inflate(R.layout.view_holder_home_fragment_card_image_right, parent,
                                false));
            case DATA_TYPE_HOME_IMAGE_MORE:
                return new ViewHolderImageTop(
                        LayoutInflater.from(mContext).inflate(R.layout.view_holder_home_fragment_card_image_more, parent,
                                false));
            default:
                return new ViewHolderImageTop(
                        LayoutInflater.from(mContext).inflate(R.layout.view_holder_home_fragment_card_image_top, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolderBannerLooper) {
            //设置banner样式
            ((ViewHolderBannerLooper) holder).banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            //设置图片加载器
            ((ViewHolderBannerLooper) holder).banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            //资源文件
            List<String> images = new ArrayList<>();
            images.add("http://atjp.hipad.com:9010/mdm_hpoa/Uploads/img/bybannert/2016-12-09/584a0b89512d4.jpg");
            images.add("http://atjp.hipad.com:9010/mdm_hpoa/Uploads/img/bybannert/2016-12-09/584a0be83240f.jpg");
            images.add("http://atjp.hipad.com:9010/mdm_hpoa/Uploads/img/bybannert/2016-12-09/584a0c4ab8f44.jpg");
            ((ViewHolderBannerLooper) holder).banner.setImages(images);
            //设置banner动画效果
            ((ViewHolderBannerLooper) holder).banner.setBannerAnimation(Transformer.DepthPage);
            //设置标题集合（当banner样式有显示title时）
            List<String> titles = new ArrayList<>();
            titles.add("标题1");
            titles.add("标题2");
            titles.add("标题3");
            ((ViewHolderBannerLooper) holder).banner.setBannerTitles(titles);
            //设置自动轮播，默认为true
            ((ViewHolderBannerLooper) holder).banner.isAutoPlay(true);
            //设置轮播时间
            ((ViewHolderBannerLooper) holder).banner.setDelayTime(1500);
            //设置指示器位置（当banner模式中有指示器时）
            ((ViewHolderBannerLooper) holder).banner.setIndicatorGravity(BannerConfig.CENTER);
            //banner设置方法全部调用完毕时最后调用
            ((ViewHolderBannerLooper) holder).banner.start();
//            Glide.with(mContext).load(R.drawable.f).into(((ViewHolderImage) holder).ivImage);
//            ((ViewHolderImage) holder).ivImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.f));
        } else if (holder instanceof ViewHolderImageTop) {
            Glide.with(mContext).load("http://atjp.hipad.com:9010/mdm_hpoa/Uploads/img/bybannert/2016-12-09/584a0b89512d4.jpg")
                    .listener(mRequestListener).into(((ViewHolderImageTop) holder).ivImage);
//            ((ViewHolderMedia) holder).ivMedia.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_logo));
        } else if (holder instanceof ViewHolderImageRight) {
            Glide.with(mContext).load("http://atjp.hipad.com:9010/mdm_hpoa/Uploads/img/bybannert/2016-12-09/584a0be83240f.jpg")
                    .into(((ViewHolderImageRight) holder).ivImage);
//            ((ViewHolderMedia) holder).ivMedia.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_logo));
        } else if (holder instanceof ViewHolderImageMore) {
            Glide.with(mContext).load("http://atjp.hipad.com:9010/mdm_hpoa/Uploads/img/bybannert/2016-12-09/584a0c4ab8f44.jpg")
                    .into(((ViewHolderImageMore) holder).ivImage);
//            ((ViewHolderMedia) holder).ivMedia.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_logo));
        } else {
//            Glide.with(mContext).load(R.drawable.f).into(((ViewHolderImageTop) holder).ivImage);
//            ((ViewHolderImage) holder).ivImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.f));
        }
    }

    @Override
    public int getItemCount() {
        if (mData != null && mData.size() > 0) {
            return mData.size();
//            return Integer.MAX_VALUE;
        }
        return 35;
    }

    class ViewHolderBannerLooper extends RecyclerView.ViewHolder implements View.OnClickListener {

        Banner banner;

        public ViewHolderBannerLooper(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onRecyclerViewItemClick != null) {
                onRecyclerViewItemClick.onItemClick(view, getAdapterPosition());
            }
        }
    }

    class ViewHolderImageTop extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivImage;
        CardView cardView;

        public ViewHolderImageTop(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.imageview_home_image);
            cardView = itemView.findViewById(R.id.cardview_home_fragment_item);
            cardView.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onRecyclerViewItemClick != null) {
                onRecyclerViewItemClick.onItemClick(view, getAdapterPosition());
            }
        }
    }

    class ViewHolderImageRight extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivImage;
        CardView cardView;

        public ViewHolderImageRight(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.imageview_home_image);
            cardView = itemView.findViewById(R.id.cardview_home_fragment_item);
            cardView.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onRecyclerViewItemClick != null) {
                onRecyclerViewItemClick.onItemClick(view, getAdapterPosition());
            }
        }
    }

    class ViewHolderImageMore extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivImage;
        CardView cardView;

        public ViewHolderImageMore(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.imageview_home_image);
            cardView = itemView.findViewById(R.id.cardview_home_fragment_item);
            cardView.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onRecyclerViewItemClick != null) {
                onRecyclerViewItemClick.onItemClick(view, getAdapterPosition());
            }
        }
    }

    RequestListener mRequestListener = new RequestListener() {
        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
            Log.d("Glide_RequestListener", "onException: " + e.toString() + "  model:" + model + " isFirstResource: " + isFirstResource);
            return false;
        }

        @Override
        public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
            Log.e("Glide_RequestListener", "model:" + model + " isFirstResource: " + isFirstResource);
            return false;
        }
    };


}
