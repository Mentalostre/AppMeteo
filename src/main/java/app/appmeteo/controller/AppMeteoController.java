package app.appmeteo.controller;

import app.appmeteo.Data;
import app.appmeteo.DataFetcher;
import app.appmeteo.OneCallData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class AppMeteoController implements Initializable {

    @FXML public TextField searchBarID;
    @FXML public DatePicker datePickerID;
    @FXML public AnchorPane weatherPanID;
    @FXML public AnchorPane welcomePanID;
    @FXML public AnchorPane favouritePan1ID;
    @FXML public AnchorPane favouritePan2ID;
    @FXML public AnchorPane favouritePan3ID;
    @FXML public AnchorPane favouritePan4ID;
    @FXML public Label invalidCityID;
    @FXML public Line line1;
    @FXML public Line line2;
    @FXML public Line line3;
    @FXML public Label maxNumFavID;
    @FXML public Pane panForecastID;

    private WeatherDataController weatherDataController;


    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        System.out.println("L'application est en cours d'utilisation ...");
        String format = "dd/MM/yy";

        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
        java.util.Date date = new java.util.Date();

        datePickerID.setPromptText(formater.format(date));
        welcomePanID.setVisible(true);
        weatherPanID.setVisible(false);
        panForecastID.setVisible(false);
        favouritePan1ID.setVisible(false);
        line1.setVisible(false);
        favouritePan2ID.setVisible(false);
        line2.setVisible(false);
        favouritePan3ID.setVisible(false);
        line3.setVisible(false);
        favouritePan4ID.setVisible(false);

    }

    @FXML
    public void keyValidation(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            welcomePanID.setVisible(false);
            weatherPanID.setVisible(true);
            DataFetcher dataFetcher = new DataFetcher(searchBarID.getText());
            Data data = new Data(dataFetcher);
            weatherDataController.updateLabel(dataFetcher, data);
            maxNumFavID.setText("");
            panForecastID.setVisible(true);
            updateForecast(data, dataFetcher);
            weatherDataController.updateHourly(data, dataFetcher);
        }
    }


    public void setWeatherDataController(WeatherDataController weatherDataController) {
        this.weatherDataController = weatherDataController;
    }

    @FXML public Label matin1;
    @FXML public Label soir1;
    @FXML public Label matin2;
    @FXML public Label soir2;
    @FXML public Label matin3;
    @FXML public Label soir3;
    @FXML public Label matin4;
    @FXML public Label soir4;
    @FXML public Label matin5;
    @FXML public Label soir5;
    @FXML public Label jour1;
    @FXML public Label jour2;
    @FXML public Label jour3;
    @FXML public Label jour4;
    @FXML public Label jour5;
    @FXML public ImageView image1For;
    @FXML public ImageView image2For;
    @FXML public ImageView image3For;
    @FXML public ImageView image4For;
    @FXML public ImageView image5For;

    public void updateForecast(Data data, DataFetcher dataFetcher) throws IOException {
        dataFetcher.setLatitudeNumber(data.getLatitude());
        dataFetcher.setLongitudeNumber(data.getLongitude());
        OneCallData oneCallData = new OneCallData();
        oneCallData.oneCallDailyData(1, dataFetcher);
        matin1.setText(oneCallData.getMorningTemp() + "\u00B0");
        soir1.setText(oneCallData.getEveningTemp() + "\u00B0");
        jour1.setText(oneCallData.getDay());
        image1For.setImage(weatherDataController.imageChoice(oneCallData.getIdWeather()));
        oneCallData.oneCallDailyData(2, dataFetcher);
        matin2.setText(oneCallData.getMorningTemp() + "\u00B0");
        soir2.setText(oneCallData.getEveningTemp() + "\u00B0");
        jour2.setText(oneCallData.getDay());
        image2For.setImage(weatherDataController.imageChoice(oneCallData.getIdWeather()));
        oneCallData.oneCallDailyData(3, dataFetcher);
        matin3.setText(oneCallData.getMorningTemp() + "\u00B0");
        soir3.setText(oneCallData.getEveningTemp() + "\u00B0");
        jour3.setText(oneCallData.getDay());
        image3For.setImage(weatherDataController.imageChoice(oneCallData.getIdWeather()));
        oneCallData.oneCallDailyData(4, dataFetcher);
        matin4.setText(oneCallData.getMorningTemp() + "\u00B0");
        soir4.setText(oneCallData.getEveningTemp() + "\u00B0");
        jour4.setText(oneCallData.getDay());
        image4For.setImage(weatherDataController.imageChoice(oneCallData.getIdWeather()));
        oneCallData.oneCallDailyData(5, dataFetcher);
        matin5.setText(oneCallData.getMorningTemp() + "\u00B0");
        soir5.setText(oneCallData.getEveningTemp() + "\u00B0");
        jour5.setText(oneCallData.getDay());
        image5For.setImage(weatherDataController.imageChoice(oneCallData.getIdWeather()));
    }
}
