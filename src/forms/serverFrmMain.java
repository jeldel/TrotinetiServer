package forms;

import threads.ServerThread;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class serverFrmMain extends JDialog {
    private JPanel contentPane;
    private JButton btnStart;
    private JButton btnStop;
    private JButton btnDbConfig;
    private JButton btnPortConfig;
    private ServerThread serverThread;

    public serverFrmMain() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Rad sa serverom");
        setBounds(500, 200, 450, 300);
        btnStop.setEnabled(false);


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
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(serverThread == null || !serverThread.isAlive()){
                    try {
                        serverThread = new ServerThread();
                        serverThread.start();
                        btnStart.setEnabled(false);
                        btnStop.setEnabled(true);
                        btnDbConfig.setEnabled(false);
                        btnPortConfig.setEnabled(false);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(serverThread != null && serverThread.getServerSocket().isBound()){
                    try {
                        serverThread.getServerSocket().close();
                        btnStart.setEnabled(true);
                        btnStop.setEnabled(false);
                        btnDbConfig.setEnabled(true);
                        btnPortConfig.setEnabled(true);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        btnDbConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new databaseConfiguration().setVisible(true);
            }
        });
        btnPortConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new portConfiguration().setVisible(true);
            }
        });
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
        serverFrmMain dialog = new serverFrmMain();
        dialog.setVisible(true);
        System.exit(0);
    }
}
