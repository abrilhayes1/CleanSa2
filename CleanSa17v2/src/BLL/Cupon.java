package BLL;

import java.util.Date;

public class Cupon {
    private int id_cupon;
    private String codigo;
    private int tipo;      // 1 porcentaje, 2 fijo
    private double valor;
    private int alcance;   // 1 carrito, 2 categoria, 3 producto
    private Integer id_categoria; // puede ser null
    private Integer id_producto;  // puede ser null
    private int activo;    // 1 activo, 0 no
    private Date fecha_desde;
    private Date fecha_hasta;

    public Cupon(int id_cupon, String codigo, int tipo, double valor, int alcance,
                 Integer id_categoria, Integer id_producto, int activo,
                 Date fecha_desde, Date fecha_hasta) {
        this.id_cupon = id_cupon;
        this.codigo = codigo;
        this.tipo = tipo;
        this.valor = valor;
        this.alcance = alcance;
        this.id_categoria = id_categoria;
        this.id_producto = id_producto;
        this.activo = activo;
        this.fecha_desde = fecha_desde;
        this.fecha_hasta = fecha_hasta;
    }

    public int getId_cupon() { return id_cupon; }
    public String getCodigo() { return codigo; }
    public int getTipo() { return tipo; }
    public double getValor() { return valor; }
    public int getAlcance() { return alcance; }
    public Integer getId_categoria() { return id_categoria; }
    public Integer getId_producto() { return id_producto; }
    public int getActivo() { return activo; }
    public Date getFecha_desde() { return fecha_desde; }
    public Date getFecha_hasta() { return fecha_hasta; }
}






