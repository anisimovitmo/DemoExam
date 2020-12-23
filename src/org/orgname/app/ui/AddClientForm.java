package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.database.entity.ClientEntity;
import org.orgname.app.database.manager.ClientEntityManager;
import org.orgname.app.util.BaseSubForm;
import org.orgname.app.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddClientForm extends BaseSubForm<ClientTableForm>
{
    private final ClientEntityManager clientEntityManager = new ClientEntityManager(Application.getInstance().getDatabase());
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    private JPanel mainPanel;
    private JTextField firstnameField;
    private JTextField surnameField;
    private JTextField patronymicField;
    private JTextField birthdayField;
    private JTextField emailField;
    private JTextField phoneField;
    private JComboBox genderBox;
    private JButton backButton;
    private JButton saveButton;
    private JTextField photoPathField;

    public AddClientForm(ClientTableForm mainForm)
    {
        super(mainForm);
        setContentPane(mainPanel);

        initBoxes();
        initButtons();

        setVisible(true);
    }

    private void initBoxes()
    {
        genderBox.addItem("Мужской");
        genderBox.addItem("Женский");
    }

    private void initButtons()
    {
        backButton.addActionListener(e -> {
            closeSubForm();
        });

        saveButton.addActionListener(e -> {
            try {
                //тут должны проверки на корректность полей
                ClientEntity newClient = new ClientEntity(
                        firstnameField.getText(),
                        surnameField.getText(),
                        patronymicField.getText(),
                        format.parse(birthdayField.getText()),
                        new Date(),
                        emailField.getText(),
                        phoneField.getText(),
                        ((String) genderBox.getSelectedItem()).toLowerCase().charAt(0),
                        photoPathField.getText()
                );

                clientEntityManager.add(newClient);
                mainForm.getModel().getValues().add(newClient);
                mainForm.getModel().fireTableDataChanged();

                closeSubForm();

            } catch (SQLException | ParseException ex) {
                ex.printStackTrace();
                DialogUtil.showError(AddClientForm.this, "Не удалось добавить клиента");
            }
        });
    }

    @Override
    public int getFormWidth() {
        return 400;
    }

    @Override
    public int getFormHeight() {
        return 500;
    }
}
