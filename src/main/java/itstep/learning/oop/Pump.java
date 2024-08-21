package itstep.learning.oop;

public class Pump extends Product
{
    int productivity;
    public int getProductivity() {
        return productivity;
    }

    public void setProductivity(int productivity) {
        this.productivity = productivity;
    }
    public Pump(int productivity, String manufacturer) {
        this.setProductivity(productivity);
        super.setManufacturer(manufacturer);
    }

    @Override
    public String getCard() {
        return String.format(
                "Pump: '%s', Power: %d l/h",
                super.getManufacturer(), this.getProductivity());
    }
}
