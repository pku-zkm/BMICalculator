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
            radioButton.setFont(fontLabel);
            bgGender.add(radioButton);
            panels[0].add(radioButton);
        }

        tfHeight = new JTextField(16);
        panels[1].add(tfHeight);
        tfWeight = new JTextField(16);
        panels[2].add(tfWeight);

        cbUnit = new JComboBox[]{new JComboBox<String>(), new JComboBox<String>()};
        cbUnit[0].addItem("米(m)");
        cbUnit[0].addItem("厘米(cm)");
        cbUnit[1].addItem("公斤(kg)");
        cbUnit[1].addItem("斤");
        Font fontSong = new Font("宋体", Font.PLAIN, 14);
        cbUnit[0].setFont(fontSong);
        cbUnit[1].setFont(fontSong);
        panels[1].add(cbUnit[0]);
        panels[2].add(cbUnit[1]);

        setLayout(new GridLayout(4, 1));
        for (JPanel panel : panels) {
            getContentPane().add(panel);
        }

        JPanel pnlCalc = new JPanel(new BorderLayout());
        pnlCalc.setBorder(new EmptyBorder(3, 5, 5, 7));
        btnCalc = new JButton("计算");
        btnCalc.setFont(fontSong);
        btnCalc.addActionListener(e -> calc());
        pnlCalc.add(btnCalc, BorderLayout.EAST);
        getContentPane().add(pnlCalc);

        Font fontTf = new Font("Monospaced", Font.PLAIN, 14);
        tfHeight.setFont(fontTf);
        tfWeight.setFont(fontTf);

        lblResult = new JLabel();
        lblResult.setFont(new Font("宋体", Font.PLAIN, 16));
        pnlCalc.add(lblResult, BorderLayout.CENTER);

        setSize(292, 165);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void calc() {
        if (tfHeight.getText().length() < 1 || tfWeight.getText().length() < 1) {
            return;
        }
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
        if (rbGender[0].isSelected()) {
            if (bmi < 20) {
                comment = "过轻";
            } else if (bmi < 25) {
                comment = "正常";
            } else if (bmi < 30) {
                comment = "偏胖";
            } else if (bmi < 35) {
                comment = "肥胖";
            } else {
                comment = "非常肥胖";
            }
        } else {
            if (bmi < 19) {
                comment = "过轻";
            } else if (bmi < 24) {
                comment = "正常";
            } else if (bmi < 29) {
                comment = "偏胖";
            } else if (bmi < 34) {
                comment = "肥胖";
            } else {
                comment = "非常肥胖";
            }
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
