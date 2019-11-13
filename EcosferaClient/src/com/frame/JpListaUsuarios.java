package com.frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.entities.Usuario;
import com.entities.Zona;
import com.exceptions.ServiciosException;
import com.framework.EcosferaScrollBar;
import com.services.UsuarioBeanRemote;
import com.services.ZonaBeanRemote;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class JpListaUsuarios extends JPanel {

	/**
	 * 
	 */ 
	private static final long serialVersionUID = 1L;



	private JTable tablaUsuario;
	private Usuario usuarioActualizar = null;
	private JTextField txtfiltro;
	

	/**
	 * Create the panel.
	 * @throws NamingException 
	 */
	public JpListaUsuarios() throws NamingException {
		JFRPrincipal.setlblTitulopanel("Mantenimiento Zonas");

		setBounds(new Rectangle(295, 256, 662, 609));
		setBackground(new Color(255, 255, 255));
		
		JPanel pnltable = new JPanel();
		pnltable.setBounds(110, 13, 452, 456);
		pnltable.setBackground(new Color(255, 255, 255));
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);
		
		JPanel pnlOptions = new JPanel();
		pnlOptions.setBounds(110, 482, 452, 55);
		pnlOptions.setBackground(new Color(255, 255, 255));
		
		JScrollPane scroolTablaUsuarios = new JScrollPane();
		scroolTablaUsuarios.setBounds(12, 78, 428, 365);
		/*
		scroolTablaZonas.setLayout(new ScrollPaneLayout() {

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
		scroolTablaZonas.getVerticalScrollBar().setUI(new EcosferaScrollBar());
		
		scroolTablaZonas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
			}
		});*/
		
		
		txtfiltro = new JTextField();
		txtfiltro.setBounds(78, 17, 362, 24);
		txtfiltro.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				filtrar();
			}
		});
		txtfiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtfiltro.setColumns(10);
		
		JLabel lblfiltro = new JLabel("Filtrar");
		lblfiltro.setBounds(12, 18, 109, 20);
		lblfiltro.setForeground(new Color(46, 139, 87));
		lblfiltro.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		
		

		this.tablaUsuario = this.cargarPermisos();
		scroolTablaUsuarios.setViewportView(tablaUsuario);
		
		JButton btnModificat = new JButton("Modificar");
		btnModificat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				usuarioActualizar = null;
				if (tablaUsuario.getSelectedRow() > -1) {
					Long id = (Long) tablaUsuario.getValueAt(tablaUsuario.getSelectedRow(), 0);
					try {
						usuarioActualizar = obtenerPorID(id);
						JpUsuarios jp = new JpUsuarios("UPDATE_ADMIN", usuarioActualizar);
						jp.setBounds(290, 238, 660, 600);
						jp.setVisible(true);
						jp.setLocation(12,12);
						JFRPrincipal.getIntance();
						JFRPrincipal.PnlWorkSpace.removeAll();
						JFRPrincipal.PnlWorkSpace.add(jp);
						JFRPrincipal.PnlWorkSpace.revalidate();
						JFRPrincipal.PnlWorkSpace.repaint();
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}else {
					reportarError("Debe seleccionar un Usuario");
				}
			}
		});
		btnModificat.setBackground(new Color(245, 255, 250));
		btnModificat.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnModificat.setForeground(new Color(46, 139, 87));
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Usuario usuarioEliminar = null;
				if (tablaUsuario.getSelectedRow() > -1) {
					Long id = (Long) tablaUsuario.getValueAt(tablaUsuario.getSelectedRow(), 0);
					try {
						usuarioEliminar = obtenerPorID(id);
						
						//if(!controles_preDelete(usuarioEliminar)) {
							eliminarUsuario(usuarioEliminar);;
							tablaUsuario.setVisible(false);
							tablaUsuario = cargarPermisos();
							scroolTablaUsuarios.setViewportView(tablaUsuario);
							tablaUsuario.setVisible(true);
							filtrar();
//						}
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}else {
					reportarError("Debe seleccionar un Usuario");
				}
			}
		});
		btnEliminar.setForeground(new Color(46, 139, 87));
		btnEliminar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnEliminar.setBackground(new Color(245, 255, 250));
		
		JButton BtnNuevo = new JButton("Agregar");
		BtnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		BtnNuevo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JpUsuarios jp;
				try {
					jp = new JpUsuarios("INSERT", null);
					jp.setBounds(290, 238, 660, 600);
					jp.setVisible(true);
					jp.setLocation(12,12);
					JFRPrincipal.getIntance();
					JFRPrincipal.PnlWorkSpace.removeAll();
					JFRPrincipal.PnlWorkSpace.add(jp);
					JFRPrincipal.PnlWorkSpace.revalidate();
					JFRPrincipal.PnlWorkSpace.repaint();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				
			}
		});
		BtnNuevo.setForeground(new Color(46, 139, 87));
		BtnNuevo.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		BtnNuevo.setBackground(new Color(245, 255, 250));
		GroupLayout gl_pnlOptions = new GroupLayout(pnlOptions);
		gl_pnlOptions.setHorizontalGroup(
			gl_pnlOptions.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlOptions.createSequentialGroup()
					.addContainerGap(144, Short.MAX_VALUE)
					.addComponent(BtnNuevo, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnModificat)
					.addGap(12))
		);
		gl_pnlOptions.setVerticalGroup(
			gl_pnlOptions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlOptions.createSequentialGroup()
					.addGap(13)
					.addGroup(gl_pnlOptions.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlOptions.createSequentialGroup()
							.addGroup(gl_pnlOptions.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(BtnNuevo, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
						.addGroup(gl_pnlOptions.createSequentialGroup()
							.addComponent(btnModificat, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(13))))
		);
		pnlOptions.setLayout(gl_pnlOptions);
		setLayout(null);
		add(pnltable);
		pnltable.setLayout(null);
		pnltable.add(scroolTablaUsuarios);
		pnltable.add(txtfiltro);
		pnltable.add(lblfiltro);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 57, 144, 24);
		pnltable.add(panel);
		
		JLabel lblId = new JLabel("ID");
		panel.add(lblId);
		lblId.setForeground(new Color(46, 139, 87));
		lblId.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(153, 57, 144, 24);
		pnltable.add(panel_1);
		
		JLabel lblUsuario = new JLabel("Usuario");
		panel_1.add(lblUsuario);
		lblUsuario.setForeground(new Color(46, 139, 87));
		lblUsuario.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(296, 57, 144, 24);
		pnltable.add(panel_2);
		
		JLabel lblMail = new JLabel("Mail");
		panel_2.add(lblMail);
		lblMail.setForeground(new Color(46, 139, 87));
		lblMail.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		add(pnlOptions);

	}
	
	
	
	public void eliminarUsuario(Usuario usuario) throws NamingException {
		UsuarioBeanRemote usuarioBeanRemote  = (UsuarioBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/UsuarioBean!com.services.UsuarioBeanRemote");
		try {
			usuarioBeanRemote.borrar(usuario.getId());
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	private JTable cargarPermisos() throws NamingException {
		
		List<Usuario> lista = listarUsuario();

		String[] nombreColumnas = {"ID", "Usuario", "Mail" };

		Object[][] datos = new Object[lista.size()][3];
		int fila = 0;

		
		for (Usuario c : lista) {
			datos[fila][0] = c.getId();
			datos[fila][1] = c.getUsuario();
			datos[fila][2] = c.getMail();
			fila++;

		}
		
		DefaultTableModel model = new DefaultTableModel(datos, nombreColumnas) {
			
			/**
			 * 
			 */
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
		
		
		tablaUsuario = new JTable(model);
		tablaUsuario.setRowSelectionAllowed(true);
		tablaUsuario.setColumnSelectionAllowed(false);
		tablaUsuario.setBorder(null);
		tablaUsuario.setBackground(new Color(255, 255, 255));
		tablaUsuario.setForeground(new Color(153, 153, 153));
		tablaUsuario.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 13));
		tablaUsuario.setShowVerticalLines(false);
		tablaUsuario.setAutoscrolls(true);
		tablaUsuario.setSize(600, 600);
		Color color = new Color(144,238,144);
		tablaUsuario.setSelectionBackground(color);
		
		return tablaUsuario;

	}
	
	private void filtrar() {
		TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.tablaUsuario.getModel());
		filtro.setRowFilter(RowFilter.regexFilter(this.txtfiltro.getText(), 1));
		this.tablaUsuario.setRowSorter(filtro);

	}
	
	public List<Usuario> listarUsuario() throws NamingException {
		UsuarioBeanRemote usuarioBeanRemote  = (UsuarioBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/UsuarioBean!com.services.UsuarioBeanRemote");
		return usuarioBeanRemote.obtenerTodos();
	}
	
	public Usuario obtenerPorID(Long id) throws NamingException {
		UsuarioBeanRemote usuarioBeanRemote  = (UsuarioBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/UsuarioBean!com.services.UsuarioBeanRemote");
		return usuarioBeanRemote.obtenerPorId(id);
	}
	
	
	public boolean controles_preCreate() {
		boolean error = false;
		
		return error;	
	}
	
	
	public boolean controles_preDelete(Zona zona) throws NamingException {
		boolean error = false;
		String mensajeError = "";
		ZonaBeanRemote zonaBeanRemote  = (ZonaBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/ZonaBean!com.services.ZonaBeanRemote");
		mensajeError = zonaBeanRemote.controles_preDelete(zona);
		if (!mensajeError.isEmpty()) {
			error = true;
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se puede eliminar la zona", JOptionPane.ERROR_MESSAGE);}
		return error;		
	}
	
	public void reportarError(String error) {
		JOptionPane.showMessageDialog(this, error);
	}
}



