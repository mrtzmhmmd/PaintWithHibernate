package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.MD5;
import controller.EntityManager;
import model.User;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class Login {

	private JFrame frmLogin;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 217, 134);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.setLocationRelativeTo(null);
		frmLogin.getContentPane().setLayout(new GridLayout(0, 2, 2, 10));

		JLabel lblUsername = new JLabel("Username:");
		frmLogin.getContentPane().add(lblUsername);

		txtUsername = new JTextField();
		frmLogin.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		frmLogin.getContentPane().add(lblPassword);

		txtPassword = new JPasswordField();
		frmLogin.getContentPane().add(txtPassword);

		JButton btnLogin = new JButton("Login");

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = new String(txtPassword.getPassword());
				String passwordMD5 = null;
				if ((!username.equals("")) && (!password.equals(""))) {
					try {
						passwordMD5 = MD5.getDigest(password);
					} catch (NoSuchAlgorithmException e2) {
						e2.printStackTrace();
					}
					User user = EntityManager.getUser(username, passwordMD5);
					if (user != null) {
						JOptionPane.showMessageDialog(null, "Hello " + user.getName());
						frmLogin.setVisible(false);
						Paint.enter(user);
					} else {
						JOptionPane.showMessageDialog(null, "User not exist!");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Enter username and password!");
				}
			}
		});
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.setVisible(false);
				Register.enter();
			}
		});
		frmLogin.getContentPane().add(btnRegister);
		frmLogin.getContentPane().add(btnLogin);
	}
}