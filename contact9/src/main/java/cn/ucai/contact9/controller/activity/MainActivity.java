package cn.ucai.contact9.controller.activity;

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

import cn.ucai.contact9.R;
import cn.ucai.contact9.controller.fragments.ContactFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView mNewgoods,mBoutique,mCategory,mCart,mContact;

    ContactFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    private void setListener() {
        mNewgoods.setOnClickListener(this);
        mBoutique.setOnClickListener(this);
        mCategory.setOnClickListener(this);
        mCart.setOnClickListener(this);
        mContact.setOnClickListener(this);
    }

    private void initView() {
        mNewgoods = (TextView) findViewById(R.id.tvNewgoods);
        mBoutique = (TextView) findViewById(R.id.tvBoutique);
        mCategory = (TextView) findViewById(R.id.tvCategory);
        mCart = (TextView) findViewById(R.id.tvCart);
        mContact = (TextView) findViewById(R.id.tvContact);

        fragment = new ContactFragment();

    }
    public void startRotate(View v){
        float y = 360-v.getRotationY();
        ViewPropertyAnimator animate = v.animate();
        animate.setDuration(1000).rotationY(y).start();
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        initDrawable();
        switch (v.getId()){
            case R.id.tvNewgoods:
                setDrawable(mNewgoods,Color.rgb(0xff,0x66,0xff),R.drawable.menu_item_new_goods_selected);
                startRotate(v);
                break;
            case R.id.tvBoutique:
                setDrawable(mBoutique,Color.rgb(0xff,0x66,0xff),R.drawable.menu_item_boutique_selected);
                startRotate(v);
                break;
            case R.id.tvCategory:
                setDrawable(mCategory,Color.rgb(0xff,0x66,0xff),R.drawable.menu_item_category_selected);
                startRotate(v);
                break;
            case R.id.tvCart:
                setDrawable(mCart,Color.rgb(0xff,0x66,0xff),R.drawable.menu_item_cart_selected);
                startRotate(v);
                break;
            case R.id.tvContact:
                setDrawable(mContact,Color.rgb(0xff,0x66,0xff),R.drawable.menu_item_contact_selected);
                startRotate(v);
                ft.replace(R.id.frameContact, fragment).commit();
                break;
        }

    }
    public void initDrawable(){
        setDrawable(mNewgoods, Color.GRAY,R.drawable.menu_item_new_goods_normal);
        setDrawable(mBoutique, Color.GRAY,R.drawable.menu_item_boutique_normal);
        setDrawable(mCategory, Color.GRAY,R.drawable.menu_item_category_normal);
        setDrawable(mCart, Color.GRAY,R.drawable.menu_item_cart_normal);
        setDrawable(mContact, Color.GRAY,R.drawable.menu_item_contact_normal);

    }
    public void setDrawable(TextView tv,int color,int drawable){
        tv.setTextColor(color);
        Drawable top = ContextCompat.getDrawable(this, drawable);
        top.setBounds(0, 0, top.getMinimumWidth(), top.getMinimumHeight());
        tv.setCompoundDrawables(null,top,null,null);
    }
}
