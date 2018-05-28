package cn.ucai.contact5.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.ucai.contact5.I;
import cn.ucai.contact5.R;
import cn.ucai.contact5.adapter.ContactAdapter;
import cn.ucai.contact5.bean.UserBean;
import cn.ucai.contact5.I2;
import cn.ucai.contact5.utils.OkUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {
    int pageId=1;

    RecyclerView mrvContact;
    ArrayList<UserBean> mContactlist;
    ContactAdapter mAdapter;

    LinearLayoutManager manager;

    SwipeRefreshLayout mSrlRefresh;
    TextView mPullUp;


    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout=inflater.inflate(R.layout.fragment_contact, container, false);
        initView(layout);
        downloadContactList(pageId, I2.DOWNLOAD);
        setListener();
        return layout;
    }

    private void setListener() {
        setPullUpListener();
        setPullDownListener();
    }

    private void setPullUpListener() {
        mrvContact.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mAdapter.setDragging(newState==RecyclerView.SCROLL_STATE_DRAGGING);
                int lastPosition = manager.findLastVisibleItemPosition();
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastPosition==mAdapter.getItemCount()-1&&mAdapter.isMore()){
                    pageId++;
                    downloadContactList(pageId,I2.PULL_UP);
                }
            }
        });
    }

    private void setPullDownListener() {
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageId=1;
                mSrlRefresh.setRefreshing(true);
                mPullUp.setVisibility(View.VISIBLE);
                downloadContactList(pageId,I2.PULL_DOWN);
            }
        });
    }

    private void downloadContactList(int pageId, final int action) {
        final OkUtils<UserBean[]> utils = new OkUtils<>(getActivity());
        utils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST,I.REQUEST_DOWNLOAD_CONTACT_LIST)
                .addParam(I.User.USER_NAME,"a")
                .addParam(I.PAGE_ID,pageId+"")
                .addParam(I.PAGE_SIZE,"10")
                .targetClass(UserBean[].class)
                .execute(new OkUtils.OnCompleteListener<UserBean[]>() {
                    @Override
                    public void onSuccess(UserBean[] result) {
                        mAdapter.setMore(result!=null&&result.length>0);
                        if(!mAdapter.isMore()){
                            if(action==I2.PULL_UP){
                                mAdapter.setFooterText("没有更多数据");
                            }
                            return;
                        }
                        ArrayList<UserBean> contactlist = utils.array2List(result);
                        mAdapter.setFooterText("加载更多数据");
                        switch(action){
                            case I2.DOWNLOAD:
                                mAdapter.initContact(contactlist);
                                break;
                            case I2.PULL_DOWN:
                                mSrlRefresh.setRefreshing(false);
                                mPullUp.setVisibility(View.GONE);
                                mAdapter.initContact(contactlist);
                                break;
                            case I2.PULL_UP:
                                mAdapter.addContact(contactlist);
                                break;
                        }
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void initView(View v) {
        mContactlist = new ArrayList<>();

        mrvContact = (RecyclerView) v.findViewById(R.id.rvContact);
        mAdapter = new ContactAdapter(getActivity(), mContactlist);
        mrvContact.setAdapter(mAdapter);

        manager = new LinearLayoutManager(getActivity());
        mrvContact.setLayoutManager(manager);

        mSrlRefresh = (SwipeRefreshLayout) v.findViewById(R.id.srlRefresh);
        mPullUp = (TextView) v.findViewById(R.id.tvPullup);
    }

}
