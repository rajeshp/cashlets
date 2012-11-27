package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import models.Photo;
import org.hibernate.type.descriptor.BinaryStream;
import play.db.jpa.Blob;
import play.db.jpa.JPA;
import play.libs.MimeTypes;
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
        
        newService.createdBy=currentUser.id.id;
        Date today = new Date();
        newService.createdOnDate= today; 
        newService.lastModifieDate= today;


        Photo servicePic = new Photo();
            try
            {

                String fileType = MimeTypes.getContentType(photo.getName());
                Blob fileBinary = new Blob();

                fileBinary.set(new FileInputStream(photo),fileType);


                servicePic.photo_binary=fileBinary;
                servicePic.addedOn = new Date();


                servicePic.save();

            }
            catch(IOException io)
            {
                System.out.println("Error Uploading image");
                servicePic.delete();


            }


        newService.save();
        }


    }
	
	
}
