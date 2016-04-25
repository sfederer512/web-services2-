/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saeed
 */
public class Users {
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saeed
 */
    private LocationGPS location;
    private int Id;
    private String Name;
    private String Born;
    private String Address;
    private String Nationality;
 private String Tel;
       public  List <Users> friends;
       
       public Users(){
        friends = new <Users> ArrayList();
        
    }

    public LocationGPS getLocation() {
        return location;
    }

    public void setLocation(LocationGPS location) {
        this.location = location;
    }

    public List<Users> getFriends() {
        return friends;
    }

    public void setFriends(List<Users> friends) {
        this.friends = friends;
    }

    
   
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getBorn() {
        return Born;
    }

    public void setBorn(String Born) {
        this.Born = Born;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String Nationality) {
        this.Nationality = Nationality;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

   @Override
    public String toString() {
        return "Users{" + "ID=" + Id + '}';
    }
    

   
}
