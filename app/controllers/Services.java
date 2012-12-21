package controllers;

import play.Logger;
import play.mvc.Controller;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: TE162141
 * Date: 12/11/12
 * Time: 1:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class Services extends Controller {



    public static void addServicePage()
    {
        render();
        Logger.info("request routed to addServices view page");
    }

    public static void addService(File photo)
    {
        System.out.println("service added...");
    }




}
