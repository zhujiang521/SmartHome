@file:OptIn(ExperimentalPermissionsApi::class)

package com.zj.smart

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.zj.smart.view.ShowDialog

@Composable
fun FeatureThatRequiresLocationPermissions(hasPermission: () -> Unit) {
    val context = LocalContext.current
    val alertDialog = rememberSaveable { mutableStateOf(false) }
    // 权限状态
    val cameraPermissionState = rememberPermissionState(
        permission = android.Manifest.permission.CAMERA
    )

    when (cameraPermissionState.status) {
        PermissionStatus.Granted -> {//已授权
            hasPermission()
        }

        is PermissionStatus.Denied -> {
            Column {
                if ((cameraPermissionState.status as PermissionStatus.Denied).shouldShowRationale) {
                    //如果用户拒绝了该权限但可以显示理由，那么请温和地解释为什么应用程序需要此权限(拒绝权限)
                    alertDialog.value = true
                    ShowDialog(
                        alertDialog = alertDialog,
                        title = stringResource(id = R.string.permission_title),
                        content = stringResource(id = R.string.permission_content),
                        cancelString = stringResource(id = R.string.permission_cancel),
                        confirmString = stringResource(id = R.string.permission_sure)
                    ) {
                        context.startSettingAppPermission()
                    }
                } else {
                    LaunchedEffect(Unit) {
                        cameraPermissionState.launchPermissionRequest()
                    }
                }
            }
        }
    }
}

/**
 * 跳转到设置权限页面
 * 联想手机专属
 */
fun Context.startSettingAppPermission() {
    try {
        val intent = Intent("com.zui.safecenter.permissionmanager.AppPermission")
        intent.putExtra("title", getString(R.string.app_name))
        intent.putExtra("package", packageName)
        startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
        // 其他安卓手机跳转到设置权限页面
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:$packageName")
        startActivity(intent)
    }
}
