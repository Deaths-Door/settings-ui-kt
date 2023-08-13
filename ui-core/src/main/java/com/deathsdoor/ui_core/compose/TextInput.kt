package com.deathsdoor.ui_core.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deathsdoor.ui_core.R
import com.deathsdoor.ui_core.extras.functinos.extensions.ImageLoader
import com.deathsdoor.ui_core.public.PreferenceExtensions.sharedPreference

@Composable
internal inline fun TextInput(
    preferenceName:String,
    key:String,
    
    title: String,
    description: String){
    
    Row(modifier = Modifier
        .padding(15.dp)
        .wrapContentHeight()
    ) {
        Column(modifier = Modifier
            .wrapContentHeight()
            .weight(2f)) {
            Text(
                text = title,
                style = TextStyle(fontSize = 18.sp),
                modifier = Modifier.padding(7.5.dp)
            )
            Text(
                text = description,
                style = TextStyle(fontSize = 12.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 10.dp),
            )
        }
        val preference = LocalContext.current.sharedPreference(preferenceName)
        Image(
            painter = ImageLoader.image(drawable = R.drawable.ic_edit),
            contentDescription = "Edit",
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
                .padding(PaddingValues(end = 20.dp))
                .align(Alignment.CenterVertically)
        )
    }
}