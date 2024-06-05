package os.File;
import os.Infomation;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class FileGUI extends JFrame {
    int FidCount = 0;
    private ArrayList<FCB> files = new ArrayList<FCB>();

    private int [] storageList = new int[Infomation.storageSize + 1];

    private JPanel createPanel;
    private JTextField nameField, pathField, sizeField;
    private JComboBox<String> typeComboBox;
    private JLabel nameLabel, pathLabel, sizeLabel, typeLabel;

    private JPanel infoPanel;
    private JTextField FidField1, nameField1, pathField1, sizeField1, typeField1, indexField1;
    private JLabel FidLabel1, nameLabel1, pathLabel1, sizeLabel1, typeLabel1, indexLabel1;

    private JPanel listPanel;
    DefaultListModel fileListModel = new DefaultListModel();
    JList fileJList = new JList(fileListModel);

    private StoragePanel storagePanel;

    private JPanel buttonPanel;
    private JButton createBtn, deleteBtn;
    
    int selectedFid = -1;

    public FileGUI() {
        this.setLayout(null);
        this.setBounds(30,30,1500,820);
        this.setTitle("文件操作");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        createPanel=new JPanel(null);
        createPanel.setBounds(20,20,350,250);
        TitledBorder titledBorder0 = BorderFactory.createTitledBorder("创建文件");
        Font font0 = new Font("宋体", Font.BOLD, 18);
        titledBorder0.setTitleFont(font0);
        createPanel.setBorder(titledBorder0);

        nameLabel = new JLabel("文件名称");
        nameLabel.setBounds(30, 40, 100, 30);
        nameLabel.setFont(new Font("宋体",Font.BOLD,20));
        createPanel.add(nameLabel);
        typeLabel = new JLabel("类型");
        typeLabel.setBounds(30, 90, 100, 30);
        typeLabel.setFont(new Font("宋体",Font.BOLD,20));
        createPanel.add(typeLabel);
        pathLabel = new JLabel("父目录");
        pathLabel.setBounds(30, 140, 100, 30);
        pathLabel.setFont(new Font("宋体",Font.BOLD,20));
        createPanel.add(pathLabel);
        sizeLabel = new JLabel("大小");
        sizeLabel.setBounds(30, 190, 100, 30);
        sizeLabel.setFont(new Font("宋体",Font.BOLD,20));
        createPanel.add(sizeLabel);

        nameField = new JTextField(20);
        nameField.setBounds(150, 40, 160, 30);
        createPanel.add(nameField);
        typeComboBox = new JComboBox<String>();
        typeComboBox.addItem("目录");
        typeComboBox.addItem("文件");
        typeComboBox.setBounds(150, 90, 160, 30);
        createPanel.add(typeComboBox);
        pathField = new JTextField(20);
        pathField.setBounds(150, 140, 160, 30);
        createPanel.add(pathField);
        sizeField = new JTextField(20);
        sizeField.setBounds(150, 190, 160, 30);
        createPanel.add(sizeField);

        this.add(createPanel);

        infoPanel=new JPanel(null);
        infoPanel.setBounds(20,320,350,350);
        TitledBorder titledBorder1 = BorderFactory.createTitledBorder("当前文件信息");
        titledBorder1.setTitleFont(font0);
        infoPanel.setBorder(titledBorder1);

        FidLabel1 = new JLabel("文件编号");
        FidLabel1.setBounds(30, 40, 100, 30);
        FidLabel1.setFont(new Font("宋体",Font.BOLD,20));
        infoPanel.add(FidLabel1);
        nameLabel1 = new JLabel("文件名称");
        nameLabel1.setBounds(30, 90, 100, 30);
        nameLabel1.setFont(new Font("宋体",Font.BOLD,20));
        infoPanel.add(nameLabel1);
        typeLabel1 = new JLabel("类型");
        typeLabel1.setBounds(30, 140, 100, 30);
        typeLabel1.setFont(new Font("宋体",Font.BOLD,20));
        infoPanel.add(typeLabel1);
        pathLabel1 = new JLabel("父目录");
        pathLabel1.setBounds(30, 190, 100, 30);
        pathLabel1.setFont(new Font("宋体",Font.BOLD,20));
        infoPanel.add(pathLabel1);
        sizeLabel1 = new JLabel("大小");
        sizeLabel1.setBounds(30, 240, 100, 30);
        sizeLabel1.setFont(new Font("宋体",Font.BOLD,20));
        infoPanel.add(sizeLabel1);
        indexLabel1 = new JLabel("盘块号");
        indexLabel1.setBounds(30, 290, 100, 30);
        indexLabel1.setFont(new Font("宋体",Font.BOLD,20));
        infoPanel.add(indexLabel1);

        FidField1 = new JTextField(20);
        FidField1.setBounds(150, 40, 160, 30);
        FidField1.setEditable(false);
        infoPanel.add(FidField1);
        nameField1 = new JTextField(20);
        nameField1.setBounds(150, 90, 160, 30);
        nameField1.setEditable(false);
        infoPanel.add(nameField1);
        typeField1 = new JTextField(20);
        typeField1.setBounds(150, 140, 160, 30);
        typeField1.setEditable(false);
        infoPanel.add(typeField1);
        pathField1 = new JTextField(20);
        pathField1.setBounds(150, 190, 160, 30);
        pathField1.setEditable(false);
        infoPanel.add(pathField1);
        sizeField1 = new JTextField(20);
        sizeField1.setBounds(150, 240, 160, 30);
        sizeField1.setEditable(false);
        infoPanel.add(sizeField1);
        indexField1 = new JTextField(20);
        indexField1.setBounds(150, 290, 160, 30);
        indexField1.setEditable(false);
        infoPanel.add(indexField1);

        this.add(infoPanel);

        listPanel = new JPanel(new BorderLayout());
        listPanel.setBounds(400, 20, 600, 730);
        fileJList.setFont(new Font("微软雅黑",Font.PLAIN,22));
        listPanel.add(fileJList, BorderLayout.CENTER);
        this.add(listPanel);

        buttonPanel = new JPanel();
        buttonPanel.setBounds(0,710,400,170);
        buttonPanel.setLayout(null);

        createBtn = new JButton("创建文件");
        createBtn.setBounds(20,10,170,30);
        createBtn.setFont(new Font("宋体", Font.BOLD, 20));
        buttonPanel.add(createBtn);

        deleteBtn = new JButton("删除文件");
        deleteBtn.setBounds(200,10,170,30);
        deleteBtn.setFont(new Font("宋体", Font.BOLD, 20));
        buttonPanel.add(deleteBtn);

        this.add(buttonPanel);

        createBtn.addActionListener(new createActionListener());
        deleteBtn.addActionListener(new deleteActionListener());
        fileJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                selectedFid = fileJList.getSelectedIndex();
                if (selectedFid == -1) return ;
                showInfo();
//                System.out.println("now selected " + selectedFid);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                selectedFid = fileJList.getSelectedIndex();
                if (selectedFid == -1) return ;
                showInfo();
            }
        });

        storagePanel = new StoragePanel();
        this.add(storagePanel);
        for(int i = 0; i < Infomation.storageSize; ++ i) {
            storageList[i] = 0;
        }
        storagePanel.updateStorageList(storageList);

        this.setVisible(true);
    }

    public boolean allocateStorage(FCB f) {
        int p = 0;
        for(int i = 0; i < Infomation.storageSize; ++ i) {
            if (storageList[i] == 0 && p < f.getSize()) {
                storageList[i] = f.getFid();
                f.setIndex(p, i);
                f.setIndex(p + 1, -1);
                ++ p;
            }
        }
        if (p < f.getSize()) {
            for (int i = 0; i < Infomation.storageSize; ++ i) {
                if (storageList[i] == f.getFid()) {
                    storageList[i] = 0;
                }
            }
            return false;
        }
        storagePanel.updateStorageList(storageList);
        return true;
    }
    public void clearStorage(int Fid) {
        for (int i = 0; i < Infomation.storageSize; ++ i) {
            if (storageList[i] == Fid) {
                storageList[i] = 0;
            }
        }
        storagePanel.updateStorageList(storageList);
    }

    public class createActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = "", path = "", type = "";
            int size = 0;
            try {
                name = nameField.getText();
                path = pathField.getText();
                size = Integer.parseInt(sizeField.getText());
                type = typeComboBox.getSelectedItem().toString();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            FCB f = new FCB(FidCount + 1, name, path, size,
                    type, new Date(System.currentTimeMillis()).toString());
            FCB father = new FCB();
            boolean flagHavePath = path.isEmpty();
            for (FCB f1: files) {
                if (f.equels(f1)) {
                    JOptionPane.showMessageDialog(null, "文件或目录已存在！",
                            "警告", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (f.getPath().equals(f1.getFullDir())) {
                    flagHavePath = true;
                    father = f1;
                }
            }
            if (!flagHavePath) return ;
            if (!allocateStorage(f)) return ;

            ++ FidCount;
            if (father.getChild() == null) {
                father.setChild(f);
            } else {
                FCB brother = father.getChild();
                while (brother.getNext() != null) {
                    brother = brother.getNext();
                }
                brother.setNext(f);
            }

            files.add(f);
            files.sort(FCB::compareTo);
            fileListModel.clear();
            for (FCB f1: files) {
                fileListModel.addElement(f1.toString());
            }

            nameField.setText("");
            pathField.setText("");
            sizeField.setText("");
            typeComboBox.setSelectedIndex(0);
        }
    }

    public class deleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedFid == -1) return ;

            LinkedList<FCB> queue = new LinkedList<FCB>();
            queue.add(files.get(selectedFid));
            while (!queue.isEmpty()) {
                FCB f = queue.removeFirst();
                if (f.getNext() != null) queue.add(f.getNext());
                if (f.getChild() != null) queue.add(f.getChild());
                clearStorage(f.getFid());
                int index = files.indexOf(f);
                files.remove(index);
                fileListModel.remove(index);
            }
            clearInfo();
            fileJList.clearSelection();
            selectedFid = -1;
        }
    }

    private void showInfo() {
        FidField1.setText(String.valueOf(files.get(selectedFid).getFid()));
        nameField1.setText(files.get(selectedFid).getName());
        typeField1.setText(files.get(selectedFid).getType());
        pathField1.setText(files.get(selectedFid).getPath());
        sizeField1.setText(String.valueOf(files.get(selectedFid).getSize()));
        indexField1.setText(files.get(selectedFid).getIndexString());
    }
    private void clearInfo() {
        FidField1.setText("");
        nameField1.setText("");
        typeField1.setText("");
        pathField1.setText("");
        sizeField1.setText("");
        indexLabel1.setText("");
    }

    public static void main(String[] args) {
        new FileGUI();
    }
}