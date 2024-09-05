package Models;


public class Item {

    public Item(Integer id, String description, Double price,Integer quantityInStock, Boolean isWholeSale) {
        Id = id;
        Description = description;
        Price = price;
        IsWholeSale = isWholeSale;
        QuantityInStock = quantityInStock;
    }

    private Integer Id;

    private String Description;

    private Double Price;

    private Integer QuantityInStock;

    private Boolean IsWholeSale;

    public Integer getId() {
        return Id;
    }


    public String getDescription() {
        return Description;
    }


    public Double getPrice() {
        return Price;
    }


    public Boolean getWholeSale() {
        return IsWholeSale;
    }


}
