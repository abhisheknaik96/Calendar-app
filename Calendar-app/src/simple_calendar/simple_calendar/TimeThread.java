package simple_calendar.simple_calendar;

// Import the standard Java classes
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.io.File;
import java.io.IOException;
import java.text.*;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class TimeThread extends Thread
{
	static MediaPlayer mediaPlayer;

	String musicFile = "PiratesOfCaribbean.mp3"; // For example
	Media sound = new Media(new File(musicFile).toURI().toString());

	public boolean running = false;
	private Set<Alarm> alarms = new HashSet<Alarm>(); // No need for this now

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

	public void alarmNotification(Event e)
	{
		System.out.println("Started doing something");
		Service<Void> service = new Service<Void>()
		{
			@Override
			protected javafx.concurrent.Task<Void> createTask()
			{
				System.out.println("Started doing");
				return new javafx.concurrent.Task<Void>()
				{
					@Override
					protected Void call() throws Exception
					{
						// Background work
						System.out.println("Started doing something");
						final CountDownLatch latch = new CountDownLatch(1);
						Platform.runLater(new Runnable()
						{
							@Override
							public void run()
							{
								try
								{
									System.out.println("Started doing");

									Stage stage = new Stage();
									try
									{
										// NotifWindow.setEvent(e);
										showAlarmWindow(stage, e);
									} catch (Exception e1)
									{
										System.out
												.println("Couldn't load NotifPage");
										e1.printStackTrace();
									}
								} finally
								{
									latch.countDown();
								}
							}
						});
						latch.await();
						// Keep with the background work
						return null;
					}
				};
			}

		};
		service.start();

	}

	public void showAlarmWindow(Stage stage, Event e) throws Exception
	{
		Parent root;
		stage.setTitle("Alarm Notif");

		try
		{

			FXMLLoader loader = new FXMLLoader(getClass().getResource("NotifWindow.fxml"));
			root = (Parent)loader.load();
			NotifWindow controller = (NotifWindow)loader.getController();
			controller.nameField.setText(e.name);
			controller.dateField.setText(e.dateOfEvent.toString());
			controller.timeField.setText(e.timeOfEvent.toString());
			
		} catch (IOException ee)
		{
			System.out.println("naa ho paaya");
			ee.printStackTrace();
			return;
		}

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();

	}

	public void run()
	{
//		alarmNotification(MainWindow.eventList.get(0));

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

				// Printing stopped to allow debugging : paka mat band ho ja
				System.out.println(df.format(now.getTime()));

				// Check alarms
				List<Event> eList = MainWindow.eventList;
				for (int i = 0; i < eList.size(); i++)
				{
					Alarm alarm = eList.get(i).getAlarm();
					if (alarm != null)
					{
						if (!alarm.isFired() && alarm.checkAlarm(now))
						{
							System.out.println(eList.get(i).name
									+ " is gonna ring");
							mediaPlayer = new MediaPlayer(sound);
							mediaPlayer.play();

							alarmNotification(eList.get(i));
						}
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