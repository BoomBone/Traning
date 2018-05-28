package cn.ucai.contact5;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.TextView;

import cn.ucai.contact5.fragments.BoutiqueFragment;
import cn.ucai.contact5.fragments.CartFragment;
import cn.ucai.contact5.fragments.CategoryFragment;
import cn.ucai.contact5.fragments.ContactFragment;
import cn.ucai.contact5.fragments.NewGoodsFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager mvpGoods;
    Fragment[] mFragments;
    GoodsAdapter mAdapter;
    TextView mNewgoods, mBoutique, mCategory, mCart, mConnect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        initView();
        setListener();
    }

    private void setListener() {
        mNewgoods.setOnClickListener(this);
        mBoutique.setOnClickListener(this);
        mCategory.setOnClickListener(this);
        mCart.setOnClickListener(this);
        mConnect.setOnClickListener(this);

        setOnPageChangeListener();

    }

    private void setOnPageChangeListener() {
        mvpGoods.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initDrawable();
                switch (position) {
                    case 0:
                        setDrawable(mNewgoods, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_new_goods_selected);
                        startRotate(mNewgoods);
                        break;
                    case 1:
                        setDrawable(mBoutique, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_boutique_selected);
                        startRotate(mBoutique);
                        break;
                    case 2:
                        setDrawable(mCategory, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_category_selected);
                        startRotate(mCategory);
                        break;
                    case 3:
                        setDrawable(mCart, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_cart_selected);
                        startRotate(mCart);
                        break;
                    case 4:
                        setDrawable(mConnect, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_contact_selected);
                        startRotate(mConnect);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        mvpGoods = (ViewPager) findViewById(R.id.fragments);
        mAdapter = new GoodsAdapter(getSupportFragmentManager(), mFragments);
        mvpGoods.setAdapter(mAdapter);

        mNewgoods = (TextView) findViewById(R.id.tvNewgoods);
        mBoutique = (TextView) findViewById(R.id.tvBoutique);
        mCategory = (TextView) findViewById(R.id.tvCategory);
        mCart = (TextView) findViewById(R.id.tvCart);
        mConnect = (TextView) findViewById(R.id.tvContact);

        setDrawable(mNewgoods, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_new_goods_selected);
    }

    public void startRotate(View v) {
        ViewPropertyAnimator animate = v.animate();
        float y = 360 - v.getRotationY();
        animate.rotationY(y)
                .setDuration(1000)
                .start();
    }

    private void initFragment() {
        mFragments = new Fragment[5];
        mFragments[0] = new NewGoodsFragment();
        mFragments[1] = new BoutiqueFragment();
        mFragments[2] = new CategoryFragment();
        mFragments[3] = new CartFragment();
        mFragments[4] = new ContactFragment();
    }

    @Override
    public void onClick(View v) {
        initDrawable();
        switch (v.getId()) {
            case R.id.tvNewgoods:
                mvpGoods.setCurrentItem(0);
                setDrawable(mNewgoods, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_new_goods_selected);
                startRotate(v);
                break;
            case R.id.tvBoutique:
                mvpGoods.setCurrentItem(1);
                setDrawable(mBoutique, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_boutique_selected);
                startRotate(v);
                break;
            case R.id.tvCategory:
                mvpGoods.setCurrentItem(2);
                setDrawable(mCategory, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_category_selected);
                startRotate(v);
                break;
            case R.id.tvCart:
                mvpGoods.setCurrentItem(3);
                setDrawable(mCart, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_cart_selected);
                startRotate(v);
                break;
            case R.id.tvContact:
                mvpGoods.setCurrentItem(4);
                setDrawable(mConnect, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_contact_selected);
                startRotate(v);
                break;
        }

    }

    public void initDrawable() {
        setDrawable(mNewgoods, Color.GRAY, R.drawable.menu_item_new_goods_normal);
        setDrawable(mBoutique, Color.GRAY, R.drawable.menu_item_boutique_normal);
        setDrawable(mCategory, Color.GRAY, R.drawable.menu_item_category_normal);
        setDrawable(mCart, Color.GRAY, R.drawable.menu_item_cart_normal);
        setDrawable(mConnect, Color.GRAY, R.drawable.menu_item_contact_normal);
    }

    public void setDrawable(TextView tv, int color, int drawable) {
        tv.setTextColor(color);
        Drawable db = ContextCompat.getDrawable(this, drawable);
        db.setBounds(0, 0, db.getMinimumWidth(), db.getMinimumHeight());
        tv.setCompoundDrawables(null, db, null, null);
    }

    class GoodsAdapter extends FragmentPagerAdapter {
        Fragment[] fragments;

        public GoodsAdapter(FragmentManager fm, Fragment[] fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }
}
