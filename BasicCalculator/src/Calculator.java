import javax.swing.*;
import java.awt.*;

/**
 * <b>Calculator</b>
 * a Standard Calculator and a Scientific Calculator.
 *
 * @author Negar
 * @since 2020-05-01
 * @version 0.0
 */
public class Calculator {

    JFrame frame;
    JTabbedPane tabPanel;

    /**
     * Making the Frame.
     */
    public Calculator(){

        frame = new JFrame("Calculator");
        frame.setBounds(600,100,415,640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }

        tabPanel = new JTabbedPane();
        tabPanel.setSize(415,640);
    }

    /**
     * Making the Standard Calculator.
     */
    private void makeStandard(){
        JPanel standardPanel = new JPanel();
        standardPanel.setSize(415,640);
        standardPanel.setLayout(null);

        JPanel keyboardPanel = new JPanel();
        keyboardPanel.setBounds(0,150,400,420);
        keyboardPanel.setLayout(new GridLayout(5,4));
        frame.add(keyboardPanel);

        JButton CBtn = new JButton();
        CBtn.setText("C");
        keyboardPanel.add(CBtn);

        JButton CEBtn = new JButton();
        CEBtn.setText("CE");
        keyboardPanel.add(CEBtn);

        JButton remainBtn = new JButton();
        remainBtn.setText("%");
        keyboardPanel.add(remainBtn);

        JButton multiBtn = new JButton();
        multiBtn.setText("×");
        keyboardPanel.add(multiBtn);

        for (int i = 9; i > 6; i--) {
            JButton btn = new JButton();
            btn.setText("" + i);
            keyboardPanel.add(btn);
            btn.setBackground(Color.cyan);
        }

        JButton divBtn = new JButton();
        divBtn.setText("÷");
        keyboardPanel.add(divBtn);

        for (int i = 6; i > 3; i--) {
            JButton btn = new JButton();
            btn.setText("" + i);
            keyboardPanel.add(btn);
            btn.setBackground(Color.cyan);
        }

        JButton sumBtn = new JButton();
        sumBtn.setText("+");
        keyboardPanel.add(sumBtn);

        for (int i = 3; i > 0; i--) {
            JButton btn = new JButton();
            btn.setText("" + i);
            keyboardPanel.add(btn);
            btn.setBackground(Color.cyan);
        }

        JButton subBtn = new JButton();
        subBtn.setText("-");
        keyboardPanel.add(subBtn);

        JButton bracketBtn = new JButton();
        bracketBtn.setText("()");
        keyboardPanel.add(bracketBtn);

        JButton zeroBtn = new JButton();
        zeroBtn.setText("0");
        keyboardPanel.add(zeroBtn);
        zeroBtn.setBackground(Color.cyan);

        JButton dotBtn = new JButton();
        dotBtn.setText(".");
        keyboardPanel.add(dotBtn);

        JButton doBtn = new JButton();
        doBtn.setText("=");
        keyboardPanel.add(doBtn);

        JTextArea display = new JTextArea(3,10);
        display.setEditable(false);
        display.setFont(new Font("Arial", 14,14));

        JScrollPane scrollPane = new JScrollPane(display);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        scrollPane.setLocation(0,0);
        scrollPane.setBounds(0,0,400,150);
        frame.getContentPane().add(scrollPane);

        standardPanel.add(scrollPane);
        standardPanel.add(keyboardPanel);
        tabPanel.add("Standard Calculator", standardPanel);
    }

