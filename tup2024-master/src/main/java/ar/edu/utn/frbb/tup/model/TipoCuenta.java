package ar.edu.utn.frbb.tup.model;

public enum TipoCuenta {

    CORRIENTE("C"),
    AHORRO("A");

    private final String descripcion;

    TipoCuenta(String descripcion) {
        this.descripcion = descripcion;
    }
}
