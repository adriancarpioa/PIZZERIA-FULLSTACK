package PizzaAPI.Services;

import PizzaAPI.Models.*;
import PizzaAPI.Models.Dto.CreateProductRequest;
import PizzaAPI.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired private IProductRepository productRepo;
    @Autowired private ISizeRepository sizeRepo;
    @Autowired private IProductSizeRepository productSizeRepo;

    public List<ProductModel> getAllProducts() {
        return productRepo.findAll();
    }
    
    public Optional<ProductModel> getProductById(Long id) {
        return productRepo.findById(id);
    }

    @Transactional
    public ProductModel createProduct(CreateProductRequest request) {
        ProductModel newProduct = new ProductModel();
        newProduct.setName(request.getName());
        newProduct.setDescription(request.getDescription());
        newProduct.setImageUrl(request.getImageUrl());
        newProduct.setActive(true);
        
        ProductModel savedProduct = productRepo.save(newProduct);
        savePrices(savedProduct, request.getPrices()); 
        return savedProduct;
    }

    @Transactional
    public ProductModel updateProduct(Long id, CreateProductRequest request) {
        ProductModel product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setImageUrl(request.getImageUrl());
        
        productSizeRepo.deleteAllByProductId(id);
        savePrices(product, request.getPrices());
        
        return productRepo.save(product);
    }

    @Transactional
    public boolean deleteProduct(Long id) {
        try {
            productRepo.deleteById(id); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void savePrices(ProductModel product, List<CreateProductRequest.SizePriceDto> prices) {
        for (CreateProductRequest.SizePriceDto item : prices) {
            SizeModel size = sizeRepo.findById(item.getSizeId())
                    .orElseThrow(() -> new RuntimeException("Tamaño no encontrado ID: " + item.getSizeId()));

            ProductSizeModel relation = new ProductSizeModel();
            relation.setProduct(product);
            relation.setSize(size);
            relation.setPrice(item.getPrice());
            productSizeRepo.save(relation);
        }
    }
}
