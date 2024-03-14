package sg.edu.nus.iss.day16lecture;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.day16lecture.model.Address;
import sg.edu.nus.iss.day16lecture.model.Employee;
import sg.edu.nus.iss.day16lecture.model.Phone;

@SpringBootApplication
public class Day16LectureApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Day16LectureApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		JsonObjectBuilder empBuilder = Json.createObjectBuilder();
		empBuilder.add("firstName", "Will")
					.add("lastName","Smith")
					.add("salary", 20000);
		//avoid use spacing in key/name
		JsonObjectBuilder addressBuilder = Json.createObjectBuilder();
		addressBuilder.add("line1","Potong Pasir View")
						.add("line2", "8 Potong Pasir Ave 1")
						.add("postal","358008");

		empBuilder.add("address", addressBuilder);

		JsonObjectBuilder phone1 = Json.createObjectBuilder();
		phone1.add("type","mobile")
				.add("number","97304666");
		JsonObjectBuilder phone2 = Json.createObjectBuilder();
		phone2.add("type","mobile")
				.add("number","98765432");

		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		arrayBuilder.add(phone1)
					.add(phone2);
		empBuilder.add("phones", arrayBuilder);

		JsonObject employee = empBuilder.build();

		System.out.println("JSON:" + employee);

	//to map Json object to Employee object
		Employee emp = new Employee();
		emp.setFirstName(employee.get("firstName").toString());
		emp.setLastName(employee.get("lastName").toString());
		emp.setSalary(Long.parseLong(employee.get("salary").toString()));

		JsonObject addressObj = employee.getJsonObject("address");
		Address myAddress = new Address();
		myAddress.setLine1(addressObj.get("line1").toString());//if get() it is object, if getString() then it is string
		myAddress.setLine2(addressObj.getString("line2"));
		myAddress.setPostal(addressObj.get("postal").toString()); 
		emp.setAddress(myAddress);

		JsonArray phones = employee.getJsonArray("phones");
		List<Phone> phoneList = new ArrayList<>();
		for(int i= 0; i < phones.size(); i++){
			JsonObject obj = phones.getJsonObject(i);
			Phone phone = new Phone(obj.getString("type"),obj.getString("number"));
			phoneList.add(phone);
		}
		emp.setPhones(phoneList);
		System.out.println("Employee object:" + emp);
	}

}
