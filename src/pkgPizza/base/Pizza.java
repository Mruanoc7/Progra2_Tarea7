package pkgPizza.base;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private double price;
    private List<Topping> toppings = new ArrayList<>();

    public Pizza(String name, double price, List<Topping> pizzaToppings) {
        this.name = name;
        this.price = price;
        for (Topping tp : toppings) {
            this.toppings.add(tp);

        }
    }

    @Override

    public String toString(){
        return "Nombre: " + this.name + " Precio " + this.price;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public void addTopping(Topping topping){
        this.toppings.add(topping);
    }

    public void removeTopping(Topping topping){
        this.toppings.remove(topping);
    }



}
