package CampusTycoon.UI.Components;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;

import CampusTycoon.GameLogic.Events.Event;
import CampusTycoon.GameLogic.Events.StrikeEvent;
import CampusTycoon.GameLogic.Timer;
import CampusTycoon.UI.Component;
import CampusTycoon.UI.ScreenUtils;
import CampusTycoon.GameUtils;
import CampusTycoon.GameLogic.MapUtils;
import CampusTycoon.UI.saveScreen;
import com.badlogic.gdx.Gdx;

import static CampusTycoon.GameLogic.Timer.pause;
import static CampusTycoon.GameLogic.Timer.resume;



public class Button extends Component {
	public int value; // Used to carry information about which button was pressed (i.e. in events)

	public Button(float X, float Y, float Width, float Height) {
		super(X, Y, Width, Height);
	}

	public Button(String imagePath, float X, float Y, float Width, float Height) {
		super(imagePath, X, Y, Width, Height);
	}

	public Button(List<String> imagePaths, float X, float Y, float Width, float Height) {

		super(imagePaths, X, Y, Width, Height);
	}

	protected static void openStartScreen(Boolean isAction) {
		ScreenUtils.OpenStartScreen();
		System.out.println("Screen changed to StartScreen");
	}

    protected static void OpenSaveScreen(Boolean isAction) {
        ScreenUtils.OpenSaveScreen();
        System.out.println("Screen changed to Save Screen");
    }

    protected static void quitGame(Boolean isAction) {
        Gdx.app.exit();
    }

	protected static void openGameplayScreen(Boolean isAction) {
		ScreenUtils.openGameplayScreen();
		System.out.println("Screen changed to GameplayScreen");
	}

    protected static void openLeaderboardScreen(Boolean isAction) {
        ScreenUtils.OpenLeaderboardScreen();
        System.out.println("Screen changed to LeaderboardScreen");
    }

    //Assessment 2
    //Button to open leaderboard from save score screen
    protected static void openLeaderboardFromSave(Boolean isAction) {

        if(Objects.equals(saveScreen.nameField.getText(), "")){
            return;
        }



        ScreenUtils.OpenLeaderboardScreen();
        System.out.println("Screen changed to LeaderboardScreen");
    }


    protected static void openEventPopup(Boolean isAction) {
		GameUtils.currentEvent = new StrikeEvent();
		System.out.println("Event opened");
	}

//    protected static void openPausePopup(Boolean isAction) {
//        GameUtils.currentEvent = new Event();
//
//    }

	protected static void closeEventPopup(Boolean isAction) {
		GameUtils.currentEvent.eventUI.close();
		System.out.println("Event closed");
	}

    protected static void EndEvent(Boolean isAction) {

        Timer.isRunning = true;



        GameUtils.currentEvent.applyEffects();

        GameUtils.currentEvent.eventUI.close();



        GameUtils.currentEvent = null;

        if(!Timer.eventQueue.isEmpty()){

            Random random = new Random();
            Timer.eventQueue.remove(0);
            Timer.nextEvent = Timer.getTimeRemaining() -(random.nextInt((61))+20);

        }

        System.out.println("Event closed");
    }

	protected void chooseEventOption(Boolean isAction) {
		GameUtils.currentEvent.chooseOption(value);
	}

	protected static void toggleAccommodationBuilding(Boolean isAction) {
		GameUtils.map.toggleBuildingPlacement(MapUtils.Placement.AccommodationBuilding);
		printBuildingChange();
	}

    protected static void Pause(Boolean isAction){
        pause();

    }

    protected static void Resume(Boolean isAction){
        resume();
    }

	protected static void toggleStudyBuilding(Boolean isAction) {
		GameUtils.map.toggleBuildingPlacement(MapUtils.Placement.StudyBuilding);
		printBuildingChange();
	}

	protected static void toggleCafeteriaBuilding(Boolean isAction) {
		GameUtils.map.toggleBuildingPlacement(MapUtils.Placement.CafeteriaBuilding);
		printBuildingChange();
	}

	protected static void toggleRelaxationBuilding(Boolean isAction) {
		GameUtils.map.toggleBuildingPlacement(MapUtils.Placement.RelaxationBuilding);
		printBuildingChange();
	}

	protected static void ToggleRestaurantBuilding(Boolean isAction) {
		GameUtils.map.toggleBuildingPlacement(MapUtils.Placement.RestaurantBuilding);
		printBuildingChange();
	}

	private static void printBuildingChange() {
		System.out.println(
				"Toggled building placement mode to: " + GameUtils.map.placing +
						"\nToggled building placement type to: " + GameUtils.map.placementType);
	}

	@Override
	public void setClickAction(String Action) {
		Consumer<Boolean> action = a -> none(a);
		switch (Action) {
			// Screen changes
			case Actions.OpenStartScreen:
				action = a -> openStartScreen(a);
				break;
			case Actions.OpenGameplayScreen:
				action = a -> openGameplayScreen(a);
				break;
            case Actions.OpenLeaderboardScreen:
                action = a -> openLeaderboardScreen(a);
                break;
            case Actions.OpenLeaderboardFromSave:
                action = a -> openLeaderboardFromSave(a);
                break;
            case Actions.OpenSaveScreen:
                action = a -> OpenSaveScreen(a);
                break;

            case Actions.QuitGame:
                action = a -> quitGame(a);
                break;

			// Events
			case Actions.OpenEventPopup:
				action = a -> openEventPopup(a);
				break;
			case Actions.CloseEventPopup:
				action = a -> closeEventPopup(a);

            case Actions.EndEvent:
                action = a -> EndEvent(a);
				break;

			case Actions.ChooseEventOption:
				action = a -> chooseEventOption(a);
				break;

            case Actions.Pause:
                action = a -> pause();
                break;
            case Actions.Resume:
                action = a -> resume();
                break;

			// Building toggles
			case Actions.ToggleAccommodationBuilding:
				action = a -> toggleAccommodationBuilding(a);
				break;
			case Actions.ToggleStudyBuilding:
				action = a -> toggleStudyBuilding(a);
				break;
			case Actions.ToggleCafeteriaBuilding:
				action = a -> toggleCafeteriaBuilding(a);
				break;
			case Actions.ToggleRelaxationBuilding:
				action = a -> toggleRelaxationBuilding(a);
				break;
			case Actions.ToggleRestaurantBuilding:
				action = a -> ToggleRestaurantBuilding(a);
			default:
				System.out.println("Invalid action passed to button: " + this.toString());
				break;
		}
		clickAction = action;
	}

}
