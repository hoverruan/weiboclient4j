package weiboclient4j;

import weiboclient4j.params.ParameterAction;

/**
 * @author Hover Ruan
 */
public class LocationService extends AbstractService {
    public LocationService(WeiboClient client) {
        super(client);
    }

    public <T extends GetMapImageParam> String getMapImage(T... params) throws WeiboClientException {
        GetMapImageResponse response = doGet("location/base/get_map_image",
                buildParams(params),
                GetMapImageResponse.class);

        return response.getMapImage();
    }

    public static interface GetMapImageParam extends ParameterAction {
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
