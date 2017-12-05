package simple_calendar.simple_calendar;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.concurrent.Task;

public class NotifWindow implements javafx.fxml.Initializable
{

	@FXML
	private Label nameLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label timeLabel;
	@FXML
	public Label nameField;
	@FXML
	public Label dateField;
	@FXML
	public Label timeField;
	@FXML
	private Button stopButton;
	@FXML
	private Button snoozeButton;

	static public void setEvent(Event e)
	{
//		dateField = new Label();
		System.out.println("In here");
//		if (dateField != null)
//			System.out.println(dateField.getText() + " <--");
//		else
//			System.out.println("Lol");
		// dateField = new Label();
		// nameField.setText(e.name);
		//dateField.setText(e.dateOfEvent.format(null));
//		dateField.setText("bhal");
		// timeField.setText(e.timeOfEvent.format(null));
		
//		Task task = new Task() 
//		{
//			@Override
//			protected Object call() throws Exception
//			{
//				// TODO Auto-generated method stub
//				static final int max = 1000000;
//		        for (int i=1; i<=max; i++) {
//		            updateProgress(i, max);
//				return i;
//			}
//
//		};
//		Thread t = new Thread(task);
//		NotifWindow.dateField.progressProperty().bind(task.progressProperty());
//		new Thread(task).start();
		
//		Task task = new Task() 
//		{
//		    @Override
//		    protected Object call() throws Exception 
//		    {
//		        int i=0;
//		    	while (i<100) 
//		        {
//		            i++;
//		        	this.updateMessage("Count " + i);
//		            Thread.sleep(10);
//		        }
//				return i;
//		    }
//		};
//		Thread t = new Thread(task);
//		NotifWindow.dateField.textProperty().bind(task.messageProperty());
//		t.start();
		    
//		new Thread(new Runnable()
//		{
//			@Override
//			public void run()
//			{
//					Platform.runLater(new Runnable()
//					{
//						@Override
//						public void run()
//						{
//							System.out.println("runLater");
//							dateField.setText("Hello");
//						}
//					});
//				}
//			}
//		).start();
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

		stopButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				try{
				TimeThread.mediaPlayer.stop();
				}
				catch(Exception e)
				{
					System.out.println("Couldn't stop alarm"); 
				}
				
				new Timer().schedule(new TimerTask()
				{
					public void run()
					{
						Platform.runLater(new Runnable()
						{
							public void run()
							{
								(((Node) event.getSource())).getScene()
										.getWindow().hide();
							}
						});
					}
				}, 1 * 1000);

			}

		});

//		System.out.println(dateField);
//		

	}
}
