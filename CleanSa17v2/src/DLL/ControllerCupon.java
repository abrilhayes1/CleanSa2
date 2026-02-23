package DLL;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.sql.Connection;

import BLL.Cupon;
import BLL.ItemVenta;

public class ControllerCupon {

    private static Connection con = Conexion.getInstance().getConnection();

    public Cupon buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM cupones WHERE codigo = ? LIMIT 1";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Integer idCategoria = (Integer) rs.getObject("id_categoria");
                Integer idProducto  = (Integer) rs.getObject("id_producto");

                return new Cupon(
                    rs.getInt("id_cupon"),
                    rs.getString("codigo"),
                    rs.getInt("tipo"),
                    rs.getDouble("valor"),
                    rs.getInt("alcance"),
                    idCategoria,
                    idProducto,
                    rs.getInt("activo"),
                    rs.getTimestamp("fecha_desde"),
                    rs.getTimestamp("fecha_hasta")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public String validarCupon(Cupon c) {

        if (c == null)
            return "El cupón no existe.";

        if (c.getActivo() != 1)
            return "El cupón está desactivado.";

        LocalDate hoy = LocalDate.now();

        Date dDesde = c.getFecha_desde();
        Date dHasta = c.getFecha_hasta();

        if (dDesde != null) {
            LocalDate fechaDesde = dDesde.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            if (hoy.isBefore(fechaDesde))
                return "El cupón todavía no está vigente.";
        }

        if (dHasta != null) {
            LocalDate fechaHasta = dHasta.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            if (hoy.isAfter(fechaHasta))
                return "El cupón está vencido.";
        }

        return null;
    }
    
    public double calcularDescuento(Cupon c, double base) {
        if (c == null) return 0;

        if (base <= 0) return 0;

        if (c.getTipo() == 1) { // porcentaje
            return base * (c.getValor() / 100.0);
        }

        // tipo == 2 (monto fijo)
        return Math.min(c.getValor(), base); // nunca más que la base
    }
    
    public double calcularBaseSegunAlcance(Cupon c, List<ItemVenta> items, double totalCarrito) {
        if (c == null) return 0;

        // Carrito
        if (c.getAlcance() == 1) {
            return totalCarrito;
        }

        // Categoría
        if (c.getAlcance() == 2 && c.getId_categoria() != null) {
            double base = 0;
            for (ItemVenta it : items) {
                if (it.getIdCategoria() == c.getId_categoria()) {
                    base += it.getSubtotal();
                }
            }
            return base;
        }

        // Producto
        if (c.getAlcance() == 3 && c.getId_producto() != null) {
            double base = 0;
            for (ItemVenta it : items) {
                if (it.getIdProducto() == c.getId_producto()) {
                    base += it.getSubtotal();
                }
            }
            return base;
        }

        return 0;
    }

    public double aplicarCupon(String codigo, List<ItemVenta> items, double totalCarrito) {
        Cupon c = buscarPorCodigo(codigo);
        String error = validarCupon(c);
        if (error != null) return 0;

        double base = calcularBaseSegunAlcance(c, items, totalCarrito);
        return calcularDescuento(c, base);
    }
    
    public boolean crearCupon(Cupon c) {
        String sql = "INSERT INTO cupones " +
                "(codigo, tipo, valor, alcance, id_categoria, id_producto, activo, fecha_desde, fecha_hasta) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getCodigo());
            ps.setInt(2, c.getTipo());
            ps.setDouble(3, c.getValor());
            ps.setInt(4, c.getAlcance());

            if (c.getId_categoria() == null) ps.setNull(5, java.sql.Types.INTEGER);
            else ps.setInt(5, c.getId_categoria());

            if (c.getId_producto() == null) ps.setNull(6, java.sql.Types.INTEGER);
            else ps.setInt(6, c.getId_producto());

            ps.setInt(7, c.getActivo());

            if (c.getFecha_desde() == null) ps.setNull(8, java.sql.Types.TIMESTAMP);
            else ps.setTimestamp(8, new Timestamp(c.getFecha_desde().getTime()));

            if (c.getFecha_hasta() == null) ps.setNull(9, java.sql.Types.TIMESTAMP);
            else ps.setTimestamp(9, new Timestamp(c.getFecha_hasta().getTime()));

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}