package weiboclient4j;

import org.codehaus.jackson.JsonNode;
import weiboclient4j.model.DomesticResult;
import weiboclient4j.model.Geo;
import weiboclient4j.params.Address;
import weiboclient4j.params.Coordinate;
import weiboclient4j.params.Coordinates;
import weiboclient4j.params.Ip;
import weiboclient4j.params.ParameterAction;
import static weiboclient4j.utils.CollectionUtils.firstItem;

import java.util.List;

/**
 * @author Hover Ruan
 */
public class LocationService extends AbstractService {
    public LocationService(WeiboClient client) {
        super(client);
    }

    public <T extends GetMapImageParam> String getMapImage(T... params) throws WeiboClientException {
        return doGet("location/base/get_map_image", buildParams(params), GetMapImageResponse.class).getMapImage();
    }

    public List<Geo> ipToGeo(Ip ip) throws WeiboClientException {
        return doGet("location/geo/ip_to_geo", withParams(ip), GeoListResponse.class).getGeos();
    }

    public List<Geo> addressToGeo(Address address) throws WeiboClientException {
        return doGet("location/geo/address_to_geo", withParams(address), GeoListResponse.class).getGeos();
    }

    public Geo geoToAddress(Coordinate coordinate) throws WeiboClientException {
        return firstItem(doGet("location/geo/geo_to_address", withParams(coordinate), GeoListResponse.class).getGeos());
    }

    public Geo gpsToOffset(Coordinate coordinate) throws WeiboClientException {
        return firstItem(doGet("location/geo/gps_to_offset", withParams(coordinate), GeoListResponse.class).getGeos());
    }

    public List<DomesticResult> isDomestic(Coordinates coordinates) throws WeiboClientException {
        JsonNode json = doGet("location/geo/is_domestic", withParams(coordinates), JsonNode.class);

        return DomesticResult.parseList(json);
    }

    public static interface GetMapImageParam extends ParameterAction {
    }

    private class GeoListResponse {
        private List<Geo> geos;

        private List<Geo> getGeos() {
            return geos;
        }

        private void setGeos(List<Geo> geos) {
            this.geos = geos;
        }
    }

    private class GetMapImageResponse {
        private MapImageItem[] map;

        public String getMapImage() {
            return map != null && map.length > 0 ? map[0].getImageUrl() : null;
        }

        private MapImageItem[] getMap() {
            return map;
        }

        private void setMap(MapImageItem[] map) {
            this.map = map;
        }
    }

    private class MapImageItem {
        private String imageUrl;

        private String getImageUrl() {
            return imageUrl;
        }

        private void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
