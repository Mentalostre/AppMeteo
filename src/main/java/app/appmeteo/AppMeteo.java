package app.appmeteo;

import app.appmeteo.controller.AppMeteoController;
import app.appmeteo.controller.FavouriteController;
import app.appmeteo.controller.WeatherDataController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;


public class AppMeteo extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            URL url = getClass().getClassLoader().getResource("app/appmeteo/view/appmeteo.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            AppMeteoController appMeteoController = new AppMeteoController();
            WeatherDataController weatherDataController = new WeatherDataController();
            FavouriteController favouriteController = new FavouriteController();
            weatherDataController.addController(appMeteoController);
            weatherDataController.addController(favouriteController);
            favouriteController.addAppMeteoController(appMeteoController);
            favouriteController.addWeatherDataController(weatherDataController);
            fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> param) {
                    if (param.equals(AppMeteoController.class)) {
                        return appMeteoController;
                    } else if (param.equals(WeatherDataController.class)) {
                        return weatherDataController;
                    } else if (param.equals(FavouriteController.class)) {
                        return favouriteController;
                    }
                    return null;
                }
            });
            AnchorPane root = fxmlLoader.load();
            primaryStage.setTitle("AppMeteo");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            primaryStage.setResizable(false);
            appMeteoController.setWeatherDataController(weatherDataController);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
