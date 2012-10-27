package controllers;

import models.Service;
import play.*;
import play.mvc.*;

import java.util.*;


public class Application extends Controller {

    public static void index() {
        render();
        System.out.println("Hello WOlfraslkjsdghkhadk");
    }


    private static int count=0;

    public static void AddService(String title, String description, String refURL, String Price, String zipcode, String phoneno, String email)
    {

        System.out.println("************ ADD Service Controller ***************");

        /*Service newService= new Service();

        newService.serivceID="id_"+count++;
        newService.name=title;
        newService.description=description;
        newService.refURL=refURL;
        newService.price=Price;
        newService.zipcode=zipcode;
        newService.phoneno=phoneno;
        newService.email=email;

        newService.save();*/


    }





}