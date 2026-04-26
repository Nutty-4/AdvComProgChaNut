import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReadyOrderDisplay implements Observer {

    private JTextArea textArea;

    private final Map<Integer, StringBuilder> orderLines = new LinkedHashMap<>();

    public ReadyOrderDisplay() {
        JFrame frame = new JFrame("Ready for Pickup");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(420, 500);
        frame.setLocation(440, 0);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setBackground(new Color(20, 20, 50));
        textArea.setForeground(new Color(200, 220, 255));
        textArea.setMargin(new Insets(10, 10, 10, 10));

        frame.add(new JScrollPane(textArea));
        frame.setVisible(true);
    }

    @Override
    public void update(Order order) {
        SwingUtilities.invokeLater(() -> {
            int num = order.getOrderNumber();

            switch (order.getStatus()) {

                case "READY":
                    if (orderLines.containsKey(num)) break;

                    String readyLine = "🔔 Order #" + num + " is READY FOR PICKUP!\n\n";
                    orderLines.put(num, new StringBuilder(readyLine));
                    textArea.append(readyLine);
                    break;

                case "PICKED UP":
                    if (!orderLines.containsKey(num)) break;

                    String pickedLine = "✅ Order #" + num + " has been picked up.\n\n";
                    orderLines.get(num).append(pickedLine);  
                    textArea.append(pickedLine);
                    break;

                case "REMOVE":
                    if (!orderLines.containsKey(num)) break; 
                    orderLines.remove(num);
                    rebuildDisplay();
                    break;
            }
        });
    }

    private void rebuildDisplay() {
        textArea.setText("");
        for (StringBuilder sb : orderLines.values()) {
            textArea.append(sb.toString());
        }
    }
}
