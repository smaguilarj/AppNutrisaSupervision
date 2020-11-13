package com.example.nutrisaapplication.ui.main.plan_trabajo.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutrisaapplication.data.SharedApp
import com.example.nutrisaapplication.data.model.PlanTrabajoModel

class PlanViewModel:ViewModel() {

    val listData= MutableLiveData<MutableList<PlanTrabajoModel>>()
    val plan= mutableListOf<PlanTrabajoModel>()


    fun setListData(listaPlanes:MutableList<PlanTrabajoModel>){
        //listData.postValue(listaPlanes)
        val listaPlan= SharedApp.prefs.borrarLista
        if (listaPlan){
            //borrar lista
            plan.clear()
            plan.addAll(listaPlanes)
        }else{
            plan.addAll(listaPlanes)
        }
        listData.value=plan
        //listData.postValue(listaPlanes)
    }
   /* fun getListData(): LiveData<List<PlanTrabajoModel>> {
        //dataPlan.obtenerListaPLan()?.let { setListData(it) }
        return listData
    }*/
   fun getListData(): LiveData<MutableList<PlanTrabajoModel>> {
       //dataPlan.obtenerListaPLan()?.let { setListData(it) }
       return listData
   }
    /*fun getListLiveData():LiveData<List<PlanTrabajoModel>>{
        return listData
    }*/
}