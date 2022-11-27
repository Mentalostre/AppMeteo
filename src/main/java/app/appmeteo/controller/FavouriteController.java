package app.appmeteo.controller;

import app.appmeteo.Data;
import app.appmeteo.DataFetcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FavouriteController implements Initializable {

    @FXML public Button buttonFav1;
    @FXML public Label nameFav1;
    @FXML public ImageView imgFav1;
    @FXML public Label tempFav1;

    @FXML public Button buttonFav2;
    @FXML public Label nameFav2;
    @FXML public ImageView imgFav2;
    @FXML public Label tempFav2;

    @FXML public Button buttonFav3;
    @FXML public Label nameFav3;
    @FXML public ImageView imgFav3;
    @FXML public Label tempFav3;

    @FXML public Button buttonFav4;
    @FXML public Label nameFav4;
    @FXML public ImageView imgFav4;
    @FXML public Label tempFav4;

    AppMeteoController appMeteoController;
    WeatherDataController weatherDataController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addWeatherDataController(WeatherDataController weatherDataController) {
        this.weatherDataController = weatherDataController;
    }

    public void addAppMeteoController(AppMeteoController appMeteoController) {
        this.appMeteoController = appMeteoController;
    }

    public void update1(String name, Image img, String temp) {
        this.nameFav1.setText(name);
        this.imgFav1.setImage(img);
        this.tempFav1.setText(temp);
    }

    public void update2(String name, Image img, String temp) {
        this.nameFav2.setText(name);
        this.imgFav2.setImage(img);
        this.tempFav2.setText(temp);
    }

    public void update3(String name, Image img, String temp) {
        this.nameFav3.setText(name);
        this.imgFav3.setImage(img);
        this.tempFav3.setText(temp);
    }

    public void update4(String name, Image img, String temp) {
        this.nameFav4.setText(name);
        this.imgFav4.setImage(img);
        this.tempFav4.setText(temp);
    }

    public void clickButton1(ActionEvent actionEvent) {
        if (weatherDataController.favorites.favoriteLen() > 0) {
            weatherDataController.favorites.deleteFavorite(0);
            if (weatherDataController.favorites.favoriteLen() == 0) {
                appMeteoController.favouritePan1ID.setVisible(false);
                appMeteoController.line1.setVisible(false);
            }
            else if (weatherDataController.favorites.favoriteLen() == 1) {
                nameFav1.setText(nameFav2.getText());
                imgFav1.setImage(imgFav2.getImage());
                tempFav1.setText(tempFav2.getText());
                appMeteoController.favouritePan2ID.setVisible(false);
                appMeteoController.line2.setVisible(false);
            }
            else if (weatherDataController.favorites.favoriteLen() == 2) {
                nameFav1.setText(nameFav2.getText());
                imgFav1.setImage(imgFav2.getImage());
                tempFav1.setText(tempFav2.getText());
                nameFav2.setText(nameFav3.getText());
                imgFav2.setImage(imgFav3.getImage());
                tempFav2.setText(tempFav3.getText());
                appMeteoController.favouritePan3ID.setVisible(false);
                appMeteoController.line3.setVisible(false);
            }
            else if (weatherDataController.favorites.favoriteLen() == 3) {
                nameFav1.setText(nameFav2.getText());
                imgFav1.setImage(imgFav2.getImage());
                tempFav1.setText(tempFav2.getText());
                nameFav2.setText(nameFav3.getText());
                imgFav2.setImage(imgFav3.getImage());
                tempFav2.setText(tempFav3.getText());
                nameFav3.setText(nameFav4.getText());
                imgFav3.setImage(imgFav4.getImage());
                tempFav3.setText(tempFav4.getText());
                appMeteoController.favouritePan4ID.setVisible(false);
            }
        }
    }

    public void clickButton2(ActionEvent actionEvent) {
        if (weatherDataController.favorites.favoriteLen() > 0) {
            weatherDataController.favorites.deleteFavorite(1);
            if (weatherDataController.favorites.favoriteLen() == 1) {
                appMeteoController.favouritePan2ID.setVisible(false);
                appMeteoController.line2.setVisible(false);
            }
            else if (weatherDataController.favorites.favoriteLen() == 2) {
                nameFav2.setText(nameFav3.getText());
                imgFav2.setImage(imgFav3.getImage());
                tempFav2.setText(tempFav3.getText());
                appMeteoController.favouritePan3ID.setVisible(false);
                appMeteoController.line3.setVisible(false);
            }
            else if (weatherDataController.favorites.favoriteLen() == 3) {
                nameFav2.setText(nameFav3.getText());
                imgFav2.setImage(imgFav3.getImage());
                tempFav2.setText(tempFav3.getText());
                nameFav3.setText(nameFav4.getText());
                imgFav3.setImage(imgFav4.getImage());
                tempFav3.setText(tempFav4.getText());
                appMeteoController.favouritePan4ID.setVisible(false);
            }
        }
    }

    public void clickButton3(ActionEvent actionEvent) {
        if (weatherDataController.favorites.favoriteLen() > 0) {
            weatherDataController.favorites.deleteFavorite(2);
            if (weatherDataController.favorites.favoriteLen() == 2) {
                appMeteoController.favouritePan3ID.setVisible(false);
                appMeteoController.line3.setVisible(false);
            }
            else if (weatherDataController.favorites.favoriteLen() == 3) {
                nameFav3.setText(nameFav4.getText());
                imgFav3.setImage(imgFav4.getImage());
                tempFav3.setText(tempFav4.getText());
                appMeteoController.favouritePan4ID.setVisible(false);
            }
        }
    }

    public void clickButton4(ActionEvent actionEvent) {
        if (weatherDataController.favorites.favoriteLen() > 0) {
            weatherDataController.favorites.deleteFavorite(3);
            if (weatherDataController.favorites.favoriteLen() == 3) {
                appMeteoController.favouritePan4ID.setVisible(false);
            }
        }
    }

    public void showData1(ActionEvent actionEvent) throws IOException {
        DataFetcher dataFetcher = new DataFetcher(nameFav1.getText());
        Data data = new Data(dataFetcher);
        weatherDataController.updateLabel(dataFetcher, data);
        appMeteoController.updateForecast(data, dataFetcher);
        weatherDataController.updateHourly(data, dataFetcher);
    }

    public void showData2(ActionEvent actionEvent) throws IOException {
        DataFetcher dataFetcher = new DataFetcher(nameFav2.getText());
        Data data = new Data(dataFetcher);
        weatherDataController.updateLabel(dataFetcher, data);
        appMeteoController.updateForecast(data, dataFetcher);
        weatherDataController.updateHourly(data, dataFetcher);
    }

    public void showData3(ActionEvent actionEvent) throws IOException {
        DataFetcher dataFetcher = new DataFetcher(nameFav3.getText());
        Data data = new Data(dataFetcher);
        weatherDataController.updateLabel(dataFetcher, data);
        appMeteoController.updateForecast(data, dataFetcher);
        weatherDataController.updateHourly(data, dataFetcher);
    }

    public void showData4(ActionEvent actionEvent) throws IOException {
        DataFetcher dataFetcher = new DataFetcher(nameFav4.getText());
        Data data = new Data(dataFetcher);
        weatherDataController.updateLabel(dataFetcher, data);
        appMeteoController.updateForecast(data, dataFetcher);
        weatherDataController.updateHourly(data, dataFetcher);
    }
}
