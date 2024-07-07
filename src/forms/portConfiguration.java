package forms;

import constants.MyServerConstant;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class portConfiguration extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel lblPort;
    private JTextField txtPort;
    private Properties properties;

    public portConfiguration() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Konfiguracija porta");
        setBounds(500, 200, 450, 300);
        popuniFormu();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (validation()) {
                        String port = txtPort.getText().trim();
                        properties.put(MyServerConstant.PORT, port);
                        properties.store(new FileOutputStream(MyServerConstant.FILE_PATH_SERVER), null);
                        JOptionPane.showMessageDialog(buttonOK, "Uspesno cuvanje!");
                        dispose();
                        new serverFrmMain().setVisible(true);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private boolean validation() {
        if (txtPort.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Polje \"Port\" ne sme biti prazno!");
            return false;
        }
        try {
            int broj = Integer.valueOf(txtPort.getText());
            if(broj > 65535){
                JOptionPane.showMessageDialog(this, "Broj porta mora biti manji od 65535!");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "U polje \"Port\" mora biti upisana cifra!");
            return false;
        }
        return true;
    }

    private void popuniFormu() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(MyServerConstant.FILE_PATH_SERVER));
            txtPort.setText(properties.getProperty(MyServerConstant.PORT));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        portConfiguration dialog = new portConfiguration();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
