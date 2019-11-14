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
import javax.swing.JScrollPane;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.entities.Perfil;
import com.entities.Permiso;
import com.exceptions.ServiciosException;
import com.framework.EcosferaScrollBar;
import com.services.PerfilesBeanRemote;
import com.services.PermisoBeanRemote;


public class JpPerf_Permisos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tablaPermisos;
	private JTextField txtfiltro;
	private JTextField textField;
	private JTable tablePermisosPerfil;
	private Perfil perfil;

	public JpPerf_Permisos(Perfil perfil) throws NamingException {
		this.perfil = perfil;
		JFRPrincipal.setlblTitulopanel("Mantenimiento Perfiles");
		
		setBounds(new Rectangle(295, 256, 650, 582));
		setBackground(new Color(255, 255, 255));
		
		JPanel pnlNew = new JPanel();
		pnlNew.setBounds(110, 0, 452, 303);
		pnlNew.setBackground(new Color(255, 255, 255));
		pnlNew.setForeground(new Color(255, 255, 255));
		
		JPanel pnltable = new JPanel();
		pnltable.setBounds(110, 301, 452, 268);
		pnltable.setBackground(new Color(255, 255, 255));
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 54, 143, 24);
		pnltable.add(panel);
		
		JLabel lblId = new JLabel("ID");
		panel.add(lblId);
		lblId.setForeground(new Color(46, 139, 87));
		lblId.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(297, 54, 143, 24);
		pnltable.add(panel_2);
		
		JLabel lblFuncionalidad = new JLabel("Funcionalidad");
		lblFuncionalidad.setForeground(new Color(46, 139, 87));
		lblFuncionalidad.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		panel_2.add(lblFuncionalidad);
		
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
					e.printStackTrace();
				}
			}
		});
		label_3.setIcon(new ImageIcon(jpDep_Zona.class.getResource("/recursos/icons/go_back.png")));
		label_3.setBounds(0, 0, 51, 55);
		PnlVolver.add(label_3);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);
		
		JScrollPane scroolTablaPermisos = new JScrollPane();
		scroolTablaPermisos.setBounds(12, 76, 428, 176);
		
		
