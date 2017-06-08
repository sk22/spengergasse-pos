package at.spengergasse.kai17521.sem03.lab04.task01.test;

import java.util.EmptyStackException;
import java.util.Stack;

import at.spengergasse.kai17521.sem03.lab04.task01.Book;

public class Test {
  public static void main(String... args) {
    Stack<Book> stack = new Stack<>();

    try {
      stack.pop();
    } catch(EmptyStackException ese) {
      System.out.println("Caught EmptyStackException.");
      System.out.println(ese);
    }

    System.out.println();

    System.out.println("Filling Stack");
    fill(stack);

    System.out.println();

    System.out.println("Emptying Stack");
    while (!stack.empty()) {
      System.out.println(stack.pop());
    }

    System.out.println();
    System.out.println("Refilling Stack");
    fill(stack);
    System.out.println();

    System.out.println("Replacing Jean Doe with Bart Simpson");
    System.out.println("Index of Jean Doe: " + stack.search(new Book("Jean Doe", 4)));
    stack.setElementAt(
      new Book("Bart Simpson", 5),
      stack.search(new Book("Jean Doe", 4))
    );

    System.out.println(stack);
    System.out.println();

    System.out.println("Removing Bart Simpson");
    stack.remove(new Book("Bart Simpson", 5));

    System.out.println(stack);
    System.out.println();


    System.out.println("New Stack consisting of original Stack's first three Books");
    Stack<Book> stack1 = new Stack<>();
    for (int i = 0; i < 3; i++) {
      stack1.push(stack.get(i));
    }
    System.out.println(stack1);
    System.out.println();

    System.out.println("Original Stack contains new Stack's Books: " +
      stack.containsAll(stack1));
    System.out.println();

    System.out.println("Capacity (The size the internal array has been initialized with: " +
      stack.capacity());
    System.out.println("Size (The number of elements the array actually has): " +
      stack.size());
  }

  private static void fill(Stack<Book> stack) {
    stack.push(new Book("John Doe", 1));
    stack.push(new Book("Jane Doe", 2));
    stack.push(new Book("James Doe", 3));
    stack.push(new Book("Jean Doe", 4));
    stack.push(new Book("Jason Doe", 5));
  }
}
