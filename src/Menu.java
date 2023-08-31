import pkgPizza.base.Pizza;
import pkgPizza.base.Topping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Menu {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pizza Menu");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            // Crear algunos ingredientes (toppings)
            Topping cheese = new Topping("Queso Extra", 5);
            Topping pepperoni = new Topping("Pepperoni", 5);
            Topping pineapple = new Topping("Piña", 4);
            Topping ham = new Topping("Jamón", 8.5);
            Topping bacon = new Topping("Tocino", 7.75);
            Topping sausage = new Topping("Salchicha", 8);
            Topping chicken = new Topping("Pollo", 10);


            // Crear una lista de ingredientes para una pizza
            List<Topping> pizzaToppings = new ArrayList<>();
            pizzaToppings.add(cheese);
            pizzaToppings.add(pepperoni);
            pizzaToppings.add(pineapple);
            pizzaToppings.add(ham);
            pizzaToppings.add(bacon);
            pizzaToppings.add(sausage);
            pizzaToppings.add(chicken);

            DefaultListModel<String> pizzaListModel = new DefaultListModel<>();
            DefaultListModel<String> toppingListModel = new DefaultListModel<>();

            // Crear una lista de pizzas
            List<Pizza> pizzas = new ArrayList<>();
            Pizza normal = new Pizza("Pizza Queso", 50.0, pizzaToppings);
            Pizza hawaiana = new Pizza("Pizza Hawaiana", 60.0, pizzaToppings);
            Pizza thin = new Pizza("Pizza delgada", 55.0, pizzaToppings);
            Pizza vegetariana = new Pizza("Pizza Vegetariana", 60.0, pizzaToppings);
            Pizza pollo = new Pizza("Pizza Pollo", 65.0, pizzaToppings);
            pizzas.add(normal);
            pizzas.add(hawaiana);
            pizzas.add(thin);
            pizzas.add(vegetariana);
            pizzas.add(pollo);

            for (Pizza pizza : pizzas) {
                pizzaListModel.addElement(pizza.getName()+ " (Q" + pizza.getPrice() + ")");
            }

            for (Topping topping : pizzaToppings) {
                toppingListModel.addElement(topping.getIngredientName() + " (Q" + topping.getPrice() + ")");
            }

            JList<String> pizzaList = new JList<>(pizzaListModel);
            JList<String> toppingList = new JList<>(toppingListModel);

            // Crear una etiqueta para mostrar el precio total
            JLabel totalPriceLabel = new JLabel("Precio total: Q0.00");
            frame.add(totalPriceLabel, BorderLayout.NORTH);

            // Crear etiquetas para los títulos
            JLabel toppingLabel = new JLabel("Toppings");
            JLabel pizzaLabel = new JLabel("Pizzas");

            // Crear paneles para las listas
            JPanel toppingPanel = new JPanel(new BorderLayout());
            toppingPanel.add(toppingLabel, BorderLayout.NORTH);
            toppingPanel.add(new JScrollPane(toppingList), BorderLayout.CENTER);

            JPanel pizzaPanel = new JPanel(new BorderLayout());
            pizzaPanel.add(pizzaLabel, BorderLayout.NORTH);
            pizzaPanel.add(new JScrollPane(pizzaList), BorderLayout.CENTER);

            // Agregar los paneles al JFrame
            frame.add(toppingPanel, BorderLayout.EAST);
            frame.add(pizzaPanel, BorderLayout.WEST);

            // Botón para preparar
            JButton prepareButton = new JButton("Preparar");
            frame.add(prepareButton, BorderLayout.CENTER);

            // Acciones para preparar
            prepareButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    final JDialog dialog = new JDialog(frame, "Preparando...", true);
                    JProgressBar progressBar = new JProgressBar();
                    progressBar.setIndeterminate(true);
                    dialog.add(progressBar);
                    dialog.setSize(300, 100);
                    dialog.setLocationRelativeTo(frame);

                    Timer timer = new Timer(3000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog.dispose(); // Cerrar el diálogo de progreso
                            int selectedPizzaIndex = pizzaList.getSelectedIndex();
                            int[] selectedToppingIndices = toppingList.getSelectedIndices();
                            if (selectedPizzaIndex >= 0 && selectedToppingIndices.length > 0) {
                                Pizza selectedPizza = pizzas.get(selectedPizzaIndex); // Obtener la pizza seleccionada
                                double total = selectedPizza.getPrice(); // Inicializar el total con el precio de la pizza

                                StringBuilder selectedToppingsText = new StringBuilder();
                                for (int index : selectedToppingIndices) {
                                    Topping selectedTopping = pizzaToppings.get(index);
                                    total += selectedTopping.getPrice();
                                    selectedPizza.addTopping(selectedTopping);
                                    selectedToppingsText.append(selectedTopping.getIngredientName()).append(", ");
                                }

                                selectedToppingsText.setLength(selectedToppingsText.length() - 2); // Eliminar la última coma y espacio
                                totalPriceLabel.setText("Su orden: " + selectedPizza.getName() + " con " + selectedToppingsText + " Total: Q" + total);
                            }
                        }
                    });

                    timer.setRepeats(false);
                    timer.start();

                    dialog.setVisible(true);
                }
            });


            // Mostrar el JFrame
            frame.setVisible(true);
        });
    }
}
