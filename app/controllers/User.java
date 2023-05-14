package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class User extends Controller {
  public static void index() {
    Member member = Accounts.getLoggedInMember();
    Logger.info("Rendering user");
    render("user.html", member);
  }
}

