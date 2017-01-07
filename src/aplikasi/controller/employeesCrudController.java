package aplikasi.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;

import aplikasi.animations.FadeInUpTransition;
import aplikasi.config.config;
import aplikasi.config.config2;
import aplikasi.interfaces.interEmployees;
import aplikasi.model.Karyawan;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.scene.input.KeyEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class employeesCrudController implements Initializable {
	@FXML
	private AnchorPane paneCrud;
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtNik;
	@FXML
	private TextArea txtAddress;
	@FXML
	private TextField txtCity;
	@FXML
	private TextField txtState;
	@FXML
	private TextField txtPhone;
	@FXML
	private TextField txtEmail;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnBack;
    Integer status;
	@FXML
	private ComboBox cbGender;
	@FXML
	private TextField txtName;
	@FXML
	private AnchorPane paneTabel;
    interEmployees crud;
	@FXML
	private TableView<Karyawan> tableData;
	@FXML
	private TableColumn colAction;
	@FXML
	private TableColumn<Karyawan, String> colNik;
	@FXML
	private TableColumn<Karyawan, String> colName;
	@FXML
	private TableColumn<Karyawan, String> colGender;
	@FXML
	private TableColumn<Karyawan, String> colEmail;
	@FXML
	private TableColumn<Karyawan, String> colAdderss;
	@FXML
	private TableColumn<Karyawan, String> colCity;
	@FXML
	private TableColumn<Karyawan, String> colState;
	@FXML
	private TableColumn<Karyawan, String> colPhone;
	@FXML
	private Button btnNew;
	@FXML
	private ImageView imgLoad;
	@FXML
	private ProgressBar bar;
    private ObservableList<Karyawan> listData;

	@Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            ApplicationContext ctx = config.getInstance().getApplicationContext();
            crud = ctx.getBean(interEmployees.class);
            listData = FXCollections.observableArrayList();
            status = 0;
            config2.setModelColumn(colNik, "nik");
            config2.setModelColumn(colName, "name");
            config2.setModelColumn(colGender, "gender");
            config2.setModelColumn(colEmail, "email");
            config2.setModelColumn(colAdderss, "address");
            config2.setModelColumn(colCity, "city");
            config2.setModelColumn(colState, "state");
            config2.setModelColumn(colPhone, "phone");
            colAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, Boolean>,ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Object, Boolean> p) {
                    return new SimpleBooleanProperty(p.getValue() != null);
                }
            });
            colAction.setCellFactory(new Callback<TableColumn<Object, Boolean>, TableCell<Object, Boolean>>() {
                @Override
                public TableCell<Object, Boolean> call(TableColumn<Object, Boolean> p) {
                    return new ButtonCell(tableData);
                }
            });
            selectWithService();
//            displayDiscountCode();
//            displayZip();
        });
// TODO
    }
	
	private void clear(){
        txtNik.clear();
        txtName.clear();
        cbGender.setValue("");
        txtEmail.clear();
        txtAddress.clear();
        txtCity.clear();
        txtState.clear();
        txtPhone.clear();
    }

