package layout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.ui.main.notificacion.NotificationInterface
import com.example.nutrisaapplication.ui.main.notificacion.model.PushNotification


class NotificacionAdapter(private var myDataset: ArrayList<PushNotification>,val  listener: NotificationInterface) : RecyclerView.Adapter<NotificacionAdapter.ViewHolder>() {
    //var pushNotifications: ArrayList<PushNotification> = ArrayList()
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    var dataMutable= mutableListOf<PushNotification>()

    inner class ViewHolder(vista: View, listener: NotificationInterface): RecyclerView.ViewHolder(vista),View.OnClickListener {

        var body: TextView
        var title: TextView
        var date: TextView
        //var boton: Button
        var listener:NotificationInterface?=null

        init {
            body = itemView.findViewById(R.id.tv_description_not)
            title = itemView.findViewById(R.id.tv_title_not)
            date=itemView.findViewById(R.id.tv_expiry_date)
            //boton=itemView.findViewById(R.id.buttonMostrar)
            this.listener= listener
            vista.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            this.listener?.onClicked(p0!!,adapterPosition)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificacionAdapter.ViewHolder {
        // create a new view
        // set the view's size, margins, paddings and layout
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_list_notification, parent, false)
        return ViewHolder(view,listener)
    }

    fun replaceData(items: ArrayList<PushNotification>) {
        setList(items)
        notifyDataSetChanged()
    }

    fun setList(list: ArrayList<PushNotification>) {
        list.also {
            this.myDataset = list
        }
    }
        fun setListMutable(list: MutableList<PushNotification>) {
            dataMutable = list
            dataMutable.forEach {
                myDataset.add(it)
            }
            notifyDataSetChanged()
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = myDataset.size

        fun addItem(pushMessage: PushNotification?) {
            if (pushMessage != null) {
                myDataset.add(0, pushMessage)
            }
            notifyItemInserted(0)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            val newNotification: PushNotification = dataMutable[position]
            holder.title.setText(newNotification.mTitle)
            holder.body.setText(newNotification.mDescription)
            holder.date.setText(String.format("Válido hasta el %s", newNotification.mExpiryDate))
        }
    }

