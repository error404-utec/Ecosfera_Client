package com.frame;

import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.entities.Departamento;
import com.entities.Zona;
import com.exceptions.ServiciosException;
import com.services.DepartamentoBeanRemote;
import com.services.ZonaBeanRemote;


public class jpDep_Zona extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtCodigo;
	private Zona zona;


	private JTable tablaDepartamento;
	private Departamento departamentoActualizar = null;
	private JTextField txtfiltro;
	private JLabel lblZona = new JLabel("");
	
	public void setlblZona(String nombre) {
		this.lblZona.setText(nombre);
	}
	
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
	/**
	 * Create the panel.
	 * @throws NamingException 
	 */
	public jpDep_Zona(Zona zona) throws NamingException {
		this.zona = zona;
		setBounds(new Rectangle(295, 256, 650, 582));
		setBackground(new Color(255, 255, 255));
		
		JPanel pnlNew = new JPanel();
		pnlNew.setBounds(0, 8, 452, 179);
		pnlNew.setBackground(new Color(255, 255, 255));
		pnlNew.setForeground(new Color(255, 255, 255));
		
		JPanel pnltable = new JPanel();
		pnltable.setBounds(0, 200, 452, 259);
		pnltable.setBackground(new Color(255, 255, 255));
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);
		
		JPanel pnlOptions = new JPanel();
		pnlOptions.setBounds(0, 472, 452, 55);
		pnlOptions.setBackground(new Color(255, 255, 255));
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(new Color(46, 139, 87));
		lblNombre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setForeground(new Color(46, 139, 87));
		lblCodigo.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtCodigo.setColumns(10);
		
		JButton btnAgregar = new JButton("Aceptar");
		
		btnAgregar.setBackground(new Color(245, 255, 250));
		btnAgregar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnAgregar.setForeground(new Color(46, 139, 87));
		
		
		lblZona.setForeground(new Color(46, 139, 87));
		lblZona.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		GroupLayout gl_pnlNew = new GroupLayout(pnlNew);
		gl_pnlNew.setHorizontalGroup(
			gl_pnlNew.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlNew.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlNew.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlNew.createSequentialGroup()
							.addGroup(gl_pnlNew.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAgregar, Alignment.TRAILING)
								.addGroup(Alignment.TRAILING, gl_pnlNew.createSequentialGroup()
									.addComponent(lblCodigo)
									.addGap(383))
								.addGroup(Alignment.TRAILING, gl_pnlNew.createSequentialGroup()
									.addGap(10)
									.addComponent(txtCodigo, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, gl_pnlNew.createSequentialGroup()
									.addGap(10)
									.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblZona, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
							.addGap(25))
						.addComponent(lblNombre))
					.addContainerGap())
		);
		gl_pnlNew.setVerticalGroup(
			gl_pnlNew.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlNew.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlNew.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlNew.createSequentialGroup()
							.addComponent(lblCodigo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNombre)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAgregar))
						.addComponent(lblZona, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
		);
		pnlNew.setLayout(gl_pnlNew);
		
		JScrollPane scroolTablaDepartamento = new JScrollPane();
		
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
		scroolTablaZonas.getVerticalScrollBar().setUI(new EcosferaScrollBar());*/
		scroolTablaDepartamento.addMouseListener(new MouseAdapter() {
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
		
		JLabel lblfiltro = new JLabel("C\u00F3digo");
		lblfiltro.setForeground(new Color(46, 139, 87));
		lblfiltro.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));

		GroupLayout gl_pnltable = new GroupLayout(pnltable);
		gl_pnltable.setHorizontalGroup(
			gl_pnltable.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnltable.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnltable.createParallelGroup(Alignment.LEADING)
						.addComponent(scroolTablaDepartamento, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
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
					.addComponent(scroolTablaDepartamento, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(107))
		);
		
		
		

		this.tablaDepartamento = this.cargarDepartamento();
		scroolTablaDepartamento.setViewportView(tablaDepartamento);
				
		pnltable.setLayout(gl_pnltable);
		
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
				Departamento departamentoEliminar = null;
				if (tablaDepartamento.getSelectedRow() > -1) {
					Long id = (Long) tablaDepartamento.getValueAt(tablaDepartamento.getSelectedRow(), 0);
					try {
						departamentoEliminar = obtenerPorID(id);
						eliminarDepartamento(departamentoEliminar);;
						tablaDepartamento.setVisible(false);
						tablaDepartamento = cargarDepartamento();
						scroolTablaDepartamento.setViewportView(tablaDepartamento);
						tablaDepartamento.setVisible(true);
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
		add(pnltable);
		add(pnlOptions);
		
		
		
		
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (departamentoActualizar!=null) {
					try {
						departamentoActualizar.setCodigo(Long.parseLong(txtCodigo.getText()));
						departamentoActualizar.setNombre(txtNombre.getText());
						acutalizarDepartamento(departamentoActualizar);
						tablaDepartamento.setVisible(false);
						tablaDepartamento = cargarDepartamento();
						scroolTablaDepartamento.setViewportView(tablaDepartamento);
						tablaDepartamento.setVisible(true);
						txtNombre.setText("");
						txtCodigo.setText("");
						departamentoActualizar = null;
						filtrar();
					} catch (NamingException e1) {
						e1.printStackTrace();
					}
				}else {
					Departamento departamento = new Departamento();
					departamento.setCodigo(Long.parseLong(txtCodigo.getText()));
					departamento.setNombre(txtNombre.getText());			
					try {
						Zona zona1 = new Zona();
						zona1 = obtenerZonaPorID(zona.getId());
						zona1.asignarDepartamento(departamento);
						crearDepartamento(zona1);
						tablaDepartamento.setVisible(false);
						tablaDepartamento = cargarDepartamento();
						scroolTablaDepartamento.setViewportView(tablaDepartamento);
						tablaDepartamento.setVisible(true);
						txtNombre.setText("");
						txtCodigo.setText("");
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
}



