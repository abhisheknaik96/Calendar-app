package simple_calendar.simple_calendar;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.time.*;

import simple_calendar.simple_calendar.Alarm;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EventWindow implements javafx.fxml.Initializable
{
	@FXML
	private Label eventNameLabel;
	@FXML
	private TextField eventName;
	@FXML
	private Label eventDetailsLabel;
	@FXML
	private TextArea eventDetails;
	@FXML
	private DatePicker eventDate;
	@FXML
	private Label timeLabel;
	@FXML
	private ComboBox<String> timeHourField;
	@FXML
	private ComboBox<String> timeMinField;
	@FXML
	private Button eventAdd;
	@FXML
	private Label addedLabel;
	

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		addedLabel.setVisible(false);
		
		List<String> hrs = new ArrayList<String>();
		List<String> mins = new ArrayList<String>();
		
//		timeHourField = new ComboBox<String>();	
//		timeMinField = new ComboBox<String>();	

		for(int i=0;i<10;i++)
			hrs.add('0'+String.valueOf(i));
		for(int i=10;i<24;i++)
			hrs.add(String.valueOf(i));
		
		for(int i=0;i<10;i++)
			mins.add('0'+String.valueOf(i));
		for(int i=10;i<60;i++)
			mins.add(String.valueOf(i));
		
		ObservableList hrList = FXCollections.observableList(hrs);
		ObservableList minList = FXCollections.observableList(mins);
		
		timeHourField.getItems().clear();
		timeHourField.setItems(hrList);
		timeMinField.getItems().clear();
		timeMinField.setItems(minList);
		
		eventAdd.setOnAction(new EventHandler<ActionEvent>()
		{
		   @Override
			public void handle(ActionEvent event)
			{
		
			    addedLabel.setText("Event Added! :)");
			    addedLabel.setVisible(true);
				//Event e;
				String eventNameString = eventName.getText();
				String eventDetailsString = eventDetails.getText();
				System.out.println(eventName);
				String date = null;
				try
				{
					date = eventDate.getValue().toString();
					System.out.println(date);
				}
				catch(Exception e)
				{
					addedLabel.setText("Please enter the date of the event...");
				}
				String timeHr = timeHourField.getValue();
				System.out.println(timeHr);
				String timeMin = timeMinField.getValue();
				System.out.println(timeMin);
				
				if(eventName==null || timeHr==null || timeMin==null || date==null)
				{
					addedLabel.setText("All fields have to be filled...");
				}
				else
				{
					int d = eventDate.getValue().getDayOfMonth();
					int m = eventDate.getValue().getMonthValue();
					int y = eventDate.getValue().getYear();
					
					Event newEvent = new Event(eventNameString, eventDetailsString, d, m, y, 
										 Integer.parseInt(timeHr), Integer.parseInt(timeMin));

					String time = timeHr + ':' + timeMin + ':' + "00";
					
					Alarm a = new Alarm(date, time);
				    //SimpleCalendarDemo.alarmClock.addAlarm( a );
					newEvent.setAlarm(a);
					
				    MainWindow.eventList.add(newEvent);
				    MainWindow.update();
				    
				    newEvent.printEvent();
				    
				    System.out.println("Total no. of events added: " + MainWindow.eventList.size());
					
				    try			// as soon as event is added, serialized.
					{
						Persist.persist(MainWindow.eventList, MainWindow.eventPersistFile);
					} 
				    catch (IOException e)
					{
				    	System.out.println("Couldn't serialize to file");
				    	e.printStackTrace();
					}
				    
					// Closes Event window after 1 sec of clicking 'Add Event'
					   new Timer().schedule(new TimerTask() 	
						{
						    public void run () 
						    { 
						    	 Platform.runLater(new Runnable() 
						    	 {
						    	       public void run() 
						    	       {
						    	    	   (((Node) event.getSource())).getScene().getWindow().hide();
						    //		    	System.exit(0);
						    	       }
						    	 });
						    }
						}, 1*1000);			// After 1*1000 ms
					
				}
			}
		});
		
		
	}

}




