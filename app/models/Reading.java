package models;

import javax.persistence.Entity;
import java.text.SimpleDateFormat;
import java.util.Date;

//time advised and provided by Kieron Garvey
//link to the source provided by Kieron https://howtodoinjava.com/java/date-time/java8-datetimeformatter-example/

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import play.db.jpa.Model;
import utils.StationAnalytics;

@Entity
public class Reading extends Model {

  public String date;
  public int code;
  public double temperature;
  public double windSpeed;
  public double pressure;
  public double windDirection;

  public Reading(int code, double temperature, double windSpeed, double pressure, double windDirection) {
//    two lines of code bellow by Kieron Garvey
    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //Create DateTimeFormatter
    this.date = FORMATTER.format(LocalDateTime.now()); //Get Current Date Time & Set formatted String
    this.code = code;
    this.temperature = temperature;
    this.windSpeed = windSpeed;
    this.pressure = pressure;
    this.windDirection = windDirection;
  }

  public String getDate() {return this.date;}

  public int getCode(){return code;}
  public double getTemperature(){
    return temperature;
  }
  public double getWindSpeed(){
    return windSpeed;
  }
  public double getPressure(){
    return pressure;
  }
  public double getWindDirection() { return windDirection;}

}
