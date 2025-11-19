package PizzaAPI.Repositories;

import PizzaAPI.Models.ProductSizeModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductSizeRepository extends JpaRepository<ProductSizeModel, Long> {
    void deleteAllByProductId(Long productId);
    Optional<ProductSizeModel> findByProductIdAndSizeId(Long productId, Long sizeId);
}
