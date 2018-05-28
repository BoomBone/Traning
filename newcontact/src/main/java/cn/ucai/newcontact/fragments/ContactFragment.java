package cn.ucai.newcontact.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.ucai.newcontact.I;
import cn.ucai.newcontact.I2;
import cn.ucai.newcontact.R;
import cn.ucai.newcontact.adapter.ContactAdapter;
import cn.ucai.newcontact.bean.UserBean;
import cn.ucai.newcontact.utils.OkUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {
    int pageId = 1;

    RecyclerView mrvContactList;
    ArrayList<UserBean> list;
    ContactAdapter mAdapter;

    LinearLayoutManager manager;

    SwipeRefreshLayout msrlRefresh;
    TextView mtvPulldown;


    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_contact, container, false);
        initView(layout);
        downloadContactList(pageId, I2.DOWNLOAD);
        setListener();
        return layout;
    }

    private void downloadContactList(int pageId, final int action) {
        final OkUtils<UserBean[]> utils = new OkUtils<>(getActivity());
        utils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST, I.REQUEST_DOWNLOAD_CONTACT_LIST)
                .addParam(I.User.USER_NAME, "a")
                .addParam(I.PAGE_ID, pageId + "")
                .addParam(I.PAGE_SIZE, "10")
                .targetClass(UserBean[].class)
                .execute(new OkUtils.OnCompleteListener<UserBean[]>() {
                    @Override
                    public void onSuccess(UserBean[] result) {
                        mAdapter.setMore(result != null && result.length > 0);
                        if (!mAdapter.isMore()) {
                            if (action == I2.PULL_UP) {
                                mAdapter.setFooterText("没有更多数据");
                            }
                            return;
                        }
                        ArrayList<UserBean> partlist = utils.array2List(result);
                        mAdapter.setFooterText("加载更多数据");
                        switch (action) {
                            case I2.DOWNLOAD:
                                mAdapter.initContact(partlist);
                                break;
                            case I2.PULL_DOWN:
                                msrlRefresh.setRefreshing(false);
                                mtvPulldown.setVisibility(View.GONE);
                                mAdapter.initContact(partlist);
                                break;
                            case I2.PULL_UP:
                                mAdapter.addContact(partlist);
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
        setPullDownListener();
        setPullUpListener();
    }

    private void setPullDownListener() {
        msrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                msrlRefresh.setRefreshing(true);
                mtvPulldown.setVisibility(View.VISIBLE);
                pageId = 1;
                downloadContactList(pageId, I2.PULL_DOWN);
            }
        });
    }

    private void setPullUpListener() {
        mrvContactList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastPosition = manager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPosition == mAdapter.getItemCount() - 1 && mAdapter.isMore()) {
                    pageId++;
                    downloadContactList(pageId, I2.PULL_UP);
                }
            }
        });
    }

    private void initView(View layout) {
        list = new ArrayList<>();
        mrvContactList = (RecyclerView) layout.findViewById(R.id.rvContactList);
        mAdapter = new ContactAdapter(getActivity(), list);
        mrvContactList.setAdapter(mAdapter);

        manager = new LinearLayoutManager(getActivity());
        mrvContactList.setLayoutManager(manager);

        msrlRefresh = (SwipeRefreshLayout) layout.findViewById(R.id.srlItem);
        mtvPulldown = (TextView) layout.findViewById(R.id.tvPullDown);
    }

}
