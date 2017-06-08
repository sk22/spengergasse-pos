package at.spengergasse.kai17521.sem03.lab08;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Samuel Kaiser
 * @since 11/28/2016
 */
public class Provider {
  private Map<Integer, Phone> phones = new HashMap<>();

  public int add(Phone phone) {
    return add(phone, phones.size());
  }

  public int add(Phone phone, int id) {
    if (phones.put(id, phone) == null) {
      return -1;
    }
    return id;
  }

  public Phone get(int id) {
    return phones.get(id);
  }

  public int size() {
    return phones.size();
  }

  public boolean remove(int id) {
    return phones.remove(id) != null;
  }

  public boolean idRegistered(int id) {
    return phones.containsKey(id);
  }

  public int load(String fileName) {
    int count = 0;
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      int id = phones.size();
      String line;
      while ((line = reader.readLine()) != null) {
        if (add(Phone.parse(line), id++) != 0) count++;
      }
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
    return count;
  }

  public int save(String fileName) {
    int count = 0;
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
      for (Phone phone : phones.values()) {
        writer.write(phone.toString() + "\n");
        count++;
      }
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
    return count;
  }

  public List<Phone> expiredPhones() {
    return phones.values().stream()
      .filter(p -> p.timeLeft().isNegative())
      .collect(Collectors.toList());
  }

  @Override
  public String toString() {
    String string = "";
    for (Phone phone : phones.values()) {
      string += phone.toString() + "\n";
    }
    return string;
  }
}
