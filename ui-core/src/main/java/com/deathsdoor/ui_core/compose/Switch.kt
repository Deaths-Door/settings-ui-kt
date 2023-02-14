package com.deathsdoor.ui_core.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deathsdoor.ui_core.public.PreferenceExtensions.changePreference
import com.deathsdoor.ui_core.public.PreferenceExtensions.sharedPreference
/**
 *       <attr name="switchChecked" format="boolean"/>
 *       <attr name="switchOnColor" format="string|color"/>
 *       <attr name="switchOffColor" format="string|color" />
 *         <attr name="showWarnWhenSwitchToggledOn" format="boolean"/>
 *      <attr name="showWarnWhenSwitchToggledOff" format="boolean"/>
 *       <attr name="showWarnWhenSwitchToggledOnMsg" format="string"/>
 *           <attr name="showWarnWhenSwitchToggledOffMsg" format="string"/>
 **/
//TODO complete this
@Composable
internal inline fun Switch(
    preferenceName:String,
    key:String,

    title: String,
    shortDescription: String,
    detailedDescription: String = shortDescription,
    useShortDescription: Boolean,

    showWarnWhenSwitchToggledOn: Boolean,
    showWarnWhenSwitchToggledOnMsg: Boolean,

    showWarnWhenSwitchToggledOff: Boolean,
    showWarnWhenSwitchToggledOffMsg: Boolean ,

    crossinline onCheckedChange: (Boolean) -> Unit,
) {
    val amUsingShortString = remember { mutableStateOf(useShortDescription) }
    Row(modifier = Modifier
        .padding(15.dp)
        .wrapContentHeight()
        .clickable { amUsingShortString.value = !amUsingShortString.value }
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
                text = if (amUsingShortString.value) shortDescription else detailedDescription,
                style = TextStyle(fontSize = 12.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 10.dp),
            )
        }
        val preference = LocalContext.current.sharedPreference(preferenceName)
        androidx.compose.material.Switch(
            checked = false,
            modifier = Modifier
                .padding(start = 10.dp)
                .weight(1f),
            onCheckedChange = {
                onCheckedChange(it)
                preference?.changePreference(key, it)
            }
        )
    }
}