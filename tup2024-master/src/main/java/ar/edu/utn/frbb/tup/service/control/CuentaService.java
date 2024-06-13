package ar.edu.utn.frbb.tup.service.control;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.persistence.SummitCuenta;
import ar.edu.utn.frbb.tup.service.exception.CuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.service.exception.TipoCuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.service.exception.TipoCuentaNotSupportedException;
import ar.edu.utn.frbb.tup.model.TipoCuenta;

public class CuentaService {
    public void darDeAltaCuenta(Cuenta cuenta, long dniTitular) throws CuentaAlreadyExistsException, TipoCuentaAlreadyExistsException, TipoCuentaNotSupportedException {
        if (SummitCuenta.buscarClientePorDni(cuenta.getTitular().getDni()) != null) {
            throw new CuentaAlreadyExistsException("La cuenta " + cuenta.getTitular().getDni() + " ya existe.");
        }

        if (!tipoCuentaEstaSoportada(cuenta)) {
            throw new TipoCuentaNotSupportedException("El tipo de cuenta " + cuenta.getTipoCuenta() + " no está soportado.");
        }

        SummitCuenta.escribirEnArchivo(cuenta);
    }
    private boolean tipoCuentaEstaSoportada(Cuenta cuenta) {
        for (TipoCuenta tipo : TipoCuenta.values()) {
            if (tipo == cuenta.getTipoCuenta()) {
                return true;
            }
        }
        return false;
        }
        public Cuenta findByDni(long dni) {
            return SummitCuenta.buscarCuentaPorDni(dni);
        }
  }


/*
 

public class CuentaService {

    private CuentaDao cuentaDao;


    private ClienteService clienteService;

    public void darDeAltaCuenta(Cuenta cuenta, long dniTitular) 
            throws CuentaAlreadyExistsException, TipoCuentaAlreadyExistsException, TipoCuentaNotSupportedException {
        if (cuentaDao.find(cuenta.getNumeroCuenta()) != null) {
            throw new CuentaAlreadyExistsException("La cuenta " + cuenta.getNumeroCuenta() + " ya existe.");
        }

        if (!tipoCuentaEstaSoportada(cuenta)) {
            throw new TipoCuentaNotSupportedException("El tipo de cuenta " + cuenta.getTipoCuenta() + " no está soportado.");
        }

        clienteService.agregarCuenta(cuenta, dniTitular);
        cuentaDao.save(cuenta);
    }

    private boolean tipoCuentaEstaSoportada(Cuenta cuenta) {
        for (TipoCuenta tipo : TipoCuenta.values()) {
            if (tipo == cuenta.getTipoCuenta()) {
                return true;
            }
        }
        return false;
    }

    public Cuenta find(long id) {
        return cuentaDao.find(id);
    }
 */