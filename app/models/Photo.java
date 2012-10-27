package models;

import play.db.jpa.Blob;
import java.util.Date;

import com.google.code.morphia.annotations.Entity;



/**
 * Created with IntelliJ IDEA.
 * User: TE162141
 * Date: 10/25/12
 * Time: 7:31 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Photo extends Model {
	   
    String photo_id;
    Blob photo_binary;
    Date addedOn;
    Date lastModifiedDate;



}
