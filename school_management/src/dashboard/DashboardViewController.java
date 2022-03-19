/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import com.jfoenix.controls.JFXButton;
import helpres.Links;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author hocin
 */
public class DashboardViewController implements Initializable {

    @FXML
    private JFXButton notificationBtn;
    @FXML
    private JFXButton menuBtn;
    @FXML
    private JFXButton menuBtn1;
    @FXML
    private JFXButton menuBtn11;
    @FXML
    private AnchorPane slideAnchor;
    @FXML
    private VBox notificationsSlide;
    @FXML
    private Button removeNotificationsBtn;
    @FXML
    private JFXButton alertsBtn;
    @FXML
    private JFXButton histoiryBtn;
    @FXML
    private JFXButton ignoredBtn;
    @FXML
    private VBox ingoreVb;
    @FXML
    private VBox historyVb;
    @FXML
    private VBox alertVb;
    @FXML
    private VBox menuSlide;
    @FXML
    private Button removeMenuBtm;
    @FXML
    private VBox menuScorll;
    @FXML
    private Circle circle;
    @FXML
    private JFXButton menuBtn2;

    boolean menuIsActive;
    boolean notificationsIsActive;
    int countMenu;
    int countNotif;
    @FXML
    private VBox anchor;
    @FXML
    private AnchorPane anchorMenuItems;
    @FXML
    private JFXButton removeItemsView;
    @FXML
    private JFXButton employeesBtn;

    boolean anchorMenuActive;
    int countAnchorMenu;
    @FXML
    private VBox ItemsVbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image image = new Image(Links.PROFILEIMAGE);
        circle.setFill(new ImagePattern(image));
        slideTransition(slideAnchor, 420, 0.1);
        slideTransition(anchorMenuItems, 1020, 0.1);

        menuIsActive = false;
        notificationsIsActive = false;
        countMenu = 1;
        countNotif = 1;
        anchorMenuActive = false;
        countAnchorMenu = 1;

        menuBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                slideTransition(menuSlide, -420, 400);
                menuIsActive = true;
                countMenu = 0;
                anchor.setStyle("-fx-background-color:#06080D;-fx-opacity:.4;");

            }
        });
        removeMenuBtm.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                slideTransition(menuSlide, 420, 400);
                menuIsActive = false;
                countMenu = 1;
                anchor.setStyle("-fx-background-color:#202940;-fx-opacity:1;");
            }
        });
        notificationBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                slideTransition(notificationsSlide, -420, 400);
                notificationsIsActive = true;
                countNotif = 0;
                anchor.setStyle("-fx-background-color:#06080D;-fx-opacity:.4;");
            }
        });
        removeNotificationsBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                slideTransition(notificationsSlide, 420, 400);
                notificationsIsActive = false;
                countNotif = 1;
                anchor.setStyle("-fx-background-color:#202940;-fx-opacity:1;");
            }
        });

        anchor.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                countMenu++;
                countNotif++;
                countAnchorMenu++;
                if (notificationsIsActive = true && countNotif == 1) {
                    slideTransition(notificationsSlide, 420, 400);
                    notificationsIsActive = false;
                }
                if (menuIsActive = true && countMenu == 1) {
                    slideTransition(menuSlide, 420, 400);
                    menuIsActive = false;

                }
                if (anchorMenuActive = true && countAnchorMenu == 1) {
                    slideTransition(anchorMenuItems, 1020, 400);
                    anchorMenuActive = false;
                }
                anchor.setStyle("-fx-background-color:#202940;-fx-opacity:1;");
            }
        });

        // notification code 
        alertsBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                alertsBtn.setStyle("-fx-border-color:#018934;-fx-font-weight:bold;-fx-font-size:14;-fx-font-family:\"Century Gothic\";");
                alertVb.setVisible(true);
                histoiryBtn.setStyle("-fx-border-color:transparent;-fx-font-size:14;-fx-font-family:\"Century Gothic\";");
                historyVb.setVisible(false);
                ignoredBtn.setStyle("-fx-border-color:transparent;-fx-font-size:14;-fx-font-family:\"Century Gothic\";");
                ingoreVb.setVisible(false);

            }
        });
        histoiryBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                histoiryBtn.setStyle("-fx-border-color:#018934;-fx-font-weight:bold;-fx-font-size:14;-fx-font-family:'Century Gothic';");
                historyVb.setVisible(true);

                alertsBtn.setStyle("-fx-border-color:transparent;-fx-font-size:14;-fx-font-family:'Century Gothic';");
                alertVb.setVisible(false);

                ignoredBtn.setStyle("-fx-border-color:transparent;-fx-font-size:14;-fx-font-family:'Century Gothic';");
                ingoreVb.setVisible(false);

            }
        });
        ignoredBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ignoredBtn.setStyle("-fx-border-color:#018934;-fx-font-weight:bold;-fx-font-size:14;-fx-font-family:\"Century Gothic\";");
                ingoreVb.setVisible(true);

                alertsBtn.setStyle("-fx-border-color:transparent;-fx-font-size:14;-fx-font-family:\"Century Gothic\";");
                alertVb.setVisible(false);

                histoiryBtn.setStyle("-fx-border-color:transparent;-fx-font-size:14;-fx-font-family:\"Century Gothic\";");
                historyVb.setVisible(false);

            }
        });
        employeesBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                loadViews(Links.EMPLOYEESSVIEW);
                slideTransition(anchorMenuItems, -1020, 400);
                anchorMenuActive = true;
                countAnchorMenu = 0;
                anchor.setStyle("-fx-background-color:#06080D;-fx-opacity:.4;");
                
            }
        });
        removeItemsView.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                loadViews(Links.EMPLOYEESSVIEW);
                slideTransition(anchorMenuItems, 1020, 400);
                anchorMenuActive = false;
                countAnchorMenu = 1;
                anchor.setStyle("-fx-background-color:#06080D;-fx-opacity:1;");
                
            }
        });

    }

    void slideTransition(Node node, double x, double t) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(t), node);
        tt.setByX(x);
        tt.fromXProperty();
        //tt.setCycleCount((int) 4f);
        //tt.setAutoReverse(true);
        tt.play();
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    @FXML
    private void remove(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setMaximized(true);
    }

    private void loadViews(String viewName) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(viewName));
            ItemsVbox.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(DashboardViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
