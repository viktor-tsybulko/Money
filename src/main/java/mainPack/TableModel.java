package mainPack;

import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Solush on 17.02.2017.
 */
public class TableModel extends AbstractTableModel {
        static private Connection connection = null;
        static private Statement statement = null;
        static private ResultSet resultSet = null;
        static private int columnCount;
        static private int rowCount;
        static private ArrayList<String> columnName = new ArrayList<String>();
        static private ArrayList<String[]> dataArray = new ArrayList<String[]>();
        TableModel(){

        }

        static void initDB(String nameParty){
            try {
                dataArray.clear();
                ConnectionJDBC conjdbc = new ConnectionJDBC();
                conjdbc.init(nameParty);
                connection = conjdbc.getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT QMAN FROM QUANTITY");
                rowCount = resultSet.getInt(1);
                resultSet = statement.executeQuery("SELECT USERNAME FROM USER");
                columnName.add("Who/Whom");
                while (resultSet.next()) {
                    columnName.add(resultSet.getString(1));
                }
                columnName.add("Summ");
                resultSet = statement.executeQuery("SELECT * FROM USER");
                columnCount = resultSet.getMetaData().getColumnCount();
                while (resultSet.next()){
                    String[] row = new String[columnCount];
                    for (int i = 1; i <= columnCount; i++){
                        row[i - 1] = resultSet.getString(i);
                    }
                    dataArray.add(row);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                        resultSet.close();
                        statement.close();
                        connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }



        @Override
        public int getRowCount() {
            return rowCount;
        }

        @Override
        public int getColumnCount() {
            return columnCount;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            String[] rows = dataArray.get(rowIndex);
            return rows[columnIndex];
        }
        @Override
        public String getColumnName(int columnIndex){
            return columnName.get(columnIndex);
        }




}
