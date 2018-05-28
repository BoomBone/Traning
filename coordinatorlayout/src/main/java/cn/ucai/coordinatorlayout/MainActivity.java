package cn.ucai.coordinatorlayout;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRv;
    List<Item> mList;
    ItemAdapter adapter;
    LinearLayoutManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Item item = new Item();
            item.setId((int) (Math.random()*100+1));
            mList.add(item);
        }
        adapter = new ItemAdapter(MainActivity.this, mList);
        manager = new LinearLayoutManager(this);
        mRv.setAdapter(adapter);
        mRv.setLayoutManager(manager);
    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
    }
}
