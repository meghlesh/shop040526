package com.cws.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cws.shop.model.ProductDocument;

public interface ProductDocumentRepository extends JpaRepository<ProductDocument, Long> {
}