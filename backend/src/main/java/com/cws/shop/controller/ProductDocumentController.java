package com.cws.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cws.shop.model.ProductDocument;
import com.cws.shop.service.ProductDocumentService;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin
public class ProductDocumentController {

    @Autowired
    private ProductDocumentService service;

    // Add document
    @PostMapping
    public ProductDocument addDocument(@RequestBody ProductDocument doc) {
        return service.save(doc);
    }

    // Get all documents
    @GetMapping
    public List<ProductDocument> getAllDocuments() {
        return service.getAll();
    }
}