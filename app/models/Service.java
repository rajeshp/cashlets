package models;




import java.util.Date;
import java.util.List;

import com.google.code.morphia.annotations.Entity;
import play.modules.morphia.Blob;

/**
 * Created with IntelliJ IDEA.
 * User: TE162141
 * Date: 10/25/12
 * Time: 5:56 PM
 * To change this template use File | Settings | File Templates.
 *
 *
 * serviceid	string
 name	string
 description	string
 url	string
 start date	date
 end date	date
 serviced area	string
 serviced city	string
 serviceable radius	int
 price	int
 keywords	string
 userid	string
 active	BOOL
 rating	float
 view count	int
 serviced count	int
 *
 *
 */
@Entity
public class Service extends Model{

    //String title String description, String refURL, String Price, String zipcode, String phoneno, String email



        public String name;
        public String description;
        public String refURL;
        //public Date startDate;
        //public Date endDate;
        public String servicedArea;
        public String servicedCity;
        public String createdBy;
        public boolean active;
        public float rating;
        public int viewCount;
        public int serviceCount;
        public Price price;
        public String zipcode;
        public String phoneno;
        public String email;
        
        public Date createdOnDate;
        public Date lastModifieDate;
        
        public List<String> photos;

        //public List<Blob>  photos;


    




}
