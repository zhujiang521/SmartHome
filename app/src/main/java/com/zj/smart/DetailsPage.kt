@file:OptIn(ExperimentalMaterial3Api::class)

package com.zj.smart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zj.smart.control.AirConditioner
import com.zj.smart.utils.StaggeredGridData

@Composable
fun DetailsPage(staggeredGridData: StaggeredGridData, back: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            //标题
            title = {
                Text(
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentSize(Alignment.Center),
                    text = stringResource(id = R.string.details_name)
                )
            },
            //导航按钮，一般为返回按钮
            navigationIcon = {
                IconButton(onClick = { back() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            },
        )
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp), colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.inverseOnSurface,
                contentColor = MaterialTheme.colorScheme.inverseOnSurface,
            )
        ) {
            if (staggeredGridData.resId != R.drawable.air) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = staggeredGridData.resId),
                    contentDescription = stringResource(id = staggeredGridData.nameId)
                )

                Text(
                    text = stringResource(id = staggeredGridData.nameId),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(align = Alignment.Center)
                        .padding(15.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )
                Text(
                    text = stringResource(id = staggeredGridData.detailsId),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(align = Alignment.Center)
                        .padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 16.sp
                )
            } else {
                AirConditioner()
            }
        }
    }
}