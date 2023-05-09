package latihan_collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionApp {
	public static void main(String[] args) {
		Collection<String> collection = new ArrayList<>();
		
		//add
		collection.add("Zayn");
		collection.add("Malik");
		collection.addAll(List.of("One","Direction","England"));
		for (String value : collection) {
			System.out.println(value);
		}
		
		//remove
		System.out.println("remove");
		collection.remove("Malik");
		collection.removeAll(List.of("One", "England"));
		for (String value : collection) {
			System.out.println(value);
		}
		
		//contains
		System.out.println("contains");
		System.out.println(collection.contains("Malik"));
		System.out.println(collection.contains("Zayn"));
		System.out.println(collection.containsAll(List.of("Direction")));
		
		//size
		System.out.println(collection.size());
		
	}
}
