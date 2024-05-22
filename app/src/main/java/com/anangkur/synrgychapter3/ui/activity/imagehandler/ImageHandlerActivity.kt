package com.anangkur.synrgychapter3.ui.activity.imagehandler

import android.Manifest
import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.load
import com.anangkur.synrgychapter3.databinding.ActivityImageHandlerBinding
import java.io.File

class ImageHandlerActivity : AppCompatActivity() {

    private val binding by lazy { ActivityImageHandlerBinding.inflate(layoutInflater) }

    private lateinit var uri: Uri

    private val permissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions(),
        ::handlePermissionResult,
    )

    private val galleryResult = registerForActivityResult(
        ActivityResultContracts.GetContent(),
        ::handleGalleryResult,
    )

    private val cameraResult = registerForActivityResult(
        ActivityResultContracts.TakePicture(),
        ::handleCameraResult,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.btnChoose.setOnClickListener {
            if (isWriteExternalStorageGranted() && isReadExternalStorageGranted() && isCameraGranted()) {
                chooseImageDialog()
            } else {
                askPermission()
            }
        }
    }

    private fun handlePermissionResult(result: Map<String, Boolean>) {
        if (result.containsValue(false)) {
            Toast.makeText(this, "permission ditolak", Toast.LENGTH_SHORT).show()
            onBackPressed()
        } else {
            Toast.makeText(this, "permission diterima", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleGalleryResult(result: Uri?) {
        binding.ivImage.load(result)
    }

    private fun handleCameraResult(result: Boolean) {
        if (result) {
            binding.ivImage.load(uri)
        }
    }

    private fun askPermission() {
        permissionRequest.launch(
            arrayOf(
                WRITE_EXTERNAL_STORAGE,
                READ_EXTERNAL_STORAGE,
                CAMERA,
            )
        )
    }

    private fun isWriteExternalStorageGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) == PERMISSION_GRANTED
    }

    private fun isReadExternalStorageGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) == PERMISSION_GRANTED
    }

    private fun isCameraGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this, CAMERA) == PERMISSION_GRANTED
    }

    private fun chooseImageDialog() {
        AlertDialog.Builder(this)
            .setMessage("Pilih Gambar")
            .setPositiveButton("Gallery") { _, _ -> openGallery() }
            .setNegativeButton("Camera") { _, _ -> openCamera() }
            .show()
    }

    private fun openGallery() {
        intent.type = "image/*"
        galleryResult.launch("image/*")
    }

    private fun openCamera() {
        val photoFile = File.createTempFile(
            "SYNRGY_",
            ".jpg",
            this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )

        uri = FileProvider.getUriForFile(
            this,
            "com.anangkur.synrgychaper3.provider",
            photoFile
        )

        cameraResult.launch(uri)
    }
}