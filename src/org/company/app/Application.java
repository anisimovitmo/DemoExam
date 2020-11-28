package org.company.app;

import org.company.app.data.manager.UserEntityManager;
import org.company.app.ui.StartForm;
import org.company.app.util.BaseForm;
import org.company.app.util.MysqlDatabase;

import java.sql.Connection;
import java.sql.SQLException;

public class Application
{
    private static Application instance;

    private final MysqlDatabase database = new MysqlDatabase("116.202.236.174", "DemoExam", "DemoExam", "DemoExam");
    private final UserEntityManager userEntityManager = new UserEntityManager(database);

    public Application()
    {
        instance = this;

        initDatabase();
        initUi();

        new StartForm();

    }

    private void initDatabase()
    {
        try(Connection c = database.getConnection()) {
        } catch (SQLException e) {
            System.out.println("Соедиение с базой установить не удалось");
            e.printStackTrace();
            System.exit(228);
        }
    }

    private void initUi()
    {
        BaseForm.setBaseApplicationTitle("Медицинский центр трубочист");
    }

    public MysqlDatabase getDatabase() {
        return database;
    }

    public UserEntityManager getUserEntityManager() {
        return userEntityManager;
    }

    public static void main(String[] args)
    {
        new Application();
    }

    public static Application getInstance() {
        return instance;
    }
}
