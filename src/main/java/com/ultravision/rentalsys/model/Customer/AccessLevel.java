package com.ultravision.rentalsys.model.Customer;

import com.ultravision.rentalsys.model.Titles.*;


public enum AccessLevel {

    RC("Regular Customer", 0.0),
    ML("Music Lover", 12.0),
    VL("Video Lover", 15.0),
    TL("TV Lover",15.0),
    PM("Premium",18.0);

    private final String access;
    private final double cost;
    private CanRent goesInside;//default

    AccessLevel(String access, double cost){
        this.access = access;
        this.cost = cost;
        this.goesInside = setBasket();//what is allowed to go inside the customer's basket considering if
        //the customer is RC,ML, VL, TL or PM.

    }

    private CanRent setBasket() {
//implementing the functional interface:
        switch(access){

            case "Regular Customer":
                return (Title i) -> false;//pays regular renting value for all items

            case "Music Lover":
                return (Title i) -> i instanceof Music;//only audio cd and live concert are free

            case "TV Lover":
                return (Title i) -> i instanceof BoxSet;//only tv box set items are free

            case "Video Lover":
                return (Title i) -> i instanceof Movie;//only movies are free

            default:
                return (Title i) -> true;//whatever the customer wants

        }

    }

    public boolean canRent(Title i) {

        return goesInside.canInclude(i);
    }

    public String getAccess() {
        return access;
    }

    public double getCost() {
        return cost;
    }

    public static boolean compareString(String string){

       try{
           AccessLevel.valueOf(string.toUpperCase().trim());

       }catch(IllegalArgumentException e){return false;}

        return true;
    }
}
