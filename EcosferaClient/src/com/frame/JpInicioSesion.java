package com.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import com.session.IniciarSesion;

import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JpInicioSesion extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
	private JPasswordField pssContra;
	private JTextPane lblError = new JTextPane();
	private JLabel label = new JLabel("");
	
	/**
	 * Create the panel.
	 * @throws NamingException 
	 */
	public JpInicioSesion() throws NamingException {

		setBounds(new Rectangle(295, 256, 650, 582));
		setBackground(new Color(255, 255, 255));
		
		JPanel pnlNew = new JPanel();
		pnlNew.setBounds(0, 0, 342, 489);
		pnlNew.setBackground(new Color(255, 255, 255));
		pnlNew.setForeground(new Color(255, 255, 255));
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(12, 84, 67, 20);
		lblUsuario.setForeground(new Color(46, 139, 87));
		lblUsuario.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
		
			public void keyReleased(KeyEvent e) {
				txtUsuario.setText(txtUsuario.getText().toUpperCase());
			}

		});
		txtUsuario.setBounds(108, 83, 209, 24);
		txtUsuario.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtUsuario.setColumns(10);
		
		JButton btnAgregar = new JButton("Aceptar");
		btnAgregar.setBounds(223, 154, 94, 27);
		
		btnAgregar.setBackground(new Color(245, 255, 250));
		btnAgregar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnAgregar.setForeground(new Color(46, 139, 87));
		setLayout(null);
		add(pnlNew);
		pnlNew.setLayout(null);
		pnlNew.add(lblUsuario);
		pnlNew.add(txtUsuario);
		pnlNew.add(btnAgregar);
		
		JLabel lblPass = new JLabel("Contrase\u00F1a");
		lblPass.setForeground(new Color(46, 139, 87));
		lblPass.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblPass.setBounds(12, 118, 94, 20);
		pnlNew.add(lblPass);
		pssContra = new JPasswordField();
		pssContra.setBounds(108, 117, 209, 24);
		pnlNew.add(pssContra);
		lblError.setEditable(false);
		
		
		lblError.setForeground(new Color(255, 69, 0));
		lblError.setBackground(Color.WHITE);
		lblError.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		lblError.setBounds(42, 357, 288, 55);
		pnlNew.add(lblError);
		
		
		label.setIcon(new ImageIcon(JpInicioSesion.class.getResource("/recursos/icons/Icon_ErrorMsg.png")));
		label.setBounds(12, 357, 33, 39);
		label.setVisible(false);
		pnlNew.add(label);
		
		
		
		
		btnAgregar.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if(!controles()) {
						String respuesta ="";
						respuesta = IniciarSesion.iniciar(txtUsuario.getText(), pssContra.getText());
						if(!respuesta.isEmpty()) {
							lblError.setText(respuesta);
							label.setVisible(true);
							
						}else {
							//JFRInicioSesion frm = JFRInicioSesion.getFrame();
							//frm.cerrar();

							JFRPrincipal.main(null);
						}
					}
				} catch (NamingException e1) {
					lblError.setText(e1.getMessage());
					label.setVisible(true);
				}		
			}
		});

	}
	
	
	
	
	
	@SuppressWarnings("deprecation")
	public boolean controles() throws NamingException {
		boolean error = false;
		if(txtUsuario.getText().isEmpty()) {
			error = true;
			lblError.setText( "El campo usuario es obligatorio");
			label.setVisible(true);
		}else if(pssContra.getText().isEmpty()) {
			error = true;
			lblError.setText("El campo contraseña es obligatorio");
			label.setVisible(true);
		}
		return error;
		
	}
	

	
}



