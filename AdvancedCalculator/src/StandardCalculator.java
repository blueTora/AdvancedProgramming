import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * <b>Standard Calculator</b>
 * Standard Calculator with the first 5 basic calculation.
 * It can be used with keyboard and buttons.
 *
 * @author Negar
 * @since 2020-05-05
 * @version 1.0
 */
public class StandardCalculator {

    JButton CBtn;
    JButton CEBtn;
    JButton doBtn;
    JButton dotBtn;
    JTextArea display;
    double firstArg;
    double secondArg;
    ArrayList<String> equation;
    int size;

    /**
     * creating the calculator.
     * initializing.
     */
    public StandardCalculator(){

        CBtn = new JButton();
        CEBtn = new JButton();
        doBtn = new JButton();
        dotBtn = new JButton();
        display = new JTextArea(3,10);
        equation = new ArrayList<String>();
        firstArg = 0;
        secondArg = 0;
        size = 0;
    }

    /**
     * making the standard panel.
     * It contains a keyboard panel and a display panel.
     *
     * @return the Standard Panel for Standard Calculator.
     */
    public JPanel makePanel(){

        JPanel standardPanel = new JPanel();
        standardPanel.setSize(415,640);
        standardPanel.setLayout(null);

        JPanel keyboardPanel = new JPanel();
        keyboardPanel.setBounds(0,150,400,420);
        keyboardPanel.setLayout(new GridLayout(5,4));

        CBtn.setText("C");
        keyboardPanel.add(CBtn);
        CBtn.addActionListener(new StandardAction());
        CBtn.setToolTipText("Clearing the whole equation.");

        CEBtn.setText("CE");
        keyboardPanel.add(CEBtn);
        CEBtn.addActionListener(new StandardAction());
        CEBtn.setToolTipText("Deleting the last Argument.");

        JButton remainBtn = new JButton();
        remainBtn.setText("%");
        keyboardPanel.add(remainBtn);
        remainBtn.addActionListener(new StandardAction());
        remainBtn.setToolTipText("Getting the remaining of diversion.");

        JButton multiBtn = new JButton();
        multiBtn.setText("×");
        keyboardPanel.add(multiBtn);
        multiBtn.addActionListener(new StandardAction());
        multiBtn.setToolTipText("Multiplying");

        for (int i = 9; i > 6; i--) {
            JButton btn = new JButton();
            btn.setText("" + i);
            keyboardPanel.add(btn);
            btn.setBackground(Color.cyan);
            btn.addActionListener(new StandardAction());
            btn.setToolTipText("Number " + i);
        }

        JButton divBtn = new JButton();
        divBtn.setText("÷");
        keyboardPanel.add(divBtn);
        divBtn.addActionListener(new StandardAction());
        divBtn.setToolTipText("Division");

        for (int i = 6; i > 3; i--) {
            JButton btn = new JButton();
            btn.setText("" + i);
            keyboardPanel.add(btn);
            btn.setBackground(Color.cyan);
            btn.addActionListener(new StandardAction());
            btn.setToolTipText("Number " + i);
        }

        JButton sumBtn = new JButton();
        sumBtn.setText("+");
        keyboardPanel.add(sumBtn);
        sumBtn.addActionListener(new StandardAction());
        sumBtn.setToolTipText("Sum");

        for (int i = 3; i > 0; i--) {
            JButton btn = new JButton();
            btn.setText("" + i);
            keyboardPanel.add(btn);
            btn.setBackground(Color.cyan);
            btn.addActionListener(new StandardAction());
            btn.setToolTipText("Number " + i);
        }

        JButton subBtn = new JButton();
        subBtn.setText("-");
        keyboardPanel.add(subBtn);
        subBtn.addActionListener(new StandardAction());
        subBtn.setToolTipText("Subtraction");

        JButton bracketBtn = new JButton();
        keyboardPanel.add(bracketBtn);
        bracketBtn.setToolTipText("This isn't a Key.");

        JButton zeroBtn = new JButton();
        zeroBtn.setText("0");
        keyboardPanel.add(zeroBtn);
        zeroBtn.setBackground(Color.cyan);
        zeroBtn.addActionListener(new StandardAction());
        zeroBtn.setToolTipText("Number 0");

        dotBtn.setText(".");
        keyboardPanel.add(dotBtn);
        dotBtn.addActionListener(new StandardAction());
        dotBtn.setToolTipText("Decimal");

        doBtn.setText("=");
        keyboardPanel.add(doBtn);
        doBtn.addActionListener(new StandardAction());
        doBtn.setToolTipText("Equal");

        display.setEditable(false);
        display.setFont(new Font("Arial", 14,14));
        display.addKeyListener(new StandardAction());
        display.setToolTipText("If you want to type with keyboard you must first click on the Test Area.");

        JScrollPane scrollPane = new JScrollPane(display);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        scrollPane.setLocation(0,0);
        scrollPane.setBounds(0,0,400,150);
        scrollPane.setToolTipText("You can scroll the Test Area.");

        standardPanel.add(scrollPane);
        standardPanel.add(keyboardPanel);

        return standardPanel;
    }

    /**
     * for action listener and key listener.
     */
    protected class StandardAction extends KeyAdapter implements ActionListener {