/*
		scroolTablaLocalidad.setLayout(new ScrollPaneLayout() {

			private static final long serialVersionUID = 1L;

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
		scroolTablaLocalidad.getVerticalScrollBar().setUI(new EcosferaScrollBar());
		scroolTablaLocalidad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
			}
		});
*/		
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
		
		
		

		this.tablaPermisos = this.cargarTodoslosPermisos();
		scroolTablaPermisos.setViewportView(tablaPermisos);
		setLayout(null);
		add(pnlNew);
		pnlNew.setLayout(null);
		
		JLabel label = new JLabel("Filtro");
		label.setForeground(new Color(46, 139, 87));
		label.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		label.setBounds(12, 55, 109, 20);
		pnlNew.add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBounds(78, 54, 362, 24);
		pnlNew.add(textField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 113, 428, 145);
		pnlNew.add(scrollPane);
		
		tablePermisosPerfil = new JTable((TableModel) null);
		scrollPane.setColumnHeaderView(tablePermisosPerfil);
		tablePermisosPerfil.setShowVerticalLines(false);
		tablePermisosPerfil.setSelectionBackground(new Color(144, 238, 144));
		tablePermisosPerfil.setRowSelectionAllowed(true);
		tablePermisosPerfil.setForeground(new Color(153, 153, 153));
		tablePermisosPerfil.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 13));
		tablePermisosPerfil.setColumnSelectionAllowed(false);
		tablePermisosPerfil.setBorder(null);
		tablePermisosPerfil.setBackground(Color.WHITE);
		tablePermisosPerfil.setAutoscrolls(true);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(154, 91, 143, 24);
		pnlNew.add(panel_3);
		
		JLabel label_1 = new JLabel("Descripci\u00F3n");
		label_1.setForeground(new Color(46, 139, 87));
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		panel_3.add(label_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(12, 91, 143, 24);
		pnlNew.add(panel_4);
		
		JLabel label_2 = new JLabel("ID");
		label_2.setForeground(new Color(46, 139, 87));
		label_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		panel_4.add(label_2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(297, 91, 143, 24);
		pnlNew.add(panel_5);
		
		JLabel label_4 = new JLabel("Funcionalidad");
		label_4.setForeground(new Color(46, 139, 87));
		label_4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		panel_5.add(label_4);
		
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(12, 262, 93, 29);
		pnlNew.add(btnAgregar);
		
		btnAgregar.setBackground(new Color(245, 255, 250));
		btnAgregar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnAgregar.setForeground(new Color(46, 139, 87));
		
		JButton btnDesasignar = new JButton("Eliminar");
		btnDesasignar.setBounds(347, 261, 93, 29);
		pnlNew.add(btnDesasignar);
		btnDesasignar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Permiso permisoEliminar = null;
				/*
				if (tablePermisosPerfil.getSelectedRow() > -1) {
					Long id = (Long) tablePermisosPerfil.getValueAt(tablePermisosPerfil.getSelectedRow(), 0);
					try {
						
						permisoEliminar = obtenerPermisoPorID(id);
						
						eliminarPerfil(perfilEliminar);
						tablaPerfiles.setVisible(false);
						tablaPerfiles = cargarPerfil();
						scroolTablaPermisos.setViewportView(tablaPerfiles);
						tablaPerfiles.setVisible(true);
						filtrar();
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}else {
					reportarError("Debe seleccionar un Perfil");
				}*/
			}
		});
		btnDesasignar.setForeground(new Color(46, 139, 87));
		btnDesasignar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnDesasignar.setBackground(new Color(245, 255, 250));
		
		JLabel label_5 = new JLabel((String) null);
		label_5.setForeground(new Color(46, 139, 87));
		label_5.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		label_5.setBounds(12, 0, 272, 33);
		pnlNew.add(label_5);
		
		
		
		
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*if (!controles_preCreate()) {
					Perfil perfil = new Perfil();		
					try {
						crearPerfil(perfil);
						tablaPerfiles.setVisible(false);
						tablaPerfiles = cargarPerfil();
						scroolTablaPermisos.setViewportView(tablaPerfiles);
						tablaPerfiles.setVisible(true);
						
					} catch (NamingException e1) {
						e1.printStackTrace();
					}
				}			*/	
			}
		});
		add(pnltable);
		pnltable.setLayout(null);
		pnltable.add(scroolTablaPermisos);
		pnltable.add(txtfiltro);
		pnltable.add(lblfiltro);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(154, 54, 143, 24);
		pnltable.add(panel_1);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setForeground(new Color(46, 139, 87));
		lblDescripcin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		panel_1.add(lblDescripcin);

	}
	
	
	public void actualizarPerfil(Perfil perfil) throws NamingException {
		PerfilesBeanRemote perfilesBeanRemote  = (PerfilesBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PerfilesBean!com.services.PerfilesBeanRemote");
		try {
			perfilesBeanRemote.actualizar(perfil);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public Perfil obtenerPorID(Long id) throws NamingException {
		PerfilesBeanRemote perfilesBeanRemote  = (PerfilesBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PerfilesBean!com.services.PerfilesBeanRemote");
		return perfilesBeanRemote.obtenerPorID(id);
	}
	
	private JTable cargarTodoslosPermisos() throws NamingException {
		PermisoBeanRemote permisosBeanRemote  = (PermisoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PermisoBean!com.services.PermisoBeanRemote");
		List<Permiso> lista = permisosBeanRemote.obtenerTodos();
		
		PerfilesBeanRemote perfilesBeanRemote  = (PerfilesBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PerfilesBean!com.services.PerfilesBeanRemote");
		Perfil perfil1 = perfilesBeanRemote.obtenerPorID(perfil.getId());
		
		List<Permiso> permisosYasignados = perfil1.getPermisos();
		
		String[] nombreColumnas = {"ID", "Descripción", "Funcionalidad" };

		Object[][] datos = new Object[lista.size()][3];
		int fila = 0;

		
		for (Permiso c : lista) {
			boolean agregar = true;
			for(Permiso a: permisosYasignados) {
				if(a.getId() == c.getId()) {
					agregar = false;
				}
			}
			if (agregar) {
			datos[fila][0] = c.getId();
			datos[fila][1] = c.getDescripcion();
			datos[fila][2] = c.getFuncionalidad();	
			
			fila++;
			}
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
		
		
		private JTable cargarPermisosDelperfil() throws NamingException {
			PerfilesBeanRemote perfilesBeanRemote  = (PerfilesBeanRemote)
					InitialContext.doLookup("ECOSFERA_MARK1/PerfilesBean!com.services.PerfilesBeanRemote");
			Perfil perfil1 = perfilesBeanRemote.obtenerPorID(perfil.getId());
			
			List<Permiso> lista = perfil1.getPermisos();
			
			
			String[] nombreColumnas = {"ID", "Descripción", "Funcionalidad" };

			Object[][] datos = new Object[lista.size()][3];
			int fila = 0;

			
			for (Permiso c : lista) {
				datos[fila][0] = c.getId();
				datos[fila][1] = c.getDescripcion();
				datos[fila][2] = c.getFuncionalidad();		
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
		
		tablePermisosPerfil = new JTable(model);
		tablePermisosPerfil.setRowSelectionAllowed(true);
		tablePermisosPerfil.setColumnSelectionAllowed(false);
		tablePermisosPerfil.setBorder(null);
		tablePermisosPerfil.setBackground(new Color(255, 255, 255));
		tablePermisosPerfil.setForeground(new Color(153, 153, 153));
		tablePermisosPerfil.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 13));
		tablePermisosPerfil.setShowVerticalLines(false);
		tablePermisosPerfil.setAutoscrolls(true);
		tablePermisosPerfil.setSize(600, 600);
		Color color = new Color(144,238,144);
		tablePermisosPerfil.setSelectionBackground(color);
		
		return tablaPermisos;

	}
	
	private void filtrar() {
		TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.tablaPermisos.getModel());
		filtro.setRowFilter(RowFilter.regexFilter(this.txtfiltro.getText(), 1));
		this.tablaPermisos.setRowSorter(filtro);

	}
	
	
	public void reportarError(String error) {
		JOptionPane.showMessageDialog(this, error);
	}
}



