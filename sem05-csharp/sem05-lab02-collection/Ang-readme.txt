Benutzen Sie Ihre zuletzt erstellten Klassen 
aus dem Vererbungsbeispiel Teil1 
  (Employee, Manager, Wageemployee, Salesperson)
  bzw. die hier bereitgestellten (etwas unfertig)
  
1. Erweiterungen  
   verbessern Sie die ToString() Implementierung
        (Es sollte jeweils Name und Name der Klasse ausgegeben werden
         also z.B. "Boss ist Manager" )
   implementieren Sie neben der vorhanden ComputePay
   auch ein Property Gehalt (nur get)   (mit gleicher Gehaltsermittlung)     

2.  Erweitern Sie die Main um eine generische List Collection,
    welche die 4 vorhandenen Instanzen (+ weitere) aufnimmt. 
             List<Employee> c1 = new List<Employee>();
             
3.  Ausgabe auf Basis der Collection:

    Hugo ist ein Employee
    Fred ist ein WageEmployee
    Wilma ist ein Salesperson
    Boss ist ein Manager

4.  Ausgabe auf Basis der Collection:
    Dies ist ein Beispiel, bitte die Summe selbst errechnen
    Hugo  0.0 das sind 0 % von 13622.7
    Fred  5376.0  das sind ... % von 13622.7
    Wilma 5746.2  das sind ... % von 13622.7
    Boss  2500.5  das sind ... % von 13622.7

5.  Sortieren Sie die Instanzen der Collection
    unter Nutzung der verschiedenen von der Collection 
    angebotenen Sortiermethoden
    
    c1.Sort();           z.B. Name         -- Exception genauer lesen hilft für die Lösung
    c1.Sort(IComparer)   z.B. Gehalt
    c1.Sort(Comparison)  z.B. Länge des Namens
    
6.  Wer Zeit hat kann sich auch weitere Möglichkeiten der Collection ansehen
     wie   FindAll,   Foreach   Methoden  siehe beiliegenden Artikel
  
7.  Erstellen Sie eine eigene generische
    Collectionklasse, welche von List<T> erbt,
    nur Employee,... Instanzen  aufnimmt
    und Methoden  Mitarbeiterliste und
                  Gehaltsliste  bereitstellt
    Hinweise dazu in Folien  c# 2.0.ppt
   
    class Mycoll<T> : List <T> where T : Employee
    {
          public void Mitarbeiterliste()
          {
             // schleife über this
          }
    }
    // dann in der Main  nur List durch Mycoll ersetzen
   
  

