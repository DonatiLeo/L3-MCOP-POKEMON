/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * Pokemon.java
 */
package serialisation;

import java.io.Serializable;

import org.json.simple.JSONObject;

/**
 * @author Leo Donati
 *
 */
public class Pokemon implements Serializable {

	//private static final long serialVersionUID = 1L;

	public JSONObject getJSON() {
		JSONObject o = new JSONObject();
		o.put("nom", nom);
		o.put("pv", pv);
		o.put("niveau", niveau);
		return o;
	}
	
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	protected String nom;
	protected int pv;
	protected int niveau;
	
	/**
	 * @param nom : nom de l'espèce de Pokemon
	 * @param pv : points de vie
	 * @param niveau : niveau
	 */
	public Pokemon(String nom, int pv, int niveau) {
		this.nom = nom;
		this.pv = pv;
		this.niveau = niveau;
	}
	
	public Pokemon() {
		this.nom = "Leo";
		this.pv = 0;
		this.niveau = 100;
	}
	
	public Pokemon(JSONObject o) {
		this.nom = (String) o.get("nom");
		this.pv = ((Long) o.get("pv")).intValue();
		this.niveau = ((Long) o.get("niveau")).intValue();
	}

	@Override
	public String toString() {
		return String.format("%-30s", nom + " (pv=" + pv + ",niv=" + niveau + ")");
	}
	
}
