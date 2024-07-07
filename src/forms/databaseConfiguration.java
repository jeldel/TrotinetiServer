package forms;

import constants.MyServerConstant;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class databaseConfiguration extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel lblUrl;
    private JTextField txtUrl;
    private JLabel lblUsername;
    private JTextField txtPassword;
    private JLabel lblPassword;
    private JTextField txtUsername;
    private Properties properties;

    public databaseConfiguration() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Konfiguracija baze");
        setBounds(500, 200, 450, 300);
        popuniFormu();


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (validation()) {
                        String url = txtUrl.getText();
                        String username = txtUsername.getText();
                        String password = txtPassword.getText();

                        properties.put(MyServerConstant.DB_CONFIG_URL, url);
                        properties.store(new FileOutputStream(MyServerConstant.FILE_PATH), null);
                        properties.put(MyServerConstant.DB_CONFIG_USERNAME, username);
                        properties.store(new FileOutputStream(MyServerConstant.FILE_PATH), null);
                        properties.put(MyServerConstant.DB_CONFIG_PASSWORD, password);
                        properties.store(new FileOutputStream(MyServerConstant.FILE_PATH), null);
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
        if (txtUrl.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Polje \"URL\" ne sme biti prazno!");
            return false;
        } else if (txtUsername.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Polje \"Username\" ne sme biti prazno!");
            return false;
        } else if (txtPassword.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Polje \"Password\" ne sme biti prazno!");
            return false;
        }
        return true;
    }

    private void popuniFormu() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream(MyServerConstant.FILE_PATH));
            txtUrl.setText(properties.getProperty(MyServerConstant.DB_CONFIG_URL));
            txtUsername.setText(properties.getProperty(MyServerConstant.DB_CONFIG_USERNAME));
            txtPassword.setText(properties.getProperty(MyServerConstant.DB_CONFIG_PASSWORD));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        databaseConfiguration dialog = new databaseConfiguration();
        dialog.setVisible(true);
        System.exit(0);
    }
}
