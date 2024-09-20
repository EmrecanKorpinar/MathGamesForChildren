import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayMenu extends JFrame {
    private boolean isTwoPlayerGame;
    private JTextField xField, yField, operationField;
    private boolean operationSelected = false;
    private String number1, number2, operation1;

    private JPanel operatorsPanel, digitsPanel, operationsPanel;
    private JButton addButton, subtractButton, multiplyButton, divideButton, clearButton, playButton, twoPlayerButton;

    public void setTwoPlayerGame(boolean isTwoPlayerGame) {
        this.isTwoPlayerGame = isTwoPlayerGame;
    }

    public void initUI() {
        setLayout(new BorderLayout(10, 10));
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize panels
        operatorsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        digitsPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        operationsPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        // Initialize text fields
        xField = createTextField("");
        operationField = createTextField("");
        yField = createTextField("");

        operatorsPanel.add(xField);
        operatorsPanel.add(operationField);
        operatorsPanel.add(yField);

        // Initialize digit buttons
        for (int i = 1; i <= 9; i++) {
            JButton digitButton = createDigitButton(Integer.toString(i), "C:\\Users\\dell\\Downloads\\Renkli Sayılar\\Renkli Sayılar\\images\\" + i + ".png");
            digitsPanel.add(digitButton);
        }

        // 0 butonunu ekleyelim
        JButton zeroButton = createDigitButton(String.valueOf(0), "C:\\Users\\dell\\Downloads\\Renkli Sayılar\\Renkli Sayılar\\images\\0.png");
        digitsPanel.add(zeroButton);

        // Initialize operation buttons
        addButton = createOperationButton("+");
        subtractButton = createOperationButton("-");
        multiplyButton = createOperationButton("x");
        divideButton = createOperationButton("/");

        operationsPanel.add(addButton);
        operationsPanel.add(subtractButton);
        operationsPanel.add(multiplyButton);
        operationsPanel.add(divideButton);

        // Initialize other buttons
        clearButton = new JButton("C");
        clearButton.setFont(new Font("Arial", Font.PLAIN, 18));
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        twoPlayerButton = new JButton("2 Player");
        twoPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTwoPlayerGame();
            }
        });

        // Add panels to the main frame
        add(operatorsPanel, BorderLayout.NORTH);
        add(digitsPanel, BorderLayout.CENTER);
        add(operationsPanel, BorderLayout.EAST);
        add(clearButton, BorderLayout.WEST);

        if (isTwoPlayerGame) {
            add(playButton, BorderLayout.SOUTH);
        } else {
            add(twoPlayerButton, BorderLayout.SOUTH);
        }

        setTitle("Math Game - Oyun Menüsü");
    }

    private JTextField createTextField(String text) {
        JTextField textField = new JTextField(text);
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(new Font("Arial", Font.PLAIN, 18));
        return textField;
    }

    private JButton createDigitButton(String digit, String imagePath) {
        JButton button = new ImageBackgroundButton(digit, imagePath);
        button.setFont(new Font("Arial", Font.PLAIN, 0));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!operationSelected) {
                    String currentText = xField.getText();
                    xField.setText(currentText + digit);
                    number1 = currentText + digit;
                } else {
                    String currentText = yField.getText();
                    yField.setText(currentText + digit);
                    number2 = currentText + digit;
                }
            }
        });
        return button;
    }

    private JButton createOperationButton(String operation) {
        JButton button = new JButton(operation);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operationSelected = true;
                String currentText = operationField.getText();
                operationField.setText(currentText + operation);
                operation1 = currentText + operation;
            }
        });
        return button;
    }

    private void startGame() {
        if (number1 == null || number2 == null) {
            JOptionPane.showMessageDialog(this, "Lütfen sayıları giriniz.", "Uyarı", JOptionPane.WARNING_MESSAGE);
        } else {
            SwingUtilities.invokeLater(() -> {
                PlayGame playGame = new PlayGame(number1 + operationField.getText() + number2, this, false); // Single-player game
                playGame.setVisible(true);
                this.setVisible(false);
                clearFields();
            });
        }
    }

    private void startTwoPlayerGame() {
        if (number1 == null || number2 == null) {
            JOptionPane.showMessageDialog(this, "Lütfen sayıları giriniz.", "Uyarı", JOptionPane.WARNING_MESSAGE);
        } else {
            SwingUtilities.invokeLater(() -> {
                PlayGame playGame = new PlayGame(number1 + operationField.getText() + number2, this, true); // Two-player game
                playGame.setVisible(true);
                this.setVisible(false);
                clearFields();
            });
        }
    }

    private void clearFields() {
        xField.setText("");
        operationField.setText("");
        yField.setText("");
        operationSelected = false;
    }
}