package app.appmeteo.controller;

import app.appmeteo.Data;
import app.appmeteo.DataFetcher;
import app.appmeteo.Favorites;
import app.appmeteo.OneCallData;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class WeatherDataController implements Initializable {

    @FXML public Label cityID;
    @FXML public Label averageTempID;
    @FXML public Label maxTempID;
    @FXML public Label minTempID;
    @FXML public Label windSpeedID;
    @FXML public Label humidityID;
    @FXML public Label feelsLikeID;
    @FXML public Label conditionID;
    @FXML public Label dateID;
    @FXML public ImageView logoWeatherID;

    private AppMeteoController appMeteoController;
    private FavouriteController favouriteController;

    public Favorites favorites = new Favorites();

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        String format = "dd/MM/yy";

        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
        java.util.Date date = new java.util.Date();
        dateID.setText(formater.format(date));
    }

    public void updateLabel(DataFetcher dataFetcher, Data data) throws UnsupportedEncodingException {
        cityID.setText(dataFetcher.getCityNameCap());
        averageTempID.setText(data.getTemp() + "\u00b0");
        maxTempID.setText(data.getMaxTemp() + "\u00b0");
        minTempID.setText(data.getMinTemp() + "\u00b0");
        windSpeedID.setText(data.getWindSpeed());
        humidityID.setText(data.getHumidity() + "%");
        feelsLikeID.setText(data.getFeelLike() + "\u00b0");
        String description = data.getDescription();
        description = description.replaceAll("\"","");
        description = new java.lang.String(description.getBytes(), StandardCharsets.UTF_8);
        String condition = String.valueOf(description.charAt(0));
        condition = condition.toUpperCase();
        condition += description.substring(1);
        conditionID.setText(condition);
        imgChoice(data.getIdWeather());

    }


    private void imgChoice(int x) {
        logoWeatherID.setImage(imageChoice(x));
    }

    public Image imageChoice(int x) {
        if (x == 800) {
            return new Image("app/appmeteo/view/imageFXML/icons8-soleil-80.png");
        }
        else if (x > 800 && x < 805) {
            return new Image("app/appmeteo/view/imageFXML/icons8-pluie-legere-80 2.png");
        }
        else if (x > 700 && x < 782) {
            return new Image("app/appmeteo/view/imageFXML/icons8-fog-96.png");
        }
        else if (x >= 600 && x <= 622) {
            return new Image("app/appmeteo/view/imageFXML/icons8-neige-80.png");
        }
        else if (x >= 500 && x <= 531) {
            return new Image("app/appmeteo/view/imageFXML/icons8-pluie-80.png");
        }
        else if (x >= 300 && x <= 321) {
            return new Image("app/appmeteo/view/imageFXML/icons8-pluie-legere-80.png");
        }
        else if (x >= 200 && x <= 232) {
            return new Image("app/appmeteo/view/imageFXML/icons8-orage-80.png");
        }
        return null;
    }

    public void addController(AppMeteoController appMeteoController) {
        this.appMeteoController = appMeteoController;
    }

    public void addController(FavouriteController favouriteController) {
        this.favouriteController = favouriteController;
    }


    @FXML
    public void addFavourite(ActionEvent event) throws IOException, InterruptedException {
        appMeteoController.maxNumFavID.setText("");
        DataFetcher dataFetcher = new DataFetcher(cityID.getText());
        Data data = new Data(dataFetcher);
        if (favorites.favoriteLen() < 4 && !favorites.isPresent(cityID.getText())) {
            favorites.addFavorite(cityID.getText());
            if (favorites.favoriteLen() == 1) {
                appMeteoController.favouritePan1ID.setVisible(true);
                appMeteoController.line1.setVisible(true);
                favouriteController.update1(cityID.getText(), imageChoice(data.getIdWeather()), data.getTemp() + "\u00b0");
                }
            else if (favorites.favoriteLen() == 2) {
                appMeteoController.favouritePan2ID.setVisible(true);
                appMeteoController.line2.setVisible(true);
                favouriteController.update2(cityID.getText(), imageChoice(data.getIdWeather()), data.getTemp() + "\u00b0");
            }
            else if (favorites.favoriteLen() == 3) {
                appMeteoController.favouritePan3ID.setVisible(true);
                appMeteoController.line3.setVisible(true);
                favouriteController.update3(cityID.getText(), imageChoice(data.getIdWeather()), data.getTemp() + "\u00b0");
            }
            else if (favorites.favoriteLen() == 4) {
                appMeteoController.favouritePan4ID.setVisible(true);
                favouriteController.update4(cityID.getText(), imageChoice(data.getIdWeather()), data.getTemp() + "\u00b0");
            }
        }
        else if (favorites.isPresent(cityID.getText())){
            appMeteoController.maxNumFavID.setText("Cette ville est d\u00e9j\u00e0 pr\u00e9sente dans les favoris");
        }
        else if (favorites.favoriteLen() >= 4){
            appMeteoController.maxNumFavID.setText("Vous avez atteint le nombre maximum de favoris");
        }

    }

    @FXML public ImageView image1Hour;
    @FXML public ImageView image2Hour;
    @FXML public ImageView image3Hour;
    @FXML public ImageView image4Hour;
    @FXML public ImageView image5Hour;
    @FXML public ImageView image6Hour;
    @FXML public ImageView image7Hour;
    @FXML public ImageView image8Hour;

    @FXML public Label hour1;
    @FXML public Label hour2;
    @FXML public Label hour3;
    @FXML public Label hour4;
    @FXML public Label hour5;
    @FXML public Label hour6;
    @FXML public Label hour7;
    @FXML public Label hour8;

    @FXML public Label temp1;
    @FXML public Label temp2;
    @FXML public Label temp3;
    @FXML public Label temp4;
    @FXML public Label temp5;
    @FXML public Label temp6;
    @FXML public Label temp7;
    @FXML public Label temp8;

    public void updateHourly(Data data, DataFetcher dataFetcher) throws IOException {
        dataFetcher.setLatitudeNumber(data.getLatitude());
        dataFetcher.setLongitudeNumber(data.getLongitude());
        OneCallData oneCallData = new OneCallData();
        oneCallData.oneCallHourlyData(1, dataFetcher);
        hour1.setText(oneCallData.getHours() + "h");
        temp1.setText(oneCallData.getHourlyTemp() + "\u00B0");
        image1Hour.setImage(imageChoice(oneCallData.getIdWeatherHourly()));
        oneCallData.oneCallHourlyData(2, dataFetcher);
        hour2.setText(oneCallData.getHours() + "h");
        temp2.setText(oneCallData.getHourlyTemp() + "\u00B0");
        image2Hour.setImage(imageChoice(oneCallData.getIdWeatherHourly()));
        oneCallData.oneCallHourlyData(3, dataFetcher);
        hour3.setText(oneCallData.getHours() + "h");
        temp3.setText(oneCallData.getHourlyTemp() + "\u00B0");
        image3Hour.setImage(imageChoice(oneCallData.getIdWeatherHourly()));
        oneCallData.oneCallHourlyData(4, dataFetcher);
        hour4.setText(oneCallData.getHours() + "h");
        temp4.setText(oneCallData.getHourlyTemp() + "\u00B0");
        image4Hour.setImage(imageChoice(oneCallData.getIdWeatherHourly()));
        oneCallData.oneCallHourlyData(5, dataFetcher);
        hour5.setText(oneCallData.getHours() + "h");
        temp5.setText(oneCallData.getHourlyTemp() + "\u00B0");
        image5Hour.setImage(imageChoice(oneCallData.getIdWeatherHourly()));
        oneCallData.oneCallHourlyData(6, dataFetcher);
        hour6.setText(oneCallData.getHours() + "h");
        temp6.setText(oneCallData.getHourlyTemp() + "\u00B0");
        image6Hour.setImage(imageChoice(oneCallData.getIdWeatherHourly()));
        oneCallData.oneCallHourlyData(7, dataFetcher);
        hour7.setText(oneCallData.getHours() + "h");
        temp7.setText(oneCallData.getHourlyTemp() + "\u00B0");
        image7Hour.setImage(imageChoice(oneCallData.getIdWeatherHourly()));
        oneCallData.oneCallHourlyData(8, dataFetcher);
        hour8.setText(oneCallData.getHours() + "h");
        temp8.setText(oneCallData.getHourlyTemp() + "\u00B0");
        image8Hour.setImage(imageChoice(oneCallData.getIdWeatherHourly()));
    }

}
