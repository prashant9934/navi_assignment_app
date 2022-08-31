package com.example.naviassignmentapp.extentions

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.show(imageUrl: String?) {
    Picasso.get().load(imageUrl).into(this)
}