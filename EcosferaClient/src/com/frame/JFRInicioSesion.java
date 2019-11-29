package com.frame;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.session.IniciarSesion;
import com.session.Sesion;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.SwingConstants;

public class JFRInicioSesion extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static int tipoInicio;
	private JPanel contentPane;
	public static JPanel WrkSpace = new JPanel();
	private int xMouse;
	private int yMouse;
	private static JFRInicioSesion frame = new JFRInicioSesion();
	private JTextField txtUsuario;
	private JPasswordField pssContra;
	JTextPane lblError = new JTextPane();
	JLabel lblNewLabel = new JLabel("");
	JPanel panel_1 = new JPanel();
	private final JPanel PnlLogOut = new JPanel();
	JLabel label_2 = new JLabel("");
	JLabel lblSingUp = new JLabel("");
	JLabel LogIn = new JLabel("");


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
		
		JLabel label_4 = new JLabel("");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setIcon(new ImageIcon(JFRInicioSesion.class.getResource("/recursos/logo.png")));
		label_4.setBounds(29, 28, 403, 650);
		contentPane.add(label_4);
		
		JLabel lblSingUp = new JLabel("Sing up");
		lblSingUp.setForeground(new Color(152, 251, 152));
		lblSingUp.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblSingUp.setBounds(562, 644, 64, 34);
		contentPane.add(lblSingUp);
		lblSingUp.setVisible(false);
		
		JLabel LogIn = new JLabel("LogIn");
		LogIn.setForeground(new Color(152, 251, 152));
		LogIn.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		LogIn.setBounds(630, 644, 56, 34);
		contentPane.add(LogIn);
		LogIn.setVisible(true);
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
		
		
		
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tipoInicio==1) {
					label_2.setIcon(new ImageIcon(JFRInicioSesion.class.getResource("/recursos/icons/btnState2.png")));
					tipoInicio=2;
					panel_1.setVisible(false);
					JpRegistroUsuario jp;
					lblSingUp.setVisible(true);
					LogIn.setVisible(false);
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
						reportarError(e.getMessage());
						e.printStackTrace();
					}
				}else {
					label_2.setIcon(new ImageIcon(JFRInicioSesion.class.getResource("/recursos/icons/btnState1.png")));
					tipoInicio=1;
					lblSingUp.setVisible(false);
					LogIn.setVisible(true);
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
		
		PnlLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				PnlLogOut.setBackground(new Color(255, 0, 0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				PnlLogOut.setBackground(new Color(0, 0, 0, 0));
			}
		});
		PnlLogOut.setBounds(29,579, 43, 44);
		PnlLogOut.setBackground(new Color(0,0,0,0));
		contentPane.add(PnlLogOut);

		PnlLogOut.setLayout(null);
		JLabel LblLogOut = new JLabel("");
		LblLogOut.setBounds(0, 0, 44, 43);
		PnlLogOut.add(LblLogOut);
		
				LblLogOut.setIcon(new ImageIcon(JFRPrincipal.class.getResource("/recursos/icons/LogOut_.png")));
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(JFRInicioSesion.class.getResource("/recursos/icons/initBackGround.png")));
		label.setBounds(0, 0, 843, 710);
		contentPane.add(label);
		WrkSpace.setVisible(false);
		WrkSpace.removeAll();
		WrkSpace.setLayout(null);
		
		
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(12, 24, 334, 538);
		WrkSpace.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("Usuario");
		label_1.setForeground(new Color(46, 139, 87));
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		label_1.setBounds(12, 96, 67, 20);
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
		txtUsuario.setBounds(108, 95, 209, 24);
		panel_1.add(txtUsuario);
		
		pssContra = new JPasswordField();
		pssContra.setBounds(108, 129, 209, 24);
		panel_1.add(pssContra);
		
		JLabel label_3 = new JLabel("Contrase\u00F1a");
		label_3.setForeground(new Color(46, 139, 87));
		label_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		label_3.setBounds(12, 130, 94, 20);
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
							
							JpInicio jp = new JpInicio();
							jp.setBounds(290, 238, 660, 600);
							jp.setVisible(true);
							jp.setLocation(12,12);
							JFRPrincipal.PnlWorkSpace.add(jp);
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
		button.setBounds(223, 166, 94, 27);
		panel_1.add(button);
		
		
		lblError.setForeground(new Color(255, 69, 0));
		lblError.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		lblError.setEditable(false);
		lblError.setBackground(Color.WHITE);
		lblError.setBounds(46, 268, 288, 244);
		panel_1.add(lblError);
		
		
		lblNewLabel.setIcon(new ImageIcon(JFRInicioSesion.class.getResource("/recursos/icons/Icon_ErrorMsg.png")));
		lblNewLabel.setBounds(12, 268, 41, 31);
		panel_1.add(lblNewLabel);
		
		JLabel lblIniciarSesion = new JLabel("Iniciar Sesi\u00F3n");
		lblIniciarSesion.setBackground(new Color(240, 240, 240));
		lblIniciarSesion.setForeground(new Color(60, 179, 113));
		lblIniciarSesion.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblIniciarSesion.setBounds(12, 35, 210, 29);
		panel_1.add(lblIniciarSesion);
		

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
	
	public void volveraInicio() {
		label_2.setIcon(new ImageIcon(JFRInicioSesion.class.getResource("/recursos/icons/btnState1.png")));
		tipoInicio=1;
		lblSingUp.setVisible(false);
		LogIn.setVisible(true);
		panel_1.setVisible(true);
		panel_1.setBounds(12, 24, 334, 445);
		

		WrkSpace.removeAll();
		WrkSpace.setLayout(null);
		WrkSpace.add(panel_1);
		WrkSpace.revalidate();
		WrkSpace.repaint();
	}
	
	public void reportarError(String error) {
		JOptionPane.showMessageDialog(this, error);
	}
}
