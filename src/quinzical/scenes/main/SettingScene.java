package quinzical.scenes.main;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import quinzical.utils.AppTheme;

/**
 * The is the setting scene where user can reset the game and change
 * themes of the application
 * 
 * @author Marcus and JiaQi
 *
 */
public class SettingScene extends Menu{

	private Stage primaryStage;
	private Scene menu;
	private Button light, dark, nz, maori, back, reset;
	private final DropShadow shadow = new DropShadow();
	private Background bg;
	
	/**
	 * This is the constructor
	 * @param primaryStage the stage of the application
	 * @param menuScene the main menu scene
	 * @param theme the theme of the application
	 * @param layout the Vbox layout of main menu scene
	 */
	public SettingScene(Stage primaryStage, Scene menuScene, AppTheme theme, VBox layout) {
		this.primaryStage = primaryStage;
		menu = menuScene;
		super.theme = theme;
		super.layout = layout;
	}

	/**
	 * Method to start the scene
	 */
	public void startScene() {
		// Title text
		Text label = new Text("Choose a background theme!");
		label.setFill(Color.web("#f26868"));
		label.setStroke(Color.web("#e82a2a"));
		label.setFont(new Font("Arial", 24));
		
		// Set up for theme buttons, reset and main menu button
		shadow.setColor(Color.web("#7f96eb"));
		light = new Button("Light Theme");
		dark = new Button("Dark Theme");
		nz = new Button("New Zealand Flag");
		maori = new Button("Maori Flag");
		back = new Button("Main Menu");
		reset= new Button("Reset Game");
		
		//set up effect for buttons
		light.setEffect(shadow);
		dark.setEffect(shadow);
		nz.setEffect(shadow);
		maori.setEffect(shadow);
		back.setEffect(shadow);
		reset.setEffect(shadow);
		bg = theme.getBackground();

		// create setting scene
		VBox vbox = new VBox(40);
		vbox.getChildren().addAll(label, light, dark, nz, maori, reset, back);

		vbox.setAlignment(Pos.BASELINE_CENTER);
		vbox.setBackground(bg);
		vbox.setPadding(new Insets(100));
		Scene setting = new Scene(vbox, 650, 600);
		primaryStage.setScene(setting);
		
		// Handle when the light button is pressed
		light.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				theme.changeLight();
				bg = theme.getBackground();
				VBox vbox2 = new VBox(40);
				vbox2.getChildren().addAll(label, light, dark, nz, maori, reset, back);
				vbox2.setAlignment(Pos.BASELINE_CENTER);
				vbox2.setBackground(bg);
				vbox2.setPadding(new Insets(100));
				Scene setting = new Scene(vbox2, 650, 600);
				primaryStage.setScene(setting);
				layout.setBackground(bg);
			}
		});
		
		// Handle when the dark button is pressed
		dark.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				theme.changeDark();
				bg = theme.getBackground();
				VBox vbox2 = new VBox(40);
				vbox2.setBackground(bg);
				vbox2.getChildren().addAll(label,light, dark, nz, maori, reset, back);
				vbox2.setAlignment(Pos.BASELINE_CENTER);
				vbox2.setPadding(new Insets(100));
				Scene setting = new Scene(vbox2, 650, 600);
				primaryStage.setScene(setting);
				layout.setBackground(bg);
			}
		});
		
		// Handle when NZ button is pressed
		nz.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				theme.changeNZ();
				bg = theme.getBackground();
				VBox vbox2 = new VBox(40);
				vbox2.getChildren().addAll(label, light, dark, nz, maori,reset, back);
				vbox2.setAlignment(Pos.BASELINE_CENTER);
				vbox2.setBackground(bg);
				vbox2.setPadding(new Insets(100));
				Scene setting = new Scene(vbox2, 650, 600);
				primaryStage.setScene(setting);
				layout.setBackground(bg);
			}
		});
		
		// Handle when Maori button is pressed
		maori.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				theme.changeMaori();
				bg = theme.getBackground();
				VBox vbox2 = new VBox(40);
				vbox2.getChildren().addAll(label, light, dark, nz, maori,reset, back);
				vbox2.setAlignment(Pos.BASELINE_CENTER);
				vbox2.setBackground(bg);
				vbox2.setPadding(new Insets(100));
				Scene setting = new Scene(vbox2, 650, 600);
				primaryStage.setScene(setting);
				layout.setBackground(bg);
			}
		});
		
		// Handle when back button is pressed
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(menu);
			}
		});
		
		// Handle when reset button is pressed
		reset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Alert confirm = new Alert(AlertType.CONFIRMATION);
				confirm.setTitle("Quinzical");
				confirm.setHeaderText("Do you want to reset the game?");
				
				ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
				ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

				confirm.getButtonTypes().setAll(yesButton, noButton);
				
				confirm.showAndWait();
				
				// If user selected yes close the game, else continue to play
				if(confirm.getResult() == yesButton) {
					File saveDir = new File("./saves");
					// Clear all save data
					for(File file : saveDir.listFiles()) {
						file.delete();
					}
				}
			}	
		});
	}
}
