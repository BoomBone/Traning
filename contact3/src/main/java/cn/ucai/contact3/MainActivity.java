package cn.ucai.contact3;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.ucai.contact3.adapter.ContactAdapter;
import cn.ucai.contact3.bean.UserBean;
import cn.ucai.contact3.utils.OkUtils;

public class MainActivity extends AppCompatActivity {
    int PageId=1;

    RecyclerView mrvContactList;
    ArrayList<UserBean> mMainContactList;
    ContactAdapter mAdapter;

    LinearLayoutManager linearLayoutManager ;

    SwipeRefreshLayout swipeRefreshLayout ;
    TextView mtvContactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        downloadContactList(PageId, I2.CONTACT_DOWN);
        setListener();
    }

    private void setListener() {
        setPullUpListener();
        setPullDownListener();
    }

    private void setPullDownListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                mtvContactList.setVisibility(View.VISIBLE);
                PageId=1;
                downloadContactList(PageId,I2.PULL_DOWN);
            }
        });
    }

    private void setPullUpListener() {
        mrvContactList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastposition=linearLayoutManager.findLastVisibleItemPosition();
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastposition==mAdapter.getItemCount()-1&&mAdapter.isMore()){
                    PageId++;
                    downloadContactList(PageId,I2.PULL_UP);
                }
            }
        });

    }

    private void downloadContactList(final int pageId, final int action) {
        final OkUtils<UserBean[]> utils = new OkUtils<>(this);
        utils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST,I.REQUEST_DOWNLOAD_CONTACT_LIST)
                .addParam(I.User.USER_NAME,"a")
                .addParam(I.PAGE_ID,pageId+"")
                .addParam(I.PAGE_SIZE,"10")
                .targetClass(UserBean[].class)
                .execute(new OkUtils.OnCompleteListener<UserBean[]>() {
                    @Override
                    public void onSuccess(UserBean[] result) {
                        //下拉刷新
                        mAdapter.setMore(result!=null&&result.length>0);
                        if(!mAdapter.isMore()){
                            if(action==I2.PULL_UP){
                                mAdapter.setFootertext("没有更多数据");
                            }
                            return;
                        }
                        ArrayList<UserBean> contactlist = utils.array2List(result);
                        mAdapter.setFootertext("加载更多数据");
                        switch(action){
                            case I2.CONTACT_DOWN:
                                mAdapter.initContact(contactlist);
                                break;
                            case I2.PULL_DOWN:
                                swipeRefreshLayout.setRefreshing(false);
                                mtvContactList.setVisibility(View.GONE);
                                mAdapter.initContact(contactlist);
                                break;
                            case I2.PULL_UP:
                                mAdapter.addContact(contactlist);
                                break;
                        }
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(MainActivity.this,error, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView() {
        mMainContactList = new ArrayList<>();
        mrvContactList = (RecyclerView) findViewById(R.id.rvContactList);
        mAdapter = new ContactAdapter(this, mMainContactList);
        mrvContactList.setAdapter(mAdapter);

        linearLayoutManager = new LinearLayoutManager(this);
        mrvContactList.setLayoutManager(linearLayoutManager);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srlContactList);
        mtvContactList = (TextView) findViewById(R.id.tvContactList);
    }
}
