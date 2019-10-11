package com.ioasys.empresas.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ioasys.empresas.EnterpriseDetailActivity
import com.ioasys.empresas.R
import com.ioasys.empresas.model.Enterprise
import com.squareup.picasso.Picasso

class SearchAdapter(private val myDataset: List<Enterprise>?) :
    RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(constraintLayout: ConstraintLayout) :
        RecyclerView.ViewHolder(constraintLayout) {

        val enterpriseImageView: ImageView =
            constraintLayout.findViewById(R.id.enterprise_image)
        val enterpriseNameTextView: TextView =
            constraintLayout.findViewById(R.id.tv_enterprise_name)
        val enterpriseTypeTextView: TextView =
            constraintLayout.findViewById(R.id.tv_enterprise_type)
        val enterpriseCountryTextView: TextView =
            constraintLayout.findViewById(R.id.tv_enterprise_country)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        // create a new view
        val constraintLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false) as ConstraintLayout
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(constraintLayout)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val enterprise = myDataset!![position]
        holder.enterpriseCountryTextView.text = enterprise.country
        holder.enterpriseNameTextView.text = enterprise.enterprise_name
        holder.enterpriseTypeTextView.text = enterprise.enterprise_type.enterprise_type_name

        Picasso.get()
            .load(enterprise.photo)
            .error(R.drawable.img_e_1_lista)
            .into(holder.enterpriseImageView)

        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var context: Context = v!!.context
                var intent: Intent = Intent(context, EnterpriseDetailActivity::class.java)
                intent.putExtra("description", enterprise.description)
                intent.putExtra("photo", enterprise.photo)
                context.startActivity(intent)
            }
        })
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset!!.size
}