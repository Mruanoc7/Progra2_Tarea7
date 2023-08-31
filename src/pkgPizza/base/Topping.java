package pkgPizza.base;

public class Topping {
    private String ingredientName;
    private double price;

    public Topping(String ingredientName, double price) {
        this.ingredientName = ingredientName;
        this.price = price;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}