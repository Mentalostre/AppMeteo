package app.appmeteo;

import java.util.ArrayList;
import java.util.List;

public class Favorites {
    public List<String> favorites = new ArrayList<>();

    public Favorites(List<String> favorites) {
        this.favorites = favorites;
    }
    public Favorites() {}
    public List<String> getFavorites() {return favorites;}

    public String getFavorite(int i) {
        return favorites.get(i);
    }

    public boolean isPresent(String city) {
        for (int i = 0; i < favorites.size(); i++) {
            if (city.equals(favorites.get(i))) return true;
        }
        return false;
    }

    public void addFavorite(String favorite) {
        if (!isPresent(favorite)) favorites.add(favorite);
    }

    public void deleteFavorite(int i) {
        favorites.remove(i);
    }

    public void deleteFavorite(String city) {
        for (int i = 0; i < favorites.size(); i++) {
            if (city.equals(favorites.get(i))) favorites.remove(i);
        }
    }

    public void swapIndex(int index1, int index2) {
        String temp = favorites.get(index1);
        favorites.set(index1, favorites.get(index2));
        favorites.set(index2, temp);
    }

    public int favoriteLen() {
        return favorites.size();
    }

    public void show() {
        if (favorites.size() > 0) {
            System.out.println(favorites.toString());
        }
        else {
            System.out.println("Empty");
        }
    }
}
