package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

/**
 * Created with IntelliJ IDEA.
 * User: TE162141
 * Date: 10/25/12
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Price extends Model{

	@Id
    public String id;
    public float price;
    public String symbol="U+20B9";


}
