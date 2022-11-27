package app.appmeteo;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class Data {
    private int temp;
    private String description;
    private int feelLike;
    private int minTemp;
    private int maxTemp;
    private int humidity;
    private String windSpeed;
    private String date;
    private double longitude;
    private double latitude;
    private int idWeather;


    public Data(DataFetcher dataFetcher) throws IOException {
        nowData(dataFetcher);
    }

    public Data() {}




    public double getLongitude() {
        return longitude;
    }

    public double getLatitude(){
        return latitude;
    }

    public static long dateToTimeStamp(String yourDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        Date date = format.parse(yourDate);
        return date.getTime()/1000;
    }

    public static String TimeStampToDate(long yourDate){
        Date date = new Date(yourDate*1000);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return format.format(date);
    }

    public static String TimeStampToDateSimple(long yourDate){
        Date date = new Date(yourDate*1000);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(date);
    }

    public static String StringToDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date convertedDate = formatter.parse(date);
        SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String finaleDateString = newFormat.format(convertedDate);
        return finaleDateString;
    }


    public void geocoding(DataFetcher fetcher) throws IOException {
        String line = "";
        Scanner scanner = new Scanner(fetcher.getUrl().openStream());
        while (scanner.hasNext()) {
            line += scanner.nextLine();
        }
        scanner.close();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(line);
        JsonObject geoObject = (JsonObject) jsonObject.get("coord");
        this.longitude = geoObject.get("lon").getAsDouble();
        this.latitude = geoObject.get("lat").getAsDouble();
    }

    public void nowData(DataFetcher fetcher) throws IOException {
        String line = "";
        long dataTime;
        Scanner scanner = new Scanner(fetcher.getUrl().openStream());
        while (scanner.hasNext()) {
            line += scanner.nextLine();
        }
        scanner.close();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(line);
        JsonObject mainObject = (JsonObject) jsonObject.get("main");
        JsonObject windObject = (JsonObject) jsonObject.get("wind");
        JsonObject geoObject = (JsonObject) jsonObject.get("coord");
        JsonArray jsonArray = jsonObject.getAsJsonArray("weather");
        this.temp = mainObject.get("temp").getAsInt();
        this.feelLike = mainObject.get("feels_like").getAsInt();
        this.minTemp = mainObject.get("temp_min" ).getAsInt();
        this.maxTemp = mainObject.get("temp_max").getAsInt();
        this.humidity = mainObject.get("humidity").getAsInt();
        this.windSpeed = windObject.get("speed") + " km/h";
        this.longitude = geoObject.get("lon").getAsDouble();
        this.latitude = geoObject.get("lat").getAsDouble();
        dataTime = jsonObject.get("dt").getAsLong();
        this.date = TimeStampToDate(dataTime);
        JsonObject descriptionJson = (JsonObject) jsonArray.get(0);
        this.description = descriptionJson.get("description").toString();
        idWeather = descriptionJson.get("id").getAsInt();
    }

    /*public void forecastData(int numberOfDays, DataFetcher fetcher) throws IOException, ParseException {
        long dataTime;
        long queryTime = System.currentTimeMillis();
        String line = "";
        Scanner scanner = new Scanner(fetcher.getForecastUrl().openStream());
        while (scanner.hasNext()) {
            line += scanner.nextLine();
        }
        scanner.close();
        JsonParser parser = new JsonParser();
        JsonObject mainJsonObject = (JsonObject) parser.parse(line);
        JsonArray mainObjectAsArray = mainJsonObject.getAsJsonArray("list");
        for (int i = 0; i < mainObjectAsArray.size(); i++) {
            JsonObject arrayIndex = (JsonObject) mainObjectAsArray.get(i);
            String dataText = arrayIndex.get("dt_txt").getAsString();
            JsonArray weather = arrayIndex.getAsJsonArray("weather");
            JsonObject weatherObject = (JsonObject) weather.get(0);
            JsonObject mainObject = (JsonObject) arrayIndex.get("main");
            JsonObject windObject = (JsonObject) arrayIndex.get("wind");
            this.temp = mainObject.get("temp") + "°C";
            this.feelLike = mainObject.get("feels_like") + "°C";
            this.minTemp = mainObject.get("temp_min") + "°C";
            this.maxTemp = mainObject.get("temp_max") + "°C";
            this.humidity = mainObject.get("humidity") + "%";
            this.windSpeed = "" + windObject.get("speed") + " m/s";
            this.description = "" + weatherObject.get("description");
            this.date = StringToDate(dataText);
            printData();
        }
    }*/

    public String getDescription() {
        return description;
    }

    public int getFeelLike() {
        return feelLike;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public int getTemp() {
        return temp;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public int getIdWeather() {
        return idWeather;
    }

    public void printData(){
        System.out.println("\n"+ this.date);
        System.out.println("Température : " + this.temp + "°C");
        System.out.println("Ressenti  : " + this.feelLike + "°C");
        System.out.println("Température minimale : " + this.minTemp + "°C");
        System.out.println("Température maximale : " + this.maxTemp + "°C");
        System.out.println("Humidité :  " + this.humidity + "%");
        System.out.println("Vitesse : " + this.windSpeed);
        System.out.println("Description : " + this.description + "\n");
    }
}
