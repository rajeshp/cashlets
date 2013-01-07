package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import models.Service;
import models.User;

import com.sun.istack.internal.FinalArrayList;

import controllers.securesocial.SecureSocial;
import controllers.securesocial.UsernamePasswordController;
import play.Logger;
import play.data.validation.Equals;
import play.data.validation.Required;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.With;
import securesocial.provider.AuthenticationMethod;
import securesocial.provider.ProviderType;
import securesocial.provider.SocialUser;
import securesocial.provider.UserId;
import securesocial.provider.UserService;
import securesocial.utils.SecureSocialPasswordHasher;


/**
 * Created with IntelliJ IDEA.
 * User: TE162141
 * Date: 10/25/12
 * Time: 9:07 PM
 * To change this template use File | Settings | File Templates.
 */
@With(SecureSocial.class)

public class UserController extends Controller {

	private static final String emailAlreadyRegistered = "emailAlreadyRegistered";
	
    public static void registerUser(@Required String fname, 
    		@Required String lname,
    		@Required String email,
    		@Required String password,
    		@Required @Equals(message = "securesocial.passwordsMustMatch", value = "password") String password2,
    		@Required String city,
    		@Required String gender,
    		@Required String dob,
    		@Required String country,
    		String phone
    		
    		)
    {
    	
    	/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
    	try {
			Date dobDate = sdf.parse(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
    	
    	
    	UserId id = new UserId();
		id.id = email;
		id.provider = ProviderType.userpass;
		
		if ( UserService.find(id) != null ) {
		validation.addError(emailAlreadyRegistered, Messages.get(emailAlreadyRegistered));
		}
		
    	
    	
    	SocialUser user = new SocialUser();
		user.id = id;
		user.firstName = fname;
		user.lastName=lname;
		user.email = email;
		user.password = SecureSocialPasswordHasher.passwordHash(password);
		// the user will remain inactive until the email verification is done.
		user.isEmailVerified = true;
		user.authMethod = AuthenticationMethod.USER_PASSWORD;
		
		user.city=city;
		user.phone=phone;
		user.gender=gender;
    	
    	System.out.println("Inside Custom user controller");
		//UsernamePasswordController.createAccount(fname, lname, email, phone, password, password2, city, gender, null);
		
		User newUser = new User();
		newUser.userid = id.id;
		newUser.details = user;
		
		newUser.save(); 
		
		System.out.println("User Account created : "+newUser.userid);
		
    	
		//render("/public/html/sign-up.html");

        System.out.println("user registered : "+id.id);

    }

    public static void logOutUser()
    {
    	SecureSocial.logout();
    	
    }


    public static void showUserProfile()
    {
        userProfile();
       // render();
    }

    public static void userProfile()
    {

      SocialUser currentUser =    SecureSocial.getCurrentUser();

        if(SecureSocial.isUserLoggedIn())
        {List<Service> userServices = Service.q().filter("createdBy",currentUser.email).asList();
            render(userServices);
        }
        else
            render("/login");
    }

    public static void editProfile()
    {
        SocialUser currentUser =    SecureSocial.getCurrentUser();

        if(SecureSocial.isUserLoggedIn())
            render(currentUser);
        else
            render("/login");
    }

    public static void updateProfile()
    {
        Logger.info("Update Profile**********88");

        if(SecureSocial.isUserLoggedIn())
        {
            //get user object
            User user = User.findById(SecureSocial.getCurrentUser().id);

            String fname = request.params.get("user[first_name]");
            String lname = request.params.get("user[last_name]");
            String phone = request.params.get("user[phone]");
            String city = request.params.get("user[location]");
            String dob = request.params.get("user[dob]");

            try
            {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date dobDate=null;
                if(dob!=null)
                dobDate =  sdf.parse(dob);

                 user.details.firstName=fname;
                 user.details.lastName=lname;
                 user.details.phone=phone;
                 user.details.city=city;
                 user.details.dob=dobDate;
                 Logger.info("befoer saving user");

                 user.save();
                 Logger.info("User Profile Updated :"+user.details.id.id);
            }
            catch(Exception pe)
            {
                Logger.error("Date Parse Exception :");
            }

            redirect("/showUserProfile");
        }
        else
        {
            render("/login");
        }
    }

}
