package com.deathsdoor.ui_core.extras

import android.content.ContentResolver
import android.content.Context
import android.net.Uri

data class Choice(val text:String,val image: Uri){
    constructor(text:String) : this(text,Uri.parse(""))
    constructor(text:String,image:String): this(text,Uri.parse(image))
    constructor(text:String,image:Int,context:Context) : this(text,Uri.parse(
        ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
            context.resources.getResourcePackageName(image) + '/' +
            context.resources.getResourceTypeName(image) + '/' +
            context.resources.getResourceEntryName(image))) {
    }
}