package itstep.learning.oop;

public class Lamp extends Product
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
        return String.format("Lamp: '%s', Power: %.1f W",
        super.getManufacturer(), this.getPower());
    }
}