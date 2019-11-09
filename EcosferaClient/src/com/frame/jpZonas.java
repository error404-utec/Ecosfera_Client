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
		JFRPrincipal.setlblTitulopanel("Mantenimiento Zonas");

		setBounds(new Rectangle(295, 256, 662, 609));
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
		txtNombre.setBounds(78, 88, 362, 24);
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtNombre.setText(txtNombre.getText().toUpperCase());
			}
		});
		txtNombre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(12, 52, 45, 20);
		lblCodigo.setForeground(new Color(46, 139, 87));
		lblCodigo.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(78, 51, 362, 24);
		txtCodigo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtCodigo.setColumns(10);
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
		
		JButton btnAgregar = new JButton("Aceptar");
		btnAgregar.setBounds(240, 125, 96, 27);
		
		btnAgregar.setBackground(new Color(245, 255, 250));
		btnAgregar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnAgregar.setForeground(new Color(46, 139, 87));
		
		JButton BtnCancelar = new JButton("Cancelar");
		BtnCancelar.setBounds(344, 125, 96, 27);
		BtnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				zonaActualizar = null;
				txtCodigo.setText("");
				txtNombre.setText("");
				txtCodigo.setEditable(true);
			}
		});
		BtnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		BtnCancelar.setForeground(new Color(46, 139, 87));
		BtnCancelar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		BtnCancelar.setBackground(new Color(245, 255, 250));
		
		JScrollPane scroolTablaZonas = new JScrollPane();
		scroolTablaZonas.setBounds(12, 78, 428, 200);
		
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
		txtfiltro.setBounds(78, 17, 362, 24);
		txtfiltro.addKeyListener(new KeyAdapter() {
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
		
		
		

		this.tablaZonas = this.cargarPermisos();
		scroolTablaZonas.setViewportView(tablaZonas);
		
		JButton btnModificat = new JButton("Modificar");
		btnModificat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				zonaActualizar = null;
				if (tablaZonas.getSelectedRow() > -1) {
					Long id = (Long) tablaZonas.getValueAt(tablaZonas.getSelectedRow(), 0);
					try {
						zonaActualizar = obtenerPorID(id);
						txtNombre.setText(zonaActualizar.getNombre());
						txtCodigo.setText(Long.toString(zonaActualizar.getCodigo()));
						txtCodigo.setEditable(false);
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}else {
					reportarError("Debe seleccionar una zona");
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
						
						if(!controles_preDelete(zonaEliminar)) {
							eliminarZona(zonaEliminar);;
							tablaZonas.setVisible(false);
							tablaZonas = cargarPermisos();
							scroolTablaZonas.setViewportView(tablaZonas);
							tablaZonas.setVisible(true);
							filtrar();
						}
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}else {
					reportarError("Debe seleccionar una zona");
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
						JFRPrincipal.LblNavegacion.setText("Inicio - Zonas - Departamentos");
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}else {
					reportarError("Debe seleccionar una zona");
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
		pnlNew.setLayout(null);
		pnlNew.add(lblCodigo);
		pnlNew.add(txtCodigo);
		pnlNew.add(txtNombre);
		pnlNew.add(lblNombre);
		pnlNew.add(BtnCancelar);
		pnlNew.add(btnAgregar);
		add(pnltable);
		pnltable.setLayout(null);
		pnltable.add(scroolTablaZonas);
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
		
		JLabel label = new JLabel("C\u00F3digo");
		panel_1.add(label);
		label.setForeground(new Color(46, 139, 87));
		label.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(296, 57, 144, 24);
		pnltable.add(panel_2);
		
		JLabel label_1 = new JLabel("Nombre");
		panel_2.add(label_1);
		label_1.setForeground(new Color(46, 139, 87));
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		add(pnlOptions);
		
		
		
		
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				if (!controles_preCreate()) {
					if (zonaActualizar!=null) {
						zonaActualizar.setCodigo(Long.parseLong(txtCodigo.getText()));
						zonaActualizar.setNombre(txtNombre.getText());
						if(!controles_postModify(zonaActualizar)) {
							crearoModificarZona(zonaActualizar);
							tablaZonas.setVisible(false);
							tablaZonas = cargarPermisos();
							scroolTablaZonas.setViewportView(tablaZonas);
							tablaZonas.setVisible(true);
							txtNombre.setText("");
							txtCodigo.setText("");
							txtCodigo.setEditable(true);
							zonaActualizar = null;
							filtrar();
						}
					}else {
						Zona zona = new Zona();
						zona.setCodigo(Long.parseLong(txtCodigo.getText()));
						zona.setNombre(txtNombre.getText());			
						if (!controles_postCreate(zona)) {
							crearZona(zona);
							tablaZonas.setVisible(false);
							tablaZonas = cargarPermisos();
							scroolTablaZonas.setViewportView(tablaZonas);
							tablaZonas.setVisible(true);
							txtNombre.setText("");
							txtCodigo.setText("");
						}
					}
				}
				} catch (NamingException e1) {
					e1.printStackTrace();
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
	
	public boolean controles_preCreate() {
		boolean error = false;
		String mensajeError ="";
		if (!txtCodigo.getText().isEmpty()) {
			if(!txtNombre.getText().isEmpty()) {
				error = false;
			}else {
				error = true;
				mensajeError = "El nombre de la zona es un campo obligatorio.";
			}
		}else {
			error = true;
			mensajeError = "El Código de la zona es un campo obligatorio.";
		}
		
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se pudo crear la zona", JOptionPane.ERROR_MESSAGE);}
		return error;	
	}
	
	public boolean controles_postCreate(Zona zona) throws NamingException {
		boolean error = false;
		String mensajeError = "";
		ZonaBeanRemote zonaBeanRemote  = (ZonaBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/ZonaBean!com.services.ZonaBeanRemote");
		mensajeError = zonaBeanRemote.controles_postCreate(zona);
		if (!mensajeError.isEmpty()) {
			error = true;
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se pudo crear la zona", JOptionPane.ERROR_MESSAGE);}
		return error;		
	}
	
	public boolean controles_postModify(Zona zona) throws NamingException {
		boolean error = false;
		String mensajeError = "";
		ZonaBeanRemote zonaBeanRemote  = (ZonaBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/ZonaBean!com.services.ZonaBeanRemote");
		mensajeError = zonaBeanRemote.controles_postModify(zona);
		if (!mensajeError.isEmpty()) {
			error = true;
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se pudo modificar la zona", JOptionPane.ERROR_MESSAGE);}
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



