package com.deathsdoor.ui_core.extras.functinos.extensions

import com.deathsdoor.ui_core.R
import com.deathsdoor.ui_core.extras.interfaces.OnSwitchToggleListener

object AttributesBuilder {
    class AttributeSet(
        private val attributes: Map<String, Any>) : android.util.AttributeSet {
        override fun getAttributeCount(): Int = attributes.size
        override fun getAttributeName(index: Int) = attributes.keys.elementAt(index)
        override fun getAttributeValue(index: Int): String = attributes.values.elementAt(index).toString()
        override fun getAttributeValue(namespace: String?, name: String?): String = attributes[name].toString()
        override fun getPositionDescription(): String = "Position $index"
        override fun getAttributeListValue(index: Int, options: Array<out String>?, defaultValue: Int): Int = defaultValue
        override fun getAttributeBooleanValue(namespace: String?, attribute: String?, defaultValue: Boolean): Boolean = attributes[attribute] as? Boolean ?: defaultValue
        override fun getAttributeBooleanValue(index: Int, defaultValue: Boolean) = attributes.values.elementAt(index) as? Boolean ?: defaultValue
        override fun getAttributeResourceValue(namespace: String?, attribute: String?, defaultValue: Int): Int = attributes[attribute] as? Int ?: defaultValue
        override fun getAttributeResourceValue(index: Int, defaultValue: Int): Int = attributes.values.elementAt(index) as? Int ?: defaultValue
        override fun getAttributeIntValue(namespace: String?, attribute: String?, defaultValue: Int): Int = attributes[attribute] as? Int ?: defaultValue
        override fun getAttributeIntValue(index: Int, defaultValue: Int): Int = attributes.values.elementAt(index) as? Int ?: defaultValue
        override fun getAttributeUnsignedIntValue(namespace: String?, attribute: String?, defaultValue: Int): Int = attributes[attribute] as? Int ?: defaultValue
        override fun getAttributeUnsignedIntValue(index: Int, defaultValue: Int): Int = attributes.values.elementAt(index) as? Int ?: defaultValue

        override fun getAttributeNameResource(p0: Int): Int {
            TODO("Not yet implemented")
        }

        override fun getAttributeListValue(
            p0: String?,
            p1: String?,
            p2: Array<out String>?,
            p3: Int
        ): Int {
            TODO("Not yet implemented")
        }
        override fun getAttributeFloatValue(namespace: String?, attribute: String?, defaultValue: Float): Float {
            // Return the attribute float value for the given namespace and attribute
            val value = when (attribute) {
                // Add cases for attributes that return float values
                else -> defaultValue
            }
            // Increment the index
            index++
            return value
        }
        override fun getAttributeFloatValue(index: Int, defaultValue: Float): Float {
            // Return the attribute float value for the given index
            val value = when (index) {
                // Add cases for attributes that return float values
                else -> defaultValue
            }
            // Increment the index
            this.index++
            return value
        }
        // Define the idAttribute property and initialize it with an empty string
        private val idAttribute: String = "xyz"
        override fun getIdAttribute(): String {
            // Return the ID attribute value
            val value = idAttribute
            // Increment the index
            index++
            return value
        }
        // Define the classAttribute property and initialize it with an empty string
        override fun getClassAttribute(): String {
            // Return the class attribute value
            val value = classAttribute
            // Increment the index
            index++
            return value
        }
        // Assign a value to the idAttributeResourceValue property
        override fun getIdAttributeResourceValue(defaultValue: Int): Int {
            // Return the ID attribute resource value
            val value = defaultValue
            // Increment the index
            index++
            return value
        }
        override fun getStyleAttribute(): Int {
            // Return the style attribute value
            val value = styleAttribute
            // Increment the index
            index++
            return value
        }
    }
}