package cn.ucai.test_drawlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        double[] b={3,4,5,6,3,2,1,};
        double[] c = m(b);
        for(int i=0;i<c.length;i++){
            Log.e("main", String.valueOf(c[i]));
        }

    }
    public static double[] m(double[] a){
        double[] b = new double[a.length];
        for(int i=0;i<a.length;i++){
            b[i]=a[i]/a[0];
        }
        return b;
    }

}
