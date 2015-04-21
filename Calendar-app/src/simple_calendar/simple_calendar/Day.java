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
		if(MainWindow.hoursOfWork!=null){
			if(date.getDayOfWeek()==DayOfWeek.MONDAY){
				noOfHours = MainWindow.hoursOfWork.get(0);
				System.out.println("Monday");
			}
			if(date.getDayOfWeek()==DayOfWeek.TUESDAY){
				noOfHours = MainWindow.hoursOfWork.get(1);
				System.out.println("Tuesday");
			}
			if(date.getDayOfWeek()==DayOfWeek.WEDNESDAY){
				noOfHours = MainWindow.hoursOfWork.get(2);
				System.out.println("Wednesday");
			}
			if(date.getDayOfWeek()==DayOfWeek.THURSDAY){
				noOfHours = MainWindow.hoursOfWork.get(3);
				System.out.println("Thursday");
			}
			if(date.getDayOfWeek()==DayOfWeek.FRIDAY){
				noOfHours = MainWindow.hoursOfWork.get(4);
				System.out.println("Friday");
			}
			if(date.getDayOfWeek()==DayOfWeek.SATURDAY){
				noOfHours = MainWindow.hoursOfWork.get(5);
				System.out.println("Saturday");
			}
			if(date.getDayOfWeek()==DayOfWeek.SUNDAY){
				noOfHours = MainWindow.hoursOfWork.get(6);
				System.out.println("Sunday");
			}
		}
		else{
			if(date.getDayOfWeek()==DayOfWeek.SUNDAY || date.getDayOfWeek()==DayOfWeek.SATURDAY)
				noOfHours = 10;
			else
				noOfHours = 4;
		}
		
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
		if(MainWindow.hoursOfWork!=null){
			if(date.getDayOfWeek()==DayOfWeek.MONDAY){
				noOfHours = MainWindow.hoursOfWork.get(0);
				System.out.println("Monday");
			}
			if(date.getDayOfWeek()==DayOfWeek.TUESDAY){
				noOfHours = MainWindow.hoursOfWork.get(1);
				System.out.println("Tuesday");
			}
			if(date.getDayOfWeek()==DayOfWeek.WEDNESDAY){
				noOfHours = MainWindow.hoursOfWork.get(2);
				System.out.println("Wednesday");
			}
			if(date.getDayOfWeek()==DayOfWeek.THURSDAY){
				noOfHours = MainWindow.hoursOfWork.get(3);
				System.out.println("Thursday");
			}
			if(date.getDayOfWeek()==DayOfWeek.FRIDAY){
				noOfHours = MainWindow.hoursOfWork.get(4);
				System.out.println("Friday");
			}
			if(date.getDayOfWeek()==DayOfWeek.SATURDAY){
				noOfHours = MainWindow.hoursOfWork.get(5);
				System.out.println("Saturday");
			}
			if(date.getDayOfWeek()==DayOfWeek.SUNDAY){
				noOfHours = MainWindow.hoursOfWork.get(6);
				System.out.println("Sunday");
			}
		}
		else{
			if(date.getDayOfWeek()==DayOfWeek.SUNDAY || date.getDayOfWeek()==DayOfWeek.SATURDAY)
				noOfHours = 10;
			else
				noOfHours = 4;
		}
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
