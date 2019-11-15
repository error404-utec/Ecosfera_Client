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
import com.entities.Usuario;
import com.exceptions.ServiciosException;
import com.framework.EcosferaScrollBar;
import com.services.PerfilesBeanRemote;
import com.services.PermisoBeanRemote;
import com.services.UsuarioBeanRemote;


public class JpUsuarios_Perfiles extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tablaPerfiles;
	private JTextField txtfiltro;
	private JTextField txtfiltro1;
	private JTable tablePerfilesUsuario;
	private static Usuario usuarioStatic;
	private JLabel lblNombrePefil = new JLabel((String) null);
	private static String staticmodo;

	public JpUsuarios_Perfiles(Usuario usuario, String modo) throws NamingException {
		usuarioStatic = usuario;
		staticmodo = modo;
		JFRPrincipal.setlblTitulopanel("Mantenimiento Perfiles");
		lblNombrePefil.setText(usuarioStatic.getId() + " - " + usuarioStatic.getUsuario());
		
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
		panel.setBounds(12, 54, 216, 24);
		pnltable.add(panel);
		
		JLabel lblId = new JLabel("ID");
		panel.add(lblId);
		lblId.setForeground(new Color(46, 139, 87));
		lblId.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(224, 54, 216, 24);
		pnltable.add(panel_2);
		
		JLabel lblFuncionalidad = new JLabel("Nombre");
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
				JpUsuarios jp;
				try {
					jp = new JpUsuarios(staticmodo,usuarioStatic);
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
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);
		
		JScrollPane scroolTablaPerfiles = new JScrollPane();
		scroolTablaPerfiles.setBackground(Color.WHITE);
		scroolTablaPerfiles.setBounds(12, 76, 428, 176);
		
		
/*
		scroolTablaPermisos.setLayout(new ScrollPaneLayout() {

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
		scroolTablaPermisos.getVerticalScrollBar().setUI(new EcosferaScrollBar());
		*/
		


		txtfiltro = new JTextField();
		txtfiltro.setBounds(78, 17, 362, 24);
		txtfiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtfiltro.setText(txtfiltro.getText().toUpperCase());
				filtrar();
			}
		});
		txtfiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtfiltro.setColumns(10);
		
		JLabel lblfiltro = new JLabel("Filtro");
		lblfiltro.setBounds(12, 18, 109, 20);
		lblfiltro.setForeground(new Color(46, 139, 87));
		lblfiltro.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		
		

		this.tablaPerfiles = this.cargarTodoslosPermisos();
		scroolTablaPerfiles.setViewportView(tablaPerfiles);
		setLayout(null);
		add(pnlNew);
		pnlNew.setLayout(null);
		
		JLabel label = new JLabel("Filtro");
		label.setForeground(new Color(46, 139, 87));
		label.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		label.setBounds(12, 55, 109, 20);
		pnlNew.add(label);
		
		txtfiltro1 = new JTextField();
		txtfiltro1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				txtfiltro1.setText(txtfiltro1.getText().toUpperCase());
				filtrar1();
			}
		});
		
		txtfiltro1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtfiltro1.setColumns(10);
		txtfiltro1.setBounds(78, 54, 362, 24);
		pnlNew.add(txtfiltro1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 113, 428, 145);
		pnlNew.add(scrollPane);
		/*
		scrollPane.setLayout(new ScrollPaneLayout() {

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
		scrollPane.getVerticalScrollBar().setUI(new EcosferaScrollBar());
		*/
		this.tablePerfilesUsuario = this.cargarPerfilesDelUsuario();
		scrollPane.setViewportView(tablePerfilesUsuario);
		
		
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
				Perfil perfilEliminar = null;
				if (tablePerfilesUsuario.getSelectedRow() > -1) {
					Long id = (Long) tablePerfilesUsuario.getValueAt(tablePerfilesUsuario.getSelectedRow(), 0);
					try {
						perfilEliminar = obtenerPerfilPorID(id);
						usuarioStatic.eliminarPerfil(perfilEliminar);

						actualizarUsuario(usuarioStatic);
						tablaPerfiles.setVisible(false);
						tablaPerfiles = cargarTodoslosPermisos();
						tablePerfilesUsuario.setVisible(false);
						tablePerfilesUsuario = cargarPerfilesDelUsuario();
						scrollPane.setViewportView(tablePerfilesUsuario);
						scroolTablaPerfiles.setViewportView(tablaPerfiles);
						tablaPerfiles.setVisible(true);
						tablePerfilesUsuario.setVisible(true);
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}else {
					reportarError("Debe seleccionar un perfil");
				}
			}
		});
		btnDesasignar.setForeground(new Color(46, 139, 87));
		btnDesasignar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnDesasignar.setBackground(new Color(245, 255, 250));
		
		
		lblNombrePefil.setForeground(new Color(46, 139, 87));
		lblNombrePefil.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNombrePefil.setBounds(12, 0, 272, 33);
		pnlNew.add(lblNombrePefil);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(224, 88, 216, 24);
		pnlNew.add(panel_1);
		
		JLabel label_1 = new JLabel("Nombre");
		label_1.setForeground(new Color(46, 139, 87));
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		panel_1.add(label_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 88, 216, 24);
		pnlNew.add(panel_3);
		
		JLabel label_2 = new JLabel("ID");
		label_2.setForeground(new Color(46, 139, 87));
		label_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		panel_3.add(label_2);
		
		
		
		
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (tablaPerfiles.getSelectedRow() > -1) {
						Long id = (Long) tablaPerfiles.getValueAt(tablaPerfiles.getSelectedRow(), 0);
						Perfil perfil = obtenerPerfilPorID(id);
						usuarioStatic.asginarPerfil(perfil);
						actualizarUsuario(usuarioStatic);
						tablaPerfiles.setVisible(false);
						tablaPerfiles = cargarTodoslosPermisos();
						tablePerfilesUsuario.setVisible(false);
						tablePerfilesUsuario = cargarPerfilesDelUsuario();
						scrollPane.setViewportView(tablePerfilesUsuario);
						scroolTablaPerfiles.setViewportView(tablaPerfiles);
						tablaPerfiles.setVisible(true);
						tablePerfilesUsuario.setVisible(true);
					}else {
						reportarError("Debe seleccionar un perfil");
					}	
				} catch (NamingException e1) {
					reportarError(e1.getMessage());
				}
			}
		});
		add(pnltable);
		pnltable.setLayout(null);
		pnltable.add(scroolTablaPerfiles);
		pnltable.add(txtfiltro);
		pnltable.add(lblfiltro);

	}
	
	
	public void actualizarUsuario(Usuario usuario) throws NamingException {
		UsuarioBeanRemote usuarioBeanRemote  = (UsuarioBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/UsuarioBean!com.services.UsuarioBeanRemote");
		try {
			usuarioBeanRemote.actualizar(usuario);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
	}
	

	public Perfil obtenerPerfilPorID(Long id) throws NamingException {
		PerfilesBeanRemote perfilesBeanRemote  = (PerfilesBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PerfilesBean!com.services.PerfilesBeanRemote");
		return perfilesBeanRemote.obtenerPorID(id);
	}
	
	
	private JTable cargarTodoslosPermisos() throws NamingException {
		PerfilesBeanRemote perfilBeanRemote  = (PerfilesBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PerfilesBean!com.services.PerfilesBeanRemote");
		List<Perfil> lista = perfilBeanRemote.obtenerTodos();
		List<Perfil> perfilesYasignados = usuarioStatic.getPerfiles();
		
		String[] nombreColumnas = {"ID", "Nombre" };

		int fila = 0;
		for (Perfil c : lista) {
			boolean agregar = true;
			for(Perfil a: perfilesYasignados) {
				if(a.getId() == c.getId()) {
					agregar = false;
				}
			}
			if (agregar) {
				fila++;
			}
		}
		
		Object[][] datos = new Object[fila][3];
		fila = 0;

		
		for (Perfil c : lista) {
			boolean agregar = true;
			for(Perfil a: perfilesYasignados) {
				if(a.getId() == c.getId()) {
					agregar = false;
				}
			}
			if (agregar) {
				datos[fila][0] = c.getId();
				datos[fila][1] = c.getNombre();	
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
			tablaPerfiles = new JTable(model);
			tablaPerfiles.setRowSelectionAllowed(true);
			tablaPerfiles.setColumnSelectionAllowed(false);
			tablaPerfiles.setBorder(null);
			tablaPerfiles.setBackground(new Color(255, 255, 255));
			tablaPerfiles.setForeground(new Color(153, 153, 153));
			tablaPerfiles.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 13));
			tablaPerfiles.setShowVerticalLines(false);
			tablaPerfiles.setAutoscrolls(true);
			tablaPerfiles.setSize(600, 600);
			Color color = new Color(144,238,144);
			tablaPerfiles.setSelectionBackground(color);
			
			return tablaPerfiles;
	
		}
		
		
		private JTable cargarPerfilesDelUsuario() throws NamingException {
			List<Perfil> lista = usuarioStatic.getPerfiles();
			
			
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
		
		tablePerfilesUsuario = new JTable(model);
		tablePerfilesUsuario.setRowSelectionAllowed(true);
		tablePerfilesUsuario.setColumnSelectionAllowed(false);
		tablePerfilesUsuario.setBorder(null);
		tablePerfilesUsuario.setBackground(new Color(255, 255, 255));
		tablePerfilesUsuario.setForeground(new Color(153, 153, 153));
		tablePerfilesUsuario.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 13));
		tablePerfilesUsuario.setShowVerticalLines(false);
		tablePerfilesUsuario.setAutoscrolls(true);
		tablePerfilesUsuario.setSize(600, 600);
		Color color = new Color(144,238,144);
		tablePerfilesUsuario.setSelectionBackground(color);
		
		return tablePerfilesUsuario;

	}
	
	private void filtrar() {
		TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.tablaPerfiles.getModel());
		filtro.setRowFilter(RowFilter.regexFilter(this.txtfiltro.getText(), 1));
		this.tablaPerfiles.setRowSorter(filtro);

	}
	
	private void filtrar1() {
		TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.tablePerfilesUsuario.getModel());
		filtro.setRowFilter(RowFilter.regexFilter(this.txtfiltro1.getText(), 1));
		this.tablePerfilesUsuario.setRowSorter(filtro);

	}
	
	
	public void reportarError(String error) {
		JOptionPane.showMessageDialog(this, error);
	}
}



