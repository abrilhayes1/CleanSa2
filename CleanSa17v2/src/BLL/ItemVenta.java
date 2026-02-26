package BLL;

public class ItemVenta {

    private int idProducto;
    private int idCategoria;
    private double subtotal;

    public ItemVenta(int idProducto, int idCategoria, double subtotal) {
        this.idProducto = idProducto;
        this.idCategoria = idCategoria;
        this.subtotal = subtotal;
    }

    public int getIdProducto() { return idProducto; }
    public int getIdCategoria() { return idCategoria; }
    public double getSubtotal() { return subtotal; }
}