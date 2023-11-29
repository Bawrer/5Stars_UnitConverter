import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.*;


/*This code defines a class named UnitConverter that extends JFrame, indicating that it is a Swing frame. */
public class UnitConverter extends JFrame {
    private JLabel fromLabel, toLabel, quantityLabel, resultLabel, imageLabel, titleLabel;
    private JTextField quantityField, resultField;
    private JComboBox<String> fromUnitComboBox, toUnitComboBox;
    private JButton convertButton, clearButton;




    /*This part initializes various Swing components (labels, text fields, combo boxes, etc.) that will be used in the graphical user interface (GUI) of the application. */
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
        resultField = new JTextField(10);

        fromUnitComboBox = new JComboBox<>(new String[]{"Feet", "Pounds", "Fahrenheit"});
        fromUnitComboBox.setPreferredSize(new Dimension(110, fromUnitComboBox.getPreferredSize().height));
        toUnitComboBox = new JComboBox<>(new String[]{"Meters", "Kilograms", "Celsius"});
        toUnitComboBox.setPreferredSize(new Dimension(110, toUnitComboBox.getPreferredSize().height));
        
        convertButton = new JButton("Convert");
        convertButton.setBackground(new Color(173, 216, 230));  // Light-blue color
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performConversion();
            }
        });


        clearButton = new JButton("Clear");
        clearButton.setBackground(Color.ORANGE);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });


        // Add an image label
        String imagePath = "metric_conveter_project\\MicrosoftTeams-image (13).png";
        File file = new File(imagePath);
        String absolutePath = file.getAbsolutePath();
        ImageIcon icon = createResizedImageIcon(absolutePath, 100, 100);
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
        panel.setBackground(Color.LIGHT_GRAY);  // Set the background color of the panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);


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

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(resultLabel, gbc);

        // Make all input fields the same size
        Dimension fieldSize = new Dimension(fromUnitComboBox.getPreferredSize().width,
                quantityField.getPreferredSize().height);
        quantityField.setPreferredSize(fieldSize);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(quantityField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(resultField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(convertButton, gbc);

        // Clear button on the far right
        gbc.gridx = 2;  // Set the column to 2
        gbc.gridy = 4;
        gbc.gridwidth = 1; // Span one column
        gbc.anchor = GridBagConstraints.EAST; // Align to the right
        panel.add(clearButton, gbc);

        // Add the image panel at the top of the BorderLayout
        add(imagePanel, BorderLayout.NORTH);

        // Add the other components panel to the center of the BorderLayout
        add(panel, BorderLayout.CENTER);

  // Set up the frame
setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent the default close operation

// Where you want to prompt the confirmation dialog, perhaps in a windowClosing listener
addWindowListener(new WindowAdapter() {
    @Override
    public void windowClosing(WindowEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(null, "Yivale Mfana?", "Confirm Exit", JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
            dispose(); // Close the frame if confirmed
        }
    }
});
pack();
setLocationRelativeTo(null); // Center the frame on the screen

    }
    private void performConversion() {
   
        try {
            double quantity = Double.parseDouble(quantityField.getText());
            String fromUnit = fromUnitComboBox.getSelectedItem().toString();
            String toUnit = toUnitComboBox.getSelectedItem().toString();

            double result = convert(fromUnit, toUnit, quantity);
            
            resultField.setText(String.valueOf(result));
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Input Error", JOptionPane.WARNING_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        
resultField.setEditable(false);
    }


    private double convert(String fromUnit, String toUnit, double quantity) throws IllegalArgumentException {
        switch (fromUnit) {
            case "Feet":
                if (toUnit.equals("Meters")) {
                    return quantity * 0.3048;
                } else {
                    throw new IllegalArgumentException("Incompatible units for conversion: Feet to " + toUnit);
                }
            case "Pounds":
                if (toUnit.equals("Kilograms")) {
                    return quantity * 0.453592;
                } else {
                    throw new IllegalArgumentException("Incompatible units for conversion: Pounds to " + toUnit);
                }
            case "Fahrenheit":
                if (toUnit.equals("Celsius")) {
                    return (quantity - 32) * 5 / 9;
                } else {
                    throw new IllegalArgumentException("Incompatible units for conversion: Fahrenheit to " + toUnit);
                }
            default:
                throw new IllegalArgumentException("Unsupported unit: " + fromUnit);
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

    /****************Clear button************************/
    private void clearFields() {
        quantityField.setText("");
        resultField.setText("");
    

        fromUnitComboBox.setSelectedIndex(0);
        toUnitComboBox.setSelectedIndex(0);
    }


    /****************main file****************************** */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UnitConverter converter = new UnitConverter();
            converter.setVisible(true);
        });
    }
}
