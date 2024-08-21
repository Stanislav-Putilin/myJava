package itstep.learning;

import itstep.learning.oop.Shop;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        new Basics().run();

        Shop shop = new Shop();
        shop.printProducts();
    }
}