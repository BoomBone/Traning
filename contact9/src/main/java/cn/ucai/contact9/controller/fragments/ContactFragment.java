package cn.ucai.contact9.controller.fragments;


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

import cn.ucai.contact9.inter.I;
import cn.ucai.contact9.inter.I2;
import cn.ucai.contact9.R;
import cn.ucai.contact9.controller.adapter.ContactAdapter;
import cn.ucai.contact9.bean.UserBean;
import cn.ucai.contact9.model.net.IModleContact;
import cn.ucai.contact9.model.net.ModleContact;
import cn.ucai.contact9.model.net.OnCompleteListener;
import cn.ucai.contact9.model.utils.ConvertUtils;
import cn.ucai.contact9.model.utils.OkUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {
    int mPageId;
    IModleContact mModel;

    RecyclerView mrvContact;
    ContactAdapter mAdapter;
    ArrayList<UserBean> mContactlist;

    LinearLayoutManager linearLayoutManager;

    SwipeRefreshLayout msrlRefresh;
    TextView mPulldown;

    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout=inflater.inflate(R.layout.fragment_contact, container, false);
        mPageId=1;
        mModel=new ModleContact();
        initView(layout);
        setListener();
        downloadContactList(mPageId, I2.ACTIVITY_DOWNLOAD);

        return layout;
    }

    private void setListener() {
        setOnPullDownListener();
        setOnPullUpListener();
    }

    private void setOnPullUpListener() {
        mrvContact.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mAdapter.setDragging(newState==RecyclerView.SCROLL_STATE_DRAGGING);
                int lastPosition = linearLayoutManager.findLastVisibleItemPosition();
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastPosition==mAdapter.getItemCount()-1&&mAdapter.isMore()){
                    mPageId++;
                    downloadContactList(mPageId,I2.ACTIVITY_PULL_UP);
                }
            }
        });


    }

    private void setOnPullDownListener() {
        msrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPageId=1;
                msrlRefresh.setRefreshing(true);
                mPulldown.setVisibility(View.VISIBLE);
                downloadContactList(mPageId,I2.ACTIVITY_PULL_DOWN);
            }
        });


    }

    private void downloadContactList(int pageId, final int action) {
        mModel.downloadContactList(getActivity(), "a", pageId, action, new OnCompleteListener<UserBean[]>() {
            @Override
            public void onSuccess(UserBean[] result) {
                mAdapter.setMore(result!=null&&result.length>0);
                if(!mAdapter.isMore()){
                    if(action==I2.ACTIVITY_PULL_UP){
                        mAdapter.setmFooterText("别拉了，没了");
                    }
                    return;
                }

                ArrayList<UserBean> partlist = ConvertUtils.array2List(result);
                mAdapter.setmFooterText("加载更多数据");
                switch (action){
                    case I2.ACTIVITY_DOWNLOAD:
                        mAdapter.initContact(partlist);
                        break;
                    case I2.ACTIVITY_PULL_DOWN:
                        msrlRefresh.setRefreshing(false);
                        mPulldown.setVisibility(View.GONE);
                        mAdapter.initContact(partlist);
                        break;
                    case I2.ACTIVITY_PULL_UP:
                        mAdapter.addContact(partlist);
                        break;

                }

            }

            @Override
            public void onError(String error) {
                Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
            }
        });

      /*  final OkUtils<UserBean[]> utils = new OkUtils<>(getActivity());
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
                            if(action==I2.ACTIVITY_PULL_UP){
                                mAdapter.setmFooterText("别拉了，没了");
                            }
                            return;
                        }

                        ArrayList<UserBean> partlist = utils.array2List(result);
                        mAdapter.setmFooterText("加载更多数据");
                        switch (action){
                            case I2.ACTIVITY_DOWNLOAD:
                                mAdapter.initContact(partlist);
                                break;
                            case I2.ACTIVITY_PULL_DOWN:
                                msrlRefresh.setRefreshing(false);
                                mPulldown.setVisibility(View.GONE);
                                mAdapter.initContact(partlist);
                                break;
                            case I2.ACTIVITY_PULL_UP:
                                mAdapter.addContact(partlist);
                                break;

                        }

                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                    }
                });*/
    }

    private void initView(View layout) {
        mContactlist = new ArrayList<>();

        mrvContact = (RecyclerView) layout.findViewById(R.id.rvContactlist);
        mAdapter = new ContactAdapter(getActivity(), mContactlist);
        mrvContact.setAdapter(mAdapter);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        mrvContact.setLayoutManager(linearLayoutManager);

        msrlRefresh = (SwipeRefreshLayout) layout.findViewById(R.id.srfRefresh);
        mPulldown = (TextView) layout.findViewById(R.id.tvPullDown);

    }

}
