package simple_calendar.simple_calendar;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
	String name;
	String details;
	LocalTime timeOfDeadline;
	LocalDate dateOfDeadline;
	int priority;
	float timeExpected;
	float gain;
	Alarm alarm;
	
	// String thingsToCarry;

	public Task(String eventName, int d, int m, int y, int hr, int min, float g, float t, int p )
	{
		name = eventName;
		timeOfDeadline = LocalTime.of(hr, min, 0);
		dateOfDeadline = LocalDate.of(y, m, d);
		priority = p;
		gain = g;
		timeExpected = t;
	}
	
	public void printTask(){
		System.out.println(name);
		System.out.println(priority);
		System.out.println(gain);
		System.out.println(timeExpected);
		System.out.println(timeOfDeadline.toString());
		System.out.println(dateOfDeadline.toString());
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
		return dateOfDeadline;
	}
}
