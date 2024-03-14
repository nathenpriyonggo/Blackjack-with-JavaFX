import Exceptions.BetExceedsFundsException;
import Exceptions.InsufficientFundsException;
import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

import java.util.Objects;

public class BlackjackJavaFX extends Application {

	Label label_welcome, label_placeBet, label_placeMoney,
			label_money, label_bet, label_dealerTotal, label_playerTotal,
			label_stand, label_hit, label_status, label_playAgain;
	TextField text_bet, text_money;
	ImageView img_token, img_money, img_title, img_stand, img_hit, img_backCard;
	Button btn_start, btn_saveBet, btn_saveMoney, btn_stand, btn_hit;
	int money, bet;
	BlackjackGame blackjackGame;
	HBox rowDealerCards, rowPlayerCards;
	PauseTransition pause = new PauseTransition(Duration.seconds(3.0));
	PauseTransition pauseRestart = new PauseTransition(Duration.seconds(3.0));


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

		// Gameplay Scene: Stand image
		Image stand = new Image("Icon/stand.png");
		img_stand = new ImageView(stand);
		img_stand.setFitWidth(100);
		img_stand.setFitHeight(60);
		img_stand.setPreserveRatio(true);

		// Gameplay Scene: Stand label
		label_stand = new Label("Stand");
		label_stand.setStyle("-fx-font-family: Cambria;"
				+ "-fx-font-size: 15;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;");
// ------------------------------------------------------------------------------------------>>
		// Gameplay Scene: Stand button
		btn_stand = new Button();
		btn_stand.setGraphic(img_stand);
		btn_stand.setOnAction(e->{

			// Disable buttons
			btn_hit.setDisable(true);
			btn_stand.setDisable(true);

			gameFinished(true);
			printWhoWon(false);
			if (money <= 0) {
				pauseRestart.play();
			}
			else {
				pause.play();
			}
		});
// ------------------------------------------------------------------------------------------>>
		// Gameplay Scene: Hit image
		Image hit = new Image("Icon/hit.png");
		img_hit = new ImageView(hit);
		img_hit.setFitWidth(100);
		img_hit.setFitHeight(60);
		img_hit.setPreserveRatio(true);


		// Gameplay Scene: Hit label
		label_hit = new Label("Hit");
		label_hit.setStyle("-fx-font-family: Cambria;"
				+ "-fx-font-size: 15;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;");
// ------------------------------------------------------------------------------------------>>
		// Gameplay Scene: Hit button
		btn_hit = new Button();
		btn_hit.setGraphic(img_hit);
		btn_hit.setOnAction(e->{

			// Hit card
			Card card = blackjackGame.hit();
			// Add hit card to user view
			rowPlayerCards.getChildren().add(returnCardImg(card));
			// Update player hand total label
			label_playerTotal.setText("Player hand: "
					+ String.valueOf(blackjackGame.playerHandTotal()));
			// Check if player busted, if yes end game
			if (checkPlayerStatusToEnd()) {
				if (money <= 0) {
					// Disable buttons
					btn_hit.setDisable(true);
					btn_stand.setDisable(true);

					pauseRestart.play();
				}
				else {
					// Disable buttons
					btn_hit.setDisable(true);
					btn_stand.setDisable(true);

					pause.play();
				}
			}
		});
// ------------------------------------------------------------------------------------------>>
		// Gameplay Scene: Back card image
		Image backCard = new Image("Card/card_back.png");
		img_backCard = new ImageView(backCard);
		img_backCard.setPreserveRatio(true);
		img_backCard.setFitWidth(120);
		img_backCard.setFitHeight(176);

		// Gameplay Scene: Game status label
		label_status = new Label("");
		label_status.setStyle("-fx-font-family: Cambria;"
				+ "-fx-font-size: 50;"
				+ "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;");

		// Gameplay Scene: Pause transition play again
		pause.setOnFinished(e->{
			primaryStage.setScene(createPlayAgainScene());
		});

		// Gameplay Scene: Pause Restart transition
		pauseRestart.setOnFinished(e->{
			primaryStage.setScene(createStartScene());
		});





