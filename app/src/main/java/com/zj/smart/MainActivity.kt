package com.zj.smart

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import com.zj.smart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            Log.i(TAG, "isGranted: $isGranted")
            if (isGranted) ScanActivity.actionActivity(this)
        }

    private fun requestPermission() {
        when {
            isPermissionGranted(android.Manifest.permission.CAMERA) -> {
                // 当前拥有这个权限
                ScanActivity.actionActivity(this)
            }

            shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA) -> {
                // 告诉用户为啥要申请这个权限
                showPermissionsFailDialog(
                    this,
                    "获取权限",
                    "扫描设备需要获取相机权限",
                    "确定",
                    "取消"
                )
            }

            else -> {
                // 申请权限
                requestPermissionLauncher.launch(
                    android.Manifest.permission.CAMERA
                )
            }
        }
    }

    private fun initView() {
        val vrView = binding.vrView
        vrView.setEventListener(object : VrPanoramaEventListener() {})
        val options = VrPanoramaView.Options()
        vrView.setInfoButtonEnabled(false)// 设置隐藏最左边信息的按钮
        vrView.setStereoModeButtonEnabled(false)// 设置隐藏立体模型的按钮
        vrView.setFullscreenButtonEnabled(true)// 隐藏全屏模式按钮
        vrView.setTouchTrackingEnabled(true) //false 只能通过传感器旋转 true设置传感器旋转加上左右方向可以通过触摸滑动旋转
//        vrView.setPureTouchTracking(true) //设置手动上下左右滑动，不能通过传感器旋转

        options.inputType = VrPanoramaView.Options.TYPE_MONO
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.new_home)
        vrView.loadImageFromBitmap(bitmap, options)
        vrView.setOnLongClickListener {
            showDispose()
            false
        }
        vrView.setOnClickListener {
            Log.e(TAG, "initView: 111111")
            Toast.makeText(this, "大爷的", Toast.LENGTH_SHORT).show()
        }
        binding.vrScan.setOnClickListener {
            requestPermission()
        }
    }

    private fun showDispose() {
        Log.e(TAG, "initView: 222222")
        Toast.makeText(this, "哈哈哈哈", Toast.LENGTH_SHORT).show()
    }

}