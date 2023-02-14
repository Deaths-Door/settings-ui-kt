package com.deathsdoor.ui_core.compose
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.deathsdoor.ui_core.compose.Switch as internalSwitch

abstract class Settings {
    companion object {
        @Preview
        @Composable
        fun Switch(
            preferenceName:String ="",
            key:String = "",

            title: String = "Title",
            shortDescription: String = "description",
            detailedDescription: String = shortDescription,
            useShortDescription: Boolean = true,

            showWarnWhenSwitchToggledOn: Boolean = true,
            showWarnWhenSwitchToggledOnMsg: Boolean = true,

            showWarnWhenSwitchToggledOff: Boolean = true,
            showWarnWhenSwitchToggledOffMsg: Boolean = true,

            onCheckedChange: (Boolean) -> Unit = {},
        ) {
            internalSwitch(
                preferenceName = preferenceName,
                key = key,
                title = title,
                shortDescription = shortDescription,
                detailedDescription = detailedDescription,
                useShortDescription = useShortDescription,
                showWarnWhenSwitchToggledOn = showWarnWhenSwitchToggledOn,
                showWarnWhenSwitchToggledOnMsg = showWarnWhenSwitchToggledOnMsg,
                showWarnWhenSwitchToggledOff = showWarnWhenSwitchToggledOff,
                showWarnWhenSwitchToggledOffMsg = showWarnWhenSwitchToggledOffMsg,
                onCheckedChange = onCheckedChange
            )
        }
    }
}