package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import model.User;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Paint {

	private JFrame frmPaint;
	private ButtonGroup btnGroup;
	protected static Color color = null;
	protected static int flag = 0;

	public static void enter(final User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Paint window = new Paint(user);
					window.frmPaint.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Paint(User user) throws SQLException {
		initialize(user);
	}

	private void initialize(User user) throws SQLException {
		frmPaint = new JFrame();
		frmPaint.setTitle("Welcome " + user.getName());
		frmPaint.setBounds(100, 100, 679, 570);
		frmPaint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPaint.setLocationRelativeTo(null);
		frmPaint.getContentPane().setLayout(null);

		final JPanel jPanel = new JPanel();
		jPanel.setBounds(10, 10, 510, 510);
		frmPaint.getContentPane().add(jPanel);

		final Panel panel = new Panel(user);
		JScrollPane scroll = new JScrollPane(panel);
		jPanel.add(scroll);

		JButton btnRectangle = new JButton("Rectangle");
		btnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 1;
				Panel.zoomFactor = 1;
			}
		});
		btnRectangle.setBounds(520, 11, 135, 23);
		frmPaint.getContentPane().add(btnRectangle);

		JButton btnCircle = new JButton("Circle");
		btnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 2;
				Panel.zoomFactor = 1;
			}
		});
		btnCircle.setBounds(520, 45, 135, 23);
		frmPaint.getContentPane().add(btnCircle);

		JButton btnLine = new JButton("Line");
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 3;
				Panel.zoomFactor = 1;
			}
		});
		btnLine.setBounds(520, 79, 135, 23);
		frmPaint.getContentPane().add(btnLine);

		JButton btnRoundRectangle = new JButton("Round Rectangle");
		btnRoundRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 4;
			}
		});
		btnRoundRectangle.setBounds(520, 113, 135, 23);
		frmPaint.getContentPane().add(btnRoundRectangle);

		JRadioButton rdbtnBlack = new JRadioButton("Black");
		rdbtnBlack.setBounds(520, 150, 109, 23);
		rdbtnBlack.setActionCommand("Black");
		frmPaint.getContentPane().add(rdbtnBlack);

		JRadioButton rdbtnBlue = new JRadioButton("Blue");
		rdbtnBlue.setBounds(520, 176, 109, 23);
		rdbtnBlue.setActionCommand("Blue");
		frmPaint.getContentPane().add(rdbtnBlue);

		JRadioButton rdbtnGreen = new JRadioButton("Green");
		rdbtnGreen.setBounds(520, 202, 109, 23);
		rdbtnGreen.setActionCommand("Green");
		frmPaint.getContentPane().add(rdbtnGreen);

		JRadioButton rdbtnRed = new JRadioButton("Red");
		rdbtnRed.setBounds(520, 228, 109, 23);
		rdbtnRed.setActionCommand("Red");
		frmPaint.getContentPane().add(rdbtnRed);

		JRadioButton rdbtnYellow = new JRadioButton("Yellow");
		rdbtnYellow.setBounds(520, 254, 109, 23);
		rdbtnYellow.setActionCommand("Yellow");
		frmPaint.getContentPane().add(rdbtnYellow);

		btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnBlack);
		btnGroup.add(rdbtnBlue);
		btnGroup.add(rdbtnGreen);
		btnGroup.add(rdbtnRed);
		btnGroup.add(rdbtnYellow);

		JButton btnZoomIn = new JButton("Zoom in");
		btnZoomIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.zoomIn();
			}
		});
		btnZoomIn.setBounds(520, 314, 135, 23);
		frmPaint.getContentPane().add(btnZoomIn);

		JButton btnZoomOut = new JButton("Zoom out");
		btnZoomOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.zoomOut();
			}
		});
		btnZoomOut.setBounds(520, 348, 135, 23);
		frmPaint.getContentPane().add(btnZoomOut);

		JButton btnSaveLogOut = new JButton("Save and Log Out");
		btnSaveLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Panel.saveShapeInDatabase();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				color = null;
				frmPaint.setVisible(false);
				Login.main(null);
			}
		});
		btnSaveLogOut.setBounds(520, 416, 135, 23);
		frmPaint.getContentPane().add(btnSaveLogOut);

		JButton btnSelectShape = new JButton("Select Shape");
		btnSelectShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 5;
			}
		});
		btnSelectShape.setBounds(520, 382, 135, 23);
		frmPaint.getContentPane().add(btnSelectShape);

		JButton btnSaveAsImage = new JButton("Save as Image");
		btnSaveAsImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedImage image = new BufferedImage(jPanel.getWidth(), jPanel.getHeight(),
						BufferedImage.TYPE_INT_RGB);
				try {
					ImageIO.write(image, "PNG", new File("X.PNG"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSaveAsImage.setBounds(520, 450, 135, 23);
		frmPaint.getContentPane().add(btnSaveAsImage);

		class VoteActionListener implements ActionListener {
			public void actionPerformed(ActionEvent ex) {
				String strColor = btnGroup.getSelection().getActionCommand();
				color = strToColor(strColor);
			}
		}

		ActionListener al = new VoteActionListener();
		rdbtnBlack.addActionListener(al);
		rdbtnBlue.addActionListener(al);
		rdbtnGreen.addActionListener(al);
		rdbtnRed.addActionListener(al);
		rdbtnYellow.addActionListener(al);
	}

	public static Color strToColor(String strColor) {
		switch (strColor) {
		case "Black":
			return Color.BLACK;
		case "Blue":
			return Color.BLUE;
		case "Green":
			return Color.GREEN;
		case "Red":
			return Color.RED;
		case "Yellow":
			return Color.YELLOW;
		default:
			return Color.CYAN;
		}
	}

	public static String colorToString(Color c) {
		if (c.equals(Color.BLACK))
			return "Black";
		else if (c.equals(Color.BLUE))
			return "Blue";
		else if (c.equals(Color.GREEN))
			return "Green";
		else if (c.equals(Color.RED))
			return "Red";
		else if (c.equals(Color.YELLOW))
			return "Yellow";
		return "Cyan";
	}
}