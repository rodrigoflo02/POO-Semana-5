package cl.salmontt.model;

public class Empleado extends Persona {

    /**
     * SE CREA LA CLASE EMPLEADO HEREDANDO DE LA CLASE PERSONA, SIRVIENDO COMO SUBCLASE
     *
     * SE CREAN ATRIBUTOS CON MODIFICADOR DE ACCESO PRIVADO
     */
    private String area_trabajo;
    private String rol;

    /**
     * SE CREA CONSTRUCTOR DE LA CLASE EMPLEADO QUE RECIBIRA LOS ATRIBUTOS DE LA CLASE PERSONA Y
     * LLAMARA A LA CLASE PADRE (SUPERCLASE) PERSONA
     * @param nombre
     * @param apellido
     * @param direccion
     * @param area_trabajo
     * @param rol
     */
    public Empleado(String nombre, String apellido, String area_trabajo, String rol, Direccion direccion) {
        super(nombre, apellido, direccion);
        this.area_trabajo = area_trabajo;
        this.rol = rol;
    }

    /**
     * GETTERS Y SETTERS
     * @return
     */
    //AREA TRABAJO
    public String getArea_trabajo() {
        return area_trabajo;
    }
    public void setArea_trabajo(String area_trabajo) {
        this.area_trabajo = area_trabajo;
    }
    //ROL
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * LA PRIMERA SOBREESCRITURA LLAMA EL METODO DE LA SUPER CLASE O CLASE PADRE
     * @return STRING EL NOMBRE Y EL APELLIDO DE LA PERSONA
     *
     * LA SEGUNDA SOBREESCRITURA IMPLEMENTA EL METODO ToString CON LA INFORMACION CONTENIDA DE LOS ATRIBUTOS
     */
    @Override
    public String mostrar_persona() {
        return "Nombre: " + getNombre() + " " + getApellido();
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "Area de trabajo: " + area_trabajo + "\nRol: " + rol;
    }
}
