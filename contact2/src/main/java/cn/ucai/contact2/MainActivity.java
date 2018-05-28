package cn.ucai.contact2;

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

import cn.ucai.contact2.adapter.ContactAdapter;
import cn.ucai.contact2.bean.UserBean;
import cn.ucai.contact2.utils.OkImageLoader;
import cn.ucai.contact2.utils.OkUtils;

import static android.R.attr.action;
import static android.R.attr.visibility;

public class MainActivity extends AppCompatActivity {

    RecyclerView mrvContactItem;
    ArrayList<UserBean> contactList;
    ContactAdapter contactAdapter;
    LinearLayoutManager linearLayoutManager;

    SwipeRefreshLayout msrlContactList;
    TextView mtvPullDown;

    int pageId=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        downloadContactList(pageId,I2.ACTION_DOWNLOAD);
        setListener();
    }

    private void setListener() {
        setPullUpListener();
        setPullDownListener();
    }

    private void setPullDownListener() {
        msrlContactList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //OkImageLoader.release();//释放缓存的图片
                msrlContactList.setRefreshing(true);
                mtvPullDown.setVisibility(View.VISIBLE);
                pageId=1;
                downloadContactList(pageId,I2.PULL_DOWN);
            }
        });
    }

    private void setPullUpListener() {
        mrvContactItem.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                contactAdapter.setDragging(newState==RecyclerView.SCROLL_STATE_DRAGGING);
                int lastPosition=linearLayoutManager.findLastVisibleItemPosition();
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastPosition==contactAdapter.getItemCount()-1&&contactAdapter.isMore()){
                    pageId++;
                    downloadContactList(pageId,I2.PULL_UP);
                }
            }
        });
    }

    private void downloadContactList(int pageId, final int action) {
        final OkUtils<UserBean[]> okUtils = new OkUtils<>(this);
        okUtils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST,I.REQUEST_DOWNLOAD_CONTACT_LIST)
                .addParam(I.User.USER_NAME,"a")
                .addParam(I.PAGE_ID,pageId+"")
                .addParam(I.PAGE_SIZE,"10")
                .targetClass(UserBean[].class)
                .execute(new OkUtils.OnCompleteListener<UserBean[]>() {
                    @Override
                    public void onSuccess(UserBean[] result) {
                        contactAdapter.setMore(result!=null&&result.length>0);
                        Log.i("main", "ContactAdapter isMor=" + contactAdapter.isMore());
                        if(!contactAdapter.isMore()){
                            Log.i("main", "Action  " + action);
                            if(action==I2.PULL_UP){
                                contactAdapter.setFooter("没有更多数据");
                                Log.i("main", "ContactAdapter Footer=" + contactAdapter.getFooter());
                            }
                            return;
                        }
                        ArrayList<UserBean> contactlist = okUtils.array2List(result);
                        contactAdapter.setFooter("加载更多数据");
                        switch(action){
                            case I2.ACTION_DOWNLOAD:
                                contactAdapter.initContact(contactlist);
                                break;
                            case I2.PULL_DOWN:
                                msrlContactList.setRefreshing(false);
                                mtvPullDown.setVisibility(View.GONE);
                                contactAdapter.initContact(contactlist);
                                break;
                            case I2.PULL_UP:
                                contactAdapter.addContact(contactlist);
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
        contactList = new ArrayList<>();
        mrvContactItem = (RecyclerView) findViewById(R.id.rvContactItem);
        contactAdapter = new ContactAdapter(this, contactList);
        mrvContactItem.setAdapter(contactAdapter);

        linearLayoutManager = new LinearLayoutManager(this);
        mrvContactItem.setLayoutManager(linearLayoutManager);

        msrlContactList = (SwipeRefreshLayout) findViewById(R.id.srlContactList);
        mtvPullDown = (TextView) findViewById(R.id.tvPullDown);

    }
}
