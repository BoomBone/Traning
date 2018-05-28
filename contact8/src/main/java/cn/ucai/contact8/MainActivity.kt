package cn.ucai.contact8

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewPropertyAnimator
import android.widget.TextView

import cn.ucai.contact8.fragments.ContactFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.onClick

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListener()
    }

    private fun setListener() {
        tvNewGoods.onClick {
            startRotate(tvNewGoods)
            setDrawable(tvNewGoods, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_new_goods_selected)
        }
        tvBoutique.onClick { this }
        tvCategory.onClick { this }
        tvCart.onClick { this }
        tvContact.onClick { this }
    }

    fun startRotate(v: View) {
        val y = 360 - v.rotationY
        val animate = v.animate()
        animate.setDuration(1000).rotationY(y).start()

    }

    override fun onClick(v: View) {
        val manager = supportFragmentManager
        val ft = manager.beginTransaction()
        initDrawable()
        when (v.id) {
            R.id.tvBoutique -> {
                startRotate(v)
                setDrawable(tvBoutique, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_boutique_selected)
            }
            R.id.tvCategory -> {
                startRotate(v)
                setDrawable(tvCategory, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_category_selected)
            }
            R.id.tvCart -> {
                startRotate(v)
                setDrawable(tvCart, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_cart_selected)
            }
            R.id.tvContact -> {
                startRotate(v)
                setDrawable(tvContact, Color.rgb(0xff, 0x66, 0xff), R.drawable.menu_item_contact_selected)
                ft.replace(R.id.frame_contact, ContactFragment()).commit()
            }
        }

    }

    fun initDrawable() {
        setDrawable(tvNewGoods, Color.GRAY, R.drawable.menu_item_new_goods_normal)
        setDrawable(tvBoutique, Color.GRAY, R.drawable.menu_item_boutique_normal)
        setDrawable(tvCategory, Color.GRAY, R.drawable.menu_item_category_normal)
        setDrawable(tvCart, Color.GRAY, R.drawable.menu_item_cart_normal)
        setDrawable(tvContact, Color.GRAY, R.drawable.menu_item_contact_normal)

    }

    fun setDrawable(tv: TextView, color: Int, drawable: Int) {
        tv.setTextColor(color)
        val top = ContextCompat.getDrawable(this, drawable)
        top.setBounds(0, 0, top.minimumWidth, top.minimumHeight)
        tv.setCompoundDrawables(null, top, null, null)
    }
}
