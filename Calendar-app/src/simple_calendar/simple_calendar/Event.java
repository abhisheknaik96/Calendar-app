package simple_calendar.simple_calendar;

import java.time.*;



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
}