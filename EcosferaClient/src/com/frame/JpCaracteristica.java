package com.frame;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.entities.Caracteristica;
import com.entities.Departamento;
import com.entities.TipoObservacion;
import com.entities.Zona;
import com.exceptions.ServiciosException;
import com.framework.TipoDatos;
import com.services.CaracteristicasBeanRemote;
import com.services.DepartamentoBeanRemote;
import com.services.TipoObservacionBeanRemote;
import com.services.ZonaBeanRemote;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JpCaracteristica extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private Caracteristica caracteristicaActualizar = null;
	private JTable tablaTiposObservaciones;

	private JTable tablaTipoObservacion = new JTable();


	/**
	 * Create the panel.
	 * @throws NamingException 
	 */
	public JpCaracteristica() throws NamingException {
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 430, 289);
		add(panel);
		panel.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(20, 31, 86, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(20, 87, 86, 20);
		panel.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 46, 14);
		panel.add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setBounds(10, 62, 96, 14);
		panel.add(lblDescripcion);
		
		JComboBox<String> cbbTipoDato = new JComboBox<String>();
		cbbTipoDato.setBounds(20, 139, 86, 20);
		panel.add(cbbTipoDato);
		cbbTipoDato.addItem("uno");
		cbbTipoDato.addItem("dos");
		cbbTipoDato.addItem("tres");
		
				
		
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				//-----------------------
				
				if (caracteristicaActualizar!=null) {
					System.out.println("coso choto");
					try {
						caracteristicaActualizar.setNombre(txtNombre.getText());
						caracteristicaActualizar.setDescripcion(txtDescripcion.getText());
						caracteristicaActualizar.setTipoDato((TipoDatos) cbbTipoDato.getSelectedItem());
						actualizarCaracteristica(caracteristicaActualizar);
						//tablaDepartamento.setVisible(false);
						//tablaDepartamento = cargarDepartamento();
						//scroolTablaDepartamento.setViewportView(tablaDepartamento);
						//tablaDepartamento.setVisible(true);
						txtNombre.setText("");
						txtDescripcion.setText("");
						
						
						System.out.println("antes que nada entra aca");
						
						
						caracteristicaActualizar = null;
						//filtrar();
					} catch (NamingException e1) {
						e1.printStackTrace();
					}
				}else {
					
					System.out.println("Coso choto caca");
					Caracteristica caracteristica = new Caracteristica();
					caracteristica.setNombre(txtNombre.getText());
					caracteristica.setDescripcion(txtDescripcion.getText());	
					caracteristicaActualizar.setTipoDato((TipoDatos) cbbTipoDato.getSelectedItem());
					
					//caracteristica.setTipoDato(obtenerTipoObservacionPorID((String)cbbTipoDato.getSelectedItem()));
					try {
						TipoObservacion tipoObservacion = new TipoObservacion();
						tipoObservacion = obtenerTipoObservacionPorID(tipoObservacion.getId());
						tipoObservacion.asignarCaracteristica(caracteristica);
						crearCaracteristica(tipoObservacion);
						//tablaDepartamento.setVisible(false);
						//tablaDepartamento = cargarCaracteristica();
						//scroolTablaDepartamento.setViewportView(tablaDepartamento);
						//tablaDepartamento.setVisible(true);
						txtNombre.setText("");
						txtDescripcion.setText("");
						
						System.out.println("vamos a probar que entre por aca ");
						
					} catch (NamingException e1) {
						e1.printStackTrace();
					}
				}

				
				
			}
		});
		
		btnAgregar.setBounds(290, 235, 89, 23);
		panel.add(btnAgregar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(161, 22, 248, 108);
		panel.add(scrollPane);
		
		tablaTiposObservaciones = new JTable();
		tablaTipoObservacion = cargarTiposObservaciones();
		
		scrollPane.setColumnHeaderView(tablaTiposObservaciones);
		scrollPane.setViewportView(tablaTipoObservacion);
		
	}
	
	
	public void actualizarCaracteristica(Caracteristica caracteristica) throws NamingException {
		CaracteristicasBeanRemote caracteristicasBeanRemote  = (CaracteristicasBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/CaracteristicasBean!com.services.CaracteristicasBeanRemote");
		try {
			caracteristicasBeanRemote.actualizar(caracteristica);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public TipoObservacion obtenerTipoObservacionPorID(Long id) throws NamingException {
		TipoObservacionBeanRemote tipoObservacionBeanRemote  = (TipoObservacionBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/TipoObservacionBean!com.services.TipoObservacionBeanRemote");
		return tipoObservacionBeanRemote.obtenerporID(id);
	}
	
	public void crearCaracteristica(TipoObservacion tipoObservacion) throws NamingException {
		TipoObservacionBeanRemote tipoObservacionBeanRemote  = (TipoObservacionBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/TipoObservacionBean!com.services.TipoObservacionBeanRemote");
		try {
			tipoObservacionBeanRemote.actualizar(tipoObservacion);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public void eliminarCaracteristica(Caracteristica caracteristica) throws NamingException {
		CaracteristicasBeanRemote caracteristicaBeanRemote  = (CaracteristicasBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/CaracteristicasBean!com.services.CaracteristicasBeanRemote");
		try {
			caracteristicaBeanRemote.borrar(caracteristica.getId());
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	//--------------------------------------------------------------
	
			public void CargarComboBox(){
				// Creacion del JTextField
				JTextField tf = new JTextField(20);
				
				// Creacion del JComboBox y añadir los items.
				JComboBox<?> cbbTipoDato = new JComboBox<Object>();
				
				
				// Accion a realizar cuando el JComboBox cambia de item seleccionado.
				cbbTipoDato.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						tf.setText(cbbTipoDato.getSelectedItem().toString());
					}
				});

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
