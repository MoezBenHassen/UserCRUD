package Services;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entities.Utilisateur;
import Entities.Admin;
import Entities.Livreur;
import Entities.Trader;
import Entities.Utilisateur;
import Utils.MyDB;
import Utils.Enums.Roles;

import javax.management.relation.Role;

public class UtilisateurService  implements IService<Utilisateur> {

    Connection cnx;
    Statement stm;

    public UtilisateurService() {
        cnx = MyDB.getInstance().getCon();
    }

    @Override
    public void add(Utilisateur user) {
        try {
            String qry = "";
            Statement statement  = cnx.createStatement();
            switch (user.getRole()){
                case admin:
                    final Trader trader = (Trader) user;
                    qry = "INSERT INTO `utilisateur` (`password`, `nom`, `prenom`, `email`, `adresse`, `avatar_url`, `role`)" +
                            "VALUES ('"+ user.getPassword() +
                            "', '"+ user.getNom() +
                            "', '" + user.getPrenom() +
                            "', '" + user.getEmail() +
                            "', '" + user.getAdresse() +
                            "', '" + user.getAvatar_url() +
                            "', '" + user.getRole() + "');";

                    break;
        }
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }}

    @Override
    public List<Utilisateur> afficher() {
        List<Utilisateur> utilisateurs = new ArrayList();
        try {

            Statement statement = cnx.createStatement();
            String query = "SELECT * FROM `utilisateur`";
            ResultSet rs = statement.executeQuery(query);



            while(rs.next()){
                String role = rs.getString("role");
                System.out.println("role value for user with id_user=4: " + role);

                if(Roles.valueOf(role)==Roles.livreur){
                    Livreur livreur = new Livreur();
                    livreur.setId_user(rs.getInt("id_user"));
                    livreur.setPassword(rs.getString("password"));
                    livreur.setNom(rs.getString("nom"));
                    livreur.setPrenom(rs.getString("prenom"));
                    livreur.setEmail(rs.getString("email"));
                    livreur.setAdresse(rs.getString("adresse"));
                    livreur.setAvatar_url(rs.getString("avatar_url"));
                    livreur.setRole(Roles.valueOf(rs.getString("role")));
                    utilisateurs.add(livreur);
                    System.out.println("LIVREUR");
                }
                if(Roles.valueOf(role)==Roles.trader){
                    Trader trader = new Trader();
                    trader.setId_user(rs.getInt("id_user"));
                    trader.setPassword(rs.getString("password"));
                    trader.setNom(rs.getString("nom"));
                    trader.setPrenom(rs.getString("prenom"));
                    trader.setEmail(rs.getString("email"));
                    trader.setAdresse(rs.getString("adresse"));
                    trader.setAvatar_url(rs.getString("avatar_url"));
                    trader.setRole(Roles.valueOf(rs.getString("role")));
                    trader.setScore((rs.getInt("score")));
                    trader.setDate_naissance(rs.getDate("date_naissance"));
                    utilisateurs.add(trader);
                    System.out.println("TRADER");
                }
                if(Roles.valueOf(role)==Roles.admin){
                    Admin admin = new Admin();
                    admin.setId_user(rs.getInt("id_user"));
                    admin.setPassword(rs.getString("password"));
                    admin.setNom(rs.getString("nom"));
                    admin.setPrenom(rs.getString("prenom"));
                    admin.setEmail(rs.getString("email"));
                    admin.setAdresse(rs.getString("adresse"));
                    admin.setAvatar_url(rs.getString("avatar_url"));
                    admin.setRole(Roles.valueOf(rs.getString("role")));
                    utilisateurs.add(admin);
                    System.out.println("ADMIN");
                }
            }
            return utilisateurs;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return null;
    }
    @Override
    public Boolean modifier(Utilisateur user) {
        return null;
    }

    @Override
    public Boolean supprimer(Utilisateur user) {
        return null;
    }
}
