package com.zj.smart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import com.zj.smart.databinding.ActivityScanBinding

class ScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanBinding
    private var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backImage.setOnClickListener { finish() }
        startCamera()
    }

    private fun startCamera() {
        // 请求 CameraProvider
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        //检查 CameraProvider 可用性，验证它能否在视图创建后成功初始化
        cameraProviderFuture?.addListener({
            try {
                val cameraProvider: ProcessCameraProvider? = cameraProviderFuture?.get()
                bindPreview(cameraProvider)
            } catch (e: Exception) {
                e.printStackTrace()
                // No errors need to be handled for this Future.
                // This should never be reached.
            }
        }, ContextCompat.getMainExecutor(this))
        binding.previewView.setOnLongClickListener {
            Toast.makeText(this, "aaaaa", Toast.LENGTH_SHORT).show()
            true
        }
    }

    //选择相机并绑定生命周期和用例
    private fun bindPreview(cameraProvider: ProcessCameraProvider?) {
        val preview = Preview.Builder().build()
        val cameraSelector =
            CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
        preview.setSurfaceProvider(binding.previewView.surfaceProvider)
        cameraProvider?.bindToLifecycle((this as LifecycleOwner), cameraSelector, preview)
    }

    companion object {

        private const val TAG = "ScanActivity"

        fun actionActivity(context: Context?) {
            if (context == null) {
                Log.w(TAG, "actionActivity: context is null.")
                return
            }
            context.startActivity(Intent(context, ScanActivity::class.java))
        }
    }

}