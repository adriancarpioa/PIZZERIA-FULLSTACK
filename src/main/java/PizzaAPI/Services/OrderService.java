package PizzaAPI.Services;

import PizzaAPI.Models.*;
import PizzaAPI.Models.Dto.CreateOrderRequest;
import PizzaAPI.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class OrderService {

    @Autowired private IOrderRepository orderRepo;
    @Autowired private IUserRepository userRepo;
    @Autowired private IProductRepository productRepo;
    @Autowired private ISizeRepository sizeRepo;
    @Autowired private IProductSizeRepository productSizeRepo;

    @Transactional
    public OrderModel createOrder(CreateOrderRequest request) {
        UserModel user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        OrderModel order = new OrderModel();
        order.setUser(user);
        order.setDate(LocalDateTime.now());
        order.setStatus("pendiente");
        order.setPaymentMethod(request.getPaymentMethod());
        order.setDetails(new ArrayList<>());
        
        double totalCalculated = 0.0;

        for (CreateOrderRequest.OrderItemDto itemDto : request.getItems()) {
            ProductModel product = productRepo.findById(itemDto.getProductId()).get();
            SizeModel size = sizeRepo.findById(itemDto.getSizeId()).get();

            ProductSizeModel priceInfo = productSizeRepo.findByProductIdAndSizeId(product.getId(), size.getId())
                    .orElseThrow(() -> new RuntimeException("Precio no configurado para este producto/tamaño"));

            OrderDetailModel detail = new OrderDetailModel();
            detail.setOrder(order); 
            detail.setProduct(product);
            detail.setSize(size);
            detail.setCantidad(itemDto.getQuantity());
            detail.setPrecioUnitario(priceInfo.getPrice()); 
            
            double subtotal = priceInfo.getPrice() * itemDto.getQuantity();
            detail.setSubtotal(subtotal);
            totalCalculated += subtotal;
            order.getDetails().add(detail);
        }

        order.setTotal(totalCalculated);
        return orderRepo.save(order); 
    }

    public java.util.List<OrderModel> getAllOrders() {
        return orderRepo.findAll();
    }
}
