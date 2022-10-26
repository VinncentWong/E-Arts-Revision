package com.earts.earts.app;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckHealthController {
    
    @GetMapping("/check")
    public ResponseEntity<Object> check(){
        Map<String,Object> map = new HashMap<>();
        map.put("status", "OK");
        return ResponseEntity.ok().body(map);
    }
}
