/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.controller;

import aplikasi.animations.FadeInLeftTransition;
import aplikasi.animations.FadeInLeftTransition1;
import aplikasi.animations.FadeInRightTransition;
import aplikasi.config.config;
import aplikasi.config.config2;
import aplikasi.config.database;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author aplikasi
 */
public class controllLogin implements Initializable {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Text lblWelcome;
    @FXML
    private Text lblUserLogin;
    @FXML
    private Text lblUsername;
    @FXML
    private Text lblPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnLogin1;
    @FXML
    private Text lblRudyCom;
    @FXML 
    private Label lblClose;        
    Stage stage;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            new FadeInRightTransition(lblUserLogin).play();
            new FadeInLeftTransition(lblWelcome).play();
            new FadeInLeftTransition1(lblPassword).play();
            new FadeInLeftTransition1(lblUsername).play();
            new FadeInLeftTransition1(txtUsername).play();
            new FadeInLeftTransition1(txtPassword).play();
            new FadeInRightTransition(btnLogin).play();
            new FadeInRightTransition(btnLogin1).play();
            lblClose.setOnMouseClicked((MouseEvent event) -> {
                Platform.exit();
                System.exit(0);
            });
        });
        // TODO
    }    

    @FXML
    private void aksiLogin(ActionEvent event) {
        if (txtUsername.getText().equals("admin") && txtPassword.getText().equals("admin")) {
            config2 c = new config2();
            c.newStage(stage, lblClose, "/aplikasi/view/homeAdmin.fxml", "Test App", true, StageStyle.UNDECORATED, false);
        }else{
            config2.dialog(Alert.AlertType.ERROR, "Error Login, Please Chek Username And Password");
        }
    }
    
    @FXML
    private void aksiMultipleLogin(ActionEvent event) {
		// TODO Auto-generated method stub
    	try {
            Connection conn = (Connection) database.getConnection();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rs = stm.executeQuery("SELECT * FROM user WHERE username='"+ txtUsername.getText() +"' AND password=MD5('" + txtPassword.getText() + "')");
            
            if (rs.next()) {
                if ("1".equals(rs.getString("typeuser"))) {
                    // its for login user type 1 (Administrator)
                    // Bila Login Sukses Maka Masuk Beranda
                    config2 c = new config2();
                    c.newStage(stage, lblClose, "/aplikasi/view/homeAdmin.fxml", "Test App", true, StageStyle.UNDECORATED, false);
                    // do something else for user 1
                } else if ("0".equals(rs.getString("typeuser"))) {
                    // its for user 2 (TU)
                	config2 c = new config2();
                    c.newStage(stage, lblClose, "/aplikasi/view/homeCustomer.fxml", "Test App", true, StageStyle.UNDECORATED, false);
                } else {
                    //do anything you want !
                }
            } else {
                // jika login gagal / salah username password
                config2.dialog(Alert.AlertType.ERROR, "Error Login, Please Chek Username And Password");
            }
        } catch (SQLException e) {
        
        }        
	}
    
    @FXML
    private void enterLogin(KeyEvent event){
    	if (event.getCode() == KeyCode.ENTER) {
			System.out.println("Pindah ke aksi login");
		}
    }
    
    
    
}
