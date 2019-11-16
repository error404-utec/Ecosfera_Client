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
import com.entities.Localidad;
import com.entities.Zona;
import com.exceptions.ServiciosException;
import com.framework.EcosferaScrollBar;
import com.services.DepartamentoBeanRemote;
import com.services.LocalidadBeanRemote;
import com.services.ObservacionBeanRemote;


public class JpLoc_Dep extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtCodigo;
	private Departamento departamento;
	private static Zona zonastatic;
	


	private JTable tablaLocalidad;
	private Localidad localidadActualizar = null;
	private JTextField txtfiltro;
	private JPanel pnlOptions = new JPanel();
	
	
	/**
	 * Create the panel.
	 * @throws NamingException 
	 */
	public JpLoc_Dep(Departamento departamento,Zona zona) throws NamingException {
		JFRPrincipal.setlblTitulopanel("Mantenimiento Localidades");
		zonastatic = zona;
		this.departamento = departamento;
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
		
		JLabel label_1 = new JLabel("C\u00F3digo");
		label_1.setForeground(new Color(46, 139, 87));
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		panel_1.add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(296, 54, 144, 24);
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
				jpDep_Zona jp;
				try {
					jp = new jpDep_Zona(zonastatic);
					jp.setBounds(290, 238, 660, 600);
					jp.setVisible(true);
					jp.setLocation(12,12);
					JFRPrincipal.getIntance();
					JFRPrincipal.PnlWorkSpace.removeAll();
					JFRPrincipal.PnlWorkSpace.add(jp);
					JFRPrincipal.PnlWorkSpace.revalidate();
					JFRPrincipal.PnlWorkSpace.repaint();
					JFRPrincipal.LblNavegacion.setText("Inicio - Zonas - Departamentos");
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
		
		
		pnlOptions.setBounds(110, 482, 452, 55);
		pnlOptions.setBackground(new Color(255, 255, 255));
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 85, 51, 20);
		lblNombre.setForeground(new Color(46, 139, 87));
		lblNombre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtNombre.setText(txtNombre.getText().toUpperCase());
			}
			
			public void keyTyped(KeyEvent arg0) {
				if(txtNombre.getText().length()>=50) {
					getToolkit().beep();
					arg0.consume();
				}
			}
		});
		txtNombre.setBounds(78, 88, 362, 24);
		txtNombre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(12, 52, 45, 20);
		lblCodigo.setForeground(new Color(46, 139, 87));
		lblCodigo.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if (!(Character.isDigit(c) ||
				(c == KeyEvent.VK_BACK_SPACE) ||
				(c == KeyEvent.VK_DELETE))) {
				getToolkit().beep();
				arg0.consume();
				}
				if(txtCodigo.getText().length()>=38) {
					getToolkit().beep();
					arg0.consume();
				}
			
			}
		});
		txtCodigo.setBounds(78, 51, 362, 24);
		txtCodigo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtCodigo.setColumns(10);
		
		
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
				txtCodigo.setText("");
				txtNombre.setText("");
				localidadActualizar = null;
				txtCodigo.setEditable(true);
			}
		});
		btnCancelar.setForeground(new Color(46, 139, 87));
		btnCancelar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnCancelar.setBackground(new Color(245, 255, 250));
		
		JScrollPane scroolTablaLocalidad = new JScrollPane();
		scroolTablaLocalidad.setBounds(12, 76, 428, 176);
		

		scroolTablaLocalidad.setLayout(new ScrollPaneLayout() {

			/**
			 * 
			 */
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
		
		JLabel lblfiltro = new JLabel("C\u00F3digo");
		lblfiltro.setBounds(12, 18, 109, 20);
		lblfiltro.setForeground(new Color(46, 139, 87));
		lblfiltro.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		
		

		this.tablaLocalidad = this.cargarLocalidad();
		scroolTablaLocalidad.setViewportView(tablaLocalidad);
		
		JButton btnModificat = new JButton("Modificar");
		btnModificat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				localidadActualizar = null;
				if (tablaLocalidad.getSelectedRow() > -1) {
					Long id = (Long) tablaLocalidad.getValueAt(tablaLocalidad.getSelectedRow(), 0);
					try {
						localidadActualizar = obtenerPorID(id);
						txtNombre.setText(localidadActualizar.getNombre());
						txtCodigo.setText(Long.toString(localidadActualizar.getCodigo()));
						txtCodigo.setEditable(false);
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}else {
					reportarError("Debe seleccionar una localidad");
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
				Localidad localidadEliminar = null;
				if (tablaLocalidad.getSelectedRow() > -1) {
					Long id = (Long) tablaLocalidad.getValueAt(tablaLocalidad.getSelectedRow(), 0);
					try {
						
						localidadEliminar = obtenerPorID(id);
						if(!controles_preDelete(localidadEliminar)) {
							if (solicitarConfirmaciones(localidadEliminar)) {
								eliminarLocalidad(localidadEliminar);
								tablaLocalidad.setVisible(false);
								tablaLocalidad = cargarLocalidad();
								scroolTablaLocalidad.setViewportView(tablaLocalidad);
								tablaLocalidad.setVisible(true);
								filtrar();
							}
						}
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}else {
					reportarError("Debe seleccionar una localidad");
				}
			}
		});
		btnEliminar.setForeground(new Color(46, 139, 87));
		btnEliminar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnEliminar.setBackground(new Color(245, 255, 250));
		GroupLayout gl_pnlOptions = new GroupLayout(pnlOptions);
		gl_pnlOptions.setHorizontalGroup(
			gl_pnlOptions.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlOptions.createSequentialGroup()
					.addContainerGap(247, Short.MAX_VALUE)
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
		pnlNew.add(lblCodigo);
		pnlNew.add(txtCodigo);
		pnlNew.add(btnCancelar);
		pnlNew.add(btnAgregar);
		pnlNew.add(txtNombre);
		pnlNew.add(lblNombre);
		
		JLabel lblDepartamento = new JLabel(departamento.getNombre());
		lblDepartamento.setForeground(new Color(46, 139, 87));
		lblDepartamento.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblDepartamento.setBounds(12, 6, 272, 33);
		pnlNew.add(lblDepartamento);
		add(pnltable);
		pnltable.setLayout(null);
		pnltable.add(scroolTablaLocalidad);
		pnltable.add(txtfiltro);
		pnltable.add(lblfiltro);
		add(pnlOptions);
		
		
		
		
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!controles_preCreate()) {
					if (localidadActualizar!=null) {
						try {
							localidadActualizar.setCodigo(Long.parseLong(txtCodigo.getText()));
							localidadActualizar.setNombre(txtNombre.getText());
							
							if(!controles_postModify(localidadActualizar)) {
								acutalizarLocalidad(localidadActualizar);
							
								tablaLocalidad.setVisible(false);
								tablaLocalidad = cargarLocalidad();
								scroolTablaLocalidad.setViewportView(tablaLocalidad);
								tablaLocalidad.setVisible(true);
								txtNombre.setText("");
								txtCodigo.setText("");
								localidadActualizar = null;
								txtCodigo.setEditable(true);
								filtrar();
							}
						} catch (NamingException e1) {
							e1.printStackTrace();
						}
					}else {
						Localidad localidad = new Localidad();
						localidad.setCodigo(Long.parseLong(txtCodigo.getText()));
						localidad.setNombre(txtNombre.getText());			
						try {
							Departamento departamento1 = new Departamento();
							departamento1 = obtenerDepartamentoPorID(departamento.getId());
							if (!controles_postCreate(localidad)) {
								departamento1.asignarLocalidad(localidad);
								
								crearLocalidad(departamento1);
								tablaLocalidad.setVisible(false);
								tablaLocalidad = cargarLocalidad();
								scroolTablaLocalidad.setViewportView(tablaLocalidad);
								tablaLocalidad.setVisible(true);
								txtNombre.setText("");
								txtCodigo.setText("");
							}
							
						} catch (NamingException e1) {
							e1.printStackTrace();
						}
					}
				}				
			}
		});

	}
	
	public void crearLocalidad(Departamento departamento) throws NamingException {
		DepartamentoBeanRemote departamentoBeanRemote  = (DepartamentoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/DepartamentoBean!com.services.DepartamentoBeanRemote");
		try {
			departamentoBeanRemote.actualizar(departamento);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public void eliminarLocalidad(Localidad localidad) throws NamingException {
		LocalidadBeanRemote localidadBeanRemote  = (LocalidadBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/LocalidadBean!com.services.LocalidadBeanRemote");
		try {
			localidadBeanRemote.borrar(localidad.getId());
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public void acutalizarLocalidad(Localidad Localidad) throws NamingException {
		LocalidadBeanRemote localidadBeanRemote  = (LocalidadBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/LocalidadBean!com.services.LocalidadBeanRemote");
		try {
			localidadBeanRemote.actualizar(Localidad);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public Departamento obtenerDepartamentoPorID(Long id) throws NamingException {
		DepartamentoBeanRemote departamentoBeanRemote  = (DepartamentoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/DepartamentoBean!com.services.DepartamentoBeanRemote");
		return departamentoBeanRemote.obtenerporID(id);
	}
	
	public Localidad obtenerPorID(Long id) throws NamingException {
		LocalidadBeanRemote localidadBeanRemote  = (LocalidadBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/LocalidadBean!com.services.LocalidadBeanRemote");
		return localidadBeanRemote.obtenerPorID(id);
	}
	
	private JTable cargarLocalidad() throws NamingException {
		departamento = obtenerDepartamentoPorID(departamento.getId());
		List<Localidad> lista = departamento.getLocalidades();
		
		
		String[] nombreColumnas = {"ID", "Código", "Nombre" };

		Object[][] datos = new Object[lista.size()][3];
		int fila = 0;

		
		for (Localidad c : lista) {
			datos[fila][0] = c.getId();
			datos[fila][1] = c.getCodigo();
			datos[fila][2] = c.getNombre();
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
		
		
		tablaLocalidad = new JTable(model);
		tablaLocalidad.setRowSelectionAllowed(true);
		tablaLocalidad.setColumnSelectionAllowed(false);
		tablaLocalidad.setBorder(null);
		tablaLocalidad.setBackground(new Color(255, 255, 255));
		tablaLocalidad.setForeground(new Color(153, 153, 153));
		tablaLocalidad.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 13));
		tablaLocalidad.setShowVerticalLines(false);
		tablaLocalidad.setAutoscrolls(true);
		tablaLocalidad.setSize(600, 600);
		Color color = new Color(144,238,144);
		tablaLocalidad.setSelectionBackground(color);
		
		return tablaLocalidad;

	}
	
	private void filtrar() {
		TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.tablaLocalidad.getModel());
		filtro.setRowFilter(RowFilter.regexFilter(this.txtfiltro.getText(), 1));
		this.tablaLocalidad.setRowSorter(filtro);

	}
	

	
	public boolean controles_preCreate() {
		boolean error = false;
		String mensajeError ="";
		if (!txtCodigo.getText().isEmpty()) {
			if(!txtNombre.getText().isEmpty()) {
				error = false;
			}else {
				error = true;
				mensajeError = "El nombre de la localidad es un campo obligatorio.";
			}
		}else {
			error = true;
			mensajeError = "El Código de la localidad es un campo obligatorio.";
		}
		
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se pudo crear la localidad", JOptionPane.ERROR_MESSAGE);}
		return error;	
	}
	
	public boolean controles_postCreate(Localidad localidad) throws NamingException {
		boolean error = false;
		String mensajeError = "";
		LocalidadBeanRemote localidadBeanRemote  = (LocalidadBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/LocalidadBean!com.services.LocalidadBeanRemote");
		mensajeError = localidadBeanRemote.controles_postCreate(localidad);
		if (!mensajeError.isEmpty()) {
			error = true;
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se pudo crear la localidad", JOptionPane.ERROR_MESSAGE);}
		return error;		
	}
	
	
	public boolean controles_postModify(Localidad localidad) throws NamingException {
		boolean error = false;
		String mensajeError = "";
		LocalidadBeanRemote localidadBeanRemote  = (LocalidadBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/LocalidadBean!com.services.LocalidadBeanRemote");
		mensajeError = localidadBeanRemote.controles_postModify(localidad);
		if (!mensajeError.isEmpty()) {
			error = true;
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se pudo modificar la localidad", JOptionPane.ERROR_MESSAGE);}
		return error;		
	}
	
	public boolean controles_preDelete(Localidad localidad) throws NamingException {
		boolean error = false;
		String mensajeError = "";
		ObservacionBeanRemote observacionBeanRemote  = (ObservacionBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/ObservacionBean!com.services.ObservacionBeanRemote");
		mensajeError = observacionBeanRemote.controles_PreDeleteLocalidad(localidad);
		
		if (mensajeError.isEmpty()) {
			LocalidadBeanRemote localidadBeanRemote  = (LocalidadBeanRemote)
					InitialContext.doLookup("ECOSFERA_MARK1/LocalidadBean!com.services.LocalidadBeanRemote");
			mensajeError = localidadBeanRemote.controles_preDelete(localidad);
		}
		
		if (!mensajeError.isEmpty()) {
			error = true;
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se puede eliminar la localidad", JOptionPane.ERROR_MESSAGE);}
		return error;		
	}
	
	public void reportarError(String error) {
		JOptionPane.showMessageDialog(this, error);
	}
	
	public boolean solicitarConfirmaciones(Localidad localidadEliminar) {
		boolean confirmado = false;
		int i =JOptionPane.showConfirmDialog(this,"¿Realmente Desea eliminar la localidad "+ localidadEliminar.getNombre()+"?","Confirmar",JOptionPane.YES_NO_OPTION);
		if (i==0) {
			confirmado = true;
		}
		return confirmado;
	}
	
}



