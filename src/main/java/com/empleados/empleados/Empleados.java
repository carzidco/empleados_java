package com.empleados.empleados;

/**
 *
 * @author Johel Gomez
 * 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Empleados extends JFrame {
    final private ArrayList<Empleado> empleados;
    final private JPanel panelPrincipal;
    private JPanel panelAgregarEmpleado;
    private JPanel panelActualizarSalario;
    private JPanel panelVisualizarSalarios;
    private JComboBox<Empleado> dropdownEmpleado;
    private static JTable tablaSalarios;
    private static JLabel labelNoHayEmpleados;

    public Empleados() {
        setTitle("Sistema de Empleados");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        empleados = new ArrayList<>();

        panelPrincipal = new JPanel(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menuEmpleados = new JMenu("Inicio");
        
        JMenuItem agregarItemEmpleado = new JMenuItem("Agregar Empleado");
        agregarItemEmpleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPanelAgregarEmpleado();
            }
        });
        
        JMenuItem agregarItemSalario = new JMenuItem("Agregar Salario");
        agregarItemSalario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPanelAgregarSalario();
            }
        });
        
        JMenuItem agregarItemVisualizarSalarios = new JMenuItem("Visualizar Salarios");
        agregarItemVisualizarSalarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPanelVisualizarSalario();
            }
        });
        
        menuEmpleados.add(agregarItemEmpleado);
        menuEmpleados.add(agregarItemSalario);
        menuEmpleados.add(agregarItemVisualizarSalarios);
        
        menuBar.add(menuEmpleados);
        setJMenuBar(menuBar);

        add(panelPrincipal);
    }

    private void mostrarPanelAgregarEmpleado() {
        
        panelAgregarEmpleado = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelAgregarEmpleado.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField campoNombre = new JTextField();
        campoNombre.setPreferredSize(new Dimension(200, 30));
        JTextField campoCedula = new JTextField();
        campoCedula.setPreferredSize(new Dimension(200, 30));
        JButton botonSubmit = new JButton("Enviar");

        panelAgregarEmpleado.add(new JLabel("Nombre:"));
        panelAgregarEmpleado.add(campoNombre);
        panelAgregarEmpleado.add(new JLabel("Cedula:"));
        panelAgregarEmpleado.add(campoCedula);
        panelAgregarEmpleado.add(new JLabel());
        panelAgregarEmpleado.add(botonSubmit);

        botonSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText();
                String cedula = campoCedula.getText();

                empleados.add(new Empleado(nombre, cedula));
                JOptionPane.showMessageDialog(Empleados.this, "Empleado creado!");

                campoNombre.setText("");
                campoCedula.setText("");
            }
        });

        panelPrincipal.removeAll();
        panelPrincipal.add(panelAgregarEmpleado, BorderLayout.CENTER);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }

    private void mostrarPanelAgregarSalario() {
        panelActualizarSalario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelActualizarSalario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        if(empleados.isEmpty()){
            labelNoHayEmpleados = new JLabel("No hay empleados registrados por favor ingresa tus empleados");
            panelActualizarSalario.add(labelNoHayEmpleados);
        }
        else {
            dropdownEmpleado = new JComboBox<>();
            for (Empleado empleado : empleados) {
                dropdownEmpleado.addItem(empleado);
            }

            JTextField textSalario = new JTextField(10);
            JButton agregarSalario = new JButton("Agregar Salario");

            panelActualizarSalario.add(new JLabel("Seleccionar Empleado:"));
            panelActualizarSalario.add(dropdownEmpleado);
            panelActualizarSalario.add(new JLabel("Ingresar Salario:"));
            panelActualizarSalario.add(textSalario);
            panelActualizarSalario.add(agregarSalario);

            agregarSalario.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Empleado empleadoSeleccionado = (Empleado) dropdownEmpleado.getSelectedItem();
                    if (empleadoSeleccionado != null) {
                        String campoSalario = textSalario.getText();
                        if (!campoSalario.isEmpty()) {
                            double salario = Double.parseDouble(campoSalario);
                            empleadoSeleccionado.agregarSalario(salario);
                            JOptionPane.showMessageDialog(Empleados.this, "Salario agregado!");
                        } else {
                            JOptionPane.showMessageDialog(Empleados.this, "Error al ingresar un salario!");
                        }
                    }
                }
            });
        }

        panelPrincipal.removeAll();
        panelPrincipal.add(panelActualizarSalario, BorderLayout.CENTER);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }

    private void mostrarPanelVisualizarSalario(){
        panelVisualizarSalarios = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelVisualizarSalarios.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
               
        tablaSalarios = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaSalarios);
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Cantidad Salarios");
            
        for (Empleado empleado : empleados) {
            Object[] rowData = new Object[2];
            rowData[0] = empleado.getNombre();
            rowData[1] = empleado.getSalarios().size();
            model.addRow(rowData);
        }
        
        tablaSalarios.setModel(model);
        
        panelVisualizarSalarios.add(scrollPane, BorderLayout.CENTER);

        panelPrincipal.removeAll();
        panelPrincipal.add(panelVisualizarSalarios, BorderLayout.CENTER);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Empleados frame = new Empleados();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
    }
}
