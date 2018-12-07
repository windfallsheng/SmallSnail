package com.smallsnailtech.smallsnail.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smallsnailtech.smallsnail.R;
import com.smallsnailtech.smallsnail.entity.PageHistoryEntity;
import com.smallsnailtech.smallsnail.minterface.OnRecyclerViewItemClick;

import java.util.List;

/**
 * @author luzhaosheng
 * @date 2018年11月06日
 */
public class PageHistoryAdapter extends RecyclerView.Adapter<PageHistoryAdapter.ViewHolderPageHistory> {

    private Context mContext;
    private List<PageHistoryEntity> mPageHistoryList;
    private OnRecyclerViewItemClick onRecyclerViewItemClick;

    public PageHistoryAdapter(Context context, List<PageHistoryEntity> pageHistoryList) {
        mContext = context;
        this.mPageHistoryList = pageHistoryList;
    }

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClick onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }

    @Override
    public ViewHolderPageHistory onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderPageHistory viewHolderRminder = new ViewHolderPageHistory(
                LayoutInflater.from(mContext).inflate(R.layout.item_page_history, parent, false));
        return viewHolderRminder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderPageHistory holder, final int position) {
        PageHistoryEntity historyEntity = mPageHistoryList.get(position);
        String pageName = historyEntity.getPageName();
        holder.tvPageName.setText(pageName);
    }

    @Override
    public int getItemCount() {
        if (mPageHistoryList != null && mPageHistoryList.size() > 0) {
            return mPageHistoryList.size();
        }
        return 0;
    }

    class ViewHolderPageHistory extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView iv;
        TextView tvPageName;

        public ViewHolderPageHistory(View itemView) {
            super(itemView);
//        iv = (TextView) ImageView.findViewById(R.id.textview_remind_time);
            tvPageName = itemView.findViewById(R.id.textview_page_name);
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
