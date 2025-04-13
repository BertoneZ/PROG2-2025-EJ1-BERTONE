package DTO;
public class CajaDeAhorro extends Cuenta implements IGestionSaldo {
    @Override
    public synchronized Boolean agregarSaldo(double monto) {
        if (monto > 0) {
            saldo += monto;
            operaciones++;
            return true;
        }
        return false;
    }
    @Override
    public synchronized Boolean quitarSaldo(double monto) {
        if (monto > 0 && saldo >= monto) {
            saldo -= monto;
            operaciones++;
            return true;
        }
        return false;
    }
    @Override
    public synchronized double getSaldo() {
        return saldo;
    }
    @Override
    public synchronized int getOperaciones() {
        return operaciones;
    }
}




