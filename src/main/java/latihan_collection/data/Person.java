package latihan_collection.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Person {
	private String name;
	private List<String> hobbies;
	
	//construct
	public Person(String name) {
		this.name = name;
		//default value
		this.hobbies = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	public List<String> getHobbies() {
//		return hobbies;
		//fieldnya diprotect menjadi immutable 
		return Collections.unmodifiableList(hobbies);
	}
	public void addHobby(String hobby) {
		hobbies.add(hobby);
	}
}
