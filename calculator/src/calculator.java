
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.print.DocFlavor.STRING;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class calculator {

  int width=500;
  int height=660;

  Color navy = new Color(0,0,51);
  Color lightblue=new Color(0,76,153);
  Color orange=new Color(250,142,0);
  Color grey =new Color(32,32,32);
   
  String[] buttonValues = {
    "AC", "+/-", "%", "÷", 
    "1", "2", "3", "=", 
    "4", "5", "6", "-",
    "7", "8", "9", "+",
    "0", ".", "√", "×"
  };

  String[] rightSymbols = {"÷", "×", "-", "+", "="};
  String[] topSymbols = {"AC", "+/-", "%"};

  JFrame frame = new JFrame("Number Cruncher");
  JLabel dispJLabel =new JLabel();
  JPanel disJPanel=new JPanel();
  JPanel buttoJPanel=new JPanel();

  String A = "0";
  String operator = null;
  String B = null;

  calculator(){
    frame.setVisible(true);
    frame.setSize(width,height);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    dispJLabel.setBackground(grey);
    dispJLabel.setForeground(Color.white);
    dispJLabel.setFont(new Font("Arial",Font.PLAIN,80));
    dispJLabel.setHorizontalAlignment(JLabel.RIGHT);
    dispJLabel.setText("0");
    dispJLabel.setOpaque(true);

    disJPanel.setLayout(new BorderLayout());
    disJPanel.add(dispJLabel);
    frame.add(disJPanel,BorderLayout.NORTH);

    buttoJPanel.setLayout(new GridLayout(5,4));
    buttoJPanel.setBackground(Color.BLACK);
    frame.add(buttoJPanel);

    for(int i=0;i<buttonValues.length;i++){
        JButton button=new JButton();
        String btvl=buttonValues[i];
        button.setFont(new Font("Arial",Font.PLAIN,32));
        button.setText(buttonValues[i]);
        button.setFocusable(false);
        button.setBorder(new LineBorder(Color.cyan));

        if(btvl=="AC"){
            button.setBackground(Color.YELLOW);
            button.setForeground(grey);
        }

        else  if(btvl=="√"){
            button.setBackground(navy);
            button.setForeground(Color.ORANGE);
        }

        else  if(btvl=="."){
            button.setBackground(navy);
            button.setForeground(Color.ORANGE);
        }

        else if(Arrays.asList(topSymbols).contains(btvl)){
            button.setBackground(Color.BLACK);
            button.setForeground(Color.RED);
        }

        else if(Arrays.asList(rightSymbols).contains(btvl)){
            button.setBackground(navy);
            button.setForeground(Color.ORANGE);
        }

        else{
            button.setBackground(Color.DARK_GRAY);
            button.setForeground(Color.white);
         }

        buttoJPanel.add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                String btvl = button.getText();
                if (Arrays.asList(rightSymbols).contains(btvl)) {
                    if (btvl=="=") {
                        if (A != null) {
                            B =dispJLabel.getText();
                            double numA = Double.parseDouble(A);
                            double numB = Double.parseDouble(B);

                            if (operator == "+") {
                                dispJLabel.setText(removeZeroDecimal(numA+numB));
                            }

                            else if (operator == "-") {
                                dispJLabel.setText(removeZeroDecimal(numA-numB));
                            }

                            else if (operator == "×") {
                                dispJLabel.setText(removeZeroDecimal(numA*numB));
                            }

                            else if (operator == "÷") {
                                dispJLabel.setText(removeZeroDecimal(numA/numB));
                            }

                            clearAll();
                        }
                    }

                    else if ("+-×÷".contains(btvl)) {
                        if (operator == null) {
                            A =dispJLabel.getText();
                            dispJLabel.setText("0");
                            B = "0";
                        }
                        operator = btvl;
                    }
                }

                else if (Arrays.asList(topSymbols).contains(btvl)) {
                    if (btvl == "AC") {
                        clearAll();
                        dispJLabel.setText("0");
                    }

                    else if (btvl== "+/-") {
                        double numDisplay = Double.parseDouble(dispJLabel.getText());
                        numDisplay *= -1;
                        dispJLabel.setText(removeZeroDecimal(numDisplay));
                    }

                    else if (btvl == "%") {
                        double numDisplay = Double.parseDouble(dispJLabel.getText());
                        numDisplay /= 100;
                        dispJLabel.setText(removeZeroDecimal(numDisplay));
                    }
                }
                
                else {  
                    if (btvl == ".") {
                        if (!dispJLabel.getText().contains(btvl)) {
                            dispJLabel.setText(dispJLabel.getText() + btvl);
                        }
                    }
                    else if ("0123456789".contains(btvl)) {
                        if (dispJLabel.getText() == "0") {
                            dispJLabel.setText(btvl);
                        }
                        else {
                            dispJLabel.setText(dispJLabel.getText() + btvl);
                        }
                    }
                }
            }
        });
        frame.setVisible(true);
    }
}

void clearAll() {
    A = "0";
    operator = null;
    B = null;
}

String removeZeroDecimal(double numDisplay) {
    if (numDisplay % 1 == 0) {
        return Integer.toString((int) numDisplay);
    }
    return Double.toString(numDisplay);
}
}