package GUI.AdminVentas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BLL.Categoria;
import BLL.Producto;
import BLL.Cupon;
import DLL.ControllerCategoria;
import DLL.ControllerProducto;
import DLL.ControllerCupon;
import GUI.common.BaseFrame;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.Date;

public class vistaCrearCupon extends BaseFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JFrame ventanaAnterior;

	private JTextField txtCodigo;
	private JTextField txtValor;
	private JComboBox<String> cmbTipo;
	private JComboBox<String> cmbAlcance;

	private JComboBox<Categoria> cmbCategoria;
	private JComboBox<Producto> cmbProducto;

	private JTextField txtDesde; // yyyy-mm-dd (opcional)
	private JTextField txtHasta; // yyyy-mm-dd (opcional)

	private JLabel lblMensaje;

	public vistaCrearCupon(JFrame anterior) {
		super();
		this.ventanaAnterior = anterior;

		setTitle("Agregar Cupón");

		contentPane = new JPanel(new BorderLayout(10, 10));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		setContentPane(contentPane);

		// ===== TOP: Logo =====
		JLabel logo = new JLabel(new ImageIcon(vistaCrearCupon.class.getResource("/img/logo.png")));
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(logo, BorderLayout.NORTH);

		// ===== CENTER: Form centrado =====
		JPanel centerWrapper = new JPanel(new GridBagLayout());
		JPanel form = new JPanel(new GridBagLayout());
		form.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(6, 6, 6, 6);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel titulo = new JLabel("Crear cupón");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		form.add(titulo, gbc);

		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;

		// Código
		gbc.gridx = 0;
		gbc.gridy = 1;
		form.add(new JLabel("Código"), gbc);

		txtCodigo = new JTextField(18);
		gbc.gridx = 1;
		gbc.gridy = 1;
		form.add(txtCodigo, gbc);

		// Tipo
		gbc.gridx = 0;
		gbc.gridy = 2;
		form.add(new JLabel("Tipo"), gbc);

		cmbTipo = new JComboBox<>(new String[] { "Porcentaje (%)", "Monto fijo ($)" });
		gbc.gridx = 1;
		gbc.gridy = 2;
		form.add(cmbTipo, gbc);

		// Valor
		gbc.gridx = 0;
		gbc.gridy = 3;
		form.add(new JLabel("Valor"), gbc);

		txtValor = new JTextField(18);
		gbc.gridx = 1;
		gbc.gridy = 3;
		form.add(txtValor, gbc);

		// Alcance
		gbc.gridx = 0;
		gbc.gridy = 4;
		form.add(new JLabel("Alcance"), gbc);

		cmbAlcance = new JComboBox<>(new String[] { "Carrito", "Categoría", "Producto" });
		gbc.gridx = 1;
		gbc.gridy = 4;
		form.add(cmbAlcance, gbc);

		// Objetivo (categoria / producto)
		gbc.gridx = 0;
		gbc.gridy = 5;
		form.add(new JLabel("Categoría"), gbc);

		cmbCategoria = new JComboBox<>();
		gbc.gridx = 1;
		gbc.gridy = 5;
		form.add(cmbCategoria, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		form.add(new JLabel("Producto"), gbc);

		cmbProducto = new JComboBox<>();
		gbc.gridx = 1;
		gbc.gridy = 6;
		form.add(cmbProducto, gbc);

		// Fechas
		gbc.gridx = 0;
		gbc.gridy = 7;
		form.add(new JLabel("Desde (yyyy-mm-dd)"), gbc);

		txtDesde = new JTextField(18);
		gbc.gridx = 1;
		gbc.gridy = 7;
		form.add(txtDesde, gbc);

		gbc.gridx = 0;
		gbc.gridy = 8;
		form.add(new JLabel("Hasta (yyyy-mm-dd)"), gbc);

		txtHasta = new JTextField(18);
		gbc.gridx = 1;
		gbc.gridy = 8;
		form.add(txtHasta, gbc);

		// Botón Crear
		JButton btnCrear = new JButton("Guardar cupón");
		btnCrear.setFont(new Font("Segoe UI", Font.BOLD, 14));
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		form.add(btnCrear, gbc);

		// Mensaje
		lblMensaje = new JLabel("");
		lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensaje.setVisible(false);
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.gridwidth = 2;
		form.add(lblMensaje, gbc);

		centerWrapper.add(form);
		contentPane.add(centerWrapper, BorderLayout.CENTER);

		// ===== SOUTH: Volver abajo derecha =====
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
		bottom.add(btnVolver);
		contentPane.add(bottom, BorderLayout.SOUTH);

		// Cargar combos
		cargarCategorias();
		cargarProductos();

		// Mostrar/ocultar objetivo según alcance
		actualizarObjetivoSegunAlcance();
		cmbAlcance.addActionListener(e -> actualizarObjetivoSegunAlcance());

		// Acciones
		btnVolver.addActionListener(e -> {
			if (ventanaAnterior != null)
				ventanaAnterior.setVisible(true);
			dispose();
		});

		btnCrear.addActionListener(e -> guardarCupon());
	}

	public vistaCrearCupon() {
		this(null);
	}

	private void cargarCategorias() {
		cmbCategoria.removeAllItems();
		ControllerCategoria cc = new ControllerCategoria();
		LinkedList<Categoria> cats = cc.mostrarCategorias();
		for (Categoria c : cats)
			cmbCategoria.addItem(c);
	}

	private void cargarProductos() {
		cmbProducto.removeAllItems();
		ControllerProducto cp = new ControllerProducto();
		LinkedList<Producto> prods = cp.mostrarProductos();
		for (Producto p : prods)
			cmbProducto.addItem(p);
	}

	private void actualizarObjetivoSegunAlcance() {
		int a = cmbAlcance.getSelectedIndex(); // 0 carrito, 1 categoria, 2 producto
		cmbCategoria.setEnabled(a == 1);
		cmbProducto.setEnabled(a == 2);
	}

	private Date parseFecha(String s) {
		if (s == null)
			return null;
		s = s.trim();
		if (s.isEmpty())
			return null;

		try {
			LocalDate ld = LocalDate.parse(s); // yyyy-mm-dd
			return java.sql.Timestamp.valueOf(ld.atStartOfDay());
		} catch (DateTimeParseException ex) {
			return null;
		}
	}

	private void guardarCupon() {
		String codigo = txtCodigo.getText().trim();
		String valorStr = txtValor.getText().trim();

		if (codigo.isEmpty() || valorStr.isEmpty()) {
			mostrarMsg("Código y valor son obligatorios.", Color.RED);
			return;
		}

		double valor;
		try {
			valor = Double.parseDouble(valorStr);
		} catch (Exception ex) {
			mostrarMsg("El valor debe ser numérico.", Color.RED);
			return;
		}

		int tipo = (cmbTipo.getSelectedIndex() == 0) ? 1 : 2;
		int alcance = cmbAlcance.getSelectedIndex() + 1; // 1 carrito, 2 categoria, 3 producto

		Integer idCategoria = null;
		Integer idProducto = null;

		if (alcance == 2) {
			Categoria c = (Categoria) cmbCategoria.getSelectedItem();
			if (c == null) {
				mostrarMsg("Elegí una categoría.", Color.RED);
				return;
			}
			idCategoria = c.getId_categoria();
		}

		if (alcance == 3) {
			Producto p = (Producto) cmbProducto.getSelectedItem();
			if (p == null) {
				mostrarMsg("Elegí un producto.", Color.RED);
				return;
			}
			// Ajustá esto si tu Producto usa otro getter para el ID
			idProducto = p.getId();
		}

		Date desde = parseFecha(txtDesde.getText());
		if (!txtDesde.getText().trim().isEmpty() && desde == null) {
			mostrarMsg("Fecha DESDE inválida. Usá yyyy-mm-dd.", Color.RED);
			return;
		}

		Date hasta = parseFecha(txtHasta.getText());
		if (!txtHasta.getText().trim().isEmpty() && hasta == null) {
			mostrarMsg("Fecha HASTA inválida. Usá yyyy-mm-dd.", Color.RED);
			return;
		}

		Cupon nuevo = new Cupon(0, codigo, tipo, valor, alcance, idCategoria, idProducto, 1, // activo por defecto
				desde, hasta);

		ControllerCupon controller = new ControllerCupon();
		boolean ok = controller.crearCupon(nuevo);

		if (ok) {
			mostrarMsg("Cupón creado correctamente.", new Color(0, 128, 0));
			txtCodigo.setText("");
			txtValor.setText("");
			txtDesde.setText("");
			txtHasta.setText("");
			cmbTipo.setSelectedIndex(0);
			cmbAlcance.setSelectedIndex(0);
			actualizarObjetivoSegunAlcance();
		} else {
			mostrarMsg("Error al crear el cupón (¿código repetido?).", Color.RED);
		}
	}

	private void mostrarMsg(String msg, Color color) {
		lblMensaje.setText(msg);
		lblMensaje.setForeground(color);
		lblMensaje.setVisible(true);
	}
}
