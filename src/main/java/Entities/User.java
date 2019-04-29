package Entities;

import java.util.LinkedList;
import java.util.List;

import Table.TableColumn;

public class User {
	
	public static Float[] prozentAbgaben = {0.1f, 0.2f};
	
	private static int newId = 0;
	
	private int id = newId++;

	private float prozentAbgabe = prozentAbgaben[0];

	@TableColumn(name = "Verkaufsnummer")
	private int nummer;
	
	@TableColumn(name = "Verk�ufer")
	private String verkaufsName = "";
	
	private List<Float> verkaufsListe = new LinkedList<Float>();
	
	public User(int verkaufsNummer)
	{
		 nummer = verkaufsNummer;
	}
	
	public User(int verkaufsNummer, String name)
	{
		nummer = verkaufsNummer;
		verkaufsName = name;
	}
	
	public void addVerkauf(float preis)
	{
		verkaufsListe.add(preis);
	}
	
	public void removeVerkauf()
	{
		if(!verkaufsListe.isEmpty())
		{
			verkaufsListe.remove(verkaufsListe.size() -1);
		}
	}
	
	public void setAbgabe(float neueAbgabe)
	{
		this.prozentAbgabe = neueAbgabe;
	}
	
	public float getAbgabe()
	{
		return prozentAbgabe;
	}
	
	public List<Float> getVerkaufsListe()
	{
		return verkaufsListe;
	}
	
	public void setVerkaufsListe(List<Float> newList)
	{
		verkaufsListe = newList;
	}
	
	public String getVerkufer() {
		return verkaufsName;
	}

	public void setVerkaufsName(String verkaufsName) {
		this.verkaufsName = verkaufsName;
	}

	public int getVerkaufsnummer() {
		return nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	
	public int getId()
	{
		return id;
	}
}
