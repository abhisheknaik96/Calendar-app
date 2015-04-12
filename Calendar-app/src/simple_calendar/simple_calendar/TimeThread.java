package simple_calendar.simple_calendar;
// Import the standard Java classes
import java.util.*;
import java.io.File;
import java.text.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class TimeThread extends Thread
{
	
	String musicFile = "StayTheNight.mp3"; // For example
	Media sound = new Media(new File(musicFile).toURI().toString());
	
	public boolean running = false;
	private Set<Alarm> alarms = new HashSet<Alarm>();		// No need for this now
	
	/**
	 * Add an alarm to our list of monitored alarms
	 */
	public void addAlarm(Alarm alarm)
	{
		this.alarms.add(alarm);
	}

	/**
	 * Remove an alarm from our list of monitored alarms
	 */
	public void removeAlarm(Alarm alarm)
	{
		this.alarms.remove(alarm);
	}

	public void close()
	{
		this.running = false;
	}
	
	public void run()
	{
		this.running = true;
		DateFormat df = DateFormat.getDateTimeInstance();
		Calendar now = Calendar.getInstance();
		while (running)
		{
			try
			{
				// Set the current time
				now.setTimeInMillis(System.currentTimeMillis());

				// Output the current time, just for debugging purposes
				System.out.println(df.format(now.getTime()));

				// Check alarms
				//for (Iterator i = alarms.iterator(); i.hasNext();)
				//for (Iterator i = EventWindow.eventList.iterator(); i.hasNext();)
				
				List<Event> eList = MainWindow.eventList;
				for(int i=0;i<eList.size();i++)
				{
					// System.out.println("In here");
					Alarm alarm = eList.get(i).getAlarm();
					if (!alarm.isFired() && alarm.checkAlarm(now))
					{
						MediaPlayer mediaPlayer = new MediaPlayer(sound);
						mediaPlayer.play();
					}
				}

				// Sleep for one second
				Thread.sleep(1000);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}