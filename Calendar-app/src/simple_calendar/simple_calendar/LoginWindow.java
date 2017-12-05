package simple_calendar.simple_calendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginWindow implements javafx.fxml.Initializable
{

	@FXML
	private Label loginLabel; // Add moodle logo in the window
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button loginButton;
	@FXML
	private Label wrongPassword;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		wrongPassword.setVisible(false);
		loginButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				String username = usernameField.getText();
				String password = passwordField.getText();
				int ans=0;
				try
				{
					ans = getMoodleCalendar(username, password);
				} 
				catch (IOException e)
				{
					System.out.println("Couldn't open Python Script to export iCalendar from Moodle");
					e.printStackTrace();
				}
				if(ans==1){
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
					}, 1 * 1000); // After 1*1000 ms
				}
				else{
					System.out.println("Wrong username or password");
					wrongPassword.setVisible(true);
				}
			}
		});

	}

	public static int getMoodleCalendar(String username, String password)
			throws IOException
	{
		String getCalFileName = "get_ical.py";
		String outFileName = "icalexport.ics";
		String pwd = System.getProperty("user.dir");

		ProcessBuilder pb = new ProcessBuilder("/usr/bin/python", pwd + '/' + 
				getCalFileName, username, password, outFileName);
		Process p = pb.start();
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String s = in.readLine();
		if(s!=null){
			int ret = new Integer(s).intValue();
			if(ret==1){
				Event.integrateMoodleCalendar(outFileName);
				return 1;
			}else{
				System.out.println("Script ran into an error...");
				return 0;
			}
		}
		else
			return 0;
	}
}
