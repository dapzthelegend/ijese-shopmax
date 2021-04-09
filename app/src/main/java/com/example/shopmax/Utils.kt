package com.example.shopmax

import androidx.databinding.BindingAdapter
import com.google.android.material.imageview.ShapeableImageView

object ImageBindings{

    const val STATUS = "pending"



    @BindingAdapter("status")
    @JvmStatic
    fun ShapeableImageView.setImage(status: String){
      if(status == STATUS){
          this.setImageResource(R.drawable.ic_baseline_pending_24)
          this.setBackgroundColor(resources.getColor(R.color.colorScooter))
      }else{
          this.setImageResource(R.drawable.ic_baseline_done_24)
          this.setBackgroundColor(resources.getColor(R.color.colorSpringGreen))
      }
    }
}