
/**
 * Write a description of class Logik here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Logik
{
    public boolean berechneAusdruck1(boolean a, boolean b, boolean c) {
        boolean statement = a || b && c;
        System.out.println(statement);
        return statement;
    }
    
    public boolean berechneAusdruck2(boolean a, boolean b, boolean c) {
        boolean statement = a || (b && c);
        System.out.println(statement);
        return statement;
    }
    
    public boolean berechneAusdruck3(boolean a, boolean b, boolean c) {
        boolean statement = !(a || b) && c;
        System.out.println(statement);
        return statement;
    }
    
    /* public void ichProbierNurWas() {
        boolean a; boolean b; boolean c;
        while(!(a && b && c)) {
            System.out.println(a + " | " + b + " | " + c);
        }
    } */
}
