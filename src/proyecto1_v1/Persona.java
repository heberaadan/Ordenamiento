package proyecto1_v1;

public class Persona {
    private int NumCuenta;
    private String Nombre;
    private String Apellido;
    
    public Persona(){
        this.Nombre = "";
        this.Apellido = "";
        this.NumCuenta = 0;
    }
    public Persona(String nombre,String apellido,int numcuenta){
        //Este constructor asigna los valores pasados por parametros a valores de la clase.
        this.Nombre=nombre;
        this.Apellido=apellido;
        this.NumCuenta=numcuenta;
    }

    public int getNumCuenta() {
        return this.NumCuenta;
    }

    public void setNumCuenta(int NumCuenta) {
        this.NumCuenta = NumCuenta;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return this.Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }
    public String getDatos(){
        return Nombre+","+Apellido+","+NumCuenta;
    }

}
