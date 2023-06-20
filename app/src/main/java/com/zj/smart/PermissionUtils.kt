package com.zj.smart

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat

private const val TAG = "PermissionUtils"

fun Context?.isPermissionsGranted(permissions: Array<String>): Boolean {
    for (permission in permissions) {
        if (!isPermissionGranted(permission)) {
            return false
        }
    }
    return true
}

fun Context?.isPermissionGranted(permission: String): Boolean {
    if (this == null) {
        Log.w(TAG, "context is null.")
        return false
    }
    return ActivityCompat.checkSelfPermission(
        this, permission
    ) == PackageManager.PERMISSION_GRANTED
}


/**
 * 当获取权限失败的时候弹出
 *
 * @param context /
 * @param title 标题
 * @param message 内容
 * @param onNegativeClickListener 取消的点击事件
 */
fun showPermissionsFailDialog(
    context: Context,
    title: String,
    message: String,
    positiveText: String,// R.string.net2confirm
    negativeText: String, // R.string.cancel
    onNegativeClickListener: ((DialogInterface) -> Unit)? = null,
    onPositiveClickListener: ((DialogInterface) -> Unit)? = null
): AlertDialog {
    //创建对话框创建器
    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveText) { dialog, _ ->
            //这里用来跳到手机设置页，方便用户开启权限
            if (onPositiveClickListener != null) {
                onPositiveClickListener(dialog)
            } else {
                startSettingAppPermission(context)
            }
        }
        .setNegativeButton(negativeText) { dialog, _ ->
            // 暂时不做操作
            onNegativeClickListener?.let { it(dialog) }
        }
    return builder.create().apply {
        setOnKeyListener { _: DialogInterface?, keyCode: Int, _: KeyEvent? -> keyCode == KeyEvent.KEYCODE_BACK }
        show()
    }
}

fun startSettingAppPermission(context: Context) {
    try {
        val intent = Intent("com.zui.safecenter.permissionmanager.AppPermission")
        intent.putExtra("title", context.getString(R.string.app_name))
        intent.putExtra("package", context.packageName)
        context.startActivity(intent)
    } catch (e: Exception) {
        Log.e(TAG, "Exception:$e")
        startSettingAppDetails(context)
    }
}

fun startSettingAppDetails(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.data = Uri.parse("package:" + context.packageName)
    context.startActivity(intent)
}