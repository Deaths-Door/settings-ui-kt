package com.deathsdoor.ui_core.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Screen(content: @Composable ColumnScope.() -> Unit){

    Column(Modifier.verticalScroll(rememberScrollState())) {
        content()
    }
}