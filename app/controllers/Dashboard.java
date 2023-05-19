package controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller {
  public static void index() {
    Logger.info("Rendering Admin");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
    //The two lines bellow were copied from https://howtodoinjava.com/java/sort/java-sorting/ 1.3. Sorting with Lambda Expressions
    Comparator<Station> nameSorter = (a, b) -> a.getName().compareToIgnoreCase(b.getName());
    Collections.sort(stations, nameSorter);
    render("dashboard.html", stations);
  }

  public static void addStation(String name, double lat, double lng) {
    Logger.info("Adding a new playlist called " + name);
    Member member = Accounts.getLoggedInMember();
    Station station = new Station(name, lat, lng);
    member.stations.add(station);
    member.save();
    redirect("/dashboard");
  }

  public static void deleteStation(Long id) {
    Logger.info("Deleting a Station");
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    member.stations.remove(station);
    member.save();
    station.delete();
    redirect("/dashboard");
  }
}

