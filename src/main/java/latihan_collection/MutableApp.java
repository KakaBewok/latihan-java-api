package latihan_collection;

import java.util.List;

import latihan_collection.data.Person;

public class MutableApp {
	public static void main(String[] args) {

		Person person = new Person("Zayn");

		person.addHobby("Coding");
		person.addHobby("Game");

		doSomethingWithHobbies(person.getHobbies());

		for (String value : person.getHobbies()) {
			System.out.println(value);
		}
	}

	// method merubah hobby
	public static void doSomethingWithHobbies(List<String> hobbies) {
		hobbies.add("Nyanyi");
	}
}
