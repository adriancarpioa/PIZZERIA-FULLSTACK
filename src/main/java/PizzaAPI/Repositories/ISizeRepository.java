
package PizzaAPI.Repositories;

import PizzaAPI.Models.SizeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ISizeRepository extends JpaRepository<SizeModel, Long>{
    
}
