package com.nathanjgaul.android.a30_days_of_kotlin_for_android_day_03

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val _videoCapture = 101
    private val _imageCapture = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @RequiresApi(Build.VERSION_CODES.ECLAIR)
    fun hasCamera(view: View){
        var toastMessage: String
        if( packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY) ) {
            toastMessage = "Camera Available"
        } else {
            toastMessage = "Camera Unavailable"
        }
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
    }

    fun captureVideo(view: View) {
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startActivityForResult(intent, _videoCapture)
    }

    fun captureImage(view: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, _imageCapture)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        val videoUri = data.data

        if (requestCode == _videoCapture || requestCode == _imageCapture) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Video saved to:\n"
                        + videoUri, Toast.LENGTH_LONG).show()
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Video recording cancelled.",
                        Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Failed to record video",
                        Toast.LENGTH_LONG).show()
            }
        }
    }
}
