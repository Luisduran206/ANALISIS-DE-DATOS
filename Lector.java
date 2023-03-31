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

public class Lector {
	List<String[]> renglones;
	String[][] matrizEmails; 
	HashMap<String, Integer> emails =  new HashMap<>();
	int inicio;
	int fin = inicio + 50;
	
	public Lector() { 
	}
	
	public void ejecutar(String ID) throws IOException {
		limite(ID);
		CrearMatriz();
		CrearHashMap();
		CrearFile();
	}
	
	public void limite(String ID) {
		int ultimosTresDigitos = Integer.parseInt(ID.substring(ID.length() - 3));
		int inicio;
		//System.out.println(ultimosTresDigitos++);
		inicio = ultimosTresDigitos;
		int fin = inicio + 50;
		this.inicio = inicio; this.fin = fin;
	}
	
	public void CrearMatriz() {
		renglones = new ArrayList<String[]>();
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
		for(int j = 1; j < 3001; j++) {
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
	            for (Map.Entry<String, Integer> entry : emails.entrySet()) {
	                bufWriter.write(entry.getKey() + "," + entry.getValue());
	                bufWriter.newLine();
	            }
	            bufWriter.close();
	            writer.close();
	            System.out.println("El documento " +writer+ ".txt"+ " fue escrito correctamente");
	            System.out.println("Se imprimió la suma de columnas de la celda "+inicio+" a la "+fin);
	            System.out.println(inicio + " " + fin);
	        } catch (IOException e) {
	            System.out.println("ERROR!ERROR! EN: " + e.getMessage());
	        }
	}
}