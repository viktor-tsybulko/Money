import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by viktor on 03.02.17.
 */
public class FirsPage {

    private static FirsPage instance;
    public static FirsPage getInstance(){
        if (instance == null)
            instance = new FirsPage();
        return instance;
    }
    private FirsPage(){

    }
    private JFrame jFrame = new JFrame("Money");
    private JLabel jLabel;
    private JScrollPane scrollPane;
    private JList jList;
    private JButton createButton;
    private JButton selectButton;
    private DefaultListModel defaultListModel = new DefaultListModel();

    public void start(){
        Box labelBox = Box.createHorizontalBox();
        jLabel = new JLabel("Your Party");
        labelBox.add(jLabel);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setBounds(50, 50, 250, 300);

        Box listBox = Box.createHorizontalBox();
        whichOpenStartPage();
        jList = new JList(defaultListModel);
        scrollPane = new JScrollPane(jList);
        scrollPane.setPreferredSize(new Dimension(100, 200));
        listBox.add(scrollPane);

        Box buttonBox = Box.createHorizontalBox();
        createButton = new JButton("Create Party");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonBox.add(createButton);
        buttonBox.add(Box.createHorizontalStrut(15));
        buttonBox.add(selectButton);

        Box mainBox = Box.createVerticalBox();
        mainBox.add(labelBox);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(listBox);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(buttonBox);
        mainBox.setBorder(new EmptyBorder(5, 5, 5, 5));

        jFrame.setContentPane(mainBox);
        jFrame.setVisible(true);
    }
    private void whichOpenStartPage(){
        OpenStartPage openStartPage = new OpenStartPage();
        if (openStartPage.findFiles()){
            for (File f : openStartPage.listFile){
                defaultListModel.addElement(f.getName().replaceAll(".sqlite", ""));
            }
        }else defaultListModel.addElement("No Party");
    }


}