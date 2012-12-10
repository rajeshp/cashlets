package controllers;

import models.Photo;
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



    public static void getPhoto(String id)
    {

        Photo p = Photo.findById(id);

        response.setContentTypeIfNotSet(p.photo_binary.type());

        renderBinary(p.photo_binary.get());


    }


}