package com.example.nutrisaapplication.data

import android.content.Context
import android.content.SharedPreferences

class Preference(context: Context) {

    val PREFS_NAME = "sharedpreferences"
    val FACHADA = "fachada"
    val PISO = "piso"
    val BARRA = "barra"
    val CAJA = "caja"
    val BODEGA = "bodega"
    val PLAN = "plan"
    val VISITA_RAPIDA = "visitaRapida"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    var visitaRapida: Boolean
        get() = prefs.getBoolean(VISITA_RAPIDA, false)
        set(value) = prefs.edit().putBoolean(VISITA_RAPIDA, value).apply()
    var fachada: Boolean
        get() = prefs.getBoolean(FACHADA, false)
        set(value) = prefs.edit().putBoolean(FACHADA, value).apply()
    var piso: Boolean
        get() = prefs.getBoolean(PISO, false)
        set(value) = prefs.edit().putBoolean(PISO, value).apply()
    var barras: Boolean
        get() = prefs.getBoolean(BARRA, false)
        set(value) = prefs.edit().putBoolean(BARRA, value).apply()
    var caja: Boolean
        get() = prefs.getBoolean(CAJA, false)
        set(value) = prefs.edit().putBoolean(CAJA, value).apply()
    var bodega: Boolean
        get() = prefs.getBoolean(BODEGA, false)
        set(value) = prefs.edit().putBoolean(BODEGA, value).apply()
    var plan: Boolean
        get() = prefs.getBoolean(PLAN, false)
        set(value) = prefs.edit().putBoolean(PLAN, value).apply()
/*    var user: String
        get() = prefs.getString(USER, "")!!
        set(value) = prefs.edit().putString(USER, value).apply()*/
/*    val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
    val sharedPreferences = EncryptedSharedPreferences.create(
        "sharedpreferences_coltomex",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )*/
/*
    fun saveSharedpreferenCripted(accessToken: String) {
        sharedPreferences.edit().putString(SHARED_NAME_TOKEN, accessToken).apply()
    }
    fun getSharepredPreferen():String {
        return sharedPreferences.getString(SHARED_NAME_TOKEN, "")!!
    }
    fun resetSharedPreference(){
        sharedPreferences.edit().clear().apply()
    }*/
}