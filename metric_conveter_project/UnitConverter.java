import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnitConverter extends JFrame {
    private JLabel fromLabel, toLabel, quantityLabel, resultLabel, imageLabel, titleLabel;
    private JTextField quantityField;
    private JComboBox<String> fromUnitComboBox, toUnitComboBox;
    private JButton convertButton, clearButton;

    public UnitConverter() {
        // Initialize components
        fromLabel = new JLabel("From Unit:");
        toLabel = new JLabel("To Unit:");
        quantityLabel = new JLabel("Quantity:");
        resultLabel = new JLabel("Result:");
        imageLabel = new JLabel();
        titleLabel = new JLabel("<html><b>Thank God it's the last sprint</b></html>", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set the font to bold

        quantityField = new JTextField(10);

        fromUnitComboBox = new JComboBox<>(new String[]{"Feet", "Pounds", "Fahrenheit"});
        toUnitComboBox = new JComboBox<>(new String[]{"Meters", "Kilograms", "Celsius"});

        convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performConversion();
            }
        });

        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        // Add an image label
        String imagePath = "C:\\Users\\Bonginkosi.Zweni\\Desktop\\metric_conveter_project\\MicrosoftTeams-image (13).png";
        ImageIcon icon = createResizedImageIcon(imagePath, 100, 100); // Specify the desired width and height
        imageLabel.setIcon(icon);

        // Wrap the image label and title label in a panel with GridBagLayout
        JPanel imagePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcImage = new GridBagConstraints();
        gbcImage.gridx = 0;
        gbcImage.gridy = 0;
        imagePanel.add(titleLabel, gbcImage);

        gbcImage.gridy = 1;
        imagePanel.add(imageLabel, gbcImage);

        // Set up layout using BorderLayout for the main content
        setLayout(new BorderLayout());

        // Create a panel for the other components using GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Add components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(fromLabel, gbc);

        gbc.gridx = 1;
        panel.add(fromUnitComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(toLabel, gbc);

        gbc.gridx = 1;
        panel.add(toUnitComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(quantityLabel, gbc);

        gbc.gridx = 1;
        panel.add(quantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(resultLabel, gbc);

        gbc.gridx = 1;
        panel.add(convertButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(clearButton, gbc);

        // Add the image panel at the top of the BorderLayout
        add(imagePanel, BorderLayout.NORTH);

        // Add the other components panel to the center of the BorderLayout
        add(panel, BorderLayout.CENTER);

        // Set up the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void performConversion() {
        try {
            double quantity = Double.parseDouble(quantityField.getText());
            String fromUnit = fromUnitComboBox.getSelectedItem().toString();
            String toUnit = toUnitComboBox.getSelectedItem().toString();

            double result = convert(fromUnit, toUnit, quantity);

            resultLabel.setText("Result: " + result);
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        }
    }

    private double convert(String fromUnit, String toUnit, double quantity) {
        switch (fromUnit) {
            case "Feet":
                return (toUnit.equals("Meters")) ? quantity * 0.3048 : quantity;
            case "Pounds":
                return (toUnit.equals("Kilograms")) ? quantity * 0.453592 : quantity;
            case "Fahrenheit":
                return (toUnit.equals("Celsius")) ? (quantity - 32) * 5 / 9 : quantity;
            default:
                return quantity;
        }
    }

    private ImageIcon createResizedImageIcon(String path, int width, int height) {
        try {
            ImageIcon icon = new ImageIcon(path);
            Image image = icon.getImage();
            Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void clearFields() {
        quantityField.setText("");
        resultLabel.setText("Result:");
        fromUnitComboBox.setSelectedIndex(0); // Set the selected index to the first item
        toUnitComboBox.setSelectedIndex(0);   // Set the selected index to the first item
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UnitConverter converter = new UnitConverter();
            converter.setVisible(true);
        });
    }
}
