package cn.ucai.contact.dao;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.ucai.contact.I;
import cn.ucai.contact.I2;
import cn.ucai.contact.MainActivity;
import cn.ucai.contact.adapter.ContactAdapter;
import cn.ucai.contact.bean.UserBean;
import cn.ucai.contact.utils.OkUtils;

/**
 * Created by Administrator on 2017/4/14.
 */

public class Dao {
    Context context;
    ContactAdapter contactAdapter;
    SwipeRefreshLayout mRefresh;
    TextView mtvRefresh;

    public Dao(Context context, ContactAdapter contactAdapter, SwipeRefreshLayout mRefresh, TextView mtvRefresh) {
        this.context = context;
        this.contactAdapter = contactAdapter;
        this.mRefresh = mRefresh;
        this.mtvRefresh = mtvRefresh;
    }

    public Dao(Context context, ContactAdapter contactAdapter) {
        this.context = context;
        this.contactAdapter = contactAdapter;
    }

    public void downloadContactList(int pageId, final int action) {

        final OkUtils<UserBean[]> utils = new OkUtils<>(context);
        utils.url(I.SERVER_URL)
                .addParam(I.KEY_REQUEST,I.REQUEST_DOWNLOAD_CONTACT_LIST)
                .addParam(I.User.USER_NAME,"a")
                .addParam(I.PAGE_ID,pageId+"")
                .addParam(I.PAGE_SIZE,"10")
                .targetClass(UserBean[].class)
                .execute(new OkUtils.OnCompleteListener<UserBean[]>() {
                    @Override
                    public void onSuccess(UserBean[] result) {
                        contactAdapter.setMore(result!=null&&result.length>0);
                        if(!contactAdapter.isMore()){
                            if(action==I2.ACTION_PULLUP){
                                contactAdapter.setFootText("没有更多数据");
                            }
                            return;
                        }
                        ArrayList<UserBean> contactList = utils.array2List(result);
                        contactAdapter.setFootText("加载更多数据");
                        switch(action){
                            case I2.ACTION_DOWLOAD:
                                contactAdapter.initContact(contactList);
                                break;
                            case I2.ACTION_PULLDOWN:
                                mRefresh.setRefreshing(false);
                                mtvRefresh.setVisibility(View.GONE);
                                contactAdapter.initContact(contactList);
                                break;
                            case I2.ACTION_PULLUP:
                                contactAdapter.addContactList(contactList);
                                break;

                        }


                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
