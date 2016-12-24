package engine;

import java.util.HashMap;
import java.util.Map;

/*
first, create an EventHandler in main class:
EventHandler MyEventHandler = new EventHandler();
then, create new events like this (outside of the class, that is):
EventHandler.Event MyNewEvent = new MyEventHandler.Event("MyEvent");
by default, this event will not have occurred, it will be "false" in the events map
*/

public class EventHandler {
	
	public EventHandler() {
		//events can be added here
	}
	class Event {
		public Event(String name) {
			events.put(name, false);
		}
	}

	public Map<String, Boolean> events = new HashMap<String, Boolean>();
	
	//more code needed to determine, what happens when an event is true, and to check if it has happened
}
