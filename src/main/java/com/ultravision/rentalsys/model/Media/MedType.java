package com.ultravision.rentalsys.model.Media;

public enum MedType {

    CD("CD"), DVD("DVD"), BLUE_RAY("Blue-Ray");

    String mediaType;


    MedType(String type){

        this.mediaType = type;

    }

    public String getMediaType() {
        return mediaType;
    }
}

