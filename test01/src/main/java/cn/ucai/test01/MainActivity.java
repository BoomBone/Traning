package cn.ucai.test01;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.ucai.test01.adapter.ItemAdapter;
import cn.ucai.test01.bean.Item;

public class MainActivity extends AppCompatActivity {
    ArrayList<Item> itemList;
    RecyclerView mRv;
    ItemAdapter adapter;
    LinearLayoutManager llm;
    TextView mTotal;
    Button mBtn;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDate();

    }


    private void initView() {
        mTotal = (TextView) findViewById(R.id.tv_total);
        mRv = (RecyclerView) findViewById(R.id.rv_item);
        mBtn = (Button) findViewById(R.id.btn_update);

        llm = new LinearLayoutManager(this);
        mRv.setLayoutManager(llm);
    }

    private void initDate() {
        itemList = new ArrayList<>();
        Random ran = new Random();
        int a;
        for (int i = 0; i < 50; i++) {
            a = ran.nextInt(1000) + 1;
            Item item = new Item(String.valueOf(a));
            itemList.add(item);
        }
        updateUI();
    }

    private void updateUI() {
        if (adapter == null) {
            adapter = new ItemAdapter(itemList, MainActivity.this);
            adapter.setListener(listener);
            mRv.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final int position = (int) v.getTag();
            if (itemList.get(position).isSelect()) {
                count--;
                mTotal.setText("total:" + count);
                itemList.get(position).setSelect(false);
                adapter.notifyItemChanged(position);
            } else {
                count++;
                mTotal.setText("total:" + count);
                itemList.get(position).setSelect(true);
                adapter.notifyItemChanged(position);
            }
        }
    };

    public void clear(View view) {
        if (count != 0) {
            for (int i = 0; i < 50; i++) {
                if (itemList.get(i).isSelect()) {
                    itemList.get(i).setSelect(false);
                }
            }
            count = 0;
            mTotal.setText("total:" + count);
            adapter.notifyDataSetChanged();
        }
    }
}
