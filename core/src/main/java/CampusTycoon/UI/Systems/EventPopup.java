package CampusTycoon.UI.Systems;

import CampusTycoon.GameUtils;
import CampusTycoon.InputHandler;
import CampusTycoon.GameLogic.Events.Event;
import CampusTycoon.UI.Component;
import CampusTycoon.UI.Drawer;

import java.util.List;
import java.util.ArrayList;

public class EventPopup {
	public Event event;
	public List<Component> elements;
	public List<Component> buttonElements; // Only elements which are passed to the InputHandler

    public EventPopup(Event Event) {
		event = Event;
    }

	public void initialise() {
		elements = new ArrayList<Component>();
		buttonElements = new ArrayList<Component>();
		GameUtils.createEventPopupUI(event);
	}

    public void close() {
		for (Component element : buttonElements) {
			InputHandler.remove(element);
		}

		for (Component element : elements) {
			Drawer.remove(0, element);
		}
    }
}
