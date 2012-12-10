package controllers;

import models.FileUploadTestModel;
import org.apache.commons.io.FileUtils;
import play.Play;
import play.libs.MimeTypes;
import play.modules.morphia.Blob;
import play.modules.morphia.Model;
import play.mvc.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TE162141
 * Date: 12/10/12
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestController extends Controller {


    public static void test()
    {
        System.out.println("Welcome to Test Controller!!!!!");


        render();

    }


    public static void fileUploadExample(String name, File photo)
    {
        System.out.println("Inside Test Controller File upload method");
        if(photo!=null)
        {
            System.out.println("FileUploaded : "+photo.getName());

            System.out.println(photo.getAbsolutePath());


            FileUploadTestModel futModel = new FileUploadTestModel();
            futModel.name=name;

            try
            {

                Blob b =    new Blob();
                b.set(photo, MimeTypes.getContentType(photo.getName()));

                futModel.photo = b;



                File f = new File(Play.applicationPath.getPath()+"/uploads/"+photo.getName());

                FileUtils.copyFile(photo,f);


                futModel.save2();




            }catch(IOException ioe)
            {
                System.out.println(ioe.toString());
            }


        }

        render();
    }


    public static List<File> getALLFileUploads()
    {

        return FileUploadTestModel.findAll();


    }


    public static void  getPhoto(String id)
    {

        FileUploadTestModel uploaded_file =  FileUploadTestModel.findById(id);

        response.setContentTypeIfNotSet(uploaded_file.photo.type());

        renderBinary(uploaded_file.photo.get());


    }


}
