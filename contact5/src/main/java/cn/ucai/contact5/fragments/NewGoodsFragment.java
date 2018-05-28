package cn.ucai.contact5.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.ucai.contact5.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewGoodsFragment extends Fragment {

    public NewGoodsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("main", "NewGoodsFragment.onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("main", "NewGoodsFragment.onCreateView()");
        return inflater.inflate(R.layout.fragment_new_goods, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("main", "NewGoodsFragment.onDestroy()");
    }
}
