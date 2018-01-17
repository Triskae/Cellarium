package classes;

import classes.Attribut;

import java.sql.SQLException;

import static util.QueryUtil.*;

public class Bottle {

    private int volume;
    private Byte[] image;
    private String barcode;
    private Wine vin;
    private int millesime;

    public Bottle(int volume, String barcode, Wine vin) {
        this.volume = volume;
        this.barcode = barcode;
        this.vin = vin;
    }

    public int getVolume() {
        return volume;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;

    }

    public Wine getVin() {
        return vin;
    }

    public String getBarcode() {
        return barcode;
    }
    public String insertQuery() throws SQLException
    {
        return buildInsert(TABLE_BOUTEILLE,
                BOUTEILLE_VOLUME, Integer.toString(volume),
                BOUTEILLE_IDVIN, Integer.toString(Attribut.getVinId(vin)),
                BOUTEILLE_CODE, barcode,
                BOUTEILLE_MILLESIME, Integer.toString(millesime));
    }
}
