package com.zj.smart

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zj.smart.view.VrView


@Composable
fun MainPage(toScan: () -> Unit) {
    val showPermission = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.toolbar_horizontal_padding)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(id = R.string.home))
            Image(
                modifier = Modifier.clickable { showPermission.value = true },
                painter = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
                contentDescription = ""
            )
        }
        if (showPermission.value) {
            FeatureThatRequiresLocationPermissions {
                toScan()
            }
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