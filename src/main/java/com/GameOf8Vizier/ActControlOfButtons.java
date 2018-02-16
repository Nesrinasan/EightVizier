package com.GameOf8Vizier;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nesrin ULGAY on 1/31/2018.
 */
public class ActControlOfButtons implements IActControlOfButtons {

    @Override
    public int horizontalButtonControl(HorizontalLayout horizontalLayout, int horizontalButtonCount) {

        int i = 1;

        while (i <= 8) {

            Button kontroledilenButon = (Button) horizontalLayout.getComponent (i);

            Resource icon = kontroledilenButon.getIcon ();
            if (icon != null && icon.equals (FontAwesome.CIRCLE_O)) {
                horizontalButtonCount++;
            }
            i++;
        }
        return horizontalButtonCount;
    }


    @Override
    public int verticalButtonControl(Map<HorizontalLayout, Map<Integer, FontAwesome>> genelKontrol, int result, int dikeyButonControl) {
        Set<HorizontalLayout> keys = genelKontrol.keySet ();
        for (HorizontalLayout horizontal : keys) {
            for (int i = 0; i <= 8; i++) {
                Button controlledButton = (Button) horizontal.getComponent (i);
                Map<Integer, Integer> controlledOfButtonData = (Map<Integer, Integer>) controlledButton.getData ();
                Set<Integer> keyList = controlledOfButtonData.keySet ();
                for (Integer controlledData : keyList) {
                    if (controlledData % 10 == result) {
                        if (controlledButton.getIcon () != null && controlledButton.getIcon ().equals (FontAwesome.CIRCLE_O)) {
                            dikeyButonControl++;
                        }
                    }
                }
            }
        }
        return dikeyButonControl;
    }

    @Override
    public int crossLeftCount(List<Button> buttonList, int xCrossLocal, int yCrossLocal, int crossLeftCount) {

        for (int i = xCrossLocal; i >= 1; i--) {
            if (yCrossLocal == 1 || i == 1) {
                xCrossLocal = i;
                break;
            }
            yCrossLocal--;
        }

        for (Button button : buttonList) {
            Map<Integer, Integer> MapControlForCrossLeftButton = new HashMap<> ();
            MapControlForCrossLeftButton.put (xCrossLocal, yCrossLocal);
            if (button.getData ().equals (MapControlForCrossLeftButton)) {
                if (button.getIcon () != null && button.getIcon ().equals (FontAwesome.RA)) {
                    crossLeftCount++;

                }

                MapControlForCrossLeftButton = new HashMap<> ();
                xCrossLocal = xCrossLocal + 1;
                yCrossLocal++;
                MapControlForCrossLeftButton.put (xCrossLocal, yCrossLocal);
            }
        }

        return crossLeftCount;
    }

    @Override
    public int crossRightCount(List<Button> buttonList, int xCrossLocal, int yCrossLocal, int crossRightCount) {
        for (int i = xCrossLocal; i <= 8; i++) {
            if (yCrossLocal == 1 || i == 8) {
                xCrossLocal = i;
                break;
            }
            yCrossLocal--;
        }

        for (Button button : buttonList) {
            Map<Integer, Integer> MapControlForCrossRightButton = new HashMap<> ();
            MapControlForCrossRightButton.put (xCrossLocal, yCrossLocal);
            if (button.getData ().equals (MapControlForCrossRightButton)) {
                if (button.getIcon () != null && button.getIcon ().equals (FontAwesome.RA)) {
                    crossRightCount++;

                }

                MapControlForCrossRightButton = new HashMap<> ();
                xCrossLocal = xCrossLocal - 1;
                yCrossLocal++;
                MapControlForCrossRightButton.put (xCrossLocal, yCrossLocal);
            }

        }
        return crossRightCount;
    }

    @Override
    public void ControlWin(List<Button> buttonList) {
        List<Button> buttonListForWin = buttonList.stream ().filter (button -> (button.getIcon () != null && button.getIcon ().equals (FontAwesome.RA))).collect (Collectors.toList ());
        if (buttonListForWin.size () == 8) {
            Notification.show ("CONGRATULATIONS, YOU WON", Notification.Type.ERROR_MESSAGE);

            buttonListForWin.stream ().forEach (button -> {
                button.removeStyleName (ValoTheme.BUTTON_DANGER);
                button.addStyleName (ValoTheme.BUTTON_FRIENDLY);
            });

        } else {
            buttonListForWin.stream ().forEach (button -> {
                button.removeStyleName (ValoTheme.BUTTON_FRIENDLY);
                button.addStyleName (ValoTheme.BUTTON_DANGER);
            });

        }
    }
}
