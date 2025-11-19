package PizzaAPI.Repositories;

import PizzaAPI.Models.OrderDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetailModel, Long> {
    
}

