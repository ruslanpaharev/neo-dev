package ru.neoflex.dev.spring.api_version_resolver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @RequestMapping(value="/url_content_type",  headers="Accept=application/vnd.myapi.v1+json")
    public ResponseEntity<String> url1_accept() {
        return ResponseEntity.ok("url1_accept");
    }

    @RequestMapping(value="/url_content_type", headers="Accept=application/vnd.myapi.v2+json")
    public ResponseEntity<String> url2_accept() {
        return ResponseEntity.ok("url2_accept");
    }

    @RequestMapping(value="/url_header", headers="v=1")
    public ResponseEntity<String> url1_header() {
        return ResponseEntity.ok("url1_header");
    }

    @RequestMapping(value="/url_header", headers="v=2")
    public ResponseEntity<String> url2_header() {
        return ResponseEntity.ok("url2_header");
    }
}
