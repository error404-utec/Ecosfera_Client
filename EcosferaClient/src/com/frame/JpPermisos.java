package com.frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
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

import com.entities.Departamento;
import com.entities.Localidad;
import com.entities.Permiso;
import com.exceptions.ServiciosException;
import com.framework.EcosferaScrollBar;
import com.services.DepartamentoBeanRemote;
import com.services.PerfilesBeanRemote;
import com.services.PermisoBeanRemote;
import java.awt.Rectangle;


public class JpPermisos extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtDesripcion;
	private JTextField txtFuncionalidad;


	private JTable tablaPermisos;
	private Permiso permisoActualizar = null;
	private JTextField txtfiltro;
	

	/**
	 * Create the panel.
	 * @throws NamingException 
	 */
	public JpPermisos() throws NamingException {
		JFRPrincipal.setlblTitulopanel("Mantenimiento de Permisos");
		setBounds(new Rectangle(295, 256, 650, 582));
		setBackground(new Color(255, 255, 255));
		
		JPanel pnlNew = new JPanel();
		pnlNew.setBounds(110, 0, 452, 165);
		pnlNew.setBackground(new Color(255, 255, 255));
		pnlNew.setForeground(new Color(255, 255, 255));
		
		JPanel pnltable = new JPanel();
		pnltable.setBounds(110, 178, 452, 291);
		pnltable.setBackground(new Color(255, 255, 255));
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 54, 144, 24);
		pnltable.add(panel);
		
		JLabel lblId = new JLabel("ID");
		panel.add(lblId);
		lblId.setForeground(new Color(46, 139, 87));
		lblId.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(153, 54, 144, 24);
		pnltable.add(panel_1);
		
		JLabel label_1 = new JLabel("Funcionalidad");
		label_1.setForeground(new Color(46, 139, 87));
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		panel_1.add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(296, 54, 144, 24);
		pnltable.add(panel_2);
		
		JLabel label_2 = new JLabel("Descripción");
		label_2.setForeground(new Color(46, 139, 87));
		label_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		panel_2.add(label_2);
		
		
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);
		
		JPanel pnlOptions = new JPanel();
		pnlOptions.setBounds(110, 482, 452, 55);
		pnlOptions.setBackground(new Color(255, 255, 255));
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setBounds(12, 89, 94, 20);
		lblDescripcion.setForeground(new Color(46, 139, 87));
		lblDescripcion.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtDesripcion = new JTextField();
		txtDesripcion.setBounds(118, 88, 322, 24);
		txtDesripcion.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtDesripcion.setColumns(10);
		
		JLabel lblFuncionalidad = new JLabel("Funcionalidad");
		lblFuncionalidad.setBounds(12, 52, 94, 20);
		lblFuncionalidad.setForeground(new Color(46, 139, 87));
		lblFuncionalidad.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtFuncionalidad = new JTextField();
		txtFuncionalidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				if(txtFuncionalidad.getText().length()>=50) {
					getToolkit().beep();
					arg0.consume();
				}
			}
			
			
			
		});
		txtFuncionalidad.setBounds(118, 51, 322, 24);
		txtFuncionalidad.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtFuncionalidad.setColumns(10);
		
		JButton btnAgregar = new JButton("Aceptar");
		btnAgregar.setBounds(240, 125, 96, 27);
		
		btnAgregar.setBackground(new Color(245, 255, 250));
		btnAgregar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnAgregar.setForeground(new Color(46, 139, 87));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(344, 125, 96, 27);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtFuncionalidad.setText("");
				txtDesripcion.setText("");
				permisoActualizar = null;
				txtFuncionalidad.setEditable(true);
			}
		});
		btnCancelar.setForeground(new Color(46, 139, 87));
		btnCancelar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnCancelar.setBackground(new Color(245, 255, 250));
		
		JScrollPane scroolTablaPermisos = new JScrollPane();
		scroolTablaPermisos.setBounds(12, 76, 428, 176);
		
		
		scroolTablaPermisos.setLayout(new ScrollPaneLayout() {

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
		scroolTablaPermisos.getVerticalScrollBar().setUI(new EcosferaScrollBar());
		scroolTablaPermisos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
			}
		});
		
		txtfiltro = new JTextField();
		txtfiltro.setBounds(78, 17, 362, 24);
		txtfiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtrar();
			}
		});
		txtfiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtfiltro.setColumns(10);
		
		JLabel lblfiltro = new JLabel("Filtro");
		lblfiltro.setBounds(12, 18, 109, 20);
		lblfiltro.setForeground(new Color(46, 139, 87));
		lblfiltro.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		
		

		this.tablaPermisos = this.cargarPermisos();
		scroolTablaPermisos.setViewportView(tablaPermisos);
		
		JButton btnModificat = new JButton("Modificar");
		btnModificat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				permisoActualizar = null;
				if (tablaPermisos.getSelectedRow() > -1) {
					Long id = (Long) tablaPermisos.getValueAt(tablaPermisos.getSelectedRow(), 0);
					try {
						permisoActualizar = obtenerPorID(id);
						txtDesripcion.setText(permisoActualizar.getDescripcion());
						txtFuncionalidad.setText(permisoActualizar.getFuncionalidad());
						txtFuncionalidad.setEditable(false);
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}else {
					reportarError("Debe seleccionar un permiso.");
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
				Permiso permEliminar = null;
				if (tablaPermisos.getSelectedRow() > -1) {
					Long id = (Long) tablaPermisos.getValueAt(tablaPermisos.getSelectedRow(), 0);
					try {
						permEliminar = obtenerPorID(id);
						if(!controles_preDelete(permEliminar)) {
							if(solicitarConfirmaciones(permEliminar)) {
								eliminarPermiso(permEliminar);;
								tablaPermisos.setVisible(false);
								tablaPermisos = cargarPermisos();
								scroolTablaPermisos.setViewportView(tablaPermisos);
								tablaPermisos.setVisible(true);
								filtrar();
							}
						}
					} catch (NamingException e) {
						e.printStackTrace();
						reportarError(e.getMessage());
					}
				}else {
					reportarError("Debe seleccionar un permiso.");
				}
					
			}
		});
		btnEliminar.setForeground(new Color(46, 139, 87));
		btnEliminar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnEliminar.setBackground(new Color(245, 255, 250));
		GroupLayout gl_pnlOptions = new GroupLayout(pnlOptions);
		gl_pnlOptions.setHorizontalGroup(
			gl_pnlOptions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlOptions.createSequentialGroup()
					.addContainerGap(279, Short.MAX_VALUE)
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
							.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_pnlOptions.createSequentialGroup()
							.addComponent(btnModificat, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(13))))
		);
		pnlOptions.setLayout(gl_pnlOptions);
		setLayout(null);
		add(pnlNew);
		pnlNew.setLayout(null);
		pnlNew.add(lblFuncionalidad);
		pnlNew.add(txtFuncionalidad);
		pnlNew.add(txtDesripcion);
		pnlNew.add(lblDescripcion);
		pnlNew.add(btnAgregar);
		pnlNew.add(btnCancelar);
		add(pnltable);
		pnltable.setLayout(null);
		pnltable.add(panel);
		pnltable.add(panel_1);
		pnltable.add(panel_2);
		pnltable.add(scroolTablaPermisos);
		pnltable.add(txtfiltro);
		pnltable.add(lblfiltro);
		add(pnlOptions);
		
		
		
		
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!controles_preCreate()) {
					if (permisoActualizar!=null) {
						try {
							permisoActualizar.setFuncionalidad(txtFuncionalidad.getText());
							permisoActualizar.setDescripcion(txtDesripcion.getText());
							crearoModificarPermiso(permisoActualizar);
							tablaPermisos.setVisible(false);
							tablaPermisos = cargarPermisos();
							scroolTablaPermisos.setViewportView(tablaPermisos);
							tablaPermisos.setVisible(true);
							txtDesripcion.setText("");
							txtFuncionalidad.setText("");
	
							permisoActualizar = null;
							txtFuncionalidad.setEditable(true);
							filtrar();
						} catch (NamingException e1) {
							e1.printStackTrace();
						}
					}else {
						Permiso permiso = new Permiso();
						permiso.setDescripcion(txtDesripcion.getText());
						permiso.setFuncionalidad(txtFuncionalidad.getText());			
						try {
							if(!controles_postCreate(permiso)) {
								crearPermiso(permiso);
								tablaPermisos.setVisible(false);
								tablaPermisos = cargarPermisos();
								scroolTablaPermisos.setViewportView(tablaPermisos);
								tablaPermisos.setVisible(true);
								txtDesripcion.setText("");
								txtFuncionalidad.setText("");
								txtFuncionalidad.setEditable(true);
							}
						} catch (NamingException e1) {
							e1.printStackTrace();
						}
					}
				}				
			}
		});

	}
	
	
	
	public void eliminarPermiso(Permiso permisos) throws NamingException {
		PermisoBeanRemote permisoBeanRemote  = (PermisoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PermisoBean!com.services.PermisoBeanRemote");
		try {
			permisoBeanRemote.borrar(permisos.getId());
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public void crearoModificarPermiso(Permiso permisos) throws NamingException {
		PermisoBeanRemote permisoBeanRemote  = (PermisoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PermisoBean!com.services.PermisoBeanRemote");
		try {
			permisoBeanRemote.actualizar(permisos);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	private JTable cargarPermisos() throws NamingException {
		
		List<Permiso> lista = listarPermiso();

		String[] nombreColumnas = {"ID", "Funcionalidad", "Descripción" };

		Object[][] datos = new Object[lista.size()][3];
		int fila = 0;

		
		for (Permiso c : lista) {
			System.out.println(c.getId());
			datos[fila][0] = c.getId();
			datos[fila][1] = c.getFuncionalidad();
			datos[fila][2] = c.getDescripcion();
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
		
		
		tablaPermisos = new JTable(model);
		tablaPermisos.setRowSelectionAllowed(true);
		tablaPermisos.setColumnSelectionAllowed(false);
		tablaPermisos.setBorder(null);
		tablaPermisos.setBackground(new Color(255, 255, 255));
		tablaPermisos.setForeground(new Color(153, 153, 153));
		tablaPermisos.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 13));
		tablaPermisos.setShowVerticalLines(false);
		tablaPermisos.setAutoscrolls(true);
		tablaPermisos.setSize(600, 600);
		Color color = new Color(144,238,144);
		tablaPermisos.setSelectionBackground(color);
		
		return tablaPermisos;

	}
	
	private void filtrar() {
		TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.tablaPermisos.getModel());
		filtro.setRowFilter(RowFilter.regexFilter(this.txtfiltro.getText(), 1));
		this.tablaPermisos.setRowSorter(filtro);

	}
	
	public List<Permiso> listarPermiso() throws NamingException {
		PermisoBeanRemote permisoBeanRemote  = (PermisoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PermisoBean!com.services.PermisoBeanRemote");
		return permisoBeanRemote.obtenerTodos();
	}
	
	public Permiso obtenerPorID(Long id) throws NamingException {
		PermisoBeanRemote permisoBeanRemote  = (PermisoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PermisoBean!com.services.PermisoBeanRemote");
		return permisoBeanRemote.obtenerporID(id);
	}
	
	public void crearPermiso(Permiso permiso) throws NamingException {
		PermisoBeanRemote permisoBeanRemote  = (PermisoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PermisoBean!com.services.PermisoBeanRemote");
		try {
			permisoBeanRemote.crear(permiso);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean controles_preCreate() {
		boolean error = false;
		String mensajeError ="";
		if (!txtDesripcion.getText().isEmpty()) {
			if(!txtFuncionalidad.getText().isEmpty()) {
				error = false;
			}else {
				error = true;
				mensajeError = "La funcionalidad del permiso es un campo obligatorio.";
			}
		}else {
			error = true;
			mensajeError = "La descripción del permiso es un campo obligatorio.";
		}
		
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se pudo crear el permiso", JOptionPane.ERROR_MESSAGE);}
		return error;	
	}
	
	public boolean controles_postCreate(Permiso permiso) throws NamingException {
		boolean error = false;
		String mensajeError = "";
		PermisoBeanRemote permisoBeanRemote  = (PermisoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PermisoBean!com.services.PermisoBeanRemote");
		mensajeError = permisoBeanRemote.controles_postCreate(permiso);
		if (!mensajeError.isEmpty()) {
			error = true;
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se pudo crear el permiso", JOptionPane.ERROR_MESSAGE);}
		return error;		
	}
	
	
		
	public boolean controles_preDelete(Permiso permiso) throws NamingException {
		boolean error = false;
		String mensajeError = "";
		PerfilesBeanRemote perfilesBeanRemote  = (PerfilesBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PerfilesBean!com.services.PerfilesBeanRemote");
		mensajeError = perfilesBeanRemote.controles_preDeletePer(permiso);
		if (!mensajeError.isEmpty()) {
			error = true;
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se puede eliminar el permiso", JOptionPane.ERROR_MESSAGE);}
		return error;		
	}
	
	public void reportarError(String error) {
		JOptionPane.showMessageDialog(this, error);
	}
	
	
	public boolean solicitarConfirmaciones(Permiso permiso) {
		boolean confirmado = false;
		int i =JOptionPane.showConfirmDialog(this,"¿Realmente Desea eliminar el permiso "+ permiso.getDescripcion()+"?","Confirmar",JOptionPane.YES_NO_OPTION);
		if (i==0) {
			confirmado = true;
		}
		return confirmado;
	}
}



