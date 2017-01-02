package aplikasi.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import aplikasi.config.config2;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class aboutUsController implements Initializable {
	
	@FXML
	private Label title;
	
	@FXML
	private Button fullscreen;
	
	@FXML
	private Button minimize;
	
	@FXML
	private Button maximize;
	
	@FXML
	private Button close;
	
	@FXML
	private Button resize;
	
	@FXML
	private Pane menuEmployees;
	
	@FXML
	private Pane menuClient;
	
	@FXML
	private Pane menuCar;
	
	@FXML
	private Pane menuReport;
	
	@FXML
	private Button btnLogout;
	Stage stage;
	Rectangle2D rec2;
	Double w,h;
	
	@FXML
	private ListView<String> listMenu;
	
	@FXML
	private AnchorPane paneData;
	config2 con = new config2();
	
	/**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

	// Event Listener on Button[#aksiMaximized].onAction
	@FXML
    private void aksiMaximized(ActionEvent event) {
        stage = (Stage) maximize.getScene().getWindow();
        if (stage.isMaximized()) {
            if (w == rec2.getWidth() && h == rec2.getHeight()) {
                stage.setMaximized(false);
                stage.setHeight(600);
                stage.setWidth(800);
                stage.centerOnScreen();
                maximize.getStyleClass().remove("decoration-button-restore");
                resize.setVisible(true);
            }else{
                stage.setMaximized(false);
                maximize.getStyleClass().remove("decoration-button-restore");
                resize.setVisible(true);
            }
            
        }else{
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
            maximize.getStyleClass().add("decoration-button-restore");
            resize.setVisible(false);
        }
    }

    @FXML
    private void aksiminimize(ActionEvent event) {
        stage = (Stage) minimize.getScene().getWindow();
        if (stage.isMaximized()) {
            w = rec2.getWidth();
            h = rec2.getHeight();
            stage.setMaximized(false);
            stage.setHeight(h);
            stage.setWidth(w);
            stage.centerOnScreen();
            Platform.runLater(() -> {
                stage.setIconified(true);
            });
        }else{
            stage.setIconified(true);
        }        
    }

    @FXML
    private void aksiResize(ActionEvent event) {
    }

    @FXML
    private void aksifullscreen(ActionEvent event) {
        stage = (Stage) fullscreen.getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        }else{
            stage.setFullScreen(true);
        }
    }

    @FXML
    private void aksiClose(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void aksiKlikListMenu(MouseEvent event) {
        switch(listMenu.getSelectionModel().getSelectedIndex()){
            case 0:{
                con.loadAnchorPane(paneData, "customer.fxml");
            }break;
            case 1:{
                con.loadAnchorPane(paneData, "product.fxml");
            }break;
            case 2:{
                con.loadAnchorPane(paneData, "micro.fxml");
            }break;
        }
    }

    @FXML
    private void aksiLogout(ActionEvent event) {
        config2 config = new config2();
        config.newStage2(stage, btnLogout, "/aplikasi/view/homeAdmin.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
    }
}
