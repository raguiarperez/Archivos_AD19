
package archivos_ad19;

import java.sql.*;
/**
 *
 * @author dam2
 */
public class Producto {
    
    Connection conn;

    public void conectar() throws SQLException {

        String driver = "jdbc:oracle:thin:";
        String host = "localhost.localdomain";
        String porto = "1521";
        String sid = "orcl";
        String usuario = "hr";
        String password = "hr";
        String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;
        conn = DriverManager.getConnection(url);
    }

    public void verLista() throws SQLException {
        //Para crear un ResultSet de tipo "scrollable" y "updatable":
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery("select produtos.* from produtos");
        System.out.println("Codigo\t" + "Descricion\t" + "Prezo");
        while (rs.next()) { //Se separan con el println mientras haya que escribir
            for (int i = 1; i <= 3; i++) { //escribe los 3 en linea
                System.out.print(rs.getString(i) + "        ");
            }
            System.out.println();
        }
        rs.close();
    }

    public void updatePrezo(String codigo, int cambioPrezo) {

        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = st.executeQuery("select produtos.* from produtos");

            while (rs.next()) {
                //Para comparar los String con el equals
                if (rs.getString(1).equals(codigo)) { //vamos a la columna que queremos e igualamos cuando coincide, se hace
                    rs.updateInt("prezo", cambioPrezo);
                    rs.updateRow();
                }
            }
            System.out.println("Prezo actualizado");
        } catch (SQLException ex) {
            System.out.println("Non se puido actualizar o prezo");

        }

    }

    public void insertarFila(String cod, String desc, int prezo) {

        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select produtos.* from produtos");
            rs.moveToInsertRow();
            rs.updateString("codigo", cod);
            rs.updateString("descricion", desc);
            rs.updateInt("prezo", prezo);
            rs.insertRow();
            System.out.println("Fila insertada");
        } catch (SQLException ex) {
            System.out.println("No se pudo insertar la fiña");
        }
    }

    public void borrarFila(String cod) {

        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("select produtos.* from produtos");
            while (rs.next()) {
                if (rs.getString(1).equals(cod)) {
                    rs.deleteRow();
                    break;
                }
            }
            System.out.println("Fila borrada");
        } catch (SQLException ex) {
            System.out.println("No se pudo borrar la fila");
        }
    }
}