//	private void displayDiscountCode(){
//        Service<ObservableList<DiscountCode>> service = new Service<ObservableList<DiscountCode>>() {
//            @Override
//            protected Task<ObservableList<DiscountCode>> createTask() {
//                return new Task<ObservableList<DiscountCode>>() {           
//                    @Override
//                    protected ObservableList<DiscountCode> call() throws Exception {
//                        ObservableList<DiscountCode> listTask = FXCollections.observableArrayList();
//                        if(listTask == null){
//                            listTask = FXCollections.observableArrayList(crud.selectCode());
//                        }else {
//                            listTask.clear();
//                            listTask.addAll(crud.selectCode());
//                        }
//                        cbDiscount.setItems(listTask);
//                        return listTask;
//                    }
//                };
//            }
//        };
//        service.start();
//    }
//    
//    private void displayZip(){
//        Service<ObservableList<MicroMarket>> service = new Service<ObservableList<MicroMarket>>() {
//            @Override
//            protected Task<ObservableList<MicroMarket>> createTask() {
//                return new Task<ObservableList<MicroMarket>>() {           
//                    @Override
//                    protected ObservableList<MicroMarket> call() throws Exception {
//                        ObservableList<MicroMarket> listTask = FXCollections.observableArrayList();
//                        if(listTask == null){
//                            listTask = FXCollections.observableArrayList(crud.selectZip());
//                        }else {
//                            listTask.clear();
//                            listTask.addAll(crud.selectZip());
//                        }
//                        cbZip.setItems(listTask);
//                        return listTask;
//                    }
//                };
//            }
//        };
//        service.start();
//    }
    
    private void auto(){
        if (crud.select().isEmpty()) {
            txtId.setText("1");
        }else{
            txtId.setText(String.valueOf(crud.auto()));
        }
    }
    
    private void selectData(){
        if(listData == null){
            listData = FXCollections.observableArrayList(crud.select());
        }else {
            listData.clear();
            listData.addAll(crud.select());
        }
        tableData.setItems(listData);
    }
    
    private void selectWithService(){
        Service<Integer> service = new Service<Integer>() {
            @Override
            protected Task<Integer> createTask() {
                selectData();
                return new Task<Integer>() {           
                    @Override
                    protected Integer call() throws Exception {
                        Integer max = crud.select().size();
                        if (max > 35) {
                            max = 30;
                        }
                        updateProgress(0, max);
                        for (int k = 0; k < max; k++) {
                            Thread.sleep(40);
                            updateProgress(k+1, max);
                        }
                        return max;
                    }
                };
            }
        };
        service.start();
        bar.progressProperty().bind(service.progressProperty());
        service.setOnRunning((WorkerStateEvent event) -> {
            imgLoad.setVisible(true);
        });
        service.setOnSucceeded((WorkerStateEvent event) -> {
            imgLoad.setVisible(false);
            new FadeInUpTransition(paneTabel).play();
        });
    }
    
    
    
    @FXML
    private void keyState(KeyEvent e){
        if (txtState.getText().length() > 2) {
            config2.dialog(Alert.AlertType.INFORMATION, "State Must 2 Char");
            txtState.clear();
        }
    }
    
    @FXML
    private void aksiKlikTableData(MouseEvent event) {
        if (status==1) {
            try {
                Karyawan klik = tableData.getSelectionModel().getSelectedItem();
                txtId.setText(klik.getId().toString());
                txtNik.setText(klik.getNama());
                txtName.setText(klik.getNama());
                cbGender.setValue(klik.getJenisKelamin());
                txtEmail.setText(klik.getEmail());
                txtAddress.setText(klik.getAlamat());
                txtCity.setText(klik.getNegara());
                txtState.setText(klik.getProvinsi());
                txtPhone.setText(klik.getNoTlp());
            } catch (Exception e) {
            }
        }
    }
    
    @FXML
    private void aksiNew(ActionEvent event) {
        paneTabel.setOpacity(0);
        new FadeInUpTransition(paneCrud).play();
        Platform.runLater(() -> {
            clear();
            auto();
        });
    }
    
    @FXML
    private void aksiSave(ActionEvent event) {
        if (txtId.getText().isEmpty()) {
            config2.dialog(Alert.AlertType.ERROR, "ID Not Empty");
            txtId.requestFocus();
        }else if (txtNik.getText().isEmpty()) {
            config2.dialog(Alert.AlertType.ERROR, "NIK Not Empty");
            txtNik.requestFocus();
        }
//            else if (cbDiscount.getValue().equals("")) {
//            config2.dialog(Alert.AlertType.ERROR, "Discount Not Empty");
//            cbDiscount.requestFocus();
//        }else if (cbZip.getValue().equals("")) {
//            config2.dialog(Alert.AlertType.ERROR, "Zip Not Empty");
//            cbZip.requestFocus();
//        }
        else if (txtName.getText().isEmpty()) {
            config2.dialog(Alert.AlertType.ERROR, "Name Not Empty");
            txtName.requestFocus();
        } else if (cbGender.getValue().equals("")) {
          config2.dialog(Alert.AlertType.ERROR, "Gender Empty");
          cbGender.requestFocus();
        } else if (txtEmail.getText().isEmpty()) {
            config2.dialog(Alert.AlertType.ERROR, "Email Not Empty");
            txtEmail.requestFocus();
        }else if (txtAddress.getText().isEmpty()) {
            config2.dialog(Alert.AlertType.ERROR, "Address Not Empty");
            txtAddress.requestFocus();
        }else if (txtCity.getText().isEmpty()) {
            config2.dialog(Alert.AlertType.ERROR, "City Not Empty");
            txtCity.requestFocus();
        }else if (txtState.getText().isEmpty()) {
            config2.dialog(Alert.AlertType.ERROR, "State Not Empty");
            txtState.requestFocus();
        }else if (txtPhone.getText().isEmpty()) {
            config2.dialog(Alert.AlertType.ERROR, "Phone Not Empty");
            txtPhone.requestFocus();
        }else{
            Karyawan a = new Karyawan();
            a.setId(Integer.valueOf(txtId.getText()));
            a.setNik(Integer.valueOf(txtNik.getText()));
            a.setNama(txtName.getText());
            a.setJenisKelamin(cbGender.getValue().toString());
            a.setEmail(txtEmail.getText());
            a.setAlamat(txtAddress.getText());
            a.setNegara(txtCity.getText());
            a.setProvinsi(txtState.getText());
            a.setNoTlp(txtPhone.getText());
            crud.saveOrUpdate(a);
            clear();
            selectData();
            auto();
            config2.dialog(Alert.AlertType.INFORMATION, "Success Save Data. . .");
        }
    }
    
    @FXML
    private void aksiBack(ActionEvent event) {
        paneCrud.setOpacity(0);
        new FadeInUpTransition(paneTabel).play();
    }
    
    private class ButtonCell extends TableCell<Object, Boolean> {
        final Hyperlink cellButtonDelete = new Hyperlink("Delete");
        final Hyperlink cellButtonEdit = new Hyperlink("Edit");
        final HBox hb = new HBox(cellButtonDelete,cellButtonEdit);
        ButtonCell(final TableView tblView){
            hb.setSpacing(4);
            cellButtonDelete.setOnAction((ActionEvent t) -> {
                status = 1;
                int row = getTableRow().getIndex();
                tableData.getSelectionModel().select(row);
                aksiKlikTableData(null);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Delete Data "+txtName.getText()+" ?");
                alert.initStyle(StageStyle.UTILITY);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    Karyawan p = new Karyawan();
                    p.setId(Integer.valueOf(txtId.getText()));
                    crud.delete(p);
                    clear();
                    selectData();
                }else{
                    clear();
                    selectData();
                    auto();
                }
                status = 0;
            });
            cellButtonEdit.setOnAction((ActionEvent event) -> {
                status = 1;
                int row = getTableRow().getIndex();
                tableData.getSelectionModel().select(row);
                aksiKlikTableData(null);
                paneTabel.setOpacity(0);
                new FadeInUpTransition(paneCrud).play();
                status = 0;
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(hb);
            }else{
                setGraphic(null);
            }
        }
    }
}
