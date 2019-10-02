package com.karrel.openweather.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karrel.openweather.R
import com.karrel.openweather.model.weather.CurrentWeather
import com.karrel.openweather.extension.KtoC
import com.karrel.openweather.extension.toEkkmm
import com.karrel.openweather.extension.toWeatherIcon
import kotlinx.android.synthetic.main.item_3hourly_weather.view.*

class Forecast3HourlyAdapter :
        RecyclerView.Adapter<Forecast3HourlyAdapter.Forecast3HourlyViewHolder>() {

    private val datas: ArrayList<CurrentWeather> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Forecast3HourlyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_3hourly_weather, parent, false)
        return Forecast3HourlyViewHolder(view)
    }

    override fun getItemCount() = datas.size

    override fun onBindViewHolder(holder: Forecast3HourlyViewHolder, position: Int) {
        holder.updateData(datas[position])
    }

    fun setData(list: List<CurrentWeather>) {
        datas.clear()
        datas.addAll(list)
        notifyDataSetChanged()
    }


    inner class Forecast3HourlyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var data: CurrentWeather
        private val textDayOfWeek: TextView = view.textDayOfWeek
        private var imageIcon: ImageView = view.imageIcon
        private val textHighTemp: TextView = view.textHighTemp
        private val textMinTemp: TextView = view.textMinTemp
        private val textDescription: TextView = view.textDescription

        fun updateData(weatherList: CurrentWeather) {
            data = weatherList
            val weather = data.weather[0]

            // Tuestday 15:00
            textDayOfWeek.text = data.dt.toEkkmm()

            imageIcon.setImageResource(weather.icon.toWeatherIcon())

            textHighTemp.text = data.main.temp_max.KtoC()
            textMinTemp.text = data.main.temp_min.KtoC()

            textDescription.text = weather.description
        }

    }
}
