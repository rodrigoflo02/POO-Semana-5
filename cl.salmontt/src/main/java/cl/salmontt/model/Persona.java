package cl.salmontt.model;

public abstract class Persona {
    /**
     * SE CREAN ATRIBUTOS CON MODIFICADOR DE ACCESO PARA LA CLASE PERSONA EN LA CUAL "DIRECCION" ESTARA
     * *  FUERTEMENTE LIGADA A ESTA CLASE FORMANDO UNA RELACION DE COMPOSICION
     * SE CREA LA CLASE PERSONA COMO UNA CLASE ABSTRACTA, YA QUE SERVIRA COMO CLASE PADRE PARA LA CLASE EMPLEADO
     * */
    private String nombre;
    private String apellido;
    private Direccion direccion; //COMPOSICION


    /**
     * SE IMPLEMENTA METODO CONSTRUCTOR CON PARAMETROS QUE RECIBIRA LOS ATRIBUTOS DE LA CLASE PERSONA
     * */
    protected Persona(String nombre, String apellido, Direccion direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }

    /**
     * IMPLEMENTACION DE GETTERS Y SETTER
     * */

    //NOMBRE
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //APELLIDO
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    //DIRECCION
    public Direccion getDireccion() {
        return direccion;
    }
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    /**
     * SE DEFINE EL METODO "MOSTRAR_PERSONA" PARA QUE LA CLASE EMPLEADO HEREDE EL METODO
     * */
    public abstract String mostrar_persona();


    /**
     *A traves de metodo ToString DE LA CLASE PERSONA Y ToString DE LA CLASE DIRECCION
     * se retornara en una cadena de texto
     * @return STRING CON LA INFORMACION CONTENIDA DE LOS ATRIBUTOS
     */
    @Override
    public String toString() {
        return "Nombre: " + nombre + " " + apellido +
                "\n" + direccion.toString();
    }

}
