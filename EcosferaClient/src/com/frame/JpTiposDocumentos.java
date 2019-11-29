package com.frame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.entities.Permiso;
import com.entities.TipoDocumento;
import com.exceptions.ServiciosException;
import com.framework.EcosferaScrollBar;
import com.services.PerfilesBeanRemote;
import com.services.PermisoBeanRemote;
import com.services.TipoDocumentoBeanRemote;
import com.services.UsuarioBeanRemote;

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
		
		JLabel label_1 = new JLabel("Funcionalidad");
		label_1.setForeground(new Color(46, 139, 87));
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		panel_1.add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(296, 54, 144, 24);
		pnltable.add(panel_2);
		
		JLabel label_2 = new JLabel("Descripción");
		label_2.setForeground(new Color(46, 139, 87));
		label_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		panel_2.add(label_2);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);
		
		JPanel pnlOptions = new JPanel();
		pnlOptions.setBounds(110, 482, 452, 55);
		pnlOptions.setBackground(new Color(255, 255, 255));
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 52, 94, 20);
		lblNombre.setForeground(new Color(46, 139, 87));
		lblNombre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				txtNombre.setText(txtNombre.getText().toUpperCase());
			}
			
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtNombre.getText().length()>=50) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		txtNombre.setBounds(118, 51, 322, 24);
		txtNombre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtNombre.setColumns(10);
		
		JLabel lblabreviatura = new JLabel("Abreviatura");
		lblabreviatura.setBounds(12, 89, 94, 20);
		lblabreviatura.setForeground(new Color(46, 139, 87));
		lblabreviatura.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		
		txtAbreviatura = new JTextField();
		txtAbreviatura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtAbreviatura.getText().length()>=6) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		txtAbreviatura.setBounds(118, 88, 322, 24);
		txtAbreviatura.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtAbreviatura.setColumns(10);
		
		//JButton btnAgregar = new JButton("Aceptar");
		//btnAgregar.setBounds(347, 145, 79, 27);
		
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
				txtAbreviatura.setText("");
				txtNombre.setText("");
				tipodocumnentoActualizar = null;
				txtNombre.setEnabled(true);
			}
		});
		
		btnCancelar.setForeground(new Color(46, 139, 87));
		btnCancelar.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		btnCancelar.setBackground(new Color(245, 255, 250));
		
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
				JpListaUsuarios jp;
				try {
					jp = new JpListaUsuarios();
					jp.setBounds(290, 238, 660, 600);
					jp.setVisible(true);
					jp.setLocation(12,12);
					JFRPrincipal.getIntance();
					JFRPrincipal.PnlWorkSpace.removeAll();
					JFRPrincipal.PnlWorkSpace.add(jp);
					JFRPrincipal.PnlWorkSpace.revalidate();
					JFRPrincipal.PnlWorkSpace.repaint();
					JFRPrincipal.LblNavegacion.setText("Inicio"+ " - " + "Usuarios");
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
		
		pnlNew.setLayout(null);
		pnlNew.add(txtAbreviatura);
		pnlNew.add(txtNombre);
		pnlNew.add(lblabreviatura);
		pnlNew.add(btnAgregar);
		pnlNew.add(btnCancelar);
		pnlNew.add(lblNombre);
		
		JScrollPane scrollTablaTiposDocumentos = new JScrollPane();
		scrollTablaTiposDocumentos.setBounds(12, 76, 428, 176);

		scrollTablaTiposDocumentos.setLayout(new ScrollPaneLayout() {

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
		txtfiltro.setBounds(78, 17, 362, 24);
		txtfiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtrar();
			}
		});
		txtfiltro.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		txtfiltro.setColumns(10);
		
		JLabel label = new JLabel("Filtro");
		label.setBounds(12, 18, 109, 20);
		label.setForeground(new Color(46, 139, 87));
		label.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		pnltable.setLayout(null);
		
		
		

		this.tablaTiposDocumentos = this.cargarTablaConsultas();
		scrollTablaTiposDocumentos.setViewportView(tablaTiposDocumentos);
		pnltable.add(scrollTablaTiposDocumentos);
		pnltable.add(txtfiltro);
		pnltable.add(label);
		
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
						txtNombre.setEnabled(false);
					} catch (NamingException e) {
						e.printStackTrace();
						reportarError(e.getMessage());
					}
				}else {
					reportarError("Debe seleccionar tipo de documento");
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
						if(!controles_preDelete(tdocEliminar)) {
							if(solicitarConfirmaciones(tdocEliminar)) {
								eliminarTipodocumento(tdocEliminar);
								tablaTiposDocumentos.setVisible(false);
								tablaTiposDocumentos = cargarTablaConsultas();
								scrollTablaTiposDocumentos.setViewportView(tablaTiposDocumentos);
								tablaTiposDocumentos.setVisible(true);
								filtrar();
							}
						}
					} catch (NamingException e) {
						e.printStackTrace();
						reportarError(e.getMessage());
					}
				}else {
					reportarError("Debe seleccionar tipo de documento");
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
		setLayout(null);
		add(pnltable);
		add(pnlNew);
		add(pnlOptions);
		
		
		
		
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!controles_preCreate()) {
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
							txtNombre.setEnabled(true);
						} catch (NamingException e1) {
							e1.printStackTrace();
						}
					}else {
						TipoDocumento tiposdocumento = new TipoDocumento();
						tiposdocumento.setNombre(txtNombre.getText());
						tiposdocumento.setAbreviatura(txtAbreviatura.getText());			
						try {
							if (!controles_postCreate(tiposdocumento)) {
								crearTipodocumento(tiposdocumento);
								tablaTiposDocumentos.setVisible(false);
								tablaTiposDocumentos = cargarTablaConsultas();
								scrollTablaTiposDocumentos.setViewportView(tablaTiposDocumentos);
								tablaTiposDocumentos.setVisible(true);
								txtNombre.setText("");
								txtAbreviatura.setText("");
								txtNombre.setEnabled(true);
							}
						} catch (NamingException e1) {
							e1.printStackTrace();
						}
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
	
	
	public boolean controles_preCreate() {
		boolean error = false;
		String mensajeError ="";
		if (!txtAbreviatura.getText().isEmpty()) {
			if(!txtNombre.getText().isEmpty()) {
				error = false;
			}else {
				error = true;
				mensajeError = "El nombre del Tipo de documento es un campo obligatorio.";
			}
		}else {
			error = true;
			mensajeError = "La Abreviatura del Tipo de documento es un campo obligatorio.";
		}
		
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se pudo crear el tipo de documento", JOptionPane.ERROR_MESSAGE);}
		return error;	
	}
	
	public boolean controles_postCreate(TipoDocumento tipo) throws NamingException {
		boolean error = false;
		String mensajeError = "";
		TipoDocumentoBeanRemote tipoDocumentoBeanRemote  = (TipoDocumentoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/TipoDocumentoBean!com.services.TipoDocumentoBeanRemote");
		mensajeError = tipoDocumentoBeanRemote.controles_postCreate(tipo);
		if (!mensajeError.isEmpty()) {
			error = true;
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se pudo crear el tipo de documento", JOptionPane.ERROR_MESSAGE);}
		return error;		
	}
	
	
		
	public boolean controles_preDelete(TipoDocumento tipo) throws NamingException {
		boolean error = false;
		String mensajeError = "";
		UsuarioBeanRemote usuarioBeanRemote  = (UsuarioBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/UsuarioBean!com.services.UsuarioBeanRemote");
		mensajeError = usuarioBeanRemote.controles_PreDelteTdco(tipo);
		if (!mensajeError.isEmpty()) {
			error = true;
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se puede eliminar el tipo de documento", JOptionPane.ERROR_MESSAGE);}
		return error;		
	}
	
	public void reportarError(String error) {
		JOptionPane.showMessageDialog(this, error);
	}
	
	
	
	public boolean solicitarConfirmaciones(TipoDocumento tipoDocumentoEliminar) {
		boolean confirmado = false;
		int i =JOptionPane.showConfirmDialog(this,"¿Realmente Desea eliminar el tipo de documento "+ tipoDocumentoEliminar.getNombre()+"?","Confirmar",JOptionPane.YES_NO_OPTION);
		if (i==0) {
			confirmado = true;
		}
		return confirmado;
	}
	
	

	
}
