package itstep.learning.oop;

import java.util.Locale;

public class Lamp extends Product implements Testeble
{
    private double power;

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        if(power < 0)
        {
            throw new RuntimeException("Negative power");
        }
        this.power = power;
    }

    public Lamp(String manufacturer, double power)
    {
        this.setPower(power);
        super.setManufacturer(manufacturer);
    }

    @Override
    public String getCard() {
        return String.format(
                Locale.ROOT,
                "Lamp: '%s', Power: %.1f W",
                super.getManufacturer(), this.getPower());
    }

    @Override
    public void test()
    {
        System.out.println("Testing:" + getCard());
    }

    @Works("as lamp")
    public void shine(){
        System.out.println("Shine on:" + getCard());
    }
}