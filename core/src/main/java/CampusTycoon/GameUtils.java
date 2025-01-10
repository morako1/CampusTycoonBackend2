package CampusTycoon;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import CampusTycoon.GameLogic.Events.StrikeEvent;
import CampusTycoon.GameLogic.Map;
import CampusTycoon.GameLogic.SatisfactionMeter;
import CampusTycoon.UI.Camera;
import CampusTycoon.UI.Component;
import CampusTycoon.UI.Component.Actions;
import CampusTycoon.UI.Component.Anchor;
import CampusTycoon.UI.Components.Leaderboard;
import CampusTycoon.UI.Drawer;
import CampusTycoon.UI.Components.Backdrop;
import CampusTycoon.UI.Components.Button;
import CampusTycoon.UI.Components.MenuText;
import CampusTycoon.GameLogic.BuildingCounter;
import CampusTycoon.GameLogic.Events.Event;
import CampusTycoon.GameLogic.Buildings.*;
import CampusTycoon.GameLogic.Timer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.sun.source.tree.TryTree;


import static CampusTycoon.GameLogic.Timer.getFinalScore;

public class GameUtils{
    public static Map map;
    public static Event currentEvent;

    public static void startGame() {
        map = new Map();
        Camera.map = map;
    }

    // Gets the image used for hover displays (just a semi-transparent version of
    // the original)
    public static String getHoverImagePath(String originalImage) {


        switch (originalImage) {
            case Accommodation.defaultImage:
                // e.g. "Accommodation.png" -> "AccommodationTransparent.png"
                return Accommodation.defaultImage.replace(".png", "Transparent.png");
            case Study.defaultImage:
                return Study.defaultImage.replace(".png", "Transparent.png");
            case Cafeteria.defaultImage:
                return Cafeteria.defaultImage.replace(".png", "Transparent.png");
            case Relaxation.defaultImage:
                return Relaxation.defaultImage.replace(".png", "Transparent.png");
            case Restaurant.defaultImage:
                return Restaurant.defaultImage.replace(".png", "Transparent.png");
            default:
                System.out.print(
                    "ERROR: Could not retrieve hover image for \"" + originalImage + "\"");
                return "MissingTexture.png";
        }
    }

    public static void createStartMenuUI() {
        Button gameLogo = new Button("StartscreenLogo.png", 0, 220, 300, 100);
        gameLogo.setAnchor(Anchor.Centre);

        Button buttonNewGame = new Button("New Game.png", 0, 90, 262, 66);
        buttonNewGame.setClickAction(Actions.OpenGameplayScreen);
        buttonNewGame.setAnchor(Anchor.Centre);

        Button buttonLeaderboard = new Button("Leaderboard.png", 0, 20, 262, 66);
        buttonLeaderboard.setClickAction(Actions.OpenLeaderboardScreen);
        buttonLeaderboard.setAnchor(Anchor.Centre);

        Button buttonQuit = new Button("Quit.png", 0, -50, 262, 66);
        buttonQuit.setClickAction(Actions.QuitGame);
        buttonQuit.setAnchor(Anchor.Centre);

        List<Component> startScreenButtonList = Arrays.asList(
            gameLogo,
            buttonNewGame,
            buttonLeaderboard,
            buttonQuit
        );

        // Add all buttons to the drawQueue
        for (Component button : startScreenButtonList) {
            // All added to layer '1' (generally on top of most other UI elements)
            Drawer.add(1, button);
        }

        // Add all buttons to the InputHandler to allow for interaction handling
        // (Allows buttons to be clicked and things to actually happen)
        InputHandler.add(startScreenButtonList);
    }

