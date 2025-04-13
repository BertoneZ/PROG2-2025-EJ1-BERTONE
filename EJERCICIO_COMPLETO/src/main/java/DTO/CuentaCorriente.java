package DTO;
public class CuentaCorriente extends Cuenta implements IGestionSaldo {
    double giroDescubierto;
   public CuentaCorriente(double giroDescubierto) {
        super();
        this.giroDescubierto = 0;
   }
    public double getGiroDescubierto() {
        return giroDescubierto;
    }
    public void setGiroDescubierto(double giroDescubierto) {
        this.giroDescubierto = giroDescubierto;
    }
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
       if (monto > 0 && (saldo - monto) >= -giroDescubierto) {
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
