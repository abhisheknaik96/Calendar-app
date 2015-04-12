package simple_calendar.simple_calendar;

import java.time.*;
import java.util.Collections;
import java.util.Comparator;

public class Event
{
	String name;
	String details;
	LocalTime timeOfEvent;
	LocalDate dateOfEvent;
	Alarm alarm;
	
	// String thingsToCarry;

	public Event(String eventName, String eventDetails, int d, int m, int y, int hr, int min)
	{
		name = eventName;
		details = eventDetails;
		timeOfEvent = LocalTime.of(hr, min, 0);
		dateOfEvent = LocalDate.of(y, m, d);
	}
	
	public void setAlarm(Alarm a)
	{
		alarm = a;
	}
	
	public Alarm getAlarm()
	{
		return this.alarm;
	}
	
	public LocalDate getDate()
	{
		return dateOfEvent;
	}
	
	public LocalTime getTime()
	{
		return timeOfEvent;
	}
	
	public String getEventName()
	{
		return name;
	}
	
	static void sortEventsByDate()
	{
		Collections.sort(MainWindow.eventList, new Comparator<Event>()
		{
			public int compare(Event c1, Event c2)
			{
				if (c1.dateOfEvent.isBefore(c2.dateOfEvent))
					return -1;
				else if (c1.dateOfEvent.isAfter(c2.dateOfEvent))
					return 1;
				else
				{
					if (c1.timeOfEvent.isBefore(c2.timeOfEvent))
						return -1;
					else
						return 1;
				}
			}
		});
	}
	
	
	
}