package com.frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;

import com.entities.Estado;
import com.entities.Perfil;
import com.entities.TipoDocumento;
import com.entities.Usuario;
import com.exceptions.ServiciosException;
import com.framework.EcosferaScrollBar;
import com.services.EstadoBeanRemote;
import com.services.PerfilesBeanRemote;
import com.services.TipoDocumentoBeanRemote;
import com.services.UsuarioBeanRemote;
import com.session.Sesion;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	private static Usuario usuariost;
	
	
	/**
	 * Create the panel.
	 * @throws NamingException 
	 */
	public JpUsuarios(String modo,Usuario usuario) throws NamingException {
		this.modo = modo;
		this.usuariost = usuario;
		cargarCombo();
		cargarComboEstado();
		setBounds(new Rectangle(295, 256, 650, 582));
		setBackground(new Color(255, 255, 255));
		
		JPanel pnlNew = new JPanel();
		pnlNew.setBounds(110, 0, 452, 401);
		pnlNew.setBackground(new Color(255, 255, 255));
		pnlNew.setForeground(new Color(255, 255, 255));
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);
		
		
		JPanel PnlVolver = new JPanel();
		PnlVolver.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		PnlVolver.setBackground(Color.WHITE);
		PnlVolver.setBounds(0, 255, 51, 55);
		add(PnlVolver);
		PnlVolver.setLayout(null);
		
		JLabel label_3 = new JLabel("");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JpListaUsuarios jp;
				try {
					jp = new JpListaUsuarios();
					jp.setBounds(290, 238, 660, 600);
					jp.setVisible(true);
					jp.setLocation(12,12);
					JFRPrincipal.getIntance();
					JFRPrincipal.PnlWorkSpace.removeAll();
					JFRPrincipal.PnlWorkSpace.add(jp);
					JFRPrincipal.PnlWorkSpace.revalidate();
					JFRPrincipal.PnlWorkSpace.repaint();
					JFRPrincipal.LblNavegacion.setText("Inicio"+ " - " + "Usuarios");
				} catch (NamingException e) {
					reportarError(e.getMessage());
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				PnlVolver.setBackground(new Color(46,139,87));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				PnlVolver.setBackground(new Color(240,240,240));
			}
		});
		label_3.setIcon(new ImageIcon(jpDep_Zona.class.getResource("/recursos/icons/go_back.png")));
		label_3.setBounds(0, 0, 51, 55);
		PnlVolver.add(label_3);
		
		JLabel lblDocumento = new JLabel("Nro. Documento");
		lblDocumento.setBounds(12, 83, 113, 20);
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
		txtDocumento.setBounds(137, 82, 303, 24);
		txtDocumento.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtDocumento.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Nombre Usuario");
		lblUsuario.setBounds(12, 13, 113, 20);
		lblUsuario.setForeground(new Color(46, 139, 87));
		lblUsuario.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtUsuario.setText(txtUsuario.getText().toUpperCase());
			}
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtUsuario.getText().length()>=30) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
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
				JpListaUsuarios jp;
				try {
					jp = new JpListaUsuarios();
				
					jp.setBounds(290, 238, 660, 600);
					jp.setVisible(true);
					jp.setLocation(12,12);
					JFRPrincipal.getIntance();
					JFRPrincipal.PnlWorkSpace.removeAll();
					JFRPrincipal.PnlWorkSpace.add(jp);
					JFRPrincipal.PnlWorkSpace.revalidate();
					JFRPrincipal.PnlWorkSpace.repaint();
				} catch (NamingException e) {
					reportarError(e.getMessage());
				}
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
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
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
		txtNombre.setBounds(137, 115, 303, 24);
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
		txtApellido.setBounds(137, 148, 303, 24);
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
		txtDireccion.setBounds(137, 181, 303, 24);
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
		txtMail.setBounds(137, 214, 303, 24);
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
		pssContra.setBounds(137, 247, 303, 24);
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
		PnlPerfiles.setBounds(110, 400, 452, 182);
		add(PnlPerfiles);
		PnlPerfiles.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 55, 428, 87);
		PnlPerfiles.add(scrollPane);
		
		scrollPane.setLayout(new ScrollPaneLayout() {

			private static final long serialVersionUID = 1L;

				@Override
				  public void layoutContainer(Container parent) {
				    JScrollPane scrollPane = (JScrollPane) parent;
				
				    Rectangle availR = scrollPane.getBounds();
				    availR.x = availR.y = 0;
				
				    Insets parentInsets = parent.getInsets();
				    availR.x = parentInsets.left;
				    availR.y = parentInsets.top;
				    availR.width -= parentInsets.left + parentInsets.right + 10;
				    availR.height -= parentInsets.top + parentInsets.bottom;
				
				    Rectangle vsbR = new Rectangle();
				    vsbR.width = 12;
				    vsbR.height = availR.height;
				    vsbR.x = availR.x + availR.width - vsbR.width;
				    vsbR.y = availR.y;
				
				    vsbR.x = vsbR.x + 10;
				    if (viewport != null) {
				      viewport.setBounds(availR);
				    }
				    if (vsb != null) {
				      vsb.setVisible(true);
				      vsb.setBounds(vsbR);
				    }
				  }
				});
		scrollPane.getVerticalScrollBar().setUI(new EcosferaScrollBar());
		
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
		if (modo.equals("UPDATE_ADMIN")) {
			TablaPerfiles = cargarPerfiles();
		}
		scrollPane.setViewportView(TablaPerfiles);
		
		JButton btnAgregafrPerfil = new JButton("Mantenimiento");
		btnAgregafrPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					JpUsuarios_Perfiles jp;
					jp = new JpUsuarios_Perfiles(usuario,modo);
					jp.setBounds(290, 238, 660, 600);
					jp.setVisible(true);
					jp.setLocation(12,12);
					JFRPrincipal.getIntance();
					JFRPrincipal.PnlWorkSpace.removeAll();
					JFRPrincipal.PnlWorkSpace.add(jp);
					JFRPrincipal.PnlWorkSpace.revalidate();
					JFRPrincipal.PnlWorkSpace.repaint();
					JFRPrincipal.LblNavegacion.setText("Inicio"+ " - " + "Perfiles");
				} catch (NamingException e) {
					reportarError(e.getMessage());
				}
			}
		});
		btnAgregafrPerfil.setForeground(new Color(46, 139, 87));
		btnAgregafrPerfil.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnAgregafrPerfil.setBackground(new Color(245, 255, 250));
		btnAgregafrPerfil.setBounds(281, 155, 159, 27);
		PnlPerfiles.add(btnAgregafrPerfil);
		
		JLabel lblPerfiles = new JLabel("Perfiles");
		lblPerfiles.setForeground(new Color(46, 139, 87));
		lblPerfiles.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblPerfiles.setBounds(12, 1, 122, 20);
		PnlPerfiles.add(lblPerfiles);
		
		JPanel panel = new JPanel();
		panel.setBounds(224, 33, 216, 24);
		PnlPerfiles.add(panel);
		
		JLabel label = new JLabel("Nombre");
		label.setForeground(new Color(46, 139, 87));
		label.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 33, 216, 24);
		PnlPerfiles.add(panel_1);
		
		JLabel label_1 = new JLabel("ID");
		label_1.setForeground(new Color(46, 139, 87));
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		panel_1.add(label_1);
		
		
		
		
		btnAgregar.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Usuario usuario1 = new Usuario();
					usuario1.setNombre(txtNombre.getText());
					usuario1.setDireccion(txtDireccion.getText());
					usuario1.setApellido(txtApellido.getText());
					usuario1.setPasswrd(pssContra.getText());	
					usuario1.setDocumento(txtDocumento.getText());
					usuario1.setUsuario(txtUsuario.getText());
					usuario1.setEstado(obtenerEstado((String)cmbEstados.getSelectedItem()));
					usuario1.setMail(txtMail.getText());
					usuario1.setTipoDocumento(obtenerTipoDocumento((String)cmbTipoDocumento.getSelectedItem()));
					if (!modo.equals("INSERT")) {
						usuario1.setId(usuariost.getId());
					}
					boolean error = controles(usuario1);
					if(!error) {
						if (modo.equals("UPDATE_ADMIN")) {
							acutalizarUsuario(usuario1);
							JpListaUsuarios jp = new JpListaUsuarios();
							jp.setBounds(290, 238, 660, 600);
							jp.setVisible(true);
							jp.setLocation(12,12);
							JFRPrincipal.getIntance();
							JFRPrincipal.PnlWorkSpace.removeAll();
							JFRPrincipal.PnlWorkSpace.add(jp);
							JFRPrincipal.PnlWorkSpace.revalidate();
							JFRPrincipal.PnlWorkSpace.repaint();
						}else if(modo.equals("INSERT")) {
							Perfil perfil1 = obtenerPerfilPorID((long) 2);
							usuario1.asginarPerfil(perfil1);
							crearUsuario(usuario1);
							JpListaUsuarios jp = new JpListaUsuarios();
							jp.setBounds(290, 238, 660, 600);
							jp.setVisible(true);
							jp.setLocation(12,12);
							JFRPrincipal.getIntance();
							JFRPrincipal.PnlWorkSpace.removeAll();
							JFRPrincipal.PnlWorkSpace.add(jp);
							JFRPrincipal.PnlWorkSpace.revalidate();
							JFRPrincipal.PnlWorkSpace.repaint();
						}else {
							acutalizarUsuario(usuario1);
							JpListaUsuarios jp = new JpListaUsuarios();
							jp.setBounds(290, 238, 660, 600);
							jp.setVisible(true);
							jp.setLocation(12,12);
							JFRPrincipal.getIntance();
							JFRPrincipal.PnlWorkSpace.removeAll();
							JFRPrincipal.PnlWorkSpace.add(jp);
							JFRPrincipal.PnlWorkSpace.revalidate();
							JFRPrincipal.PnlWorkSpace.repaint();
						}
					}
				} catch (NamingException e1) {
					reportarError(e1.getMessage());
				}		
			}
		});
		
		
		
		if(modo.equals("UPDATE_USER")) {
			txtUsuario.setEditable(false);
			txtDocumento.setEditable(false);
			txtMail.setEditable(false);
			cmbTipoDocumento.setEnabled(false);
			cmbEstados.setEnabled(false);
			btnAgregafrPerfil.setEnabled(false);
			txtUsuario.setText(usuario.getUsuario());
			txtNombre.setText(usuario.getNombre());
			txtApellido.setText(usuario.getApellido());
			txtDireccion.setText(usuario.getDireccion());
			txtDocumento.setText(usuario.getDocumento());
			txtMail.setText(usuario.getMail());
			cmbEstados.setSelectedItem(usuario.getEstado().getNombre());
			cmbTipoDocumento.setSelectedItem(usuario.getTipoDocumento().getNombre());
			TablaPerfiles = cargarPerfiles();
			pssContra.setText(usuario.getPasswrd());
			passRepetirContra.setText(usuario.getPasswrd());
			btnAgregafrPerfil.setEnabled(false);
			btnAgregafrPerfil.setVisible(false);
		}else if(modo.equals("UPDATE_ADMIN")) {
			txtUsuario.setEditable(false);
			txtDocumento.setEditable(false);
			txtMail.setEditable(false);
			cmbTipoDocumento.setEnabled(false);;
			btnAgregafrPerfil.setEnabled(true);
			pssContra.setEditable(false);
			txtUsuario.setText(usuario.getUsuario());
			txtNombre.setText(usuario.getNombre());
			txtApellido.setText(usuario.getApellido());
			txtDireccion.setText(usuario.getDireccion());
			txtDocumento.setText(usuario.getDocumento());
			txtMail.setText(usuario.getMail());
			pssContra.setText(usuario.getPasswrd());
			passRepetirContra.setText(usuario.getPasswrd());
			cmbEstados.setSelectedItem(usuario.getEstado().getNombre());
			cmbTipoDocumento.setSelectedItem(usuario.getTipoDocumento().getNombre());
			TablaPerfiles = cargarPerfiles();
			passRepetirContra.setEditable(false);
		}else if(modo.equals("INSERT")) {
			txtUsuario.setEditable(true);
			txtDocumento.setEditable(true);
			txtMail.setEditable(true);
			cmbTipoDocumento.setEnabled(true);
			cmbEstados.setEnabled(true);
			btnAgregafrPerfil.setEnabled(true);
			pssContra.setEditable(true);
			passRepetirContra.setEditable(true);
		}

	}
	
	
	private JTable cargarPerfiles() throws NamingException {
		List<Perfil> lista = usuariost.getPerfiles();
		
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
			reportarError(e.getMessage());
		}
		
	}
	
	public void eliminarUsuario(Usuario usuario) throws NamingException {
		UsuarioBeanRemote usuariodBeanRemote  = (UsuarioBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/UsuarioBean!com.services.UsuarioBeanRemote");
		try {
			usuariodBeanRemote.borrar(usuario.getId());
		} catch (ServiciosException e) {
			reportarError(e.getMessage());
		}
		
	}
	
	public void acutalizarUsuario(Usuario usuario) throws NamingException {
		UsuarioBeanRemote usuariodBeanRemote  = (UsuarioBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/UsuarioBean!com.services.UsuarioBeanRemote");
		try {
			usuariodBeanRemote.actualizar(usuario);
		} catch (ServiciosException e) {
			reportarError(e.getMessage());
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
	
	public Perfil obtenerPerfilPorID(Long id) throws NamingException {
		PerfilesBeanRemote perfilesBeanRemote  = (PerfilesBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PerfilesBean!com.services.PerfilesBeanRemote");
		return perfilesBeanRemote.obtenerPorID(id);
	}
	
	@SuppressWarnings("deprecation")
	public boolean controles(Usuario usuario) throws NamingException {
		boolean error = false;
		if (txtApellido.getText().isEmpty()) {
			error = true;
			JOptionPane.showMessageDialog(this, "El campo apellido es obligatorio");
		}else if(txtDireccion.getText().isEmpty()) {
			error = true;
			JOptionPane.showMessageDialog(this, "El campo direccion es obligatorio");
		}else if(txtDocumento.getText().isEmpty()) {
			error = true;
			JOptionPane.showMessageDialog(this, "El campo nro. de documento es obligatorio");
		}else if(txtMail.getText().isEmpty()) {
			error = true;
			JOptionPane.showMessageDialog(this, "El campo mail es obligatorio");
		}else if(txtNombre.getText().isEmpty()) {
			error = true;
			JOptionPane.showMessageDialog(this, "El campo nombre es obligatorio");
		}else if(txtUsuario.getText().isEmpty()) {
			error = true;
			JOptionPane.showMessageDialog(this, "El campo usuario es obligatorio");
		}else if(cmbTipoDocumento.getSelectedIndex()==-1) {
			error = true;
			JOptionPane.showMessageDialog(this, "debe seleccionar un tipo de documento");
		}else if(pssContra.getText().isEmpty()) {
			error = true;
			JOptionPane.showMessageDialog(this, "el campo contraseña es obligatorio");
		}else if(passRepetirContra.getText().isEmpty()) {
			error = true;
			JOptionPane.showMessageDialog(this, "el campo repetir contraseña es obligatorio");
		}else if(!txtMail.getText().contains("@")) {
			error = true;
			JOptionPane.showMessageDialog(this, "Formato de mail incorrecto");
		}
		if (!error) {
			if (!passRepetirContra.getText().equals(pssContra.getText())) {
				error = true;
				JOptionPane.showMessageDialog(this, "La confirmacion de contraseña no es igual a la contraseña");
			}
		}
		UsuarioBeanRemote usuariodBeanRemote  = (UsuarioBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/UsuarioBean!com.services.UsuarioBeanRemote");
		if(modo.equals("INSERT")) {
			String respuestaBean =usuariodBeanRemote.controlarUnicidad(usuario);
			if (!respuestaBean.isEmpty()) {
				error = true;
				JOptionPane.showMessageDialog(this,respuestaBean);
			}
		}else {
			
			String respuestaBean =usuariodBeanRemote.controles_preModify(usuario);	
			if (!respuestaBean.isEmpty()) {
				error = true;
				JOptionPane.showMessageDialog(this,respuestaBean);
			}
		}
		return error;
		
	}
	
	public boolean controles_preModify(Usuario usuario) throws NamingException {
		boolean error = false;
		
		UsuarioBeanRemote usuariodBeanRemote  = (UsuarioBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/UsuarioBean!com.services.UsuarioBeanRemote");
		String respuestaBean =usuariodBeanRemote.controles_preModify(usuario);	
		if (!respuestaBean.isEmpty()) {
			error = true;
			JOptionPane.showMessageDialog(this,respuestaBean);
		}	
		return error;
	}
	
	public void reportarError(String error) {
		JOptionPane.showMessageDialog(this, error);
	}
}



