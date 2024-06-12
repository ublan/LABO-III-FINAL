package ar.edu.utn.frbb.tup.service.control;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.persistence.SummitCuenta;
import ar.edu.utn.frbb.tup.service.exception.CuentaAlreadyExistsException;

public class CuentaService {
    public void darDeAltaCuenta(Cuenta cuenta) throws CuentaAlreadyExistsException {

        SummitCuenta.escribirEnArchivo(cuenta);
    }

  }
