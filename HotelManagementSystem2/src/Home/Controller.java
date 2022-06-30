package Home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button btnExit;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtGender;
    @FXML
    private TextField txtCountry;
    @FXML
    private TextField txtContact;
    @FXML
    private Button btnAddGuest;
    Connection con1;
    PreparedStatement insert;



    @FXML
    public void handleButtonAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        String name = txtName.getText();
        String gender = txtGender.getText();
        String country = txtCountry.getText();
        String contact = txtContact.getText();
        if (event.getSource() == btnAddGuest) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost/hms", "root", "Your password here");
            insert = con1.prepareStatement("insert into guests(Name,Gender,Country,Contact)values(?,?,?,?)");
            insert.setString(1, name);
            insert.setString(2, gender);
            insert.setString(3, country);
            insert.setString(4, contact);

            insert.executeUpdate();
            txtName.setText("");
            txtGender.setText("");
            txtCountry.setText("");
            txtContact.setText("");
        }
    }
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
