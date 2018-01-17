package mapfoursquare;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

import javax.swing.text.html.parser.Parser;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import static mapfoursquare.MapUtils.geolocalisation;
import static mapfoursquare.MapUtils.geolocalisationString;

public class ParserFoursquare {

    private FoursquareApi foursquareApi;
    private static String ll = "43.6863732,7.2329360000000005";



    public ParserFoursquare(Boolean geolocalized) {
            this.foursquareApi = new FoursquareApi("V5HMJJ3KRCH3T053QWHGST2PIDWEH44H2JUQCGKZ5RKHWICP", "P4QQSTEJKPFGJI3P2P2PXNQ5MINLEH5WEXKTIBELFV15030H", "");
            foursquareApi.setVersion("20170801");

            if (geolocalized) {

                try {
                    ll = geolocalisationString();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (GeoIp2Exception e) {
                    e.printStackTrace();
                }
            }
    }



    public Result<VenuesSearchResult> search() throws FoursquareApiException {
        return foursquareApi.venuesSearch(ll, "cave vin", 10, "checkin", null, null, null, null);
    }

    /**public Result<VenuesSearchResult> searchWithLocation(String ll) throws FoursquareApiException {
        return foursquareApi.venuesSearch(ll, "cave vin", 10, "checkin", null, null, null, null);
    }**/


}
