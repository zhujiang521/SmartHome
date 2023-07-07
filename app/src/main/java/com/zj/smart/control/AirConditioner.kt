package com.zj.smart.control

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val TAG = "AirConditioner"

@Composable
fun AirConditioner() {
    val context = LocalContext.current
    // 空调开关
    val airSwitch = rememberSaveable { mutableStateOf(false) }
    // 温度
    val temperature = rememberSaveable { mutableStateOf(23) }
    // 类型，制冷还是制热
    val airType = rememberSaveable { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = if (airType.value) "Tip: 为你的夏日带去清凉！" else "Tip: 为你的冬日带来温暖！",
            fontSize = 15.sp,
            modifier = Modifier.padding(bottom = 20.dp),
            color = MaterialTheme.colorScheme.onBackground
        )

        // 模拟空调样式
        Conditioner(airSwitch, airType, temperature)

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.padding(10.dp)) {

            Button(onClick = { airType.value = true }, shape = RoundedCornerShape(15.dp)) {
                Text(text = "冷风")
            }

            Spacer(modifier = Modifier.width(10.dp))

            Switch(checked = airSwitch.value, onCheckedChange = {
                airSwitch.value = !airSwitch.value
            })

            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = { airType.value = false }, shape = RoundedCornerShape(15.dp)) {
                Text(text = "热风")
            }

        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            if (temperature.value >= 31) {
                Toast.makeText(context, "温度不能再高了！！！", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "AirConditioner: 太热了")
                return@Button
            }
            temperature.value = temperature.value + 1
        }, shape = RoundedCornerShape(15.dp)) {
            Text(text = "+")
        }

        Spacer(modifier = Modifier.height(10.dp))


        Button(onClick = {
            if (temperature.value <= 16) {
                Toast.makeText(context, "温度不能再低了！！！", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "AirConditioner: 太冷了")
                return@Button
            }
            temperature.value = temperature.value - 1
        }, shape = RoundedCornerShape(15.dp)) {
            Text(text = "-")
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun Conditioner(
    airSwitch: MutableState<Boolean>,
    airType: MutableState<Boolean>,
    temperature: MutableState<Int>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 20.dp)
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(
            topStart = 18f,
            topEnd = 18f,
            bottomStart = 36f,
            bottomEnd = 36f
        )
    ) {
        Column {

            Divider(
                modifier = Modifier.padding(top = 6.dp),
                thickness = 0.4.dp,
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(6.dp)
            ) {
                Image(
                    painter = painterResource(id = com.zj.smart.R.mipmap.logo),
                    contentDescription = "",
                )

                if (airSwitch.value) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.End,
                    ) {
                        Image(
                            painter = painterResource(id = if (airType.value) com.zj.smart.R.drawable.ic_snow else com.zj.smart.R.drawable.ic_sun),
                            contentDescription = "",
                            modifier = Modifier.size(25.dp)
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = "${temperature.value}°C",
                            fontSize = 20.sp,
                            fontFamily = FontFamily(
                                Font(
                                    com.zj.smart.R.font.lcd,
                                    FontWeight.SemiBold
                                )
                            )
                        )
                    }
                }

            }
        }
    }
}

@Preview
@Composable
private fun Conditioner() {
    // 空调开关
    val airSwitch = rememberSaveable { mutableStateOf(true) }
    // 温度
    val temperature = rememberSaveable { mutableStateOf(23) }
    // 类型，制冷还是制热
    val airType = rememberSaveable { mutableStateOf(true) }
    Conditioner(airSwitch = airSwitch, airType = airType, temperature = temperature)
}