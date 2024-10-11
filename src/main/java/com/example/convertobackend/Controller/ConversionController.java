package com.example.convertobackend.Controller;

import com.example.convertobackend.Service.ConversionServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:43949")
@RestController
public class ConversionController {

    @GetMapping("/hello")
    public String defaultRoute(){
        return "<h1>Hello World";
    }

    @Autowired
    private ConversionServices conversionService;


    @PostMapping("/convert")
    public String convertData(@RequestParam String prev, @RequestParam String newFormat, @RequestBody String inputCode) throws JSONException, JsonProcessingException {
        System.out.println("Received Input: " + inputCode);
        String output = "";
        output = conversionService.convert( prev, newFormat, inputCode);
        System.out.println(output);
        return output;
    }
}
