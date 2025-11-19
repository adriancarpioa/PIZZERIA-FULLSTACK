//package PizzaAPI.Models;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "producto")
//public class ProductModel {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id_producto")
//    private Long id;
//
//    @Column(name = "nombre_producto", nullable = false)
//    private String name;
//
//    @Column(name = "descripcion_producto", columnDefinition = "TEXT")
//    private String description;
//
//    @Column(name = "imagen_url_producto")
//    private String imageUrl;
//
//    @Column(name = "activo_producto")
//    private Boolean active = true;
//
//    // Getters y Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//    public String getDescription() { return description; }
//    public void setDescription(String description) { this.description = description; }
//    public String getImageUrl() { return imageUrl; }
//    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
//    public Boolean getActive() { return active; }
//    public void setActive(Boolean active) { this.active = active; }
//}

package PizzaAPI.Models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference; // Opcional, para evitar bucles
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "producto")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    @Column(name = "nombre_producto", nullable = false)
    private String name;

    @Column(name = "descripcion_producto", columnDefinition = "TEXT")
    private String description;

    @Column(name = "imagen_url_producto")
    private String imageUrl;

    @Column(name = "activo_producto")
    private Boolean active = true;

    // --- AQUÍ ESTÁ LA MAGIA DE LA CASCADA ---
    // mappedBy = "product": Indica que la dueña de la relación es la otra clase (campo 'product')
    // cascade = CascadeType.ALL: Si borras Pizza, borra sus hijos. Si guardas Pizza, guarda sus hijos.
    // orphanRemoval = true: Si quitas un precio de esta lista, bórralo de la BD.
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSizeModel> prices = new ArrayList<>();

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public List<ProductSizeModel> getPrices() { return prices; }
    public void setPrices(List<ProductSizeModel> prices) { this.prices = prices; }
}
