@file:OptIn(ExperimentalMaterial3Api::class)

package com.zj.smart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.zj.smart.view.PreviewView

@Composable
fun ScanPage(back: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            //标题
            title = {
                Text(
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentSize(Alignment.Center),
                    text = stringResource(id = R.string.scan_name)
                )
            },
            //导航按钮，一般为返回按钮
            navigationIcon = {
                IconButton(onClick = { back() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            },
        )
        PreviewView()
    }
}