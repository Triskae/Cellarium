package classes;

import bdd.Connexion;
import bdd.Password;
import static util.QueryUtil.*;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {

    // Ajout d'un nouvel utilisateur dans la bdd
    public static void newUser(Connection con,String pseudo, String nom, String prenom, String date, String mdp, String email, String ville) {
        PreparedStatement pst = null;
        try {

            byte[]salt = Password.getNextSalt();

            String stm = "INSERT INTO Utilisateur(pseudo, nom,prenom,datenaiss,mdp,email,ville,salt) VALUES(?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(stm);
            pst.setString(1, pseudo);
            pst.setString(2, nom);
            pst.setString(3, prenom);
            pst.setDate(4, Date.valueOf(date));
            pst.setBytes(5, Password.hash(mdp,salt));
            pst.setString(6, email);
            pst.setString(7, ville);
            pst.setBytes(8, salt);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(User.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    // Regarde dans la base de données si le login / mdp est correct
    public static boolean login(String email,String mdp) throws SQLException {
        Connection con = Connexion.connexionBDD();
        PreparedStatement pst = con.prepareStatement(buildSelect(TABLE_UTILISATEUR,"count(*) as c")+" where " + UTILISATEUR_EMAIL + " = '" + email + "'");
        ResultSet rs=pst.executeQuery();
        rs.next();
        if(rs.getInt("c")>0)
        {
            pst = con.prepareStatement("SELECT mdp,salt from utilisateur WHERE email='"+email+"'");
            rs = null;
            rs=pst.executeQuery();
            rs.next();

            return Password.isExpectedPassword(mdp,rs.getBytes(2),rs.getBytes(1));
        }
        else return false;
    }



}
