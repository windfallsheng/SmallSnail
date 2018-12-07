package com.smallsnailtech.smallsnail.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smallsnailtech.smallsnail.R;
import com.smallsnailtech.smallsnail.entity.InfoCategoryEntity;
import com.smallsnailtech.smallsnail.minterface.OnRecyclerViewItemClick;

import java.util.List;

/**
 * @author luzhaosheng
 * @date 2018年11月06日
 */
public class InfoCategoryAdapter extends RecyclerView.Adapter<InfoCategoryAdapter.ViewHolderPageHistory> {

    private Context mContext;
    private List<InfoCategoryEntity> mInfoCategoryList;
    private OnRecyclerViewItemClick onRecyclerViewItemClick;

    public InfoCategoryAdapter(Context context, List<InfoCategoryEntity> infoCategoryEntity) {
        mContext = context;
        this.mInfoCategoryList = infoCategoryEntity;
    }

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClick onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }

    @Override
    public ViewHolderPageHistory onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderPageHistory viewHolderRminder = new ViewHolderPageHistory(
                LayoutInflater.from(mContext).inflate(R.layout.view_holder_info_category, parent, false));
        return viewHolderRminder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderPageHistory holder, final int position) {
        InfoCategoryEntity infoCategoryEntity = mInfoCategoryList.get(position);
        String categoryName = infoCategoryEntity.getCategoryName();
        holder.tvCategoryName.setText(categoryName);
    }

    @Override
    public int getItemCount() {
        if (mInfoCategoryList != null && mInfoCategoryList.size() > 0) {
            return mInfoCategoryList.size();
        }
        return 0;
    }

    class ViewHolderPageHistory extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvCategoryName;

        public ViewHolderPageHistory(View itemView) {
            super(itemView);
//        iv = (TextView) ImageView.findViewById(R.id.textview_remind_time);
            tvCategoryName = itemView.findViewById(R.id.textview_category_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onRecyclerViewItemClick != null) {
                onRecyclerViewItemClick.onItemClick(view, getAdapterPosition());
            }
        }
    }

}
