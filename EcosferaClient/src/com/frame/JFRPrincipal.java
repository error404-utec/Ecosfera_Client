package com.frame;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.naming.NamingException;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.FlowLayout;

public class JFRPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int xMouse;
	private int yMouse;

	public static JPanel PnlWorkSpace = new JPanel(); 		
	//Orden de menu---------------------------
	private JPanel PnlMenuInicio = new JPanel();
	private JPanel PnlZonas = new JPanel();
	private JPanel PnlUsuarios = new JPanel();
	private JPanel PnlTiposObservaciones = new JPanel();
	private JPanel PnlPermisos = new JPanel();
	private JPanel PnlObservaciones = new JPanel();
	private JPanel PnlRevision = new JPanel();
	//---------------------------------------
	
	private JPanel PnlTopMenu = new JPanel();
	private static JPanel PnlTopMenu_Title = new JPanel();
	private JPanel PnlTopMenu_Menu = new JPanel();
	private static JLabel lblTitulopanel = new JLabel("");
	private JSeparator separator_1 = new JSeparator();
	private JLabel LblUsuarios_Title = new JLabel("Usuarios");
	private JLabel LblTiposObservaciones_Title = new JLabel("Tipos de Observaci\u00F3n");
	private JLabel LblTiposObservaciones_Icon = new JLabel("");
	private JLabel LblPermisos_Icon = new JLabel("");
	private JSeparator separator = new JSeparator();
	public static JLabel LblNavegacion =	new JLabel("Inicio");
	private JPanel PnlMove = new JPanel();
	private JLabel LblPermisos_Title = new JLabel("Permisos");
	private JLabel LblZonas_Icon = new JLabel("");
	private JLabel LblZonas_title = new JLabel("Zonas");
	private JLabel LblRevision_Icon = new JLabel("");
	private JLabel LblUsuario_Icon = new JLabel("");
	private JLabel LblObservaciones_title = new JLabel("Observaciones");
	private JLabel LblObservaciones_Icon = new JLabel("");
	private final JPanel PnlLogOut = new JPanel();
	private final JPanel PnlTopMenu_1 = new JPanel();
	private final JPanel PnlTopMenu_2 = new JPanel();
	private final JPanel PnlTopMenu_3 = new JPanel();
	private final JPanel PnlTopMenu_4 = new JPanel();
	private final JLabel lblTopMenu_1 = new JLabel("");	
	private final JLabel lblTopMenu_2 = new JLabel("");
	private final JLabel lblTopMenu_3 = new JLabel("");		
	private final JLabel lblTopMenu_4 = new JLabel("");
	private static JFRPrincipal frame = new JFRPrincipal();

	public static void setlblTitulopanel(String titulo) {
		lblTitulopanel.setText(titulo);
		PnlTopMenu_Title.revalidate();
		PnlTopMenu_Title.repaint();
	}
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setUndecorated(true);
					frame.setBackground(new Color(0,0,0,0));
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static JFRPrincipal getIntance(){
        return frame;
    }

	private void reinicarMenu() {
		PnlUsuarios.setVisible(true);
		PnlTiposObservaciones.setVisible(true);
		PnlZonas.setVisible(true);
		PnlPermisos.setVisible(true);
		PnlObservaciones.setVisible(true);
		PnlRevision.setVisible(true);
		
		PnlZonas.setBounds(0, 121, 278, 57);
		PnlUsuarios.setBounds(0, 178, 278, 57);
		PnlTiposObservaciones.setBounds(0, 234, 278, 57);
		PnlPermisos.setBounds(0, 289, 278, 57);
		PnlObservaciones.setBounds(0, 346, 278, 57);
		PnlRevision.setBounds(0, 403, 278, 57);
		
		PnlMenuInicio_repaint();
	}
	
	private void reinicarTopMenu() {
		PnlTopMenu_1.setVisible(false);
		PnlTopMenu_2.setVisible(false);
		PnlTopMenu_3.setVisible(false);
		PnlTopMenu_4.setVisible(false);
	}
	
	private void PnlTopMenu_Repaint() {
		PnlTopMenu.revalidate();
		PnlTopMenu.repaint();
	}
	
	/**
	 * Create the frame.
	 */
	private JFRPrincipal() {
		contentPane = new JPanel();
		setAlwaysOnTop(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 958, 885);

		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		PnlObservaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				PnlObservacion_MouseEntered();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				PnlObservacion_MouseExited();
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PnlObservacion_MouseClicked();
			}
		});
		PnlTopMenu_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if (lblTopMenu_1.getText().equals("Zonas")) {
						JpZonas jp;
						jp = new JpZonas();
						jp.setBounds(290, 238, 660, 600);
						jp.setVisible(true);
						jp.setLocation(12,12);
						PnlWorkSpace.removeAll();
						PnlWorkSpace.add(jp);
					}else if(lblTopMenu_1.getText().equals("Documentos")){
						JpTiposDocumentos jp;
						jp = new JpTiposDocumentos();
						jp.setBounds(290, 238, 660, 600);
						jp.setVisible(true);
						jp.setLocation(12,12);
						PnlWorkSpace.removeAll();
						PnlWorkSpace.add(jp);
					}
					
				} catch (NamingException e) {
					e.printStackTrace();
				}

				PnlWorkSpace.revalidate();
				PnlWorkSpace.repaint();
				LblNavegacion.setText("Inicio"+ " - " + lblTopMenu_1.getText());
				lblTitulopanel.setText("Mantenimiento de " + lblTopMenu_1.getText());
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				PnlTopMenu_1_MouseEntered();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				PnlTopMenu_1_MouseExited();
			}
		});
		
		PnlTopMenu_1.setBackground(new Color(60,179,113));
		
		PnlObservaciones.setBackground(new Color(60,179,113));

		PnlTopMenu.setBounds(284, 61, 662, 169);

		PnlTopMenu_Title.setBackground(new Color(144, 238, 144));
		
		PnlTopMenu_Menu.setBackground(new Color(255, 255, 255));
		

		lblTitulopanel.setForeground(new Color(240, 255, 240));
		lblTitulopanel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		lblTitulopanel.setBackground(new Color(204, 255, 204));
		
		
		separator_1.setBackground(new Color(204, 255, 204));
		separator_1.setForeground(new Color(204, 255, 204));
		GroupLayout gl_PnlTopMenu_Title = new GroupLayout(PnlTopMenu_Title);
		gl_PnlTopMenu_Title.setHorizontalGroup(
			gl_PnlTopMenu_Title.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_PnlTopMenu_Title.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_PnlTopMenu_Title.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_PnlTopMenu_Title.createSequentialGroup()
							.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
							.addGap(17))
						.addComponent(lblTitulopanel, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_PnlTopMenu_Title.setVerticalGroup(
			gl_PnlTopMenu_Title.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_PnlTopMenu_Title.createSequentialGroup()
					.addContainerGap(34, Short.MAX_VALUE)
					.addComponent(lblTitulopanel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		PnlTopMenu_Title.setLayout(gl_PnlTopMenu_Title);
		PnlTopMenu_Menu.setLayout(null);
		
		
		PnlWorkSpace.setBounds(284, 229, 662, 609);
		PnlWorkSpace.setBackground(new Color(255, 255, 255));
		GroupLayout gl_PnlTopMenu = new GroupLayout(PnlTopMenu);
		gl_PnlTopMenu.setHorizontalGroup(
			gl_PnlTopMenu.createParallelGroup(Alignment.LEADING)
				.addComponent(PnlTopMenu_Menu, GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
				.addComponent(PnlTopMenu_Title, GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
		);
		gl_PnlTopMenu.setVerticalGroup(
			gl_PnlTopMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PnlTopMenu.createSequentialGroup()
					.addComponent(PnlTopMenu_Title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(PnlTopMenu_Menu, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
		);
		PnlTopMenu_1.setBounds(12, 13, 150, 47);
		
		PnlTopMenu_Menu.add(PnlTopMenu_1);
		PnlTopMenu_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		lblTopMenu_1.setForeground(new Color(240, 255, 240));
		lblTopMenu_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		PnlTopMenu_1.add(lblTopMenu_1);
		PnlTopMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if (lblTopMenu_2.getText().equals("Estados")) {
						JpEstados jp;
						jp = new JpEstados();
						jp.setBounds(290, 238, 660, 600);
						jp.setVisible(true);
						jp.setLocation(12,12);
						PnlWorkSpace.removeAll();
						PnlWorkSpace.add(jp);
					}
					
				} catch (NamingException e) {
					e.printStackTrace();
				}

				PnlWorkSpace.revalidate();
				PnlWorkSpace.repaint();
				LblNavegacion.setText("Inicio"+ " - " + lblTopMenu_2.getText());
				lblTitulopanel.setText("Mantenimiento de " + lblTopMenu_2.getText());
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				PnlTopMenu_2_MouseEntered();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				PnlTopMenu_2_MouseExited();
			}
		});
		
		
		PnlTopMenu_2.setBackground(new Color(60, 179, 113));
		PnlTopMenu_2.setBounds(174, 13, 150, 47);
		PnlTopMenu_Menu.add(PnlTopMenu_2);
		PnlTopMenu_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		lblTopMenu_2.setForeground(new Color(240, 255, 240));
		lblTopMenu_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		PnlTopMenu_2.add(lblTopMenu_2);
		PnlTopMenu_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					if (lblTopMenu_3.getText().equals("Perfiles")) {
						JpPerfiles jp;
						jp = new JpPerfiles();
						jp.setBounds(290, 238, 660, 600);
						jp.setVisible(true);
						jp.setLocation(12,12);
						PnlWorkSpace.removeAll();
						PnlWorkSpace.add(jp);
					}
					
				} catch (NamingException e) {
					e.printStackTrace();
				}

				PnlWorkSpace.revalidate();
				PnlWorkSpace.repaint();
				LblNavegacion.setText("Inicio"+ " - " + lblTopMenu_3.getText());
				lblTitulopanel.setText("Mantenimiento de " + lblTopMenu_3.getText());
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				PnlTopMenu_3_MouseEntered();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				PnlTopMenu_3_MouseExited();
			}
		});
		
		
		PnlTopMenu_3.setBackground(new Color(60, 179, 113));
		PnlTopMenu_3.setBounds(334, 13, 150, 47);
		PnlTopMenu_Menu.add(PnlTopMenu_3);
		PnlTopMenu_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		lblTopMenu_3.setForeground(new Color(240, 255, 240));
		lblTopMenu_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		PnlTopMenu_3.add(lblTopMenu_3);
		
		
		PnlTopMenu_4.setBackground(new Color(60, 179, 113));
		PnlTopMenu_4.setBounds(496, 13, 150, 47);
		PnlTopMenu_Menu.add(PnlTopMenu_4);
		PnlTopMenu_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		lblTopMenu_4.setForeground(new Color(240, 255, 240));
		lblTopMenu_4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		PnlTopMenu_4.add(lblTopMenu_4);
		PnlTopMenu.setLayout(gl_PnlTopMenu);
		contentPane.setLayout(null);
		
		
		PnlTopMenu_1.setVisible(false);
		PnlTopMenu_2.setVisible(false);
		PnlTopMenu_3.setVisible(false);
		PnlTopMenu_4.setVisible(false);
		
		
		PnlMenuInicio.setBounds(5, 61, 279, 782);
		PnlMenuInicio.setBackground(new Color(46, 139, 87));
		
		LblUsuario_Icon.setIcon(new ImageIcon(JFRPrincipal.class.getResource("/recursos/icons/users.png")));
		LblUsuario_Icon.setBounds(153, 0, 113, 75);
		
		
		
		PnlTiposObservaciones.setBounds(0, 234, 278, 57);
		
		PnlUsuarios.setBounds(0, 178, 278, 57);
		PnlUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				PnlUsuarios_MouseEntered();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				PnlUsuarios_MouseExited();
			}
			@Override
	
			public void mouseClicked(MouseEvent e) {
				try {
					PnlUsuarios_MouseClicked();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		PnlUsuarios.setLayout(null);
		PnlUsuarios.setBackground(new Color(60, 179, 113));
		
		LblUsuarios_Title.setForeground(new Color(240, 255, 240));
		LblUsuarios_Title.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		LblUsuarios_Title.setBounds(12, 13, 89, 29);
		PnlUsuarios.add(LblUsuarios_Title);
		PnlUsuarios.add(LblUsuario_Icon);
		
		LblUsuario_Icon.setVisible(false);
		LblObservaciones_Icon.setVisible(false);
				
				
		PnlTiposObservaciones.setLayout(null);
		PnlTiposObservaciones.setBackground(new Color(60, 179, 113));
		
		LblTiposObservaciones_Title.setForeground(new Color(240, 255, 240));
		LblTiposObservaciones_Title.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		LblTiposObservaciones_Title.setBounds(12, 13, 210, 29);
		PnlTiposObservaciones.add(LblTiposObservaciones_Title);
		

		LblTiposObservaciones_Icon.setIcon(new ImageIcon(JFRPrincipal.class.getResource("/recursos/icons/id-card.png")));
		LblTiposObservaciones_Icon.setBounds(168, 0, 141, 83);
		PnlTiposObservaciones.add(LblTiposObservaciones_Icon);
		
		LblTiposObservaciones_Icon.setVisible(false);
		

		separator.setBounds(12, 0, 199, 9);
		separator.setBackground(new Color(204, 255, 204));
		
		LblPermisos_Icon.setIcon(new ImageIcon(JFRPrincipal.class.getResource("/recursos/icons/settingDefault.png")));
		LblPermisos_Icon.setBounds(156, 0, 122, 80);
		LblPermisos_Icon.setVisible(false);
		
		PnlPermisos.setBounds(0, 290, 278, 57);
				
		PnlPermisos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				PnlPermisos_MouseEntered();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				PnlPermisos_MouseExited();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					PnlPermisos_MouseClicked();
				} catch (NamingException e1) {
					e1.printStackTrace();
				}
			}				
		}
		);

		PnlMove.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				xMouse  = arg0.getX();

				yMouse = arg0.getY();
			}
		});
		PnlMove.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();

				int y = e.getYOnScreen();

				 
				setLocation(x - xMouse , y - yMouse);
			}
			
		});
		PnlMove.setBackground(Color.WHITE);
		PnlMove.setBounds(5, 5, 941, 56);
		
		
		LblNavegacion.setBounds(290, 25, 557, 18);
		
		LblNavegacion.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		
		JPanel PnlMove_PnlMenu = new JPanel();
		PnlMove_PnlMenu.setBackground(new Color(46, 139, 87));
		PnlMove_PnlMenu.setBounds(0, 0, 279, 56);
		contentPane.add(PnlMove);
		PnlMove.setLayout(null);
		PnlMove.add(PnlMove_PnlMenu);
		PnlMove_PnlMenu.setLayout(null);
				
				
				
		JLabel lblTitulo = new JLabel("Ecosfera");
		lblTitulo.setBounds(12, 13, 114, 41);
		PnlMove_PnlMenu.add(lblTitulo);
		lblTitulo.setBackground(new Color(204, 255, 204));
		lblTitulo.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		lblTitulo.setForeground(new Color(240, 255, 240));
		PnlMove.add(LblNavegacion);
		PnlPermisos.setBackground(new Color(60, 179, 113));
		PnlPermisos.setLayout(null);
		
		LblPermisos_Title.setForeground(new Color(240, 255, 240));
		LblPermisos_Title.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		LblPermisos_Title.setBounds(12, 13, 210, 29);
		PnlPermisos.add(LblPermisos_Title);
		
	
		PnlPermisos.add(LblPermisos_Icon);
		contentPane.add(PnlMenuInicio);
		PnlMenuInicio.setLayout(null);
		
		
		LblZonas_Icon.setIcon(new ImageIcon(JFRPrincipal.class.getResource("/recursos/icons/zones.png")));
		LblZonas_Icon.setBounds(177, 0, 89, 57);
		
		PnlZonas.setBounds(0, 121, 278, 57);
		PnlZonas.setBorder(null);
		PnlZonas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				PnlZonas_MouseEntered();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				PnlZonas_MouseExited();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					PnlZonas_MouseClicked();
				} catch (NamingException e1) {
					e1.printStackTrace();
				}				
			}
		});
		PnlZonas.setBackground(new Color(60, 179, 113));
		PnlZonas.setLayout(null);
		LblZonas_Icon.setVisible(false);
		PnlZonas.add(LblZonas_Icon);
		
		PnlObservaciones.setBounds(0, 346, 278, 57);
		PnlRevision.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				PnlRevision_MouseEntered();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				PnlRevision_MouseExited();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				PnlRevision_MouseClicked();
			}
		});
		PnlRevision.setBounds(0, 403, 278, 57);
		PnlRevision.setBackground(new Color(60,179,113));
		
		LblZonas_title.setForeground(new Color(240, 255, 240));
		LblZonas_title.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		LblZonas_title.setBounds(12, 13, 89, 29);
		PnlZonas.add(LblZonas_title);
		
		PnlMenuInicio.add(PnlZonas);
		PnlMenuInicio.add(separator);
		PnlMenuInicio.add(PnlUsuarios);
		PnlMenuInicio.add(PnlTiposObservaciones);
		PnlMenuInicio.add(PnlPermisos);
		PnlMenuInicio.add(PnlObservaciones);
		PnlObservaciones.setLayout(null);
		
		

		LblObservaciones_title.setForeground(new Color(240, 255, 240));
		LblObservaciones_title.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		LblObservaciones_title.setBounds(12, 13, 210, 29);
		PnlObservaciones.add(LblObservaciones_title);
		
		
		LblObservaciones_Icon.setIcon(new ImageIcon(JFRPrincipal.class.getResource("/recursos/icons/Observacion.png")));
		LblObservaciones_Icon.setBounds(152, 0, 126, 91);
		PnlObservaciones.add(LblObservaciones_Icon);
		PnlMenuInicio.add(PnlRevision);
		PnlRevision.setLayout(null);
		
		JLabel LblRevision_Title = new JLabel("Revision");
		LblRevision_Title.setForeground(new Color(240, 255, 240));
		LblRevision_Title.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		LblRevision_Title.setBounds(12, 13, 210, 29);
		PnlRevision.add(LblRevision_Title);
		LblRevision_Icon.setIcon(new ImageIcon(JFRPrincipal.class.getResource("/recursos/icons/revision.png")));
		LblRevision_Icon.setBounds(155, 0, 123, 84);
		LblRevision_Icon.setVisible(false);
		PnlRevision.add(LblRevision_Icon);
		PnlLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				PnlLogOut.setBackground(new Color(255, 0, 0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				PnlLogOut.setBackground(new Color(46, 139, 87));
			}
		});
		PnlLogOut.setBounds(224, 725, 43, 44);
		PnlLogOut.setBackground(new Color(46, 139, 87));
		
		PnlMenuInicio.add(PnlLogOut);
		PnlLogOut.setLayout(null);
		
		JLabel LblLogOut = new JLabel("");
		LblLogOut.setBounds(0, 0, 44, 43);
		PnlLogOut.add(LblLogOut);

		LblLogOut.setIcon(new ImageIcon(JFRPrincipal.class.getResource("/recursos/icons/LogOut_.png")));
		

		PnlTiposObservaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				PnlTiposObervacion_MouseEntered();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				PnlTiposObervacion_MouseExited();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					PnlTiposObervacion_MouseClicked();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(PnlWorkSpace);
		PnlWorkSpace.setLayout(null);
		contentPane.add(PnlTopMenu);
		

	}
	
	//Mouse Cliked

	private void PnlZonas_MouseClicked() throws NamingException {
		JpZonas jp = new JpZonas();
		jp.setBounds(290, 238, 660, 600);
		jp.setVisible(true);
		jp.setLocation(12,12);
		
		
		
		PnlWorkSpace.removeAll();
		PnlWorkSpace.add(jp);
		
		PnlWorkSpace.revalidate();
		PnlWorkSpace.repaint();
		LblNavegacion.setText("Inicio"+ " - " + "Zonas");
		lblTitulopanel.setText("Mantenimiento de Zonas");
		
		new Thread() {
			public void run() {
				reinicarMenu();
				int x = 278;
				int y = 57;
				while (y >1) {
					x= x -1;
					y--;
					PnlZonas.setSize(x, y);
					PnlMenuInicio_MouseClikedFX(5);
					try {
						sleep(0,1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}	
				}	
				
				PnlZonas.setVisible(false);
				PnlMenuInicio.revalidate();
				PnlMenuInicio.repaint();
				
				reinicarTopMenu();
				PnlTopMenu_1.setVisible(true);
				lblTopMenu_1.setText("Zonas");
				PnlTopMenu_Repaint();
				
			}
		}.start();
		
		
	}
	
	private void PnlUsuarios_MouseClicked() throws NamingException {	
		reinicarMenu();
		
		JpListaUsuarios jp = new JpListaUsuarios();
		jp.setBounds(290, 238, 660, 600);
		jp.setVisible(true);
		jp.setLocation(12,12);
		
		PnlWorkSpace.removeAll();
		PnlWorkSpace.add(jp);
		PnlWorkSpace.revalidate();
		PnlWorkSpace.repaint();
		LblNavegacion.setText("Inicio" + " - " + "Usuarios");
		lblTitulopanel.setText("Mantenimiento de Usuarios");
		new Thread() {
			public void run() {
				reinicarMenu();
				int x = 278;
				int y = 57;
				while (y >1) {
					x= x -3;
					y--;
					PnlUsuarios.setSize(x, y);
					PnlMenuInicio_MouseClikedFX(4);
					try {
						sleep(0,1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}	
				}	
				PnlUsuarios.setVisible(false);
				PnlMenuInicio.revalidate();
				PnlMenuInicio.repaint();
			}
		}.start();
		PnlUsuarios.setVisible(false);
		
		//Revalido y pinto PnlMenuInicio
		PnlMenuInicio.revalidate();
		PnlMenuInicio.repaint();
		reinicarTopMenu();
		PnlTopMenu_1.setVisible(true);
		lblTopMenu_1.setText("Documentos");
		PnlTopMenu_2.setVisible(true);
		lblTopMenu_2.setText("Estados");
		PnlTopMenu_3.setVisible(true);
		lblTopMenu_3.setText("Perfiles");
	}

	
	private void PnlTiposObervacion_MouseClicked() throws NamingException {
		JpTiposObservaciones jp;
		
		jp = new JpTiposObservaciones();
		jp.setBounds(290, 238, 660, 600);
		jp.setVisible(true);
		jp.setLocation(12,12);
		
		PnlWorkSpace.removeAll();
		PnlWorkSpace.add(jp);
		PnlWorkSpace.revalidate();
		PnlWorkSpace.repaint();
		LblNavegacion.setText("Inicio" + " - " + "Tipos de Obervaciones");
		lblTitulopanel.setText("Mantenimiento de Tipos de Obervaciones");
		
		new Thread() {
			public void run() {
				reinicarMenu();
				int x = 278;
				int y = 57;
				while (y >1) {
					x= x -3;
					y--;
					PnlTiposObservaciones.setSize(x, y);
					PnlMenuInicio_MouseClikedFX(3);
					try {
						sleep(0,1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}	
				}	
				PnlTiposObservaciones.setVisible(false);
				PnlMenuInicio.revalidate();
				PnlMenuInicio.repaint();
			}
		}.start();
	}
	
	private void PnlPermisos_MouseClicked() throws NamingException {
		JPanel jp = new JpPermisos();
		jp.setBounds(290, 238, 660, 600);
		jp.setVisible(true);
		jp.setLocation(12,12);
		
		PnlWorkSpace.removeAll();
		PnlWorkSpace.add(jp);
		PnlWorkSpace.revalidate();
		PnlWorkSpace.repaint();
		LblNavegacion.setText("Inicio"+ " - " + "Permisos");
		lblTitulopanel.setText("Mantenimiento de Permisos");
		new Thread() {
			public void run() {
				reinicarMenu();
				int x = 278;
				int y = 57;
				while (y >1) {
					x= x -3;
					y--;
					PnlPermisos.setSize( x, y);
					PnlMenuInicio_MouseClikedFX(2);
					try {
						sleep(0,1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}	
				}	
				PnlPermisos.setVisible(false);
				PnlMenuInicio.revalidate();
				PnlMenuInicio.repaint();
			}
		}.start();
		
		
	}
	
	private void PnlObservacion_MouseClicked() {
		new Thread() {
			public void run() {
				reinicarMenu();
				int x = 278;
				int y = 57;
				while (y >1) {
					x= x -3;
					y--;
					PnlObservaciones.setSize(x, y);
					PnlMenuInicio_MouseClikedFX(1);
					try {
						sleep(0,1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}	
				}	
				PnlObservaciones.setVisible(false);
				PnlMenuInicio.revalidate();
				PnlMenuInicio.repaint();
			}
		}.start();
	}
	
	private void PnlRevision_MouseClicked() {
		new Thread() {
			public void run() {
				reinicarMenu();
				int x = 278;
				int y = 57;
				while (y >1) {
					x= x -3;
					y--;
					PnlRevision.setSize( x, y);
					try {
						sleep(0,1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}	
				}	
				PnlRevision.setVisible(false);
				PnlMenuInicio.revalidate();
				PnlMenuInicio.repaint();
			}
		}.start();
	}
	
	//Mouse Entered
	private void PnlZonas_MouseEntered() {
		PnlZonas.setBackground(new Color(144, 238, 144));
		LblZonas_Icon.setVisible(true);
		LblZonas_Icon.setBounds(177, 0, 89, 80);
		PnlZonas.setSize( 278, 80);
		PnlMenuInicio_MouseEnteredX(5);
	}
	
	private void PnlUsuarios_MouseEntered() {
		PnlUsuarios.setBackground(new Color(144, 238, 144));
		LblUsuario_Icon.setSize(113, 80);
		PnlUsuarios.setSize(278, 80);	
		LblUsuario_Icon.setVisible(true);
		PnlMenuInicio_MouseEnteredX(4);
	}
	
	private void PnlTiposObervacion_MouseEntered() {
		PnlTiposObservaciones.setBackground(new Color(144, 238, 144));
		LblTiposObservaciones_Icon.setVisible(true);
		LblTiposObservaciones_Icon.setSize(113, 80);
		PnlTiposObservaciones.setSize(278, 80);	
		PnlMenuInicio_MouseEnteredX(3);			
	}
	
	private void PnlPermisos_MouseEntered() {
		PnlPermisos.setBackground(new Color(144, 238, 144));
		LblPermisos_Icon.setVisible(true);
		LblPermisos_Icon.setSize(120, 80);
		PnlPermisos.setSize(278, 80);
		PnlMenuInicio_MouseEnteredX(2);
	}
	
	private void PnlObservacion_MouseEntered() {
		PnlObservaciones.setBackground(new Color(144, 238, 144));
		LblObservaciones_Icon.setVisible(true);
		PnlObservaciones.setSize(278, 80);
		PnlMenuInicio_MouseEnteredX(1);
	}
	
	private void PnlRevision_MouseEntered() {
		PnlRevision.setBackground(new Color(144, 238, 144));
		LblRevision_Icon.setVisible(true);
		PnlRevision.setSize(278, 80);
		PnlMenuInicio_repaint();
	}
	
	//Mouse Exited
	private void PnlZonas_MouseExited() {
		PnlZonas.setBackground(new Color(60, 179, 113));
		LblZonas_Icon.setVisible(false);
		PnlZonas.setBounds(0, 121, 278, 57);
		if (PnlZonas.isVisible()) {PnlMenuInicio_MouseExitedFX(5);}
	}
	
	private void PnlUsuarios_MouseExited() {
		PnlUsuarios.setBackground(new Color(60, 179, 113));
		LblUsuario_Icon.setSize(278, 57);
		PnlUsuarios.setSize(278, 57);
		LblUsuario_Icon.setVisible(false);
		if (PnlUsuarios.isVisible()) {PnlMenuInicio_MouseExitedFX(4);}
	}
	
	private void PnlTiposObervacion_MouseExited() {
		PnlTiposObservaciones.setBackground(new Color(60, 179, 113));
		LblTiposObservaciones_Icon.setVisible(false);
		LblTiposObservaciones_Icon.setSize(113, 57);
		PnlTiposObservaciones.setSize(278, 57);
		if (PnlTiposObservaciones.isVisible()) {PnlMenuInicio_MouseExitedFX(3);	}
	}
	
	private void PnlPermisos_MouseExited() {
		PnlPermisos.setBackground(new Color(60, 179, 113));
		LblPermisos_Icon.setVisible(false);
		LblPermisos_Icon.setSize(113, 57);
		PnlPermisos.setSize(278, 57);
		if (PnlPermisos.isVisible()) {PnlMenuInicio_MouseExitedFX(2);}
	}
	
	private void PnlObservacion_MouseExited() {
		PnlObservaciones.setBackground(new Color(60, 179, 113));
		LblObservaciones_Icon.setVisible(false);
		PnlObservaciones.setSize(278, 57);
		if (PnlObservaciones.isVisible()) {PnlMenuInicio_MouseExitedFX(1);}
	}
	
	private void PnlRevision_MouseExited() {
		PnlRevision.setBackground(new Color(60, 179, 113));
		LblRevision_Icon.setVisible(false);
		PnlRevision.setSize(278, 57);
		PnlMenuInicio_repaint();
	}
	
	private void PnlMenuInicio_repaint() {
		PnlMenuInicio.revalidate();
		PnlMenuInicio.repaint();
	}
	
	private void PnlMenuInicio_MouseExitedFX(int fxNumber){
		if (fxNumber > 4) {			PnlUsuarios.setBounds(0, PnlUsuarios.getBounds().y-23, 278, 57);}
		if (fxNumber > 3) {			PnlTiposObservaciones.setBounds(0, PnlTiposObservaciones.getBounds().y-23, 278, 57);}
		if (fxNumber > 2) {			PnlPermisos.setBounds(0, PnlPermisos.getBounds().y-23, 278, 57);}
		if (fxNumber > 1) {			PnlObservaciones.setBounds(0, PnlObservaciones.getBounds().y-23, 278, 57);}
		if (fxNumber >= 1) {			PnlRevision.setBounds(0, PnlRevision.getBounds().y-23, 278, 57);}
		PnlMenuInicio_repaint();
	}
	
	private void PnlMenuInicio_MouseEnteredX(int fxNumber){
		if (fxNumber > 4) {			PnlUsuarios.setBounds(0, PnlUsuarios.getBounds().y+23, 278, 57);}
		if (fxNumber > 3) {			PnlTiposObservaciones.setBounds(0, PnlTiposObservaciones.getBounds().y+23, 278, 57);}
		if (fxNumber > 2) {			PnlPermisos.setBounds(0, PnlPermisos.getBounds().y+23, 278, 57);}
		if (fxNumber > 1) {			PnlObservaciones.setBounds(0, PnlObservaciones.getBounds().y+23, 278, 57);}
		if (fxNumber >= 1) {			PnlRevision.setBounds(0, PnlRevision.getBounds().y+23, 278, 57);}
		PnlMenuInicio_repaint();
	}
	
	private void PnlMenuInicio_MouseClikedFX(int fxNumber) {
		if (fxNumber > 4) {			PnlUsuarios.setBounds(0, PnlUsuarios.getBounds().y-1, 278, 57);}
		if (fxNumber > 3) {			PnlTiposObservaciones.setBounds(0, PnlTiposObservaciones.getBounds().y-1, 278, 57);}
		if (fxNumber > 2) {			PnlPermisos.setBounds(0, PnlPermisos.getBounds().y-1, 278, 57);}
		if (fxNumber > 1) {			PnlObservaciones.setBounds(0, PnlObservaciones.getBounds().y-1, 278, 57);}
		if (fxNumber >= 1) {		PnlRevision.setBounds(0, PnlRevision.getBounds().y-1, 278, 57);}
		PnlMenuInicio_repaint();
	}
	
	private void PnlTopMenu_1_MouseEntered() {
		PnlTopMenu_1.setBackground(new Color(144, 238, 144));
	}
	
	private void PnlTopMenu_1_MouseExited() {
		PnlTopMenu_1.setBackground(new Color(60, 179, 113));
	}
	
	private void PnlTopMenu_2_MouseEntered() {
		PnlTopMenu_2.setBackground(new Color(144, 238, 144));
	}
	
	private void PnlTopMenu_2_MouseExited() {
		PnlTopMenu_2.setBackground(new Color(60, 179, 113));
	}
	
	private void PnlTopMenu_3_MouseEntered() {
		PnlTopMenu_3.setBackground(new Color(144, 238, 144));
	}
	
	private void PnlTopMenu_3_MouseExited() {
		 PnlTopMenu_3.setBackground(new Color(60, 179, 113));
	}
	
	private void PnlTopMenu_4_MouseEntered() {
		PnlTopMenu_4.setBackground(new Color(144, 238, 144));
	}
	
	private void PnlTopMenu_4_MouseExited() {
		PnlTopMenu_4.setBackground(new Color(60, 179, 113));
	}
}
