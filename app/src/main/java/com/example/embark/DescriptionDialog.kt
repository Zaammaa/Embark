package com.example.embark

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.DialogFragment
import com.example.embark.Challenges.Challenge
import android.content.Context.LAYOUT_INFLATER_SERVICE
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.getSystemService

class DescriptionDialog {

    fun create(challenge: Challenge, context: Context){

        val inflater = LayoutInflater.from(context)
        val popupView: View = inflater.inflate(R.layout.fragment_description_dialog, null)

        popupView.findViewById<TextView>(R.id.description).text = challenge.displayFullDescription()
        popupView.findViewById<TextView>(R.id.difficulty).text = "Difficulty Rating: " + challenge.challengeDifficulty.toString()

        // create the popup window
        val width = LinearLayout.LayoutParams.WRAP_CONTENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT
        val focusable = true // lets taps outside the popup also dismiss it

        val popupWindow = PopupWindow(popupView, width, height, focusable)

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0)

        // dismiss the popup window when touched
        popupView.setOnTouchListener { v, event ->
            popupWindow.dismiss()
            true
        }
    }
}