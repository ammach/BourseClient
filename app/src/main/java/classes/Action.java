package classes;

import java.io.Serializable;

/**
 * Created by ammach on 5/4/2016.
 */
public class Action implements Serializable {

   // private static final long serialVersionUID = 354054054054L;

    String nom;
    String valeur;

    public Action(String nom, String valeur) {
        this.nom = nom;
        this.valeur = valeur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }
}
