package simple_calendar.simple_calendar;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.time.*;

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

public class TaskWindow implements javafx.fxml.Initializable
{
	@FXML
	private Label taskLabel;
	@FXML
	public TextArea taskDetails;
	@FXML
	public DatePicker taskDate;
	@FXML
	private Label timeLabel;
//	@FXML
//	public TextField timeField;
	@FXML
	public ComboBox<String> timeHourField;
	@FXML
	public ComboBox<String> timeMinField;
	@FXML
	public ComboBox<String> priorityField;
	@FXML
	public ComboBox<String> gainField;
	@FXML
	public ComboBox<String> timeExpectedField;
	@FXML
	public Button taskAdd;
	@FXML
	private Label addedLabel;
	
	
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
				System.out.println(priorityS);
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
					if(priorityS.compareTo("Moderate")==0) j=3;
					if(priorityS.compareTo("Less")==0) j=2;
					if(priorityS.compareTo("Least")==0) j=1;
					Task newTask = new Task(taskName, d, m, y, 
										 Integer.parseInt(timeHr), Integer.parseInt(timeMin), 
										 Float.parseFloat(gainS), Float.parseFloat(timeExpectedS), j);

					String time = timeHr + ':' + timeMin + ':' + "00";
					
					newTask.printTask();
				    MainWindow.taskList.add(newTask);
				    MainWindow.update();
				    System.out.println("No. of tasks added: " + MainWindow.taskList.size());
				
				    try			// as soon as task is added, serialized.
					{
						Persist.persist(MainWindow.taskList, MainWindow.taskPersistFile);
					} 
				    catch (IOException e)
					{
				    	System.out.println("Couldn't serialize to file");
				    	e.printStackTrace();
					}
				    
					// Closes Task window after 1 sec of clicking 'Add Task'
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

