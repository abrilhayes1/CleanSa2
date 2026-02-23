package BLL;

public class ItemVenta {
    private int idProducto;
    private int idCategoria;
    private double precioUnitario;
    private int cantidad;

    public ItemVenta(int idProducto, int idCategoria, double precioUnitario, int cantidad) {
        this.idProducto = idProducto;
        this.idCategoria = idCategoria;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public int getIdProducto() { return idProducto; }
    public int getIdCategoria() { return idCategoria; }
    public double getPrecioUnitario() { return precioUnitario; }
    public int getCantidad() { return cantidad; }

    public double getSubtotal() { return precioUnitario * cantidad; }
}