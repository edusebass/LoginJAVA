import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class loginusuario {
    public JPanel rootusuario;
    public JTextField userloginField1;
    private JButton iniciarSesionButton;
    private JButton registrarseButton;
    private JPasswordField passwordloginField1;

    public String usuario;


    public loginusuario(String usuario) {
        this.usuario = usuario;
    }

    public loginusuario() {
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File archivo = new File("src/usuarios.dat");
                    Scanner scanner = new Scanner(archivo);
                    String usuario = userloginField1.getText();
                    System.out.println(usuario);
                    String password = new String(passwordloginField1.getPassword());
                    boolean usuarioExiste = false;

                    while (scanner.hasNextLine()) {
                        String linea = scanner.nextLine();
                        String[] datos = linea.split(",");
                        if (datos.length == 2 && datos[0].equals(usuario) && datos[1].equals(password)) {
                            usuarioExiste = true;
                            break;
                        }
                    }

                    scanner.close();

                    if (usuarioExiste) {
                        Window mainWindow = SwingUtilities.getWindowAncestor(iniciarSesionButton);

                        // Cerrar la ventana principal
                        if (mainWindow != null) {
                            mainWindow.dispose();
                        }



                        JFrame frame = new JFrame("paginaprinicipal");
                        frame.setContentPane(new paginaprinicipal(usuario).pagemain);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setSize(250, 300);
                        frame.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectos\n" +
                                "Ingrese nuevamente credenciales");
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window mainWindow = SwingUtilities.getWindowAncestor(registrarseButton);

                // Cerrar la ventana principal
                if (mainWindow != null) {
                    mainWindow.dispose();
                }
                JFrame frame = new JFrame("registroUsuarios");
                frame.setContentPane(new registroUsuarios().rootpanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setSize(250, 300);
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("loginusuario");
        frame.setContentPane(new loginusuario().rootusuario);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(250, 300);
        frame.setVisible(true);
    }
}
