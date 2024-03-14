package sg.edu.nus.iss.day16lecture.restcontroller;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@RestController
@RequestMapping(path = "/api/time", produces = MediaType.APPLICATION_JSON_VALUE) //without path also ok if there is only 1 parameter
public class TimeRestController {

    @GetMapping
    public ResponseEntity <String> getTimeAsJson (){
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String currDate = sdf.format(currentDate);

        JsonObject obj = Json.createObjectBuilder()
                            .add("time", currDate)
                            .build();

        return ResponseEntity.ok(obj.toString());
    }

    //payload come from the above API function as a payload string
    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE) //copy the JSON from GET to body at POST
    public ResponseEntity <String> postTimeAsJsonString(@RequestBody String payload){
        //read object from GET
        JsonReader jReader = Json.createReader((new StringReader(payload)));
        JsonObject jObject =jReader.readObject();
        System.out.println("jObject payload: " + jObject.toString());

        JsonObject responsePayload = Json.createObjectBuilder()
                                            .add("time",jObject.get("time").toString())
                                            .add("status", "unchaged")
                                            .add("updatedBy","Darryl")
                                            .build();
        
                                            //create header
        MultiValueMap <String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Darryl", (new Date()).toString());

        return new ResponseEntity <String>(responsePayload.toString(), headers, HttpStatus.OK);
        //curl -X POST http://localhost:8080/api/time -H 'Content-Type: application/json' -d '{"time":"14-Mar-2023"}'
    }
}
