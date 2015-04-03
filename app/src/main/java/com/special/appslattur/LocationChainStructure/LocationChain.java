package com.special.appslattur.LocationChainStructure;

import android.location.Location;

import java.util.ArrayList;

/**
 * Created by Ari on 29.1.2015.
 */
public class LocationChain {
    String chainName;
    ArrayList<LocationLink> links = new ArrayList<LocationLink>();
    int count = 0;
    int ID;
    public LocationChain(String chainName){
        this.chainName = chainName;

    }
    public int getID(){
        return ID;
    }
    public String getName(){
        return this.chainName;
    }
    public void newLink(Location location, int id, int en){
        LocationLink tlink = new LocationLink(location, id, en);
        ID = id;
        this.links.add(tlink);
        this.count++;
    }
    public void removeLink(int index){
        this.links.remove(index);
        this.count--;
    }
    public ArrayList<LocationLink> getLinks(){
        return this.links;
    }
    public ArrayList<LocationLink> getEnabledLocations(){
        ArrayList<LocationLink> templist = new ArrayList<LocationLink>();
        for(LocationLink ll : this.links){
            if(ll.enabled == 1){
                templist.add(ll);
            }

        }
        return templist;
    }


    public class LocationLink{
        Location location;
        public int enabled = 1;
        int id;
        public LocationLink(Location location, int id, int en){
            this.location = location;
            this.id = id;
            this.enabled = en;
        }
        public void setEnabled(int b){
            this.enabled = b;
        }
        public boolean isEnabled(){
            if(this.enabled == 1){
                return true;
            }return false;

        }
        public Location getLocation(){
            return this.location;
        }
        public int getId(){
            return this.id;
        }


    }
}
