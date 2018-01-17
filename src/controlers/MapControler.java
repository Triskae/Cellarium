package controlers;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import mapfoursquare.MapUtils;
import mapfoursquare.ParserFoursquare;


public class MapControler implements Initializable, MapComponentInitializedListener {

    @FXML
    private Button button;

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;
    private Double lat = 43.6863732;
    private Double lng = 7.2329360000000005;
    private Boolean demo = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }



    @Override
    public void mapInitialized() {

        ParserFoursquare parserFoursquare = new ParserFoursquare(demo);
        Result <VenuesSearchResult> result = null;
        ArrayList<LatLong> latLongList = new ArrayList<>();

        try {
            result = parserFoursquare.search();
        } catch (FoursquareApiException e) {
            e.printStackTrace();
        }



        if (result.getMeta().getCode() == 200) {
            // if query was ok we can finally we do something with the data


            for (CompactVenue venue : result.getResult().getVenues()) {

                latLongList.add(new LatLong(venue.getLocation().getLat(), venue.getLocation().getLng()));
            }

            //Set the initial properties of the map.
            MapOptions mapOptions = new MapOptions();

            if (demo) {
                try {
                    Double [] temp = MapUtils.geolocalisation();
                    lat = temp[0];
                    lng = temp[1];
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (GeoIp2Exception e) {
                    e.printStackTrace();
                }
            }

            mapOptions.center(new LatLong(lat, lng))
                    .overviewMapControl(false)
                    .panControl(false)
                    .rotateControl(false)
                    .scaleControl(false)
                    .streetViewControl(false)
                    .zoomControl(false)
                    .zoom(12);

            map = mapView.createMap(mapOptions);

            //Add markers to the map

            for (LatLong ltlong : latLongList) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(ltlong);
                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);
            }


            InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
            infoWindowOptions.content("<h2>Fred Wilkie</h2>"
                    + "Current Location: Safeway<br>"
                    + "ETA: 45 minutes");

            /**InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
             fredWilkeInfoWindow.open(map, fredWilkieMarker);**/



        } else {
            System.out.println("Error occured: ");
            System.out.println("  code: " + result.getMeta().getCode());
            System.out.println("  type: " + result.getMeta().getErrorType());
            System.out.println("  detail: " + result.getMeta().getErrorDetail());
        }

    }

}