package com.cws.shop.service;

import java.util.List;
import com.cws.shop.model.ProductDocument;

public interface ProductDocumentService {
    ProductDocument save(ProductDocument doc);
    List<ProductDocument> getAll();
}