package cn.ucai.colorball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView mtv1,mtv2,mtv3,mtv4,mtv5,mtv6,mtv7;
    Button mbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                mtv1.setText(""+(random.nextInt(33)+1));
                mtv2.setText(""+(random.nextInt(33)+1));
                mtv3.setText(""+(random.nextInt(33)+1));
                mtv4.setText(""+(random.nextInt(33)+1));
                mtv5.setText(""+(random.nextInt(33)+1));
                mtv6.setText(""+(random.nextInt(33)+1));
                mtv7.setText(""+(random.nextInt(16)+1));
            }
        });
    }

    private void initView() {
        mtv1= (TextView) findViewById(R.id.tv1);
        mtv2= (TextView) findViewById(R.id.tv2);
        mtv3= (TextView) findViewById(R.id.tv3);
        mtv4= (TextView) findViewById(R.id.tv4);
        mtv5= (TextView) findViewById(R.id.tv5);
        mtv6= (TextView) findViewById(R.id.tv6);
        mtv7= (TextView) findViewById(R.id.tv7);
        mbtn= (Button) findViewById(R.id.btn);
    }

}
