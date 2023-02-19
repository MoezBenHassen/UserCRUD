

import Entities.Livraison;
import Entities.Utilisateur;
import Services.LivraisonService;
import Services.UtilisateurService;

import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UtilisateurService us = new UtilisateurService();
        System.out.println(us.afficher()) ;
    }
}