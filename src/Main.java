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
    public static void main(String[] args) {
        Main listen = new Main();
        frame = new JFrame("Calculator");
        //Create result text\\
        result = new JTextField(16);
        result.setEditable(false);
        //Realize list of numbers
        ArrayList<JButton> numericBut = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            numericBut.add(new JButton(Integer.toString(i)));
            numericBut.get(i).addActionListener(listen);
        }
        //Realize list of numeric operation
        List<String> operations = Arrays.asList("+", "-", "*", "/", "=", "C");

        //Realize buttons panel
        JPanel buttons = new JPanel();
        numericBut.forEach(buttons::add);
        operations.forEach(it -> {
            JButton button = new JButton(it);
            button.addActionListener(listen);
            buttons.add(button);
        });

        //Build in buttons to grid layout
        GridLayout numsAndOpsLayout = new GridLayout(4, 4);
        buttons.setLayout(numsAndOpsLayout);

        //Group elements to one panel
        JPanel mainPanel = new JPanel();
        mainPanel.add(result);
        mainPanel.add(buttons);


        frame.add(mainPanel);
        frame.setSize(360, 360);
        frame.setVisible(true);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command);
        if(Character.isDigit(command.charAt(0))) {
            if(operation.equals("")) {
                operand1 += Integer.parseInt(command);
            }
            else {
                operand2 += Integer.parseInt(command);
            }
            result.setText(operand1 + operation + operand2);
        }
        else {
            if(command.equals("C")) {
                operand1 = "";
                operand2 = "";
                operation = "";
                result.setText(operand1 + operation + operand2);
            }
        }

    }
}