import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RestaurantBillGUI extends JFrame {
    private JComboBox<String> itemComboBox;
    private JTextField quantityField;
    private JButton addButton;
    private JButton generateBillButton;
    private JTextArea billArea;
    private List<MenuItem> menu;
    private List<OrderItem> order;

    static class MenuItem {
        String name;
        double price;

        MenuItem(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    static class OrderItem {
        MenuItem item;
        int quantity;

        OrderItem(MenuItem item, int quantity) {
            this.item = item;
            this.quantity = quantity;
        }
    }

    public RestaurantBillGUI() {
        setTitle("Hotel Fountain - Bill Generation");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize menu
        menu = new ArrayList<>();
        menu.add(new MenuItem("Chicken Biryani", 150.0));
        menu.add(new MenuItem("Mutton Biryani", 200.0));
        menu.add(new MenuItem("Chicken Curry", 160.0));
        menu.add(new MenuItem("Fish Curry", 190.0));
        menu.add(new MenuItem("Butter Chicken", 220.0));
        menu.add(new MenuItem("Tandoori Chicken", 250.0));
        menu.add(new MenuItem("Chicken Tikka", 180.0));
        menu.add(new MenuItem("Chicken Kebab", 170.0));
        menu.add(new MenuItem("Mutton Curry", 210.0));
        menu.add(new MenuItem("Lamb Chops", 300.0));
        menu.add(new MenuItem("Prawn Curry", 230.0));
        menu.add(new MenuItem("Fish Fry", 200.0));
        menu.add(new MenuItem("Chicken 65", 140.0));
        menu.add(new MenuItem("Mutton Korma", 240.0));
        menu.add(new MenuItem("Beef Curry", 220.0));
        menu.add(new MenuItem("Pork Vindaloo", 250.0));
        menu.add(new MenuItem("Duck Roast", 280.0));
        menu.add(new MenuItem("Turkey Burger", 190.0));
        menu.add(new MenuItem("Chicken Nuggets", 120.0));
        menu.add(new MenuItem("Fish Fingers", 130.0));
        menu.add(new MenuItem("Mutton Seekh Kebab", 160.0));
        menu.add(new MenuItem("Chicken Shawarma", 150.0));
        menu.add(new MenuItem("Lamb Biryani", 220.0));
        menu.add(new MenuItem("Prawn Biryani", 240.0));
        menu.add(new MenuItem("Crab Curry", 260.0));
        menu.add(new MenuItem("Squid Rings", 180.0));
        menu.add(new MenuItem("Octopus Salad", 200.0));
        menu.add(new MenuItem("Venison Steak", 350.0));
        menu.add(new MenuItem("Rabbit Stew", 190.0));
        menu.add(new MenuItem("Quail Roast", 270.0));
        menu.add(new MenuItem("Goose Liver", 320.0));

        order = new ArrayList<>();

        // Top panel for selection
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        itemComboBox = new JComboBox<>();
        for (MenuItem item : menu) {
            itemComboBox.addItem(item.name + " - Rs. " + item.price);
        }

        quantityField = new JTextField(5);
        addButton = new JButton("Add to Order");
        generateBillButton = new JButton("Generate Bill");

        topPanel.add(new JLabel("Select Item:"));
        topPanel.add(itemComboBox);
        topPanel.add(new JLabel("Quantity:"));
        topPanel.add(quantityField);
        topPanel.add(addButton);
        topPanel.add(generateBillButton);

        // Center panel for bill display
        billArea = new JTextArea();
        billArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(billArea);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Event listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemToOrder();
            }
        });

        generateBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateBill();
            }
        });
    }

    private void addItemToOrder() {
        int selectedIndex = itemComboBox.getSelectedIndex();
        String qtyText = quantityField.getText().trim();
        if (qtyText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter quantity.");
            return;
        }
        try {
            int quantity = Integer.parseInt(qtyText);
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be positive.");
                return;
            }
            MenuItem selectedItem = menu.get(selectedIndex);
            order.add(new OrderItem(selectedItem, quantity));
            JOptionPane.showMessageDialog(this, "Added " + quantity + " x " + selectedItem.name + " to order.");
            quantityField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid quantity.");
        }
    }

    private void generateBill() {
        if (order.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items in order.");
            return;
        }
        double total = 0.0;
        StringBuilder bill = new StringBuilder();
        bill.append("HOTEL FOUNTAIN\n");
        bill.append("================\n");
        bill.append("Bill Details:\n");
        for (OrderItem oi : order) {
            double itemTotal = oi.item.price * oi.quantity;
            bill.append(oi.item.name).append(" x ").append(oi.quantity).append(" - Rs. ").append(itemTotal).append("\n");
            total += itemTotal;
        }
        bill.append("================\n");
        bill.append("Total: Rs. ").append(total).append("\n");
        bill.append("Thank you for dining with us!\n");
        billArea.setText(bill.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RestaurantBillGUI().setVisible(true);
        });
    }
}
