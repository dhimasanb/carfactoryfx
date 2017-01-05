package aplikasi.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import aplikasi.animations.FadeInLeftTransition1;
import aplikasi.animations.FadeInRightTransition;
import aplikasi.animations.FadeInUpTransition;
import aplikasi.config.config2;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class homeCustomerController implements Initializable {
	
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
	private Pane menuCustomer;
	
	@FXML
	private Pane menuVehicle;
	
	@FXML
	private Pane menuReport;
	
	@FXML
	private Pane menuSpareparts;
	
	@FXML
	private Pane menuAbout;
	
	@FXML
	private AnchorPane anchorLogo;
	
	@FXML
	private AnchorPane anchorAdmin;
	
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
        rec2 = Screen.getPrimary().getVisualBounds(); 
        w = 0.1;
        h = 0.1;
        Platform.runLater(() -> {
            stage = (Stage) maximize.getScene().getWindow();
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
            maximize.getStyleClass().add("decoration-button-restore");
            resize.setVisible(false);
            new FadeInUpTransition(anchorLogo).play();
            new FadeInUpTransition(anchorAdmin).play();
            new FadeInRightTransition(menuEmployees).play();
            new FadeInLeftTransition1(menuCustomer).play();
            new FadeInLeftTransition1(menuReport).play();
            new FadeInRightTransition(menuSpareparts).play();
            new FadeInRightTransition(menuVehicle).play();
            new FadeInLeftTransition1(menuAbout).play();
//          lblClose.setOnMouseClicked((MouseEvent event) -> {
//          Platform.exit();
//          System.exit(0);
//      });
        });
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
    private void aksiKlikEmployeesMenu(MouseEvent event) {
        config2 config = new config2();
        config.newStage2(stage, btnLogout, "/aplikasi/view/formMenu.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
    }
    
    @FXML
    private void aksiKlikVehicleMenu(MouseEvent event) {
        config2 config = new config2();
        config.newStage2(stage, btnLogout, "/aplikasi/view/aboutUs.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
    }
    
    @FXML
    private void aksiKlikSparepartsMenu(MouseEvent event) {
        config2 config = new config2();
        config.newStage2(stage, btnLogout, "/aplikasi/view/sparepartsMenu.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
    }
    
    @FXML
    private void aksiKlikCustomerMenu(MouseEvent event) {
        config2 config = new config2();
        config.newStage2(stage, btnLogout, "/aplikasi/view/customerArea.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
    }
    
    @FXML
    private void aksiKlikFinancialMenu(MouseEvent event) {
        config2 config = new config2();
        config.newStage2(stage, btnLogout, "/aplikasi/view/financialReports.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
    }
    
    @FXML
    private void aksiKlikAboutMenu(MouseEvent event) {
        config2 config = new config2();
        config.newStage2(stage, btnLogout, "/aplikasi/view/aboutUs.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
    }

    @FXML
    private void aksiLogout(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Are you sure logout from Customer Dashboard?");
		alert.setContentText("Please choose!");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    // ... user chose OK
	        config2 config = new config2();
	        config.newStage2(stage, btnLogout, "/aplikasi/view/login.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
		} else {
		    // ... user chose CANCEL or closed the dialog
			// tidak melakukan apa2
		}	
 
    }
}
