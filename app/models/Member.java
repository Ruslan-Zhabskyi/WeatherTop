package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.*;

@Entity
public class Member extends Model {
  public String firstname;
  public String lastname;
  public String email;
  public String password;

  @OneToMany(cascade = CascadeType.ALL)

  public List<Station> stations = new ArrayList<Station>();

  public Member(String firstname, String lastname, String email, String password) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.password = password;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public static Member findByEmail(String email) {
    return find("email", email).first();
  }

  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }
}
