package com.pany;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.*;

/**
 * Created by Universal on 2/2/2018.
 */
public class testClass {

    public static void main(String[] args) {

        System.out.println(horizontalButtonControl());
    }

    public static int horizontalButtonControl() {

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        List<HorizontalLayout> horizontalLayouts = new ArrayList<>();
        Button button;

        for (int i = 0; i < 8; i++) {
            horizontalLayouts.add(horizontalLayout);
        }
        for (HorizontalLayout layout : horizontalLayouts) {
            button = new Button();
            button.setIcon(FontAwesome.CIRCLE_O);
            layout.addComponent(button);
        }

        int horizontalButtonCount = 0;

        for (HorizontalLayout horizontalLay : horizontalLayouts) {
            Button kontroledilenButon = (Button) horizontalLay.getComponent(0);

            Resource icon = kontroledilenButon.getIcon();
            if (icon != null && icon.equals(FontAwesome.CIRCLE_O)) {
                horizontalButtonCount++;

            }
        }
        return horizontalButtonCount;
    }


}
