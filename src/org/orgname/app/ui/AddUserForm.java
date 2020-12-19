package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.database.entity.GenderEnum;
import org.orgname.app.database.entity.UserEntity;
import org.orgname.app.database.manager.UserEntityManager;
import org.orgname.app.util.BaseForm;
import org.orgname.app.util.BaseSubForm;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

public class AddUserForm extends BaseSubForm<UsersTableForm>
{
    private final UserEntityManager userEntityManager = new UserEntityManager(Application.getInstance().getDatabase());

    private JPanel mainPanel;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JTextField ageField;
    private JTextField jobField;
    private JButton backButton;
    private JButton nextButton;
    private JComboBox genderBox;

    public AddUserForm(UsersTableForm mainForm)
    {
        super(mainForm);
        setContentPane(mainPanel);

        initElements();
        initButtons();

        setVisible(true);
    }

    private void initElements()
    {
        genderBox.addItem(GenderEnum.MALE);
        genderBox.addItem(GenderEnum.FEMALE);
    }

    private void initButtons()
    {
        backButton.addActionListener(e -> {
            closeSubForm();
        });

        nextButton.addActionListener(e -> {
            //подразумеваются проверки полей на корректность
            UserEntity user = new UserEntity(
                    loginField.getText(),
                    new String(passwordField.getPassword()),
                    (GenderEnum) genderBox.getSelectedItem(),
                    Integer.parseInt(ageField.getText()),
                    jobField.getText()
            );

            try {
                userEntityManager.add(user);
                mainForm.getModel().addRow(
                        new Object[] {
                                user.getId(),
                                user.getLogin(),
                                user.getPassword(),
                                user.getGender(),
                                user.getAge(),
                                user.getJob(),
                                user.getNotes()
                        }
                );
                closeSubForm();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    @Override
    public int getFormWidth() {
        return 400;
    }

    @Override
    public int getFormHeight() {
        return 250;
    }
}
