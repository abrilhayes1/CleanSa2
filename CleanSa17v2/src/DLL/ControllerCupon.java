package DLL;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;



import java.sql.Connection;

import BLL.Cupon;

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
}