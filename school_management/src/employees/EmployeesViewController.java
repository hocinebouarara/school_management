/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employees;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import dao.DbConnect;
import helpres.Links;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Callback;
import models.Employee;
import dao.employees.EmployeeDao;
import dao.employees.EmployeeDaoImp;

/**
 * FXML Controller class
 *
 * @author hocin
 */
public class EmployeesViewController implements Initializable {

    @FXML
    private TableView<Employee> employeesTabeView;
    @FXML
    private TableColumn<Employee, String> idCol;
    @FXML
    private TableColumn<Employee, String> imageCol;
    @FXML
    private TableColumn<Employee, String> nameCol;
    @FXML
    private TableColumn<Employee, String> birthCol;
    @FXML
    private TableColumn<Employee, String> addressCol;
    @FXML
    private TableColumn<Employee, String> phoneCol;
    @FXML
    private TableColumn<Employee, String> typeCol;
    @FXML
    private TableColumn<Employee, String> emailCol;
    @FXML
    private TableColumn<Employee, String> operationsCol;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    Employee employee = null;
    ObservableList<Employee> employeesList = FXCollections.observableArrayList();
    @FXML
    private JFXButton add;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connection = DbConnect.getConnect();
        refreshTable();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        imageCol.setCellValueFactory(new PropertyValueFactory<>("image"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        birthCol.setCellValueFactory(new PropertyValueFactory<>("birth"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        //editableCols();
        // ManageCol.setCellValueFactory(new PropertyValueFactory("update"));
        // insert btn in every row
        Callback<TableColumn<Employee, String>, TableCell<Employee, String>> cellFoctory = (TableColumn<Employee, String> param) -> {

            // make the tablecell containing buttons
            final TableCell<Employee, String> cell = new TableCell<Employee, String>() {

                // Override updateItem method
                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    // ensure that cell is created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        JFXCheckBox checkBox = new JFXCheckBox();
                        checkBox.setStyle(" -fx-cursor: hand ;"
                                + "-glyph-size:20px;");

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:21px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:21px;"
                                + "-fx-fill:#00E676;"
                        );

                        deleteIcon.setOnMouseClicked((MouseEvent mouseEvent) -> {

                            try {

                                employee = employeesTabeView.getSelectionModel().getSelectedItem();
                                connection = DbConnect.getConnect();
                                query = "delete from article where idar =" + employee.getId();
                                preparedStatement = connection.prepareCall(query);
                                preparedStatement.execute();
                                refreshTable();
                                employee = null;

                            } catch (SQLException ex) {
                                Logger.getLogger(EmployeesViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        editIcon.setOnMouseClicked((MouseEvent mouseEvent) -> {

                            employee = employeesTabeView.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/products/addProduct.fxml"));

                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(EmployeesViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
//                            AddProductController addProductController = loader.getController();
//                            addProductController.setTextFields(product.getId(), product.getReference(), product.getDesignation(),
//                                    product.getQuantity(), product.getCategory(), product.getBuyingPrice(),
//                                    product.getSalePrice(), product.getTotalBuy(), product.getTotalSale());
//                            
//                            addProductController.setUpdate(true);
//                            Parent p = loader.getRoot();
//                            Stage stage = new Stage();
//                            stage.setScene(new Scene(p));
//                            stage.initStyle(StageStyle.TRANSPARENT);
//                            stage.show();
//                            

                        });

                        HBox managebtn = new HBox(checkBox, editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(checkBox, new javafx.geometry.Insets(2, 0, 0, 2));
                        HBox.setMargin(editIcon, new javafx.geometry.Insets(2, 4, 0, 2));
                        HBox.setMargin(deleteIcon, new javafx.geometry.Insets(2, 0, 0, 3));
                        setGraphic(managebtn);

                        setText(null);

                    }

                }

            };

            return cell;

        };
        operationsCol.setCellFactory(cellFoctory);
        employeesTabeView.setItems(employeesList);

        add.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                EmployeeDao employeeDao = new EmployeeDaoImp();

//                Employee employee = new Employee(2, "image", "abdo bouarara", new Date(), "lardjem", "0782702330", "employé", "abdobouarara@gmail.com", "rabah08");
//                employeeDao.findAll().forEach(System.out::println);
//                Employee e = employeeDao.findById(1);
//                System.out.println(e);
                employeeDao.deleteById(30);

            }
        });

    }

    private void refreshTable() {
        employeesList.clear();

 
        
        try {
            query = "select * from employees";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Image image = new Image(Links.PROFILEIMAGE);
                Circle circle = new Circle();
                circle.setFill(new ImagePattern(image));
                System.out.println("link of image is :" + resultSet.getString(2));
                employeesList.add(new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("image"),
                        resultSet.getString("fullName"),
                        resultSet.getDate("birth"),
                        resultSet.getString("address"),
                        resultSet.getString("phone"),
                        resultSet.getString("type"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                )
                );
                employeesTabeView.setItems(employeesList);

            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            System.err.print(e);

        }
    }

}
