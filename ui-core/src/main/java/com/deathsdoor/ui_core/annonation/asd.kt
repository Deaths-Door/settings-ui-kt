package com.deathsdoor.ui_core.annonation

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

//TODO make this
annotation class PreviewParms<T>(){
    class PrivatePreviewParameterProvider<T>: PreviewParameterProvider<T> {
        override val values: Sequence<T> = sequenceOf( )
    }
}