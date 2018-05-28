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
public class BoutiqueFragment extends Fragment {


    public BoutiqueFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("main", "BoutiqueFragment.onCreate()");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("main", "BoutiqueFragment.onCreateView()");
        return inflater.inflate(R.layout.fragment_boutique, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("main", "BoutiqueFragment.onDestroy()");
    }
}
