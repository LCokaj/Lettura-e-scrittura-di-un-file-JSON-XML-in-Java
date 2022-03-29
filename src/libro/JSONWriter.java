package libro;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

public class JSONWriter {
    
    public static final String JSON_FILE="libri.json";

    public static void main(String[] args) throws FileNotFoundException, IOException {//Il comando throws erve a gestire l'ecceccione in caso il file non viene trovato

        Libro libri[] = new Libro[2];//creo un array limitato ad un massimo di 3 elementi 
        
        libri[0] = new Libro();//creo il primo libro all'interno dell'array
        
        libri[0].setGenere("fantasy");
        libri[0].setTitolo("Lo Hobbit");
        libri[0].setAutore("J. R. R. Tolkien");
        libri[0].setPrezzo(9.9f);
        //setto tutti gli attributi al libro1
        
        libri[1] = new Libro();//creo il secondo libro all'interno dell'array
        
        libri[1].setGenere("fantasy");
        libri[1].setTitolo("Il signore degli anelli");
        libri[1].setAutore("J. R. R. Tolkien");
        libri[1].setPrezzo(30.00f);
        //setto tutti gli attributi al libro2

    
        JsonObjectBuilder rootObject = Json.createObjectBuilder();
        JsonObjectBuilder booksObject = Json.createObjectBuilder();
        JsonArrayBuilder bookArray = Json.createArrayBuilder();
        
        
        for (Libro libro : libri){
            JsonObjectBuilder bookObject =Json.createObjectBuilder();
            bookObject.add("genere", libro.getGenere());
            bookObject.add("titolo", libro.getTitolo());
            bookObject.add("autore", libro.getAutore());
            bookObject.add("prezzo", libro.getPrezzo());
            bookArray.add(bookObject.build());           
        }
        //questo ciclo for serve a riepire il json con i valori assegnati precedentemente
        
        booksObject.add("libri", bookArray.build());
        rootObject.add("libreria", booksObject.build());
        
        OutputStream output = new FileOutputStream(JSON_FILE); 
        
        JsonWriter jsonWriter = Json.createWriter(output);
        
        jsonWriter.writeObject(rootObject.build());
        
        jsonWriter.close();
        
        output.close();
             
    }
    
}