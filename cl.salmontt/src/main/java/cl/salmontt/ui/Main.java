package cl.salmontt.ui;

import cl.salmontt.data.GestorDatos;
import cl.salmontt.model.Empleado;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List <String> Areas = new ArrayList<>();
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        GestorDatos EmpleadosDatos = new GestorDatos();
        AreasEmpresa();

        boolean ciclo = true;
        while(ciclo) {
            System.out.println("Seleccione el tipo de archivo con el que se trabajara: \n1) xlsx (Excel) \n2) csv \n3) cerrar el programa");
            int sel = Integer.parseInt(sc.nextLine());
            switch (sel){
                case 1:
                    EmpleadosDatos.CargarExcelEmpleados("Empleados.xlsx");
                    MostrarAreasConEmpleados(EmpleadosDatos.ListaEmpleadosExcel);
                    break;
                case 2:
                    List<Empleado> EmpleadosCSV = EmpleadosDatos.CargarCSVEmpleados("EmpleadosCSV.csv");
                    MostrarAreasConEmpleados(EmpleadosCSV);
                    break;
                case 3:
                    System.out.println("El programa finalizara en breve...");
                    ciclo = false;
                    break;
                default:
                    System.out.println("Seleccione una opcion valida");
            }
        }
        sc.close();
    }

    //METODOS
    public static void AreasEmpresa(){
        Areas.add("TI");
        Areas.add("Dev");
        Areas.add("Administracion");
        Areas.add("CiberSoc");
        Areas.add("Ventas");
        Areas.add("Contabilidad");
    }
    public static void MostrarAreasConEmpleados(List<Empleado> empleados) {
        System.out.println("********************** Filtro por Areas **********************");
        for (String area : Areas) {
            for (Empleado empleado : empleados) {
                if (empleado.getArea_trabajo().equalsIgnoreCase(area)) {
                    System.out.println("AREA: " + area);
                    System.out.println("Nombre: "+empleado.getNombre());
                    System.out.println("Apellido: "+empleado.getApellido());
                    System.out.println("Rol: " +  empleado.getRol());
                    System.out.println("----------------------------------------");
                }
            }

        }
    }
}