    public static void createGameplayUI() {
        Button buttonAccommodation = new Button("Buildings\\Accommodation.png", -500, 10, 90, 66);
        buttonAccommodation.setClickAction(Actions.ToggleAccommodationBuilding);
        buttonAccommodation.setAnchor(Anchor.BottomCentre);

        Button buttonPause = new Button("Pause.png", 0, 0, 80, 80);
        buttonPause.setClickAction(Actions.Pause);
        //buttonPause.setClickAction(Actions.OpenEventPopup);
        buttonPause.setAnchor(Anchor.TopLeft);

        MenuText accommodationCount = new MenuText(
            String.valueOf("Housing"),
            -550, 110, 2f, 2f);
        accommodationCount.setAnchor(Anchor.BottomCentre);
        BuildingCounter.UI.add(accommodationCount);

        Button buttonStudy = new Button("Buildings\\Study.png", -300, 10, 90, 66);
        buttonStudy.setClickAction(Actions.ToggleStudyBuilding);
        buttonStudy.setAnchor(Anchor.BottomCentre);

        MenuText studyCount = new MenuText(
            String.valueOf("Study"),
            -350, 110, 2f, 2f);
        studyCount.setAnchor(Anchor.BottomCentre);
        BuildingCounter.UI.add(studyCount);

        Button buttonCafe = new Button("Buildings\\Cafeteria.png", -100, 10, 90, 66);
        buttonCafe.setClickAction(Actions.ToggleCafeteriaBuilding);
        buttonCafe.setAnchor(Anchor.BottomCentre);

        MenuText cafeCount = new MenuText(
            String.valueOf("Cafe"),
            -150, 110, 2f, 2f);
        cafeCount.setAnchor(Anchor.BottomCentre);
        BuildingCounter.UI.add(cafeCount);

        Button buttonRelax = new Button("Buildings\\Relaxation.png", 100, 10, 90, 66);
        buttonRelax.setClickAction(Actions.ToggleRelaxationBuilding);
        buttonRelax.setAnchor(Anchor.BottomCentre);

        MenuText relaxCount = new MenuText(
            String.valueOf("Park"),
            50, 110, 2f, 2f);
        relaxCount.setAnchor(Anchor.BottomCentre);
        BuildingCounter.UI.add(relaxCount);

        Button buttonRestaurant = new Button("Buildings\\Restaurant.png", 300, 10, 90, 66);
        buttonRestaurant.setClickAction(Actions.ToggleRestaurantBuilding);
        buttonRestaurant.setAnchor(Anchor.BottomCentre);

        MenuText restaurantCount = new MenuText(
            String.valueOf("Restaurant"), 250, 110, 2f, 2f);
        restaurantCount.setAnchor(Anchor.BottomCentre);
        BuildingCounter.UI.add(restaurantCount);

        Button buttonPH6 = new Button("Placeholder.png", 500, 10, 90, 66);
        buttonPH6.setAnchor(Anchor.BottomCentre);

        Button buttonDollar = new Button("Dollar.png", -300, 0, 70, 66);
        buttonDollar.setAnchor(Anchor.TopCentre);

        Button buttonHouses = new Button("House.png", 0, 0, 70, 66);
        buttonHouses.setAnchor(Anchor.TopCentre);

        Button buttonPeople = new Button("Person.png", 300, 0, 60, 66);
        buttonPeople.setAnchor(Anchor.TopCentre);

        //Button notif1 = new Button("ExclamationMark.png", -12, 0, 100, 80);
        //notif1.setClickAction(Actions.OpenEventPopup);
//		notif1.setAnchor(Anchor.TopLeft);

//		Button notif2 = new Button("Play.png", 0, 80, 80, 80);
//		notif2.setClickAction(Actions.Resume);
//		notif2.setAnchor(Anchor.TopLeft);


        //currentEvent = new StrikeEvent();

        Button buttonSatisfaction = new Button("Satisfaction.png", 100, 10, 200, 66);
        buttonSatisfaction.setAnchor(Anchor.TopRight);

        List<Component> UIButtons = Arrays.asList(
            buttonAccommodation, buttonStudy, buttonCafe, buttonRelax, buttonRestaurant, buttonPH6,
            /*notif2, notif1,*/ buttonPause, buttonSatisfaction,
            buttonDollar, buttonHouses, buttonPeople);

        // Add all buttons to the drawQueue
        for (Component button : UIButtons) {
            // All added to layer '1' (generally on top of most other UI elements)
            Drawer.add(1, button);
        }

        // Add all buttons to the InputHandler to allow for interaction handling
        InputHandler.add(UIButtons);

        MenuText satisfactionText = new MenuText("", 50, 30, 2f, 2f);
        satisfactionText.setAnchor(Anchor.TopRight);
        SatisfactionMeter.satisfactionText = satisfactionText;
        SatisfactionMeter.updateDisplay();

//		MenuText notifText1 = new MenuText("Notification 1", 130, 23, 1.5f, 1.5f);
//		notifText1.setAnchor(Anchor.TopLeft);
//
        MenuText notifText2 = new MenuText("Notification 2", 135, 105, 1.5f, 1.5f);
        notifText2.setAnchor(Anchor.TopLeft);

        MenuText buildingCounterText = new MenuText(String.valueOf(BuildingCounter.getTotalBuildingCount()), 70, 25, 2f,
            2f);
        buildingCounterText.setAnchor(Anchor.TopCentre);
        BuildingCounter.totalCountUI = buildingCounterText;

        MenuText timerText = new MenuText("Time: " + Timer.getTimeRemaining(), 135, 100, 2f, 2f);
        timerText.setAnchor(Anchor.TopRight);
        Timer.text = timerText;

        List<Component> textElements = Arrays.asList(satisfactionText, /*notifText1,*/ notifText2, buildingCounterText,
            accommodationCount, studyCount, cafeCount, relaxCount, restaurantCount, timerText);

        // Add all text to the drawQueue
        for (Component text : textElements) {
            // All added to layer '2' (on top of almost all other UI elements)
            Drawer.add(2, text);
        }
        // No need to add text to the InputHandler (unless you really want to be able to
        // click on it for some reason)
    }

//    public static void pauseModeUI(){
//        Button resumeGame = new Button("Play.png", 100, 0, 80, 80);
//        resumeGame.setClickAction(Actions.Resume);
//        resumeGame.setAnchor(Anchor.TopLeft);
//        Drawer.add(1, resumeGame);
//    }




