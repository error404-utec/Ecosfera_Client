package com.frame;

import java.awt.Color;
import java.awt.Font;

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
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.entities.Departamento;
import com.entities.TipoDocumento;
import com.entities.TipoObservacion;
import com.exceptions.ServiciosException;
import com.services.DepartamentoBeanRemote;
import com.services.TipoObservacionBeanRemote;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class JpTiposObservaciones extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtTelEmergencia;
	private JTextField txtFiltro;
	private JTable tablaTipoObservacion = new JTable();
	
	private TipoObservacion observacionActualizar = null;
	

	/**
	 * Create the panel.
	 * @throws NamingException 
	 */
	public JpTiposObservaciones() throws NamingException {
		setBackground(Color.WHITE);
		
		JPanel pnlNew = new JPanel();
		pnlNew.setBounds(110, 0, 452, 165);
		pnlNew.setBackground(new Color(255, 255, 255));
		pnlNew.setForeground(new Color(255, 255, 255));
		
		JPanel pnltable = new JPanel();
		pnltable.setBounds(110, 178, 452, 291);
		pnltable.setBackground(new Color(255, 255, 255));
		
		JLabel lblFiltro = new JLabel("Filtro");
		lblFiltro.setBounds(12, 18, 109, 20);
		lblFiltro.setForeground(new Color(46, 139, 87));
		lblFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtFiltro = new JTextField();
		txtFiltro.setBounds(78, 17, 362, 24);
		txtFiltro.setColumns(10);
		txtFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtrar();
			}
		});
		
		JScrollPane scrollTablaTipoObservacion = new JScrollPane();
		scrollTablaTipoObservacion.setBounds(12, 78, 428, 200);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(166, 12, 274, 24);
		txtNombre.setColumns(10);
		
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtNombre.setText(txtNombre.getText().toUpperCase());
			}
		});
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 13, 51, 20);
		lblNombre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblNombre.setForeground(new Color(46, 139, 87));
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(166, 49, 274, 22);
		txtDescripcion.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setBounds(12, 49, 79, 20);
		lblDescripcion.setForeground(new Color(46, 139, 87));
		lblDescripcion.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtTelEmergencia = new JTextField();
		txtTelEmergencia.setBounds(166, 82, 271, 22);
		txtTelEmergencia.setColumns(10);
		
		txtTelEmergencia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if (!(Character.isDigit(c)||
				(c == KeyEvent.VK_BACK_SPACE) ||
				(c == KeyEvent.VK_DELETE))) {
				getToolkit().beep();
				arg0.consume();
				}
			}
		});
		
		JLabel lblTelfonoDeEmergencia = new JLabel("Tel\u00E9fono de Emergencia");
		lblTelfonoDeEmergencia.setBounds(12, 82, 150, 20);
		lblTelfonoDeEmergencia.setForeground(new Color(46, 139, 87));
		lblTelfonoDeEmergencia.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		//--------------- Agregando tipo de observacion --------------------------
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(240, 125, 96, 27);
		btnAceptar.setBackground(Color.WHITE);
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (observacionActualizar!=null) {
					try {
						observacionActualizar.setNombre(txtNombre.getText());
						observacionActualizar.setDescripcion(txtDescripcion.getText());
						observacionActualizar.setTelEmergencia(txtTelEmergencia.getText());
						
						crearoModificarTipoObservacion(observacionActualizar);
						tablaTipoObservacion.setVisible(false);
						tablaTipoObservacion= cargarTiposObservaciones();
						scrollTablaTipoObservacion.setViewportView(tablaTipoObservacion);
						tablaTipoObservacion.setVisible(true);
						txtNombre.setText("");
						txtDescripcion.setText("");
						txtTelEmergencia.setText("");
						txtNombre.setEditable(true);
						observacionActualizar = null;
						filtrar();
						
					} catch (NamingException e1) {
						e1.printStackTrace();
					}
				}else {
					
					
					TipoObservacion tipoObservacion = new TipoObservacion();
					tipoObservacion.setNombre(txtNombre.getText());
					tipoObservacion.setDescripcion(txtDescripcion.getText());
					tipoObservacion.setTelEmergencia(txtTelEmergencia.getText());
					
					try {
						if (!controles_postCreate(tipoObservacion)) {
							crearTipoObservacion(tipoObservacion);
							tablaTipoObservacion.setVisible(false);
							tablaTipoObservacion = cargarTiposObservaciones();
							scrollTablaTipoObservacion.setViewportView(tablaTipoObservacion);
							tablaTipoObservacion.setVisible(true);
							txtNombre.setText("");
							txtDescripcion.setText("");
							txtTelEmergencia.setText("");
						}
					} catch (NamingException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		setLayout(null);
		
		btnAceptar.setForeground(new Color(46, 139, 87));
		btnAceptar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtDescripcion.setText("");
				txtNombre.setText("");
				txtTelEmergencia.setText("");
				txtNombre.setEditable(true);
				observacionActualizar = null;
			}
		});
		btnCancelar.setBounds(344, 125, 96, 27);
		btnCancelar.setForeground(new Color(46, 139, 87));
		btnCancelar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnCancelar.setBackground(Color.WHITE);
		add(pnlNew);
		pnlNew.setLayout(null);
		pnlNew.add(btnCancelar);
		pnlNew.add(btnAceptar);
		pnlNew.add(lblDescripcion);
		pnlNew.add(lblTelfonoDeEmergencia);
		pnlNew.add(txtNombre);
		pnlNew.add(txtDescripcion);
		pnlNew.add(txtTelEmergencia);
		pnlNew.add(lblNombre);
		add(pnltable);
		pnltable.setLayout(null);
		pnltable.add(lblFiltro);
		pnltable.add(txtFiltro);
		pnltable.add(scrollTablaTipoObservacion);

		this.tablaTipoObservacion = this.cargarTiposObservaciones();
		scrollTablaTipoObservacion.setViewportView(tablaTipoObservacion);
		
		JPanel pnlOptions = new JPanel();
		pnlOptions.setBounds(110, 478, 452, 55);
		pnlOptions.setBackground(new Color(255, 255, 255));
		add(pnlOptions);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(235, 0, 93, 29);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				TipoObservacion tipoObservacionEliminar = null;
				if (tablaTipoObservacion.getSelectedRow() > -1) {
					Long id = (Long) tablaTipoObservacion.getValueAt(tablaTipoObservacion.getSelectedRow(), 0);
					try {
						tipoObservacionEliminar = obtenerTipoObservacionPorID(id);
						if(!controles_preDelete(tipoObservacionEliminar)) {
							if (solicitarConfirmaciones(tipoObservacionEliminar)) {
								eliminarTipoObservacion(tipoObservacionEliminar);
								tablaTipoObservacion.setVisible(false);
								tablaTipoObservacion = cargarTiposObservaciones();
								scrollTablaTipoObservacion.setViewportView(tablaTipoObservacion);
								tablaTipoObservacion.setVisible(true);
								filtrar();
							}
						}
					} catch (NamingException ev) {
						ev.printStackTrace();
					}
				}else {
					reportarError("Debe seleccionar un tipo de observacion");
				}
				

			}
		});
		btnEliminar.setForeground(new Color(46, 139, 87));
		btnEliminar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnEliminar.setBackground(new Color(245, 255, 250));
		
		JButton btnModificarf = new JButton("Modificar");
		btnModificarf.setBounds(346, 0, 99, 29);
		btnModificarf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				observacionActualizar = null;
				if (tablaTipoObservacion.getSelectedRow() > -1) {
					Long id = (Long) tablaTipoObservacion.getValueAt(tablaTipoObservacion.getSelectedRow(), 0);
					try {
						observacionActualizar = obtenerTipoObservacionPorID(id);
						txtDescripcion.setText(observacionActualizar.getDescripcion());
						txtTelEmergencia.setText(observacionActualizar.getTelEmergencia());
						txtNombre.setText(observacionActualizar.getNombre());
						txtNombre.setEditable(false);
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}else {
					reportarError("Debe seleccionar un tipo de observacion");
				}
			}
		});
		btnModificarf.setForeground(new Color(46, 139, 87));
		btnModificarf.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnModificarf.setBackground(new Color(245, 255, 250));
		pnlOptions.setLayout(null);
		pnlOptions.add(btnEliminar);
		pnlOptions.add(btnModificarf);
		
		//scrollTablaTipoObservacion.setViewportView(tablaTipoObservacion);
		//pnlTable.add(txtFiltro);
		//this.tablaTipoObservacion = cargarTiposObservaciones();

	}
	
	public void crearoModificarTipoObservacion (TipoObservacion tipoObservacion) throws NamingException {
		TipoObservacionBeanRemote tipoObservacionBeanRemote = (TipoObservacionBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/TipoObservacionBean!com.services.TipoObservacionBeanRemote");
		try {
			tipoObservacionBeanRemote.actualizar(tipoObservacion);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
		
	public void crearTipoObservacion(TipoObservacion tipoObservacion) throws NamingException {
		TipoObservacionBeanRemote tipoObservacionBeanRemote = (TipoObservacionBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/TipoObservacionBean!com.services.TipoObservacionBeanRemote");
		try {
			tipoObservacionBeanRemote.crear(tipoObservacion);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public void eliminarTipoObservacion(TipoObservacion tipoObservacion) throws NamingException {
		TipoObservacionBeanRemote TipoObservacionBean = (TipoObservacionBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/TipoObservacionBean!com.services.TipoObservacionBeanRemote");

		try {
			TipoObservacionBean.borrar(tipoObservacion.getId());
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public void actualizarTipoObservacion(TipoObservacion tipoObservacion) throws NamingException {
		TipoObservacionBeanRemote TipoObservacionBean = (TipoObservacionBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/TipoObservacionBean!com.services.TipoObservacionBeanRemote");
		try {
			TipoObservacionBean.actualizar(tipoObservacion);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public TipoObservacion obtenerTipoObservacionPorID(Long id) throws NamingException {
		TipoObservacionBeanRemote TipoObservacionBean = (TipoObservacionBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/TipoObservacionBean!com.services.TipoObservacionBeanRemote");
		return TipoObservacionBean.obtenerporID(id);
	}
	
	
	private void filtrar() {
		TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.tablaTipoObservacion.getModel());
		filtro.setRowFilter(RowFilter.regexFilter(this.txtFiltro.getText(), 1));
		this.tablaTipoObservacion.setRowSorter(filtro);

	}
	
private JTable cargarTiposObservaciones() throws NamingException {
		
		//List<TipoObservacion> lista = listaTipoObservacion();
		List<TipoObservacion> lista = listarTipoObservacion();
		String[] nombreColumnas = {"ID", "Nombre", "Descripción", "Tel Emergencia" };

		Object[][] datos = new Object[lista.size()][4];
		int fila = 0;

		
		for (TipoObservacion o : lista) {
			System.out.println(o.getId());
			datos[fila][0] = o.getId();
			datos[fila][1] = o.getNombre();
			datos[fila][2] = o.getDescripcion();
			datos[fila][3] = o.getTelEmergencia();
			
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
		
		tablaTipoObservacion = new JTable(model);
		tablaTipoObservacion.setRowSelectionAllowed(true);
		tablaTipoObservacion.setColumnSelectionAllowed(false);
		tablaTipoObservacion.setBorder(null);
		tablaTipoObservacion.setBackground(new Color(255, 255, 255));
		tablaTipoObservacion.setForeground(new Color(153, 153, 153));
		tablaTipoObservacion.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 13));
		tablaTipoObservacion.setShowVerticalLines(false);
		tablaTipoObservacion.setAutoscrolls(true);
		tablaTipoObservacion.setSize(600, 600);
		Color color = new Color(144,238,144);
		tablaTipoObservacion.setSelectionBackground(color);
		
		return tablaTipoObservacion;
	
	}


	private List<TipoObservacion> listarTipoObservacion() throws NamingException  {
		TipoObservacionBeanRemote tipoObservacionBean = (TipoObservacionBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/TipoObservacionBean!com.services.TipoObservacionBeanRemote");
		return tipoObservacionBean.obtenerTodos();
	
	}
	
	
	public boolean controles_preCreate() {
		boolean error = false;
		String mensajeError ="";
		if (!txtDescripcion.getText().isEmpty()) {
			if(!txtNombre.getText().isEmpty()) {
				if (!txtTelEmergencia.getText().isEmpty()) {
					error = false;
				}else {
					error = true;
					mensajeError = "El telefono de emeregencia del tipo de observacion es un campo obligatorio.";
				}
			}else {
				error = true;
				mensajeError = "El nombre del tipo de observacion es un campo obligatorio.";
			}
		}else {
			error = true;
			mensajeError = "La descripcion del tipo de observacion  es un campo obligatorio.";
		}
		
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se pudo crear el departamento", JOptionPane.ERROR_MESSAGE);}
		return error;	
	}
	
	public boolean controles_postCreate(TipoObservacion tipoObservacion) throws NamingException {
		boolean error = false;
		String mensajeError = "";
		TipoObservacionBeanRemote tipoObservacionBean = (TipoObservacionBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/TipoObservacionBean!com.services.TipoObservacionBeanRemote");
		mensajeError = tipoObservacionBean.controles_postCreate(tipoObservacion);
		if (!mensajeError.isEmpty()) {
			error = true;
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se pudo crear el Tipo de Observacion", JOptionPane.ERROR_MESSAGE);}
		return error;		
	}
	
	
	public boolean controles_preDelete(TipoObservacion tipoObservacion) throws NamingException {
		boolean error = false;
		String mensajeError = "";
		TipoObservacionBeanRemote tipoObservacionBean = (TipoObservacionBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/TipoObservacionBean!com.services.TipoObservacionBeanRemote");
		mensajeError = tipoObservacionBean.controles_preDelete(tipoObservacion);
		if (!mensajeError.isEmpty()) {
			error = true;
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se puede eliminar el Tipo de Observacion", JOptionPane.ERROR_MESSAGE);}
		return error;		
	}
	
	public void reportarError(String error) {
		JOptionPane.showMessageDialog(this, error);
	}
	
	public boolean solicitarConfirmaciones(TipoObservacion tipoObservacion) {
		boolean confirmado = false;
		int i =JOptionPane.showConfirmDialog(this,"¿Realmente Desea eliminar el tipo de observacion "+ tipoObservacion.getNombre()+"?","Confirmar",JOptionPane.YES_NO_OPTION);
		if (i==0) {
			confirmado = true;
		}
		return confirmado;
	}
}

