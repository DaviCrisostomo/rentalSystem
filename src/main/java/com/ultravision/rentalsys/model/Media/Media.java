
///THE INITIAL IDEA WAS TREAT A MEDIA AS AN ARRAY OF TRACKS////

/*package com.ultravision.rentalsys.model.Media;

import javax.persistence.Transient;
import java.util.ArrayList;

public class Media {

    MedType media;
    int runtime;
// List<String>tracks;


    public Media(MedType type){

        this.media = type;

    }

    public String getMedType() {

        return media.mediaType;
    }

    public void setRuntime(int runtime) {

        this.runtime = runtime;
    }

    public int getRuntime(){

        return runtime;
    }

    public void addTracks(String trackToAdd){

        String[] allTracks = trackToAdd.split("\\*");
        for (String thisTrack : allTracks) {
            mediaContainer.add(thisTrack.trim());
        }

    }

    public void addTrack(int position, String trackToAdd){

        //position-1 to make method more user friendly
        this.mediaContainer.add(position-1, trackToAdd);
    }


    public void removeTrack(int position){

        this.mediaContainer.remove(position);
    }



    public String getTracks() {


        StringBuilder content = new StringBuilder();

        for(int i =0; i< mediaContainer.size();i++){
            String mark= i<mediaContainer.size()-1?"; ":".";
            content.append((i+1)+"-"+mediaContainer.get(i)+mark);
        }

        return content.toString();
    }

}
*/