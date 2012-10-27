package models;

import java.util.Date;

import play.libs.OAuth;
import securesocial.provider.AuthenticationMethod;
import securesocial.provider.UserId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;


/**
 * Created with IntelliJ IDEA.
 * User: TE162141
 * Date: 10/25/12
 * Time: 6:53 PM
 * To change this template use File | Settings | File Templates.
 */
// Need to over-ride SocialUser class of securesocial, but since this is a model class and cannot over-ride more than 1 class
// we copied all the attributes of SocialUser class in our Custom User Class
@Entity
public class User extends Model{
   @Id
  public  String userid;
  public  String password;
  public  String firstName;
  public  String lastName;
  public  String phone;
  public  String city;
  public  String photoID;
  public  String registeredDate;
    /**
     * The user's email
     */
    public String email;
    /**
     * The time of the last login.  This is set by the SecureSocial controller.
     */
    public Date lastAccess;

    /**
     * The method that was used to authenticate the user.
     */
    public AuthenticationMethod authMethod;

    
    /**
     * A boolean indicating if the user has validated his email adddress (available when authMethod is USER_PASSWORD)
     */
    public boolean isEmailVerified;
    
    






}
