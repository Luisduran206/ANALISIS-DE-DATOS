import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainData {

	public static void main(String[] args) throws IOException {
		
		/*HashMap<String, Integer> miMapa = new HashMap<>();
		File docs = new File("Datos.csv");
		String path = docs.getAbsolutePath();
		System.out.println(path);*/
		
		String ID = "177406";
		Lector miLectorExamen = new Lector();
		miLectorExamen.ejecutar(ID);
		
		
	}
}
