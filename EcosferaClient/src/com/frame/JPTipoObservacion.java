package com.frame;

import javax.swing.JPanel;
import java.awt.Rectangle;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.entities.Caracteristica;
import com.entities.Departamento;
import com.entities.Permiso;
import com.entities.TipoDocumento;
import com.entities.TipoObservacion;
import com.entities.Zona;
import com.exceptions.ServiciosException;
import com.services.DepartamentoBeanRemote;
import com.services.PermisoBeanRemote;
import com.services.TipoDocumentoBeanRemote;
import com.services.TipoObservacionBeanRemote;
import com.services.ZonaBeanRemote;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class JPTipoObservacion extends JPanel {
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtTelEmergencia;
	private JTextField txtFiltro;
	private JTable tablaTipoObservacion = new JTable();
	
	private TipoObservacion observacionActualizar = null;
	

	/**
	 * Create the panel.
	 */
	public JPTipoObservacion() {
		
		JPanel pnlNew = new JPanel();
		pnlNew.setBackground(Color.WHITE);
		
		JPanel pnlTable = new JPanel();
		pnlTable.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(pnlNew, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(pnlTable, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(pnlNew, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlTable, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblFiltro = new JLabel("Filtro");
		lblFiltro.setForeground(new Color(46, 139, 87));
		lblFiltro.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtFiltro = new JTextField();
		txtFiltro.setColumns(10);
		
		JScrollPane scrollTablaTipoObservacion = new JScrollPane();
		GroupLayout gl_pnlTable = new GroupLayout(pnlTable);
		gl_pnlTable.setHorizontalGroup(
			gl_pnlTable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTable.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlTable.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFiltro)
						.addGroup(gl_pnlTable.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_pnlTable.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollTablaTipoObservacion)
								.addComponent(txtFiltro, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		gl_pnlTable.setVerticalGroup(
			gl_pnlTable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTable.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFiltro)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollTablaTipoObservacion, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
		);
		scrollTablaTipoObservacion.setColumnHeaderView(tablaTipoObservacion);
		pnlTable.setLayout(gl_pnlTable);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblNombre.setForeground(new Color(46, 139, 87));
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setForeground(new Color(46, 139, 87));
		lblDescripcion.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtTelEmergencia = new JTextField();
		txtTelEmergencia.setColumns(10);
		
		JLabel lblTelfonoDeEmergencia = new JLabel("Tel\u00E9fono de Emergencia");
		lblTelfonoDeEmergencia.setForeground(new Color(46, 139, 87));
		lblTelfonoDeEmergencia.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		//--------------- Agregando tipo de observacion --------------------------
		JButton btnAceptar = new JButton("Aceptar");
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
		
		btnAceptar.setForeground(new Color(46, 139, 87));
		btnAceptar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		
		JPanel Close_Panel = new JPanel();
		Close_Panel.setForeground(Color.WHITE);
		Close_Panel.setBackground(Color.WHITE);
		GroupLayout gl_pnlNew = new GroupLayout(pnlNew);
		gl_pnlNew.setHorizontalGroup(
			gl_pnlNew.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlNew.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlNew.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAceptar, Alignment.TRAILING)
						.addComponent(lblDescripcion, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTelfonoDeEmergencia)
						.addGroup(Alignment.TRAILING, gl_pnlNew.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_pnlNew.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
								.addComponent(txtDescripcion, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
								.addComponent(txtTelEmergencia, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
							.addGap(11))
						.addGroup(gl_pnlNew.createSequentialGroup()
							.addComponent(lblNombre)
							.addPreferredGap(ComponentPlacement.RELATED, 358, Short.MAX_VALUE)
							.addComponent(Close_Panel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_pnlNew.setVerticalGroup(
			gl_pnlNew.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlNew.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlNew.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre)
						.addComponent(Close_Panel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDescripcion)
					.addGap(5)
					.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblTelfonoDeEmergencia)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtTelEmergencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAceptar)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		pnlNew.setLayout(gl_pnlNew);
		setLayout(groupLayout);

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
		String[] nombreColumnas = {"ID", "Nombre", "Descripción", "Tel_Emergencia" };

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
		
		return tablaTipoObservacion;


	
	}


	private List<TipoObservacion> listarTipoObservacion() throws NamingException  {
		TipoObservacionBeanRemote tipoObservacionBean = (TipoObservacionBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/TipoObservacionBean!com.services.TipoObservacionBeanRemote");
		return tipoObservacionBean.obtenerTodos();
	
	}



}