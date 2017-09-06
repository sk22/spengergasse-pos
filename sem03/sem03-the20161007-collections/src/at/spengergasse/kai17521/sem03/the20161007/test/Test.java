package at.spengergasse.kai17521.sem03.the20161007.test;

import java.util.ArrayList;

import at.spengergasse.kai17521.sem03.the20161007.Book;

public class Test {
  public static void main(String... args) {
    ArrayList<Book> list = new ArrayList<>();
    
    list.add(new Book("John Doe", 1));
    list.add(new Book("Jane Doe", 2));
    list.add(new Book("James Doe", 3));
    list.add(new Book("Jean Doe", 4));
    list.add(new Book("Jason Doe", 5));
    
    Book comparison = new Book("Jean Doe", 4);
    if (list.contains(comparison)) {
      System.out.println(comparison);
    }
    
    list.remove("James Doe");
    
    System.out.println(list);
  }
}
