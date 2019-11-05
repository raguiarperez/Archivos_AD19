
package archivos_ad19;

import java.awt.Cursor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam2
 */
public class Archivos_AD19 {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws SQLException {
      Producto obj= new Producto();
      obj.conectar();
     // obj.insertarFila("p4", "cinta", 2);
     // obj.borrarFila("p4");
      obj.verLista();
      
    }
    
}