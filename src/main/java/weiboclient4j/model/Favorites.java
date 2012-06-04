package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Favorites {
    private List<FavoritesItem> favorites;
    private int totalNumber;

    public List<FavoritesItem> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<FavoritesItem> favorites) {
        this.favorites = favorites;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }
}
