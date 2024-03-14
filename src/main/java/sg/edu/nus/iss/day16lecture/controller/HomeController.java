package sg.edu.nus.iss.day16lecture.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/time")
    @ResponseBody //without this, the Thyemeleaf expect a view to return the request and end up code 500; with this it send it as data without rendering the view
    public String getTimeAsJson (){
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String currDate = sdf.format(currentDate);

        JsonObject obj = Json.createObjectBuilder()
                            .add("time", currDate)
                            .build();

        return obj.toString();
    }
    
    @GetMapping ("/test")
    @ResponseBody 
    public ResponseEntity<String> getTestEmployee(){
        JsonObject jEmployee = Json.createObjectBuilder()
                                    .add("firstName", "Tailor")
                                    .add("lastName", "Swift")
                                    .build();

        // return ResponseEntity.ok(jEmployee.toString());
        return ResponseEntity.status(HttpStatus.OK).body(jEmployee.toString()); //OK > 200 ; CREATED > 201
    }
}
