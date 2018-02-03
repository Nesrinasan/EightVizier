package com.pany;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

import java.util.List;
import java.util.Map;

/**
 * Created by Nesrin ULGAY on 2/3/2018.
 */

public interface IActControlOfButtons {

    public int horizontalButtonControl(HorizontalLayout horizontalLayout, int horizontalButtonCount);

    public int verticalButtonControl(Map<HorizontalLayout, Map<Integer, FontAwesome>> genelKontrol, int sonuc, int dikeyButonControl);

    public int crossLeftCount(List<Button> buttonList, int xCrossLocal, int yCrossLocal, int crossLeftCount);

    public int crossRightCount(List<Button> buttonList, int xCrossLocal, int yCrossLocal, int crossRightCount);

    public void ControlWin(List<Button> buttonList);

}