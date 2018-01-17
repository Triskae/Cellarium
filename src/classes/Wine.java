package classes;

import classes.Attribut;
import bdd.Connexion;
import org.w3c.dom.Attr;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static util.QueryUtil.*;

public class Wine {
    private String domaine;
    private String appellation;
    private String couleur;
    private String cepage;
    private float degres;

    public Wine(String domaine, String appellation, String couleur, String cepage, float degres) {
        this.domaine = domaine;
        this.appellation = appellation;
        this.couleur = couleur;
        this.cepage = cepage;
        this.degres=degres;

    }
    public String insertQuery() throws SQLException
    {
        return buildInsert(TABLE_VIN,
                VIN_COULEUR, couleur,
                VIN_DEGRES, Float.toString(degres),
                VIN_IDAPP, Integer.toString(Attribut.getAppellationId(appellation)),
                VIN_IDCEPAGE, Integer.toString(Attribut.getCepageId(cepage)),
                VIN_IDDOMAINE, Integer.toString(Attribut.getCepageId(domaine)));
    }




}
