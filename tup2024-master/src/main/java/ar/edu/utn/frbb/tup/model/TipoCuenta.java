package ar.edu.utn.frbb.tup.model;

public enum TipoCuenta {

    CORRIENTE("C"),
    AHORRO("A");

    private final String descripcion;

    TipoCuenta(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public static TipoCuenta fromString(String text) {
        for (TipoCuenta tipo : TipoCuenta.values()) {
            if (tipo.getDescripcion().equalsIgnoreCase(text)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("No se pudo encontrar un TipoCuenta con la descripción: " + text);
    }
}
