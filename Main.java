import java.io.IOException;
public class Main {

	public static void main(String[] args) throws IOException {
		String id = "177406"; //Ingresar el ID en la variable ID
		Lector miLectorExamen = new Lector();
		miLectorExamen.ejecutar(id);
	}
}
