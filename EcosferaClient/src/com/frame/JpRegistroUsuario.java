package com.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.entities.Estado;
import com.entities.TipoDocumento;
import com.entities.Usuario;
import com.exceptions.ServiciosException;
import com.services.EstadoBeanRemote;
import com.services.TipoDocumentoBeanRemote;
import com.services.UsuarioBeanRemote;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JpRegistroUsuario extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtDocumento;
	private JTextField txtUsuario;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDireccion;
	private JTextField txtMail;
	private JPasswordField pssContra;
	private JPasswordField passRepetirContra;
	private JComboBox<String> cmbTipoDocumento = new JComboBox<String>();
	private JTextPane lblError = new JTextPane();
	private JLabel label = new JLabel("");
	
	/**
	 * Create the panel.
	 * @throws NamingException 
	 */
	public JpRegistroUsuario() throws NamingException {
		cargarCombo();
		setBounds(new Rectangle(295, 256, 650, 582));
		setBackground(new Color(255, 255, 255));
		
		JPanel pnlNew = new JPanel();
		pnlNew.setBounds(0, 0, 342, 489);
		pnlNew.setBackground(new Color(255, 255, 255));
		pnlNew.setForeground(new Color(255, 255, 255));
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);
		
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(12, 84, 84, 20);
		lblDocumento.setForeground(new Color(46, 139, 87));
		lblDocumento.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtDocumento = new JTextField();
		txtDocumento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtDocumento.setText(txtDocumento.getText().toUpperCase());
			}
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtDocumento.getText().length()>=30) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		txtDocumento.setBounds(108, 83, 209, 24);
		txtDocumento.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtDocumento.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(12, 14, 67, 20);
		lblUsuario.setForeground(new Color(46, 139, 87));
		lblUsuario.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				txtUsuario.setText(txtUsuario.getText().toUpperCase());
			}
			
			public void keyTyped(KeyEvent e) {
				if(txtUsuario.getText().length()>=30) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		txtUsuario.setBounds(108, 13, 209, 24);
		txtUsuario.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtUsuario.setColumns(10);
		
		JButton btnAgregar = new JButton("Aceptar");
		btnAgregar.setBounds(227, 317, 94, 27);
		
		btnAgregar.setBackground(new Color(245, 255, 250));
		btnAgregar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnAgregar.setForeground(new Color(46, 139, 87));
		setLayout(null);
		add(pnlNew);
		pnlNew.setLayout(null);
		pnlNew.add(lblUsuario);
		pnlNew.add(txtUsuario);
		pnlNew.add(btnAgregar);
		pnlNew.add(txtDocumento);
		pnlNew.add(lblDocumento);
		
		JLabel lblTipoDocumento = new JLabel("T. Documento");
		lblTipoDocumento.setForeground(new Color(46, 139, 87));
		lblTipoDocumento.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblTipoDocumento.setBounds(12, 47, 99, 20);
		pnlNew.add(lblTipoDocumento);
		
		
		cmbTipoDocumento.setMaximumRowCount(99);
		cmbTipoDocumento.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		cmbTipoDocumento.setBackground(Color.WHITE);
		cmbTipoDocumento.setBounds(108, 46, 209, 24);
		pnlNew.add(cmbTipoDocumento);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(new Color(46, 139, 87));
		lblNombre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblNombre.setBounds(12, 117, 84, 20);
		pnlNew.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setForeground(new Color(46, 139, 87));
		lblApellido.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblApellido.setBounds(12, 150, 67, 20);
		pnlNew.add(lblApellido);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setForeground(new Color(46, 139, 87));
		lblDireccin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblDireccin.setBounds(12, 183, 84, 20);
		pnlNew.add(lblDireccin);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setForeground(new Color(46, 139, 87));
		lblMail.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblMail.setBounds(12, 216, 72, 20);
		pnlNew.add(lblMail);
		
		JLabel lblPass = new JLabel("Contrase\u00F1a");
		lblPass.setForeground(new Color(46, 139, 87));
		lblPass.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblPass.setBounds(12, 249, 94, 20);
		pnlNew.add(lblPass);
		
		JLabel lblConfPass = new JLabel("Confirmar");
		lblConfPass.setForeground(new Color(46, 139, 87));
		lblConfPass.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblConfPass.setBounds(12, 282, 84, 20);
		pnlNew.add(lblConfPass);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if (!(Character.isAlphabetic(c) ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SPACE))) {
					getToolkit().beep();
					arg0.consume();
				}
				if(txtNombre.getText().length()>=50) {
					getToolkit().beep();
					arg0.consume();
				}
			}
		});
		txtNombre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		txtNombre.setBounds(108, 116, 209, 24);
		pnlNew.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if (!(Character.isAlphabetic(c) ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SPACE))) {
					getToolkit().beep();
					arg0.consume();
				}
				if(txtApellido.getText().length()>=50) {
					getToolkit().beep();
					arg0.consume();
				}
			}
		});
		txtApellido.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtApellido.setColumns(10);
		txtApellido.setBounds(108, 149, 209, 24);
		pnlNew.add(txtApellido);
		
		txtDireccion = new JTextField();
		txtDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(txtDireccion.getText().length()>=100) {
					getToolkit().beep();
					arg0.consume();
				}
			}
		});
		txtDireccion.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(108, 182, 209, 24);
		pnlNew.add(txtDireccion);
		
		txtMail = new JTextField();
		txtMail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtMail.setText(txtMail.getText().toUpperCase());
			}
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtMail.getText().length()>=30) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		txtMail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtMail.setColumns(10);
		txtMail.setBounds(108, 215, 209, 24);
		pnlNew.add(txtMail);
		pssContra = new JPasswordField();
		pssContra.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if ((c == KeyEvent.VK_SPACE)) {
					getToolkit().beep();
					arg0.consume();
				}
				if(pssContra.getText().length()>=30) {
					getToolkit().beep();
					arg0.consume();
				}
			}
		});
		pssContra.setBounds(108, 248, 209, 24);
		pnlNew.add(pssContra);
		passRepetirContra = new JPasswordField();
		passRepetirContra.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c == KeyEvent.VK_SPACE)) {
					getToolkit().beep();
					e.consume();
				}
				if(passRepetirContra.getText().length()>=30) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		passRepetirContra.setBounds(108, 281, 209, 24);
		pnlNew.add(passRepetirContra);
		lblError.setEditable(false);
		
		
		lblError.setForeground(new Color(255, 69, 0));
		lblError.setBackground(Color.WHITE);
		lblError.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		lblError.setBounds(42, 357, 288, 99);
		pnlNew.add(lblError);
		
		
		label.setIcon(new ImageIcon(JpRegistroUsuario.class.getResource("/recursos/icons/Icon_ErrorMsg.png")));
		label.setBounds(12, 357, 33, 39);
		label.setVisible(false);
		pnlNew.add(label);
		
		
		
		
		btnAgregar.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Usuario usuario = new Usuario();
					usuario.setNombre(txtDocumento.getText());
					usuario.setDireccion(txtDireccion.getText());
					usuario.setApellido(txtApellido.getText());
					usuario.setPasswrd(pssContra.getText());	
					usuario.setDocumento(txtDocumento.getText());
					usuario.setUsuario(txtUsuario.getText());
					usuario.setEstado(obtenerEstado());
					usuario.setMail(txtMail.getText());
					usuario.setTipoDocumento(obtenerTipoDocumento((String)cmbTipoDocumento.getSelectedItem()));
					boolean error = controles(usuario);
					if(!error) {
						crearUsuario(usuario);
						JFRInicioSesion jfr = JFRInicioSesion.getFrame();
						jfr.volveraInicio();
					}
				} catch (NamingException e1) {
					e1.printStackTrace();
				}		
			}
		});

	}
	
	public void crearUsuario(Usuario usuario) throws NamingException {
		UsuarioBeanRemote usuariodBeanRemote  = (UsuarioBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/UsuarioBean!com.services.UsuarioBeanRemote");
		try {
			usuariodBeanRemote.actualizar(usuario);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public void eliminarUsuario(Usuario usuario) throws NamingException {
		UsuarioBeanRemote usuariodBeanRemote  = (UsuarioBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/UsuarioBean!com.services.UsuarioBeanRemote");
		try {
			usuariodBeanRemote.borrar(usuario.getId());
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public void acutalizarUsuario(Usuario usuario) throws NamingException {
		UsuarioBeanRemote usuariodBeanRemote  = (UsuarioBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/UsuarioBean!com.services.UsuarioBeanRemote");
		try {
			usuariodBeanRemote.actualizar(usuario);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
		
	public Usuario obtenerPorID(Long id) throws NamingException {
		UsuarioBeanRemote usuariodBeanRemote  = (UsuarioBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/UsuarioBean!com.services.UsuarioBeanRemote");
		return usuariodBeanRemote.obtenerPorId(id);
	}
	
	public TipoDocumento obtenerTipoDocumento(String nombre) throws NamingException {
		TipoDocumentoBeanRemote tipoDocumentoBeanRemote  = (TipoDocumentoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/TipoDocumentoBean!com.services.TipoDocumentoBeanRemote");
		return tipoDocumentoBeanRemote.obtenerPorNombre(nombre);
	}
	
	
	public Estado obtenerEstado() throws NamingException {
		EstadoBeanRemote estadoBeanRemote  = (EstadoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/EstadoBean!com.services.EstadoBeanRemote");
		
		Estado estado = estadoBeanRemote.obtenerPorNombre("NUEVO");
		return estado;
		
	}
	
	public void cargarCombo() throws NamingException {
		TipoDocumentoBeanRemote tipoDocumentoBeanRemote  = (TipoDocumentoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/TipoDocumentoBean!com.services.TipoDocumentoBeanRemote");
		
		List<TipoDocumento> col = tipoDocumentoBeanRemote.obtenerTodos();
		
		for(TipoDocumento elemento : col) {
			cmbTipoDocumento.addItem(elemento.getNombre());
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public boolean controles(Usuario usuario) throws NamingException {
		boolean error = false;
		if (txtApellido.getText().isEmpty()) {
			error = true;
			lblError.setText("El campo apellido es obligatorio");
		}else if(txtDireccion.getText().isEmpty()) {
			error = true;
			lblError.setText("El campo direccion es obligatorio");
		}else if(txtDocumento.getText().isEmpty()) {
			error = true;
			lblError.setText("El campo nro. de documento es obligatorio");
		}else if(txtMail.getText().isEmpty()) {
			error = true;
			lblError.setText("El campo mail es obligatorio");
		}else if(txtNombre.getText().isEmpty()) {
			error = true;
			lblError.setText("El campo nombre es obligatorio");
		}else if(txtUsuario.getText().isEmpty()) {
			error = true;
			lblError.setText("El campo usuario es obligatorio");
		}else if(cmbTipoDocumento.getSelectedIndex()==-1) {
			error = true;
			lblError.setText("debe seleccionar un tipo de documento");
		}else if(pssContra.getText().isEmpty()) {
			error = true;
			lblError.setText("el campo contraseña es obligatorio");
		}else if(passRepetirContra.getText().isEmpty()) {
			error = true;
			lblError.setText("el campo repetir contraseña es obligatorio");
		}else if(!txtMail.getText().contains("@")) {
			error = true;
			lblError.setText("Formato de mail incorrecto");
		}
		if (!error) {
			if (!passRepetirContra.getText().equals(pssContra.getText())) {
				error = true;
				lblError.setText("La confirmacion de contraseña no es igual a la contraseña");
			}
		}
		UsuarioBeanRemote usuariodBeanRemote  = (UsuarioBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/UsuarioBean!com.services.UsuarioBeanRemote");
		
		
		String respuestaBean =usuariodBeanRemote.controlarUnicidad(usuario);
		if (!respuestaBean.isEmpty()) {
			error = true;
			lblError.setText(respuestaBean);
		}
		if(error) {
			label.setVisible(true);
		}
		return error;
		
	}
	
	
}



