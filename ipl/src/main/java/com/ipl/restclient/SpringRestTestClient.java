package com.ipl.restclient;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
 
import org.springframework.web.client.RestTemplate;

import com.ipl.model.User;
 

 
public class SpringRestTestClient {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/ipl";
     
    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllUsers(){
        //("Testing listAllUsers API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        List<User> users = restTemplate.getForObject(REST_SERVICE_URI+"/user/all", List.class);
         
      if(users.size()==0){
		for (User user : users) {
			//(user);
		}
        }else{
            //("No user exist----------");
        }
    }
     
    /* GET */
    private static void getUser(){
        //("Testing getUser API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(REST_SERVICE_URI+"/user/1", User.class);
        //(user);
    }
     
    /* POST */
    private static void createUser() {
        //("Testing create User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = new User("abc","abc","ROLE_USER",1,new Double(500),"");
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/add", user, User.class);
        //("Location : "+uri.toASCIIString());
    }
 
    /* PUT */
    private static void updateUser() {
        //("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
       // User user  = new User(1,"Tomy",33, 70000);
       // restTemplate.put(REST_SERVICE_URI+"/user/1", user);
      //  //(user);
    }
 
    /* DELETE */
    private static void deleteUser() {
        //("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/3");
    }
 
 
    /* DELETE */
    private static void deleteAllUsers() {
        //("Testing all delete Users API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/");
    }
    /*
   
    public static void main(String args[]){
        listAllUsers();
        createUser();
        getUser();
       
        listAllUsers();
        updateUser();
        listAllUsers();
        deleteUser();
        listAllUsers();
        deleteAllUsers();
        listAllUsers();
    }*/
}