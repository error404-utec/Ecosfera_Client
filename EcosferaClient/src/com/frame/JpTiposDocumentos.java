package com.frame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import com.entities.TipoDocumento;
import com.exceptions.ServiciosException;
import com.framework.EcosferaScrollBar;
import com.services.TipoDocumentoBeanRemote;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JpTiposDocumentos extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtAbreviatura;


	private JTable tablaTiposDocumentos;
	private TipoDocumento tipodocumnentoActualizar = null;
	private JTextField txtfiltro;
	

	/**
	 * Create the panel.
	 * @throws NamingException 
	 */
	public JpTiposDocumentos() throws NamingException {
		setBackground(new Color(255, 255, 255));
		JPanel pnlNew = new JPanel();
		
		pnlNew.setBackground(new Color(255, 255, 255));
		pnlNew.setForeground(new Color(255, 255, 255));
		
		JPanel pnltable = new JPanel();
		pnltable.setBackground(new Color(255, 255, 255));
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);
		
		JPanel pnlOptions = new JPanel();
		pnlOptions.setBackground(new Color(255, 255, 255));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(pnltable, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pnlNew, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pnlOptions, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addComponent(pnlNew, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnltable, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlOptions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(75, Short.MAX_VALUE))
		);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(new Color(46, 139, 87));
		lblNombre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		
		JLabel lblabreviatura = new JLabel("Abreviatura");
		lblabreviatura.setForeground(new Color(46, 139, 87));
		lblabreviatura.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtAbreviatura = new JTextField();
		txtAbreviatura.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtAbreviatura.setColumns(10);
		
		JButton btnAgregar = new JButton("Aceptar");
		
		btnAgregar.setBackground(new Color(245, 255, 250));
		btnAgregar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnAgregar.setForeground(new Color(46, 139, 87));
		
		JPanel Close_Panel = new JPanel();
		Close_Panel.addMouseListener(new MouseAdapter() {
			/*@Override
			public void mouseClicked(MouseEvent e) {
				try {
					this.finalize();
				} catch (Throwable e1) {
										e1.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Close_Panel.setBackground(new Color(255, 0, 0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Close_Panel.setBackground(new Color(255, 255, 255));
			}*/
		});
		Close_Panel.setForeground(Color.WHITE);
		Close_Panel.setBackground(new Color(255, 255, 255));
		GroupLayout gl_pnlNew = new GroupLayout(pnlNew);
		gl_pnlNew.setHorizontalGroup(
			gl_pnlNew.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlNew.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlNew.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlNew.createSequentialGroup()
							.addGap(10)
							.addComponent(txtAbreviatura, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
						.addGroup(gl_pnlNew.createSequentialGroup()
							.addGap(10)
							.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
						.addComponent(lblabreviatura)
						.addComponent(btnAgregar, Alignment.TRAILING)
						.addGroup(gl_pnlNew.createSequentialGroup()
							.addComponent(lblNombre)
							.addPreferredGap(ComponentPlacement.RELATED, 389, Short.MAX_VALUE)
							.addComponent(Close_Panel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_pnlNew.setVerticalGroup(
			gl_pnlNew.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlNew.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlNew.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlNew.createSequentialGroup()
							.addComponent(lblNombre)
							.addGap(2))
						.addGroup(gl_pnlNew.createSequentialGroup()
							.addComponent(Close_Panel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblabreviatura)
					.addGap(12)
					.addComponent(txtAbreviatura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAgregar)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		pnlNew.setLayout(gl_pnlNew);
		
		JScrollPane scrollTablaTiposDocumentos = new JScrollPane();

		scrollTablaTiposDocumentos.setLayout(new ScrollPaneLayout() {
		  /**
			 * 
			 */
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
		scrollTablaTiposDocumentos.getVerticalScrollBar().setUI(new EcosferaScrollBar());
		    
		    
		
		txtfiltro = new JTextField();
		txtfiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtrar();
			}
		});
		txtfiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtfiltro.setColumns(10);
		
		JLabel label = new JLabel("Nombre");
		label.setForeground(new Color(46, 139, 87));
		label.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));

		GroupLayout gl_pnltable = new GroupLayout(pnltable);
		gl_pnltable.setHorizontalGroup(
			gl_pnltable.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pnltable.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnltable.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollTablaTiposDocumentos, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_pnltable.createSequentialGroup()
							.addGap(10)
							.addComponent(txtfiltro, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
						.addComponent(label, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_pnltable.setVerticalGroup(
			gl_pnltable.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnltable.createSequentialGroup()
					.addGap(18)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtfiltro, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollTablaTiposDocumentos, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(107))
		);
		
		
		

		this.tablaTiposDocumentos = this.cargarTablaConsultas();
		scrollTablaTiposDocumentos.setViewportView(tablaTiposDocumentos);
				
		pnltable.setLayout(gl_pnltable);
		
		JButton btnModificat = new JButton("Modificar");
		btnModificat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tipodocumnentoActualizar = null;
				if (tablaTiposDocumentos.getSelectedRow() > -1) {
					Long id = (Long) tablaTiposDocumentos.getValueAt(tablaTiposDocumentos.getSelectedRow(), 0);
					try {
						tipodocumnentoActualizar = obtenerPorID(id);
						txtNombre.setText(tipodocumnentoActualizar.getNombre());
						txtAbreviatura.setText(tipodocumnentoActualizar.getAbreviatura());
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
				TipoDocumento tdocEliminar = null;
				if (tablaTiposDocumentos.getSelectedRow() > -1) {
					Long id = (Long) tablaTiposDocumentos.getValueAt(tablaTiposDocumentos.getSelectedRow(), 0);
					try {
						tdocEliminar = obtenerPorID(id);
						eliminarTipodocumento(tdocEliminar);
						tablaTiposDocumentos.setVisible(false);
						tablaTiposDocumentos = cargarTablaConsultas();
						scrollTablaTiposDocumentos.setViewportView(tablaTiposDocumentos);
						tablaTiposDocumentos.setVisible(true);
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
			gl_pnlOptions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlOptions.createSequentialGroup()
					.addContainerGap(279, Short.MAX_VALUE)
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
		setLayout(groupLayout);
		
		
		
		
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (tipodocumnentoActualizar!=null) {
					try {
						tipodocumnentoActualizar.setAbreviatura(txtAbreviatura.getText());
						tipodocumnentoActualizar.setNombre(txtNombre.getText());
						crearoModificarTipodocumento(tipodocumnentoActualizar);
						tablaTiposDocumentos.setVisible(false);
						tablaTiposDocumentos = cargarTablaConsultas();
						scrollTablaTiposDocumentos.setViewportView(tablaTiposDocumentos);
						tablaTiposDocumentos.setVisible(true);
						txtNombre.setText("");
						txtAbreviatura.setText("");
						tipodocumnentoActualizar = null;
						filtrar();
					} catch (NamingException e1) {
						e1.printStackTrace();
					}
				}else {
					TipoDocumento tiposdocumento = new TipoDocumento();
					tiposdocumento.setNombre(txtNombre.getText());
					tiposdocumento.setAbreviatura(txtAbreviatura.getText());			
					try {
						crearTipodocumento(tiposdocumento);
						tablaTiposDocumentos.setVisible(false);
						tablaTiposDocumentos = cargarTablaConsultas();
						scrollTablaTiposDocumentos.setViewportView(tablaTiposDocumentos);
						tablaTiposDocumentos.setVisible(true);
						txtNombre.setText("");
						txtAbreviatura.setText("");
					} catch (NamingException e1) {
						e1.printStackTrace();
					}
				}
				
				
			}
		});

	}
	
	public List<TipoDocumento> listarTiposDocumentos() throws NamingException {
		TipoDocumentoBeanRemote tipoDocumentoBeanRemote  = (TipoDocumentoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/TipoDocumentoBean!com.services.TipoDocumentoBeanRemote");
		return tipoDocumentoBeanRemote.obtenerTodos();
	}
	
	public TipoDocumento obtenerPorID(Long id) throws NamingException {
		TipoDocumentoBeanRemote tipoDocumentoBeanRemote  = (TipoDocumentoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/TipoDocumentoBean!com.services.TipoDocumentoBeanRemote");
		return tipoDocumentoBeanRemote.obtenerporID(id);
	}
	
	public void crearTipodocumento(TipoDocumento tipoDocumento) throws NamingException {
		TipoDocumentoBeanRemote tipoDocumentoBeanRemote  = (TipoDocumentoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/TipoDocumentoBean!com.services.TipoDocumentoBeanRemote");
		try {
			tipoDocumentoBeanRemote.crear(tipoDocumento);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public void eliminarTipodocumento(TipoDocumento tipoDocumento) throws NamingException {
		TipoDocumentoBeanRemote tipoDocumentoBeanRemote  = (TipoDocumentoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/TipoDocumentoBean!com.services.TipoDocumentoBeanRemote");
		try {
			tipoDocumentoBeanRemote.borrar(tipoDocumento.getId());
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public void crearoModificarTipodocumento(TipoDocumento tipoDocumento) throws NamingException {
		TipoDocumentoBeanRemote tipoDocumentoBeanRemote  = (TipoDocumentoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/TipoDocumentoBean!com.services.TipoDocumentoBeanRemote");
		try {
			tipoDocumentoBeanRemote.crearOModificar(tipoDocumento);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	private JTable cargarTablaConsultas() throws NamingException {
		
		List<TipoDocumento> lista = listarTiposDocumentos();

		String[] nombreColumnas = {"ID", "Nombre", "Abreviatura"};

		Object[][] datos = new Object[lista.size()][3];
		int fila = 0;

		
		for (TipoDocumento c : lista) {
			System.out.println(c.getId());
			datos[fila][0] = c.getId();
			datos[fila][1] = c.getNombre();
			datos[fila][2] = c.getAbreviatura();
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
		
		
		tablaTiposDocumentos = new JTable(model);
		tablaTiposDocumentos.setRowSelectionAllowed(true);
		tablaTiposDocumentos.setColumnSelectionAllowed(false);
		tablaTiposDocumentos.setBorder(null);
		tablaTiposDocumentos.setBackground(new Color(255, 255, 255));
		tablaTiposDocumentos.setForeground(new Color(153, 153, 153));
		tablaTiposDocumentos.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 13));
		tablaTiposDocumentos.setShowVerticalLines(false);
		tablaTiposDocumentos.setAutoscrolls(true);
		tablaTiposDocumentos.setSize(600, 600);
		Color color = new Color(144,238,144);
		tablaTiposDocumentos.setSelectionBackground(color);
		
		return tablaTiposDocumentos;

	}
	
	private void filtrar() {
		TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.tablaTiposDocumentos.getModel());
		filtro.setRowFilter(RowFilter.regexFilter(this.txtfiltro.getText(), 1));
		this.tablaTiposDocumentos.setRowSorter(filtro);

	}
	
	

	
}