		// Play again Scene: Play again label
		label_playAgain = new Label("Play Again?");
		label_playAgain.setStyle("-fx-font-family: Cambria;"
				+ "-fx-font-size: 60;"
				+ "-fx-text-fill: white;"
				+ "-fx-background-color: #40826D;"
				+ "-fx-font-weight: bold;");

		// Begin game
		primaryStage.setScene((Scene)createStartScene());
		primaryStage.setResizable(false);
		primaryStage.show();
	}


	// FIXME: Fully functional but not commented, modify if possible!
	public Scene createStartScene() {

		// Restart settings
		text_money.setText("");
		text_money.setDisable(false);
		text_bet.setText("");
		text_bet.setDisable(false);
		btn_saveMoney.setDisable(false);
		btn_saveBet.setDisable(false);
		btn_start.setDisable(true);


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
		BackgroundImage backgroundImage = new BackgroundImage(bgImage,
				null, null, null, null);
		Background background = new Background(backgroundImage);
		pane.setBackground(background);

		return new Scene(pane, 800, 960);
	}

	/*
	User-defined function that returns a formatted card based on
	the card argument which was passed
	 */
	ImageView returnCardImg(Card img) {
		String filename = "Card/" + img.suit + "/"
				+ img.suit + "_" + img.value + ".png";
		Image cardImage = new Image(filename);
		ImageView img_card = new ImageView(cardImage);
		img_card.setPreserveRatio(true);
		img_card.setFitWidth(120);
		img_card.setFitHeight(176);
		return img_card;
	}

	/*
	User-defined function that checks inside the hit button
	whether a player busted, if player hasn't busted, return false,
	otherwise return true
	 */
	boolean checkPlayerStatusToEnd() {

		// Check if player busted
		if (blackjackGame.playerBusted()) {
			gameFinished(false);
			printWhoWon(true);
			return true;
		}
		// Check if player got 21
		else if (blackjackGame.playerHandTotal() == 21) {
			gameFinished(false);
			printWhoWon(false);
			return true;
		}
		return false;
	}

	/*
	User-defined function that makes dealer draw since player's game
	or turn is finished
	 */
	void gameFinished(boolean dealerDraw) {

		// Remove all cards
		rowDealerCards.getChildren().remove(0);
		rowDealerCards.getChildren().remove(0);
		// Depends on parameter, hit if wanted, don't if not
		if (dealerDraw) {
			while (blackjackGame.evalBankerDraw()) {
				blackjackGame.dealerHit();
			}
		}
		for (Card card : blackjackGame.dealerHand()) {
			rowDealerCards.getChildren().add(returnCardImg(card));
		}
		// Update dealer hand total label
		label_dealerTotal.setText("Dealer hand: "
				+ String.valueOf(blackjackGame.dealerHandTotal()));

	}

	/*
	User-defined function that sets label_status to game status and
	update current budget
	 */
	void printWhoWon(boolean busted) {

		// Blackjack
		if (blackjackGame.isBlackjack(blackjackGame.playerHand)) {
			label_status.setText("Blackjack!");
			money += (2 * bet + bet / 2);
		}
		// Player wins
		else if (blackjackGame.whoWonWithArgs().equals("player")) {
			label_status.setText("You Won!");
			money += (2 * bet);
		}
		// Player busted
		else if (busted) {
			label_status.setText("Bust!");
		}
		// Player lost
		else if (blackjackGame.whoWonWithArgs().equals("dealer")) {
			label_status.setText("You Lost!");
		}
		// Draw
		else if (blackjackGame.whoWonWithArgs().equals("push")) {
			label_status.setText("Push!");
			money += bet;
		}
	}

	/*
	User-defined function to check if player or dealer gets blackjack
	 */
	void checkBlackjack() {
		if (blackjackGame.isBlackjack(blackjackGame.playerHand) ||
				blackjackGame.isBlackjack(blackjackGame.dealerHand())) {

			// Disable buttons
			btn_hit.setDisable(true);
			btn_stand.setDisable(true);

			gameFinished(false);
			printWhoWon(false);

			if (money <= 0) {
				pauseRestart.play();
			}
			else {
				pause.play();
			}
		}
	}

	// FIXME: Gameplay Scene in working progress...
	public Scene createGameplayScene() {

		// Start game
		blackjackGame = new BlackjackGame();
		blackjackGame.startGame();
		rowDealerCards = new HBox(5);
		rowDealerCards.setAlignment(Pos.CENTER);
		rowPlayerCards = new HBox(5);
		rowPlayerCards.setAlignment(Pos.CENTER);

		// Show only one of dealer hand
		Card dealerFirstCard = blackjackGame.dealerHand().get(0);
		rowDealerCards.getChildren().add(returnCardImg(dealerFirstCard));
		// Add hidden card
		rowDealerCards.getChildren().add(img_backCard);

		// Show player hand
		for (Card card : blackjackGame.playerHand()) {
			rowPlayerCards.getChildren().add(returnCardImg(card));
		}

		// Display settings
		btn_hit.setDisable(false);
		btn_stand.setDisable(false);
		label_status.setText("");
		label_money.setText("$" + String.valueOf(money));
		label_bet.setText("$" + String.valueOf(bet));
		blackjackGame.setBet(bet);
		label_dealerTotal.setText("Dealer hand: "
				+ String.valueOf(blackjackGame.dealerFirstCardTotal()));
		label_playerTotal.setText("Player hand: "
				+ String.valueOf(blackjackGame.playerHandTotal()));

		// Check if player or either dealer gets blackjack
		checkBlackjack();

		HBox rowFunds = new HBox(10, img_money, label_money);
		rowFunds.setAlignment(Pos.CENTER_LEFT);
		HBox rowCurrentBet = new HBox(10, img_token, label_bet);
		rowCurrentBet.setAlignment(Pos.CENTER);

		VBox colStand = new VBox(10, btn_stand, label_stand);
		colStand.setAlignment(Pos.CENTER);
		VBox colHit = new VBox(10, btn_hit, label_hit);
		colHit.setAlignment(Pos.CENTER);

		HBox rowStandHit = new HBox(70, colStand, colHit);
		rowStandHit.setAlignment(Pos.CENTER);

		VBox paneVertical = new VBox(25, rowFunds, label_dealerTotal, rowDealerCards,
				label_status, rowPlayerCards, label_playerTotal, rowCurrentBet, rowStandHit);
		paneVertical.setAlignment(Pos.CENTER);

		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setCenter(paneVertical);

		// Background image
		Image bgImage = new Image("Background/gameBG.png");
		BackgroundImage backgroundImage = new BackgroundImage(bgImage,
				null, null, null, null);
		Background background = new Background(backgroundImage);
		pane.setBackground(background);

		return new Scene(pane, 800, 960);
	}

	public Scene createPlayAgainScene() {

		text_bet.setDisable(false);
		text_bet.setText("");
		btn_saveBet.setDisable(false);
		btn_start.setDisable(true);
		label_money.setText("$" + String.valueOf(money));

		HBox rowFunds = new HBox(10, img_money, label_money);
		rowFunds.setAlignment(Pos.CENTER_LEFT);

		HBox rowStartBet = new HBox(20);
		rowStartBet.setAlignment(Pos.CENTER);
		rowStartBet.getChildren().addAll(img_token, text_bet, btn_saveBet);

		VBox paneBet = new VBox(10);
		paneBet.setAlignment(Pos.CENTER);
		paneBet.getChildren().addAll(label_placeBet, rowStartBet);

		VBox paneVertical = new VBox(100);
		paneVertical.setAlignment(Pos.CENTER);
		paneVertical.getChildren().addAll(rowFunds, label_playAgain, paneBet, btn_start);

		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));

		// Background image
		Image bgImage = new Image("Background/startBG.png");
		BackgroundImage backgroundImage = new BackgroundImage(bgImage,
				null, null, null, null);
		Background background = new Background(backgroundImage);
		pane.setBackground(background);
		pane.setCenter(paneVertical);

		return new Scene(pane, 800, 960);
	}
}
