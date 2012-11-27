package models;

import com.google.code.morphia.annotations.*;
import com.google.code.morphia.annotations.Id;
import play.db.jpa.Blob;
import java.util.Date;


/**
* Created with IntelliJ IDEA.
* User: TE162141
* Date: 10/25/12
* Time: 7:31 PM
* To change this template use File | Settings | File Templates.
*/
@Entity
public class Photo extends Model {


    public static Blob photo_binary;
    public static Date addedOn;

}
