package PizzaAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "producto_tamaño", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_producto_fk", "id_tamaño_fk"})
})
public class ProductSizeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_producto_fk", nullable = false)
    @JsonIgnore // add to rodrigo
    private ProductModel product;

    @ManyToOne
    @JoinColumn(name = "id_tamaño_fk", nullable = false)
    private SizeModel size;

    @Column(name = "precio", nullable = false)
    private Double price;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ProductModel getProduct() { return product; }
    public void setProduct(ProductModel product) { this.product = product; }
    public SizeModel getSize() { return size; }
    public void setSize(SizeModel size) { this.size = size; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}