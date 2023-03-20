package repository;

import db.DbConnectionManager;
import domainModell.person.Person;
import domainModell.site.Site;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class SiteDao extends BasicDao<Site> implements Dao<Site> {

    DbConnectionManager dbConManagerSingleton = null;
    public SiteDao() {
        this(new DbConnectionManager());
        dbConManagerSingleton = DbConnectionManager.getInstance();
    }
    public SiteDao(DbConnectionManager dbConnectionManager) {
        this.dbConManagerSingleton = dbConnectionManager;
    }

    @Override
    public Optional<Site> get(int id) throws NoSuchElementException {
        Site site = null;
        try {
            ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, site_name, site_city FROM sites WHERE id=" + id);
            if (!resultSet.next())
                throw new NoSuchElementException("The site with id " + id + " doesen't exist in database");
            else
                site = new Site(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
            dbConManagerSingleton.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(site);
    }

    @Override
    public List<Site> getAll() throws SQLException {
        return super.getAll("SELECT id, site_name, site_city FROM sites");
    }

    @Override
    public Optional<Site> save(Site t) {
        ResultSet resultSet;
        PreparedStatement preparedStatement = null;
        DbConnectionManager.getInstance().open();
        try {
            preparedStatement = dbConManagerSingleton.prepareStatement(
                    "INSERT INTO sites (site_name, site_city) " +
                            "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, t.getName());
            preparedStatement.setString(2, t.getSiteCity());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                return Optional.of(new Site(resultSet.getInt(1), t.getName(), t.getSiteCity()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbConnectionManager.getInstance().close();
        return Optional.ofNullable(t);
    }

    public Optional<Site> update(Site t) throws NoSuchElementException {
        PreparedStatement preparedStatement = null;
        int rowCount = 0;
        try {
            preparedStatement = dbConManagerSingleton.prepareStatement(
                    "UPDATE sites SET site_name=?, site_city=? WHERE id=" + t.getId(), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, t.getName());
            preparedStatement.setString(2, t.getSiteCity());
            rowCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rowCount == 0) {
            throw new NoSuchElementException("Site with ID " + t.getId() + " does not exist in the database.");
        }
        return Optional.of(new Site((int) t.getId(), t.getName(), t.getSiteCity()));
    }

    @Override
    public Optional<Site> delete(int id) {
        Site site = null;
        PreparedStatement preparedStatement = null;
        try {
            ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, site_name, site_city" +
                    " FROM sites WHERE id=" + id);
            if (!resultSet.next())
                throw new NoSuchElementException("The site with id " + id + " doesn't exist in database");
            else
                site = new Site(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            preparedStatement = dbConManagerSingleton.prepareStatement(
                    "DELETE FROM sites WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(site);
    }

    @Override
    protected Site convertResultSetToDomainObject(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("site_name");
        String city = resultSet.getString("site_city");
        return new Site(id, name, city);
    }

    @Override
    protected Site convertFromSaveOrUpdateToDomainObject(Optional<ResultSet> resultSet, List<Object> argLlist) throws SQLException {
        int id;
        if (resultSet != null) {
            id = resultSet.get().getInt(1);
        }else
            id = (int) argLlist.get(2);
        String name = (String) argLlist.get(0);
        String city = (String) argLlist.get(1);
        return new Site(id, name, city);
    }
}
