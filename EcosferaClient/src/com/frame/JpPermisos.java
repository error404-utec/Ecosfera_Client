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
import com.entities.Permiso;
import com.exceptions.ServiciosException;
import com.framework.EcosferaScrollBar;
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
		setBounds(new Rectangle(295, 256, 650, 582));
		setBackground(new Color(255, 255, 255));
		
		JPanel pnlNew = new JPanel();
		pnlNew.setBounds(0, 8, 452, 165);
		pnlNew.setBackground(new Color(255, 255, 255));
		pnlNew.setForeground(new Color(255, 255, 255));
		
		JPanel pnltable = new JPanel();
		pnltable.setBounds(0, 180, 452, 259);
		pnltable.setBackground(new Color(255, 255, 255));
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);
		
		JPanel pnlOptions = new JPanel();
		pnlOptions.setBounds(0, 446, 452, 55);
		pnlOptions.setBackground(new Color(255, 255, 255));
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setForeground(new Color(46, 139, 87));
		lblDescripcion.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtDesripcion = new JTextField();
		txtDesripcion.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtDesripcion.setColumns(10);
		
		JLabel lblFuncionalidad = new JLabel("Funcionalidad");
		lblFuncionalidad.setForeground(new Color(46, 139, 87));
		lblFuncionalidad.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtFuncionalidad = new JTextField();
		txtFuncionalidad.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtFuncionalidad.setColumns(10);
		
		JButton btnAgregar = new JButton("Aceptar");
		
		btnAgregar.setBackground(new Color(245, 255, 250));
		btnAgregar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnAgregar.setForeground(new Color(46, 139, 87));
		
		JPanel Close_Panel = new JPanel();
		Close_Panel.addMouseListener(new MouseAdapter() {
			
		});
		Close_Panel.setForeground(Color.WHITE);
		Close_Panel.setBackground(new Color(255, 255, 255));
		GroupLayout gl_pnlNew = new GroupLayout(pnlNew);
		gl_pnlNew.setHorizontalGroup(
			gl_pnlNew.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlNew.createSequentialGroup()
					.addGroup(gl_pnlNew.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_pnlNew.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFuncionalidad)
							.addPreferredGap(ComponentPlacement.RELATED, 306, Short.MAX_VALUE)
							.addComponent(Close_Panel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_pnlNew.createSequentialGroup()
							.addGap(22)
							.addComponent(txtFuncionalidad, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
						.addGroup(gl_pnlNew.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_pnlNew.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlNew.createSequentialGroup()
									.addGap(10)
									.addComponent(txtDesripcion, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
								.addComponent(lblDescripcion)))
						.addGroup(Alignment.TRAILING, gl_pnlNew.createSequentialGroup()
							.addContainerGap(347, Short.MAX_VALUE)
							.addComponent(btnAgregar)))
					.addContainerGap())
		);
		gl_pnlNew.setVerticalGroup(
			gl_pnlNew.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlNew.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlNew.createParallelGroup(Alignment.LEADING)
						.addComponent(Close_Panel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFuncionalidad))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFuncionalidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDescripcion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtDesripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addComponent(btnAgregar)
					.addContainerGap())
		);
		pnlNew.setLayout(gl_pnlNew);
		
		JScrollPane scroolTablaPermisos = new JScrollPane();
		
		
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
		txtfiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtrar();
			}
		});
		txtfiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtfiltro.setColumns(10);
		
		JLabel lblfiltro = new JLabel("Funcionalidad");
		lblfiltro.setForeground(new Color(46, 139, 87));
		lblfiltro.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));

		GroupLayout gl_pnltable = new GroupLayout(pnltable);
		gl_pnltable.setHorizontalGroup(
			gl_pnltable.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnltable.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnltable.createParallelGroup(Alignment.LEADING)
						.addComponent(scroolTablaPermisos, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(gl_pnltable.createSequentialGroup()
							.addGap(10)
							.addComponent(txtfiltro, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
						.addComponent(lblfiltro, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_pnltable.setVerticalGroup(
			gl_pnltable.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnltable.createSequentialGroup()
					.addGap(18)
					.addComponent(lblfiltro, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtfiltro, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scroolTablaPermisos, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(107))
		);
		
		
		

		this.tablaPermisos = this.cargarPermisos();
		scroolTablaPermisos.setViewportView(tablaPermisos);
				
		pnltable.setLayout(gl_pnltable);
		
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
					} catch (NamingException e) {
						e.printStackTrace();
					}
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
						eliminarPermiso(permEliminar);;
						tablaPermisos.setVisible(false);
						tablaPermisos = cargarPermisos();
						scroolTablaPermisos.setViewportView(tablaPermisos);
						tablaPermisos.setVisible(true);
						filtrar();
					} catch (NamingException e) {
						e.printStackTrace();
					}
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
		add(pnltable);
		add(pnlOptions);
		
		
		
		
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
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
						filtrar();
					} catch (NamingException e1) {
						e1.printStackTrace();
					}
				}else {
					Permiso permiso = new Permiso();
					permiso.setDescripcion(txtDesripcion.getText());
					permiso.setFuncionalidad(txtFuncionalidad.getText());			
					try {
						crearPermiso(permiso);
						tablaPermisos.setVisible(false);
						tablaPermisos = cargarPermisos();
						scroolTablaPermisos.setViewportView(tablaPermisos);
						tablaPermisos.setVisible(true);
						txtDesripcion.setText("");
						txtFuncionalidad.setText("");
					} catch (NamingException e1) {
						e1.printStackTrace();
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
}



