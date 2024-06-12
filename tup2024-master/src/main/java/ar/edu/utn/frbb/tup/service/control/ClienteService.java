package ar.edu.utn.frbb.tup.service.control;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.persistence.SummitCliente;
import ar.edu.utn.frbb.tup.service.exception.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.service.exception.TipoCuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.model.Cuenta;
public class ClienteService {



    public void darDeAltaCliente(Cliente cliente) throws ClienteAlreadyExistsException {
        if (SummitCliente.findByDni(String.valueOf(cliente.getDni())) != null) {
            throw new ClienteAlreadyExistsException("Ya existe un cliente con DNI " + cliente.getDni());
        }

        if(cliente.getFechaNacimiento() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
        }

        if (cliente.getEdad() < 18) {
            throw new IllegalArgumentException("El cliente debe ser mayor a 18 años");
        }

        SummitCliente.escribirEnArchivo(cliente);
    }
    
    public void agregarCuenta(Cuenta cuenta, long dniTitular) throws TipoCuentaAlreadyExistsException {
        Cliente titular = SummitCliente.findByDni(String.valueOf(dniTitular));
        cuenta.setTitular(titular);
        if (titular.tieneCuenta(cuenta.getTipoCuenta(), cuenta.getMoneda())) {
            throw new TipoCuentaAlreadyExistsException("El cliente ya posee una cuenta de ese tipo y moneda");
        }
        titular.addCuenta(cuenta);
        SummitCliente.escribirEnArchivo(titular);
    }

    public static Cliente findByDni(String dni, boolean readFromFile) {
        Cliente cliente = SummitCliente.findByDni(dni);
        if(cliente == null) {
            throw new IllegalArgumentException("El cliente no existe");
        }
        return cliente;
    }

}
/* 
ClienteDao clienteDao;
    

public ClienteService(ClienteDao clienteDao) {
    this.clienteDao = clienteDao;
}

public void darDeAltaCliente(Cliente cliente) throws ClienteAlreadyExistsException {

    if (clienteDao.find(cliente.getDni(), false) != null) {
        throw new ClienteAlreadyExistsException("Ya existe un cliente con DNI " + cliente.getDni());
    }


    if(cliente.getFechaNacimiento() == null) {
        throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
    }

    if (cliente.getEdad() < 18) {
        throw new IllegalArgumentException("El cliente debe ser mayor a 18 años");
    }

    clienteDao.save(cliente);
}


public Cliente buscarClientePorDni(long dni) {
    Cliente cliente = clienteDao.find(dni, true);
    if(cliente == null) {
        throw new IllegalArgumentException("El cliente no existe");
    }
    return cliente;
}
*/