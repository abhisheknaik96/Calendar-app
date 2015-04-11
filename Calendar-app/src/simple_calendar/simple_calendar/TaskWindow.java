package simple_calendar.simple_calendar;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.time.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class TaskWindow implements javafx.fxml.Initializable
{
	@FXML
	private Label taskLabel;
	@FXML
	private TextArea taskDetails;
	@FXML
	private DatePicker taskDate;
	@FXML
	private Label timeLabel;
//	@FXML
//	private TextField timeField;
	@FXML
	private ComboBox<String> timeHourField;
	@FXML
	private ComboBox<String> timeMinField;
	@FXML
	private ComboBox<String> priorityField;
	@FXML
	private ComboBox<String> gainField;
	@FXML
	private ComboBox<String> timeExpectedField;
	@FXML
	private Button taskAdd;
	@FXML
	private Label addedLabel;
	
	
	// The main list of events   						--> Make this a Priority Queue
														// And make a weighted priority 
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		addedLabel.setVisible(false);
		
		List<String> hrs = new ArrayList<String>();
		List<String> mins = new ArrayList<String>();
		List<String> priority = new ArrayList<String>();
		List<String> gain = new ArrayList<String>();
		List<String> timeExpected = new ArrayList<String>();
		
//		timeHourField = new ComboBox<String>();	
//		timeMinField = new ComboBox<String>();	

		for(int i=0;i<10;i++)
			hrs.add('0'+String.valueOf(i));
		for(int i=10;i<24;i++)
			hrs.add(String.valueOf(i));
		
		priority.add("Least");
		priority.add("Less");
		priority.add("Moderate");
		priority.add("High");
		priority.add("Highest");

		gain.add("0.25");
		gain.add("0.5");
		gain.add("0.75");
		gain.add("1");
		gain.add("1.5");
		gain.add("2");
		gain.add("2.5");
		gain.add("3");
		gain.add("4");
		gain.add("5");
		gain.add("7.5");
		gain.add("10");
		gain.add("20");
		gain.add("50");
		
		timeExpected.add("0.5");
		timeExpected.add("1");
		timeExpected.add("1.5");
		timeExpected.add("2");
		timeExpected.add("2.5");
		timeExpected.add("3");
		timeExpected.add("4");
		timeExpected.add("5");
		timeExpected.add("6");
		timeExpected.add("7");
		timeExpected.add("8");
		timeExpected.add("9");
		timeExpected.add("10");
		
		
		for(int i=0;i<10;i++)
			mins.add('0'+String.valueOf(i));
		for(int i=10;i<60;i++)
			mins.add(String.valueOf(i));
		
		ObservableList hrList = FXCollections.observableList(hrs);
		ObservableList minList = FXCollections.observableList(mins);
		ObservableList priorityList = FXCollections.observableList(priority);
		ObservableList gainList = FXCollections.observableList(gain);
		ObservableList timeExpectedList = FXCollections.observableList(timeExpected);
		
		timeHourField.getItems().clear();
		timeHourField.setItems(hrList);
		timeMinField.getItems().clear();
		timeMinField.setItems(minList);
		priorityField.getItems().clear();
		priorityField.setItems(priorityList);
		gainField.getItems().clear();
		gainField.setItems(gainList);
		timeExpectedField.getItems().clear();
		timeExpectedField.setItems(timeExpectedList);
		
		taskAdd.setOnAction(new EventHandler<ActionEvent>()
		{
		   @Override
			public void handle(ActionEvent event)
			{
		
				addedLabel.setText("Task Added! :)");
				addedLabel.setVisible(true);
				//task e;
				String taskName = taskDetails.getText();
				System.out.println(taskName);
				String date = null;
				try
				{
					date = taskDate.getValue().toString();
				}
				catch(Exception e)
				{
					addedLabel.setText("Please enter the date of the task...");
				}
				String timeHr = timeHourField.getValue();
				String timeMin = timeMinField.getValue();
				String timeExpectedS = timeExpectedField.getValue();
				String priorityS = priorityField.getValue();
				String gainS = gainField.getValue();
				
				if(taskName==null || timeHr==null || timeMin==null || date==null || gainS==null || 
						priorityS==null || timeExpectedS==null)
				{
					addedLabel.setText("All fields have to be filled...");
				}
				else
				{
					int d = taskDate.getValue().getDayOfMonth();
					int m = taskDate.getValue().getMonthValue();
					int y = taskDate.getValue().getYear();
					
					int j = 0;
					if(priorityS.compareTo("Highest")==0) j=5;
					if(priorityS.compareTo("High")==0) j=4;
					if(priorityS.compareTo("Modeate")==0) j=3;
					if(priorityS.compareTo("Less")==0) j=2;
					if(priorityS.compareTo("Least")==0) j=1;
					Task newTask = new Task(taskName, d, m, y, 
										 Integer.parseInt(timeHr), Integer.parseInt(timeMin), 
										 Float.parseFloat(gainS), Float.parseFloat(timeExpectedS), j);

					String time = timeHr + ':' + timeMin + ':' + "00";
					
					newTask.printTask();
				    SimpleCalendarDemo.taskList.add(newTask);
				    SimpleCalendarDemo.update();
				    System.out.println("No. of tasks added: " + SimpleCalendarDemo.eventList.size());
				}
			}
		});
		
		
	}

}

