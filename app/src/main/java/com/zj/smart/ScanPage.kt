@file:OptIn(ExperimentalMaterial3Api::class)

package com.zj.smart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zj.smart.utils.StaggeredGridData
import com.zj.smart.utils.staggeredGridDataMutableList
import com.zj.smart.view.PreviewView

private val newData = StaggeredGridData(R.string.legion, R.drawable.legion_black)

@Composable
fun ScanPage(back: () -> Unit) {
    val showControls = rememberSaveable { mutableStateOf(false) }
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

        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = {
                    if (!staggeredGridDataMutableList.contains(newData)) {
                        staggeredGridDataMutableList.add(0, newData)
                    }
                    showControls.value = true
                })
            }) {
            PreviewView()
            if (showControls.value) {
                ModelControls(showControls)
            }
        }
    }
}

@Composable
private fun ModelControls(showControls: MutableState<Boolean>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 150.dp)
            .background(color = Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier.padding(bottom = 10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onTertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            )
        ) {
            Text(
                text = stringResource(id = R.string.legion),
                modifier = Modifier
                    .padding(5.dp),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
            )
        }

        Card(
            modifier = Modifier.padding(bottom = 10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onTertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            )
        ) {
            Text(
                text = stringResource(id = R.string.pet_introduction),
                modifier = Modifier
                    .padding(8.dp),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp
            )
        }

        Card(
            modifier = Modifier.padding(bottom = 10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onTertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            )
        ) {
            Text(
                text = stringResource(id = R.string.pet_content1),
                modifier = Modifier
                    .padding(8.dp),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp
            )
        }
        Card(
            modifier = Modifier.padding(bottom = 10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onTertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            )
        ) {
            Text(
                text = stringResource(id = R.string.pet_content2),
                modifier = Modifier
                    .padding(8.dp),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp
            )
        }
        Card(
            modifier = Modifier.padding(bottom = 10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onTertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            )
        ) {
            Text(
                text = stringResource(id = R.string.pet_content3),
                modifier = Modifier
                    .padding(8.dp),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp
            )
        }

        Volume()


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.Center)
                .padding(bottom = 10.dp),
            onClick = { showControls.value = false }) {
            Text(
                text = stringResource(id = com.google.vr.cardboard.R.string.cancel_button),
                fontSize = 13.sp
            )
        }
    }
}

@Composable
private fun Volume() {

    var volume by remember { mutableIntStateOf(4) }

    Row {
        Card(
            modifier = Modifier.padding(bottom = 10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onTertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            )
        ) {
            Text(
                text = "-",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        if (volume > 0) {
                            volume--
                        }
                    },
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp
            )
        }

        Text(
            text = "$volume",
            modifier = Modifier
                .padding(8.dp),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp
        )

        Card(
            modifier = Modifier.padding(bottom = 10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onTertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            )
        ) {
            Text(
                text = "+",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        if (volume < 10) {
                            volume++
                        }
                    },
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp
            )
        }
    }
}