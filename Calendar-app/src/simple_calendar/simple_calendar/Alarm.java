package simple_calendar.simple_calendar;

// Import the standard Java classes
import java.util.*;
import java.io.Serializable;
import java.text.*;

public class Alarm implements Serializable
{
	private static final long serialVersionUID = -8465116787318420577L;
	
	private Calendar alarmTime = Calendar.getInstance();

	private boolean isAlarmOn = true;
	private boolean fired = false;
	private String command;

	
	// public Alarm( String date, String time, String command )
	public Alarm(String date, String time)
	{
		// Parse the date
		StringTokenizer st = new StringTokenizer(date, "-", false);
		int year = Integer.parseInt(st.nextToken());
		int month = Integer.parseInt(st.nextToken());
		int day = Integer.parseInt(st.nextToken());

		System.out.println(month + " " + day + " " + year);

		// Parse the time
		st = new StringTokenizer(time, ":", false);
		int hour = Integer.parseInt(st.nextToken());
		int minute = Integer.parseInt(st.nextToken());
		int second = Integer.parseInt(st.nextToken());

		// Set our alarm time
		this.alarmTime.set(year, month - 1, day, hour, minute, second);

		// this.command = command;
	}

	public String getCommand()
	{
		return this.command;
	}

	public boolean isFired()
	{
		return this.fired;
	}
	
	public void disableAlarm()
	{
		this.isAlarmOn = false;
	}

	public boolean checkAlarm(Calendar now)
	{
		System.out.println(now.getTime());
		if (isAlarmOn==true && (now.compareTo(alarmTime) >= 0))
		{
			this.fired = true;
			return true;
		}
		return false;
	}
}