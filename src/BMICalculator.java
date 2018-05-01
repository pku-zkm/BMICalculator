import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BMICalculator extends JFrame {
    public BMICalculator() {
        super("BMI Calculator");

        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        JPanel[] panels = new JPanel[]{new JPanel(flowLayout), new JPanel(flowLayout), new JPanel(flowLayout)};
        JLabel[] labels = new JLabel[]{new JLabel("性别："), new JLabel("身高："), new JLabel("体重：")};
        Font fontLabel = new Font("黑体", Font.PLAIN, 16);
        for (int i = 0; i < labels.length; i++) {
            labels[i].setFont(fontLabel);
            panels[i].add(labels[i]);
        }


        rbGender = new JRadioButton[]{new JRadioButton("男", true),
                new JRadioButton("女")};
        bgGender = new ButtonGroup();
        for (JRadioButton radioButton : rbGender) {
            bgGender.add(radioButton);
            panels[0].add(radioButton);
        }

        tfHeight = new JTextField(10);
        panels[1].add(tfHeight);

        tfWeight = new JTextField(10);
        panels[2].add(tfWeight);

        cbUnit = new JComboBox[]{new JComboBox<String>(), new JComboBox<String>()};
        cbUnit[0].addItem("米(m)");
        cbUnit[0].addItem("厘米(cm)");
        cbUnit[1].addItem("公斤(kg)");
        cbUnit[1].addItem("斤");
        panels[1].add(cbUnit[0]);
        panels[2].add(cbUnit[1]);

        setLayout(new GridLayout(4, 1));
        for (JPanel panel : panels) {
            getContentPane().add(panel);
        }

        JPanel pnlCalc = new JPanel(new BorderLayout());
        pnlCalc.setBorder(new EmptyBorder(5, 5, 5, 5));
        btnCalc = new JButton("计算");
        btnCalc.addActionListener(e -> calc());
        pnlCalc.add(btnCalc, BorderLayout.EAST);
        getContentPane().add(pnlCalc);

        lblResult = new JLabel();
        lblResult.setFont(new Font("宋体", Font.PLAIN, 16));
        pnlCalc.add(lblResult, BorderLayout.CENTER);


        setSize(270, 170);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void calc() {
        double height = Double.parseDouble(tfHeight.getText());
        double weight = Double.parseDouble(tfWeight.getText());
        if (cbUnit[0].getSelectedIndex() == 1) {
            height /= 100;
        }
        if (cbUnit[1].getSelectedIndex() == 1) {
            weight /= 2;
        }
        double bmi = weight / (height * height);
        String comment;
        if (bmi < 18.5) {
            comment = "过轻";
        } else if (bmi < 24) {
            comment = "正常";
        } else if (bmi < 28) {
            comment = "偏胖";
        } else {
            comment = "肥胖";
        }
        SwingUtilities.invokeLater(() ->
                lblResult.setText(String.format("您的BMI是%.1f，%s。", bmi, comment)));
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BMICalculator().setVisible(true));
    }

    private JLabel lblResult;
    private JTextField tfHeight;
    private JTextField tfWeight;
    private JButton btnCalc;
    private JRadioButton[] rbGender;
    private ButtonGroup bgGender;
    private JComboBox[] cbUnit;

}
