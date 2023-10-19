package com.taufik.pokemonx.utils

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.taufik.pokemonx.R

fun ImageView.loadImage(
    url: String
) {
    Glide.with(this)
        .load(url)
        .placeholder(R.color.primary_700)
        .into(this)
}

fun getPokemonNumber(url: String): Int {
    val urlParts = url.split("/")
    return Integer.parseInt(urlParts[urlParts.size - 2])
}

fun showErrorLog(tag: String, message: String?) {
    Log.e(tag, "Error message: $message")
}