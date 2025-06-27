
package gui;

import data.BaseDatos;
import data.Trabajador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainView extends JFrame {
    private final BaseDatos baseDatos;
    private JTable tablaTrabajadores;
    private DefaultTableModel modeloTabla;
    private JTextField txtNombre, txtApellido, txtRut, txtIsapre, txtAfp;
    private JButton btnAgregar, btnBuscar, btnEliminar, btnLimpiar;
    private JButton btnEditarNombre, btnEditarApellido, btnEditarIsapre, btnEditarAfp;

    public MainView() {
        this.baseDatos = new BaseDatos();
        configurarVentana();
        inicializarComponentes();
        actualizarTabla();
    }

    private void configurarVentana() {
        setTitle("Sistema de Trabajadores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        // Panel principal con BoxLayout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Panel de formulario
        JPanel formulario = new JPanel(new GridLayout(5, 2, 5, 5));
        formulario.setBorder(BorderFactory.createTitledBorder("Datos del Trabajador"));

        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtRut = new JTextField();
        txtIsapre = new JTextField();
        txtAfp = new JTextField();

        formulario.add(new JLabel("Nombre:"));
        formulario.add(txtNombre);
        formulario.add(new JLabel("Apellido:"));
        formulario.add(txtApellido);
        formulario.add(new JLabel("RUT:"));
        formulario.add(txtRut);
        formulario.add(new JLabel("Isapre:"));
        formulario.add(txtIsapre);
        formulario.add(new JLabel("AFP:"));
        formulario.add(txtAfp);

        // Panel de botones principales
        JPanel botonesPrincipales = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar");
        btnBuscar = new JButton("Buscar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        botonesPrincipales.add(btnAgregar);
        botonesPrincipales.add(btnBuscar);
        botonesPrincipales.add(btnEliminar);
        botonesPrincipales.add(btnLimpiar);

        // Panel de botones de edición
        JPanel botonesEdicion = new JPanel(new FlowLayout());
        botonesEdicion.setBorder(BorderFactory.createTitledBorder("Opciones de Edición"));
        btnEditarNombre = new JButton("Editar Nombre");
        btnEditarApellido = new JButton("Editar Apellido");
        btnEditarIsapre = new JButton("Editar Isapre");
        btnEditarAfp = new JButton("Editar AFP");

        botonesEdicion.add(btnEditarNombre);
        botonesEdicion.add(btnEditarApellido);
        botonesEdicion.add(btnEditarIsapre);
        botonesEdicion.add(btnEditarAfp);

        // Tabla de trabajadores
        String[] columnas = {"RUT", "Nombre", "Apellido", "Isapre", "AFP"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaTrabajadores = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaTrabajadores);
        scrollPane.setPreferredSize(new Dimension(750, 200));

        // Agregar todos los componentes al panel principal
        panel.add(Box.createVerticalStrut(10));
        panel.add(formulario);
        panel.add(Box.createVerticalStrut(10));
        panel.add(botonesPrincipales);
        panel.add(Box.createVerticalStrut(10));
        panel.add(botonesEdicion);
        panel.add(Box.createVerticalStrut(10));
        panel.add(scrollPane);
        panel.add(Box.createVerticalStrut(10));

        // Configurar eventos
        configurarEventos();

        // Agregar panel principal a la ventana con márgenes
        JPanel panelConMargen = new JPanel(new BorderLayout());
        panelConMargen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelConMargen.add(panel, BorderLayout.CENTER);
        add(panelConMargen);
    }

    // [El resto del código permanece igual...]

    private void configurarEventos() {
        btnAgregar.addActionListener(e -> agregarTrabajador());
        btnBuscar.addActionListener(e -> buscarTrabajador());
        btnEliminar.addActionListener(e -> eliminarTrabajador());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnEditarNombre.addActionListener(e -> editarNombre());
        btnEditarApellido.addActionListener(e -> editarApellido());
        btnEditarIsapre.addActionListener(e -> editarIsapre());
        btnEditarAfp.addActionListener(e -> editarAfp());

        // Guardar datos al cerrar la ventana
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                baseDatos.guardarDatos();
            }
        });
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        List<Trabajador> trabajadores = baseDatos.obtenerTrabajadores();
        for (Trabajador t : trabajadores) {
            Object[] fila = {t.getRut(), t.getNombre(), t.getApellido(), t.getIsapre(), t.getAfp()};
            modeloTabla.addRow(fila);
        }
    }

    private void agregarTrabajador() {
        try {
            String rut = txtRut.getText();
            if (baseDatos.buscarPorRut(rut) != null) {
                JOptionPane.showMessageDialog(this, "Ya existe un trabajador con ese RUT");
                return;
            }

            Trabajador trabajador = new Trabajador(
                    txtNombre.getText(),
                    txtApellido.getText(),
                    rut,
                    txtIsapre.getText(),
                    txtAfp.getText()
            );

            baseDatos.agregarTrabajador(trabajador);
            actualizarTabla();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Trabajador agregado exitosamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar trabajador");
        }
    }

    private void buscarTrabajador() {
        String rut = JOptionPane.showInputDialog(this, "Ingrese el RUT del trabajador:");
        if (rut != null && !rut.isEmpty()) {
            Trabajador trabajador = baseDatos.buscarPorRut(rut);
            if (trabajador != null) {
                txtNombre.setText(trabajador.getNombre());
                txtApellido.setText(trabajador.getApellido());
                txtRut.setText(trabajador.getRut());
                txtIsapre.setText(trabajador.getIsapre());
                txtAfp.setText(trabajador.getAfp());
            } else {
                JOptionPane.showMessageDialog(this, "Trabajador no encontrado");
            }
        }
    }

    private void eliminarTrabajador() {
        String rut = JOptionPane.showInputDialog(this, "Ingrese el RUT del trabajador a eliminar:");
        if (rut != null && !rut.isEmpty()) {
            Trabajador trabajador = baseDatos.buscarPorRut(rut);
            if (trabajador != null) {
                baseDatos.eliminarTrabajador(rut);
                actualizarTabla();
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Trabajador eliminado exitosamente");
            } else {
                JOptionPane.showMessageDialog(this, "Trabajador no encontrado");
            }
        }
    }

    private void editarNombre() {
        editarCampo("nombre");
    }

    private void editarApellido() {
        editarCampo("apellido");
    }

    private void editarIsapre() {
        editarCampo("isapre");
    }

    private void editarAfp() {
        editarCampo("afp");
    }

    private void editarCampo(String campo) {
        String rut = JOptionPane.showInputDialog(this, "Ingrese el RUT del trabajador a editar:");
        if (rut != null && !rut.isEmpty()) {
            Trabajador trabajador = baseDatos.buscarPorRut(rut);
            if (trabajador != null) {
                String nuevoValor = JOptionPane.showInputDialog(this, "Ingrese nuevo " + campo + ":");
                if (nuevoValor != null && !nuevoValor.isEmpty()) {
                    switch (campo) {
                        case "nombre" -> trabajador.setNombre(nuevoValor);
                        case "apellido" -> trabajador.setApellido(nuevoValor);
                        case "isapre" -> trabajador.setIsapre(nuevoValor);
                        case "afp" -> trabajador.setAfp(nuevoValor);
                    }
                    baseDatos.actualizarTrabajador(trabajador);
                    actualizarTabla();
                    JOptionPane.showMessageDialog(this, campo.substring(0, 1).toUpperCase() +
                            campo.substring(1) + " actualizado exitosamente");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Trabajador no encontrado");
            }
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtRut.setText("");
        txtIsapre.setText("");
        txtAfp.setText("");
    }
}