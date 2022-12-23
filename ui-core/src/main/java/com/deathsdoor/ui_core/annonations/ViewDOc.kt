package com.deathsdoor.ui_core.annonations

/**
 * An annotation that can be used to provide documentation for custom views.
 */
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
@kotlin.annotation.Target(AnnotationTarget.TYPE, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
annotation class ViewDoc(
    /**
     * The name of the attribute or method.
     */
    val name: String = "",
    /**
     * The data type of the attribute or method.
     */
    val type: String = "",
    /**
     * A description of the attribute or method.
     */
    val description: String = ""
)
