package simple_calendar.simple_calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class Day
{
	LocalDate date;
	int noOfHours;
	int noOfEvents;
	List<Task> tasksOnTheDay;
	int tasksDone;
	
	public Day(LocalDate d)
	{
		date = d;
		if(date.getDayOfWeek()==DayOfWeek.SUNDAY || date.getDayOfWeek()==DayOfWeek.SATURDAY)
			noOfHours = 10;
		else
			noOfHours = 4;
		
		noOfEvents = 0;
		for(Event e : MainWindow.eventList)
		{
			if(e.dateOfEvent.isEqual(date))
			{
				noOfEvents++;
			}
		}
		noOfHours-=noOfEvents;  // For now considering 1 hour for each event as didn't 
		if(noOfHours<0)			// take input of expected time for a event
			noOfHours = 0;
		tasksDone = 0;
	}
	
	public Day(LocalDate d,int numOfDoneTasks)
	{
		date = d;
		if(date.getDayOfWeek()==DayOfWeek.SUNDAY || date.getDayOfWeek()==DayOfWeek.SATURDAY)
			noOfHours = 10;
		else
			noOfHours = 4;
		
		noOfEvents = 0;
		for(Event e : MainWindow.eventList)
		{
			if(e.dateOfEvent.isEqual(date))
			{
				noOfEvents++;
			}
		}
		noOfHours-=noOfEvents;  // For now considering 1 hour for each event as didn't 
		if(noOfHours<0)			// take input of expected time for a event
			noOfHours = 0;
		tasksDone = numOfDoneTasks;
		noOfHours -= numOfDoneTasks;
	}
	
	public void setTasksForDay()
	{
		
	}
}
