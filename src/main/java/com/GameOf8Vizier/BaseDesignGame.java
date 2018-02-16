package com.GameOf8Vizier;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Universal on 2/4/2018.
 */
public class BaseDesignGame implements IBaseDesignGame {

    @Override
    public Button createGameButtons(Button buton) {
        buton = new Button ();
        buton.setWidth ("50px");
        buton.setHeight ("50px");
        buton.setIcon (FontAwesome.CIRCLE_O);
        return buton;
    }

    @Override
    public void addButtonLocal(int buttonYLocal, int buttonXLocal, Button button) {
        Map<Integer, Integer> butonLocals = new HashMap<> ();
        butonLocals.put (buttonXLocal, buttonYLocal);
        button.setData (butonLocals);
    }

    @Override
    public Button createVerticalNumberButons(int buttonCaption, Button buton) {
        String buttonCaptions = String.valueOf (buttonCaption);

        buton = new Button (buttonCaptions);
        if (buttonCaption == 0) {
            buton.setIcon (FontAwesome.TIMES_CIRCLE);
            buton.setCaption ("");
            buton.setEnabled (true);
        } else {
            buton.setEnabled (false);
        }
        buton.setWidth ("50px");
        buton.setHeight ("50px");
        return buton;
    }

    @Override
    public Button createHorizontalNumberButon(int buttonCaption, Button buton, List<Button> buttonList) {
        String buttonCaptions = String.valueOf (buttonCaption);

        buton = new Button (buttonCaptions);
        if (buttonCaption == 0) {
            buton.setIcon (FontAwesome.TIMES_CIRCLE);
            buton.setCaption ("");
            buton.setEnabled (true);
            buton.addClickListener (new Button.ClickListener () {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    restartGame (buttonList);
                }
            });
        } else {
            buton.setEnabled (false);
        }
        buton.setWidth ("50px");
        buton.setHeight ("50px");

        return buton;
    }

    private void restartGame(List<Button> buttonList) {
        buttonList = buttonList.stream ().filter (button -> button.getIcon () != null && button.getIcon ().equals (FontAwesome.RA)).collect (Collectors.toList ());

        buttonList.stream ().forEach (button -> {
            button.setIcon (FontAwesome.CIRCLE_O);
            button.removeStyleName (ValoTheme.BUTTON_DANGER);
            button.removeStyleName (ValoTheme.BUTTON_FRIENDLY);

        });
    }
}
