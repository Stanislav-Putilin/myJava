package itstep.learning.oop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Shop {

    private List<Product> products;

    public void run()  {
        printProducts();
        System.out.println("----Manual---");
        printManualProducts();
        System.out.println("----NonManual---");
        printNonManualProducts();
        System.out.println("----Work---");
        showWorks();

        System.out.println("----isWarranty---");
        isWarranty();

        System.out.println("----Warranty---");
        showWarranty();
    }

    public Shop() {
        products = new ArrayList<Product>();
        products.add(new Lamp("Philips", 60.0));
        products.add(new Pump("Sulzer",100));
        products.add(new Accumulator("Panasonic",5000));
    }

    private  void printManualProducts(){
        for(Product product : products)
        {
            if(product instanceof  Manual)
            {
                System.out.println(product.getCard());
            }
        }
    }

    private  void printNonManualProducts(){
        for(Product product : products)
        {
            if(! (product instanceof  Manual))
            {
                System.out.println(product.getCard());
            }
        }
    }

    private void printProducts(){
        for(Product product : products)
        {
            if(product instanceof Testeble)
            {
                ((Testeble) product).test();
            }
            else
            {
                System.out.println(product.getCard());
            }
        }
    }

    private void showWorks() {
        for(Product product : products)
        {
          for(Method method : product.getClass().getDeclaredMethods())
          {
              if(method.isAnnotationPresent(Works.class))
              {
                  System.out.print(method.getAnnotation(Works.class).value() + " ");

                  method.setAccessible(true);

                  try {
                      method.invoke(product);
                  } catch (IllegalAccessException | InvocationTargetException e) {
                      System.err.println(e.getMessage());
                  }
              }
          }
        }
    }

    private void isWarranty() {
        for (Product product : products) {

            if (product.getClass().isAnnotationPresent(Warranty.class)) {
                System.out.println("Warranty: yes | Product: " + product.getCard());
            }
            else {
                System.out.println("Warranty: --- | Product: " + product.getCard());
            }
        }
    }

    private void showWarranty() {
        for (Product product : products) {

            if (product.getClass().isAnnotationPresent(Warranty.class)) {

                String warranty = product.getClass().getAnnotation(Warranty.class).value();
                System.out.println("Warranty: " + warranty + " Product: " + product.getCard());
            }
            else {
                System.out.println("Warranty: ------- Product: " + product.getCard());
            }
        }
    }
}