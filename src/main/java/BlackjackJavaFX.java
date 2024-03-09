import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BlackjackJavaFX extends Application {

	Label label_welcome, label_placeBet, label_placeMoney;
	TextField text_bet, text_money;
	ImageView img_token, img_money, img_title;
	Button btn_start, btn_saveBet, btn_saveMoney;
	int money, bet;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Blackjack in JavaFX");

		// Start Scene: Welcome Label
		label_welcome = new Label("Welcome to Blackjack!");
		label_welcome.setStyle("-fx-text-fill: white;" + "-fx-font-size: 40;"
				+ "-fx-font-family: Cambria");

		// Start Scene: title image
		Image title = new Image("Icon/title.png");
		img_title = new ImageView(title);
		img_title.setFitWidth(400);
		img_title.setPreserveRatio(true);

		// Start Scene: Place money label
		label_placeMoney = new Label("Place your starting money:");
		label_placeMoney.setStyle("-fx-text-fill: white;" + "-fx-font-size: 20;"
				+ "-fx-font-family: Cambria");

		// Start Scene: Money text field
		text_money = new TextField("" );
		text_money.setStyle("-fx-font-family: Cambria;" + "-fx-font-size: 20");

		// Start Scene: Money image
		Image dollar = new Image("Icon/dollar.png");
		img_money = new ImageView(dollar);
		img_money.setFitHeight(50);
		img_money.setFitWidth(50);
		img_money.setPreserveRatio(true);

		// Start Scene: Money save button
		btn_saveMoney = new Button("Save");
		btn_saveMoney.setStyle("-fx-text-fill: black;" + "-fx-font-size: 15;"
				+ "-fx-font-family: Cambria;" + "-fx-background-color: white;"
				+ "-fx-border-color: black;");
		btn_saveMoney.setOnAction(e->{
			try {
				money = Integer.parseInt(text_money.getText());
				text_money.setDisable(true);
				text_money.setText("$" + text_money.getText());
				btn_saveMoney.setDisable(true);
			}
			catch (NumberFormatException badInput) {
				text_money.setText("Invalid input.");
			}

			// Enable start button
			if (btn_saveMoney.isDisable() && btn_saveBet.isDisable()) {
				btn_start.setDisable(false);
			}
		});

		// Start Scene: Place bet label
		label_placeBet = new Label("Place your bet:");
		label_placeBet.setStyle("-fx-text-fill: white;" + "-fx-font-size: 20;"
				+ "-fx-font-family: Cambria");

		// Start Scene: Bet text field
		text_bet = new TextField("" );
		text_bet.setStyle("-fx-font-family: Cambria;" + "-fx-font-size: 20");

		// Start Scene: Token image
		Image token = new Image("Icon/token.png");
		img_token = new ImageView(token);
		img_token.setFitHeight(50);
		img_token.setFitWidth(50);
		img_token.setPreserveRatio(true);

		// Start Scene: Bet save button
		btn_saveBet = new Button("Save");
		btn_saveBet.setStyle("-fx-text-fill: black;" + "-fx-font-size: 15;"
				+ "-fx-font-family: Cambria;" + "-fx-background-color: white;"
				+ "-fx-border-color: black;");
		btn_saveBet.setOnAction(e->{
			try {
				bet = Integer.parseInt(text_bet.getText());
				text_bet.setDisable(true);
				text_bet.setText("$" + text_bet.getText());
				btn_saveBet.setDisable(true);
			}
			catch (NumberFormatException badInput) {
				text_bet.setText("Invalid input.");
			}

			// Enable start button
			if (btn_saveMoney.isDisable() && btn_saveBet.isDisable()) {
				btn_start.setDisable(false);
			}
		});

		// Start Scene: Start button
		btn_start = new Button("Let's start!");
		btn_start.setDisable(true);
		btn_start.setStyle("-fx-text-fill: white;" + "-fx-font-size: 20;"
				+ "-fx-font-family: Cambria;" + "-fx-background-color: darkGreen;"
				+ "-fx-border-color: white;");
		btn_start.setOnAction(e->{
			primaryStage.setScene((Scene)createGameplayScene());
		});



		primaryStage.setScene((Scene)createStartScene());
		primaryStage.show();
	}


	public Scene createStartScene() {

		HBox rowMoney = new HBox(20);
		rowMoney.setAlignment(Pos.CENTER);
		rowMoney.getChildren().addAll(img_money, text_money, btn_saveMoney);

		VBox paneMoney = new VBox(10);
		paneMoney.setAlignment(Pos.CENTER);
		paneMoney.getChildren().addAll(label_placeMoney, rowMoney);

		HBox rowBet = new HBox(20);
		rowBet.setAlignment(Pos.CENTER);
		rowBet.getChildren().addAll(img_token, text_bet, btn_saveBet);

		VBox paneBet = new VBox(10);
		paneBet.setAlignment(Pos.CENTER);
		paneBet.getChildren().addAll(label_placeBet, rowBet);

		VBox paneCenter = new VBox(30);
		paneCenter.getChildren().addAll(paneMoney, paneBet);

		VBox paneVertical = new VBox(100);
		paneVertical.setAlignment(Pos.CENTER);
		paneVertical.getChildren().addAll(img_title, paneCenter, btn_start);

		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setCenter(paneVertical);

		// Background image
		Image bgImage = new Image("Background/startBG.png");
		BackgroundImage backgroundImage = new BackgroundImage(bgImage, null, null, null, null);
		Background background = new Background(backgroundImage);
		pane.setBackground(background);

		return new Scene(pane, 540, 960);
	}

	public Scene createGameplayScene() {

		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));

		// Background image
		Image bgImage = new Image("Background/gameBG.jpg");
		BackgroundImage backgroundImage = new BackgroundImage(bgImage, null, null, null, null);
		Background background = new Background(backgroundImage);
		pane.setBackground(background);

		return new Scene(pane, 540, 960);
	}

}
