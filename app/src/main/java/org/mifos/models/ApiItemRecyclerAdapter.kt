package org.mifos.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_api_item.view.*
import org.mifos.R
import org.mifos.ui.HomeViewModel
import org.mifos.utils.hide
import org.mifos.utils.show

/**
 * Created by grandolf49 on 17-06-2020
 *
 * RecyclerView Adapter class for displaying ApiItems
 */
class ApiItemRecyclerAdapter(
    var context: Context,
    private var apiItemsList: MutableList<ApiItemModel>,
    private val homeViewModel: HomeViewModel
) : RecyclerView.Adapter<ApiItemRecyclerAdapter.ApiItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiItemViewHolder {
        return ApiItemViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.layout_api_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return apiItemsList.size
    }

    override fun onBindViewHolder(holder: ApiItemViewHolder, position: Int) {
        val apiItem: ApiItemModel = apiItemsList[position]
        holder.tvApiName.text = apiItem.apiName
        holder.tvApiDescription.text = apiItem.apiDescription
        holder.btnTestApi.setOnClickListener {
            holder.llApiResponse.show()
            holder.pbApiResponse.show()
            holder.tvApiResponse.text = context.getString(R.string.api_response)

            // Handle API Response
            homeViewModel.testApi(
                apiItem.apiEndPoint!!,
                { response ->
                    holder.pbApiResponse.hide()
                    holder.tvApiResponse.text = "$response"
                    homeViewModel.homeListener?.onSuccess("Success")
                },
                { error ->
                    holder.pbApiResponse.hide()
                    holder.tvApiResponse.text = "$error"
                    homeViewModel.homeListener?.onFailure(error.toString())
                }, {
                    holder.pbApiResponse.hide()
                })
        }
    }

    class ApiItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvApiName: TextView = itemView.tv_api_name
        var tvApiDescription: TextView = itemView.tv_api_description
        var tvApiResponse: TextView = itemView.tv_api_response
        var btnTestApi: Button = itemView.btn_test_api
        var btn_copy_response: Button = itemView.btn_copy_response
        var llApiResponse: RelativeLayout = itemView.ll_api_response
        var pbApiResponse: ProgressBar = itemView.pb_api_response
    }

}