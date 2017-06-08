
/**
 * Simulation eines Autos.
 * 
 * @author  Samuel Kaiser <kai17521@spengergasse.at>
 * @version 2015-11-15
 * @since   2015-10-14
 */
public class Auto
{
    private float normverbrauch;   // l / 100km
    private int fassungsvermoegen; // l
    private float tankinhalt;      // l
    private int kilometerstand;    // km
    private String typ;
    
    /**
     * Standard-Konstruktor, definiert verwendet Standardwerte.
     */
    public Auto() {
        setNormverbrauch(5f);
        setFassungsvermoegen(50);
        setTankinhalt(35f);
        setKilometerstand(40000);
        setTyp("PKW");
    }
    
    /**
     * Konstruktor mit Übergabeparametern
     */
    public Auto(float normverbrauch, float tankinhalt, int fassungsvermoegen, int kilometerstand, String typ) {
        setNormverbrauch(normverbrauch);
        setFassungsvermoegen(fassungsvermoegen);
        setTankinhalt(tankinhalt);
        setKilometerstand(kilometerstand);
        setTyp(typ);
    }
    
    /**
     * Auto volltanken
     * 
     * @return Getankte Treibstoffmenge
     */
    public float tanken() {
        float getankt = fassungsvermoegen - tankinhalt;
        setTankinhalt(fassungsvermoegen);
        return getankt;
    }
    /**
     * Auto zu bestimmter Menge an Treibstoff tanken.
     * 
     * @param  preis Preis für einen Liter Treibstoff
     * @param  liter Menge an Treibstoff
     * @return       Preis für getankten Treibstoff
     */
    public float tanken(float preis, float liter) {
        float zuZahlen = preis*liter;
        float gesamt = tankinhalt+liter;
        if(gesamt<=fassungsvermoegen) {
            setTankinhalt(gesamt);
            return zuZahlen;
        } else {
            zuZahlen = preis*(fassungsvermoegen-tankinhalt);
            setTankinhalt(fassungsvermoegen);
            return zuZahlen;
        }
    }
    /**
     * Auto zu bestimmtem Preis tanken.
     * 
     * @param  preis  Preis für einen Liter Treibstoff
     * @param  betrag Preis, der bezahlt wird
     * @return        Menge des getankten Treibstoffes in Liter
     */
    public float tanken(float preis, int betrag) {
        float liter = betrag/preis;
        float gesamt = tankinhalt+liter;
        if(gesamt<=fassungsvermoegen) {
            setTankinhalt(gesamt);
            return liter;
        } else {
            liter = fassungsvermoegen - tankinhalt;
            setTankinhalt(fassungsvermoegen);
            return liter;
        }
    }
    
    /**
     * Fahren, so lange der Tankinhalt ausreicht.
     * 
     * @return Zurückgelegte Strecke in km
     */
    public int fahren() {
        float strecke = 100f/normverbrauch*tankinhalt;
        addKilometerstand((int)strecke);
        setTankinhalt(0f);
        return (int)strecke;
    }
    /**
     * Für bestimmte Strecke fahren.
     * 
     * @return Zurückgelegte Strecke in km
     */
    public int fahren(int strecke) {
        /*
         * normverbrauch     = 4.9 l / 100 km
         * fassungsvermoegen = 50 l
         * strecke
         */        
        float treibstoff = normverbrauch/100*strecke;
        if(treibstoff>=tankinhalt) {
            strecke = (int)(100f/normverbrauch*tankinhalt);
            setTankinhalt(0);
        } else {
            setTankinhalt(tankinhalt-treibstoff);
        }
        addKilometerstand(strecke);
        
        return strecke;
    }
    
    public float getNormverbrauch() {
        return this.normverbrauch;
    }
    public int getKilometerstand() {
        return this.kilometerstand;
    }
    public float getTankinhalt() {
        return this.tankinhalt;
    }
    public float getFassungsvermoegen() {
        return this.fassungsvermoegen;
    }
    public float getRestreichweite() {
        return (int)(100f/normverbrauch*tankinhalt);
    }
    public String getTyp() {
        return this.typ;
    }
    
    /** Setze Normverbrauch */
    public void setNormverbrauch(float normverbrauch) {
        if(normverbrauch>0) {
            this.normverbrauch = normverbrauch;
        } else {
            System.out.println("Ungültiger Normverbrauch!");
        }
    }
    
    /** Setze Tankinhalt */
    public void setTankinhalt(float tankinhalt) {
        if(tankinhalt>=0 && tankinhalt <= fassungsvermoegen) {
            this.tankinhalt = tankinhalt;
        } else {
            System.out.println("Ungültiger Tankinhalt!");
            System.out.println("Aktueller Wert: " + this.tankinhalt + "\n");
        }
    }
    
    /** Setze Fassungsvermögen */
    public void setFassungsvermoegen(int fassungsvermoegen) {
        if(fassungsvermoegen>tankinhalt) {
            this.fassungsvermoegen = fassungsvermoegen;
        } else {
            System.out.println("Ungültiges Fassungsvermögen!");
            System.out.println("Aktueller Wert: " + this.fassungsvermoegen + "\n");
        }
    }
    
    /** Setze Kilometerstand */
    public void setKilometerstand(int kilometerstand) {
        if(kilometerstand>=0) {
            this.kilometerstand = kilometerstand;
        } else {
            System.out.println("Kilometerstand muss größer oder gleich 0 sein!");
            System.out.println("Aktueller Wert: " + this.kilometerstand + "\n");
        }
    }
    /** Füge Wert zu Kilometerstand hinzu */
    public void addKilometerstand(int kilometerstand) {
        if(kilometerstand>0) {
            this.kilometerstand += kilometerstand;
        } else {
            System.out.println("Kilometerstand kann nicht kleiner werden, der Wert muss also größer als 0 sein!\n\r");
            System.out.println("Aktueller Wert: " + this.kilometerstand + "\n");
        }
    }
    
    /** Setze Typ */
    public void setTyp(String typ) {
        if(typ != null && typ != "") {
            this.typ = typ;
        } else {
            System.out.println("Typ muss einen Wert haben!");
            System.out.println("Aktueller Wert: " + this.typ + "\n");
        }
    }
    
    /** Gibt Informationen über das Auto zurück */
    public String getInfo() {
        String text = "Normverbrauch: " + normverbrauch + " l / 100 km, "
         + "aktueller Tankinhalt: " + tankinhalt + " l, "
         + "Fassungsvermögen: " + fassungsvermoegen + " l, "
         + "Kilometerstand: " + kilometerstand + " km, "
         + "Typ: " + typ;
        return text;
    }
    
    /** Gibt Informationen über das Auto in der Konsole aus */
    public void printInfo() {
        System.out.println(getInfo().replace(", ", "\n") + "\n");
    }
}