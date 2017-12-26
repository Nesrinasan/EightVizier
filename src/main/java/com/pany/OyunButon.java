package com.pany;


import com.vaadin.ui.Button;

/**
 * Created by NESRIN on 4.12.2017.
 */
public class OyunButon extends Button {
    String caption = "*";
    public OyunButon(String caption){
        this.caption = caption;
        this.setCaption(caption);
    }

}
