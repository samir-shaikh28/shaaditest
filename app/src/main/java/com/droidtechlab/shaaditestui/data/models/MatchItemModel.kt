package com.droidtechlab.shaaditestui.data.models

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableInt
import com.bumptech.glide.RequestManager
import com.droidtechlab.shaaditestui.R
import com.droidtechlab.shaaditestui.RequestHandlerInterface
import com.droidtechlab.shaaditestui.data.db.entities.LocationEntity
import com.droidtechlab.shaaditestui.data.db.entities.NameEntity
import com.droidtechlab.shaaditestui.data.db.entities.ShaadiMatchListEntity

class MatchItemModel(
    var matchItem: ShaadiMatchListEntity,
    var requestManager: RequestManager,
    var listener: RequestHandlerInterface
) :
    CustomModel() {

    /**
     *  to handle statues CTA and status label visibility
     *   -1 = Fresh Application, Show Button
     *   0 = Rejected
     *   1 = Accepted
     * */
    val applicationState = ObservableInt(matchItem.matchState)

    override fun getLayoutIdentifier(): Int {
        return R.layout.row_match_item
    }

    fun acceptRequest(v: View) {
        listener.acceptRequest(matchItem._id)
        applicationState.set(1)

    }

    fun declineRequest(v: View) {
        listener.declineRequest(matchItem._id)
        applicationState.set(0)
    }

    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(imageView: AppCompatImageView, model: MatchItemModel) {
            model.requestManager.load(model.matchItem.picture?.large).into(imageView)
        }

        @SuppressLint("SetTextI18n")
        @JvmStatic
        @BindingAdapter("setName")
        fun setName(txtView: AppCompatTextView, name: NameEntity) {
            name.apply {
                txtView.text = "$title. $first $last"
            }
        }

        @SuppressLint("SetTextI18n")
        @JvmStatic
        @BindingAdapter("setLocation")
        fun setLocation(txtView: AppCompatTextView, location: LocationEntity) {
            location.apply {
                txtView.text = "$city, $state, $country - $postcode"
            }
        }

        @JvmStatic
        @BindingAdapter("setStatusLabel")
        fun setStatusLabel(textView: AppCompatTextView, state: Int) {
            when (state) {
                -1 -> textView.visibility = View.GONE
                0 -> {
                    textView.visibility = View.VISIBLE
                    textView.text = "Rejected"
                    textView.setBackgroundResource(R.color.color_rejected)
                }
                1 -> {
                    textView.visibility = View.VISIBLE
                    textView.text = "Accepted"
                    textView.setBackgroundResource(R.color.color_accepted)
                }
            }
        }
    }
}