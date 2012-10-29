package controllers;

import java.util.Date;

import play.modules.morphia.Model;
import play.mvc.Controller;
import play.mvc.With;

import models.Price;
import models.Service;
import securesocial.provider.SocialUser;
import controllers.securesocial.SecureSocial;

@With(SecureSocial.class)
public class ServiceController extends Controller {

	
	public static void AddService( String title, String description, String refURL, float price, String zipcode,String phone, String email)
    {

        System.out.println("************ ADD Service Controller ***************");
        
        SocialUser currentUser = SecureSocial.getCurrentUser();
        if(currentUser!=null)
        {
        
        Service newService= new Service();

        newService.name=title;
        newService.description=description;
        newService.refURL=refURL;
        Price p = new Price();
        p.price=price;
        newService.price=p;
        newService.zipcode=zipcode;
        
        newService.createdBy=currentUser.id.id;
        Date today = new Date();
        newService.createdOnDate= today; 
        newService.lastModifieDate= today;
        newService.save();
        }


    }
	
	
}
