import javax.swing.*;
import java.awt.*;

public class TeaMakerDisplay implements Observer {

    private JTextArea textArea;

    public TeaMakerDisplay() {
        JFrame frame = new JFrame("👨‍🍳 Tea Maker Station");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(420, 500);
        frame.setLocation(0, 0);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setBackground(new Color(30, 30, 30));
        textArea.setForeground(new Color(200, 255, 200));
        textArea.setMargin(new Insets(10, 10, 10, 10));

        frame.add(new JScrollPane(textArea));
        frame.setVisible(true);
    }

    @Override
    public void update(Order order) {
        SwingUtilities.invokeLater(() -> {
            switch (order.getStatus()) {
                case "PREPARING":
                    textArea.append("=== NEW ORDER #" + order.getOrderNumber() + " ===\n");
                    for (Beverage b : order.getDrinks()) {
                        textArea.append("  ☕ " + b.getDescription() + "\n");
                    }
                    textArea.append("\n");
                    break;

                case "READY":
                    textArea.append("Order #" + order.getOrderNumber() + " complete!\n\n");
                    break;
            }
        });
    }
}
