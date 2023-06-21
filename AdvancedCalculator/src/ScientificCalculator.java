import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * <b>Scientific Calculator</b>
 * Scientific Calculator
 * It can be used with keyboard and buttons.
 *
 * for now it only can calculate sin, cos, tan, cot.
 *
 * @author Negar
 * @since 2020-05-05
 * @version 1.0
 */
public class ScientificCalculator extends StandardCalculator {

    private JButton logBtn;
    private JButton powBtn;
    private JButton sinBtn;
    private JButton tanBtn;
    private JButton lnBtn;
    private JButton expBtn;
    private JButton revBtn;
    private JButton shiftBtn;
    private JButton pow2Btn;
    private JButton bracketBtn1;
    private JButton bracketBtn2;
    private JButton PIBtn;
    private JButton EBtn;

    /**
     * creating the calculator.
     * initializing.
     */
    public ScientificCalculator(){
        super();

        logBtn = new JButton();
        powBtn = new JButton();
        sinBtn = new JButton("sin");
        tanBtn = new JButton("tan");
        lnBtn = new JButton("ln");
        expBtn = new JButton("exp");
        EBtn = new JButton("e");
        PIBtn = new JButton("π");
        bracketBtn1 = new JButton("(");
        bracketBtn2 = new JButton(")");
        revBtn = new JButton("1/x");
        pow2Btn = new JButton("pow2");
        shiftBtn = new JButton("shift");
    }

    /**
     * making the scientific panel.
     * It contains a keyboard panel and a display panel.
     *
     * @return the scientific Panel for scientific Calculator.
     */
    public JPanel makePanel(){

        JPanel scientificPanel = new JPanel();
        scientificPanel.setSize(415,640);
        scientificPanel.setLayout(null);

        JPanel keyboardPanel = new JPanel();
        keyboardPanel.setBounds(0,100,400,470);
        keyboardPanel.setLayout(new GridLayout(8,4));

        logBtn.setText("Log");
        keyboardPanel.add(logBtn);


        powBtn.setText("Pow");
        keyboardPanel.add(powBtn);

        keyboardPanel.add(sinBtn);
        sinBtn.addActionListener(new ScientificAction());

        keyboardPanel.add(tanBtn);
        tanBtn.addActionListener(new ScientificAction());

        keyboardPanel.add(lnBtn);

        keyboardPanel.add(expBtn);

        keyboardPanel.add(PIBtn);

        keyboardPanel.add(EBtn);

        keyboardPanel.add(bracketBtn1);

        keyboardPanel.add(bracketBtn2);

        keyboardPanel.add(revBtn);

        keyboardPanel.add(pow2Btn);

        CBtn.setText("C");
        keyboardPanel.add(CBtn);
        CBtn.addActionListener(new StandardAction());
        CBtn.setToolTipText("Clearing the whole equation.");

        CEBtn.setText("CE");
        keyboardPanel.add(CEBtn);
        CEBtn.addActionListener(new StandardAction());
        CEBtn.setToolTipText("Deleting the last Argument.");

        keyboardPanel.add(shiftBtn);
        shiftBtn.addActionListener(new ScientificAction());

        JButton multiBtn = new JButton();
        multiBtn.setText("×");
        keyboardPanel.add(multiBtn);
        //multiBtn.addActionListener(new StandardAction());
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
        //divBtn.addActionListener(new StandardAction());
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
        //sumBtn.addActionListener(new StandardAction());
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
        //subBtn.addActionListener(new StandardAction());
        subBtn.setToolTipText("Subtraction");

        dotBtn.setText(".");
        keyboardPanel.add(dotBtn);
        dotBtn.addActionListener(new StandardAction());
        dotBtn.setToolTipText("Decimal");

        JButton zeroBtn = new JButton();
        zeroBtn.setText("0");
        keyboardPanel.add(zeroBtn);
        zeroBtn.setBackground(Color.cyan);
        zeroBtn.addActionListener(new StandardAction());
        zeroBtn.setToolTipText("Number 0");

        doBtn.setText("=");
        keyboardPanel.add(doBtn);
        //doBtn.addActionListener(new StandardAction());
        doBtn.setToolTipText("Equal");

        JButton remainBtn = new JButton();
        remainBtn.setText("%");
        keyboardPanel.add(remainBtn);
        //remainBtn.addActionListener(new StandardAction());
        remainBtn.setToolTipText("Getting the remaining of diversion.");

        display.setEditable(false);
        display.setFont(new Font("Arial", 14,14));
        display.addKeyListener(new StandardAction());
        display.setToolTipText("If you want to type with keyboard you must first click on the Test Area.");

        JScrollPane scrollPane = new JScrollPane(display);
        scrollPane.setPreferredSize(new Dimension(400, 100));
        scrollPane.setLocation(0,0);
        scrollPane.setBounds(0,0,400,100);
        scrollPane.setToolTipText("You can scroll the Test Area.");

        scientificPanel.add(scrollPane);
        scientificPanel.add(keyboardPanel);

        return scientificPanel;
    }

    /**
     * for action listener and key listener.
     */
    private class ScientificAction implements ActionListener {

        /**
         * It will change the sin and tan button with shift button
         * to cos and cot button and call for trigonometry method to calculate them.
         *
         * the shift only work through shift button.
         *
         * @param e action event from buttons.
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == shiftBtn){

                if (sinBtn.getActionCommand().equals("sin")){
                    sinBtn.setText("cos");
                    tanBtn.setText("cot");

                } else {
                    sinBtn.setText("sin");
                    tanBtn.setText("cos");
                }

            } else if (e.getActionCommand().equals("sin")){

                trigonometry(e.getActionCommand());

            } else if (e.getActionCommand().equals("cos")){

                trigonometry(e.getActionCommand());

            } else if (e.getActionCommand().equals("tan")){

                trigonometry(e.getActionCommand());

            } else if (e.getActionCommand().equals("cot")){

                trigonometry(e.getActionCommand());

            }

        }
    }

    /**
     * calculate the sin, cos, tan, cot calculation.
     *
     * @param arg the argument from one of the four Strings above
     * @return if it was successful it will return true.
     */
    private boolean trigonometry(String arg){

        if (equation.size() > 0){

            String num = equation.get( equation.size()-1 );
            display.append( arg );
            double temp = 0.0;

            if (isNum(num)){

                firstArg = Double.parseDouble(num);

                if (arg.equals("sin")){
                    temp = Math.sin(firstArg);

                } else if (arg.equals("cos")){
                    temp = Math.cos(firstArg);

                } else if (arg.equals("tan")){
                    temp = Math.tan(firstArg);

                } else if (arg.equals("cot")){
                    temp = 1/Math.tan(firstArg);

                }

                String res = "" + temp;
                display.append("\n" + res + "\n");
                equation = new ArrayList<String>();
                equation.add(res);
                firstArg = temp;
                secondArg = 0;
                size = 1;

                return true;

            } else
                System.out.println("Error!-8");

        }else
            System.out.println("Error!-7");

        return false;
    }
}
