import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends JFrame implements ActionListener {
    static JFrame frame;
    static JTextField result;
    static String operand1 = "", operand2 = "";
    static String operation = "";

    static boolean useEqual = false;
    public static void main(String[] args) {
        Main listen = new Main();
        frame = new JFrame("Calculator"); //Build frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        result = new JTextField(16); //Create result text
        result.setEditable(false);

        ArrayList<JButton> numericBut = new ArrayList<>();//Realize number buttons
        for(int i = 0; i < 10; i++) {
            numericBut.add(new JButton(Integer.toString(i)));
            numericBut.get(i).addActionListener(listen);
        }

        List<String> operations = Arrays.asList("+", "-", "*", "/", "=", "C"); //Realize list of numeric operation


        //Realize num-buttons panel
        JPanel numButtons = new JPanel();
        numericBut.forEach(numButtons::add);


        //Build in buttons to grid layout
        GridLayout numLayout = new GridLayout(3, 3);
        numButtons.setLayout(numLayout);

        //Group elements to one panel
        JPanel mainPanel = new JPanel();
        mainPanel.add(result);
        mainPanel.add(numButtons);


        frame.add(mainPanel);
        frame.setSize(360, 360);
        frame.setVisible(true);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command);
        if(Character.isDigit(command.charAt(0))) {
            if(useEqual == true) {
                operand1 = "";
                operand2 = "";
                operation = "";
                useEqual = false;
            }
            if(operation.equals("")) {
                operand1 += Integer.parseInt(command);
            }
            else {
                operand2 += Integer.parseInt(command);
            }
            result.setText(operand1 + operation + operand2);
        }
        else if (command.equals("C")) {
            operand1 = "";
            operand2 = "";
            operation = "";
            result.setText("");
        }
        else if(command.equals("=")) {
            if(operand2.equals("")) {
                System.out.println(operand1);
                operation = "";
            }
            else {
                switch (operation) {
                    case "+" -> {
                        operand1 = String.valueOf(Integer.parseInt(operand1) + Integer.parseInt(operand2));
                    }
                    case "-" -> {
                        operand1 = String.valueOf(Integer.parseInt(operand1) - Integer.parseInt(operand2));
                    }
                    case "*" -> {
                        operand1 = String.valueOf(Integer.parseInt(operand1) * Integer.parseInt(operand2));
                    }
                    case "/" -> {
                        operand1 = String.valueOf(Integer.parseInt(operand1) / Integer.parseInt(operand2));
                    }
                }
                operand2 = "";
                operation = "";
                useEqual = true;
                result.setText(operand1);
            }
        }
        else {
            if(useEqual == true) useEqual = false;
            operation = command;
            result.setText(operand1 + operation + operand2);
        }

    }
}