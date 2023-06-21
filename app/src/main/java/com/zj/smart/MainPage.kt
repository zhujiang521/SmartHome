@file:OptIn(ExperimentalMaterial3Api::class)

package com.zj.smart

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zj.smart.view.FeatureThatRequiresLocationPermissions
import com.zj.smart.view.VrView


val staggeredGridDataMutableList = mutableListOf<StaggeredGridData>().apply {
    add(StaggeredGridData(R.string.air, R.drawable.air))
    add(StaggeredGridData(R.string.display, R.drawable.display))
    add(StaggeredGridData(R.string.projector, R.drawable.projector))
    add(StaggeredGridData(R.string.light, R.drawable.light))
    add(StaggeredGridData(R.string.lock, R.drawable.lock))
    add(StaggeredGridData(R.string.camera, R.drawable.camera))
    add(StaggeredGridData(R.string.scale, R.drawable.scale))
    add(StaggeredGridData(R.string.cleaner, R.drawable.cleaner))
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainPage(toScan: () -> Unit, toDetails: (StaggeredGridData) -> Unit) {
    val showPermission = rememberSaveable { mutableStateOf(false) }
    val isJump = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        TopAppBar(
            //标题
            title = {
                Text(text = stringResource(id = R.string.home))
            },
            //其它按钮
            actions = {
                IconButton(onClick = { showPermission.value = true }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            },
        )

        if (showPermission.value) {
            FeatureThatRequiresLocationPermissions {
                if (!isJump.value) {
                    toScan()
                    isJump.value = false
                }
            }
            showPermission.value = false
        }

        Card(modifier = Modifier.padding(horizontal = 16.dp)) {
            VrView()
        }

        //填充数据
        LazyVerticalStaggeredGrid(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 9.dp),
            columns = StaggeredGridCells.Fixed(2),
            content = {
                staggeredGridDataMutableList.forEachIndexed { _, staggeredGridData ->
                    item {
                        SmartCard(staggeredGridData, toDetails)
                    }
                }
            })

    }
}

data class StaggeredGridData(
    val nameId: Int, val resId: Int, val detailsId: Int = R.string.device_details
)

@Composable
private fun SmartCard(
    staggeredGridData: StaggeredGridData,
    toDetails: (StaggeredGridData) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                toDetails(staggeredGridData)
            }, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onTertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        )
    ) {

        Image(
            painter = painterResource(id = staggeredGridData.resId),
            contentDescription = stringResource(id = staggeredGridData.nameId)
        )

        Text(
            text = stringResource(id = staggeredGridData.nameId),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.Center)
                .padding(10.dp),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 15.sp
        )
    }
}