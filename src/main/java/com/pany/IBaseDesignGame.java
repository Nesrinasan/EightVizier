package com.pany;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Universal on 1/23/2018.
 */
public interface IBaseDesignGame {

    public void createGameButtons();

    public void addButtonLocal(int buttonYLocal, int buttonXLocal);

    public void putButtonsOnBoard(VerticalLayout verticalLayout);

    public void createVerticalNumberButons(int buttonCaption);

    public void createHorizontalNumberButon(int buttonCaption);

}
