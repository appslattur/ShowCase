package com.special.appslattur.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;

import com.special.appslattur.LocationChainStructure.LocationChain;

import java.util.ArrayList;


/**
 * Created by Ari on 10.1.2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TestLocations.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE_LOCATIONS = "Locations";
    private SQLiteDatabase db;

    public DataBaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tmpTable =
                "CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE_LOCATIONS+" (" +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "NAME VARCHAR NOT NULL, "   +
                        "LATITUDE TEXT NOT NULL, "  +
                        "LONGITUDE TEXT NOT NULL, " +
                        "ENABLED TEXT NOT NULL, "   +
                        "SHORTDESC TEXT NOT NULL, " +
                        "LONGDESC TEXT NOT NULL, "   +
                        "DISCOUNT TEXT"             +");";
        db.execSQL(tmpTable);


    }

    public void clearTable(){
        this.db.delete(DATABASE_TABLE_LOCATIONS, null, null);
    }

    public void refreshTable(){
        clearTable();
        populateTable();
    }


    public void newLocation(String name, String longitude, String latitude){
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("LONGITUDE", longitude);
        values.put("LATITUDE", latitude);
        values.put("ENABLED", 1);
        values.put("SHORTDESC", "Nothing Here");
        values.put("LONGDESC", "Nothing Here");
        this.db.insert(DATABASE_TABLE_LOCATIONS, null, values);
    }
    public void newLocation(String name, String longitude, String latitude, String enabled){
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("LONGITUDE", longitude);
        values.put("LATITUDE", latitude);
        values.put("ENABLED", enabled);
        values.put("SHORTDESC", "Nothing Here");
        values.put("LONGDESC", "Nothing Here");
        this.db.insert(DATABASE_TABLE_LOCATIONS, null, values);
    }
    public void newLocation(String name, String longitude, String latitude, String enabled, String shortdesc){
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("LONGITUDE", longitude);
        values.put("LATITUDE", latitude);
        values.put("ENABLED", enabled);
        values.put("SHORTDESC", shortdesc);
        values.put("LONGDESC", shortdesc);
        this.db.insert(DATABASE_TABLE_LOCATIONS, null, values);
    }
    public void newLocation(String name, String longitude, String latitude, String enabled, String shortdesc, String longdesc){
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("LONGITUDE", longitude);
        values.put("LATITUDE", latitude);
        values.put("ENABLED", enabled);
        values.put("SHORTDESC", shortdesc);
        values.put("LONGDESC", longdesc);
        this.db.insert(DATABASE_TABLE_LOCATIONS, null, values);
    }
    public void setEnabledById(int id, int enabled){
        ContentValues values = new ContentValues();
        values.put("ENABLED", String.valueOf(enabled));
        this.db.update(DATABASE_TABLE_LOCATIONS, values, "ID="+id, null);
    }
    public void setEnabledByName(String name, int enabled){
        ContentValues values = new ContentValues();
        values.put("ENABLED", String.valueOf(enabled));
        this.db.update(DATABASE_TABLE_LOCATIONS, values,"NAME="+name, null);
    }
    public void setShortDescById(int id, String desc){
        ContentValues values = new ContentValues();
        values.put("SHORTDESC", desc);
        this.db.update(DATABASE_TABLE_LOCATIONS, values, "ID="+id, null);
    }
    public void setShortDescByName(String name, String desc){
        ContentValues values = new ContentValues();
        values.put("SHORTDESC", desc);
        this.db.update(DATABASE_TABLE_LOCATIONS, values, "NAME="+name, null);
    }
    public void setLongDescById(int id, String desc){
        ContentValues values = new ContentValues();
        values.put("LONGDESC", desc);
        this.db.update(DATABASE_TABLE_LOCATIONS, values, "ID="+id, null);
    }
    public void setLongDescByName(String name, String desc){
        ContentValues values = new ContentValues();
        values.put("LONGDESC", desc);
        this.db.update(DATABASE_TABLE_LOCATIONS, values, "NAME="+name, null);
    }
    public void setDiscountById(int id, int appslattur){
        ContentValues values = new ContentValues();
        values.put("DISCOUNT", appslattur);
        this.db.update(DATABASE_TABLE_LOCATIONS, values, "ID="+id, null);
    }
    public void setDiscountsByName(String name, int appslattur){
        ContentValues values = new ContentValues();
        values.put("DISCOUNT", appslattur);
        this.db.update(DATABASE_TABLE_LOCATIONS, values, "NAME="+name, null);
    }
    public void setLongitudeById(int id, double longitude){
        ContentValues values = new ContentValues();
        values.put("LONGITUDE", longitude);
        this.db.update(DATABASE_TABLE_LOCATIONS, values, "ID="+id, null);
    }
    public void setLatitudeById(int id, double latitude){
        ContentValues values = new ContentValues();
        values.put("LATITUDE", latitude);
        this.db.update(DATABASE_TABLE_LOCATIONS, values, "ID="+id, null);
    }
    public void setLocationById(int id, Location location){
        ContentValues values = new ContentValues();
        values.put("LONGITUDE", String.valueOf(location.getLongitude()));
        values.put("LATITUDE", String.valueOf(location.getLatitude()));
        this.db.update(DATABASE_TABLE_LOCATIONS, values, "ID="+id, null);
    }
    public ArrayList<Location> getAllLocationsAsList(){
        String query = "SELECT (NAME, LONGITUDE, LATITUDE) FROM "+ DATABASE_TABLE_LOCATIONS+ " ORDER by ID DESC Limit 50";
        Cursor cursor = this.db.rawQuery(query, null);
        ArrayList<Location> locations = new ArrayList<Location>();
        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(0);
                double longitude = Double.parseDouble(cursor.getString(1));
                double latitude = Double.parseDouble(cursor.getString(2));
                Location loc = new Location(name);
                loc.setLongitude(longitude);
                loc.setLatitude(latitude);
                locations.add(loc);
            }while(cursor.moveToNext());
        }
        return locations;
    }
    public ArrayList<Location> getLocationsByName(String name){
        ArrayList<Location> tempList = new ArrayList<Location>();
        String query = "SELECT (NAME, LONGITUDE, LATITUDE) FROM "+ DATABASE_TABLE_LOCATIONS+ " WHERE NAME="+name;
        Cursor cur = this.db.rawQuery(query, null);
        if(cur.moveToFirst()){
            do{
                Location temploc = new Location(cur.getString(0));
                temploc.setLongitude(Double.parseDouble(cur.getString(1)));
                temploc.setLatitude(Double.parseDouble(cur.getString(2)));
                tempList.add(temploc);
            }while(cur.moveToNext());
        }
        return tempList;
    }
    public Location getLocationById(int id){

        String query = "SELECT (NAME, LONGITUDE, LATITUDE) FROM "+ DATABASE_TABLE_LOCATIONS+ " WHERE ID="+id;
        Cursor cur = this.db.rawQuery(query, null);
        if(cur.moveToFirst()){
            Location loc = new Location(cur.getString(0));
            loc.setLongitude(Double.parseDouble(cur.getString(1)));
            loc.setLatitude(Double.parseDouble(cur.getString(2)));
            return loc;
        }
        return new Location("No Location with That Id");
    }
    public int getDiscountById(int id, int appslattur){
        String query ="SELECT DISCOUNT FROM "+ DATABASE_TABLE_LOCATIONS+" WHERE ID="+id;
        Cursor cur = this.db.rawQuery(query, null);
        if(cur.moveToFirst()){
            return Integer.parseInt(cur.getString(0));
        }
        else return 0;
    }
    public String getShortDescById(int id){
        String query ="SELECT SHORTDESC FROM "+ DATABASE_TABLE_LOCATIONS+" WHERE ID="+id;
        Cursor cur = this.db.rawQuery(query, null);
        if(cur.moveToFirst()){
            return cur.getString(0);
        }
        else return "Nothing Here";
    }
    public String getLongDescById(int id){
        String query ="SELECT LONGDESC FROM "+ DATABASE_TABLE_LOCATIONS+" WHERE ID="+id;
        Cursor cur = this.db.rawQuery(query, null);
        if(cur.moveToFirst()){
            return cur.getString(0);
        }
        else return "Nothing Here";
    }
    public String getNameById(int id){
        String query ="SELECT NAME FROM "+ DATABASE_TABLE_LOCATIONS+" WHERE ID="+id;
        Cursor cur = this.db.rawQuery(query, null);
        if(cur.moveToFirst()){
            return cur.getString(0);
        }
        else return "Nothing Here";
    }



    //////////////////
    public void populateTable(){
        String subway = "Subway";
        String tolvutek = "Tölvutek";
        String start = "Start";
        String bootcamp = "Bootcamp";
        String worldclass = "WorldClass";
        String bullan ="Hamborgarabúlla Tómasar";
        String serrano = "Serranó";


        String templong = "66";
        String templat = "-21";

        String shortdesc ="Some short description";
        String longdesc = getHipster();

        newLocation(subway, templat,templong , "1", shortdesc, longdesc);
        newLocation(tolvutek, templat,templong , "1", shortdesc, longdesc);
        newLocation(start, templat, templong, "1", shortdesc, longdesc);
        newLocation(bootcamp, templat, templong, "1", shortdesc, longdesc);
        newLocation(worldclass, templat, templong, "1", shortdesc, longdesc);
        newLocation(bullan, templat, templong, "1", shortdesc, longdesc);
        newLocation(serrano, templat, templong, "1", shortdesc, longdesc);


    }

    private String getHipster(){
        String hipster = "Dreamcatcher Kickstarter jean shorts, meditation polaroid ugh hella flexitarian tattooed gentrify brunch bicycle rights Truffaut Schlitz migas. Roof party Wes Anderson shabby chic wayfarers crucifix. PBR keytar authentic small batch, butcher food truck plaid paleo Wes Anderson lo-fi fixie raw denim polaroid pour-over. Try-hard fixie kitsch keffiyeh Blue Bottle. Aesthetic Bushwick roof party, scenester kogi leggings 90's cornhole try-hard hashtag PBR. Put a bird on it fap cliche, 3 wolf moon retro tousled you probably haven't heard of them. Cliche kitsch paleo whatever Tumblr mumblecore cray.\n" +
                "\n" +
                "Salvia Austin swag, asymmetrical skateboard stumptown church-key fingerstache authentic squid master cleanse. Meditation jean shorts bicycle rights cornhole aesthetic keffiyeh. Ethical jean shorts messenger bag selvage hoodie, Etsy kitsch organic trust fund actually swag art party forage scenester. Wayfarers VHS deep v, Etsy stumptown ennui farm-to-table cardigan YOLO PBR&B. Kickstarter Odd Future kale chips, Bushwick DIY craft beer McSweeney's twee. Sriracha mlkshk selfies deep v seitan leggings, quinoa actually Echo Park migas Thundercats. Before they sold out polaroid kale chips, Portland fixie yr 3 wolf moon twee you probably haven't heard of them aesthetic.\n" +
                "\n" +
                "You probably haven't heard of them hashtag cronut next level deep v post-ironic, listicle aesthetic health goth hella. Thundercats cardigan mustache, beard sriracha photo booth Williamsburg scenester listicle. Locavore Thundercats High Life lumbersexual. Shoreditch selfies chillwave vegan, Williamsburg irony pork belly XOXO twee actually master cleanse. Cornhole 90's Odd Future XOXO, umami kitsch Thundercats organic Austin Pitchfork. Yr lomo fap, locavore wayfarers Odd Future 3 wolf moon direct trade cronut jean shorts Brooklyn lo-fi trust fund photo booth. Fashion axe butcher heirloom asymmetrical yr, disrupt twee leggings health goth ethical sriracha ugh biodiesel artisan YOLO.\n" +
                "\n" +
                "Tousled post-ironic cred, photo booth health goth beard polaroid disrupt Bushwick. Organic sriracha artisan, crucifix craft beer freegan Pitchfork mustache roof party twee Echo Park 90's. Intelligentsia hoodie sriracha McSweeney's scenester irony, cardigan Thundercats street art tofu flannel flexitarian. Kickstarter flannel hashtag, wayfarers try-hard Banksy drinking vinegar pop-up four loko cliche scenester cred street art Helvetica. Hashtag chia pug, PBR&B cliche Schlitz disrupt yr four dollar toast +1 ethical synth chambray polaroid. Pour-over Portland tofu butcher artisan four loko, kogi post-ironic paleo skateboard seitan lo-fi narwhal lumbersexual four dollar toast. Tattooed tofu American Apparel, distillery letterpress chia roof party pork belly cliche yr swag Blue Bottle cray pug Etsy.\n" +
                "\n" +
                "Actually brunch aesthetic Bushwick church-key cornhole, leggings selfies pour-over High Life freegan. Jean shorts banjo direct trade gentrify, ennui scenester Marfa four dollar toast lomo sriracha swag fingerstache occupy. YOLO hashtag try-hard, migas taxidermy keytar food truck literally. Fingerstache Kickstarter Blue Bottle gastropub, ethical fashion axe twee lumbersexual. Direct trade dreamcatcher cardigan locavore Austin selfies. Neutra brunch wayfarers distillery DIY. Umami tofu twee forage, before they sold out Bushwick Austin PBR&B blog shabby chic.\n" +
                "\n" +
                "Selvage dreamcatcher trust fund YOLO, cold-pressed photo booth Wes Anderson crucifix hella Brooklyn keffiyeh cornhole. DIY cred messenger bag, +1 scenester direct trade Intelligentsia. 90's Wes Anderson next level blog deep v vegan synth. Butcher flexitarian salvia, swag semiotics Godard +1 pop-up Intelligentsia put a bird on it brunch pug drinking vinegar. Fixie heirloom lumbersexual Neutra. Etsy cornhole ennui pour-over brunch. Portland Brooklyn cliche, Wes Anderson next level paleo fixie +1 pug Schlitz.\n" +
                "Oh. You need a little dummy text for your mockup? How quaint.\n" +
                "\n" +
                "I bet you’re still using Bootstrap too…";
        return hipster;
    }

    public String getLocations(){
        String query = "SELECT * FROM "+ DATABASE_TABLE_LOCATIONS+ " ORDER by ID DESC Limit 50";
        Cursor cursor = this.db.rawQuery(query, null);
        String MASSIVESTRING ="";
        if(cursor.moveToFirst()){
            do{

                String _1 = cursor.getString(1);
                String _2 = cursor.getString(2);
                String _3 = cursor.getString(3);
                int enabled = cursor.getInt(4);

                String comibined = "((Name:"+_3+",Longitude:"+_1+",Latitude:"+_2+"))";
                MASSIVESTRING+=comibined;

            }
            while(cursor.moveToNext());


        }
        return MASSIVESTRING;
    }

    public ArrayList<LocationChain> getLocationList(){
        String query = "SELECT * FROM "+ DATABASE_TABLE_LOCATIONS+ " ORDER by ID DESC Limit 50";
        Cursor cursor = this.db.rawQuery(query, null);
        ArrayList<LocationChain> myLocationChain = new ArrayList<LocationChain>();
        if(cursor.moveToFirst()){
            do{
                double longitude = Double.parseDouble(cursor.getString(3));
                double latitude = Double.parseDouble(cursor.getString(2));
                String name = cursor.getString(1);
                int enabled = cursor.getInt(4);
                int id = cursor.getInt(0);
                boolean matched = false;

                for(LocationChain subChain: myLocationChain){
                    if(subChain.getName().equals(name)&& subChain != null) {
                        matched = true;
                        /*
                        Location temploc = new Location(name);
                        temploc.setLatitude(latitude);
                        temploc.setLongitude(longitude);
                        subChain.newLink(temploc, id, enabled);
                        */
                        break;
                    }
                }

                if(!matched){
                    LocationChain tempchain = new LocationChain(name);
                    Location location = new Location(name);

                    location.setLatitude(latitude);
                    location.setLongitude(longitude);

                    tempchain.newLink(location, id, enabled);
                    myLocationChain.add(tempchain);
                }


            }
            while(cursor.moveToNext());


        }
        return myLocationChain;
    }

    public void establishConnection()
    {
        this.db = this.getWritableDatabase();
    }

    public void closeConnection()
    {
        this.db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //For later use
    }

}
