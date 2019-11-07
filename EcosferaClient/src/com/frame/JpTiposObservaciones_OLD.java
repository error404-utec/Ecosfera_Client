package com.frame;

import java.awt.Color;
import java.awt.Font;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
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
import com.entities.TipoObservacion;
import com.exceptions.ServiciosException;
import com.services.TipoObservacionBeanRemote;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class JpTiposObservaciones_OLD extends JPanel {
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
	public JpTiposObservaciones_OLD() throws NamingException {
		setBackground(Color.WHITE);
		
		JPanel pnlNew = new JPanel();
		pnlNew.setBounds(0, 0, 449, 235);
		pnlNew.setBackground(Color.WHITE);
		
		JPanel pnlTable = new JPanel();
		pnlTable.setBounds(0, 234, 449, 245);
		pnlTable.setBackground(Color.WHITE);
		
		JLabel lblFiltro = new JLabel("Filtro");
		lblFiltro.setBounds(12, 13, 33, 20);
		lblFiltro.setForeground(new Color(46, 139, 87));
		lblFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtFiltro = new JTextField();
		txtFiltro.setBounds(22, 40, 410, 22);
		txtFiltro.setColumns(10);
		
		JScrollPane scrollTablaTipoObservacion = new JScrollPane();
		scrollTablaTipoObservacion.setBounds(12, 74, 420, 151);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(22, 40, 404, 22);
		txtNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 13, 51, 20);
		lblNombre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblNombre.setForeground(new Color(46, 139, 87));
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(22, 100, 404, 22);
		txtDescripcion.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setBounds(12, 73, 79, 20);
		lblDescripcion.setForeground(new Color(46, 139, 87));
		lblDescripcion.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtTelEmergencia = new JTextField();
		txtTelEmergencia.setBounds(22, 160, 404, 22);
		txtTelEmergencia.setColumns(10);
		
		JLabel lblTelfonoDeEmergencia = new JLabel("Tel\u00E9fono de Emergencia");
		lblTelfonoDeEmergencia.setBounds(12, 133, 150, 20);
		lblTelfonoDeEmergencia.setForeground(new Color(46, 139, 87));
		lblTelfonoDeEmergencia.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		//--------------- Agregando tipo de observacion --------------------------
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(351, 200, 86, 27);
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
						crearTipoObservacion(tipoObservacion);
						tablaTipoObservacion.setVisible(false);
						tablaTipoObservacion = cargarTiposObservaciones();
						scrollTablaTipoObservacion.setViewportView(tablaTipoObservacion);
						tablaTipoObservacion.setVisible(true);
						txtNombre.setText("");
						txtDescripcion.setText("");
						txtTelEmergencia.setText("");
					} catch (NamingException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		setLayout(null);
		
		btnAceptar.setForeground(new Color(46, 139, 87));
		btnAceptar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		
		JPanel Close_Panel = new JPanel();
		Close_Panel.setBounds(417, 13, 20, 20);
		Close_Panel.setForeground(Color.WHITE);
		Close_Panel.setBackground(Color.WHITE);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtDescripcion.setText("");
				txtNombre.setText("");
				txtTelEmergencia.setText("");
				observacionActualizar = null;
			}
		});
		btnCancelar.setBounds(248, 200, 91, 27);
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
		pnlNew.add(Close_Panel);
		add(pnlTable);
		pnlTable.setLayout(null);
		pnlTable.add(lblFiltro);
		pnlTable.add(scrollTablaTipoObservacion);

		this.tablaTipoObservacion = this.cargarTiposObservaciones();
		scrollTablaTipoObservacion.setViewportView(tablaTipoObservacion);
		
		JPanel pnlOptions = new JPanel();
		pnlOptions.setBackground(Color.WHITE);
		pnlOptions.setBounds(0, 492, 449, 55);
		add(pnlOptions);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				TipoObservacion tipoObservacionEliminar = null;
				if (tablaTipoObservacion.getSelectedRow() > -1) {
					Long id = (Long) tablaTipoObservacion.getValueAt(tablaTipoObservacion.getSelectedRow(), 0);
					try {
						tipoObservacionEliminar = obtenerTipoObservacionPorID(id);
						eliminarTipoObservacion(tipoObservacionEliminar);
						tablaTipoObservacion.setVisible(false);
						tablaTipoObservacion = cargarTiposObservaciones();
						scrollTablaTipoObservacion.setViewportView(tablaTipoObservacion);
						tablaTipoObservacion.setVisible(true);
						filtrar();
					} catch (NamingException ev) {
						ev.printStackTrace();
					}
				}

			}
		});
		btnEliminar.setForeground(new Color(46, 139, 87));
		btnEliminar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnEliminar.setBackground(new Color(245, 255, 250));
		
		JButton btnModificarf = new JButton("Modificar");
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
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnModificarf.setForeground(new Color(46, 139, 87));
		btnModificarf.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnModificarf.setBackground(new Color(245, 255, 250));
		GroupLayout gl_pnlOptions = new GroupLayout(pnlOptions);
		gl_pnlOptions.setHorizontalGroup(
			gl_pnlOptions.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 452, Short.MAX_VALUE)
				.addGroup(gl_pnlOptions.createSequentialGroup()
					.addContainerGap(247, Short.MAX_VALUE)
					.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnModificarf)
					.addGap(12))
		);
		gl_pnlOptions.setVerticalGroup(
			gl_pnlOptions.createParallelGroup(Alignment.LEADING)
				.addGap(0, 55, Short.MAX_VALUE)
				.addGroup(gl_pnlOptions.createSequentialGroup()
					.addGap(13)
					.addGroup(gl_pnlOptions.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlOptions.createSequentialGroup()
							.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_pnlOptions.createSequentialGroup()
							.addComponent(btnModificarf, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(13))))
		);
		pnlOptions.setLayout(gl_pnlOptions);
		
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
}

