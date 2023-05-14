package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

import utils.StationAnalytics;

@Entity
public class Station extends Model {
  public String name;
  public double lat;
  public double lng;
  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();

  public Station(String name, double lat, double lng) {

    this.name = name;
    this.lat = lat;
    this.lng = lng;
  }

  public String getName() {
    return name;
  }

  public double getLat() {
    return lat;
  }

  public double getLng() {
    return lng;
  }

  /*
   ** Trends bespoke methods
   */
  public String recentTrendTemperature() {
    if (readings.size() > 2) {
      if ((readings.get(readings.size() - 1).getTemperature()) > readings.get(readings.size() - 2).getTemperature()
          && (readings.get(readings.size() - 2).getTemperature()) > readings.get(readings.size() - 3).getTemperature()) {
        return "Increasing";
      } else if ((readings.get(readings.size() - 1).getTemperature()) < readings.get(readings.size() - 2).getTemperature()
          && (readings.get(readings.size() - 2).getTemperature()) < readings.get(readings.size() - 3).getTemperature()) {
        return "Decreasing";
      } else return "Steady";
    } else
      return null;
  }

  public String recentTrendWindSpeed() {
    if (readings.size() > 2) {
      if ((readings.get(readings.size() - 1).getWindSpeed()) > readings.get(readings.size() - 2).getWindSpeed()
          && (readings.get(readings.size() - 2).getWindSpeed()) > readings.get(readings.size() - 3).getWindSpeed()) {
        return "Increasing";
      } else if ((readings.get(readings.size() - 1).getWindSpeed()) < readings.get(readings.size() - 2).getWindSpeed()
          && (readings.get(readings.size() - 2).getWindSpeed()) < readings.get(readings.size() - 3).getWindSpeed()) {
        return "Decreasing";
      } else return "Steady";
    } else
      return null;
  }

  public String recentTrendPressure() {
    if (readings.size() > 2) {
      if ((readings.get(readings.size() - 1).getPressure()) > readings.get(readings.size() - 2).getPressure()
          && (readings.get(readings.size() - 2).getPressure()) > readings.get(readings.size() - 3).getPressure()) {
        return "Increasing";
      } else if ((readings.get(readings.size() - 1).getPressure()) < readings.get(readings.size() - 2).getPressure()
          && (readings.get(readings.size() - 2).getPressure()) < readings.get(readings.size() - 3).getPressure()) {
        return "Decreasing";
      } else return "Steady";
    } else
      return null;
  }

  /*
   ** Recent data bespoke methods
   */
  public String recentWindDirection() {
    if (readings.size() != 0) {
      Reading recentWindDirection = readings.get(readings.size() - 1);
      return StationAnalytics.windDirectionCompassConversion(recentWindDirection.getWindDirection());
    } else
      return null;
  }

  public double recentWindFeelsLike() {
    return StationAnalytics.windChillCalculator(recentTemperatureC(), recentWindSpeed());
  }

  public int recentCode() {
    if (readings.size() != 0) {
      Reading recentCode = readings.get(readings.size() - 1);
      return recentCode.getCode();
    } else
      return 0;
  }

  public String recentCodeConverted() {
    return StationAnalytics.weatherCodes(recentCode());
  }

  public double recentTemperatureC() {
    if (readings.size() != 0) {
      Reading recentTemperature = readings.get(readings.size() - 1);
      return recentTemperature.getTemperature();
    } else
      return 0;
  }

  public double recentTemperatureF() {
    if (readings.size() != 0) {
      return StationAnalytics.celsiusToFahrenheit(recentTemperatureC());
    } else
      return 0;
  }

  public double recentWindSpeed() {
    if (readings.size() != 0) {
      Reading recentWindSpeed = readings.get(readings.size() - 1);
      return recentWindSpeed.getWindSpeed();
    } else
      return 0;
  }

  public String recentWindSpeedBeaufort() {
    return StationAnalytics.windSpeedBeaufortConversion(recentWindSpeed());
  }

  public String recentWindSpeedBeaufortLabel() {
    return StationAnalytics.windSpeedBeaufortConversionLabel(recentWindSpeed());
  }

  public double recentPressure() {
    if (readings.size() != 0) {
      Reading recentPressure = readings.get(readings.size() - 1);
      return recentPressure.getPressure();
    } else
      return 0;
  }

  /*
   ** Methods to display min and max value
   */
  public double lowestPressure() {
    if (readings.size() != 0) {
      double lowestPressure = readings.get(0).getPressure();
      for (int i = 1; i < readings.size(); i++) {
        if (readings.get(i).getPressure() < lowestPressure) {
          lowestPressure = readings.get(i).getPressure();
        }
      }
      return lowestPressure;
    } else
      return 0;
  }

  public double highestPressure() {
    if (readings.size() != 0) {
      double highestPressure = readings.get(0).getPressure();
      for (int i = 1; i < readings.size(); i++) {
        if (readings.get(i).getPressure() > highestPressure) {
          highestPressure = readings.get(i).getPressure();
        }
      }
      return highestPressure;
    } else
      return 0;
  }

  public double lowestWindSpeed() {
    if (readings.size() != 0) {
      double lowestWindSpeed = readings.get(0).getWindSpeed();
      for (int i = 1; i < readings.size(); i++) {
        if (readings.get(i).getWindSpeed() < lowestWindSpeed) {
          lowestWindSpeed = readings.get(i).getWindSpeed();
        }
      }
      return lowestWindSpeed;
    } else
      return 0;
  }

  public double highestWindSpeed() {
    if (readings.size() != 0) {
      double highestWindSpeed = readings.get(0).getWindSpeed();
      for (int i = 1; i < readings.size(); i++) {
        if (readings.get(i).getWindSpeed() > highestWindSpeed) {
          highestWindSpeed = readings.get(i).getWindSpeed();
        }
      }
      return highestWindSpeed;
    } else
      return 0;
  }

  public double lowestTemperatureC() {
    if (readings.size() != 0) {
      double lowestTemperature = readings.get(0).getTemperature();
      for (int i = 1; i < readings.size(); i++) {
        if (readings.get(i).getTemperature() < lowestTemperature) {
          lowestTemperature = readings.get(i).getTemperature();
        }
      }
      return lowestTemperature;
    } else
      return 0;
  }

  public double highestTemperatureC() {
    if (readings.size() != 0) {
      double highestTemperature = readings.get(0).getTemperature();
      for (int i = 1; i < readings.size(); i++) {
        if (readings.get(i).getTemperature() > highestTemperature) {
          highestTemperature = readings.get(i).getTemperature();
        }
      }
      return highestTemperature;
    } else
      return 0;
  }
}
