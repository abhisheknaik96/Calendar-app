package simple_calendar.simple_calendar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.util.List;

public class EventPersist
{

	public static void persist(List<Event> list) throws IOException
	{

		FileOutputStream fos = new FileOutputStream("Events.txt");
		ObjectOutputStream os = new ObjectOutputStream(fos);

//		for (int i = 0; i < list.size(); i++)
//			os.writeObject(list.get(i));
		os.writeObject(list);
		
		os.flush();
		os.close();

	}

	public static void retrieve() throws IOException, ClassNotFoundException
	{
		
		FileInputStream fis = new FileInputStream("Events.txt");
		ObjectInputStream is = new ObjectInputStream(fis);
		
		try 
		{
			
			MainWindow.eventList = (List<Event>) is.readObject();
//	        while (true) 
//	        {
//	            MainWindow.eventList.add((Event)is.readObject());
//	        }
	    } 
		catch (OptionalDataException e) 
		{
	        if (!e.eof) throw e;
	    } 
		finally 
		{
	        is.close();
	    }
		
	}
}
