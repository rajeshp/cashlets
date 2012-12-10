package models;

import com.google.code.morphia.annotations.Entity;
import play.modules.morphia.Blob;

import com.google.code.morphia.annotations.Id;

import java.io.File;


/**
 * Created with IntelliJ IDEA.
 * User: TE162141
 * Date: 12/10/12
 * Time: 5:20 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class FileUploadTestModel extends Model {

   /* @Id
    public String  id;*/


    public String name;
    public Blob photo;




}
