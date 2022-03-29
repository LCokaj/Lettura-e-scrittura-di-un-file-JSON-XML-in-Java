
package libro;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import java.util.ArrayList; //importo la classe dell'arraylist


public class JSONReader {
    
    public static final String JSON_FILE="libri.json";
    
    
    public static void main(String[] args) throws FileNotFoundException {//Il comando throws erve a gestire l'ecceccione in caso il file non viene trovato

       ArrayList<String> libri = new ArrayList<String>(); //creo un'array listi(o almeno ci provo)
        
        InputStream input = new FileInputStream(JSON_FILE);
        
        JsonReader jsonReader = Json.createReader(input);
        
        JsonObject jsonObject = jsonReader.readObject();
        
        jsonReader.close();
        
        JsonObject innerJsonObject = jsonObject.getJsonObject("libreria");
        
        JsonArray jsonArray = innerJsonObject.getJsonArray("libri");
        
        libri = new Libro[jsonArray.size()]; //istanzio un nuovo array della classe Libro
        
        int index = 0;//creo l'indice
        
        for (JsonValue element : jsonArray) {
            Libro libro = new Libro();//creo un nuovo oggetto libro da aggiungere all'array
            
            libro.setGenere(element.asJsonObject().getString("genere"));
            libro.setTitolo(element.asJsonObject().getString("titolo"));
            libro.setAutore(element.asJsonObject().getString("autore"));
            libro.setPrezzo((float) element.asJsonObject().getJsonNumber("prezzo").doubleValue());
            //setto gli attributi prendedo i valori contenuti nel file json
            
            libri.set(index++, libro);  //riempo l'array con tutti i libri generati con i comandi precedeti      
        }
        
        for (index=0; index<libri.length; index++)
            System.out.println(libri.get(index));//visulizzo a schermo tutti i libri generati
       
    }
    
}