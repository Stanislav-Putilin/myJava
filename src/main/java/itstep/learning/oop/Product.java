package itstep.learning.oop;

public abstract class Product
{
    private String manufacturer;

    public abstract String getCard();

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
