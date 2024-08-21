package itstep.learning.oop;

import java.util.Locale;

public class Accumulator extends Product
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
}
