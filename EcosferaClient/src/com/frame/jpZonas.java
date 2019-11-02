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

import com.entities.Zona;
import com.exceptions.ServiciosException;
import com.framework.EcosferaScrollBar;
import com.services.ZonaBeanRemote;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class jpZonas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtCodigo;



	private JTable tablaZonas;
	private Zona zonaActualizar = null;
	private JTextField txtfiltro;
	

	/**
	 * Create the panel.
	 * @throws NamingException 
	 */
	public jpZonas() throws NamingException {
		

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
		
		JPanel Close_Panel = new JPanel();
		Close_Panel.addMouseListener(new MouseAdapter() {
			
		});
		Close_Panel.setForeground(Color.WHITE);
		Close_Panel.setBackground(new Color(255, 255, 255));
		
		JButton BtnCancelar = new JButton("Cancelar");
		BtnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				zonaActualizar = null;
				txtCodigo.setText("");
				txtNombre.setText("");
			}
		});
		BtnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		BtnCancelar.setForeground(new Color(46, 139, 87));
		BtnCancelar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		BtnCancelar.setBackground(new Color(245, 255, 250));
		GroupLayout gl_pnlNew = new GroupLayout(pnlNew);
		gl_pnlNew.setHorizontalGroup(
			gl_pnlNew.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlNew.createSequentialGroup()
					.addGroup(gl_pnlNew.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlNew.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCodigo)
							.addPreferredGap(ComponentPlacement.RELATED, 363, Short.MAX_VALUE)
							.addComponent(Close_Panel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlNew.createSequentialGroup()
							.addGap(22)
							.addComponent(txtCodigo, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE))
						.addGroup(gl_pnlNew.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_pnlNew.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlNew.createSequentialGroup()
									.addGap(10)
									.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE))
								.addComponent(lblNombre)))
						.addGroup(gl_pnlNew.createSequentialGroup()
							.addContainerGap()
							.addComponent(BtnCancelar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAgregar)))
					.addContainerGap())
		);
		gl_pnlNew.setVerticalGroup(
			gl_pnlNew.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlNew.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlNew.createParallelGroup(Alignment.LEADING)
						.addComponent(Close_Panel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCodigo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNombre)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_pnlNew.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAgregar)
						.addComponent(BtnCancelar))
					.addContainerGap())
		);
		pnlNew.setLayout(gl_pnlNew);
		
		JScrollPane scroolTablaZonas = new JScrollPane();
		
		
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
		});
		
		txtfiltro = new JTextField();
		txtfiltro.addKeyListener(new KeyAdapter() {
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
						.addComponent(scroolTablaZonas, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
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
					.addComponent(scroolTablaZonas, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(107))
		);
		
		
		

		this.tablaZonas = this.cargarPermisos();
		scroolTablaZonas.setViewportView(tablaZonas);
				
		pnltable.setLayout(gl_pnltable);
		
		JButton btnModificat = new JButton("Modificar");
		btnModificat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				zonaActualizar = null;
				if (tablaZonas.getSelectedRow() > -1) {
					Long id = (Long) tablaZonas.getValueAt(tablaZonas.getSelectedRow(), 0);
					try {
						zonaActualizar = obtenerPorID(id);
						txtNombre.setText(Long.toString(zonaActualizar.getCodigo()));
						txtCodigo.setText(zonaActualizar.getNombre());
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
				Zona zonaEliminar = null;
				if (tablaZonas.getSelectedRow() > -1) {
					Long id = (Long) tablaZonas.getValueAt(tablaZonas.getSelectedRow(), 0);
					try {
						zonaEliminar = obtenerPorID(id);
						eliminarZona(zonaEliminar);;
						tablaZonas.setVisible(false);
						tablaZonas = cargarPermisos();
						scroolTablaZonas.setViewportView(tablaZonas);
						tablaZonas.setVisible(true);
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
		
		JButton btnDepartamentos = new JButton("Departamentos");
		btnDepartamentos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				JPanel jp;
				Zona zonaEliminar = null;
				if (tablaZonas.getSelectedRow() > -1) {
					Long id = (Long) tablaZonas.getValueAt(tablaZonas.getSelectedRow(), 0);
					try {
						
						zonaEliminar = obtenerPorID(id);
						jp = new jpDep_Zona(zonaEliminar);
						jp.setBounds(290, 238, 660, 600);
						jp.setVisible(true);
						jp.setLocation(12,12);
						JFRPrincipal.getIntance();
						JFRPrincipal.PnlWorkSpace.removeAll();
						JFRPrincipal.PnlWorkSpace.add(jp);
						JFRPrincipal.PnlWorkSpace.revalidate();
						JFRPrincipal.PnlWorkSpace.repaint();
						JFRPrincipal.LblNavegacion.setText("Inicio"+ " - " + "Departamentos");
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}

				
			}
		});
		btnDepartamentos.setForeground(new Color(46, 139, 87));
		btnDepartamentos.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnDepartamentos.setBackground(new Color(245, 255, 250));
		GroupLayout gl_pnlOptions = new GroupLayout(pnlOptions);
		gl_pnlOptions.setHorizontalGroup(
			gl_pnlOptions.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pnlOptions.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnDepartamentos)
					.addPreferredGap(ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
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
								.addComponent(btnDepartamentos, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
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
				
				if (zonaActualizar!=null) {
					try {
						zonaActualizar.setCodigo(Long.parseLong(txtCodigo.getText()));
						zonaActualizar.setNombre(txtNombre.getText());
						crearoModificarZona(zonaActualizar);
						tablaZonas.setVisible(false);
						tablaZonas = cargarPermisos();
						scroolTablaZonas.setViewportView(tablaZonas);
						tablaZonas.setVisible(true);
						txtNombre.setText("");
						txtCodigo.setText("");

						zonaActualizar = null;
						filtrar();
					} catch (NamingException e1) {
						e1.printStackTrace();
					}
				}else {
					Zona zona = new Zona();
					zona.setCodigo(Long.parseLong(txtCodigo.getText()));
					zona.setNombre(txtNombre.getText());			
					try {
						crearZona(zona);
						tablaZonas.setVisible(false);
						tablaZonas = cargarPermisos();
						scroolTablaZonas.setViewportView(tablaZonas);
						tablaZonas.setVisible(true);
						txtNombre.setText("");
						txtCodigo.setText("");
					} catch (NamingException e1) {
						e1.printStackTrace();
					}
				}
				
				
			}
		});

	}
	
	
	
	public void eliminarZona(Zona zona) throws NamingException {
		ZonaBeanRemote zonaBeanRemote  = (ZonaBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/ZonaBean!com.services.ZonaBeanRemote");
		try {
			zonaBeanRemote.borrar(zona.getId());
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public void crearoModificarZona(Zona zona) throws NamingException {
		ZonaBeanRemote zonaBeanRemote  = (ZonaBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/ZonaBean!com.services.ZonaBeanRemote");
		try {
			zonaBeanRemote.actualizar(zona);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	private JTable cargarPermisos() throws NamingException {
		
		List<Zona> lista = listarZona();

		String[] nombreColumnas = {"ID", "Código", "Nombre" };

		Object[][] datos = new Object[lista.size()][3];
		int fila = 0;

		
		for (Zona c : lista) {
			datos[fila][0] = c.getId();
			datos[fila][1] = c.getCodigo();
			datos[fila][2] = c.getNombre();
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
		
		
		tablaZonas = new JTable(model);
		tablaZonas.setRowSelectionAllowed(true);
		tablaZonas.setColumnSelectionAllowed(false);
		tablaZonas.setBorder(null);
		tablaZonas.setBackground(new Color(255, 255, 255));
		tablaZonas.setForeground(new Color(153, 153, 153));
		tablaZonas.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 13));
		tablaZonas.setShowVerticalLines(false);
		tablaZonas.setAutoscrolls(true);
		tablaZonas.setSize(600, 600);
		Color color = new Color(144,238,144);
		tablaZonas.setSelectionBackground(color);
		
		return tablaZonas;

	}
	
	private void filtrar() {
		TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.tablaZonas.getModel());
		filtro.setRowFilter(RowFilter.regexFilter(this.txtfiltro.getText(), 1));
		this.tablaZonas.setRowSorter(filtro);

	}
	
	public List<Zona> listarZona() throws NamingException {
		ZonaBeanRemote zonaBeanRemote  = (ZonaBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/ZonaBean!com.services.ZonaBeanRemote");
		return zonaBeanRemote.obtenerTodos();
	}
	
	public Zona obtenerPorID(Long id) throws NamingException {
		ZonaBeanRemote zonaBeanRemote  = (ZonaBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/ZonaBean!com.services.ZonaBeanRemote");
		return zonaBeanRemote.obtenerporID(id);
	}
	
	public void crearZona(Zona zona) throws NamingException {
		ZonaBeanRemote zonaBeanRemote  = (ZonaBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/ZonaBean!com.services.ZonaBeanRemote");
		try {
			zonaBeanRemote.crear(zona);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
}



