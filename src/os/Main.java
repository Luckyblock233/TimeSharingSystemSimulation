package os;

import javax.swing.*;
import os.Process.*;

class Start {
    JPanel myPanel = new JPanel();
    JTextField Field1 = new JTextField(5);
    JTextField Field2 = new JTextField(5);
    JTextField Field3 = new JTextField(5);
    JTextField Field4 = new JTextField(5);
    JTextField Field5 = new JTextField(5);

    private boolean check() {
        try {
            Infomation.memorySize=Integer.parseInt(Field1.getText());
            Infomation.singleTime=Integer.parseInt(Field2.getText());
            Infomation.osSize=Integer.parseInt(Field3.getText());
            Infomation.channel=Integer.parseInt(Field4.getText());
            Infomation.storageSize=Integer.parseInt(Field5.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "请输入合法数字！",
                    "ERROR!", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (Infomation.osSize > Infomation.memorySize) {
            JOptionPane.showMessageDialog(null, "系统所需内存大于总内存！",
                    "ERROR!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    public Start() {
        myPanel.add(new JLabel("内存大小:"));
        myPanel.add(Field1);
        myPanel.add(Box.createHorizontalStrut(15)); 
        myPanel.add(new JLabel("时间片大小:"));
        myPanel.add(Field2);
        myPanel.add(Box.createHorizontalStrut(15)); 
        myPanel.add(new JLabel("系统所需内存:"));
        myPanel.add(Field3);
        myPanel.add(Box.createHorizontalStrut(15)); 
        myPanel.add(new JLabel("最大道数:"));
        myPanel.add(Field4);
        myPanel.add(Box.createHorizontalStrut(15)); 
        myPanel.add(new JLabel("外存大小:"));
        myPanel.add(Field5);
        myPanel.add(Box.createHorizontalStrut(15)); 

        while (true) {
            int result = JOptionPane.showConfirmDialog(null, myPanel,
                    "Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION && this.check()) {
                new GUI();
                break;
            } else if (result == JOptionPane.OK_CANCEL_OPTION) {
                break;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new Start();
    }
}
