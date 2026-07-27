import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

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

  String A = "0";
  String operator = null;
  String B = null;

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

      button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          JButton button = (JButton) e.getSource();
          String buttonValue = button.getText();
          if (Arrays.asList(rightSymbols).contains(buttonValue)) {
            if (buttonValue == "=" && displayLabel.getText() != "0") {
              B = displayLabel.getText();
              double numA = Double.parseDouble(A);
              double numB = Double.parseDouble(B);
              switch (operator) {
                case "+" -> {
                  displayLabel.setText(removeDecimal(numA + numB));
                }
                case "-" -> {
                  displayLabel.setText(removeDecimal(numA - numB));
                }
                case "×" -> {
                  displayLabel.setText(removeDecimal(numA * numB));
                }
                case "÷" -> {
                  displayLabel.setText(removeDecimal(numA / numB));
                }

              }

            } else if ("÷×-+".contains(buttonValue)) {
              if (operator == null) {
                A = displayLabel.getText();
                displayLabel.setText(("0"));
                B = "0";
              }
              operator = buttonValue;
            }
          } else if (Arrays.asList(topSymbols).contains(buttonValue)) {
            switch (buttonValue) {
              case "AC" -> {
                clearAll();
                displayLabel.setText("0");
              }
              case "+/-" -> {
                double numDisplay = Double.parseDouble(displayLabel.getText());
                numDisplay *= -1;
                displayLabel.setText(removeDecimal(numDisplay));
              }
              case "%" -> {
                double numDisplay = Double.parseDouble(displayLabel.getText());
                numDisplay /= 100;
                displayLabel.setText(removeDecimal(numDisplay));
              }
            }
          } else {
            if (buttonValue == ".") {
              if (!displayLabel.getText().contains(buttonValue)) {
                displayLabel.setText(displayLabel.getText() + buttonValue);
              }
            } else if ("0123456789".contains(buttonValue)) {
              if (displayLabel.getText() == "0") {
                displayLabel.setText(buttonValue);
              } else {
                displayLabel.setText(displayLabel.getText() + buttonValue);
              }
            }
          }
        }
      });
    }
    frame.setVisible(true);
  }

  private void clearAll() {
    A = "0";
    operator = null;
    B = null;
  }

  private String removeDecimal(double numDisplay) {
    if (numDisplay % 1 == 0) {
      return Integer.toString((int) numDisplay);
    }
    return Double.toString(numDisplay);
  }
}
