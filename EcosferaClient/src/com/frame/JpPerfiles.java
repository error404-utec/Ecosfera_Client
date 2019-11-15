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

import com.entities.Departamento;
import com.entities.Perfil;
import com.exceptions.ServiciosException;
import com.framework.EcosferaScrollBar;
import com.services.PerfilesBeanRemote;


public class JpPerfiles extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTable tablaPerfiles;
	private JTextField txtfiltro;
	private JPanel pnlOptions = new JPanel();

	public JpPerfiles() throws NamingException {
		JFRPrincipal.setlblTitulopanel("Mantenimiento Perfiles");
		
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
		panel.setBounds(12, 54, 211, 24);
		pnltable.add(panel);
		
		JLabel lblId = new JLabel("ID");
		panel.add(lblId);
		lblId.setForeground(new Color(46, 139, 87));
		lblId.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(223, 54, 217, 24);
		pnltable.add(panel_2);
		
		JLabel label_2 = new JLabel("Nombre");
		label_2.setForeground(new Color(46, 139, 87));
		label_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		panel_2.add(label_2);
		add(pnlOptions);
		
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
		
		
		pnlOptions.setBounds(110, 482, 452, 55);
		pnlOptions.setBackground(new Color(255, 255, 255));
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 53, 51, 20);
		lblNombre.setForeground(new Color(46, 139, 87));
		lblNombre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtNombre.setText(txtNombre.getText().toUpperCase());
			}
		});
		txtNombre.setBounds(78, 52, 362, 24);
		txtNombre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		
		
		JButton btnAgregar = new JButton("Aceptar");
		btnAgregar.setBounds(240, 89, 96, 27);
		
		btnAgregar.setBackground(new Color(245, 255, 250));
		btnAgregar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnAgregar.setForeground(new Color(46, 139, 87));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(344, 89, 96, 27);
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtNombre.setText("");
			}
		});
		btnCancelar.setForeground(new Color(46, 139, 87));
		btnCancelar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnCancelar.setBackground(new Color(245, 255, 250));
		
		JScrollPane scroolTablaPerfiles = new JScrollPane();
		scroolTablaPerfiles.setBounds(12, 76, 428, 176);
		
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
		
		
		

		this.tablaPerfiles = this.cargarPerfil();
		scroolTablaPerfiles.setViewportView(tablaPerfiles);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Perfil perfilEliminar = null;
				if (tablaPerfiles.getSelectedRow() > -1) {
					Long id = (Long) tablaPerfiles.getValueAt(tablaPerfiles.getSelectedRow(), 0);
					try {
						
						perfilEliminar = obtenerPorID(id);
						if(!controles_preDelete(perfilEliminar)) {
							eliminarPerfil(perfilEliminar);
							tablaPerfiles.setVisible(false);
							tablaPerfiles = cargarPerfil();
							scroolTablaPerfiles.setViewportView(tablaPerfiles);
							tablaPerfiles.setVisible(true);
							filtrar();
						}
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}else {
					reportarError("Debe seleccionar un Perfil");
				}
			}
		});
		btnEliminar.setForeground(new Color(46, 139, 87));
		btnEliminar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnEliminar.setBackground(new Color(245, 255, 250));
		
		JButton btnAsignar = new JButton("Permisos");
		btnAsignar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JPanel jp;
				Perfil perfilParametro= null;
				if (tablaPerfiles.getSelectedRow() > -1) {
					Long id = (Long) tablaPerfiles.getValueAt(tablaPerfiles.getSelectedRow(), 0);
					try {
						perfilParametro = obtenerPorID(id);
						jp = new JpPerf_Permisos(perfilParametro);
						jp.setBounds(290, 238, 660, 600);
						jp.setVisible(true);
						jp.setLocation(12,12);
						JFRPrincipal.getIntance();
						JFRPrincipal.PnlWorkSpace.removeAll();
						JFRPrincipal.PnlWorkSpace.add(jp);
						JFRPrincipal.PnlWorkSpace.revalidate();
						JFRPrincipal.PnlWorkSpace.repaint();
						JFRPrincipal.LblNavegacion.setText("Inicio"+ " - " + "Localidades");
					} catch (NamingException e1) {
						e1.printStackTrace();
					}
				}else {
					reportarError("Debe seleccionar un Perfil");
				}
			}
		});
		btnAsignar.setForeground(new Color(46, 139, 87));
		btnAsignar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnAsignar.setBackground(new Color(245, 255, 250));
		GroupLayout gl_pnlOptions = new GroupLayout(pnlOptions);
		gl_pnlOptions.setHorizontalGroup(
			gl_pnlOptions.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlOptions.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAsignar, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
					.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_pnlOptions.setVerticalGroup(
			gl_pnlOptions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlOptions.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlOptions.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAsignar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		pnlOptions.setLayout(gl_pnlOptions);
		setLayout(null);
		add(pnlNew);
		pnlNew.setLayout(null);
		pnlNew.add(btnCancelar);
		pnlNew.add(btnAgregar);
		pnlNew.add(txtNombre);
		pnlNew.add(lblNombre);
		add(pnltable);
		pnltable.setLayout(null);
		pnltable.add(scroolTablaPerfiles);
		pnltable.add(txtfiltro);
		pnltable.add(lblfiltro);
		add(pnlOptions);
		
		
		
		
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!controles_preCreate()) {
					Perfil perfil = new Perfil();
					perfil.setNombre(txtNombre.getText());			
					try {
						if (!controles_postCreate(perfil)) {
							
							crearPerfil(perfil);
							tablaPerfiles.setVisible(false);
							tablaPerfiles = cargarPerfil();
							scroolTablaPerfiles.setViewportView(tablaPerfiles);
							tablaPerfiles.setVisible(true);
							txtNombre.setText("");
						}
						
					} catch (NamingException e1) {
						e1.printStackTrace();
					}
				}				
			}
		});

	}
	
	public void crearPerfil(Perfil perfil) throws NamingException {
		PerfilesBeanRemote perfilesBeanRemote  = (PerfilesBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PerfilesBean!com.services.PerfilesBeanRemote");
		try {
			perfilesBeanRemote.actualizar(perfil);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public void eliminarPerfil(Perfil perfil) throws NamingException {
		PerfilesBeanRemote perfilesBeanRemote  = (PerfilesBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PerfilesBean!com.services.PerfilesBeanRemote");
		try {
			perfilesBeanRemote.borrar(perfil.getId());
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public Perfil obtenerPorID(Long id) throws NamingException {
		PerfilesBeanRemote perfilesBeanRemote  = (PerfilesBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PerfilesBean!com.services.PerfilesBeanRemote");
		return perfilesBeanRemote.obtenerPorID(id);
	}
	
	private JTable cargarPerfil() throws NamingException {
		PerfilesBeanRemote perfilesBeanRemote  = (PerfilesBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PerfilesBean!com.services.PerfilesBeanRemote");
		List<Perfil> lista = perfilesBeanRemote.obtenerTodos();
		
		
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
	
	private void filtrar() {
		TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.tablaPerfiles.getModel());
		filtro.setRowFilter(RowFilter.regexFilter(this.txtfiltro.getText(), 1));
		this.tablaPerfiles.setRowSorter(filtro);

	}
	

	
	public boolean controles_preCreate() {
		boolean error = false;
		String mensajeError ="";
		if(!txtNombre.getText().isEmpty()) {
			error = false;
		}else {
			error = true;
			mensajeError = "El nombre de la localidad es un campo obligatorio.";
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se pudo crear el perfil", JOptionPane.ERROR_MESSAGE);}
		return error;	
	}
	
	public boolean controles_postCreate(Perfil perfil) throws NamingException {
		boolean error = false;
		String mensajeError = "";
		PerfilesBeanRemote perfilesBeanRemote  = (PerfilesBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/PerfilesBean!com.services.PerfilesBeanRemote");
		mensajeError = perfilesBeanRemote.controles_postCreate(perfil);
		if (!mensajeError.isEmpty()) {
			error = true;
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se pudo crear el perfil", JOptionPane.ERROR_MESSAGE);}
		return error;		
	}
	
	
	public boolean controles_preDelete(Perfil perfil) throws NamingException {
		boolean error = false;
		/*
		String mensajeError = "";
		LocalidadBeanRemote localidadBeanRemote  = (LocalidadBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/LocalidadBean!com.services.LocalidadBeanRemote");
		mensajeError = localidadBeanRemote.controles_preDelete(localidad);
		if (!mensajeError.isEmpty()) {
			error = true;
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se puede eliminar la localidad", JOptionPane.ERROR_MESSAGE);}
		*/
		return error;		
	}
	
	public void reportarError(String error) {
		JOptionPane.showMessageDialog(this, error);
	}
}



