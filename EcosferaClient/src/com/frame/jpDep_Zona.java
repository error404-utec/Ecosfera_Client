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
import javax.swing.JComponent;
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
import com.entities.Zona;
import com.exceptions.ServiciosException;
import com.framework.EcosferaScrollBar;
import com.services.DepartamentoBeanRemote;
import com.services.ZonaBeanRemote;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;


public class jpDep_Zona extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtCodigo;
	private Zona zona;
	private JLabel lblZonaName = new JLabel((String) null);
	


	private JTable tablaDepartamento;
	private Departamento departamentoActualizar = null;
	private JTextField txtfiltro;
	

	
	/**
	 * Create the panel.
	 * @throws NamingException 
	 */
	public jpDep_Zona(Zona zona) throws NamingException {
		this.zona = zona;
		lblZonaName.setText(zona.getNombre());
		JFRPrincipal.setlblTitulopanel("Mantenimiento Localidades");
		setBounds(new Rectangle(295, 256, 650, 582));
		setBackground(new Color(255, 255, 255));
		
		
		JPanel pnlNew = new JPanel();
		pnlNew.setBounds(110, 0, 452, 165);
		pnlNew.setBackground(new Color(255, 255, 255));
		pnlNew.setForeground(new Color(255, 255, 255));
		
		JPanel pnltable = new JPanel();
		pnltable.setBounds(110, 178, 452, 291);
		pnltable.setBackground(new Color(255, 255, 255));
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);
		
		JPanel pnlOptions = new JPanel();
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
				if(txtCodigo.getText().length()>=5) {
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
				departamentoActualizar = null;
				txtCodigo.setEditable(true);
			}
		});
		btnCancelar.setForeground(new Color(46, 139, 87));
		btnCancelar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnCancelar.setBackground(new Color(245, 255, 250));
		
		JScrollPane scroolTablaDepartamento = new JScrollPane();
		scroolTablaDepartamento.setBounds(12, 78, 428, 200);
		
		
		scroolTablaDepartamento.setLayout(new ScrollPaneLayout() {


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
		scroolTablaDepartamento.getVerticalScrollBar().setUI(new EcosferaScrollBar());
		scroolTablaDepartamento.addMouseListener(new MouseAdapter() {
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
		
		
		

		this.tablaDepartamento = this.cargarDepartamento();
		scroolTablaDepartamento.setViewportView(tablaDepartamento);
		
		JButton btnModificat = new JButton("Modificar");
		btnModificat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				departamentoActualizar = null;
				if (tablaDepartamento.getSelectedRow() > -1) {
					Long id = (Long) tablaDepartamento.getValueAt(tablaDepartamento.getSelectedRow(), 0);
					try {
						departamentoActualizar = obtenerPorID(id);
						txtNombre.setText(Long.toString(departamentoActualizar.getCodigo()));
						txtCodigo.setText(departamentoActualizar.getNombre());
						txtCodigo.setEditable(false);
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}else {
					reportarError("Debe seleccionar un departamento");
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
				Departamento departamentoEliminar = null;
				if (tablaDepartamento.getSelectedRow() > -1) {
					Long id = (Long) tablaDepartamento.getValueAt(tablaDepartamento.getSelectedRow(), 0);
					try {
						departamentoEliminar = obtenerPorID(id);
						if(!controles_preDelete(departamentoEliminar)) {
							eliminarDepartamento(departamentoEliminar);;
							tablaDepartamento.setVisible(false);
							tablaDepartamento = cargarDepartamento();
							scroolTablaDepartamento.setViewportView(tablaDepartamento);
							tablaDepartamento.setVisible(true);
							filtrar();
						}	
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}else {
					reportarError("Debe seleccionar un departamento");
				}
			}
		});
		btnEliminar.setForeground(new Color(46, 139, 87));
		btnEliminar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnEliminar.setBackground(new Color(245, 255, 250));
		
		JButton btnLocalidades = new JButton("Localidades");
		btnLocalidades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JPanel jp;
				Departamento departamentoParametro= null;
				if (tablaDepartamento.getSelectedRow() > -1) {
					Long id = (Long) tablaDepartamento.getValueAt(tablaDepartamento.getSelectedRow(), 0);
					try {
						departamentoParametro = obtenerPorID(id);
						jp = new JpLoc_Dep(departamentoParametro);
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
					reportarError("Debe seleccionar un departamento");
				}
				
			}
		});
		btnLocalidades.setForeground(new Color(46, 139, 87));
		btnLocalidades.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnLocalidades.setBackground(new Color(245, 255, 250));
		GroupLayout gl_pnlOptions = new GroupLayout(pnlOptions);
		gl_pnlOptions.setHorizontalGroup(
			gl_pnlOptions.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlOptions.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLocalidades, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
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
								.addComponent(btnLocalidades, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
						.addGroup(gl_pnlOptions.createSequentialGroup()
							.addComponent(btnModificat, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(13))))
		);
		pnlOptions.setLayout(gl_pnlOptions);
		setLayout(null);
		add(pnlNew);
		pnlNew.setLayout(null);
		pnlNew.add(btnCancelar);
		pnlNew.add(btnAgregar);
		pnlNew.add(lblCodigo);
		pnlNew.add(txtCodigo);
		pnlNew.add(txtNombre);
		pnlNew.add(lblNombre);
		
		
		lblZonaName.setForeground(new Color(46, 139, 87));
		lblZonaName.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblZonaName.setBounds(12, 6, 272, 33);
		pnlNew.add(lblZonaName);
		add(pnltable);
		pnltable.setLayout(null);
		pnltable.add(scroolTablaDepartamento);
		pnltable.add(txtfiltro);
		pnltable.add(lblfiltro);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 54, 144, 24);
		pnltable.add(panel);
		
		JLabel label = new JLabel("ID");
		label.setForeground(new Color(46, 139, 87));
		label.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		panel.add(label);
		
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
				jpZonas jp;
				try {
					jp = new jpZonas();
					jp.setBounds(290, 238, 660, 600);
					jp.setVisible(true);
					jp.setLocation(12,12);
					JFRPrincipal.getIntance();
					JFRPrincipal.PnlWorkSpace.removeAll();
					JFRPrincipal.PnlWorkSpace.add(jp);
					JFRPrincipal.PnlWorkSpace.revalidate();
					JFRPrincipal.PnlWorkSpace.repaint();
					JFRPrincipal.LblNavegacion.setText("Inicio"+ " - " + "Zonas");
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		label_3.setIcon(new ImageIcon(jpDep_Zona.class.getResource("/recursos/icons/go_back.png")));
		label_3.setBounds(0, 0, 51, 55);
		PnlVolver.add(label_3);
		
		
		
		
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!controles_preCreate()) {
					try {
						if (departamentoActualizar!=null) {
							departamentoActualizar.setCodigo(Long.parseLong(txtCodigo.getText()));
							departamentoActualizar.setNombre(txtNombre.getText());
							if(!controles_postModify(departamentoActualizar)) {
								acutalizarDepartamento(departamentoActualizar);
								tablaDepartamento.setVisible(false);
								tablaDepartamento = cargarDepartamento();
								scroolTablaDepartamento.setViewportView(tablaDepartamento);
								tablaDepartamento.setVisible(true);
								txtNombre.setText("");
								txtCodigo.setText("");
								departamentoActualizar = null;
								txtCodigo.setEditable(true);
								filtrar();
							}
						}else {
							Departamento departamento = new Departamento();
							departamento.setCodigo(Long.parseLong(txtCodigo.getText()));
							departamento.setNombre(txtNombre.getText());			
							
							Zona zona1 = new Zona();
							zona1 = obtenerZonaPorID(zona.getId());
							zona1.asignarDepartamento(departamento);
							if (!controles_postCreate(departamento)) {
								crearDepartamento(zona1);
								tablaDepartamento.setVisible(false);
								tablaDepartamento = cargarDepartamento();
								scroolTablaDepartamento.setViewportView(tablaDepartamento);
								tablaDepartamento.setVisible(true);
								txtNombre.setText("");
								txtCodigo.setText("");
							}
	
						}	
					} catch (NamingException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

	}
	
	public void crearDepartamento(Zona zona) throws NamingException {
		ZonaBeanRemote zonaBeanRemote  = (ZonaBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/ZonaBean!com.services.ZonaBeanRemote");
		try {
			zonaBeanRemote.actualizar(zona);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public void eliminarDepartamento(Departamento departamento) throws NamingException {
		DepartamentoBeanRemote departamentoBeanRemote  = (DepartamentoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/DepartamentoBean!com.services.DepartamentoBeanRemote");
		try {
			departamentoBeanRemote.borrar(departamento.getId());
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public void acutalizarDepartamento(Departamento departamento) throws NamingException {
		DepartamentoBeanRemote departamentoBeanRemote  = (DepartamentoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/DepartamentoBean!com.services.DepartamentoBeanRemote");
		try {
			departamentoBeanRemote.actualizar(departamento);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public Zona obtenerZonaPorID(Long id) throws NamingException {
		ZonaBeanRemote zonaBeanRemote  = (ZonaBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/ZonaBean!com.services.ZonaBeanRemote");
		return zonaBeanRemote.obtenerporID(id);
	}
	
	
	private JTable cargarDepartamento() throws NamingException {
		zona= obtenerZonaPorID(zona.getId());
		List<Departamento> lista = zona.getDepartamentos();
		
		
		String[] nombreColumnas = {"ID", "Código", "Nombre" };

		Object[][] datos = new Object[lista.size()][3];
		int fila = 0;

		
		for (Departamento c : lista) {
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
		
		
		tablaDepartamento = new JTable(model);
		tablaDepartamento.setRowSelectionAllowed(true);
		tablaDepartamento.setColumnSelectionAllowed(false);
		tablaDepartamento.setBorder(null);
		tablaDepartamento.setBackground(new Color(255, 255, 255));
		tablaDepartamento.setForeground(new Color(153, 153, 153));
		tablaDepartamento.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 13));
		tablaDepartamento.setShowVerticalLines(false);
		tablaDepartamento.setAutoscrolls(true);
		tablaDepartamento.setSize(600, 600);
		Color color = new Color(144,238,144);
		tablaDepartamento.setSelectionBackground(color);
		
		return tablaDepartamento;

	}
	
	private void filtrar() {
		TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.tablaDepartamento.getModel());
		filtro.setRowFilter(RowFilter.regexFilter(this.txtfiltro.getText(), 1));
		this.tablaDepartamento.setRowSorter(filtro);

	}
	

	
	public Departamento obtenerPorID(Long id) throws NamingException {
		DepartamentoBeanRemote departamentoBeanRemote  = (DepartamentoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/DepartamentoBean!com.services.DepartamentoBeanRemote");
		return departamentoBeanRemote.obtenerporID(id);
	}
	
	public void crearDepartamento(Departamento departamento) throws NamingException {
		DepartamentoBeanRemote departamentoBeanRemote  = (DepartamentoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/DepartamentoBean!com.services.DepartamentoBeanRemote");
		try {
			departamentoBeanRemote.crear(departamento);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
	}
	
	public boolean controles_preCreate() {
		boolean error = false;
		String mensajeError ="";
		if (!txtCodigo.getText().isEmpty()) {
			if(!txtNombre.getText().isEmpty()) {
				error = false;
			}else {
				error = true;
				mensajeError = "El nombre del departamento es un campo obligatorio.";
			}
		}else {
			error = true;
			mensajeError = "El Código del departamento es un campo obligatorio.";
		}
		
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se pudo crear el departamento", JOptionPane.ERROR_MESSAGE);}
		return error;	
	}
	
	public boolean controles_postCreate(Departamento departamento) throws NamingException {
		boolean error = false;
		String mensajeError = "";
		DepartamentoBeanRemote departamentoBeanRemote  = (DepartamentoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/DepartamentoBean!com.services.DepartamentoBeanRemote");
		mensajeError = departamentoBeanRemote.controles_postCreate(departamento);
		if (!mensajeError.isEmpty()) {
			error = true;
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se pudo crear el departamento", JOptionPane.ERROR_MESSAGE);}
		return error;		
	}
	
	
	public boolean controles_postModify(Departamento departamento) throws NamingException {
		boolean error = false;
		String mensajeError = "";
		DepartamentoBeanRemote departamentoBeanRemote  = (DepartamentoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/DepartamentoBean!com.services.DepartamentoBeanRemote");
		mensajeError = departamentoBeanRemote.controles_postModify(departamento);
		if (!mensajeError.isEmpty()) {
			error = true;
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se pudo modificar el departamento", JOptionPane.ERROR_MESSAGE);}
		return error;		
	}
	
	public boolean controles_preDelete(Departamento departamento) throws NamingException {
		boolean error = false;
		String mensajeError = "";
		DepartamentoBeanRemote departamentoBeanRemote  = (DepartamentoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/DepartamentoBean!com.services.DepartamentoBeanRemote");
		mensajeError = departamentoBeanRemote.controles_preDelete(departamento);
		if (!mensajeError.isEmpty()) {
			error = true;
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se puede eliminar el departamento", JOptionPane.ERROR_MESSAGE);}
		return error;		
	}
	
	public void reportarError(String error) {
		JOptionPane.showMessageDialog(this, error);
	}
	
	
}



