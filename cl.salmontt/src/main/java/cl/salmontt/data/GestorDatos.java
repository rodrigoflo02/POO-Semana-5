package cl.salmontt.data;

import cl.salmontt.model.Direccion;
import cl.salmontt.model.Empleado;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorDatos {

    public List<Empleado> ListaEmpleadosExcel = new ArrayList<>();

    //***********************************************************************
    public void CargarExcelEmpleados ( String ruta){
        try (FileInputStream fileinputstream = new FileInputStream(ruta)){
            XSSFWorkbook libro = new XSSFWorkbook(fileinputstream);
            XSSFSheet hoja = libro.getSheetAt(0);
            FormulaEvaluator evaluarformula = libro.getCreationHelper().createFormulaEvaluator();

            //Recorrer filas de excel
            for (int i = 1; i<=hoja.getLastRowNum(); i++ ){
                var fila = hoja.getRow(i);
                if (fila == null) continue;

                String nombre = fila.getCell(0).getStringCellValue();
                String apellido = fila.getCell(1).getStringCellValue();
                String area = fila.getCell(2).getStringCellValue();
                String rol = fila.getCell(3).getStringCellValue();
                String direccionCompleta = EvaluarFormulaCelda(fila.getCell(4), evaluarformula);

                // Parsear la dirección completa para crear objeto Direccion
                Direccion direccion = ParsearDireccion(direccionCompleta);

                Empleado empleado = new Empleado(nombre, apellido, area, rol, direccion);
                AñadirEmpleado(empleado);
            }
            libro.close();
        } catch (FileNotFoundException a) {
            System.out.println("Archivo no disponible...");
        } catch (IOException b) {
            System.out.println("Hubo un error al cargar el archivo...");
        }
    }
    //***********************************************************************
    public List <Empleado> CargarCSVEmpleados ( String ruta){
    List <Empleado> ListaEmpleadosCSV = new ArrayList<>();

        ClassLoader cl = getClass().getClassLoader();

        try{
            InputStream archivo = cl.getResourceAsStream(ruta);
            if (archivo == null){
                System.out.println("No se ha encontrado el archivo...");
                return ListaEmpleadosCSV;
            }

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(archivo));
                String linea;
                int nrolinea = 0;

                while((linea = br.readLine()) != null){
                    nrolinea++;
                    if(linea.trim().isEmpty()) continue;

                    String [] seccion = linea.split(",");
                    if (seccion.length != 8){
                        System.out.println("Hubo un error en la linea: " + nrolinea);
                        continue;

                    }

                    String nombre = seccion[0].trim();
                    String apellido = seccion[1].trim();
                    String area = seccion[2].trim();
                    String rol = seccion[3].trim();
                    String callestr = seccion[4].trim();
                    String calle = "";
                    String numerostr;
                    int numero = 0;

                    //separamos por espacios en blanco y buscar digitos hasta el final del texto
                    String[] partescalle =  callestr.split("\\s+(?=[0-9]+$)");
                    if (partescalle.length >= 2){
                        calle = partescalle[0].trim();
                        numerostr = partescalle[1].trim();
                        try {
                            numero = Integer.parseInt(numerostr);
                        } catch (NumberFormatException e) {
                            System.out.println("Hubo un error al transformar el numero de casa: " + numerostr);
                        }
                    }
                    String comuna = seccion[5].trim();
                    String ciudad = seccion[6].trim();
                    String region = seccion[7].trim();

                    Direccion direccion = new Direccion( calle, numero , comuna, ciudad, region);
                    Empleado empleado = new Empleado(nombre, apellido, area, rol, direccion);

                    ListaEmpleadosCSV.add(empleado);
                }
            } catch (IOException b){
                System.out.println("Hubo un error al cargar el archivo...");
            }
        } catch (NullPointerException a){
            System.out.println("No se ha encontrado el archivo...");
        }
        return ListaEmpleadosCSV;
    }

    //metodos aplicados para archivo excel
    private String EvaluarFormulaCelda (Cell celda, FormulaEvaluator evaluarformula){
        if (celda == null) return "";

        if (celda.getCellType() == CellType.FORMULA){
            return evaluarformula.evaluate(celda).getStringValue();
        } else {
            return celda.getStringCellValue();
        }
    }
    //***********************************************************************
    private Direccion ParsearDireccion (String direccionCompleta){
        //validar si la direccion no contiene nada o esta vacia
        if (direccionCompleta == null || direccionCompleta.isEmpty()) {
            return new Direccion("",0 , "", "", "");
        }

        try {
            String[] partes = direccionCompleta.split(",");

            if (partes.length >= 4){
                String callecompleta = partes[0];
                String comuna = partes[1];
                String ciudad =partes[2];
                String region = partes[3];

                //separacion de las calles con numero
                String[] calleseccion = callecompleta.split(" ");
                String numeroStr = calleseccion[calleseccion.length - 1];
                String calle = callecompleta.substring(0,callecompleta.lastIndexOf(' ')).trim();

                int numero = 0;
                try {
                    numero= Integer.parseInt(numeroStr);
                } catch (NumberFormatException e) {
                    System.out.println("Formato de la casa incorrecto");
                }
                return new Direccion(calle, numero, comuna, ciudad, region);
            }
        } catch (Exception e) {
            System.out.println("No se pudo transformar la direccion");
        }
        //Si el parseo no funciona retornara en una nueva direccion como medida de seguridad
        return new Direccion("",0 , "", "", "");
    }
    //***********************************************************************
    public void AñadirEmpleado(Empleado empleado){
        ListaEmpleadosExcel.add(empleado);
    }
    //***********************************************************************
    public void ListarEmpleadosExcel (){
        System.out.println("*********** LISTA DE EMPLEADOS ***********");
        for (Empleado empleado : ListaEmpleadosExcel) {
            System.out.println(empleado + "\n**********************");
        }
    }




}