    /**
     * Making the Scientific Calculator.
     */
    private void makeScientific(){
        JPanel scientificPanel = new JPanel();
        scientificPanel.setSize(415,640);
        scientificPanel.setLayout(null);

        JPanel keyboardPanel = new JPanel();
        keyboardPanel.setBounds(0,100,400,470);
        keyboardPanel.setLayout(new GridLayout(8,4));
        frame.add(keyboardPanel);

        JButton logBtn = new JButton();
        logBtn.setText("Log");
        keyboardPanel.add(logBtn);

        JButton powBtn = new JButton();
        powBtn.setText("Pow");
        keyboardPanel.add(powBtn);

        JButton sinBtn = new JButton("sin/cos");
        keyboardPanel.add(sinBtn);

        JButton tanBtn = new JButton("tan/cot");
        keyboardPanel.add(tanBtn);

        JButton lnBtn = new JButton("ln");
        keyboardPanel.add(lnBtn);

        JButton expBtn = new JButton("exp");
        keyboardPanel.add(expBtn);

        JButton PIBtn = new JButton();
        PIBtn.setText("π");
        keyboardPanel.add(PIBtn);

        JButton EBtn = new JButton();
        EBtn.setText("e");
        keyboardPanel.add(EBtn);

        JButton bracketBtn1 = new JButton();
        bracketBtn1.setText("(");
        keyboardPanel.add(bracketBtn1);

        JButton bracketBtn2 = new JButton();
        bracketBtn2.setText(")");
        keyboardPanel.add(bracketBtn2);

        JButton revBtn = new JButton("1/x");
        keyboardPanel.add(revBtn);

        JButton pow2Btn = new JButton("pow2");
        keyboardPanel.add(pow2Btn);

        JButton CBtn = new JButton();
        CBtn.setText("C");
        keyboardPanel.add(CBtn);

        JButton CEBtn = new JButton();
        CEBtn.setText("CE");
        keyboardPanel.add(CEBtn);

        JButton shiftBtn = new JButton("shift");
        keyboardPanel.add(shiftBtn);

        JButton multiBtn = new JButton();
        multiBtn.setText("×");
        keyboardPanel.add(multiBtn);

        for (int i = 9; i > 6; i--) {
            JButton btn = new JButton();
            btn.setText("" + i);
            keyboardPanel.add(btn);
            btn.setBackground(Color.cyan);
        }

        JButton divBtn = new JButton();
        divBtn.setText("÷");
        keyboardPanel.add(divBtn);

        for (int i = 6; i > 3; i--) {
            JButton btn = new JButton();
            btn.setText("" + i);
            keyboardPanel.add(btn);
            btn.setBackground(Color.cyan);
        }

        JButton sumBtn = new JButton();
        sumBtn.setText("+");
        keyboardPanel.add(sumBtn);

        for (int i = 3; i > 0; i--) {
            JButton btn = new JButton();
            btn.setText("" + i);
            keyboardPanel.add(btn);
            btn.setBackground(Color.cyan);
        }

        JButton subBtn = new JButton();
        subBtn.setText("-");
        keyboardPanel.add(subBtn);

        JButton dotBtn = new JButton();
        dotBtn.setText(".");
        keyboardPanel.add(dotBtn);

        JButton zeroBtn = new JButton();
        zeroBtn.setText("0");
        keyboardPanel.add(zeroBtn);
        zeroBtn.setBackground(Color.cyan);

        JButton doBtn = new JButton();
        doBtn.setText("=");
        keyboardPanel.add(doBtn);

        JButton remainBtn = new JButton();
        remainBtn.setText("%");
        keyboardPanel.add(remainBtn);

        JTextArea display = new JTextArea(3,10);
        display.setEditable(false);
        display.setFont(new Font("Arial", 14,14));

        JScrollPane scrollPane = new JScrollPane(display);
        scrollPane.setPreferredSize(new Dimension(400, 100));
        scrollPane.setLocation(0,0);
        scrollPane.setBounds(0,0,400,100);
        frame.getContentPane().add(scrollPane);

        scientificPanel.add(scrollPane);
        scientificPanel.add(keyboardPanel);
        tabPanel.add("Scientific Calculator",scientificPanel);
    }

    /**
     * init the Calculator.
     */
    public void init(){

        makeStandard();
        makeScientific();

        frame.add(tabPanel);
        frame.setVisible(true);
    }
}
