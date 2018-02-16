package com.GameOf8Vizier;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.*;


@Theme("mytheme")
public class GameOf8Vizier extends UI {

    VerticalLayout mainLayout = new VerticalLayout();
    HorizontalLayout layout = null;
    Button buton;
    List<Button> buttonList;
    List<HorizontalLayout> horLayouts;
    Map<HorizontalLayout, Map<Integer, FontAwesome>> genelKontrol;

    public GameOf8Vizier() {
        inits();

    }

    protected void inits() {
        setContent(mainLayout);
        putButtonsOnBoard(mainLayout);
        startGame();
    }

    public void putButtonsOnBoard(VerticalLayout mainLayout) {

        BaseDesignGame baseDesignGame = new BaseDesignGame();

        horLayouts = new ArrayList<>();
        buttonList = new ArrayList<>();
        Map<Integer, FontAwesome> verticalButtonsControllerMap = new HashMap<>();

        genelKontrol = new HashMap<>();
        int buttonYLocal = 0;

        for (int horizontalButtonCount = 0; horizontalButtonCount <= 8; horizontalButtonCount++) {
            int buttonXLocal = 0;

            layout = new HorizontalLayout();

            for (int verticalButtonCount = 0; verticalButtonCount <= 8; verticalButtonCount++) {

                if (horizontalButtonCount == 0) {
                    buton = baseDesignGame.createHorizontalNumberButon(verticalButtonCount, buton, buttonList);
                } else if (horizontalButtonCount != 0 && verticalButtonCount == 0) {
                    buton = baseDesignGame.createVerticalNumberButons(horizontalButtonCount, buton);
                } else {
                    buton = baseDesignGame.createGameButtons(buton);

                }

                baseDesignGame.addButtonLocal(buttonYLocal, buttonXLocal, buton);
                buttonXLocal++;
                verticalButtonsControllerMap.put(buttonXLocal, FontAwesome.CIRCLE_O);
                layout.addComponent(buton);
                buttonList.add(buton);

            }
            buttonYLocal++;
            horLayouts.add(layout);
            mainLayout.addComponent(layout);
            genelKontrol.put(layout, verticalButtonsControllerMap);
        }
    }

    private void startGame() {

        horLayouts.stream().forEach(horLayout -> {
            for (int i = 0; i <= 8; i++) {
                buttonAddClickListener(genelKontrol, horLayout, i);
            }
        });
    }

    private void buttonAddClickListener(final Map<HorizontalLayout, Map<Integer, FontAwesome>> genelKontrol, final HorizontalLayout horizontalLayout, int i) {
        Button selectedButton = (Button) horizontalLayout.getComponent(i);

        selectedButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ActControlOfButtons actControlOfButtons = new ActControlOfButtons();
                int result = 0;

                int xCrossLocal = 0;
                int yCrossLocal = 0;

                int horizontalButtonCount = 0;
                int verticalButtonCount = 0;
                int crossLeftCount = 0;
                int crossRightCount = 0;

                if (selectedButton.getIcon().equals(FontAwesome.RA)) {
                    selectedButton.setIcon(FontAwesome.CIRCLE_O);
                    selectedButton.removeStyleName(ValoTheme.BUTTON_DANGER);
                    selectedButton.removeStyleName(ValoTheme.BUTTON_FRIENDLY);
                    actControlOfButtons.ControlWin(buttonList);

                } else {
                    Map<Integer, Integer> dataSecbtn = (Map<Integer, Integer>) selectedButton.getData();

                    for (Integer key : dataSecbtn.keySet()) {
                        result = key % 10;
                        yCrossLocal = dataSecbtn.get(key);
                        xCrossLocal = key;
                    }

                    /**
                     * row button control
                     */
                    horizontalButtonCount = actControlOfButtons.horizontalButtonControl(horizontalLayout, horizontalButtonCount);

                    /**
                     * column button control
                     */
                    verticalButtonCount = actControlOfButtons.verticalButtonControl(genelKontrol, result, verticalButtonCount);

                    /**
                     Left side cross button control
                     */
                    crossLeftCount = actControlOfButtons.crossLeftCount(buttonList, xCrossLocal, yCrossLocal, crossLeftCount);

                    /**
                     Right side cross button control
                     */
                    crossRightCount = actControlOfButtons.crossRightCount(buttonList, xCrossLocal, yCrossLocal, crossRightCount);

                    if (horizontalButtonCount == 8 && verticalButtonCount == 8 && crossLeftCount == 0 && crossRightCount == 0) {
                        selectedButton.setIcon(FontAwesome.RA);
                        selectedButton.addStyleName(ValoTheme.BUTTON_DANGER);

                    }
                    actControlOfButtons.ControlWin(buttonList);
                }
            }

        });
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = GameOf8Vizier.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
