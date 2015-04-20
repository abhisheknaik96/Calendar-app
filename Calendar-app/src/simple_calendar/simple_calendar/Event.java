package simple_calendar.simple_calendar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.*;
import java.util.Collections;
import java.util.Comparator;

public class Event implements Serializable
{
	private static final long serialVersionUID = -8465116787318420577L;
	String name;
	String details;
	LocalTime timeOfEvent;
	LocalDate dateOfEvent;
	Alarm alarm;

	public Event(String eventName, String eventDetails, int d, int m, int y,
			int hr, int min)
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

	public static void integrateMoodleCalendar(String outFileName) throws IOException
	{
		BufferedReader in = null;
		String line;
//		String fileName = "icalexport.ics";
		String eventName = null;
		int d,m,y;
		int hr, min;
		d=m=y=hr=min=0;
		
		try
		{
			in = new BufferedReader(new FileReader(outFileName));

			while ((line= in.readLine()) != null)
			{
				if(line.contains("SUMMARY")==true)
					eventName = line.substring(8);
				if(line.contains("DTSTART"))
				{
					y = Integer.parseInt( line.substring(8,12) );
					m = Integer.parseInt( line.substring(12,14) );
					d = Integer.parseInt( line.substring(14,16) );
					
					hr = Integer.parseInt( line.substring(17,19) );
					min = Integer.parseInt( line.substring(19,21) );
				}
				if(eventName!=null && (y*d*m*hr*min)!=0 )
				{
					Event newEvent = new Event(eventName, "Buck up!", d, m, y, hr, min);
					MainWindow.eventList.add(newEvent);
					eventName = null;
					d=m=y=hr=min=0;
				}
			}
		} 
		finally
		{
			if (in != null)
				in.close();
		}
		MainWindow.update();
	}

}