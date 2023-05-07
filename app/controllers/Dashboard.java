package controllers;

import java.util.List;

import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Admin");

    List<Station> stations = Station.findAll();
    render ("dashboard.html", stations);
  }
  public static void addStation (String name)
  {
    Station station = new Station (name);
    Logger.info ("Adding a new playlist called " + name);
    station.save();
    redirect ("/dashboard");
  }

}

