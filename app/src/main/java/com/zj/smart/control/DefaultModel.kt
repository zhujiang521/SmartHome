package com.zj.smart.control

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zj.smart.utils.StaggeredGridData

@Composable
fun DefaultModel(staggeredGridData: StaggeredGridData) {
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
}
