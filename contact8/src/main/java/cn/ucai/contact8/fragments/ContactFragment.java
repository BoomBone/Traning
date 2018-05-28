package cn.ucai.contact8.fragments;


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

import cn.ucai.contact8.I;
import cn.ucai.contact8.I2;
import cn.ucai.contact8.R;
import cn.ucai.contact8.adapter.ContactAdapter;
import cn.ucai.contact8.bean.UserBean;
import cn.ucai.contact8.utils.OkUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {
    int pageId = 1;

    RecyclerView mrvContactList;
    ContactAdapter mAdapter;
    ArrayList<UserBean> contactlist;

    LinearLayoutManager linearLayoutManager;

    SwipeRefreshLayout mSRLRefresh;
    TextView mPullDown;

    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_contact, container, false);
        initView(layout);
        downloadContactList(pageId, I2.ACTION_DOWNLOAD);
        setListener();
        return layout;
    }

    private void setListener() {
        setOnPullUpListener();
        setOnPullDownListener();
    }

    private void setOnPullDownListener() {
        mSRLRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageId=1;
                mSRLRefresh.setRefreshing(true);
                mPullDown.setVisibility(View.VISIBLE);
                downloadContactList(pageId,I2.ACTION_PULL_DOWN);
            }
        });

    }

    private void setOnPullUpListener() {
        mrvContactList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastPosition = linearLayoutManager.findLastVisibleItemPosition();
                mAdapter.setDragging(newState==RecyclerView.SCROLL_STATE_DRAGGING);
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastPosition==mAdapter.getItemCount()-1&&mAdapter.isMore()){
                    pageId++;
                    downloadContactList(pageId,I2.ACTION_PULL_UP);

                }
            }
        });

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
                            if (action == I2.ACTION_PULL_UP) {
                                mAdapter.setFooterText("别拉了，没了");
                            }
                            return;
                        }
                        ArrayList<UserBean> partlist = utils.array2List(result);
                        mAdapter.setFooterText("加载更多数据");
                        switch (action) {
                            case I2.ACTION_DOWNLOAD:
                                mAdapter.initContact(partlist);
                                break;
                            case I2.ACTION_PULL_DOWN:
                                mSRLRefresh.setRefreshing(false);
                                mPullDown.setVisibility(View.GONE);
                                mAdapter.initContact(partlist);
                                break;
                            case I2.ACTION_PULL_UP:
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

    private void initView(View layout) {
        contactlist = new ArrayList<>();
        mrvContactList = (RecyclerView) layout.findViewById(R.id.rvContactlist);
        mAdapter = new ContactAdapter(getActivity(), contactlist);
        mrvContactList.setAdapter(mAdapter);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        mrvContactList.setLayoutManager(linearLayoutManager);

        mSRLRefresh = (SwipeRefreshLayout) layout.findViewById(R.id.srfRefresh);
        mPullDown = (TextView) layout.findViewById(R.id.tvpulldown);
    }

}
