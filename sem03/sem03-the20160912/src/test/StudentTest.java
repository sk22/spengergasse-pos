package test;

// import static org.junit.Assert.*;
import org.junit.Test;
import daten.Student;

public class StudentTest {
	private Student hansi;

	@Test
	public void test() {
		hansi = new Student("Hansi", 12);
		hansi.print();
	}
}
