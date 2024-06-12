package ar.edu.utn.frbb.tup.service;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.TipoCuenta;
import ar.edu.utn.frbb.tup.service.exception.CuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.service.exception.TipoCuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.service.control.ClienteService;
import ar.edu.utn.frbb.tup.service.control.CuentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CuentaServiceTest {

    @InjectMocks
    private CuentaService cuentaService;

    @Mock
    private CuentaDao cuentaDao;

    @Mock
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // 1 - CUENTA EXISTENTE
    @Test
    void cuentaYaExisteTest() throws TipoCuentaAlreadyExistsException {

        Cuenta cuenta = new Cuenta();
        long dniTitular = 12345678L;

        when(cuentaDao.find(anyLong())).thenReturn(new Cuenta());

        assertThrows(CuentaAlreadyExistsException.class, () -> cuentaService.darDeAltaCuenta(cuenta, dniTitular));

        verify(clienteService, never()).agregarCuenta(any(Cuenta.class), anyLong());
        verify(cuentaDao, never()).save(any(Cuenta.class));
    }

    //2 - CUENTA NO SOPORTADA
    @Test
    void tipoCuentaNoSoportadaTest() throws CuentaAlreadyExistsException, TipoCuentaAlreadyExistsException {

        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta(null); // Tipo de cuenta no soportado 
        long dniTitular = 12345678L;
        when(cuentaDao.find(anyLong())).thenReturn(null);


        assertThrows(TipoCuentaNotSupportedException.class, () -> cuentaService.darDeAltaCuenta(cuenta, dniTitular));
    }

    // 3 - CLIENTE YA TIENE CUENTA DE ESE TIPO
    @Test
    void tipoCuentaYaExisteTest()
            throws CuentaAlreadyExistsException, TipoCuentaAlreadyExistsException {

        long dniTitular = 12345678L;
        Cuenta cuentaExistente = new Cuenta();
        cuentaExistente.setTipoCuenta(TipoCuenta.CAJA_AHORRO);
        Cliente clienteExistente = new Cliente();
        when(clienteService.buscarClientePorDni(dniTitular)).thenReturn(clienteExistente);
        doThrow(new TipoCuentaAlreadyExistsException("El cliente ya posee una cuenta de ese tipo y moneda"))
                .when(clienteService).agregarCuenta(cuentaExistente, dniTitular);


        assertThrows(TipoCuentaAlreadyExistsException.class,
                () -> cuentaService.darDeAltaCuenta(cuentaExistente, dniTitular));
    }

    // 4 - CUENTA CREADA EXITOSAMENTE
    @Test
    void darDeAltaCuentaTest() throws CuentaAlreadyExistsException, TipoCuentaAlreadyExistsException, TipoCuentaNotSupportedException {

        Cuenta cuenta = new Cuenta();
        long dniTitular = 12345678L;
        when(cuentaDao.find(anyLong())).thenReturn(null);
        doNothing().when(clienteService).agregarCuenta(cuenta, dniTitular);


        cuentaService.darDeAltaCuenta(cuenta, dniTitular);


        verify(cuentaDao, times(1)).find(anyLong());
        verify(clienteService, times(1)).agregarCuenta(cuenta, dniTitular);
        verify(cuentaDao, times(1)).save(cuenta);
    }
}