package com.example.demoimage;


import com.example.demoimage.entities.Bomber;
import com.example.demoimage.entities.Entity;
import com.example.demoimage.entities.Grass;
import com.example.demoimage.entities.Wall;
import com.example.demoimage.graphics.Sprite;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameViewManager {
  private AnchorPane gamePane;
  private Scene gameScene;
  private Stage gameStage;
  private static final int GAME_WIDTH = 1100;
  private static final int GAME_HEIGHT = 700;

  public static final int WIDTH = 30;
  public static final int HEIGHT = 20;
  private GraphicsContext gc;
  private Canvas canvas;
  private List<Entity> entities = new ArrayList<>();
  private List<Entity> stillObjects = new ArrayList<>();
  private Stage menuStage;

  public GameViewManager() {
    initializeStage();
    createKeyListener();
  }

  private void createKeyListener() {
    gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {

        } else if (event.getCode() == KeyCode.RIGHT) {

        }
      }
    });

    gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {

        } else if (event.getCode() == KeyCode.RIGHT) {

        }
      }
    });
  }

  private void initializeStage() {
//    gamePane = new AnchorPane();
//    gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
//    gameStage = new Stage();
//    gameStage.setScene(gameScene);

    canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT); //tao 1 canvas man hinh trong voi game width va height
    gc = canvas.getGraphicsContext2D();

    Group root = new Group();
    root.getChildren().add(canvas);

    gameScene = new Scene(root);
    gameStage = new Stage();
    gameStage.setScene(gameScene);
  }

  public void createNewGame(Stage menuStage) {
    this.menuStage = menuStage;
    this.menuStage.hide();
    createTimer();
    gameStage.show();
  }

  private void createTimer() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long l) {
        render();
        update();
      }
    };
    timer.start();
    createMap();

    Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
    entities.add(bomberman);
  }

  public void createMap() {
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        Entity object;
        if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
          object = new Wall(i, j, Sprite.wall.getFxImage());
        }
        else {
          object = new Grass(i, j, Sprite.grass.getFxImage());
        }
        stillObjects.add(object);
      }
    }
  }

  public void update() {
    entities.forEach(Entity::update);
  }

  public void render() {
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    stillObjects.forEach(g -> g.render(gc));
    entities.forEach(g -> g.render(gc));
  }
}
