package com.smallsnailtech.smallsnail.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smallsnailtech.smallsnail.R;

/**
 * @author luzhaosheng
 * @date 2018年11月06日
 */
public class BrowseFragment extends Fragment {

    private View mViewContent;

    public static BrowseFragment newInstance() {
//        Bundle bundle = new Bundle();
//        bundle.putString("", "");
        BrowseFragment browseFragment = new BrowseFragment();
//        functionSettings.setArguments(bundle);
        return browseFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mViewContent = inflater.inflate(R.layout.fragment_browse,null);
        Log.d(this.getClass().getName(), "onCreateView");
        return mViewContent;
    }

}
