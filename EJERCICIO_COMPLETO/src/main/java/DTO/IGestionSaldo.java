package DTO;
public interface IGestionSaldo {
    Boolean agregarSaldo(double monto);
    Boolean quitarSaldo(double monto);
    double getSaldo();
    int getOperaciones();
}
