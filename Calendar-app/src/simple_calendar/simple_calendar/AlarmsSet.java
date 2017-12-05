package simple_calendar.simple_calendar;

// Import the Java Media classes
//import javax.media.*;

// Import the standard Java classes
import java.util.*;
import java.text.*;

// Import the JDOM classes
import org.w3c.dom.*;

public class AlarmsSet
{
	private TimeThread timeThread = new TimeThread();

	public void addAlarm(Alarm alarm)
	{
		timeThread.addAlarm(alarm);
	}

	public void start()
	{
		timeThread.start();
	}

	// public static void main( String[] args )
	// {
	// String configFilename = "alarms.xml";
	// if( args.length >= 1 )
	// {
	// configFilename = args[ 0 ];
	// }
	// JAlarmClock alarmClock = new JAlarmClock();
	// String date = "04/09/2015";
	// String time = "10:18:40";
	// Alarm a = new Alarm( date, time);
	// alarmClock.addAlarm( a );
	//
	// alarmClock.start();
	// }
}