package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Photo;
import org.apache.solr.common.SolrDocument;
import org.hibernate.type.descriptor.BinaryStream;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.MimeTypes;
import play.modules.morphia.Blob;
import play.modules.morphia.Model;
import play.mvc.Controller;
import play.mvc.With;

import models.Price;
import models.Service;
import securesocial.provider.SocialUser;
import controllers.securesocial.SecureSocial;

@With(SecureSocial.class)



public class ServiceController extends Controller {

	
	public static void AddService( String title, String description, String refURL, float price, String zipcode,String phone, String email, File photo)
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

        if(currentUser!=null)
        newService.createdBy=currentUser.id.id;
        else
        newService.createdBy="Anonymous";


        Date today = new Date();
        newService.createdOnDate= today; 
        newService.lastModifieDate= today;


        List<Blob> uploaded_pics = new ArrayList<Blob>();


        if(photo!=null)
        {
            try
            {
                Blob b =    new Blob();
                b.set(photo, MimeTypes.getContentType(photo.getName()));

                uploaded_pics.add(b);

                Photo service_photo = new Photo();

                if(currentUser!=null)
                    service_photo.addedBy=currentUser.id.id;
                else
                    service_photo.addedBy="Anonymous";

                service_photo.addedOn = new Date();

                service_photo.photo_binary=b;

                service_photo.save();


                List<String> photoids = new ArrayList<String>();
                photoids.add(service_photo.getId().toString());

                newService.photos = photoids;


            }
            catch(IOException ie)
            {
                Logger.error("FileUpload IOException : ", ie);
            }

        }
        else
        {
            Logger.info("No Service pics uploaded by user");
        }

        //newService.photos = uploaded_pics;

        newService.save();

        render("/public/html/index.html");

        }
        else
        {
            render("/login");
        }

    }
	

    public static void showAddServicePage()
    {
        System.out.println("page render from service controller");
        render();

    }


}
