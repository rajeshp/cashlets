package controllers;

import models.Photo;
import models.Service;
import play.mvc.*;

import java.util.*;


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