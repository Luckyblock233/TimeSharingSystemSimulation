package os.File;

import javax.swing.*;
import os.Infomation;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class StoragePanel extends JPanel {
    private int[] storageList;
    private JLabel[][] graph = new JLabel[37][20];
    private int tip = 0;
    public StoragePanel() {
        this.setLayout(new GridLayout(37, 20));
        this.setBounds(1040, 20, 400, 740);
        TitledBorder titledBorder = BorderFactory.createTitledBorder("位示图 总磁块数：" + String.valueOf(Infomation.storageSize));
        titledBorder.setTitleFont(new Font("宋体", Font.BOLD, 18));
        this.setBorder(titledBorder);

        for (int i = 0; i < 37; i++) {
            for (int j = 0; j < 20; j++) {
                graph[i][j] = new JLabel();
                this.add(graph[i][j]);
            }
        }
        this.setVisible(true);
    }

    public void updateStorageList(int [] storageList) {
        this.storageList = storageList;

        for (int i = 0, pos = 0; i < 37; i++) {
            for (int j = 0; j < 20; j++) {
                graph[i][j].setText("");
                if (pos < storageList.length) {
                    graph[i][j].setText(String.valueOf(storageList[pos]));
                    ++ pos;
                }
            }
        }
    }
}
