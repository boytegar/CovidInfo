package id.boytegar.covidinfo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import id.boytegar.covidinfo.R
import id.boytegar.covidinfo.adapter.InfoAdapter
import id.boytegar.covidinfo.databinding.FragmentMainBinding
import id.boytegar.covidinfo.helper.UseCaseResult
import id.boytegar.covidinfo.model.Global
import id.boytegar.covidinfo.viewmodel.InfoViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment : Fragment(), OnMapReadyCallback {

    lateinit var infoViewModel: InfoViewModel
    lateinit var gmaps: GoogleMap
    lateinit var bindingUtil: FragmentMainBinding
    lateinit var adapter: InfoAdapter
    lateinit var list_data: Global


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        infoViewModel = ViewModelProvider(this).get(InfoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingUtil = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        bindingUtil.infoViewModel = infoViewModel
        bindingUtil.lifecycleOwner = this
        return bindingUtil.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.mapView.run {
            onCreate(savedInstanceState)
            getMapAsync(this@MainFragment)
        }
        initView(view)
    }

    fun initView(view: View){
        createAdapter()
        infoViewModel.list_global.observe(viewLifecycleOwner, Observer {
            when(it){
                is UseCaseResult.Success<Global>->{
                    list_data = it.data
                    adapter.setData(list_data)

                }
                is UseCaseResult.Progress ->{
                    Log.e("Data", "Progress")
                }
                is UseCaseResult.Error ->{
                    Log.e("Data", it.exception.toString())
                }

            }
        })
    }

    override fun onMapReady(maps: GoogleMap) {
        gmaps = maps
        gmaps.run {
            isMyLocationEnabled = false
            uiSettings.isMyLocationButtonEnabled = false
            uiSettings.isZoomGesturesEnabled = false
            uiSettings.isZoomControlsEnabled = false
            uiSettings.isScrollGesturesEnabled = false
            setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                activity, R.raw.dark))
        }
    }

    fun createAdapter() {
        adapter = InfoAdapter()
        view_pager.adapter = adapter
        view_pager.clipToPadding = false
        view_pager.clipChildren  = false
        view_pager.offscreenPageLimit = 3
        view_pager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_ALWAYS
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(resources.getDimension(R.dimen._10sdp).toInt()))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY =  (0.85f + r * 0.15f)
        }
        view_pager.setPageTransformer(compositePageTransformer)
        view_pager.registerOnPageChangeCallback(pagechange)
    }

    val pagechange = object: ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            val attr = list_data[position].attributes
            val lat = attr?.lat
            val lng = attr?.long
            val latlngs = LatLng(lat!!, lng!!)
            gmaps.run {
                clear()
                addMarker(MarkerOptions().position(latlngs).title("Marker in ${attr?.countryRegion}"))
                moveCamera(CameraUpdateFactory.newLatLngZoom(latlngs, 4f))
            }

        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    view_pager.unregisterOnPageChangeCallback(pagechange)
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }


}
