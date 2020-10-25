package com.jasper.main;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jasper.model.Tarjetas;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class SimpleReportMain {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleReportMain.class);

	public static void main(String[] args) {
		try {
			LOGGER.info("Generando POJOS...");
			List<Tarjetas> tarjetas = getListTarjetas();
			List<Tarjetas> cuentas = getListCuentas();

			LOGGER.info("Analizando archivo Jasper jrxml...");
			/* Obtiene archivo jrxml */
			InputStream is = SimpleReportMain.class.getResourceAsStream("/reports/TarjetasReport.jrxml");
			/* Compila archivo jrxml y lo pasa a variable de tipo JasperReport */
			JasperReport report = JasperCompileManager.compileReport(is);

			/* Pasa Pojos a JRBeanCollectionDataSource */
			JRBeanCollectionDataSource dsTarjeta = new JRBeanCollectionDataSource(tarjetas);
			JRBeanCollectionDataSource dsCuenta = new JRBeanCollectionDataSource(cuentas);

			/* Agrega JRBeanCollectionDataSource al mapa parametro */
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("tarjeta", dsTarjeta);
			parameters.put("cuenta", dsCuenta);

			/* Agrega reporte jrxml compilado y parametros a JasperPrint */
			JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

			/* Genera archivo final pdf */
			JasperExportManager.exportReportToPdfFile(print, "src/main/resources/reports/Tarjetas.pdf");
			LOGGER.info("==Reporte generado correctamente==");
		} catch (JRException jre) {
			LOGGER.error("Error al generar reporte. ", jre);
		}
	}

	private static List<Tarjetas> getListTarjetas() {
		List<Tarjetas> tarjetas = new ArrayList<>();
		tarjetas.add(new Tarjetas(1, "DDA", "00154", 500.50, "comprobante1"));
		tarjetas.add(new Tarjetas(2, "TDC", "00155", 800.66, "comprobante2"));
		tarjetas.add(new Tarjetas(3, "TDC", "00156", 3850.5, "comprobante3"));
		tarjetas.add(new Tarjetas(4, "DDA", "00157", 5624.5, "comprobante4"));
		tarjetas.add(new Tarjetas(5, "DDA", "00158", 666.66, "comprobante5"));
		return tarjetas;
	}

	private static List<Tarjetas> getListCuentas() {
		List<Tarjetas> cuentas = new ArrayList<>();
		cuentas.add(new Tarjetas(11, "cuenta1", "011", 666.50, "comprobante1Cu"));
		cuentas.add(new Tarjetas(12, "cuenta2", "012", 780.66, "comprobante2Cu"));
		return cuentas;
	}

}