        /**
         * written for action listener for buttons.
         * It will delete or clear display and creating the equation array for calculations.
         *
         * @param e action event from buttons.
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == CBtn){
                equation = new ArrayList<String>();
                size = 0;

                int line = display.getLineCount()-1;
                try {
                    display.replaceRange("",display.getLineStartOffset(line),display.getLineEndOffset(line));
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }

            } else if (e.getSource() == CEBtn){

                if (equation.size() > 0){
                    int len = equation.get( equation.size()-1 ).length();
                    equation.remove( equation.size()-1 );
                    size--;

                    int line = display.getLineCount()-1;
                    int last = 0;
                    try {
                        last = display.getLineEndOffset(line);
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }

                    display.replaceRange("",last-len,last);
                }

            } else if (e.getSource() == doBtn){

                if(equation.size() >= 3){

                    String temp = equation.get( equation.size()-1 );
                    if (isNum(temp)){
                        display.append( e.getActionCommand() );
                        evaluate();
                    } else {
                        System.out.println("Error!-1");
                    }

                } else {
                    System.out.println("Error!-2");
                }

            } else {
                if (equation.size() > 0){
                    String temp = equation.get( equation.size()-1 );

                    if ((isNum(temp) && isNum( e.getActionCommand() )) || ((temp.charAt( temp.length()-1 )) == '.' || e.getSource() == dotBtn)){
                        equation.set(equation.size()-1, temp + e.getActionCommand());
                        display.append( e.getActionCommand() );

                    } else {
                        if (isNum(temp) || isNum( e.getActionCommand() )){
                            equation.add(e.getActionCommand());
                            display.append( e.getActionCommand() );
                            size++;
                        } else
                            System.out.println("Error!-6");
                    }

                } else {
                    if (isNum(e.getActionCommand())){
                        equation.add(e.getActionCommand());
                        display.append( e.getActionCommand() );
                        size++;
                    } else
                        System.out.println("Error!-6");
                }
            }
        }

        /**
         * written for key listener for typing with keyboard.
         *
         * @param e key event from keyboard
         */
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);

            String key = Character.toString(e.getKeyChar());

            if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                equation = new ArrayList<String>();
                size = 0;

                int line = display.getLineCount()-1;
                try {
                    display.replaceRange("",display.getLineStartOffset(line),display.getLineEndOffset(line));
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }

            } else if (e.getKeyCode() == KeyEvent.VK_DELETE){

                if (equation.size() > 0){
                    int len = equation.get( equation.size()-1 ).length();
                    equation.remove( equation.size()-1 );
                    size--;

                    int line = display.getLineCount()-1;
                    int last = 0;
                    try {
                        last = display.getLineEndOffset(line);
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }

                    display.replaceRange("",last-len,last);
                }

            } else if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_EQUALS){

                if(equation.size() >= 3){

                    String temp = equation.get( equation.size()-1 );
                    if (isNum(temp)){
                        display.append( "= " );
                        evaluate();
                    } else {
                        System.out.println("Error!-1");
                    }
                } else {
                    System.out.println("Error!-2");
                }
            } else if (isKeyValid(e.getKeyChar()) || isNum(key)){

                if (equation.size() > 0){
                    String temp = equation.get( equation.size()-1 );

                    if ((isNum(temp) && isNum( key )) || ((temp.charAt( temp.length()-1 )) == '.' || e.getKeyChar() == '.')){
                        equation.set(equation.size()-1, temp + key);
                        display.append( key );

                    } else {
                        if (isNum(key) || isNum(temp)){
                            equation.add(key);
                            display.append( key );
                            size++;
                        } else
                            System.out.println("Error!-6");
                    }

                } else {
                    if (isNum(key)){
                        equation.add(key);
                        display.append( key );
                        size++;
                    } else
                        System.out.println("Error!-6");
                }

            } else
                System.out.println("Error!-5");
        }

    }

    /**
     * for calculating the 5 basic calculations.
     * @param arg the calculation argument.
     * @return if it was successful will return true
     */
    private boolean calculate(char arg){
        double temp;

        if ( arg != ' ' ){

            if (arg == '+'){
                temp = firstArg + secondArg;

            } else if (arg == '-'){
                temp = firstArg - secondArg;

            } else if (arg == '%'){
                temp = firstArg % secondArg;

            } else if (arg == '×'){
                temp = firstArg * secondArg;

            } else if (arg == '÷'){
                temp = firstArg / secondArg;

            } else {
                System.out.println("Error!-3-2");
                return false;
            }
        } else{
            System.out.println("Error!-3-1");
            return false;
        }

        firstArg = temp;
        secondArg = 0.0;
        return true;
    }

    /**
     * It will separate the equation parts and call for calculate method.
     * in the end our result will save in firstArg field.
     */
    void evaluate(){

        char arg = ' ';

        while (size > 0){

            for (String num : equation){

                if (isNum(num)){

                    if (firstArg == 0){
                        firstArg = Double.parseDouble(num);
                        size--;

                    } else {
                        secondArg = Double.parseDouble(num);
                        size--;

                        boolean ifTrue = calculate(arg);
                        if (!ifTrue){
                            System.out.println("Error!-3");
                        }
                    }

                } else {
                    if (num.length() == 1){
                        arg = num.charAt(0);
                        size--;
                    } else {
                        System.out.println("Error!-4");
                    }
                }
            }
        }

        display.append(" " + firstArg);
        firstArg = 0;
        secondArg = 0;
        equation = new ArrayList<String>();
        display.append("\n");
        size = 0;
    }

    /**
     * for determining if a String is a number or not.
     *
     * @param nm the String we are checking
     * @return return true if it is a number
     */
    boolean isNum(String nm) {
        if (nm.matches("[0-9]+.?[0-9]*")) {
            return true;
        }
        return false;
    }

    /**
     * it will determine if our key is one of
     * + - * / % this keys.
     *
     * @param e our key's character
     * @return true if it is one of the characters above
     */
    private boolean isKeyValid(char e){

        if (e == '+' || e == '-' || e == '*' || e == '/' || e == '%' || e == '.')
            return true;

        return false;
    }
}