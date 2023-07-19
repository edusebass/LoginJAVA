import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class registroUsuarios {
    private JTextField usuarioField1;
    private JButton registrarButton;
    private JButton iniciarSesionButton;
    private JPasswordField passwordField1;
    public JPanel rootpanel;

    public registroUsuarios() {
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File archivo = new File("src/usuarios.dat");
                    Scanner scanner = new Scanner(archivo);
                    String usuario = usuarioField1.getText();
                    String password = new String(passwordField1.getPassword());
                    boolean usuarioExiste = false;

                    while (scanner.hasNextLine()) {
                        String linea = scanner.nextLine();
                        String[] datos = linea.split(",");
                        if (datos.length == 2 && datos[0].equals(usuario)) {
                            usuarioExiste = true;
                            break;
                        }
                    }

                    scanner.close();

                    if (usuarioExiste) {
                        JOptionPane.showMessageDialog(null, "El usuario ya existe en el archivo\nIngresa nuevamente credenciales");
                        usuarioField1.setText("");
                        passwordField1.setText("");
                    } else {
                        FileWriter writer = new FileWriter("src/usuarios.dat", true);
                        writer.write(usuario + "," + password + "\n");
                        writer.close();
                        JOptionPane.showMessageDialog(null, "Usuario guardado correctamente.");
                        usuarioField1.setText("");
                        passwordField1.setText("");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la ventana principal
                Window mainWindow = SwingUtilities.getWindowAncestor(iniciarSesionButton);

                // Cerrar la ventana principal
                if (mainWindow != null) {
                    mainWindow.dispose();
                }

                JFrame frame = new JFrame("loginusuario");
                frame.setContentPane(new loginusuario().rootusuario);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("registroUsuarios");
        frame.setContentPane(new registroUsuarios().rootpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();frame.setLocationRelativeTo(null);
        frame.setSize(250, 300);
        frame.setVisible(true);
    }
}
