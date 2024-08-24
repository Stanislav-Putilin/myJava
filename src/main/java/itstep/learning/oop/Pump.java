package itstep.learning.oop;

import java.util.Locale;

@Warranty("2 years")
public class Pump extends Product implements Manual
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

    @Works("as pump")
    public void pump(){
        System.out.println("Pump on:" + getCard());
    }

    @Override
    public String getCard() {
        return String.format(
                Locale.ROOT,
                "Pump: '%s', Productivity: %d l/h",
                super.getManufacturer(), this.getProductivity());
    }
}
