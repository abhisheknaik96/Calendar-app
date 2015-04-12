package simple_calendar.simple_calendar;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;

public class Task
{
	String name;
	String details;
	LocalTime timeOfDeadline;
	LocalDate dateOfDeadline;
	int priority;
	float timeExpected;
	float gain;
	Alarm alarm;

	public Task(String taskName, int d, int m, int y, int hr, int min,
			float g, float t, int p)
	{
		name = taskName;
		timeOfDeadline = LocalTime.of(hr, min, 0);
		dateOfDeadline = LocalDate.of(y, m, d);
		priority = p;
		gain = g;
		timeExpected = t;
	}

	public void printTask()
	{
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
	
	static void sortTasksByDate()
	{
		Collections.sort(MainWindow.taskList, new Comparator<Task>()
		{
			public int compare(Task c1, Task c2)
			{
				if (c1.dateOfDeadline.isBefore(c2.dateOfDeadline))
					return -1;
				else if (c1.dateOfDeadline.isAfter(c2.dateOfDeadline))
					return 1;
				else
				{
					if (c1.timeOfDeadline.isBefore(c2.timeOfDeadline))
						return -1;
					else if (c1.timeOfDeadline.isAfter(c2.timeOfDeadline))
						return 1;
					else 
						return 0;
				}
			}
		});
	}
	
	static void sortTasksByExpectedTime()
	{
		Collections.sort(MainWindow.taskList, new Comparator<Task>()
		{
			public int compare(Task c1, Task c2)
			{
				if (c1.timeExpected < c2.timeExpected)
					return -1;
				else if (c1.timeExpected > c2.timeExpected)
					return 1;
				else
					return 0;
			}
		});
	}
	
	
	static void ShortestTaskFirstScheduler()
	{
		sortTasksByExpectedTime();
		
		// Now times have to alloted after the 'classesEnd' time.
	}
}
