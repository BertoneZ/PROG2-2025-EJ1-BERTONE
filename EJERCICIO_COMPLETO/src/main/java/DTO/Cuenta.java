package DTO;
public abstract class Cuenta {
    protected double saldo;
    protected int operaciones;
    public Cuenta() {
        this.saldo = 0;
        this.operaciones = 0;
    }
    public double getSaldo() {
        return saldo;
    }
    public int getOperaciones() {
        return operaciones;
    }
}
