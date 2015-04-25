package simple_calendar.simple_calendar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.util.ArrayList;
import java.util.List;

public class Persist
{

	public static void persist(List<?> list, String fileName) throws IOException
	{

		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream os = new ObjectOutputStream(fos);

//		for (int i = 0; i < list.size(); i++)
//			os.writeObject(list.get(i));
		os.writeObject(list);
		
		os.flush();
		os.close();
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> retrieve(String fileName) throws IOException, ClassNotFoundException
	{
		
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream is = new ObjectInputStream(fis);
		
		List<T> tempList = new ArrayList<T>();
		try 
		{
			 tempList = (List<T>) is.readObject();
			
			//MainWindow.eventList = (List<Event>) is.readObject();
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
		return tempList;
	}
}
