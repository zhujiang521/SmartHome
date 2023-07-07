package com.zj.smart.control

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zj.smart.R

@Composable
fun TvControl() {
    Spacer(modifier = Modifier.padding(10.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Image(
            painter = painterResource(id = R.drawable.tv_menu),
            contentDescription = "menu",
            modifier = Modifier
                .size(45.dp)
                .background(
                    color = MaterialTheme.colorScheme.onSecondary,
                    shape = CircleShape
                )
                .padding(10.dp)
                .clickable { }
        )

        Image(
            painter = painterResource(id = R.drawable.tv_home),
            contentDescription = "home",
            modifier = Modifier
                .size(45.dp)
                .background(
                    color = MaterialTheme.colorScheme.onSecondary,
                    shape = CircleShape
                )
                .padding(10.dp)
                .clickable { }
        )

        Image(
            painter = painterResource(id = R.drawable.tv_close),
            contentDescription = "close",
            modifier = Modifier
                .size(45.dp)
                .background(
                    color = MaterialTheme.colorScheme.onSecondary,
                    shape = CircleShape
                )
                .padding(10.dp)
                .clickable { }
        )
    }

    Spacer(modifier = Modifier.padding(10.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.Center)
            .size(150.dp)
            .padding(5.dp)
            .background(
                color = MaterialTheme.colorScheme.inversePrimary,
                shape = CircleShape
            )
            .border(BorderStroke(1.dp, Color.White), shape = CircleShape)
    ) {
        Text(
            text = "ok", modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.onSecondary,
                    shape = CircleShape
                )
                .size(40.dp)
                .align(Alignment.Center)
                .wrapContentSize(Alignment.Center)
                .clickable { },
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp
        )

        Text(
            text = "^", modifier = Modifier
                .size(35.dp)
                .padding(5.dp)
                .background(
                    color = MaterialTheme.colorScheme.onSecondary,
                    shape = CircleShape
                )
                .align(Alignment.TopCenter)
                .wrapContentSize(Alignment.Center)
                .clickable { },
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp
        )

        Text(
            text = "v", modifier = Modifier
                .size(35.dp)
                .padding(5.dp)
                .background(
                    color = MaterialTheme.colorScheme.onSecondary,
                    shape = CircleShape
                )
                .align(Alignment.BottomCenter)
                .wrapContentSize(Alignment.Center)
                .clickable { },
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp
        )

        Text(
            text = "<", modifier = Modifier
                .size(35.dp)
                .padding(5.dp)
                .background(
                    color = MaterialTheme.colorScheme.onSecondary,
                    shape = CircleShape
                )
                .align(Alignment.CenterStart)
                .wrapContentSize(Alignment.Center)
                .clickable { },
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp
        )

        Text(
            text = ">", modifier = Modifier
                .size(35.dp)
                .padding(5.dp)
                .background(
                    color = MaterialTheme.colorScheme.onSecondary,
                    shape = CircleShape
                )
                .align(Alignment.CenterEnd)
                .wrapContentSize(Alignment.Center)
                .clickable { },
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp
        )

    }

    Spacer(modifier = Modifier.padding(10.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = R.drawable.tv_add),
            contentDescription = "add",
            modifier = Modifier
                .size(45.dp)
                .background(
                    color = MaterialTheme.colorScheme.onSecondary,
                    shape = CircleShape
                )
                .padding(10.dp)
                .clickable { }
        )

        Image(
            painter = painterResource(id = R.drawable.tv_switch),
            contentDescription = "home",
            modifier = Modifier
                .size(65.dp)
                .background(
                    color = MaterialTheme.colorScheme.onSecondary,
                    shape = CircleShape
                )
                .padding(15.dp)
                .clickable { }
        )

        Image(
            painter = painterResource(id = R.drawable.tv_subtract),
            contentDescription = "subtract",
            modifier = Modifier
                .size(45.dp)
                .background(
                    color = MaterialTheme.colorScheme.onSecondary,
                    shape = CircleShape
                )
                .padding(10.dp)
                .clickable { }
        )
    }

    Spacer(modifier = Modifier.padding(10.dp))

}