    //(Assessment 2) This will tell the user the effects of their choice
    public static void EventResultPopup(){
        Timer.isRunning = false;


        Backdrop eventScreenBackdrop = new Backdrop("Backdrop.png", 0, 30, 400, 350);
        eventScreenBackdrop.setAnchor(Anchor.Centre);
        eventScreenBackdrop.update();
        currentEvent.eventUI.elements.add(eventScreenBackdrop);
        Drawer.add(1, eventScreenBackdrop); // Layer 1 so its behind the rest of the UI


        Button buttonAccept = new Button("Accept.png", 0, -100, 126, 66);
        buttonAccept.setClickAction(Actions.EndEvent);
        buttonAccept.setAnchor(Anchor.Centre);
        buttonAccept.value = 1; // Used so the Event class knows which button was clicked



        buttonAccept.update();
        currentEvent.eventUI.elements.add(buttonAccept);
        Drawer.add(2, buttonAccept);

        InputHandler.add(buttonAccept);
        List<Component> eventChoices = Arrays.asList(buttonAccept);

        currentEvent.eventUI.buttonElements = eventChoices;



        MenuText testText = new MenuText(
            currentEvent.resultText,
            -eventScreenBackdrop.getBaseWidth() / 2 + eventScreenBackdrop.getBaseX() + 15,
            eventScreenBackdrop.getBaseHeight() / 2 + eventScreenBackdrop.getBaseY() - 12,
            1.5f, 1.5f);
        testText.setAnchor(Anchor.Centre);
        testText.update();
        currentEvent.eventUI.elements.add(testText);
        Drawer.add(2, testText);

    }


