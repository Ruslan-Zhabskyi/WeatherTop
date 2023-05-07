package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Reading extends Model {
  public int code;
  public double temperature;
  public double windSpeed;
  public int pressure;
  public double windDirection;

  public Reading(int code, double temperature, double windSpeed, int pressure, double windDirection) {
    this.code = code;
    this.temperature = temperature;
    this.windSpeed = windSpeed;
    this.pressure = pressure;
    this.windDirection = windDirection;
  }
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