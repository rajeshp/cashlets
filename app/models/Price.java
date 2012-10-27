package models;

import com.google.code.morphia.annotations.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: TE162141
 * Date: 10/25/12
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Price extends Model{

    public String priceid;
    public String price;
    public String symbol;


}
