package GUI.Cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Carrito;
import GUI.Admin.LoginAdmin;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class verCarrito extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;

	public verCarrito(Carrito carrito) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Modelo de datos para la tabla
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID Carrito" + carrito.getId_carrito());
		model.addColumn("Cliente " + carrito.getFk_cliente());
		model.addColumn("Total compra: " + carrito.getTotal_compra());

		// Botón para salir
		btnNewButton = new JButton("Salir");
		btnNewButton.setBounds(10, 240, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginAdmin ventana = new LoginAdmin();
				ventana.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);
	}
}