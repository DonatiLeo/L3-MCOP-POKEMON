/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * Pokedex.java
 */
package serialisation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * @author Leo Donati
 *
 */
public class Pokedex implements Serializable{

	protected TreeMap<String, Pokemon> pokedex;
	
	/**
	 * 
	 */
	public Pokedex(String fileName) {
		this();
		initializeFromCSV(fileName);
	}
	
	public Pokedex() {
		pokedex = new TreeMap<>();
	}
	
	public Pokedex(JSONArray ar) {
		this();
		for (Object o : ar) {
			JSONObject jso = (JSONObject) o;
			pokedex.put((String) jso.get("nom"), 
					new Pokemon((JSONObject) jso.get("pokemon")));
		}
	}
	
	public JSONArray getJSON() {
		JSONArray a = new JSONArray();
		pokedex.forEach( (name, pok) -> {
			JSONObject o = new JSONObject();
			o.put("nom", name);
			o.put("pokemon", pok.getJSON());
			a.add(o);
		});
		return a;
	}
	
	public TreeMap<String, Pokemon> getPokedex() {
		return pokedex;
	}
	
	public void setPokedex(TreeMap<String, Pokemon> map) {
		this.pokedex = map;
	}
	
	private void initializeFromCSV(String nomFichier) {
		String nom, ligne = "";
		int pv, niveau;
		try {
			FileReader fichier = new FileReader(nomFichier);
			BufferedReader reader = new BufferedReader(fichier);
			ligne = reader.readLine();
			Scanner scanner = new Scanner(ligne).useDelimiter(";");
			while (reader.ready()) {
				ligne = reader.readLine();
				scanner = new Scanner(ligne).useDelimiter(";");
				scanner.nextInt();
				nom    = scanner.next();
				pv 	   = scanner.nextInt();
				for (int i=0 ; i <10; i++) scanner.nextInt();
				for (int i=0 ; i <2; i++) scanner.next();
				niveau  = scanner.nextInt();
				//
				pokedex.put(nom,  new Pokemon(nom, pv, niveau));
			}
			scanner.close();
			reader.close();
			fichier.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Fichier " + nomFichier  + " non trouvé !");
			e.printStackTrace();
			System.exit(0);
		}
		catch (IOException e) {
			System.out.println("Erreur de IO");
			e.printStackTrace();
			System.exit(0);
		}
		catch (Exception e) {
			System.out.println("Erreur non catchée en lisant : " + ligne);
			e.printStackTrace();
			System.exit(0);
		}
	}

	@Override
	public String toString() {
		String s = "-------Mon Pokedex-------------\n";
		int i = 1;
		for (Map.Entry<String, Pokemon> pokEntry : pokedex.entrySet()) {
			s += pokEntry.getValue();
			if (i++ % 3 == 0) s += "\n";
			else s += " ";
		}
		return s;
	}
	
	public Pokemon find(String name) {
		return pokedex.get(name);
	}
}
