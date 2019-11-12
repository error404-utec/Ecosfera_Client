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
import javax.swing.RowFilter;

import com.entities.Departamento;
import com.entities.Estado;
import com.entities.Perfil;
import com.entities.TipoDocumento;
import com.entities.Usuario;
import com.exceptions.ServiciosException;
import com.services.EstadoBeanRemote;
import com.services.TipoDocumentoBeanRemote;
import com.services.UsuarioBeanRemote;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class JpUsuarios extends JPanel {
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
	private JComboBox<String> cmbEstados = new JComboBox<String>();
	private JTable TablaPerfiles;
	private String modo;
	private Usuario usuario;
	
	
	/**
	 * Create the panel.
	 * @throws NamingException 
	 */
	public JpUsuarios(String modo,Usuario usuario) throws NamingException {
		this.modo = modo;
		this.usuario = usuario;
		cargarCombo();
		cargarComboEstado();
		setBounds(new Rectangle(295, 256, 650, 582));
		setBackground(new Color(255, 255, 255));
		
		JPanel pnlNew = new JPanel();
		pnlNew.setBounds(0, 13, 452, 388);
		pnlNew.setBackground(new Color(255, 255, 255));
		pnlNew.setForeground(new Color(255, 255, 255));
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);
		
		JLabel lblDocumento = new JLabel("Nro. Documento");
		lblDocumento.setBounds(12, 83, 113, 20);
		lblDocumento.setForeground(new Color(46, 139, 87));
		lblDocumento.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtDocumento = new JTextField();
		txtDocumento.setBounds(137, 82, 303, 24);
		txtDocumento.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtDocumento.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Nombre Usuario");
		lblUsuario.setBounds(12, 13, 113, 20);
		lblUsuario.setForeground(new Color(46, 139, 87));
		lblUsuario.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(137, 12, 303, 24);
		txtUsuario.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtUsuario.setColumns(10);
		
		JButton btnAgregar = new JButton("Aceptar");
		btnAgregar.setBounds(347, 351, 94, 27);
		
		btnAgregar.setBackground(new Color(245, 255, 250));
		btnAgregar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnAgregar.setForeground(new Color(46, 139, 87));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(236, 351, 99, 27);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//if (modo.equals("INSERT"))
				//txtUsuario.setText("");
				//txtDocumento.setText("");
				//txtApellido.setText("");
				//txtDireccion.setText("");
				//txtMail.setText("");
				//txtNombre.setText("");
				//cmbTipoDocumento.setSelectedIndex(-1);
			}
		});
		btnCancelar.setForeground(new Color(46, 139, 87));
		btnCancelar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnCancelar.setBackground(new Color(245, 255, 250));
		setLayout(null);
		add(pnlNew);
		pnlNew.setLayout(null);
		pnlNew.add(lblUsuario);
		pnlNew.add(txtUsuario);
		pnlNew.add(btnCancelar);
		pnlNew.add(btnAgregar);
		pnlNew.add(txtDocumento);
		pnlNew.add(lblDocumento);
		
		JLabel lblTipoDocumento = new JLabel("Tipo Documento");
		lblTipoDocumento.setForeground(new Color(46, 139, 87));
		lblTipoDocumento.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblTipoDocumento.setBounds(12, 50, 113, 20);
		pnlNew.add(lblTipoDocumento);
		
		
		cmbTipoDocumento.setMaximumRowCount(99);
		cmbTipoDocumento.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		cmbTipoDocumento.setBackground(Color.WHITE);
		cmbTipoDocumento.setBounds(137, 45, 303, 24);
		pnlNew.add(cmbTipoDocumento);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(new Color(46, 139, 87));
		lblNombre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblNombre.setBounds(12, 116, 113, 20);
		pnlNew.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setForeground(new Color(46, 139, 87));
		lblApellido.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblApellido.setBounds(12, 149, 113, 20);
		pnlNew.add(lblApellido);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setForeground(new Color(46, 139, 87));
		lblDireccin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblDireccin.setBounds(12, 182, 113, 20);
		pnlNew.add(lblDireccin);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setForeground(new Color(46, 139, 87));
		lblMail.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblMail.setBounds(12, 215, 113, 20);
		pnlNew.add(lblMail);
		
		JLabel lblPass = new JLabel("Contrase\u00F1a");
		lblPass.setForeground(new Color(46, 139, 87));
		lblPass.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblPass.setBounds(12, 248, 113, 20);
		pnlNew.add(lblPass);
		
		JLabel lblConfPass = new JLabel("Repetir contrase\u00F1a");
		lblConfPass.setForeground(new Color(46, 139, 87));
		lblConfPass.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblConfPass.setBounds(12, 281, 122, 20);
		pnlNew.add(lblConfPass);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		txtNombre.setBounds(137, 115, 303, 24);
		pnlNew.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtApellido.setColumns(10);
		txtApellido.setBounds(137, 148, 303, 24);
		pnlNew.add(txtApellido);
		
		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(137, 181, 303, 24);
		pnlNew.add(txtDireccion);
		
		txtMail = new JTextField();
		txtMail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtMail.setColumns(10);
		txtMail.setBounds(137, 214, 303, 24);
		pnlNew.add(txtMail);
		pssContra = new JPasswordField();
		pssContra.setBounds(137, 247, 303, 24);
		pnlNew.add(pssContra);
		passRepetirContra = new JPasswordField();
		passRepetirContra.setBounds(137, 280, 303, 24);
		pnlNew.add(passRepetirContra);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setForeground(new Color(46, 139, 87));
		lblEstado.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblEstado.setBounds(12, 314, 122, 20);
		pnlNew.add(lblEstado);
		
		
		cmbEstados.setMaximumRowCount(99);
		cmbEstados.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		cmbEstados.setBackground(Color.WHITE);
		cmbEstados.setBounds(137, 314, 303, 24);
		pnlNew.add(cmbEstados);
		
		JPanel PnlPerfiles = new JPanel();
		PnlPerfiles.setBackground(Color.WHITE);
		PnlPerfiles.setBounds(0, 414, 452, 168);
		add(PnlPerfiles);
		PnlPerfiles.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 428, 108);
		PnlPerfiles.add(scrollPane);
		
		TablaPerfiles = new JTable((TableModel) null);
		TablaPerfiles.setShowVerticalLines(false);
		TablaPerfiles.setSelectionBackground(new Color(144, 238, 144));
		TablaPerfiles.setRowSelectionAllowed(true);
		TablaPerfiles.setForeground(new Color(153, 153, 153));
		TablaPerfiles.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 13));
		TablaPerfiles.setColumnSelectionAllowed(false);
		TablaPerfiles.setBorder(null);
		TablaPerfiles.setBackground(Color.WHITE);
		TablaPerfiles.setAutoscrolls(true);
		scrollPane.setViewportView(TablaPerfiles);
		
		JButton btnAgregafrPerfil = new JButton("Agregar");
		btnAgregafrPerfil.setForeground(new Color(46, 139, 87));
		btnAgregafrPerfil.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnAgregafrPerfil.setBackground(new Color(245, 255, 250));
		btnAgregafrPerfil.setBounds(346, 134, 94, 27);
		PnlPerfiles.add(btnAgregafrPerfil);
		
		JButton BtnEliminar = new JButton("Eliminar");
		BtnEliminar.setForeground(new Color(46, 139, 87));
		BtnEliminar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		BtnEliminar.setBackground(new Color(245, 255, 250));
		BtnEliminar.setBounds(240, 134, 94, 27);
		PnlPerfiles.add(BtnEliminar);
		
		
		
		
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
					usuario.setEstado(obtenerEstado((String)cmbEstados.getSelectedItem()));
					usuario.setMail(txtMail.getText());
					usuario.setTipoDocumento(obtenerTipoDocumento((String)cmbTipoDocumento.getSelectedItem()));
					boolean error = controles(usuario);
					if(!error) {
						crearUsuario(usuario);
					}
				} catch (NamingException e1) {
					e1.printStackTrace();
				}		
			}
		});
		
		txtUsuario.setText(usuario.getNombre());
		txtNombre.setText(usuario.getNombre());
		txtApellido.setText(usuario.getApellido());
		txtDireccion.setText(usuario.getDireccion());
		txtDocumento.setText(usuario.getDocumento());
		txtMail.setText(usuario.getMail());
		//cmbEstados.setSelectedIndex(anIndex);
		TablaPerfiles = cargarPerfiles();
		
		if(modo.equals("UPDATE_USER")) {
			txtUsuario.setEditable(false);
			txtDocumento.setEditable(false);
			txtMail.setEditable(false);
			cmbTipoDocumento.setEditable(false);
			cmbEstados.setEditable(false);
			btnAgregafrPerfil.setEnabled(false);
			BtnEliminar.setEnabled(false);
		}else if(modo.equals("UPDATE_ADMIN")) {
			txtUsuario.setEditable(false);
			txtDocumento.setEditable(false);
			txtMail.setEditable(false);
			cmbTipoDocumento.setEditable(false);
			cmbEstados.setEditable(true);
			btnAgregafrPerfil.setEnabled(true);
			BtnEliminar.setEnabled(true);
			pssContra.setEditable(false);
			passRepetirContra.setEditable(false);
		}else if(modo.equals("INSERT")) {
			txtUsuario.setEditable(true);
			txtDocumento.setEditable(true);
			txtMail.setEditable(true);
			cmbTipoDocumento.setEditable(true);
			cmbEstados.setEditable(true);
			btnAgregafrPerfil.setEnabled(true);
			BtnEliminar.setEnabled(true);
			pssContra.setEditable(true);
			passRepetirContra.setEditable(true);
		}

	}
	
	
	private JTable cargarPerfiles() throws NamingException {
		List<Perfil> lista = usuario.getPerfiles();
		
		
		String[] nombreColumnas = {"ID", "Nombre" };

		Object[][] datos = new Object[lista.size()][2];
		int fila = 0;

		
		for (Perfil c : lista) {
			datos[fila][0] = c.getId();
			datos[fila][1] = c.getNombre();
			fila++;

		}
		
		DefaultTableModel model = new DefaultTableModel(datos, nombreColumnas) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				 return String.class;
			}
		};
		
		
		TablaPerfiles = new JTable(model);
		TablaPerfiles.setRowSelectionAllowed(true);
		TablaPerfiles.setColumnSelectionAllowed(false);
		TablaPerfiles.setBorder(null);
		TablaPerfiles.setBackground(new Color(255, 255, 255));
		TablaPerfiles.setForeground(new Color(153, 153, 153));
		TablaPerfiles.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 13));
		TablaPerfiles.setShowVerticalLines(false);
		TablaPerfiles.setAutoscrolls(true);
		TablaPerfiles.setSize(600, 600);
		Color color = new Color(144,238,144);
		TablaPerfiles.setSelectionBackground(color);
		
		return TablaPerfiles;

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
	
	
	public Estado obtenerEstado(String nombre) throws NamingException {
		EstadoBeanRemote estadoBeanRemote  = (EstadoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/EstadoBean!com.services.EstadoBeanRemote");
		
		return estadoBeanRemote.obtenerPorNombre(nombre);
		
	}
	
	public void cargarComboEstado() throws NamingException {
		EstadoBeanRemote estadoBeanRemote  = (EstadoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/EstadoBean!com.services.EstadoBeanRemote");
		
		List<Estado> col = estadoBeanRemote.obtenerTodos();
		
		for(Estado elemento : col) {
			cmbEstados.addItem(elemento.getNombre());
		}
		
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
			JOptionPane.showMessageDialog(this, "El campo apellido es obligatorio");
		}else if(txtDireccion.getText().isEmpty()) {
			error = true;
			JOptionPane.showMessageDialog(null, "El campo direccion es obligatorio");
		}else if(txtDocumento.getText().isEmpty()) {
			error = true;
			JOptionPane.showMessageDialog(null, "El campo nro. de documento es obligatorio");
		}else if(txtMail.getText().isEmpty()) {
			error = true;
			JOptionPane.showMessageDialog(null, "El campo mail es obligatorio");
		}else if(txtNombre.getText().isEmpty()) {
			error = true;
			JOptionPane.showMessageDialog(null, "El campo nombre es obligatorio");
		}else if(txtUsuario.getText().isEmpty()) {
			error = true;
			JOptionPane.showMessageDialog(null, "El campo usuario es obligatorio");
		}else if(cmbTipoDocumento.getSelectedIndex()==-1) {
			error = true;
			JOptionPane.showMessageDialog(null, "debe seleccionar un tipo de documento");
		}else if(pssContra.getText().isEmpty()) {
			error = true;
			JOptionPane.showMessageDialog(null, "el campo contraseña es obligatorio");
		}else if(passRepetirContra.getText().isEmpty()) {
			error = true;
			JOptionPane.showMessageDialog(null, "el campo repetir contraseña es obligatorio");
		}
		
		if (!error) {
			if (!passRepetirContra.getText().equals(pssContra.getText())) {
				error = true;
				JOptionPane.showMessageDialog(this, "La confirmacion de contraseña no es igual a la contraseña");
			}
		}
		UsuarioBeanRemote usuariodBeanRemote  = (UsuarioBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/UsuarioBean!com.services.UsuarioBeanRemote");
		
		String respuestaBean =usuariodBeanRemote.controlarUnicidad(usuario);
		if (!respuestaBean.equals("")) {
			error = true;
			JOptionPane.showMessageDialog(this,respuestaBean);
		}
		return error;
		
	}
}



