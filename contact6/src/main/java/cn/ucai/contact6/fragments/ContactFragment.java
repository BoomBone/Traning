package cn.ucai.contact6.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.ucai.contact6.I;
import cn.ucai.contact6.I2;
import cn.ucai.contact6.R;
import cn.ucai.contact6.adapter.ContactAdapter;
import cn.ucai.contact6.bean.UserBean;
import cn.ucai.contact6.utils.OkUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {
    int pageId = 1;

    RecyclerView mrvContactList;
    ArrayList<UserBean> list;
    ContactAdapter mAdapter;

    //LinearLayoutManager manager;
    GridLayoutManager girdManager;

    SwipeRefreshLayout swipeRefreshLayout;
    TextView mtvPullDown;


    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_contact, container, false);
        initView(layout);
        downLoadContact(pageId, I2.DOWNLOAD);
        setListener();
        return layout;
    }

    private void downLoadContact(int pagesId, final int download) {
        final OkUtils<UserBean[]> utils = new OkUtils<>(getContext());
        utils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST, I.REQUEST_DOWNLOAD_CONTACT_LIST)
                .addParam(I.User.USER_NAME, "a")
                .addParam(I.PAGE_ID, pagesId + "")
                .addParam(I.PAGE_SIZE, "10")
                .targetClass(UserBean[].class)
                .execute(new OkUtils.OnCompleteListener<UserBean[]>() {
                    @Override
                    public void onSuccess(UserBean[] result) {

                        mAdapter.setMore(result != null && result.length > 0);
                        if (!mAdapter.isMore()) {
                            if (download == I2.PULLUP ) {
                                mAdapter.setFooterText("别拉了，没了");
                            }
                            return;
                        }
                        ArrayList<UserBean> partlist = utils.array2List(result);
                        mAdapter.setFooterText("加载更多数据");
                        Log.i("main", "result:" + partlist);
                        switch (download) {
                            case I2.DOWNLOAD:
                                mAdapter.initContact(partlist);
                                break;
                            case I2.PULLUP:
                                mAdapter.addContact(partlist);
                                break;
                            case I2.PULLDOWN:
                                swipeRefreshLayout.setRefreshing(false);
                                mtvPullDown.setVisibility(View.GONE);
                                mAdapter.initContact(partlist);
                                break;
                        }

                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void setListener() {
        setPullUpListener();
        setPullDownListener();
    }

    private void setPullUpListener() {
        mrvContactList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastPosition = girdManager.findLastVisibleItemPosition();
                mAdapter.setDragging(newState==RecyclerView.SCROLL_STATE_DRAGGING);
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastPosition==mAdapter.getItemCount()-1&&mAdapter.isMore()){
                    pageId++;
                    downLoadContact(pageId,I2.PULLUP);
                }

            }
        });

    }

    private void setPullDownListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                pageId=1;
                mtvPullDown.setVisibility(View.VISIBLE);
                downLoadContact(pageId,I2.PULLDOWN);
            }
        });
    }

    private void initView(View layout) {
        list = new ArrayList<>();

        swipeRefreshLayout = (SwipeRefreshLayout) layout.findViewById(R.id.srlRefresh);
        mtvPullDown = (TextView) layout.findViewById(R.id.tvPullDownRefresh);

        mrvContactList = (RecyclerView) layout.findViewById(R.id.rvContactList);
        mAdapter = new ContactAdapter(getContext(), list);
        mrvContactList.setAdapter(mAdapter);

        girdManager = new GridLayoutManager(getActivity(), 3);
        //manager = new LinearLayoutManager(getActivity());
        mrvContactList.setLayoutManager(girdManager);


    }

}
