package com.ecommerce.backend.services.privateServices;

import com.ecommerce.backend.models.entity.Product;
import com.ecommerce.backend.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    public final ProductRepository repository;

    public String insertProduct(Product product){
        product.setCreatedOn(LocalDate.now());
        repository.save(product);
        return "Product added.";
    }

    public String insertManyProducts(List<Product> products){
        repository.saveAll(products);
        return "Products added.";
    }

    public List<Product> selectAll(){
        return repository.findAll();
    }

    public Optional<Product> selectById(long id){
        return repository.findById(id);
    }

    public String updateProduct(long id, Product product){
        Optional<Product> productExist = repository.findById(id);
        if(productExist.isPresent()){
            Product existingProduct = productExist.get();
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setImage(product.getImage());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStockQuantity(product.getStockQuantity());
            repository.save(existingProduct);
            return "Product Updated.";
        }
        return "product does not exist.";
    }

    public String deleteProduct(long id){
        repository.deleteById(id);
        return "Product deleted.";
    }
}
