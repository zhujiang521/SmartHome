@file:OptIn(ExperimentalMaterial3Api::class)

package com.zj.smart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zj.smart.view.FeatureThatRequiresLocationPermissions
import com.zj.smart.view.VrView


@Composable
fun MainPage(toScan: () -> Unit) {
    val showPermission = rememberSaveable { mutableStateOf(false) }

    val isJump = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
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
        VrView()
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            // Add 5 items
            items(5) { index ->
                Card(
                    modifier = Modifier.padding(start = 15.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    )
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.smart),
                        contentDescription = ""
                    )

                    Text(
                        text = "Item: $index",
                        modifier = Modifier.padding(10.dp),
                        color = Color.Black
                    )
                }
            }
        }
    }
}