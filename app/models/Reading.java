package models;

import javax.persistence.Entity;
import java.text.SimpleDateFormat;
import java.util.Date;

import play.db.jpa.Model;

@Entity
public class Reading extends Model {

  public long date;
  public int code;
  public double temperature;
  public double windSpeed;
  public int pressure;
  public double windDirection;

  public Reading(int code, double temperature, double windSpeed, int pressure, double windDirection) {
    this.date = System.currentTimeMillis();
    this.code = code;
    this.temperature = temperature;
    this.windSpeed = windSpeed;
    this.pressure = pressure;
    this.windDirection = windDirection;
  }

  public long getDate() {return this.date;}

  public int getCode(){return code;}
  public double getTemperature(){
    return temperature;
  }
  public double getWindSpeed(){
    return windSpeed;
  }
  public int getPressure(){
    return pressure;
  }
  public double getWindDirection() { return windDirection;}

}
