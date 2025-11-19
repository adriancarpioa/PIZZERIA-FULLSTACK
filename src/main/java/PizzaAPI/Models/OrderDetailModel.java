package PizzaAPI.Models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore; 

@Entity
@Table(name = "detalle_pedido")
public class OrderDetailModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pedido_fk")
    @JsonIgnore 
    private OrderModel order;

    @ManyToOne
    @JoinColumn(name = "id_producto_fk")
    private ProductModel product;

    @ManyToOne
    @JoinColumn(name = "id_tamaño_fk")
    private SizeModel size;

    private Integer cantidad;
    private Double precioUnitario; 
    private Double subtotal;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public OrderModel getOrder() { return order; }
    public void setOrder(OrderModel order) { this.order = order; }
    public ProductModel getProduct() { return product; }
    public void setProduct(ProductModel product) { this.product = product; }
    public SizeModel getSize() { return size; }
    public void setSize(SizeModel size) { this.size = size; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
}