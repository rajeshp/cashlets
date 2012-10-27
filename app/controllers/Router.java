package controllers;

import play.mvc.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: TE162141
 * Date: 10/24/12
 * Time: 7:54 PM
 * To change this template use File | Settings | File Templates.
 */

public class Router extends Controller {

    public static void route(String page)
    {
        System.out.println("requested routed to page : "+page+".html");
        render("/public/html/"+page+".html");
    }

}
