package app.appmeteo.model;

import app.appmeteo.Favorites;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorsTest {

    private Authors authors = new Authors(Arrays.asList("Martin Dupont", "Marie Martin", "François Cordonnier"));

    @Test
    public void testToString() {
        String expected = "Martin Dupont, Marie Martin, François Cordonnier";
        assertEquals(expected, authors.toString());
    }

    @Test
    public void testIsPresent() {
        List<String> favor = new ArrayList<>();
        favor.add("carpentras");
        Favorites favorites = new Favorites(favor);
        if (favorites.isPresent("carpentras")) System.out.println("testIsPresent: true\n");
        else System.out.println("testIsPresent: false\n");
    }

    @Test
    public void testAddFavorite() {
        List<String> favor = new ArrayList<>();
        Favorites favorites = new Favorites(favor);
        String city = "carpentras";
        favorites.addFavorite(city);
        if (favorites.isPresent(city)) System.out.println("testAddFavorite(): true\n");
        else System.out.println("testAddFavorite(): false\n");
    }

    @Test
    public void testDeleteFavorite() {
        List<String> favor = new ArrayList<>();
        Favorites favorites = new Favorites(favor);
        favorites.addFavorite("carpentras");
        favorites.deleteFavorite("carpentras");
        if (favorites.isPresent("carpentras")) System.out.println("testDeleteFavorite: false\n");
        else System.out.println("testDeleteFavorite: true\n");
    }

    @Test
    public void testSwapIndex() {
        List<String> favor = new ArrayList<>();
        Favorites favorites = new Favorites(favor);
        favorites.addFavorite("carpentras");
        favorites.addFavorite("paris");
        favorites.addFavorite("marseille");
        favorites.swapIndex(0, 1);
        if (favorites.getFavorite(0).equals("paris") && favorites.getFavorite(1).equals("carpentras")) {
            System.out.println("testSwapIndex: true\n");
        } else System.out.println("testSwapIndex: false\n");
    }

}