    public static void createEventPopupUI(Event event) {
        Backdrop eventScreenBackdrop = new Backdrop("Backdrop.png", 0, 30, 400, 350);
        eventScreenBackdrop.setAnchor(Anchor.Centre);
        eventScreenBackdrop.update();
        event.eventUI.elements.add(eventScreenBackdrop);
        Drawer.add(1, eventScreenBackdrop); // Layer 1 so its behind the rest of the UI

        Button buttonAccept = new Button("Accept.png", -110, -100, 126, 66);
        buttonAccept.setClickAction(Actions.ChooseEventOption);
        buttonAccept.setAnchor(Anchor.Centre);
        buttonAccept.value = 1; // Used so the Event class knows which button was clicked

		/*Button buttonNeutral = new Button("Neutral.png", 0, -106, 126, 66);
		buttonNeutral.setClickAction(Actions.ChooseEventOption);
		buttonNeutral.setAnchor(Anchor.Centre);
		buttonNeutral.value = 2;*/

        Button buttonReject = new Button("Reject.png", 110, -100, 126, 66);
        buttonReject.setClickAction(Actions.ChooseEventOption);
        buttonReject.setAnchor(Anchor.Centre);
        buttonReject.value = 2;



        List<Component> eventChoices = Arrays.asList(buttonAccept, buttonReject);

        for (Component button : eventChoices) {
            // All added to layer '2' (on top of almost all other UI elements)
            button.update();
            event.eventUI.elements.add(button);
            Drawer.add(2, button);
        }
        InputHandler.add(eventChoices);
        event.eventUI.buttonElements = eventChoices;

        // MenuText eventTextTitle = new MenuText("Event 1", 0, 0, 0, 0);
        // eventTextTitle.setAnchor(Anchor.Centre);
        MenuText testText = new MenuText(
            event.eventText,
            -eventScreenBackdrop.getBaseWidth() / 2 + eventScreenBackdrop.getBaseX() + 15,
            eventScreenBackdrop.getBaseHeight() / 2 + eventScreenBackdrop.getBaseY() - 12,
            1.5f, 1.5f);
        testText.setAnchor(Anchor.Centre);
        testText.update();
        event.eventUI.elements.add(testText);
        Drawer.add(2, testText);
    }

    public static void createEndScreenUI() {
        Button buttonSaveScore = new Button("Save Score.png", 0, 90, 262, 66);
        buttonSaveScore.setClickAction(Actions.OpenSaveScreen);
        buttonSaveScore.setAnchor(Anchor.Centre);

        MenuText finalScore = new MenuText("Score: "+ (getFinalScore()), -50, 200, 2f, 2f);
        finalScore.setAnchor(Anchor.Centre);
        Drawer.add(1, finalScore);

        Button buttonMainMenu = new Button("Main Menu.png", 0, 20, 262, 66);
        buttonMainMenu.setClickAction(Actions.OpenStartScreen);
        buttonMainMenu.setAnchor(Anchor.Centre);

        Button buttonNewGame = new Button("New Game.png", 0, -50, 262, 66);
        buttonNewGame.setClickAction(Actions.OpenGameplayScreen);
        buttonNewGame.setAnchor(Anchor.Centre);

        List<Component> endScreenButtonList = Arrays.asList(
            buttonSaveScore,
            buttonMainMenu,
            buttonNewGame);

        // Add all buttons to the drawQueue
        for (Component button : endScreenButtonList) {
            // All added to layer '1' (generally on top of most other UI elements)
            Drawer.add(1, button);
        }

        // Add all buttons to the InputHandler to allow for interaction handling
        // (Allows buttons to be clicked and things to actually happen)
        InputHandler.add(endScreenButtonList);
    }
    public static void leaderboardUI() {
        Button buttonMainMenu = new Button("Main Menu.png", 0, -300, 262, 66);
        buttonMainMenu.setClickAction(Actions.OpenStartScreen);
        buttonMainMenu.setAnchor(Anchor.Centre);

        //Assessment 2
        //Added text for Leaderboard





          String leaderBoardString =  Leaderboard.displayLeaderboard();

            MenuText leaderboardText = new MenuText(leaderBoardString, -60f,100,1.5f,1.5f);
            leaderboardText.setAnchor(Anchor.Centre);
            Drawer.add(2,leaderboardText);



     //   Map leaderboardMap = Leaderboard.


        //leaderboardText.text =

        Button leaderboardTitle = new Button(("Leaderboard.png"), 0, 320, 262, 66);
        leaderboardTitle.setAnchor(Anchor.Centre);



        List<Component> endScreenButtonList = Arrays.asList(
            leaderboardTitle,
            buttonMainMenu
        );

        // Add all buttons to the drawQueue
        for (Component button : endScreenButtonList) {
            // All added to layer '1' (generally on top of most other UI elements)
            Drawer.add(1, button);
        }



        // Add all buttons to the InputHandler to allow for interaction handling
        // (Allows buttons to be clicked and things to actually happen)
        InputHandler.add(endScreenButtonList);
    }

    public static void createSaveScreenUI() {






    }



}

