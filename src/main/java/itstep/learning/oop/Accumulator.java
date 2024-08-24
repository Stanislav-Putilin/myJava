package itstep.learning.oop;

import java.util.Locale;

@Warranty("5 years")
public class Accumulator extends Product implements Testeble
{
    private Integer capacity;

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Accumulator(String manufacturer, Integer capacity) {
        this.setCapacity(capacity);
        super.setManufacturer(manufacturer);
    }

    @Override
    public String getCard() {
        return String.format(
                Locale.ROOT,
                "Accumulator: '%s', Capacity: %d Ah",
                super.getManufacturer(), this.getCapacity());
    }

    @Override
    public void test()
    {
        System.out.println("Testing:" + getCard());
    }

    @Works("as accumulator")
    public void discharges(){
        System.out.println("Discharges on:" + getCard());
    }
}