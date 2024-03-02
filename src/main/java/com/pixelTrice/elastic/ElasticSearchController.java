package com.pixelTrice.elastic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ElasticSearchController {

    @Autowired
    private ElasticSearchQuery elasticSearchQuery;

    @PostMapping("/createOrUpdateDocument")
    public ResponseEntity<Object> createOrUpdateDocument(@RequestBody Product poll) throws IOException {
          String response = elasticSearchQuery.createOrUpdateDocument(poll);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getDocument")
    public ResponseEntity<Object> getDocumentById(@RequestParam String pollId) throws IOException {
       Product poll =  elasticSearchQuery.getDocumentById(pollId);
        return new ResponseEntity<>(poll, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDocument")
    public ResponseEntity<Object> deleteDocumentById(@RequestParam String pollId) throws IOException {
        String response =  elasticSearchQuery.deleteDocumentById(pollId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/searchDocument")
    public ResponseEntity<Object> searchAllDocument() throws IOException {
        List<Product> polls = elasticSearchQuery.searchAllDocuments();
        return new ResponseEntity<>(polls, HttpStatus.OK);
    }
}
