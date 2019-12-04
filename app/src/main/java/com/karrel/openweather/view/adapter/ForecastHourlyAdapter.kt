package com.karrel.openweather.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karrel.openweather.R
import team.tuna.openweather.model.weather.CurrentWeather
import com.karrel.openweather.extension.KtoC
import com.karrel.openweather.extension.toAmPmHour2
import com.karrel.openweather.extension.toWeatherIcon
import kotlinx.android.synthetic.main.item_hourly_weather.view.*

class ForecastHourlyAdapter :
    RecyclerView.Adapter<ForecastHourlyAdapter.ForecastHourlyViewHolder>() {

    private val datas: ArrayList<CurrentWeather> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastHourlyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hourly_weather, parent, false)
        return ForecastHourlyViewHolder(view)
    }

    override fun getItemCount() = datas.size

    override fun onBindViewHolder(holder: ForecastHourlyViewHolder, position: Int) {
        holder.updateData(datas[position])
    }

    fun setData(list: List<CurrentWeather>) {
        println("list.size : ${list.size}")
        datas.clear()
        datas.addAll(list)
        notifyDataSetChanged()
    }


    inner class ForecastHourlyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var data: CurrentWeather
        private var textTime: TextView = view.textTime
        private var imageIcon: ImageView = view.imageIcon
        private var textCurrentTemp: TextView = view.textCurrentTemp

        fun updateData(data: CurrentWeather) {
            println("ForecastHourlyViewHolder :: updateData")
            this.data = data
            val weather = data.weather[0]

            textTime.text = data.dt.toAmPmHour2()

            imageIcon.setImageResource(weather.icon.toWeatherIcon())

            textCurrentTemp.text = data.main.temp.KtoC()
        }

    }
}
