package com.sankalp.captureimageusingcamera

import android.Manifest.permission.CAMERA
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker.checkPermission
import java.security.AccessController.checkPermission
import java.security.Permission
import java.util.jar.Manifest

private const val REQUEST_CODE = 10
class MainActivity : AppCompatActivity() {

    lateinit var  imgView  : ImageView
    lateinit var  btnCapture : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgView = findViewById(R.id.imageView)
        btnCapture = findViewById(R.id.buttonCapture)

     btnCapture.setOnClickListener {
         val clickImageIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
         if (clickImageIntent.resolveActivity(this.packageManager)!=null){
             startActivityForResult(clickImageIntent,10)
         } else{
             Toast.makeText(this,"Unable to open Camera",Toast.LENGTH_SHORT).show()
         }
     }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 10){
            val pic = data?.getParcelableExtra<Bitmap>("data")
            imgView.setImageBitmap(pic)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 101 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            btnCapture.isEnabled = true
    }
}