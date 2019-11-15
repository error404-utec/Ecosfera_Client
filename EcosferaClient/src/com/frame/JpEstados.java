package com.frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.entities.Estado;
import com.exceptions.ServiciosException;
import com.framework.Celda_CheckBox;
import com.framework.EcosferaScrollBar;
import com.framework.Render_CheckBox;
import com.services.EstadoBeanRemote;
import com.services.UsuarioBeanRemote;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class JpEstados extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtFiltro;
	private JTable tablaTipoEstados;
	private JCheckBox chkPemLogin = new JCheckBox("\u00BFPermite Login?");
	private JCheckBox chkPermRegistro = new JCheckBox("\u00BFPermite registro?");
	private JCheckBox chkEliminado = new JCheckBox("\u00BFEliminaci\u00F3n l\u00F3gica?");
	
	private Estado estadoActualizar = null;
	

	public JpEstados() throws NamingException {
		setBackground(Color.WHITE);
		
		JPanel pnlNew = new JPanel();
		pnlNew.setBounds(110, 0, 452, 165);
		pnlNew.setBackground(new Color(255, 255, 255));
		pnlNew.setForeground(new Color(255, 255, 255));
		
		JPanel pnltable = new JPanel();
		pnltable.setBounds(110, 178, 452, 291);
		pnltable.setBackground(new Color(255, 255, 255));
		
		
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
				txtFiltro.setText(txtFiltro.getText().toUpperCase());
				filtrar();
			}
		});
		
		JScrollPane scrollTablaEstado = new JScrollPane();
		scrollTablaEstado.setBounds(12, 78, 428, 200);
		
		scrollTablaEstado.setLayout(new ScrollPaneLayout() {

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
			scrollTablaEstado.getVerticalScrollBar().setUI(new EcosferaScrollBar());
		
		
		txtNombre = new JTextField();
		txtNombre.setBounds(110, 12, 330, 24);
		txtNombre.setColumns(10);
		
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtNombre.setText(txtNombre.getText().toUpperCase());
			}
			@Override		
			public void keyTyped(KeyEvent arg0) {
				if(txtNombre.getText().length()>=50) {
					getToolkit().beep();
					arg0.consume();
				}
			}
		});
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 13, 51, 20);
		lblNombre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblNombre.setForeground(new Color(46, 139, 87));
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(240, 125, 96, 27);
		btnAceptar.setBackground(Color.WHITE);
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if(!controles_preCreate()) {
						if (estadoActualizar!=null) {
							estadoActualizar.setNombre(txtNombre.getText());
							if (chkEliminado.isSelected()) {estadoActualizar.setEliminado(true);}else {estadoActualizar.setEliminado(false);}
							if (chkPemLogin.isSelected()) {estadoActualizar.setPermiteLogin(true);}else {estadoActualizar.setPermiteLogin(false);}
							if (chkPermRegistro.isSelected()) {estadoActualizar.setPermiteRegistro(true);}else {estadoActualizar.setPermiteRegistro(false);}
							
							if (!controles_postCreate(estadoActualizar)) {
								crearoModificarEstado(estadoActualizar);
								tablaTipoEstados.setVisible(false);
								tablaTipoEstados= cargarEstado();
								scrollTablaEstado.setViewportView(tablaTipoEstados);
								tablaTipoEstados.setVisible(true);
								txtNombre.setText("");
								chkEliminado.setSelected(false);
								chkPermRegistro.setSelected(false);
								chkPemLogin.setSelected(false);
								txtNombre.setEditable(true);
								estadoActualizar = null;
								filtrar();
							}
						}else {
							Estado estado = new Estado();
							estado.setNombre(txtNombre.getText());
							if (chkEliminado.isSelected()) {estado.setEliminado(true);}else {estado.setEliminado(false);}
							if (chkPemLogin.isSelected()) {estado.setPermiteLogin(true);}else {estado.setPermiteLogin(false);}
							if (chkPermRegistro.isSelected()) {estado.setPermiteRegistro(true);}else {estado.setPermiteRegistro(false);}
							
							
							if (!controles_postCreate(estado)) {
								crearEstado(estado);
								tablaTipoEstados.setVisible(false);
								tablaTipoEstados = cargarEstado();
								scrollTablaEstado.setViewportView(tablaTipoEstados);
								tablaTipoEstados.setVisible(true);
								chkEliminado.setSelected(false);
								chkPermRegistro.setSelected(false);
								chkPemLogin.setSelected(false);
								txtNombre.setText("");
							}
						}
					}
				} catch (NamingException e1) {
					reportarError(e1.getMessage());
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
				txtNombre.setText("");
				txtNombre.setEditable(true);
				estadoActualizar = null;
				chkEliminado.setSelected(false);
				chkPermRegistro.setSelected(false);
				chkPemLogin.setSelected(false);
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
		pnlNew.add(txtNombre);
		pnlNew.add(lblNombre);
		

		chkPemLogin.setBackground(Color.WHITE);
		chkPemLogin.setBounds(110, 45, 129, 25);
		pnlNew.add(chkPemLogin);
		
		
		chkPermRegistro.setBackground(Color.WHITE);
		chkPermRegistro.setBounds(257, 45, 133, 25);
		pnlNew.add(chkPermRegistro);
		chkEliminado.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chkEliminado.isSelected()) {
					chkPemLogin.setSelected(false);
					chkPermRegistro.setSelected(false);
					chkPermRegistro.setEnabled(false);
					chkPemLogin.setEnabled(false);
				}else {
					chkPermRegistro.setEnabled(true);
					chkPemLogin.setEnabled(true);
				}
			}
		});
		
		
		chkEliminado.setBackground(Color.WHITE);
		chkEliminado.setBounds(110, 75, 152, 25);
		pnlNew.add(chkEliminado);
		add(pnltable);
		pnltable.setLayout(null);
		pnltable.add(lblFiltro);
		pnltable.add(txtFiltro);
		pnltable.add(scrollTablaEstado);

		this.tablaTipoEstados = this.cargarEstado();
		scrollTablaEstado.setViewportView(tablaTipoEstados);
		
		JPanel pnlOptions = new JPanel();
		pnlOptions.setBounds(110, 478, 452, 55);
		pnlOptions.setBackground(new Color(255, 255, 255));
		add(pnlOptions);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(235, 0, 93, 29);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Estado estadoEliminar = null;
				if (tablaTipoEstados.getSelectedRow() > -1) {
					Long id = (Long) tablaTipoEstados.getValueAt(tablaTipoEstados.getSelectedRow(), 0);
					try {
						estadoEliminar = obtenerEstadoPorID(id);
						if(!controles_preDelete(estadoEliminar)) {
							if(solicitarConfirmaciones(estadoEliminar)) {
								eliminarEstado(estadoEliminar);
								tablaTipoEstados.setVisible(false);
								tablaTipoEstados = cargarEstado();
								scrollTablaEstado.setViewportView(tablaTipoEstados);
								tablaTipoEstados.setVisible(true);
								filtrar();
							}
						}
					} catch (NamingException ev) {
						ev.printStackTrace();
					}
				}else {
					reportarError("Debe seleccionar un Estado");
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
				estadoActualizar = null;
				if (tablaTipoEstados.getSelectedRow() > -1) {
					Long id = (Long) tablaTipoEstados.getValueAt(tablaTipoEstados.getSelectedRow(), 0);
					try {
						estadoActualizar = obtenerEstadoPorID(id);

						txtNombre.setText(estadoActualizar.getNombre());
						chkEliminado.setSelected(estadoActualizar.isEliminado());
						chkPemLogin.setSelected(estadoActualizar.isPermiteLogin());
						chkPermRegistro.setSelected(estadoActualizar.isPermiteRegistro());
						
						txtNombre.setEditable(false);
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}else {
					reportarError("Debe seleccionar un Estado");
				}
			}
		});
		btnModificarf.setForeground(new Color(46, 139, 87));
		btnModificarf.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnModificarf.setBackground(new Color(245, 255, 250));
		pnlOptions.setLayout(null);
		pnlOptions.add(btnEliminar);
		pnlOptions.add(btnModificarf);
		

	}
	
	public void crearoModificarEstado (Estado estado) throws NamingException {
		EstadoBeanRemote estadoBeanRemote = (EstadoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/EstadoBean!com.services.EstadoBeanRemote");
		try {
			estadoBeanRemote.actualizar(estado);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
		
	public void crearEstado(Estado estado) throws NamingException {
		EstadoBeanRemote estadoBeanRemote = (EstadoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/EstadoBean!com.services.EstadoBeanRemote");
		try {
			estadoBeanRemote.crear(estado);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public void eliminarEstado(Estado estado) throws NamingException {
		EstadoBeanRemote estadoBeanRemote = (EstadoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/EstadoBean!com.services.EstadoBeanRemote");

		try {
			estadoBeanRemote.borrar(estado.getId());
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public void actualizarEstado(Estado estado) throws NamingException {
		EstadoBeanRemote estadoBeanRemote = (EstadoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/EstadoBean!com.services.EstadoBeanRemote");
		try {
			estadoBeanRemote.actualizar(estado);
		} catch (ServiciosException e) {
			e.printStackTrace();
		}
		
	}
	
	public Estado obtenerEstadoPorID(Long id) throws NamingException {
		EstadoBeanRemote estadoBeanRemote = (EstadoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/EstadoBean!com.services.EstadoBeanRemote");
		return estadoBeanRemote.obtenerporID(id);
	}
	
	
	private void filtrar() {
		TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.tablaTipoEstados.getModel());
		filtro.setRowFilter(RowFilter.regexFilter(this.txtFiltro.getText(), 1));
		this.tablaTipoEstados.setRowSorter(filtro);

	}
	
	private JTable cargarEstado() throws NamingException {
		
		List<Estado> lista = listarEstados();
		String[] nombreColumnas = {"ID", "Nombre", "Login", "Registro", "Eliminado" };

		Object[][] datos = new Object[lista.size()][5];
		int fila = 0;

		
		for (Estado o : lista) {
			System.out.println(o.getId());
			datos[fila][0] = o.getId();
			datos[fila][1] = o.getNombre();
			datos[fila][2] = o.isPermiteLogin();
			datos[fila][3] = o.isPermiteRegistro();
			datos[fila][4] = o.isEliminado();
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
		
		
		
		tablaTipoEstados = new JTable(model);
		
		tablaTipoEstados.getColumnModel().getColumn(2).setCellEditor(new Celda_CheckBox());
		tablaTipoEstados.getColumnModel().getColumn(2).setCellRenderer(new Render_CheckBox());
		tablaTipoEstados.getColumnModel().getColumn(3).setCellEditor(new Celda_CheckBox());
		tablaTipoEstados.getColumnModel().getColumn(3).setCellRenderer(new Render_CheckBox());
		tablaTipoEstados.getColumnModel().getColumn(4).setCellEditor(new Celda_CheckBox());
		tablaTipoEstados.getColumnModel().getColumn(4).setCellRenderer(new Render_CheckBox());
		
		tablaTipoEstados.setRowSelectionAllowed(true);
		tablaTipoEstados.setColumnSelectionAllowed(false);
		tablaTipoEstados.setBorder(null);
		tablaTipoEstados.setBackground(new Color(255, 255, 255));
		tablaTipoEstados.setForeground(new Color(153, 153, 153));
		tablaTipoEstados.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 13));
		tablaTipoEstados.setShowVerticalLines(false);
		tablaTipoEstados.setAutoscrolls(true);
		tablaTipoEstados.setSize(600, 600);
		Color color = new Color(144,238,144);
		tablaTipoEstados.setSelectionBackground(color);
		
		return tablaTipoEstados;
	
	}


	private List<Estado> listarEstados() throws NamingException  {
		EstadoBeanRemote estadoBeanRemote = (EstadoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/EstadoBean!com.services.EstadoBeanRemote");
		return estadoBeanRemote.obtenerTodos();
	
	}
	
	
	public boolean controles_preCreate() {
		boolean error = false;
		String mensajeError ="";
		if(!txtNombre.getText().isEmpty()) {
				error = false;
		}else {
			error = true;
			mensajeError = "El nombre del estado es un campo obligatorio.";
		}
		
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se pudo crear el Estado", JOptionPane.ERROR_MESSAGE);}
		return error;	
	}
	
	public boolean controles_postCreate(Estado estado) throws NamingException {
		boolean error = false;
		String mensajeError = "";
		EstadoBeanRemote estadoBean = (EstadoBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/EstadoBean!com.services.EstadoBeanRemote");
		mensajeError = estadoBean.controles_postCreate(estado);
		if (!mensajeError.isEmpty()) {
			error = true;
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se pudo crear el Estado", JOptionPane.ERROR_MESSAGE);}
		return error;		
	}
	
	public boolean controles_preDelete(Estado estado) throws NamingException {
		boolean error = false;
		String mensajeError = "";
		if (estado.getId()==1) {
			error = true;
			mensajeError="No se permite la eliminacion del estado NUEVO.";
		}
		if (estado.getId()==2) {
			error = true;
			mensajeError="No se permite la eliminacion del estado ELIMINADO.";
		}
		
		UsuarioBeanRemote usuarioBeanRemote = (UsuarioBeanRemote)
				InitialContext.doLookup("ECOSFERA_MARK1/UsuarioBean!com.services.UsuarioBeanRemote");
		mensajeError = usuarioBeanRemote.controles_PreDelteEstado(estado);
		if (!mensajeError.isEmpty()) {
			error = true;
		}
		if (error) {JOptionPane.showMessageDialog(this, mensajeError, "No se puede eliminar el Estado", JOptionPane.ERROR_MESSAGE);}
		return error;		
	}
	
	public void reportarError(String error) {
		JOptionPane.showMessageDialog(this, error);
	}
	
	public boolean solicitarConfirmaciones(Estado estado) {
		boolean confirmado = false;
		int i =JOptionPane.showConfirmDialog(this,"¿Realmente Desea eliminar el Estado "+ estado.getNombre()+"?","Confirmar",JOptionPane.YES_NO_OPTION);
		if (i==0) {
			confirmado = true;
		}
		return confirmado;
	}
}

