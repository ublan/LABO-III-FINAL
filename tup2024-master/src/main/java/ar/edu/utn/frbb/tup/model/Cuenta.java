package ar.edu.utn.frbb.tup.model;

import ar.edu.utn.frbb.tup.service.exception.CantidadNegativaException;
import ar.edu.utn.frbb.tup.service.exception.NoAlcanzaException;

import java.time.LocalDateTime;
import java.util.Random;

public class Cuenta {
    private long numeroCuenta;
    String nombre;
    LocalDateTime fechaCreacion;
    int balance;
    TipoCuenta tipoCuenta;
    Cliente titular;
    private String CBU; // Agregar CBU a la cuenta
    String moneda;

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }


    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public Cuenta setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Cuenta setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public Cuenta setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public int getBalance() {
        return balance;
    }

    public Cuenta setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public String getCBU() {
        return CBU;
    }

    public void setCBU() {
        this.CBU = generarCBU();
    }

    private String generarCBU() {
        Random random = new Random();
        return String.format("%08d", random.nextInt(100000000));
    }

    public void debitarDeCuenta(int cantidadADebitar) throws NoAlcanzaException, CantidadNegativaException {
        if (cantidadADebitar < 0) {
            throw new CantidadNegativaException();
        }

        if (balance < cantidadADebitar) {
            throw new NoAlcanzaException();
        }
        this.balance = this.balance - cantidadADebitar;
    }

    public void forzaDebitoDeCuenta(int i) {
        this.balance = this.balance - i;
    }

    public long getNumeroCuenta() {
        return numeroCuenta;
    }
    public void setNumeroCuenta(Long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getMoneda() {
        return moneda;
    }

    public Cuenta setMoneda(String moneda) {
        this.moneda = moneda;
        return this;
    }
}
