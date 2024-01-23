package com.gmb.openstreetmap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.gmb.openstreetmap.databinding.ActivityMainBinding
import org.osmdroid.api.IMapController
import org.osmdroid.bonuspack.BuildConfig
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mapView: MapView

    private lateinit var mapController: IMapController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().setUserAgentValue(BuildConfig.LIBRARY_PACKAGE_NAME);
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mapView = binding.mapview
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setBuiltInZoomControls(true)
        mapView.setMultiTouchControls(true)


        mapView.controller.setZoom(12)

        val center = GeoPoint(-24.01079, -46.41796)
        mapController = mapView.getController()
        mapController.animateTo(center)

        addMarker(center)
    }

    private fun addMarker(center: GeoPoint) {
        val marker = Marker(mapView)
        marker.position = center
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.setIcon(ResourcesCompat.getDrawable(resources, R.drawable.ic_map, null))
        marker.setTitle("Start point")

        mapView.overlays.clear()
        mapView.overlays.add(marker)
        mapView.invalidate()
    }
}