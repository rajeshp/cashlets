package controllers;

import java.util.List;

import models.Service;

import controllers.securesocial.SecureSocial;
import play.mvc.Controller;
import securesocial.provider.SocialUser;
import sun.util.logging.resources.logging;

/**
 * Created with IntelliJ IDEA.
 * User: TE162141
 * Date: 10/24/12
 * Time: 7:54 PM
 * To change this template use File | Settings | File Templates.
 */

public class Router extends Controller {

	public static void indexPage()
	{
		SocialUser user = SecureSocial.getCurrentUser();
		List<Service> services = Application.getServices();
		models.Service service =null;
		
		
		System.out.println("No. of services :"+services.size());
		
		
		render("/public/html/index.html");
	}
	
	
    public static void route(String page)
    {
    	
    	SocialUser user = SecureSocial.getCurrentUser();
    	
    	
        System.out.println("requested routed to page : "+page+".html");
        
        if(user!=null && (page.equals("log-in")||page.equals("sign-up")))
        	indexPage();
        	else {

            if(page.equals("addServicePage"))
                Services.addServicePage();



        	 render("/public/html/"+page+".html");
		}
        
       
    }
    
    public static void afterLoginSuccessful()
    {
    	render("/public/html/index.html");
    }



    
}
