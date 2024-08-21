package itstep.learning.oop;

import java.util.Locale;

public class Pump extends Product
{
    int productivity;
    public int getProductivity() {
        return productivity;
    }

    public void setProductivity(int productivity) {
        this.productivity = productivity;
    }
    public Pump(String manufacturer, int productivity) {
        this.setProductivity(productivity);
        super.setManufacturer(manufacturer);
    }

    @Override
    public String getCard() {
        return String.format(
                Locale.ROOT,
                "Pump: '%s', Productivity: %d l/h",
                super.getManufacturer(), this.getProductivity());
    }
}
