import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Calculator {
  int boardWidht = 360;
  int boradHeight = 540;

  Color customLightGray = new Color(212, 212, 210);
  Color customDarkGray = new Color(80, 80, 80);
  Color customBlack = new Color(28, 28, 28);
  Color customOrange = new Color(255, 149, 0);

  String[] buttonValues = {
      "AC", "+/-", "%", "÷",
      "7", "8", "9", "×",
      "4", "5", "6", "-",
      "1", "2", "3", "+",
      "0", ".", "√", "="
  };
  String[] rightSymbols = { "÷", "×", "-", "+", "=" };
  String[] topSymbols = { "AC", "+/-", "%" };

  JFrame frame = new JFrame("Calculator");
  JLabel displayLabel = new JLabel();
  JPanel displayPanel = new JPanel();
  JPanel buttonsPanel = new JPanel();

  Calculator() {
    // frame.setVisible(true);
    frame.setSize(boardWidht, boradHeight);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    displayLabel.setBackground(customBlack);
    displayLabel.setForeground(Color.WHITE);
    displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
    displayLabel.setHorizontalAlignment(JLabel.RIGHT);
    displayLabel.setText("0");
    displayLabel.setOpaque(true);

    displayPanel.setLayout(new BorderLayout());
    displayPanel.add(displayLabel);
    frame.add(displayPanel, BorderLayout.NORTH);

    buttonsPanel.setLayout(new GridLayout(5, 4));
    buttonsPanel.setBackground(customBlack);
    frame.add(buttonsPanel);

    for (int i = 0; i < buttonValues.length; i++) {
      JButton button = new JButton();
      String buttonValue = buttonValues[i];
      button.setFont(new Font("Arial", Font.PLAIN, 30));
      button.setText(buttonValue);
      button.setFocusable(false);
      button.setBorder(new LineBorder(customBlack));
      buttonsPanel.add(button);
    }
    frame.setVisible(true);
  }
}
