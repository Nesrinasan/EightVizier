package com.pany;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.*;


@Theme("mytheme")
public class GameOf8Vizier extends UI implements IBaseDesignGame {

    VerticalLayout mainLayout = new VerticalLayout();
    HorizontalLayout layout = null;
    Button buton;
    List<Button> buttonList;
    List<HorizontalLayout> horLayouts;
    Map<HorizontalLayout, Map<Integer, FontAwesome>> genelKontrol;
    List<Button> buttonListExpectForNullData;

    public GameOf8Vizier() {
        inits();
        putButtonsOnBoard(mainLayout);
        startGame();
    }

    protected void inits() {
        setContent(mainLayout);
    }

    @Override
    public void putButtonsOnBoard(VerticalLayout mainLayout) {

        horLayouts = new ArrayList<>();
        buttonList = new ArrayList<>();
        Map<Integer, FontAwesome> verticalButtonsControllerMap = new HashMap<>();

        genelKontrol = new HashMap<>();
        int buttonYLocal = 0;

        for (int horizontalButtonCount = 0; horizontalButtonCount <= 8; horizontalButtonCount++) {
            int buttonXLocal = 1;

            layout = new HorizontalLayout();

            for (int verticalButtonCount = 0; verticalButtonCount <= 8; verticalButtonCount++) {

                if (horizontalButtonCount == 0) {
                    createHorizontalNumberButon(verticalButtonCount);
                } else if (horizontalButtonCount != 0 && verticalButtonCount == 0) {
                    createVerticalNumberButons(horizontalButtonCount);
                } else {
                    createGameButtons();

                }
                buttonXLocal++;
                addButtonLocal(buttonYLocal, buttonXLocal);
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

    @Override
    public void addButtonLocal(int buttonYLocal, int buttonXLocal) {
        Map<Integer, Integer> butonLocals = new HashMap<>();
        butonLocals.put(buttonXLocal, buttonYLocal);
        buton.setData(butonLocals);
    }

    @Override
    public void createGameButtons() {
        buton = new Button();
        buton.setWidth("50px");
        buton.setHeight("50px");
        buton.setIcon(FontAwesome.CIRCLE_O);
    }

    private void startGame() {

        for (HorizontalLayout horizontalLayout : horLayouts) {
            for (int i = 0; i <= 8; i++) {

                buttonAddClickListener(genelKontrol, horizontalLayout, i);
            }
        }
    }


    private List<Button> ButtonListWithData() {
        buttonListExpectForNullData = new ArrayList<>();
        for (Button button : buttonList) {
            buttonListExpectForNullData.add(button);
        }
        return buttonListExpectForNullData;
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
                    if (dataSecbtn != null) {

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
                        crossLeftCount = actControlOfButtons.crossLeftCount(ButtonListWithData(), xCrossLocal, yCrossLocal, crossLeftCount);

                        /**
                         Right side cross button control
                         */
                        crossRightCount = actControlOfButtons.crossRightCount(ButtonListWithData(), xCrossLocal, yCrossLocal, crossRightCount);

                        if (horizontalButtonCount == 8 && verticalButtonCount == 8 && crossLeftCount == 0 && crossRightCount == 0) {
                            selectedButton.setIcon(FontAwesome.RA);
                            selectedButton.addStyleName(ValoTheme.BUTTON_DANGER);

                        }
                        actControlOfButtons.ControlWin(buttonList);
                    }
                }
            }

        });
    }

    @Override
    public void createHorizontalNumberButon(int buttonCaption) {
        String buttonCaptions = String.valueOf(buttonCaption);

        buton = new Button(buttonCaptions);
        if (buttonCaption == 0) {
            buton.setIcon(FontAwesome.TIMES_CIRCLE);
            buton.setCaption("");
            buton.setEnabled(true);
            buton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    restartGame();
                }
            });
        } else {
            buton.setEnabled(false);
        }
        buton.setWidth("50px");
        buton.setHeight("50px");
    }

    private void restartGame() {
        for (Button button : buttonList) {
            if (button.getIcon() != null && button.getIcon().equals(FontAwesome.RA)) {
                button.setIcon(FontAwesome.CIRCLE_O);
                button.removeStyleName(ValoTheme.BUTTON_DANGER);
                button.removeStyleName(ValoTheme.BUTTON_FRIENDLY);
            }
        }
    }

    @Override
    public void createVerticalNumberButons(int buttonCaption) {
        String buttonCaptions = String.valueOf(buttonCaption);

        buton = new Button(buttonCaptions);
        if (buttonCaption == 0) {
            buton.setIcon(FontAwesome.TIMES_CIRCLE);
            buton.setCaption("");
            buton.setEnabled(true);
        } else {
            buton.setEnabled(false);
        }
        buton.setWidth("50px");
        buton.setHeight("50px");
    }


    @Override
    protected void init(VaadinRequest vaadinRequest) {
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = GameOf8Vizier.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
