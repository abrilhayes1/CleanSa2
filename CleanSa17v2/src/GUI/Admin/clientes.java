package GUI.Admin;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.*;
import DLL.*;

public class clientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Cliente clienteSeleccionado;
    private JTextField inpFiltro;
   
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clientes frame = new clientes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public clientes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.controlShadow);
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSeleccionado = new JLabel("Seleccionado:");
        lblSeleccionado.setBounds(10, 10, 760, 20);
        contentPane.add(lblSeleccionado);

        model = new DefaultTableModel(new String[]{
        	"nombre",
     		"contraseña",
      		"direccion",
      		"dni",
      		"id",
          	"tipo"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 40, 760, 200);
        contentPane.add(scrollPane);

        // Acción al seleccionar fila
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {

         

                	clienteSeleccionado = new Cliente(
                        (String) model.getValueAt(row, 0),
                        (String) model.getValueAt(row, 1),
                        (String) model.getValueAt(row, 2),
                        (String) model.getValueAt(row, 3),
                        (int) model.getValueAt(row, 4),
                        (int) model.getValueAt(row,5)
                        
                        
                    );
                	
                	lblSeleccionado.setText(clienteSeleccionado.toString());
                	
                	
          }
                }
                });
                    inpFiltro = new JTextField();
                    inpFiltro.setBounds(10, 296, 118, 40);
                    contentPane.add(inpFiltro);
                    inpFiltro.setColumns(10);
                    inpFiltro.setVisible(true);
                    JButton btnNewButton = new JButton("Filtrar nombre");
                    btnNewButton.setVisible(true);
                    btnNewButton.addActionListener(new ActionListener() {
                    	public void actionPerformed(ActionEvent e) {
                    		
                    		cargarTablaFiltro(inpFiltro.getText());
                    	}
                    });
                    btnNewButton.setBounds(162, 291, 118, 51);
                    contentPane.add(btnNewButton);
                    
                    JLabel lblNewLabel = new JLabel("Filtro");
                    lblNewLabel.setBounds(10, 271, 117, 14);
                    contentPane.add(lblNewLabel);
                    
                    JButton volver = new JButton("Recargar");
                    volver.addActionListener(new ActionListener() {
                    	public void actionPerformed(ActionEvent e) {
                    		cargarTabla();
                    		
                    	}
                    });
                    volver.setBounds(310, 291, 118, 51);
                    contentPane.add(volver);
            
           
      

        // Cargar datos
        cargarTabla();

        
    }

    private void cargarTabla() {
        model.setRowCount(0);
        ControllerCliente controller = new ControllerCliente(); // NUEVO
        LinkedList<Cliente> clientes = controller.mostrarClientes(); // CAMBIO
        for (Cliente u : clientes) {
            model.addRow(

            		new Object[]{
            				u.getNombre(),
            				u.getContrasena(),
            				u.getDireccion(),
            				u.getDni(),
            				u.getId(),
            				u.getTipo()
            				
            		}
            		);
        }
    }
    
    private void cargarTablaFiltro(String filtro) {
        model.setRowCount(0);
        ControllerCliente controller = new ControllerCliente(); 
        LinkedList<Cliente> clientes = controller.mostrarClientes(); 
        for (Cliente u : clientes) {
            if (u.getNombre().startsWith(filtro)) {
		
        	model.addRow(

            		new Object[]{
            				u.getNombre(),
            				u.getContrasena(),
            				u.getDireccion(),
            				u.getDni(),
            				u.getId(),
            				u.getTipo()
            				
            		}
            		);
        }
    		
		}
    }

}