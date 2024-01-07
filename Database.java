import java.sql.*;

    //Interface Database
    public interface Database {
        void view() throws SQLException;
        void insert() throws SQLException;
        void update() throws SQLException;
        void save() throws SQLException;
        void delete() throws SQLException;
    }