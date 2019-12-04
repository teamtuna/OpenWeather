package com.karrel.openweather.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karrel.openweather.R
import com.karrel.openweather.extension.KtoC
import com.karrel.openweather.extension.alphaAnim
import com.karrel.openweather.extension.loadImage
import com.karrel.openweather.extension.toWeatherIcon
import team.tuna.openweather.model.weather.CurrentWeather
import com.karrel.openweather.network.googlemapImageUrl
import kotlinx.android.synthetic.main.item_current_weather.view.*

class CurrentListAdapter(val onClickItem: (cityId: Int, cityName: String) -> Unit) :
        RecyclerView.Adapter<CurrentListAdapter.WeatherListViewHolder>() {
    private val wetherDatas: ArrayList<CurrentWeather> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_current_weather, parent, false)
        return WeatherListViewHolder(view)
    }

    override fun getItemCount() = wetherDatas.size

    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        holder.updateData(wetherDatas[position])
    }

    fun setData(datas: List<CurrentWeather>) {
        println("datas : size > ${datas.size}")
        wetherDatas.clear()
        wetherDatas.addAll(datas)
        notifyDataSetChanged()
    }


    inner class WeatherListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var data: CurrentWeather? = null
        private var imageMap: ImageView = view.imageMap
        private var textName: TextView = view.textName
        private var textTemp: TextView = view.textTemp
        private var textDescription: TextView = view.textDescription
        private var imageIcon: ImageView = view.imageIcon

        init {
            view.setOnClickListener(object : OnSingleClickListener() {
                override fun onSingleClick(view: View) {
                    data?.let {
                        onClickItem(it.id, it.name)
                    }
                }
            })
        }

        fun updateData(data: CurrentWeather) {
            this.data = data

            textName.text = data.name
            textTemp.text = data.main.temp.KtoC()
            val weather = data.weather[0]
            textDescription.text = weather.description

            imageIcon.setImageResource(weather.icon.toWeatherIcon())

            // 구글맵
            val url = googlemapImageUrl(lat = data.coord.lat, lon = data.coord.lon)
            imageMap.loadImage(url, onSuccess = { imageMap.alphaAnim() })
        }
    }
}

abstract class OnSingleClickListener : View.OnClickListener {

    private val CLICK_INTERVAL = 1000L
    private var lastClickedTime = 0L

    abstract fun onSingleClick(view: View)

    override fun onClick(v: View) {
        val currentClickTime = System.currentTimeMillis()
        val clickGapTime = currentClickTime - lastClickedTime
        lastClickedTime = currentClickTime

        if (clickGapTime < CLICK_INTERVAL) {
            return
        }

        onSingleClick(v)
    }

}