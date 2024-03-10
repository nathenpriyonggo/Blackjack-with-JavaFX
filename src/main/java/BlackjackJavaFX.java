import Exceptions.BetExceedsFundsException;
import Exceptions.InsufficientFundsException;
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

	Label label_welcome, label_placeBet, label_placeMoney,
			label_money, label_bet, label_dealerTotal, label_playerTotal;
	TextField text_bet, text_money;
	ImageView img_token, img_money, img_title, img_stand, img_hit;
	Button btn_start, btn_saveBet, btn_saveMoney, btn_stand, btn_hit;
	int money, bet;


	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Blackjack in JavaFX");

		// Start Scene: Welcome Label
		label_welcome = new Label("Welcome to Blackjack!");
		label_welcome.setStyle("-fx-text-fill: white;"
				+ "-fx-font-size: 40;"
				+ "-fx-font-family: Cambria");

		// Start Scene: title image
		Image title = new Image("Icon/title.png");
		img_title = new ImageView(title);
		img_title.setFitWidth(400);
		img_title.setPreserveRatio(true);

		// Start Scene: Place money label
		label_placeMoney = new Label("Place your starting money:");
		label_placeMoney.setStyle("-fx-text-fill: white;"
				+ "-fx-font-size: 20;"
				+ "-fx-font-family: Cambria");

		// Start Scene: Money text field
		text_money = new TextField("" );
		text_money.setStyle("-fx-font-family: Cambria;"
				+ "-fx-font-size: 20");

		// Start Scene: Money image
		Image dollar = new Image("Icon/dollar.png");
		img_money = new ImageView(dollar);
		img_money.setFitHeight(50);
		img_money.setFitWidth(50);
		img_money.setPreserveRatio(true);

		// Start Scene: Money save button
		btn_saveMoney = new Button("Save");
		btn_saveMoney.setStyle("-fx-text-fill: black;"
				+ "-fx-font-size: 15;"
				+ "-fx-font-family: Cambria;"
				+ "-fx-background-color: white;"
				+ "-fx-border-color: black");
		btn_saveMoney.setOnAction(e->{
			try {
				money = Integer.parseInt(text_money.getText());
				text_money.setDisable(true);
				text_money.setText("$" + text_money.getText());
				btn_saveMoney.setDisable(true);
			}
			// If bet more not an integer, return error
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
		label_placeBet.setStyle("-fx-text-fill: white;"
				+ "-fx-font-size: 20;"
				+ "-fx-font-family: Cambria");

		// Start Scene: Bet text field
		text_bet = new TextField("" );
		text_bet.setStyle("-fx-font-family: Cambria;"
				+ "-fx-font-size: 20");

		// Start Scene: Token image
		Image token = new Image("Icon/token.png");
		img_token = new ImageView(token);
		img_token.setFitHeight(50);
		img_token.setFitWidth(50);
		img_token.setPreserveRatio(true);

		// Start Scene: Bet save button
		btn_saveBet = new Button("Save");
		btn_saveBet.setStyle("-fx-text-fill: black;"
				+ "-fx-font-size: 15;"
				+ "-fx-font-family: Cambria;"
				+ "-fx-background-color: white;"
				+ "-fx-border-color: black");
		btn_saveBet.setOnAction(e->{
			try {
				bet = Integer.parseInt(text_bet.getText());
				if (money == 0) {
					throw new InsufficientFundsException();
				}
				else if (bet > money) {
					throw new BetExceedsFundsException();
				}
				text_bet.setDisable(true);
				text_bet.setText("$" + text_bet.getText());
				btn_saveBet.setDisable(true);
				money -= bet;
			}
			// If bet more not an integer, return error
			catch (NumberFormatException badInput) {
				text_bet.setText("Invalid input.");
			}
			catch (InsufficientFundsException insufficientFundInput) {
                text_bet.setText("No funds available.");
            }
			catch (BetExceedsFundsException betExceedsFundsInput) {
				text_bet.setText("Bet exceeds funds.");
			}

            // Enable start button
			if (btn_saveMoney.isDisable() && btn_saveBet.isDisable()) {
				btn_start.setDisable(false);
			}
		});

		// Start Scene: Start button
		btn_start = new Button("Let's start!");
		btn_start.setDisable(true);
		btn_start.setStyle("-fx-text-fill: white;"
				+ "-fx-font-size: 20;"
				+ "-fx-font-family: Cambria;"
				+ "-fx-background-color: #2E8B57;"
				+ "-fx-border-color: white");
		btn_start.setOnAction(e->{
			primaryStage.setScene((Scene)createGameplayScene());
		});




		// Gameplay Scene: Money label
		label_money = new Label();
		label_money.setStyle("-fx-font-family: Cambria;"
				+ "-fx-font-size: 20;"
				+ "-fx-text-fill: white");

		// Gameplay Scene: Bet label
		label_bet = new Label();
		label_bet.setStyle("-fx-font-family: Cambria;"
				+ "-fx-font-size: 30;"
				+ "-fx-font-weight: bold;"
				+ "-fx-text-fill: white");

		// Gameplay Scene: Dealer and player total label
		label_dealerTotal = new Label();
		label_dealerTotal.setStyle("-fx-font-family: Cambria;"
				+ "-fx-font-size: 15;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-style: italic;"
				+ "-fx-background-color: #40826D");
		label_playerTotal = new Label();
		label_playerTotal.setStyle("-fx-font-family: Cambria;"
				+ "-fx-font-size: 15;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-style: italic;"
				+ "-fx-background-color: #40826D");

		/*
		TODO ------------------------------------------------------------------------->
		 After finish (img_stand) graphic design, fix code
		 */
		// Gameplay Scene: Stand image
		Image stand = new Image("Icon/title.png"); // FIXME
		img_stand = new ImageView(stand);
		img_stand.setFitHeight(50);
		img_stand.setFitWidth(75);
		img_stand.setPreserveRatio(true);

		/*
		TODO ------------------------------------------------------------------------->
		 After finish (img_hit) graphic design, fix code
		 */
		// Gameplay Scene: Hit image
		Image hit = new Image("Icon/title.png"); // FIXME
		img_hit = new ImageView(hit);
		img_hit.setFitHeight(50);
		img_hit.setFitWidth(75);
		img_hit.setPreserveRatio(true);

		/*
		TODO ------------------------------------------------------------------------->
		 Update (btn_stand) after fixing (img_stand)
		 */
		// Gameplay Scene: Stand button
		btn_stand = new Button();
		btn_stand.setGraphic(img_stand);

		/*
		TODO ------------------------------------------------------------------------->
		 Update (btn_hit) after fixing (img_hit)
		 */
		// Gameplay Scene: Hit button
		btn_hit = new Button();
		btn_hit.setGraphic(img_hit);




		// Begin game
		primaryStage.setScene((Scene)createStartScene());
		primaryStage.setResizable(false);
		primaryStage.show();
	}


	// FIXME: Fully functional but not commented, modify if possible!
	public Scene createStartScene() {

		HBox rowStartMoney = new HBox(20);
		rowStartMoney.setAlignment(Pos.CENTER);
		rowStartMoney.getChildren().addAll(img_money, text_money, btn_saveMoney);

		VBox paneMoney = new VBox(10);
		paneMoney.setAlignment(Pos.CENTER);
		paneMoney.getChildren().addAll(label_placeMoney, rowStartMoney);

		HBox rowStartBet = new HBox(20);
		rowStartBet.setAlignment(Pos.CENTER);
		rowStartBet.getChildren().addAll(img_token, text_bet, btn_saveBet);

		VBox paneBet = new VBox(10);
		paneBet.setAlignment(Pos.CENTER);
		paneBet.getChildren().addAll(label_placeBet, rowStartBet);

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

	// FIXME: Gameplay Scene in working progress...
	public Scene createGameplayScene() {

		/*
		TODO ------------------------------------------------------------------------->
		 Start (blackjackGame) algorithm to start gameplay
		 */
		BlackjackGame blackjackGame = new BlackjackGame();


		label_money.setText("$" + String.valueOf(money));
		label_bet.setText("$" + String.valueOf(bet));
		label_dealerTotal.setText("Dealer hand: " + String.valueOf(30));
		label_playerTotal.setText("Player hand: " + String.valueOf(31));

		HBox rowFunds = new HBox(10, img_money, label_money);
		rowFunds.setAlignment(Pos.CENTER_LEFT);
		HBox rowCurrentBet = new HBox(10, img_token, label_bet);
		rowCurrentBet.setAlignment(Pos.CENTER);

		/*
		TODO ------------------------------------------------------------------------->
		 Call (btn_stand) and (btn_hit) and set respective HBox
		 */


		VBox paneVertical = new VBox(30, rowFunds, label_dealerTotal,
				label_playerTotal, rowCurrentBet);
		paneVertical.setAlignment(Pos.CENTER);

		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setCenter(paneVertical);

		// Background image
		Image bgImage = new Image("Background/gameBG.jpg");
		BackgroundImage backgroundImage = new BackgroundImage(bgImage, null, null, null, null);
		Background background = new Background(backgroundImage);
		pane.setBackground(background);

		return new Scene(pane, 540, 960);
	}

}
