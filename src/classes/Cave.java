package classes;

import controlers.LockerControler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Cave {

    private static ArrayList<LockerControler> casierControllers = new ArrayList<>();
    private static ObservableList<Bottle> listBottles;
    private static ObservableList<String> listNomBouteilles = FXCollections.observableArrayList();
    //FXCollections.observableArrayList("Château Le Mayne", "Château Micardis", "La Cave d'Augustin Florent", "Château l'Hirondelle", "Château Haut-Moulin", "Charlemagne", "Chanturgue", "Châteaugay");
    //private static ObservableList<Bottle> listBottles = FXCollections.observableArrayList(bouteille1, bouteille2);


    public Cave() {
        listBottles = FXCollections.observableArrayList();
    }

    public static ObservableList<String> getListNomBouteilles() {
        return listNomBouteilles;
    }

    public static ObservableList<Bottle> getListBottles() {
        return listBottles;
    }

    /*public static void updateListBouteilles() {
        for (Bottle b : listBottles) {
            if(!contientBouteille(b)) {
                listNomBouteilles.add(b.getNom());
            }
        }

    }

    public static void ajouterBouteille(Bottle b) {
        listBottles.add(b);
        listNomBouteilles.add(b.getNom());
    }*/

    public static boolean contientBouteille(Bottle b) {
        return listBottles.contains(b);
    }


}
