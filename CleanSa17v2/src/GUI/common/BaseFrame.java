package GUI.common;

import javax.swing.JFrame;

public class BaseFrame extends JFrame {

    protected static final int WIDTH = 800;
    protected static final int HEIGHT = 500;

    public BaseFrame() {
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // CENTRAR VENTANA
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
