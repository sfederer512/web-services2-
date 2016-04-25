/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

/**
 *
 * @author saeed
 */

import datamodel.Users;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
//import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ParsingJson {

    private static JSONTokener jsonOut;

    public static void main(String myHelpers[]) throws JSONException, IOException {
                       
                File f = new File("/home/saeed/NetBeansProjects/assiment2/src/java/parsers/frinds.json");

                    String json = readFile(f.getPath());
                    jsonOut = new JSONTokener(json);
                    
         ArrayList <Users> users = new <Users> ArrayList();
         
         
         try {
        JSONObject output = new JSONObject(jsonOut);
       // JSONObject jsonRoot = new JSONObject(json);
        JSONArray jsonArray = output.getJSONArray("user");
                
        for(int i=0; i < jsonArray.length(); i++){
            JSONObject userObject = jsonArray.getJSONObject(i);
            Users user = new Users();
            user.setId(Integer.parseInt(userObject.getString("Id")));
           // user.setAddress(userObject.getString("address"));
            user.setBorn(userObject.getString("born"));
                user.setNationality(userObject.getString("nationality"));

            
            user.setName(userObject.getString("name"));
         
            user.setAddress(userObject.getString("address"));
           
            user.setTel(userObject.getString("tel"));
          
            users.add(user);
        }

       } catch (JSONException e) {
          e.printStackTrace();
       }
                         
            
           File fr = new File("/home/saeed/NetBeansProjects/assiment2/src/java/parsers/relation.json");
            String jsonRelations = readFile(fr.getPath());
                    jsonOut = new JSONTokener(jsonRelations);
                    
               try {
               JSONObject output = new JSONObject(jsonOut);
               JSONArray jsonArray = output.getJSONArray("relations");
                
                 for(int i=0; i < jsonArray.length(); i++){
                  JSONObject userObject = jsonArray.getJSONObject(i);
                  int fiid, secid;
                  fiid= Integer.parseInt(userObject.getString("fiid"));
                  secid= Integer.parseInt(userObject.getString("secid"));
                  
                  users.get(secid-1).friends.add(users.get(fiid-1));
                  users.get(fiid-1).friends.add(users.get(secid-1));
                 }
                     } catch (JSONException e) {
              e.printStackTrace();
              }
               
               for(int i =0; i <users.size(); i++){
            Users u = (Users) users.get(i);
           System.out.println(" \nUser : "+ u.getId());
           System.out.println(" \t Name : "+ u.getName());
           System.out.println(" \t Date  : "+ u.getBorn());
       
           System.out.println(" \t Address : "+u.getAddress());
           System.out.println(" \t Telophne : "+u.getTel());
           System.out.println(" \t Nationality : "+u.getNationality());
           System.out.println("  \tFriend List :");
           for(int m =0 ; m<u.friends.size() ; m++){
               System.out.println(" \t\tFriend "+ (m+1) +": "+u.friends.get(m).getName()+" ");
           }
           
           // System.out.println("\n\n");
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

}