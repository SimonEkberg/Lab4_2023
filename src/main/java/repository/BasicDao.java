package repository;

import db.DbConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * @Author Simon Siljamäki Ekberg
 */
public abstract  class BasicDao <T> {
    DbConnectionManager dbConnectionManager = null;
    protected BasicDao() {
        this(DbConnectionManager.getInstance());
    }
    protected BasicDao(DbConnectionManager dbConnectionManager) {
        this.dbConnectionManager = dbConnectionManager;
    }

    protected abstract T convertResultSetToDomainObject(ResultSet resultSet) throws SQLException;

    /**
     *
     * @param resultSet If not null - object are being saved, if null - object are being updated
     * @param argLlist List of argument/attribute of incoming object
     * @return New domain object
     * @throws SQLException
     */
    protected abstract T convertFromSaveOrUpdateToDomainObject(Optional<ResultSet> resultSet, List<Object> argLlist) throws SQLException;
    protected PreparedStatement getStatement(String SQLstring,
                                             int statementReturnValue) throws SQLException{
        return dbConnectionManager.prepareStatement(SQLstring, statementReturnValue);
    }
    protected Optional<T> get(String sqlString) throws SQLException{
        ResultSet resultSet = dbConnectionManager.excecuteQuery(sqlString);
        if(!resultSet.next())
            return Optional.ofNullable(null);
        return Optional.ofNullable(convertResultSetToDomainObject(resultSet));
    }
    protected List<T> getAll(String sqlString) throws SQLException{
        ArrayList<T> list = new ArrayList<>();
        ResultSet resultSet = dbConnectionManager.excecuteQuery(sqlString);
        while (resultSet.next()){
            list.add(convertResultSetToDomainObject(resultSet));
        }
        return list;
    }
    protected Optional<T> save(String sqlString, Object... args) throws SQLException{
        PreparedStatement preparedStatement = getStatement(sqlString, Statement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);
        }
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected == 0)
            return Optional.empty();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        Optional<ResultSet> resultSet = Optional.of(generatedKeys);
        return Optional.ofNullable(convertFromSaveOrUpdateToDomainObject(resultSet, List.of(args)));
    }
  protected Optional<T> update(String sqlString, Object... args) throws SQLException {
      PreparedStatement preparedStatement = getStatement(sqlString,Statement.NO_GENERATED_KEYS);
      for (int i = 0; i < args.length; i++) {
          preparedStatement.setObject(i+1, args[i]);
      }
      int rowsAffected = preparedStatement.executeUpdate();
      if (rowsAffected == 0) {
          return Optional.empty();
      }
      return Optional.ofNullable(convertFromSaveOrUpdateToDomainObject(null, List.of(args)));
  }
  protected void delete(String sqlString) throws SQLException{
        PreparedStatement preparedStatement = getStatement(sqlString, Statement.NO_GENERATED_KEYS);
        preparedStatement.executeUpdate();
  }
}
