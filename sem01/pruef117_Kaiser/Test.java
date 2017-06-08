
public class Test {
    public static void main(String[] args) {
        System.out.println("Defekten Kopierer mit ungültigen Werten instanzieren:");
        Kopierer kopierer = new Kopierer(10000, 10000, true);
        kopierer.kopieren(20);
        
        System.out.println("Kopierer reparieren:");
        kopierer.setDefekt(false);
        
        System.out.println("Kopierer in neuen CopyShop stellen:");
        CopyShop shop = new CopyShop();
        shop.kopiererAufstellen(kopierer);
        shop.printInfo();
        
        System.out.println("Gerät nochmal hinzufügen und null übergeben:");
        shop.kopiererAufstellen(kopierer);
        shop.kopiererAufstellen(null);
        
        System.out.println("Mehr Seiten als möglich kopieren:");
        kopierer.kopieren(10000000);
        
        System.out.println("Negativen Wert für Papier-nachfüllen:");
        kopierer.papierNachfuehllen(-123);
        
        System.out.println("Kopierer-PrintInfo:");
        kopierer.printInfo();
        
        System.out.println("CopyShop-PrintInfo:");
        shop.printInfo();
    }
}
