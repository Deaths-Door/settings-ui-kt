package com.deathsdoor.ui_core.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable

//TODO complete and add attribute to it
@Composable
fun Category(content: LazyListScope.() -> Unit){
    LazyColumn(content = content)
}