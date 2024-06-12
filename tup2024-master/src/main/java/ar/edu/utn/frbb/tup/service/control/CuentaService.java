package ar.edu.utn.frbb.tup.service.control;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.persistence.SummitCuenta;
import ar.edu.utn.frbb.tup.service.exception.CuentaAlreadyExistsException;

public class CuentaService {
    public void darDeAltaCuenta(Cuenta cuenta, long dniTitular) throws CuentaAlreadyExistsException {
        throws CuentaAlreadyExistsException, TipoCuentaAlreadyExistsException, TipoCuentaNotSupportedException {
            if (buscarClientePorDni(cuenta.getNumeroCuenta()) != null) {
                throw new CuentaAlreadyExistsException("La cuenta " + cuenta.getNumeroCuenta() + " ya existe.");
            }

        SummitCuenta.escribirEnArchivo(cuenta);
    }

  }
/*
 
@Component
public class CuentaService {
    @Autowired
    private CuentaDao cuentaDao;

    @Autowired
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