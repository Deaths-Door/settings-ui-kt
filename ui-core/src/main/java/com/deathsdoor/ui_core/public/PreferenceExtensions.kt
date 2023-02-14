package com.deathsdoor.ui_core.public

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
//TODO update doc

//Extensions preference functions
object PreferenceExtensions{
    fun Context.sharedPreference(name: String, mode:Int = Context.MODE_PRIVATE): SharedPreferences? = getSharedPreferences(name,mode)

    fun SharedPreferences.getPreferenceValue(key:String): Any? = this.all.forEach { if(it.key == key) return it.value }
    fun SharedPreferences.getPreferenceValue(key:String, defaultValue:String = "") : String? = this.getString(key,defaultValue)
    fun SharedPreferences.getPreferenceValue(key:String, defaultValue:Boolean = false) : Boolean = this.getBoolean(key,defaultValue)
    fun SharedPreferences.getPreferenceValue(key:String, defaultValue:Int = -1) : Int = this.getInt(key,defaultValue)
    fun SharedPreferences.getPreferenceValue(key:String, defaultValue:Float = -1f) : Float = this.getFloat(key,defaultValue)
    fun SharedPreferences.getPreferenceValue(key:String, defaultValue:Long = -1) : Long = this.getLong(key,defaultValue)
    fun SharedPreferences.getPreferenceValue(key:String, defaultValue:Set<String> = setOf()) :MutableSet<String>? = this.getStringSet(key,defaultValue)
    fun <T> SharedPreferences.getPreferenceValue(key:String, defaultValue:List<T> = listOf()): ArrayList<*>? = Gson().fromJson(getString(key,Gson().toJson(defaultValue)),ArrayList::class.java)

    fun SharedPreferences.changePreference(key:String, value:String) = this.edit().putString(key,value).commit()
    fun SharedPreferences.changePreference(key:String, value:Boolean) = this.edit().putBoolean(key,value).commit()
    fun SharedPreferences.changePreference(key:String, value:Int) = this.edit().putInt(key,value).commit()
    fun SharedPreferences.changePreference(key:String, value:Float) = this.edit().putFloat(key,value).apply()
    fun SharedPreferences.changePreference(key:String, value:Long) = this.edit().putLong(key,value).apply()
    fun SharedPreferences.changePreference(key:String, value:Set<String>) = this.edit().putStringSet(key,value).apply()
    fun <T> SharedPreferences.changePreference(key:String, value:List<T>) = this.edit().putString(key, Gson().toJson(value)).commit()

    fun SharedPreferences.removeValue(key:String) = this.edit().remove(key).commit()
    fun SharedPreferences.clearValues(key:String) = this.edit().clear().commit()
}