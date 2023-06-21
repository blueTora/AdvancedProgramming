import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;

/**
 * <h1>Calculator</h1>
 * a Standard Calculator and a Scientific Calculator.
 *
 * @author Negar
 * @since 2020-05-05
 * @version 1.0
 */
public class Main implements ActionListener {

    private JFrame frame = new JFrame("Calculator");
    private JMenuBar bar = new JMenuBar();
    private JMenuItem exitItem = new JMenuItem("Exit", KeyEvent.VK_T);
    private JMenuItem copyItem = new JMenuItem("Copy Context", KeyEvent.VK_C);

    private StandardCalculator standardC;
    private ScientificCalculator scientificC;

    Main (){
        standardC = new StandardCalculator();
        scientificC = new ScientificCalculator();

        frame.setBounds(600,100,415,640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        exitItem.setMnemonic('E');
        exitItem.addActionListener(this);

        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
        copyItem.addActionListener(this);

        file.add(copyItem);
        file.add(exitItem);
        bar.add(file);
        frame.setJMenuBar(bar);

        JTabbedPane tabPanel = new JTabbedPane();
        tabPanel.setSize(415,640);

        JPanel StP = standardC.makePanel();
        StP.setFocusable(true);
        StP.requestFocusInWindow();

        JPanel ScP = scientificC.makePanel();

        tabPanel.add("Standard Calculator",  StP);
        tabPanel.add("Scientific Calculator", ScP);

        frame.add(tabPanel);
        frame.setVisible(true);
    }

    /**
     * calling the calculator class.
     * @param args Unused.
     */
    public static void main(String[] args) {
        try {

            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Look and Feel not set");
        }

        Main m = new Main();
    }

    /**
     * for barMenu actions.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == copyItem){
            standardC.display.selectAll();
            standardC.display.copy();

        } else if (e.getSource() == exitItem){
            System.exit(0);
        }
    }
}