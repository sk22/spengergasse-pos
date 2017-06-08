
/**
 * Aufgabe 11 "Fahrrad" (Schildberger)
 * 
 * @author Kaiser Samuel <kai17521@spengergasse.at>
 * @version 2015-12-09
 * @since 2015-12-02
 */
public class Person
{
    private String name;
    private int alter;
    
    public Person(String name) {
        setName(name);
        setAlter(alter);
    }
    
    public Person() {
        setName(null);
        setAlter(0);
    }
    
    public void setName(String name) {
        if(name == "" || name == null) {
            this.name = "Anonym";
        } else {
            this.name = name;
        }
    }
    
    public void setAlter(int alter) {
        if(alter <= 0) {
            this.alter = 30;
        } else {
            this.alter = alter;
        }
    }
    
    public String getName() {
        return this.name;
    }
}
