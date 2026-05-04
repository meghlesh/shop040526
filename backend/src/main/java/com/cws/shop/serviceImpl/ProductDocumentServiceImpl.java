package com.cws.shop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cws.shop.model.ProductDocument;
import com.cws.shop.repository.ProductDocumentRepository;
import com.cws.shop.service.ProductDocumentService;

@Service
public class ProductDocumentServiceImpl implements ProductDocumentService {

    @Autowired
    private ProductDocumentRepository repo;

    @Override
    public ProductDocument save(ProductDocument doc) {
        return repo.save(doc);
    }

    @Override
    public List<ProductDocument> getAll() {
        return repo.findAll();
    }
}