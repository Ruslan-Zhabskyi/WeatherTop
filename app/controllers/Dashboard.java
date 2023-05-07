package controllers;

import java.util.List;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Admin");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
    render ("dashboard.html", stations);
  }
  public static void addStation (String name)
  {
    Logger.info ("Adding a new playlist called " + name);
    Member member = Accounts.getLoggedInMember();
    Station station = new Station (name);
    member.stations.add(station);
    member.save();
    redirect ("/dashboard");
  }

}

