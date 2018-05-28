package cn.ucai.contact7;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.TextView;

import cn.ucai.contact7.fragments.ContactFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView mtvNewGoods,mtvBoutique,mtvCartgory,mtvCart,mtvContact;
    ContactFragment mfragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    private void setListener() {
        mtvNewGoods.setOnClickListener(this);
        mtvBoutique.setOnClickListener(this);
        mtvCartgory.setOnClickListener(this);
        mtvCart.setOnClickListener(this);
        mtvContact.setOnClickListener(this);

    }

    private void initView() {
        mtvNewGoods = (TextView) findViewById(R.id.tvNewgoods);
        mtvBoutique = (TextView) findViewById(R.id.tvBoutique);
        mtvCartgory = (TextView) findViewById(R.id.tvCartgory);
        mtvCart = (TextView) findViewById(R.id.tvCart);
        mtvContact = (TextView) findViewById(R.id.tvContact);

        mfragment=new ContactFragment();
    }
    public void startRotate(View v){
        ViewPropertyAnimator animate = v.animate();
        float y = 360-v.getRotationY();
        animate.setDuration(1000)
                .rotationY(y)
                .start();
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        initDrawable();
        switch(v.getId()){
            case R.id.tvNewgoods:
                setDrawable(mtvNewGoods, Color.rgb(0xff,0x66,0xff),R.drawable.menu_item_new_goods_selected);
                startRotate(v);
                break;
            case R.id.tvBoutique:
                setDrawable(mtvBoutique, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_boutique_selected);
                startRotate(v);

                break;
            case R.id.tvCartgory:
                setDrawable(mtvCartgory, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_category_selected);
                startRotate(v);

                break;

            case R.id.tvCart:
                setDrawable(mtvCart, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_cart_selected);
                startRotate(v);
                break;
            case R.id.tvContact:
                setDrawable(mtvContact, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_contact_selected);
                startRotate(v);
                ft.replace(R.id.frameGoods, mfragment).commit();
                break;
        }

    }
    public void initDrawable(){
        setDrawable(mtvNewGoods, Color.GRAY,R.drawable.menu_item_new_goods_normal);
        setDrawable(mtvBoutique, Color.GRAY,R.drawable.menu_item_boutique_normal);
        setDrawable(mtvCartgory, Color.GRAY,R.drawable.menu_item_category_normal);
        setDrawable(mtvCart, Color.GRAY,R.drawable.menu_item_cart_normal);
        setDrawable(mtvContact, Color.GRAY,R.drawable.menu_item_contact_normal);

    }

    public void setDrawable(TextView tv,int color,int drawable){
        tv.setTextColor(color);
        Drawable top = ContextCompat.getDrawable(this, drawable);
        top.setBounds(0, 0, top.getMinimumWidth(), top.getMinimumHeight());
        tv.setCompoundDrawables(null,top,null,null);

    }
}
