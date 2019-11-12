package com.ultravision.rentalsys.Utilities;

public class Input {

    //checking if the string is an acceptable value for Long
    public static boolean forLong(String string){

        try{

            Long.parseLong(string);

        }catch(NumberFormatException n){
            return false;
        }
      return true;
    }
    //checking if the string is an acceptable value for Integer
    public static boolean forInt(String string){

        try{

            Integer.parseInt(string);

        }catch(NumberFormatException n){
            return false;
        }
        return true;
    }

}
