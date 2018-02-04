package com.pany;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

/**
 * Created by Universal on 1/23/2018.
 */
public interface IBaseDesignGame {

    public Button createGameButtons(Button button);

    public void addButtonLocal(int buttonYLocal, int buttonXLocal, Button button);

    public Button createVerticalNumberButons(int buttonCaption, Button button);

    public Button createHorizontalNumberButon(int buttonCaption, Button button, List<Button> buttonList);

}
