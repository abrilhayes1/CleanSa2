package GUI.Cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import BLL.Carrito;
import BLL.Cliente;
import BLL.ItemVenta;
import DLL.ControllerCarrito;
import DLL.ControllerCupon;
import GUI.common.BaseFrame;

public class CarritosEnProcesoCliente extends BaseFrame {

    private static final long serialVersionUID = 1L;

    public CarritosEnProcesoCliente(Cliente cliente, Carrito carrito) {

        // ===== Ventana / contenedor =====
        JPanel root = new JPanel(new BorderLayout(0, 12));
        root.setBorder(new EmptyBorder(16, 16, 16, 16));
        root.setBackground(new Color(200, 200, 200));
        setContentPane(root);

        // ===== Header con logo =====
        JLabel logo = new JLabel();
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setIcon(new ImageIcon(CarritosEnProcesoCliente.class.getResource("/img/logo.png")));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.add(logo, BorderLayout.CENTER);
        root.add(header, BorderLayout.NORTH);

        // ===== Panel central (tarjeta) =====
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(new Color(235, 235, 235));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(170, 170, 170)),
                new EmptyBorder(14, 14, 14, 14)
        ));
        root.add(card, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 13);
        Font valueFont = new Font("Segoe UI", Font.BOLD, 13);

        // Helpers
        java.util.function.BiConsumer<Integer, String> addLabel = (row, text) -> {
            JLabel l = new JLabel(text);
            l.setFont(labelFont);
            gbc.gridx = 0;
            gbc.gridy = row;
            gbc.weightx = 0;
            card.add(l, gbc);
        };

        java.util.function.BiConsumer<Integer, String> addValue = (row, text) -> {
            JLabel v = new JLabel(text);
            v.setFont(valueFont);
            gbc.gridx = 1;
            gbc.gridy = row;
            gbc.weightx = 1;
            card.add(v, gbc);
        };

        int row = 0;
        addLabel.accept(row, "ID carrito:");
        addValue.accept(row++, String.valueOf(carrito.getId_carrito()));

        addLabel.accept(row, "Fecha:");
        addValue.accept(row++, String.valueOf(carrito.getFecha()));

        addLabel.accept(row, "Estado:");
        addValue.accept(row++, String.valueOf(carrito.getEstado()));

        addLabel.accept(row, "Total:");
        addValue.accept(row++, String.valueOf(carrito.getTotal_compra()));

        addLabel.accept(row, "Código de envío:");
        addValue.accept(row++, String.valueOf(carrito.getCodigo_envio()));

        addLabel.accept(row, "FK cliente:");
        addValue.accept(row++, String.valueOf(carrito.getFk_cliente()));


        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 8, 10, 8);
        card.add(new JLabel(" "), gbc);
        gbc.gridwidth = 1;
        gbc.insets = new Insets(6, 8, 6, 8);


        JLabel lblCupon = new JLabel("Cupón:");
        lblCupon.setFont(labelFont);

        JTextField txtCupon = new JTextField();
        txtCupon.setPreferredSize(new Dimension(200, 28));

        JButton btnAplicarCupon = new JButton("Aplicar");
        btnAplicarCupon.setPreferredSize(new Dimension(110, 28));

        JPanel cuponRow = new JPanel();
        cuponRow.setOpaque(false);
        cuponRow.setLayout(new BoxLayout(cuponRow, BoxLayout.X_AXIS));
        cuponRow.add(lblCupon);
        cuponRow.add(Box.createHorizontalStrut(10));
        cuponRow.add(txtCupon);
        cuponRow.add(Box.createHorizontalStrut(10));
        cuponRow.add(btnAplicarCupon);

        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        card.add(cuponRow, gbc);
        gbc.gridwidth = 1;

        // ===== Resultados =====
        JLabel lblDescuento = new JLabel("Descuento: 0");
        lblDescuento.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDescuento.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel lblTotalFinal = new JLabel("Total final: " + carrito.getTotal_compra());
        lblTotalFinal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTotalFinal.setForeground(new Color(0, 110, 0));
        lblTotalFinal.setHorizontalAlignment(SwingConstants.LEFT);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        card.add(lblDescuento, gbc);

        gbc.gridy = row + 1;
        card.add(lblTotalFinal, gbc);
        gbc.gridwidth = 1;


        btnAplicarCupon.addActionListener((ActionEvent e) -> {
            String codigo = txtCupon.getText().trim();

            if (codigo.isEmpty()) {
                lblDescuento.setText("Descuento: 0");
                lblTotalFinal.setText("Total final: " + carrito.getTotal_compra());
                lblTotalFinal.setForeground(new Color(0, 110, 0));
                return;
            }

            ControllerCarrito controllerCarrito = new ControllerCarrito();
            List<ItemVenta> items = controllerCarrito.obtenerItemsCarritoEnProceso(cliente.getId());

            ControllerCupon controllerCupon = new ControllerCupon();
            double descuento = controllerCupon.aplicarCupon(codigo, items, carrito.getTotal_compra());

            double totalFinal = carrito.getTotal_compra() - descuento;

            lblDescuento.setText("Descuento: " + descuento);
            lblTotalFinal.setText("Total final: " + totalFinal);

            if (descuento > 0) {
                lblTotalFinal.setForeground(new Color(0, 110, 0));
            } else {
                lblTotalFinal.setForeground(new Color(90, 90, 90));

                 JOptionPane.showMessageDialog(null, "No se pudo aplicar el cupón.");
            }
        });

        // ===== Botonera abajo =====
        JPanel footer = new JPanel();
        footer.setOpaque(false);
        footer.setLayout(new BoxLayout(footer, BoxLayout.X_AXIS));

        JButton btnDetalle = new JButton("Ver detalle");
        JButton btnSalir = new JButton("Salir");

        btnDetalle.setPreferredSize(new Dimension(140, 34));
        btnSalir.setPreferredSize(new Dimension(140, 34));

        footer.add(btnDetalle);
        footer.add(Box.createHorizontalGlue());
        footer.add(btnSalir);

        root.add(footer, BorderLayout.SOUTH);

        btnSalir.addActionListener(e -> {
            generalCliente generalcliente = new generalCliente(cliente);
            generalcliente.setVisible(true);
            dispose();
        });

        btnDetalle.addActionListener(e -> {
            CarritosDetalle carritosdetalle = new CarritosDetalle(cliente, carrito.getId_carrito());
            carritosdetalle.setVisible(true);
            dispose();
        });

        pack();                       // ajusta el tamaño a lo que necesitan los componentes
        setMinimumSize(new Dimension(720, 520)); // evita que quede demasiado chico
        setLocationRelativeTo(null);  // centra la ventana
    }
}