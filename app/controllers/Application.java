package controllers;

import models.Price;
import models.Service;
import play.*;
import play.data.validation.Required;
import play.mvc.*;
import securesocial.provider.SocialUser;

import java.util.*;

import controllers.securesocial.SecureSocial;


public class Application extends Controller {

    public static void index() {
        render();
        System.out.println("Hello WOlfraslkjsdghkhadk");
    }



    
    
    
    public static List<Service> getServices()
    {
    		
    	return Service.findAll();
    	
    	
    }





}