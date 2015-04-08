package simple_calendar.simple_calendar;

import java.time.*;

class Time
{
	int hour;
	int min;
	
	Time(int hr, int m)
	{
		hour = hr;
		min = m;
	}
}

public class Event
{
	String name;
	String details;
	LocalTime timeOfEvent;
	LocalDate dateOfEvent;
	
	// String thingsToCarry;

	public Event(String eventName, String eventDetails, int d, int m, int y, int hr, int min)
	{
		name = eventName;
		details = eventDetails;
		timeOfEvent = LocalTime.of(hr, min, 0);
		dateOfEvent = LocalDate.of(y, m, d);
	}
}