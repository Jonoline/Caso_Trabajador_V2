package gui;

import data.Trabajador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class InterfazCLITest {
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private ByteArrayInputStream inputStream;
    private final static String SALIR = "5\n";

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    private void setSimulatedInput(String input) {
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }

    @Test
    void testCrearYMostrarTrabajador() {
        // Datos iniciales + ver datos (0) + salir (5)
        String input = "Juan\nPérez\n12345678-9\nColmena\nHabitat\n0\n5\n";
        setSimulatedInput(input);

        new InterfazCLI();

        String output = outputStream.toString();
        assertTrue(output.contains("Juan"));
        assertTrue(output.contains("Pérez"));
        assertTrue(output.contains("12345678-9"));
        assertTrue(output.contains("Colmena"));
        assertTrue(output.contains("Habitat"));
    }

    @Test
    void testOpcionInvalida() {
        // Datos iniciales + opción inválida (9) + salir (5)
        String input = "Ana\nGarcía\n98765432-1\nBanmédica\nProvida\n9\n5\n";
        setSimulatedInput(input);

        new InterfazCLI();

        String output = outputStream.toString();
        assertTrue(output.contains("Opción inválida"));
    }

    @Test
    void testEditarNombre() {
        // Datos iniciales + editar nombre (1) + nuevo nombre + ver datos (0) + salir (5)
        String input = "María\nLópez\n11111111-1\nCruz Blanca\nCapital\n1\nNuevoNombre\n0\n5\n";
        setSimulatedInput(input);

        new InterfazCLI();

        String output = outputStream.toString();
        assertTrue(output.contains("NuevoNombre"));
    }

    @Test
    void testEditarApellido() {
        // Datos iniciales + editar apellido (2) + nuevo apellido + ver datos (0) + salir (5)
        String input = "María\nLópez\n11111111-1\nCruz Blanca\nCapital\n2\nNuevoApellido\n0\n5\n";
        setSimulatedInput(input);

        new InterfazCLI();

        String output = outputStream.toString();
        assertTrue(output.contains("NuevoApellido"));
    }

    @Test
    void testEditarIsapre() {
        // Datos iniciales + editar isapre (3) + nueva isapre + ver datos (0) + salir (5)
        String input = "María\nLópez\n11111111-1\nCruz Blanca\nCapital\n3\nNuevaIsapre\n0\n5\n";
        setSimulatedInput(input);

        new InterfazCLI();

        String output = outputStream.toString();
        assertTrue(output.contains("NuevaIsapre"));
    }

    @Test
    void testEditarAfp() {
        // Datos iniciales + editar afp (4) + nueva afp + ver datos (0) + salir (5)
        String input = "María\nLópez\n11111111-1\nCruz Blanca\nCapital\n4\nNuevaAfp\n0\n5\n";
        setSimulatedInput(input);

        new InterfazCLI();

        String output = outputStream.toString();
        assertTrue(output.contains("NuevaAfp"));
    }

    @Test
    void testFlujoCompleto() {
        // Datos iniciales + ver datos (0) + editar nombre (1) +
        // editar apellido (2) + ver datos (0) + salir (5)
        String input = "Pedro\nSánchez\n22222222-2\nConsalud\nModelo\n" +
                "0\n" +  // ver datos originales
                "1\nPablo\n" +  // cambiar nombre
                "2\nRodríguez\n" +  // cambiar apellido
                "0\n" +  // ver datos modificados
                "5\n";   // salir
        setSimulatedInput(input);

        new InterfazCLI();

        String output = outputStream.toString();
        assertTrue(output.contains("Pablo"));
        assertTrue(output.contains("Rodríguez"));
        assertTrue(output.contains("22222222-2"));
    }
}