import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lector {
	List<String[]> renglones;
	List<String> palabras;
	String[][] matrizEmails; 
	HashMap<String, Integer> emails =  new HashMap<>();
	int inicio; 
	int fin;
	String ID;
	
	public Lector() { }
	
	public void ejecutar(String ID) throws IOException {
		this.ID = ID;
		limite(ID);
		CrearMatriz();
		CrearHashMap();
		CrearFile();
	}
	
	public void limite(String ID) {
		int ultimosTresDigitos = Integer.parseInt(ID.substring(ID.length() - 3));
		int inicio;
		inicio = ultimosTresDigitos;
		int fin = inicio + 50;
		this.inicio = inicio; this.fin = fin;
	}
	
	public void CrearMatriz() {
		renglones = new ArrayList<String[]>();
		//EN ESTA LÍNEA POR FAVOR SUSTITUIR PATH ENTRE PARÉNTESIS POR LA DIRECCIÓN DEL DOCUMENTO...
		try (BufferedReader br = new BufferedReader(new FileReader("/Users/luisduranflores/Downloads/UDLAP/SEGUNDO SEMESTRE/P. ORIENTADA A OBJETOS/PROGRAMAS/DataAnalisis/emails.csv"))) {
			String line;
		    while ((line = br.readLine()) != null) {
		        String[] emails = line.split(",");
		        renglones.add(emails);
		    }
		    br.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		matrizEmails = new String[renglones.size()][];
		for (int i = 0; i < renglones.size(); i++) {
		    String[] column = renglones.get(i);
		    matrizEmails[i] = column;
		}
	}
	
	public void CrearHashMap() {
		List<String> palabras = new ArrayList<>();
		this.palabras = palabras;
		for(int j = 1; j < 3001; j++) {
			String temporal = matrizEmails[0][j];
			palabras.add(temporal);
			emails.put(matrizEmails[0][j], 0);	
		}
		for(int x = 1; x < 3001; x++) {
			int valor = 0;
			for(int y = inicio; y < fin; y++) {
				valor = valor + Integer.parseInt(matrizEmails[y][x]);
			}	
			emails.replace(matrizEmails[0][x], valor);
		}
	}
	
	public void CrearFile() throws IOException {
		 try {
	            FileWriter writer = new FileWriter("177406.txt");
	            BufferedWriter bufWriter = new BufferedWriter(writer);
	            for(int i = 1; i < 3001; i++) {
	            	for(int j = 0; j < 3000; j++) {
	            	bufWriter.write(palabras.get(j)+", " + emails.get(palabras.get(j)) + "\n");
	            	}
	            }
	            bufWriter.close();
	            writer.close();
	            System.out.println("El documento " +ID+ ".txt"+ " fue escrito correctamente");
	            System.out.println("Se imprimió la suma de columnas de la celda "+inicio+" a la "+fin);
	        } catch (IOException e) {
	            System.out.println("ERROR!ERROR! EN: " + e.getMessage());
	        }
	}
}