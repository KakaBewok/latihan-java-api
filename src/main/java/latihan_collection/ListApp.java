package latihan_collection;

import java.util.ArrayList;
import java.util.List;

public class ListApp {
	public static void main(String[] args) {

//		List<String> string = new LinkedList<>();
		List<String> string = new ArrayList<>();

		// add
		string.add("Muhammad");
		string.add("Zayn");
		string.add("Malik");

		// set
		string.set(0, "M");

		// remove
		string.remove(1);
//		string.remove("Malik");

		// print
		for (String value : string) {
			System.out.println(value);
		}

		// get
		System.out.println(string.get(1));

	}
}
