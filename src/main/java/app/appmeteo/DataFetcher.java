package app.appmeteo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Locale;

public class DataFetcher {
    private String key = "23f141883b30f694d69fb4c958a9f3ce";
    private String prefix = "api.openweathermap.org/data/2.5/weather?q=";
    private String suffix = "&appid=";
    private String cityName;
    private String protocol = "https://";
    private String lang = "&lang=fr";
    private String units = "&units=metric";
    private String forecastPrefix = "api.openweathermap.org/data/2.5/forecast?q=";
    private String forecastCnt = "&cnt=";
    private String oneCallPrefix = "api.openweathermap.org/data/2.5/onecall?";
    private String latitude = "lat=";
    private double latitudeNumber;
    private String longitude = "&lon=";
    private double longitudeNumber;
    private String hourelyExclusion = "&exclude=current,minutely,daily,alerts";
    private String dailyExclusion = "&exclude=current,minutely,hourly,alerts";
    private int numberOfDays;



    public DataFetcher(String city){
        this.cityName = city;
    }

    public DataFetcher(String city, int numberOfDays){
        this.cityName = city;
        this.numberOfDays = numberOfDays;
    }

    public URL prepareQuery() throws MalformedURLException {
        return new URL(protocol + prefix + this.cityName + units + suffix + key + lang);
    }

    public URL prepareForecastQuery() throws MalformedURLException{
        return new URL(protocol + forecastPrefix + this.cityName + units + forecastCnt + suffix + key + lang);
    }

    public URL prepareOneCallHourlyQuery()throws MalformedURLException{
        return new URL(protocol + oneCallPrefix + latitude + latitudeNumber + longitude + longitudeNumber + units + hourelyExclusion + suffix + key + lang);
    }

    public URL prepareOneCallDailyQuery() throws MalformedURLException{
        return new URL(protocol + oneCallPrefix + latitude + latitudeNumber + longitude + longitudeNumber + units + dailyExclusion + suffix + key + lang);

    }

    public String getCityName(){
        return this.cityName;
    }

    public String getCityNameCap() { return this.cityName.toUpperCase(Locale.ROOT);}

    public void setLongitudeNumber(double longitudeNumber) {
        this.longitudeNumber = longitudeNumber;
    }

    public URL getUrl() throws MalformedURLException {
        return prepareQuery();
    }

    public URL getOneCallHourelyURL() throws MalformedURLException {
        return prepareOneCallHourlyQuery();
    }

    public URL getOneCallDailyURL() throws MalformedURLException {
        return prepareOneCallDailyQuery();
    }

    public void setLatitudeNumber(Double latitude) {
        this.latitudeNumber = latitude;
    }



    public void geocodingQuery(){
        try {
            URL url = prepareQuery();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int reponsecode = conn.getResponseCode();

            if (reponsecode != 200){
                System.out.println("This city does not exist, please check its spelling.");
                throw new RuntimeException("HttpResponseCode: " + reponsecode);
            }
            else {
                Data data = new Data();
                data.geocoding(this);
            }

        }
        catch (Exception e){

        }
    }

    public void nowQuery(){
        try {
            URL url = prepareQuery();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int reponsecode = conn.getResponseCode();

            if (reponsecode != 200){
                System.out.println("This city does not exist, please check its spelling.");
            }
            else {
                Data data = new Data();
                data.nowData(this);
                data.printData();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void oneCallHourlyQuery() throws IOException {

            URL url = prepareOneCallHourlyQuery();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int reponsecode = conn.getResponseCode();

            if (reponsecode != 200){
                System.out.println("This city does not exist, please check its spelling.");
                throw new RuntimeException("HttpResponseCode: " + reponsecode);
            }
            else {
                Data data = new Data(this);
                this.latitudeNumber=data.getLatitude();
                this.longitudeNumber=data.getLongitude();
                OneCallData oneCallData = new OneCallData();
                oneCallData.oneCallHourlyData(numberOfDays,this);
                oneCallData.printHourly();
            }

        }



    public void oneCallDailyQuery() throws IOException {

            URL url = prepareOneCallDailyQuery();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int reponsecode = conn.getResponseCode();

            if (reponsecode != 200){
                System.out.println("This city does not exist, please check its spelling.");
                throw new RuntimeException("HttpResponseCode: " + reponsecode);
            }
            else {
                Data data = new Data(this);
                this.latitudeNumber=data.getLatitude();
                this.longitudeNumber=data.getLongitude();
                OneCallData oneCallData = new OneCallData();
                oneCallData.oneCallDailyData(numberOfDays, this);
                oneCallData.printDaily();
            }

        }

    }
