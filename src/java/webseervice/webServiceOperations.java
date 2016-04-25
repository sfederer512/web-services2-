/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webseervice;

import datamodel.LocationGPS;
import datamodel.Users;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author Saeed
 */
@WebService(serviceName = "webServiceOperations")
public class webServiceOperations {

   

    
     ArrayList <Users> users ;
    @WebMethod(operationName = "operation")
    public String friendrel(@WebParam(name = "Id") int Id) throws IOException  {
      JSONObject object = null ;
  
         String info = "";
         try {
             parsing();
         } catch (JSONException ex) {
             Logger.getLogger(webServiceOperations.class.getName()).log(Level.SEVERE, null, ex);
         }
         
 
               Users m = new Users();
              m= users.get(Id-1);
             for(int s = 0 ; s< m.friends.size() ; s++){
            try {
                object = new JSONObject();
                
                object.put("Name",m.friends.get(s).getName());
                
                
                String name = m.friends.get(s).getName();
              
                info= info.concat("  "+(s+1) ).concat("- ").concat(name);
               
            } catch (JSONException ex) {
		Logger.getLogger(webServiceOperations.class.getName()).log(Level.SEVERE, null, ex);
	}
             }
            
        return info ;
    }
    
    private static JSONTokener jsonOut;

  public void parsing() throws JSONException, IOException {
                       users= new <Users> ArrayList();
                       
                           String h="/home/saeed/NetBeansProjects/assiment2/src/java/parsers/frinds.json";
 JSONObject req;
        req = new JSONObject(readFile(h));
      
         
         
      
        JSONArray jsonArray = req.getJSONArray("user");
                
        for(int i=0; i < jsonArray.length(); i++){
            JSONObject userObject = jsonArray.getJSONObject(i);
            Users user = new Users();
            user.setId(Integer.parseInt(userObject.getString("Id")));
            user.setName(userObject.getString("name"));
          
            user.setBorn(userObject.getString("born"));
            user.setNationality(userObject.getString("nationality"));
                     user.setTel(userObject.getString("tel"));

            
            String l = userObject.getString("location");
            String[] parts = l.split(",");
            String part1 = parts[0]; 
            String part2 = parts[1];
            LocationGPS location = new LocationGPS();
            location.setX(Double.parseDouble(part1));
            location.setY(Double.parseDouble(part2));
            
            location.setAddress(userObject.getString("address"));
            user.setLocation(location);
     
          
            users.add(user);
        }

       
       
                         
            
          String y = "/home/saeed/NetBeansProjects/assiment2/src/java/parsers/relation.json";
            JSONObject jsonRelations = new JSONObject (readFile(y));
        
               JSONArray jsonArray1 = jsonRelations.getJSONArray("relations");
                   
           
             
                
                 for(int i=0; i < jsonArray1.length(); i++){
                  JSONObject userObject = jsonArray1.getJSONObject(i);
                  int fiid, secid;
                  fiid= Integer.parseInt(userObject.getString("fiid"));
                  secid= Integer.parseInt(userObject.getString("secid"));
                  
                  users.get(secid-1).friends.add(users.get(fiid-1));
                  users.get(fiid-1).friends.add(users.get(secid-1));
                 }
                     } 
              

    private static String readFile(String file) throws IOException {
        
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }

        return stringBuilder.toString();
    }

         
          
    

    /**
     * Web service operation
     * @param Id
     * @throws java.io.IOException
     */
    @WebMethod(operationName = "getLocation")
    public String getLocation(@WebParam(name = "Id") int Id) throws IOException {
         String add;
         double x,y;
        if(users.size()>0){
             Users s = new Users();
        s=users.get(Id-1);
          LocationGPS l = new LocationGPS();
          l=s.getLocation();
           x = l.getX();
           y = l.getY();
        add  = l.getAddress();
        }else{
        try {
             parsing();
         } catch (JSONException ex) {
             Logger.getLogger(webServiceOperations.class.getName()).log(Level.SEVERE, null, ex);
         }
        Users s = new Users();
        s=users.get(Id-1);
          LocationGPS l = new LocationGPS();
          l=s.getLocation();
           x = l.getX();
           y = l.getY();
          add = l.getAddress();
        }
        return add+" "+x+" , "+y ;
    }
}
