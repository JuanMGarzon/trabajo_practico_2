package ar.edu.unju.fi.ejercicio5.model;

import ar.edu.unju.fi.ejercicio5.interfaces.Pago;
import java.time.LocalDate;

public class PagoEfectivo implements Pago {
    private double montoPagado;
    private LocalDate fechaPago;

    public PagoEfectivo(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
        this.montoPagado = 0.0;
    }

    @Override
    public void realizarPago(double monto) {
        this.montoPagado = monto - (monto * 0.10);
    }

    @Override
    public void imprimirRecibo() {
        System.out.println("Fecha de pago: " + fechaPago);
        System.out.println("Monto pagado: " + montoPagado);
    }
}
