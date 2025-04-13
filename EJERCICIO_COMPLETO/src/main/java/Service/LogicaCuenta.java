package Service;
import java.util.ArrayList;
import java.util.List;
import DTO.*;
public class LogicaCuenta {
    private static volatile LogicaCuenta instancia;
    private List<Cuenta> cuentas;
    private LogicaCuenta() {
        cuentas = new ArrayList<>();
    }
    public static LogicaCuenta getInstance() {
        if(instancia == null) {
            synchronized (LogicaCuenta.class) {
                if(instancia == null) {
                    instancia = new LogicaCuenta();
                }
            }
        }
        return instancia;
    }
    public synchronized void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }
    public synchronized List<Cuenta> obtenerCuentas() {
        return new ArrayList<>(cuentas);
    }
    public synchronized boolean agregarSaldo(int cuentaIndex, double monto) {
        if(cuentaIndex >= 0 && cuentaIndex < cuentas.size()&& monto > 0) {
            Cuenta cuenta = cuentas.get(cuentaIndex);
            if (cuenta instanceof IGestionSaldo) {
                return ((IGestionSaldo) cuenta).agregarSaldo(monto);
            }
        }
        return false;
    }
    public synchronized boolean quitarSaldo(int cuentaIndex, double monto) {
        if(cuentaIndex >= 0 && cuentaIndex < cuentas.size()&& monto > 0) {
            Cuenta cuenta = cuentas.get(cuentaIndex);
            if (cuenta instanceof IGestionSaldo) {
                return ((IGestionSaldo) cuenta).quitarSaldo(monto);
            }
        }
        return false;
    }
    public synchronized double consultarSaldo(int cuentaIndex) {
        if (cuentaIndex >= 0 && cuentaIndex < cuentas.size()) {
            Cuenta cuenta = cuentas.get(cuentaIndex);
            if (cuenta instanceof IGestionSaldo) {
                return ((IGestionSaldo) cuenta).getSaldo();
            }
        }
        return -1;
    }
}
