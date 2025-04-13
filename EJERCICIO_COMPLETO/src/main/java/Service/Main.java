package Service;
import java.util.Random;
import DTO.*;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando el sistema de gestión de cuentas...");
        crearCuentasAleatorias();
        ejecutarOperacionesConcurrentes();
        imprimirResultados();
    }

    private static void crearCuentasAleatorias() {
        LogicaCuenta logica = LogicaCuenta.getInstance(); // Obtener Singleton
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            logica.agregarCuenta(new CajaDeAhorro()); // Agregar CajaDeAhorro
            logica.agregarCuenta(new CuentaCorriente(500 + random.nextDouble() * 500)); // Agregar CuentaCorriente
        }
        System.out.println("Se han creado 10 cuentas aleatorias (5 CajaDeAhorro y 5 CuentaCorriente).");
    }


    private static void ejecutarOperacionesConcurrentes() {
        LogicaCuenta logica = LogicaCuenta.getInstance();
        Random random = new Random();
        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    int cuentaIndex = random.nextInt(10);
                    double monto = random.nextDouble() * 100;

                    if (random.nextBoolean()) {
                        logica.agregarSaldo(cuentaIndex, monto);
                    } else {
                        logica.quitarSaldo(cuentaIndex, monto);
                    }
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Error en la ejecución de los hilos: " + e.getMessage());
            }
        }
        System.out.println("Se han completado las operaciones concurrentes (10,000 iteraciones).");
    }
    private static void imprimirResultados() {
        LogicaCuenta logica = LogicaCuenta.getInstance();

        System.out.println("\nResultados finales:");
        for (int i = 0; i < 10; i++) {
            System.out.println("Cuenta " + i + ":");
            System.out.println("Saldo final: " + logica.consultarSaldo(i));
            System.out.println("Operaciones realizadas: " + logica.obtenerCuentas().get(i).getOperaciones());
            System.out.println("---");
        }
    }
}
