package app.appmeteo;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class OneCallData {
    private int morningTemp;
    private int eveningTemp;
    private int idWeather;
    private String weatherDescriptionDay;
    private String day;

    private int idWeatherHourly;
    private String weatherDescriptionHourly;
    private int hourlyTemp;
    private int hours;

    public OneCallData (){}

    public void oneCallDailyData(int numberOfDays, DataFetcher fetcher) throws IOException,IndexOutOfBoundsException {
        String line = "";
        Scanner scanner = new Scanner(fetcher.getOneCallDailyURL().openStream());
        while (scanner.hasNext()) {
            line += scanner.nextLine();
        }
        scanner.close();
        JsonParser parser = new JsonParser();
        JsonObject mainJsonObject = (JsonObject) parser.parse(line);
        JsonArray mainObjectAsArray = mainJsonObject.getAsJsonArray("daily");
        JsonObject arrayIndex = (JsonObject) mainObjectAsArray.get(numberOfDays);
        long dataTime = arrayIndex.get("dt").getAsLong();
        JsonObject weather = (JsonObject) arrayIndex.get("temp");
        JsonElement mornTemp = weather.get("morn");
        JsonElement eveTemp = weather.get("eve");
        JsonArray weatherArray = arrayIndex.getAsJsonArray("weather");
        JsonObject description = (JsonObject) weatherArray.get(0);
        this.day = nameOfDay(TimeToDay(dataTime));
        this.morningTemp= mornTemp.getAsInt();
        this.eveningTemp= eveTemp.getAsInt();
        this.idWeather=description.get("id").getAsInt();
        this.weatherDescriptionDay = description.get("description").toString();
    }

    public static int TimeToDay(long yourdate){
        Date date = new Date(yourdate*1000);
        return date.getDay();
    }
    public static int TimeToHours(long yourdate){
        Date date = new Date(yourdate*1000);
        return date.getHours();
    }

    public String nameOfDay(int number){
        if (number==0){
            return "Dimanche";
        }
        else if(number==1){
            return "Lundi";
        }
        else if(number==2){
            return "Mardi";
        }
        else if(number==3){
            return "Mercredi";
        }
        else if(number==4){
            return "Jeudi";
        }
        else if(number==5){
            return "Vendredi";
        }
        else if(number==6){
            return "Samedi";
        }
        return "erreur";
    }

    public void oneCallHourlyData(int numberOfHours, DataFetcher fetcher) throws IOException {
        String line = "";
        Scanner scanner = new Scanner(fetcher.getOneCallHourelyURL().openStream());
        while (scanner.hasNext()){
            line += scanner.nextLine();
        }
        scanner.close();
        JsonParser parser = new JsonParser();
        JsonObject mainJsonObject = (JsonObject) parser.parse(line);
        JsonArray mainObjectAsArray = mainJsonObject.getAsJsonArray("hourly");
        JsonObject arrayIndex = (JsonObject) mainObjectAsArray.get(numberOfHours);
        this.hourlyTemp = arrayIndex.get("temp").getAsInt();
        long dataTime = arrayIndex.get("dt").getAsLong();
        JsonArray weather = arrayIndex.getAsJsonArray("weather");
        JsonObject weatherObject = (JsonObject) weather.get(0);
        JsonElement value = weatherObject.get("id");
        this.hours=TimeToHours(dataTime);
        this.idWeatherHourly = value.getAsInt();
        this.weatherDescriptionHourly = weatherObject.get("description").toString();
    }

    public int getIdWeather() {
        return idWeather;
    }

    public int getEveningTemp() {
        return eveningTemp;
    }

    public int getMorningTemp() {
        return morningTemp;
    }

    public String getDay() {
        return day;
    }

    public int getHourlyTemp() {
        return hourlyTemp;
    }

    public int getHours() {
        return hours;
    }

    public int getIdWeatherHourly() {
        return idWeatherHourly;
    }

    public void printDaily(){
        System.out.println("\n"+ this.day);
        System.out.println("Matin :  " + this.morningTemp + "°C");
        System.out.println("Après-midi : " + this.eveningTemp + "°C");
        System.out.println("Description : " + this.weatherDescriptionDay + "\n");
    }
    public void printHourly(){
        System.out.println("\n"+ this.hours + "h");
        System.out.println("Température :  " + this.hourlyTemp + "°C");
        System.out.println("Description : " + this.weatherDescriptionHourly + "\n");
    }
}
