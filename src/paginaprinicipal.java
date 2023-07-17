import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;


public class paginaprinicipal extends loginusuario {
    public JLabel usuariologeado;
    public JPanel pagemain;





    public paginaprinicipal(String usuario) {
        super(usuario);

        usuariologeado.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                // Edita el texto del JLabel con el contenido deseado
                usuariologeado.setText("Bienvenido: " + usuario);
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("paginaprinicipal");
        frame.setContentPane(new paginaprinicipal("").pagemain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        System.out.println();
    }
}
