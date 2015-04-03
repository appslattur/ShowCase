package com.special.appslattur.Tools;

import com.special.R;

/**
 * Created by Ari on 2.4.2015.
 */
public class LogoMatcher {

    public static int getLogoResourceByName(String name){

        String s = String.valueOf(name);
        if (s.equals("Serranó")) {
            return R.drawable.serrano;

        } else if(s.equals("WorldClass")){
            return R.drawable.worldclass;
        }
        else if (s.equals("Start")){
            return R.drawable.start;
        }
        else if (s.equals("Bootcamp")){
            return R.drawable.bootcamp;
        }
        else if (s.equals("Hamborgarabúlla Tómasar")){
            return R.drawable.hamborgarabullan;
        }
        else if(s.equals("Subway")){
            return R.drawable.subway;
        }


        return  R.drawable.ph_1;
    }

}
