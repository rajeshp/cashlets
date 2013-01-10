package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: TE162141
 * Date: 1/10/13
 * Time: 4:05 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class AdminUser extends Model {

    @Id
    public String username;

    public String password;
    public Date lastLogin;
    public String email;


}
