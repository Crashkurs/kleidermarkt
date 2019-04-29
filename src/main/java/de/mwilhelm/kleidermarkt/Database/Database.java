package de.mwilhelm.kleidermarkt.Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import Entities.User;

public class Database implements Serializable {

	private static Database database = new Database();
	
	/**
	 * Objects the database has to store follow now
	 * 
	 */
	private List<User> users;
	
	private Database()
	{
		users = new LinkedList<User>();
		for(int x = 0; x < 50; x++)
		{
			users.add(new User(x, "a " + x));
		}
	}
	
	public static void loadFromFile(File file) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		FileInputStream fileInput = new FileInputStream(file);
		ObjectInputStream input = new ObjectInputStream(fileInput);
		database = (Database) input.readObject();
		input.close();
		fileInput.close();
	}
	
	public static void storeToFile(File file) throws IOException
	{
		FileOutputStream fileOutput = new FileOutputStream(file);
		ObjectOutputStream output = new ObjectOutputStream(fileOutput);
		output.writeObject(database);
		output.close();
		fileOutput.close();
	}
	
	public static void addUser(User newUser)
	{
		boolean added = false;
		for(int x = 0; x < database.users.size(); x++)
		{
			if(database.users.get(x).getVerkaufsnummer() > newUser.getVerkaufsnummer())
			{
				database.users.add(x, newUser);
				added = true;
				break;
			}
		}
		if(!added)
			database.users.add(newUser);
	}
	
	public static void removeUser(User user)
	{
		database.users.remove(user);
	}
	
	public static void removeUser(int userNummer)
	{
		for(User user : database.users)
		{
			if(user.getVerkaufsnummer() == userNummer)
			{
				database.users.remove(user);
				return;
			}
		}
	}
	
	public static List<User> getUsers()
	{
		return database.users;
	}
	
	public static User getUserById(int id)
	{
		for(User user : getUsers())
		{
			if(user.getId() == id)
				return user;
		}
		return null;
	}
	
	public static User getUserByNumber(int number)
	{
		for(User user : getUsers())
		{
			if(user.getVerkaufsnummer() == number)
				return user;
		}
		return null;
	}
}
