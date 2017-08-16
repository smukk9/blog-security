package com.sandeep.security.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandeep.security.entity.User;
import com.sandeep.security.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
@RequestMapping(path="/demo")
public class MainController {


    @Autowired
    private UserProfileRepository userRepository;

    @GetMapping(path="/add") // Map ONLY GET Requests
    public @ResponseBody
    String addNewUser (@RequestParam String name
            , @RequestParam String email) {
                User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @RequestMapping(value = "/greeting", method = POST)
    @ResponseBody
    public String greetingJson(HttpEntity<String> httpEntity) throws IOException{
        HttpHeaders headers = httpEntity.getHeaders();
        String body = httpEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode bodyJson = objectMapper.readTree(body);
        JsonNode name = bodyJson.get("name");
        System.out.print(name.textValue());
        return name.textValue();    
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}