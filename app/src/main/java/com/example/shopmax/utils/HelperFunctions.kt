@file:Suppress("DEPRECATION")

package com.example.shopmax.utils


import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopmax.R
import com.google.android.material.snackbar.Snackbar

fun String.convertToTitleCaseIteratingChars(): String? {
    if (this.isEmpty()) {
        return this
    }
    val converted = StringBuilder()
    var convertNext = true
    for (ch in this.toCharArray()) {
        var s = ch
        when {
            Character.isSpaceChar(ch) -> {
                convertNext = true
            }
            convertNext -> {
                s = Character.toTitleCase(ch)
                convertNext = false
            }
            else -> {
                s = Character.toLowerCase(ch)
            }
        }
        converted.append(s)
    }
    return converted.toString()
}

fun setMediatorSources(mediator: MediatorLiveData<Boolean>, list:List<MutableLiveData<String>>, validation: () -> Unit ){
    for(item in list) mediator.addSource(item){validation()}
}

fun CoordinatorLayout.showError(e: String) {

    val snackbar = Snackbar
        .make(this, e, Snackbar.LENGTH_LONG)
    val sbView = snackbar.view
    val textView = sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
    textView.setTextColor(resources.getColor(R.color.colorWhite))
    snackbar.show()


}
