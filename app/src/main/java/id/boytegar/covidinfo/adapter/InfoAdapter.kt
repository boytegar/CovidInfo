package id.boytegar.covidinfo.adapter

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.boytegar.covidinfo.databinding.ItemSliderBinding
import id.boytegar.covidinfo.model.GlobalItem
import kotlinx.android.synthetic.main.item_slider.view.*


class InfoAdapter() : RecyclerView.Adapter<InfoAdapter.ViewHolder>() {

    var datas: List<GlobalItem> = listOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = datas[position]
        holder.bind(item)
    }

    inner class ViewHolder(val binding: ItemSliderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: GlobalItem) {
            if(data != null){
                binding.info = data.attributes
                binding.executePendingBindings()
            }

        }
    }
    override fun onCreateViewHolder(views: ViewGroup, position: Int):ViewHolder {
        val binding  = ItemSliderBinding.inflate(
            LayoutInflater.from(views.context), views, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    fun setData(list: List<GlobalItem>){
        datas = list
        notifyDataSetChanged()
    }
}