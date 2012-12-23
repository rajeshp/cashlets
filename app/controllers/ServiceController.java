package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Photo;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import play.Logger;
import play.libs.MimeTypes;
import play.modules.morphia.Blob;
import play.mvc.Controller;
import play.mvc.With;

import models.Service;
import play.utils.Properties;
import securesocial.provider.SocialUser;
import controllers.securesocial.SecureSocial;
import utils.SolrServerFactory;

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
        newService.price=price;
        newService.currency="INR";
        newService.zipcode=zipcode;

            if(currentUser!=null)
                newService.createdBy=currentUser.id.id;
           /* else
            newService.createdBy="Anonymous"; */

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
            //saving the service images in Photos collection as blob.
                service_photo.save();

            //adding the references of images to the service collection for easier and faster search
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

        //saving the service in collection
        newService.save();


        //add to solr index

            try
            {

            SolrServer server = SolrServerFactory.getServer("http://localhost:8080/solr");

                SolrInputDocument doc = new SolrInputDocument();

                doc.setField("id",newService.getId());
                doc.setField("name",title);
                doc.setField("description", description);
                doc.setField("refURL",refURL);
                doc.setField("price",price);
                doc.setField("zipcode",zipcode);

                server.add(doc);
                server.commit();


            }
            catch(Exception e)
            {
                e.printStackTrace();
            }



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


    public static void showServiceINFO()
    {

    }

    public static void editService()
    {
        render();
    }

    public static void deleteService(String id)
    {
       // Service.q().filter("_id",id).delete();
        Logger.info("Inside delete service");
        Service service = Service.findById(id);

        //check if the currentUser is allowed to delete the service.
            SocialUser currentUser = SecureSocial.getCurrentUser();

        if(currentUser.email.equals(service.createdBy))
        {
            //first delete service pics
            List<String> photos = null;
            if(service.photos!=null)
            {   photos = service.photos ;

                for(String photo : photos)
                {
                    Photo.findById(photo).delete();
                }

            }
            //delete the service
            service.delete();
            Logger.info("Deleted Service id: "+id) ;

        }
        else
        {
           // unauthorized();
            forbidden();
        }
    }

    public static void updateService(Service service)
    {

    }


    public static void serviceDetails(String id)
    {
        System.out.println("service ID : "+id);

        Service currentService = getServiceInfoById(id);

        render(currentService);
    }


    public static Service getServiceInfoById(String id)
    {
        return Service.findById(id) ;
    }


    public static String getService(String id)
    {
        Service service =  Service.findById(id) ;
        System.out.println("Service Name :"+service.name);

        return service.toString() ;
    }

}
