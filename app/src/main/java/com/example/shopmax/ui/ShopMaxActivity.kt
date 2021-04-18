package com.example.shopmax.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.shopmax.R
import com.example.shopmax.databinding.ActivityShopMaxBinding
import com.example.shopmax.utils.showError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_shop_max.*


@AndroidEntryPoint
class ShopMaxActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShopMaxBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shop_max)
        binding.lifecycleOwner = this

    }

    fun showError(e:String){
        coordinatorLayout.showError(e)
    }


}
