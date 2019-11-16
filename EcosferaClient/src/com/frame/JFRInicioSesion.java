package com.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.session.IniciarSesion;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.naming.NamingException;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JFRInicioSesion extends JFrame {
	private static int tipoInicio;
	private JPanel contentPane;
	private static JPanel WrkSpace = new JPanel();
	private int xMouse;
	private int yMouse;
	private static JFRInicioSesion frame = new JFRInicioSesion();
	private JTextField txtUsuario;
	private JPasswordField pssContra;
	JTextPane lblError = new JTextPane();
	JLabel lblNewLabel = new JLabel("");
	JPanel panel_1 = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFRInicioSesion frame = new JFRInicioSesion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static JFRInicioSesion getFrame() {
		return frame;
	}
	/**
	 * Create the frame.
	 */
	private JFRInicioSesion() {
		setUndecorated(true);
		lblNewLabel.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 843, 710);
		contentPane = new JPanel();
		setBackground(new Color(0,0,0,0));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		tipoInicio = 1;
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0,0,0,0));
		panel.setBounds(29, 13, 765, 34);
		panel.setOpaque(true);
		contentPane.add(panel);
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				xMouse  = arg0.getX();

				yMouse = arg0.getY();
			}
		});
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();

				int y = e.getYOnScreen();

				 
				setLocation(x - xMouse , y - yMouse);
			}
			
		});
		
		
		WrkSpace.setBackground(Color.WHITE);
		WrkSpace.setBounds(455, 28, 358, 603);
		contentPane.add(WrkSpace);
		
		
		JLabel label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tipoInicio==1) {
					label_2.setIcon(new ImageIcon(JFRInicioSesion.class.getResource("/recursos/icons/btnState2.png")));
					tipoInicio=2;
					panel_1.setVisible(false);
					JpRegistroUsuario jp;
					try {
						jp = new JpRegistroUsuario();
						jp.setBounds(0, 0, 358, 603);
						jp.setVisible(true);

						WrkSpace.removeAll();
						WrkSpace.setLayout(null);
						WrkSpace.add(jp);
						WrkSpace.revalidate();
						WrkSpace.repaint();
					} catch (NamingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					label_2.setIcon(new ImageIcon(JFRInicioSesion.class.getResource("/recursos/icons/btnState1.png")));
					tipoInicio=1;

						panel_1.setVisible(true);
						panel_1.setBounds(12, 24, 334, 445);
						

						WrkSpace.removeAll();
						WrkSpace.setLayout(null);
						WrkSpace.add(panel_1);
						WrkSpace.revalidate();
						WrkSpace.repaint();
					
				}
			}
		});
		label_2.setIcon(new ImageIcon(JFRInicioSesion.class.getResource("/recursos/icons/btnState1.png")));
		label_2.setBounds(542, 634, 184, 63);
		contentPane.add(label_2);
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(JFRInicioSesion.class.getResource("/recursos/icons/initBackGround.png")));
		label.setBounds(0, 0, 843, 710);
		contentPane.add(label);
		WrkSpace.setVisible(false);
		WrkSpace.removeAll();
		WrkSpace.setLayout(null);
		
		
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(12, 24, 334, 445);
		WrkSpace.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("Usuario");
		label_1.setForeground(new Color(46, 139, 87));
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		label_1.setBounds(12, 14, 67, 20);
		panel_1.add(label_1);
		
		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			
			public void keyReleased(KeyEvent e) {
				txtUsuario.setText(txtUsuario.getText().toUpperCase());
			}
		});
		txtUsuario.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(108, 13, 209, 24);
		panel_1.add(txtUsuario);
		
		pssContra = new JPasswordField();
		pssContra.setBounds(108, 47, 209, 24);
		panel_1.add(pssContra);
		
		JLabel label_3 = new JLabel("Contrase\u00F1a");
		label_3.setForeground(new Color(46, 139, 87));
		label_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		label_3.setBounds(12, 48, 94, 20);
		panel_1.add(label_3);
		
		JButton button = new JButton("Aceptar");
		button.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if(!controles()) {
						String respuesta ="";
						respuesta = IniciarSesion.iniciar(txtUsuario.getText(), pssContra.getText());
						if(!respuesta.isEmpty()) {
							lblError.setText(respuesta);
							lblNewLabel.setVisible(true);
							
						}else {
							dispose();
							JFRPrincipal.main(null);
						}
					}
				} catch (NamingException e1) {
					lblError.setText(e1.getMessage());
					label.setVisible(true);
				}		
			}
		});
		button.setForeground(new Color(46, 139, 87));
		button.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		button.setBackground(new Color(245, 255, 250));
		button.setBounds(223, 84, 94, 27);
		panel_1.add(button);
		
		
		lblError.setForeground(new Color(255, 69, 0));
		lblError.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		lblError.setEditable(false);
		lblError.setBackground(Color.WHITE);
		lblError.setBounds(46, 268, 288, 55);
		panel_1.add(lblError);
		
		
		lblNewLabel.setIcon(new ImageIcon(JFRInicioSesion.class.getResource("/recursos/icons/Icon_ErrorMsg.png")));
		lblNewLabel.setBounds(12, 268, 41, 31);
		panel_1.add(lblNewLabel);
		

		WrkSpace.revalidate();
		WrkSpace.repaint();
		WrkSpace.setVisible(true);
		contentPane.revalidate();
		contentPane.repaint();
	}
	

	
	@SuppressWarnings("deprecation")
	public boolean controles() throws NamingException {
		boolean error = false;
		if(txtUsuario.getText().isEmpty()) {
			error = true;
			lblError.setText( "El campo usuario es obligatorio");
			lblNewLabel.setVisible(true);
		}else if(pssContra.getText().isEmpty()) {
			error = true;
			lblError.setText("El campo contraseña es obligatorio");
			lblNewLabel.setVisible(true);
		}
		return error;
		
	}
}
