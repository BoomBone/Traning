package cn.ucai.contact;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.ucai.contact.adapter.ContactAdapter;
import cn.ucai.contact.bean.UserBean;
import cn.ucai.contact.dao.Dao;
import cn.ucai.contact.utils.OkImageLoader;
import cn.ucai.contact.utils.OkUtils;

public class MainActivity extends AppCompatActivity {


    RecyclerView mrvContact;
    ArrayList<UserBean> contactlist;
    ContactAdapter contactAdapter;
    LinearLayoutManager linearlayout;

    int PageId=1;

    SwipeRefreshLayout mRefresh;
    TextView mtvRefresh;
    Dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        dao = new Dao(this, contactAdapter,mRefresh,mtvRefresh);
        dao.downloadContactList(PageId,I2.ACTION_DOWLOAD);
        setListener();
    }

    private void setListener() {
        setPullDownListener();
        setPullUpListern();
    }

    private void setPullUpListern() {
        mrvContact.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastPosition=linearlayout.findLastVisibleItemPosition();
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastPosition==contactAdapter.getItemCount()-1&&contactAdapter.isMore){
                    PageId++;
                    dao.downloadContactList(PageId,I2.ACTION_PULLUP);
                }
            }
        });
    }

    private void setPullDownListener() {
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mtvRefresh.setVisibility(View.VISIBLE);
                mRefresh.setRefreshing(true);
                PageId=1;
                dao.downloadContactList(PageId,I2.ACTION_PULLDOWN);
            }
        });
    }


    private void initView() {
        contactlist = new ArrayList<>();
        mrvContact = (RecyclerView) findViewById(R.id.rvcontact);
        contactAdapter = new ContactAdapter(this, contactlist);
        mrvContact.setAdapter(contactAdapter);

        linearlayout = new LinearLayoutManager(this);
        mrvContact.setLayoutManager(linearlayout);

        mRefresh = (SwipeRefreshLayout) findViewById(R.id.srflcontact);
        mtvRefresh = (TextView) findViewById(R.id.tvRefresh);
    }

}
