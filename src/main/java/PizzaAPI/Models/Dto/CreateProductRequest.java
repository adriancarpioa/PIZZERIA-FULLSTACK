package PizzaAPI.Models.Dto;

import java.util.List;

public class CreateProductRequest {
    private String name;
    private String description;
    private String imageUrl;
    private List<SizePriceDto> prices;

    // Getters y Setters Principal
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public List<SizePriceDto> getPrices() { return prices; }
    public void setPrices(List<SizePriceDto> prices) { this.prices = prices; }

    // Clase interna estática para los precios
    public static class SizePriceDto {
        private Long sizeId;
        private Double price;

        public Long getSizeId() { return sizeId; }
        public void setSizeId(Long sizeId) { this.sizeId = sizeId; }
        public Double getPrice() { return price; }
        public void setPrice(Double price) { this.price = price; }
    }
}