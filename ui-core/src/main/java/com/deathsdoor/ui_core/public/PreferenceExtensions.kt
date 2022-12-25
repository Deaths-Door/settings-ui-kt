package com.deathsdoor.ui_core.public

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

//Extensions preference functions
object PreferenceExtensions{
    fun Context.getPreference(name: String, mode:Int = Context.MODE_PRIVATE): SharedPreferences? = getSharedPreferences(name,mode)

    fun SharedPreferences.getPreferenceValue(key:String): Any? = this.all.forEach { if(it.key == key) return it.value }
    fun SharedPreferences.getPreferenceValue(key:String, defaultValue:String = "") : String? = this.getString(key,defaultValue)
    fun SharedPreferences.getPreferenceValue(key:String, defaultValue:Boolean = false) : Boolean = this.getBoolean(key,defaultValue)
    fun SharedPreferences.getPreferenceValue(key:String, defaultValue:Int = -1) : Int = this.getInt(key,defaultValue)
    fun SharedPreferences.getPreferenceValue(key:String, defaultValue:Float = -1f) : Float = this.getFloat(key,defaultValue)
    fun SharedPreferences.getPreferenceValue(key:String, defaultValue:Long = -1) : Long = this.getLong(key,defaultValue)
    fun SharedPreferences.getPreferenceValue(key:String, defaultValue:Set<String> = setOf()) :MutableSet<String>? = this.getStringSet(key,defaultValue)
    fun SharedPreferences.getPreferenceValue(key:String, defaultValue:List<Any> = listOf()): List<Any> {
        val str = Gson().toJson(defaultValue)
        val data = this.getString(key,str)
        return if(data == str) defaultValue
        else Gson().fromJson(data,ArrayList::class.java)
    }


    fun SharedPreferences.changePreference(key:String, value:String) = this.edit().putString(key,value).commit()
    fun SharedPreferences.changePreference(key:String, value:Boolean) = this.edit().putBoolean(key,value).commit()
    fun SharedPreferences.changePreference(key:String, value:Int) = this.edit().putInt(key,value).commit()
    fun SharedPreferences.changePreference(key:String, value:Float) = this.edit().putFloat(key,value).apply()
    fun SharedPreferences.changePreference(key:String, value:Long) = this.edit().putLong(key,value).apply()
    fun SharedPreferences.changePreference(key:String, value:Set<String>) = this.edit().putStringSet(key,value).apply()
    fun SharedPreferences.changePreference(key:String, value:List<Any>) = this.edit().putString(key, Gson().toJson(value)).apply()
}