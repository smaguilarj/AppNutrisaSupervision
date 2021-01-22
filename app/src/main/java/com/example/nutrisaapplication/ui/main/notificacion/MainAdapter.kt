package com.example.nutrisaapplication.ui.main.notificacion

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.ui.main.notificacion.model.PushNotification

class MainAdapter(val context: Context, val listeners: NotificationInterface) : RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    var dataList = mutableListOf<PushNotification>()

    fun setListData(data: MutableList<PushNotification>) {
        dataList = data
    }
    inner class ViewHolder(vista: View, listener: NotificationInterface?) : RecyclerView.ViewHolder(vista),View.OnClickListener {

        var listener:NotificationInterface?=null
        var body: TextView
        var title: TextView
        var date: TextView
        var tvBtn: TextView

        //var boton: Button
        init {
            tvBtn = itemView.findViewById(R.id.tvMostrar)
            body = itemView.findViewById(R.id.tv_description_not)
            title = itemView.findViewById(R.id.tv_title_not)
            date = itemView.findViewById(R.id.tv_expiry_date)
            this.listener = listener
            vista.setOnClickListener(this)
            // boton = itemView.findViewById(R.id.buttonMostrar)
        }

        override fun onClick(p0: View?) {
            this.listener?.onClicked(p0!!,adapterPosition)
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainAdapter.ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_list_notification, parent, false)
        return ViewHolder(view,listeners)
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        val newNotification: PushNotification = dataList[position]
        holder.title.setText(newNotification.mTitle)
        holder.body.setText(newNotification.mDescription)
        holder.date.setText(String.format("Válido hasta el %s", newNotification.mExpiryDate))
        holder.tvBtn.setText("Mostrar")
    }

    override fun getItemCount(): Int {
        return if (dataList.size >= 0) {
            dataList.size
        } else {
            0
        }
    }


}

