package com.taufik.pokemonx.utils

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.taufik.pokemonx.R
import java.util.Locale

fun ImageView.loadImage(
    url: String
) {
    Glide.with(this)
        .load(url)
        .placeholder(R.color.primary_700)
        .into(this)
}

fun String.replaceFirstChar(): String {
    return replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
}

fun getPokemonImage(url: String): String {
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${getPokemonNumber(url)}.png"
}

fun getPokemonNumber(url: String): Int {
    val urlParts = url.split("/")
    return Integer.parseInt(urlParts[urlParts.size - 2])
}

fun showErrorLog(tag: String, message: String?) {
    Log.e(tag, "Error message: $message")
}