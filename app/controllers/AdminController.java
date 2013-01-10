package controllers;

import models.AdminUser;
import play.Logger;
import play.data.validation.Equals;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.Scope;

/**
 * Created with IntelliJ IDEA.
 * User: TE162141
 * Date: 1/10/13
 * Time: 3:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdminController extends Controller {

    public static void showLoginPage()
    {
        render();
    }


    public static void login(String username, String password)
    {
        Logger.info("username"+username+"  password="+password);

        AdminUser adminUser = AdminUser.findById(username);


        if(adminUser!=null)
            Logger.info("**** ADMIN USER FOUND :"+adminUser);

        AdminUser user = adminUser;

        if(adminUser!=null && !adminUser.password.equals(password))
        {

            Scope.Session.current().put("user", adminUser.getId() );


            render("/admincontroller/home.html", user);



        }
        else
        {  forbidden();
           response.writeChunk("Invalid Credentials");
        }

    }



    public static void changePassword(@Required String oldPassword, @Required String newPassword, @Required @Equals(message="Both Passwords Should Match, Try Again", value="newPassword")String passwordRepeat)
    {

        AdminUser adminUser = AdminUser.findById(Scope.Session.current().get("user"));


        if(adminUser==null)
        {
            unauthorized();
        }

     if(true ||adminUser.password.equals(oldPassword))
     {
        if(newPassword.equals(passwordRepeat))
        {
            String passwordHash = new securesocial.utils.PlayCryptoHasher().passwordHash(newPassword);
            adminUser.password=passwordHash;

            adminUser.save();

            render("/admincontroller/home.html");


        }
         else
        {
            flash.error("Both Passwords do not match, Try Again");
            render("/admincontroller/showChangePasswordsPage.html");

        }
     }
        else
     {
         forbidden();
         render("/admin/login");
     }


    }



    public static void showChangePasswordPage()
    {
        render();
    }

}
