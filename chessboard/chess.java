package chessboard;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class chess extends Application {
    Pane mainPane = new Pane();
    ImagePattern borderImagePattern = new ImagePattern(new Image("chessboard/images/border.jpg"));
    public customPane pane = new customPane(1, 1, borderImagePattern);

    GridPane gridPane = new GridPane();
    customRectangle[][] rectangles = new customRectangle[8][8];
    ObjectProperty<Integer> turn = new SimpleObjectProperty<>(0);
    
    final int blackRook = -1, blackKnight = -2, blackBishop = -3, blackKing = -4, blackQueen = -5, blackPawn = -6, 
              whiteRook =  1, whiteKnight =  2, whiteBishop =  3, whiteKing =  4, whiteQueen =  5, whitePawn =  6;

    final int blank = 0, white = 2, black = 1;
    
    //region //!IMAGEVIEW ANDS SPAWNS
    customImageView blackPawnIV1 = new customImageView(new Image("chessboard/images/BlackPawn.png"), 1, 0, blackPawn);
    customImageView blackPawnIV2 = new customImageView(new Image("chessboard/images/BlackPawn.png"), 1, 1, blackPawn);
    customImageView blackPawnIV3 = new customImageView(new Image("chessboard/images/BlackPawn.png"), 1, 2, blackPawn);
    customImageView blackPawnIV4 = new customImageView(new Image("chessboard/images/BlackPawn.png"), 1, 3, blackPawn);
    customImageView blackPawnIV5 = new customImageView(new Image("chessboard/images/BlackPawn.png"), 1, 4, blackPawn); 
    customImageView blackPawnIV6 = new customImageView(new Image("chessboard/images/BlackPawn.png"), 1, 5, blackPawn);
    customImageView blackPawnIV7 = new customImageView(new Image("chessboard/images/BlackPawn.png"), 1, 6, blackPawn);
    customImageView blackPawnIV8 = new customImageView(new Image("chessboard/images/BlackPawn.png"), 1, 7, blackPawn);
    
    customImageView blackRook1IV = new customImageView(new Image("chessboard/images/BlackRook.png"), 0, 0, blackRook);
    customImageView blackRook2IV = new customImageView(new Image("chessboard/images/BlackRook.png"), 0, 7, blackRook);
    
    customImageView blackKnight1IV = new customImageView(new Image("chessboard/images/BlackKnight.png"), 0, 1, blackKnight);
    customImageView blackKnight2IV = new customImageView(new Image("chessboard/images/BlackKnight.png"), 0, 6, blackKnight);

    customImageView blackBishop1IV = new customImageView(new Image("chessboard/images/BlackBishop.png"), 0, 2, blackBishop);
    customImageView blackBishop2IV = new customImageView(new Image("chessboard/images/BlackBishop.png"), 0, 5, blackBishop);

    customImageView blackKingIV = new customImageView(new Image("chessboard/images/BlackKing.png"), 0, 3, blackKing);
    customImageView blackQueenIV = new customImageView(new Image("chessboard/images/BlackQueen.png"), 0, 4, blackQueen);
    
    
    customImageView whitePawnIV1 = new customImageView(new Image("chessboard/images/WhitePawn.png"), 6, 0, whitePawn);
    customImageView whitePawnIV2 = new customImageView(new Image("chessboard/images/WhitePawn.png"), 6, 1, whitePawn);
    customImageView whitePawnIV3 = new customImageView(new Image("chessboard/images/WhitePawn.png"), 6, 2, whitePawn);
    customImageView whitePawnIV4 = new customImageView(new Image("chessboard/images/WhitePawn.png"), 6, 3, whitePawn);
    customImageView whitePawnIV5 = new customImageView(new Image("chessboard/images/WhitePawn.png"), 6, 4, whitePawn);
    customImageView whitePawnIV6 = new customImageView(new Image("chessboard/images/WhitePawn.png"), 6, 5, whitePawn);
    customImageView whitePawnIV7 = new customImageView(new Image("chessboard/images/WhitePawn.png"), 6, 6, whitePawn);
    customImageView whitePawnIV8 = new customImageView(new Image("chessboard/images/WhitePawn.png"), 6, 7, whitePawn);

    customImageView whiteRook1IV = new customImageView(new Image("chessboard/images/WhiteRook.png"), 7, 0, whiteRook);
    customImageView whiteRook2IV = new customImageView(new Image("chessboard/images/WhiteRook.png"), 7, 7, whiteRook);
    
    customImageView whiteKnight1IV = new customImageView(new Image("chessboard/images/WhiteKnight.png"), 7, 1, whiteKnight);
    customImageView whiteKnight2IV = new customImageView(new Image("chessboard/images/WhiteKnight.png"), 7, 6, whiteKnight);

    customImageView whiteBishop1IV = new customImageView(new Image("chessboard/images/WhiteBishop.png"), 7, 2, whiteBishop);
    customImageView whiteBishop2IV = new customImageView(new Image("chessboard/images/WhiteBishop.png"), 7, 5, whiteBishop);

    customImageView whiteKingIV = new customImageView(new Image("chessboard/images/WhiteKing.png"), 7, 3, whiteKing);
    customImageView whiteQueenIV = new customImageView(new Image("chessboard/images/WhiteQueen.png"), 7, 4, whiteQueen);

    ImagePattern whiteBlock = new ImagePattern(new Image("chessboard/images/white.jpg"));
    ImagePattern blackBlock = new ImagePattern(new Image("chessboard/images/black.jpg"));

    ImagePattern selected = new ImagePattern(new Image("chessboard/images/Selected.jpg"));
    ImagePattern available = new ImagePattern(new Image("chessboard/images/Available.jpg"));
    ImagePattern enemy = new ImagePattern(new Image("chessboard/images/Enemy.jpg"));
    //endregion

    GridPane whiteOut = new GridPane();
    GridPane blackOut = new GridPane();

    @Override
    public void start(Stage primaryStage) throws Exception {
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                rectangles[i][j] = new customRectangle(i, j, (i + j) % 2 == 0 ? whiteBlock: blackBlock);
                rectangles[i][j].setStroke(Color.DARKRED);
                rectangles[i][j].setStrokeWidth(0.5);
                pane.getChildren().add(rectangles[i][j]);

                if (j == 0 || j == 1)
                    rectangles[i][j].setIsOccupied(1);
                if (j == 6 || j == 7)
                    rectangles[i][j].setIsOccupied(2);
        }}
        
        //region //! CREATING PANE
        StackPane paneWhite = new StackPane(),
                  paneBlack = new StackPane();

        customPane paneUp = new customPane(1, 32, borderImagePattern),
                   paneDown = new customPane(1, 32, borderImagePattern),
                   paneLeft = new customPane(32, 1, borderImagePattern),
                   paneRight = new customPane(32, 1, borderImagePattern);
                   
        customPane paneK = new customPane(4, 32, borderImagePattern),
                   paneL = new customPane(4, 32, borderImagePattern),
                   paneM = new customPane(4, 32, borderImagePattern),
                   paneN = new customPane(4, 32, borderImagePattern);

        customPane paneA = new customPane(32, 32, borderImagePattern),
                   paneB = new customPane(32, 32, borderImagePattern),
                   paneC = new customPane(32, 32, borderImagePattern),
                   paneD = new customPane(32, 32, borderImagePattern);
             
        customPane paneE = new customPane(32, 32, borderImagePattern),
                   paneF = new customPane(32, 32, borderImagePattern),
                   paneG = new customPane(32, 32, borderImagePattern),
                   paneH = new customPane(32, 32, borderImagePattern);
        
        customPane paneI = new customPane(32, 1, borderImagePattern),
                   paneJ = new customPane(32, 1, borderImagePattern);
        //endregion

        //region //! ADD GRIDPANE
        gridPane.add(paneA,     0, 0 );
        gridPane.add(paneI,     0, 1 );
        gridPane.add(paneC,     0, 2 );
        gridPane.add(paneK,     1, 0 );
        gridPane.add(paneWhite, 1, 1 );
        gridPane.add(paneM,     1, 2 );
        gridPane.add(paneE,     2, 0 );
        gridPane.add(paneLeft,  2, 1 );
        gridPane.add(paneG,     2, 2 );
        gridPane.add(paneUp,    3, 0 );
        gridPane.add(pane,      3, 1 );
        gridPane.add(paneDown,  3, 2 );
        gridPane.add(paneF,     4, 0 );
        gridPane.add(paneRight, 4, 1 );
        gridPane.add(paneH,     4, 2 );
        gridPane.add(paneL,     5, 0 );
        gridPane.add(paneBlack, 5, 1 );
        gridPane.add(paneN,     5, 2 );
        gridPane.add(paneB,     6, 0 );
        gridPane.add(paneJ,     6, 1 );
        gridPane.add(paneD,     6, 2 );
        //endregion
        

        ImagePattern whiteBlock = new ImagePattern(new Image("chessboard/images/whitePaneBlock.jpg"));
        GridPane whiteGridPane = new GridPane();
        
        ImagePattern blackBlock = new ImagePattern(new Image("chessboard/images/whitePaneBlock.jpg"));
        GridPane blackGridPane = new GridPane();

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 2; j++){
                whiteGridPane.add((new customPane(8, 8, whiteBlock)), j, i);
            }
        }
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 2; j++){
                blackGridPane.add((new customPane(8, 8, blackBlock)), j, i);
            }
        }

        paneWhite.getChildren().add(whiteGridPane);
        paneWhite.getChildren().add(whiteOut);
        
        paneBlack.getChildren().add(blackGridPane);
        paneBlack.getChildren().add(blackOut);

        gridPane.setHgap(0);
        gridPane.setVgap(0);
        gridPane.setPadding(new Insets(0, 0, 0, 0));

        mainPane.getChildren().addAll(gridPane);
        gridPane.prefWidthProperty().bind(mainPane.widthProperty());
        gridPane.prefHeightProperty().bind(mainPane.heightProperty());
        
        Scene scene = new Scene(mainPane, 1050, 675);

        //region //!ADD ALL PANES
        pane.getChildren().addAll(blackPawnIV1, blackPawnIV2, blackPawnIV3, blackPawnIV4, blackPawnIV5,     
                                  blackPawnIV6, blackPawnIV7, blackPawnIV8,  blackRook1IV, blackRook2IV, blackBishop1IV, blackBishop2IV, blackKnight1IV, blackKnight2IV, blackKingIV, blackQueenIV, whitePawnIV1, whitePawnIV2, whitePawnIV3, whitePawnIV4, whitePawnIV5, whitePawnIV6, whitePawnIV7, whitePawnIV8, whiteRook1IV, whiteRook2IV, whiteBishop1IV, whiteBishop2IV, whiteKnight1IV,
                                  whiteKnight2IV, whiteKingIV, whiteQueenIV);
        //endregion
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Title");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public class customPane extends Pane{
        customPane(){}


        customPane(double width, double height, ImagePattern imagePattern){
            width = 13.0/8.0 * width;
            height = 17.0/16.0 * height;
            this.setMinWidth(1050 / width);
            this.setMinHeight(675 / height);
            Rectangle paneRectangle = new Rectangle();
            paneRectangle.setWidth(1050 / width);
            paneRectangle.setHeight(685 / height);
            paneRectangle.setFill(imagePattern);
            paneRectangle.setStroke(imagePattern);
            paneRectangle.setStrokeWidth(1);
            
            this.getChildren().add(paneRectangle);
        }
    }

    public class customRectangle extends Rectangle{
        int isOccupied = 0;

        customRectangle (int x, int y, Paint fill){
            this.xProperty().bind(pane.widthProperty().multiply(x).divide(8));
            this.yProperty().bind(pane.heightProperty().multiply(y).divide(8));
            this.widthProperty().bind(pane.widthProperty().divide(8));
            this.heightProperty().bind(pane.heightProperty().divide(8));
            this.setFill(fill);
        }

        public int getIsOccupied(){
            return isOccupied;
        }
        
        public void setIsOccupied(int code){
            isOccupied = code;
        }
    }   

    public class customImageView extends ImageView {
        ObjectProperty<Point2D> oldCoordinates = new SimpleObjectProperty<>();
        ObjectProperty<Double> xCoordinate = new SimpleObjectProperty<>();
        ObjectProperty<Double> yCoordinate = new SimpleObjectProperty<>();
        ObjectProperty<Point2D> mouseLocation = new SimpleObjectProperty<>();

        public ObjectProperty<Point2D> getOldCoordinates(){
            return oldCoordinates;
        }

        boolean isDeleted = false;
        public void setDeleted(){
            isDeleted = true;
        }
        public boolean getDeleted(){
            return isDeleted;
        }

        customImageView(Image image, double y, double x, int code){
            if (isBlack(code)){
                this.setDisable(true);
            }

            oldCoordinates.set(new Point2D(x, y));
            xCoordinate.set(x);
            yCoordinate.set(y);

            setImage(image);
            setX(oldCoordinates.getValue().getX());
            setY(oldCoordinates.getValue().getY());
            
            this.fitWidthProperty().bind(pane.widthProperty().divide(8));
            this.fitHeightProperty().bind(pane.heightProperty().divide(8));

            pane.widthProperty().addListener( e -> {
                setX(xCoordinate.getValue() * pane.getWidth() / 8);
            });
            
            pane.heightProperty().addListener( e -> {
                setY(yCoordinate.getValue() * pane.getHeight() / 8);
            });

            setOnMousePressed(e -> {
                mouseLocation.set(new Point2D(e.getX() - getX(), e.getY() - getY()));
                int oldX = (int) oldCoordinates.getValue().getX(),
                    oldY = (int) oldCoordinates.getValue().getY();
                
                rectangles[oldX][oldY].setFill(selected);

                switch (code) {
                    case whitePawn :
                        if (oldY > 0 && rectangles[oldX][oldY-1].getIsOccupied() == blank){
                            rectangles[oldX][oldY-1].setFill(available);
                        } if (oldY == 6 && rectangles[oldX][oldY-2].getIsOccupied() == blank){
                            rectangles[oldX][oldY-2].setFill(available);
                        } if (oldX < 7 && oldY > 0 && rectangles[oldX+1][oldY-1].getIsOccupied() == black){
                            rectangles[oldX+1][oldY-1].setFill(enemy);
                        } if (oldX > 0 && oldY > 0 && rectangles[oldX-1][oldY-1].getIsOccupied() == black){
                            rectangles[oldX-1][oldY-1].setFill(enemy);
                        }
                        break;
                        
                    case blackPawn :
                        if (oldY < 7 && rectangles[oldX][oldY+1].getIsOccupied() == blank){
                            rectangles[oldX][oldY+1].setFill(available);
                        } if (oldY < 7 && oldX < 7 && rectangles[oldX+1][oldY+1].getIsOccupied() == white){
                            rectangles[oldX+1][oldY+1].setFill(enemy);
                        } if (oldY < 7 && oldX > 0 && rectangles[oldX-1][oldY+1].getIsOccupied() == white){
                            rectangles[oldX-1][oldY+1].setFill(enemy);
                        } if (oldY == 1 && rectangles[oldX][oldY+2].getIsOccupied() == blank){
                            rectangles[oldX][oldY+2].setFill(available);
                        }
                        break;

                    case whiteRook : case blackRook :
                        int[] arrayRook = new int[]{-1, 0, 0, 1};
                        for (int i = 0; i < 4; i++){
                            for (int j = 0; j < 4; j++){
                                for (int m = oldX + arrayRook[i], n = oldY + arrayRook[j]; 
                                        m < 8 && m >= 0 && n < 8 && n >= 0 && arrayRook[i] != arrayRook[j] && arrayRook[i] != -arrayRook[j];
                                            m += arrayRook[i], n += arrayRook[j]){

                                    if (rectangles[m][n].getIsOccupied() == blank){
                                        rectangles[m][n].setFill(available);
                                    } else if (isNotEnemy(code, rectangles[m][n].getIsOccupied())){
                                        break;
                                    } else {
                                        rectangles[m][n].setFill(enemy);
                                        break;
                                    }
                        }}}
                        break;
                    
                    case whiteKnight : case blackKnight :
                        final int[] array = new int[]{-2, -1, 1, 2};
                        for (int i = 0; i < 4; i++){
                            for (int j = 0, m = oldX + array[i]; j < 4 && m >= 0 && m < 8; j++){
                                int n = oldY + array[j];
                                if (n < 0 || n >= 8 || array[i] == array[j] || array[i] == -array[j]){
                                    continue;
                                }
                                if (rectangles[m][n].getIsOccupied() == blank){
                                    rectangles[m][n].setFill(available);
                                } else if (isEnemy(code, rectangles[m][n].getIsOccupied())){
                                    rectangles[m][n].setFill(enemy);
                                }
                            }
                        }
                        break;
                        
                    case whiteBishop : case blackBishop :
                        for (int i = -1; i <= 1; i += 2){
                            for (int j = -1; j <= 1; j += 2){
                                for (int m = oldX + i, n = oldY + j; m < 8 && m >= 0 && n >= 0 && n < 8; m += i, n += j){
                                    if (rectangles[m][n].getIsOccupied() == blank){
                                        rectangles[m][n].setFill(available);
                                    } else if (isEnemy(code, rectangles[m][n].getIsOccupied())){
                                        rectangles[m][n].setFill(enemy);
                                        break;
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                    break;

                    case whiteQueen : case blackQueen :
                        
                    int[] arrayQueenR = new int[]{-1, 0, 0, 1};
                    for (int i = 0; i < 4; i++){
                        for (int j = 0; j < 4; j++){
                            for (int m = oldX + arrayQueenR[i], n = oldY + arrayQueenR[j];
                                    m < 8 && m >= 0 && n < 8 && n >= 0 && arrayQueenR[i] != arrayQueenR[j] && arrayQueenR[i] != -arrayQueenR[j];
                                        m += arrayQueenR[i], n += arrayQueenR[j]){
                                if (rectangles[m][n].getIsOccupied() == blank){
                                    rectangles[m][n].setFill(available);
                                } else if (isNotEnemy(code, rectangles[m][n].getIsOccupied())){
                                    break;
                                } else {
                                    rectangles[m][n].setFill(enemy);
                                    break;
                    }}}}
                    
                    for (int i = -1; i <= 1; i += 2){
                        for (int j = -1; j <= 1; j += 2){
                            for (int m = oldX + i, n = oldY + j; m < 8 && m >= 0 && n >= 0 && n < 8; m += i, n += j){
                                if (rectangles[m][n].getIsOccupied() == blank){
                                    rectangles[m][n].setFill(available);
                                } else if (isEnemy(code, rectangles[m][n].getIsOccupied())){
                                    rectangles[m][n].setFill(enemy);
                                    break;
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                    break;
                
                    case whiteKing : case blackKing :
                        for (int i = oldX > 0 ? oldX - 1 : 0; i <= oldX + 1 && i < 8; i++){
                            for (int j = oldY > 0 ? oldY - 1 : 0; j <= oldY + 1 && j < 8; j++){
                                if (rectangles[i][j].getIsOccupied() == blank){
                                    rectangles[i][j].setFill(available);
                                } else if (isEnemy(code, rectangles[i][j].getIsOccupied())){
                                    rectangles[i][j].setFill(enemy);
                                }
                            }
                        }
                }
            });
            
            this.setOnMouseDragged(e -> {
                double deltaX = mouseLocation.get().getX() ;
                double deltaY = mouseLocation.get().getY() ;

                setX(e.getX() - deltaX);
                setY(e.getY() - deltaY);
            });

            this.setOnMouseReleased(e -> {
                ObjectProperty<Integer> proceed = new SimpleObjectProperty<>(0);
                int oldX = (int) oldCoordinates.getValue().getX(),
                    oldY = (int) oldCoordinates.getValue().getY(),
                    newX = (int) (e.getX() / (pane.getWidth() / 8)),
                    newY = (int) (e.getY() / (pane.getHeight() / 8));

                if (isInPane(e.getX(), e.getY())){
                    switch (code) {
                        case whitePawn : case blackPawn:
                            if (soldierPath(oldX, oldY, newX, newY, code))
                                proceed.set(1);
                            break;
                    
                        case whiteRook : case blackRook:
                            if (rookPath(oldX, oldY, newX, newY, code)){
                                deleteEnemy(newX, newY);
                                proceed.set(1);
                            }
                            break;

                        case whiteKnight : case blackKnight :
                            if (knightPath(oldX, oldY, newX, newY, code)){
                                deleteEnemy(newX, newY);
                                proceed.set(1);
                            }
                            break;

                        case whiteBishop  : case blackBishop : 
                            if (bishopPath(oldX, oldY, newX, newY, code)){
                                deleteEnemy(newX, newY);
                                proceed.set(1);
                            }
                            break;

                        case whiteKing : case blackKing :
                            if (kingPath(oldX, oldY, newX, newY, code)){
                                deleteEnemy(newX, newY);
                                proceed.set(1);
                            }
                                break;

                        case whiteQueen : case blackQueen :
                            if (queenPath(oldX, oldY, newX, newY, code)){
                                deleteEnemy(newX, newY);
                                proceed.set(1);
                            }
                            break;

                        default:
                            proceed.set(1);;
                    }
                
                } else{
                    setX(oldCoordinates.getValue().getX() * pane.getWidth() / 8);
                    setY(oldCoordinates.getValue().getY() * pane.getHeight() / 8);
                }

                if (proceed.getValue() == 1){
                    setX(e.getX() - (e.getX() % (pane.getWidth() / 8)));
                    setY(e.getY() - (e.getY() % (pane.getHeight() / 8)));
                    xCoordinate.set((double) (int)(e.getX() / (pane.getWidth() / 8)));
                    yCoordinate.set((double) (int)(e.getY() / (pane.getHeight() / 8)));
                    oldCoordinates.set(new Point2D((double) (int)(e.getX() / (pane.getWidth() / 8)),(double) (int)(e.getY() / (pane.getHeight() / 8))));

                    rectangles[oldX][oldY].setIsOccupied(0);
                    if (isWhite(code)){
                        rectangles[newX][newY].setIsOccupied(2);
                    } else {
                        rectangles[newX][newY].setIsOccupied(1);
                    }
                    
                    turn.set((turn.get().intValue() + 1) % 2);

                    if (turn.getValue() == 0){
                        removeBlackFocus();
                        retrieveWhiteFocus();
                    } else{
                        removeWhiteFocus();
                        retrieveBlackFocus();
                    }

                } else {
                    setX(oldCoordinates.getValue().getX() * pane.getWidth() / 8);
                    setY(oldCoordinates.getValue().getY() * pane.getHeight() / 8);
                }      

                resetColors();
            });
        }

        public boolean isInPane(double x, double y){
            if(x > 0 && x < pane.getWidth() && y > 0 && y < pane.getHeight())
                return true;
            
            return false;
        }

        public boolean soldierPath(int oldX, int oldY, int newX, int newY, int code){
            if (code == whitePawn && oldX == newX && ((oldY == 6 && oldY - newY == 2) || oldY - newY == 1)
                    && rectangles[newX][newY].getIsOccupied() == blank)
                return true;
                
            if (code == blackPawn && oldX == newX && ((oldY == 1 && oldY - newY == -2) || oldY - newY == -1)
                    && rectangles[newX][newY].getIsOccupied() == blank)
                return true;

            if (isEnemy(code, rectangles[(int)newX][(int)newY].getIsOccupied()) && Math.pow(oldX - newX, 2) == 1 && Math.pow(oldY-newY, 2) == 1){
                deleteEnemy(newX, newY);
                return true;
            }
            return false;
        }

        public boolean rookPath(int oldX, int oldY, int newX, int newY, int code){
            if (oldX == newX){
                for (int i = oldY + 1; oldY < newY && i < newY; i++){
                    if (rectangles[oldX][i].getIsOccupied() != blank){
                        return false;
                }}
                
                for (int i = newY + 1; newY < oldY && i < oldY; i++){
                    if (rectangles[oldX][i].getIsOccupied() != blank){
                        return false;  
                }}

                if (isNotEnemy(code, rectangles[newX][newY].getIsOccupied())){
                    return false;
                }   
                return true;
            }
            
            if (oldY == newY){
                for (int i = oldX + 1; oldX < newX && i < newX; i++){
                    if (rectangles[i][oldY].getIsOccupied() != blank){
                        return false;  
                }}

                for (int i = newX + 1; newX < oldX && i < oldX; i++){
                    if (rectangles[i][oldY].getIsOccupied() != blank){
                        return false;
                }}
                
                if (isNotEnemy(code, rectangles[newX][newY].getIsOccupied())){
                    return false;
                }
                return true;
            }
            return false;
        }

        public boolean knightPath(int oldX, int oldY, int newX, int newY, int code){
            double distX = Math.sqrt(Math.pow(oldX - newX, 2));
            double distY = Math.sqrt(Math.pow(oldY - newY, 2));

            if (isNotEnemy(code, rectangles[newX][newY].getIsOccupied()))
                return false;
            
            if ((distX == 2 && distY == 1))
                return true;

            if ((distX == 1 && distY == 2))
                return true;

            return false;
        }

        public boolean bishopPath(int oldX, int oldY, int newX, int newY, int code){
            if ((oldX - newX) == (newY - oldY)){
                if (oldX < newX){
                    for (int i = oldX + 1, j = oldY - 1; i < newX; i++, j--){
                        if (rectangles[i][j].getIsOccupied() != blank){
                            return false;
                        }
                    }
                    if ((code == blackBishop && rectangles[newX][newY].getIsOccupied() == black) 
                            || (code == whiteBishop && rectangles[newX][newY].getIsOccupied() == white)){
                        return false;
                    }
                }
                if (newX < oldX){
                    for (int i = newX + 1, j = newY - 1; i < oldX; i++, j--){
                        if (rectangles[i][j].getIsOccupied() != blank){
                            return false;
                        }
                    }
                    if ((code == blackBishop && rectangles[newX][newY].getIsOccupied() == black) 
                            || (code == whiteBishop && rectangles[newX][newY].getIsOccupied() == white)){
                        return false;
                }}
                if(oldX != newX)
                    return true;
                
                return false;
            }

            if ((oldX - newX) == (oldY - newY)){
                if (oldX < newX){
                    for (int i = oldX + 1, j = oldY + 1; i < newX; i++, j++){
                        if (rectangles[i][j].getIsOccupied() != blank){
                            return false;
                    }}
                    if ((code == blackBishop && rectangles[newX][newY].getIsOccupied() == black) 
                            || (code == whiteBishop && rectangles[newX][newY].getIsOccupied() == white)){
                        return false;
                    }
                }
                if (newX < oldX){
                    for (int i = newX + 1, j = newY + 1; i < oldX && j < oldY; i++, j++){
                        if (rectangles[i][j].getIsOccupied() != blank){
                            return false;
                        }}
                    if ((code == blackBishop && rectangles[newX][newY].getIsOccupied() == black) 
                            || (code == whiteBishop && rectangles[newX][newY].getIsOccupied() == white)){
                        return false;
                }}
                
                if(oldX != newX)
                    return true;
                
                return false;
            }

            return false;
        }

        public boolean queenPath(int oldX, int oldY, int newX, int newY, int code){
            if (rookPath(oldX, oldY, newX, newY, code / 5) || bishopPath(oldX, oldY, newX, newY, code * 3 / 5))
                return true;

            return false;
        }

        public boolean kingPath(int oldX, int oldY, int newX, int newY, int code){
            if (oldX == newX && oldY == newY)
                return false;
                
             if (Math.pow(oldX - newX, 2) <= 1 && Math.pow(oldY - newY, 2) <= 1){
                if ((code == whiteKing && rectangles[newX][newY].getIsOccupied() == white)
                        || (code == blackKing && rectangles[newX][newY].getIsOccupied() == black))
                    return false;

                return true;
            }

            return false;
        }
    
        static ObjectProperty<Integer> recordWhite = new SimpleObjectProperty<>(-1);
        static ObjectProperty<Integer> recordBlack = new SimpleObjectProperty<>(-1);

        public void deleteEnemy(double x, double y){
            double widthRatio = pane.getWidth() / 8, heightRatio = pane.getHeight() / 8;

            if (blackPawnIV1.getX() / widthRatio == x && blackPawnIV1.getY() / heightRatio == y  
                    && !blackPawnIV1.getDeleted()){
                blackPawnIV1.setDeleted();
                pane.getChildren().remove(blackPawnIV1);
                recordBlack.set(recordBlack.getValue() + 1);
                blackOut.add(blackPawnIV1, recordBlack.get().intValue() % 2, recordBlack.get().intValue() / 2);
            }
            if (blackPawnIV2.getX() / widthRatio == x && blackPawnIV2.getY() / heightRatio == y  
                    && !blackPawnIV2.getDeleted()){
                blackPawnIV2.setDeleted();
                pane.getChildren().remove(blackPawnIV2);
                recordBlack.set(recordBlack.getValue() + 1);
                blackOut.add(blackPawnIV2, recordBlack.get().intValue() % 2, recordBlack.get().intValue() / 2);
            }
            if (blackPawnIV3.getX() / widthRatio == x && blackPawnIV3.getY() / heightRatio == y  
                    && !blackPawnIV3.getDeleted()){
                blackPawnIV3.setDeleted();
                pane.getChildren().remove(blackPawnIV3);
                recordBlack.set(recordBlack.getValue() + 1);
                blackOut.add(blackPawnIV3, recordBlack.get().intValue() % 2, recordBlack.get().intValue() / 2);
            }
            if (blackPawnIV4.getX() / widthRatio == x && blackPawnIV4.getY() / heightRatio == y  
                    && !blackPawnIV4.getDeleted()){
                blackPawnIV4.setDeleted();
                pane.getChildren().remove(blackPawnIV4);
                recordBlack.set(recordBlack.getValue() + 1);
                blackOut.add(blackPawnIV4, recordBlack.get().intValue() % 2, recordBlack.get().intValue() / 2);
            }
            if (blackPawnIV5.getX() / widthRatio == x && blackPawnIV5.getY() / heightRatio == y  
                    && !blackPawnIV5.getDeleted()){
                blackPawnIV5.setDeleted();
                pane.getChildren().remove(blackPawnIV5);
                recordBlack.set(recordBlack.getValue() + 1);
                blackOut.add(blackPawnIV5, recordBlack.get().intValue() % 2, recordBlack.get().intValue() / 2);
            }
            if (blackPawnIV6.getX() / widthRatio == x && blackPawnIV6.getY() / heightRatio == y  
                    && !blackPawnIV6.getDeleted()){
                blackPawnIV6.setDeleted();
                pane.getChildren().remove(blackPawnIV6);
                recordBlack.set(recordBlack.getValue() + 1);
                blackOut.add(blackPawnIV6, recordBlack.get().intValue() % 2, recordBlack.get().intValue() / 2);
            }
            if (blackPawnIV7.getX() / widthRatio == x && blackPawnIV7.getY() / heightRatio == y  
                    && !blackPawnIV7.getDeleted()){
                blackPawnIV7.setDeleted();
                pane.getChildren().remove(blackPawnIV7);
                recordBlack.set(recordBlack.getValue() + 1);
                blackOut.add(blackPawnIV7, recordBlack.get().intValue() % 2, recordBlack.get().intValue() / 2);
            }
            if (blackPawnIV8.getX() / widthRatio == x && blackPawnIV8.getY() / heightRatio == y  
                    && !blackPawnIV8.getDeleted()){
                blackPawnIV8.setDeleted();
                pane.getChildren().remove(blackPawnIV8);
                recordBlack.set(recordBlack.getValue() + 1);
                blackOut.add(blackPawnIV8, recordBlack.get().intValue() % 2, recordBlack.get().intValue() / 2);
            } 
            
            if (whitePawnIV1.getX() / widthRatio == x && whitePawnIV1.getY() / heightRatio == y  
                    && !whitePawnIV1.getDeleted()){
                whitePawnIV1.setDeleted();
                pane.getChildren().remove(whitePawnIV1);
                recordWhite.set(recordWhite.getValue() + 1);
                whiteOut.add(whitePawnIV1, recordWhite.get().intValue() % 2, recordWhite.get().intValue() / 2);
            }
            if (whitePawnIV2.getX() / widthRatio == x && whitePawnIV2.getY() / heightRatio == y  
                    && !whitePawnIV2.getDeleted()){
                whitePawnIV2.setDeleted();
                pane.getChildren().remove(whitePawnIV2);
                recordWhite.set(recordWhite.getValue() + 1);
                whiteOut.add(whitePawnIV2, recordWhite.get().intValue() % 2, recordWhite.get().intValue() / 2);
            }
            if (whitePawnIV3.getX() / widthRatio == x && whitePawnIV3.getY() / heightRatio == y  
                    && !whitePawnIV3.getDeleted()){
                whitePawnIV3.setDeleted();
                pane.getChildren().remove(whitePawnIV3);
                recordWhite.set(recordWhite.getValue() + 1);
                whiteOut.add(whitePawnIV3, recordWhite.get().intValue() % 2, recordWhite.get().intValue() / 2);
            }
            if (whitePawnIV4.getX() / widthRatio == x && whitePawnIV4.getY() / heightRatio == y  
                    && !whitePawnIV4.getDeleted()){
                whitePawnIV4.setDeleted();
                pane.getChildren().remove(whitePawnIV4);
                recordWhite.set(recordWhite.getValue() + 1);
                whiteOut.add(whitePawnIV4, recordWhite.get().intValue() % 2, recordWhite.get().intValue() / 2);
            }
            if (whitePawnIV5.getX() / widthRatio == x && whitePawnIV5.getY() / heightRatio == y  
                    && !whitePawnIV5.getDeleted()){
                whitePawnIV5.setDeleted();
                pane.getChildren().remove(whitePawnIV5);
                recordWhite.set(recordWhite.getValue() + 1);
                whiteOut.add(whitePawnIV5, recordWhite.get().intValue() % 2, recordWhite.get().intValue() / 2);
            }
            if (whitePawnIV6.getX() / widthRatio == x && whitePawnIV6.getY() / heightRatio == y  
                    && !whitePawnIV6.getDeleted()){
                whitePawnIV6.setDeleted();
                pane.getChildren().remove(whitePawnIV6);
                recordWhite.set(recordWhite.getValue() + 1);
                whiteOut.add(whitePawnIV6, recordWhite.get().intValue() % 2, recordWhite.get().intValue() / 2);
            }
            if (whitePawnIV7.getX() / widthRatio == x && whitePawnIV7.getY() / heightRatio == y  
                    && !whitePawnIV7.getDeleted()){
                whitePawnIV7.setDeleted();
                pane.getChildren().remove(whitePawnIV7);
                recordWhite.set(recordWhite.getValue() + 1);
                whiteOut.add(whitePawnIV7, recordWhite.get().intValue() % 2, recordWhite.get().intValue() / 2);
            }
            if (whitePawnIV8.getX() / widthRatio == x && whitePawnIV8.getY() / heightRatio == y  
                    && !whitePawnIV8.getDeleted()){
                whitePawnIV8.setDeleted();
                pane.getChildren().remove(whitePawnIV8);
                recordWhite.set(recordWhite.getValue() + 1);
                whiteOut.add(whitePawnIV8, recordWhite.get().intValue() % 2, recordWhite.get().intValue() / 2);
            }
            
            if (blackBishop1IV.getX() / widthRatio == x && blackBishop1IV.getY() / heightRatio == y  
                    && !blackBishop1IV.getDeleted()){
                blackBishop1IV.setDeleted();
                pane.getChildren().remove(blackBishop1IV);
                recordBlack.set(recordBlack.getValue() + 1);
                blackOut.add(blackBishop1IV, recordBlack.get().intValue() % 2, recordBlack.get().intValue() / 2);
            }
            if (blackBishop2IV.getX() / widthRatio == x && blackBishop2IV.getY() / heightRatio == y  
                    && !blackBishop2IV.getDeleted()){
                blackBishop2IV.setDeleted();
                pane.getChildren().remove(blackBishop2IV);
                recordBlack.set(recordBlack.getValue() + 1);
                blackOut.add(blackBishop2IV, recordBlack.get().intValue() % 2, recordBlack.get().intValue() / 2);
            }       
            if (whiteBishop1IV.getX() / widthRatio == x && whiteBishop1IV.getY() / heightRatio == y  
                    && !whiteBishop1IV.getDeleted()){
                whiteBishop1IV.setDeleted();
                pane.getChildren().remove(whiteBishop1IV);
                recordWhite.set(recordWhite.getValue() + 1);
                whiteOut.add(whiteBishop1IV, recordWhite.get().intValue() % 2, recordWhite.get().intValue() / 2);
            }
            if (whiteBishop2IV.getX() / widthRatio == x && whiteBishop2IV.getY() / heightRatio == y  
                    && !whiteBishop2IV.getDeleted()){
                whiteBishop2IV.setDeleted();
                pane.getChildren().remove(whiteBishop2IV);
                recordWhite.set(recordWhite.getValue() + 1);
                whiteOut.add(whiteBishop2IV, recordWhite.get().intValue() % 2, recordWhite.get().intValue() / 2);
            }
            
            if (blackRook1IV.getX() / widthRatio == x && blackRook1IV.getY() / heightRatio == y  
                    && !blackRook1IV.getDeleted()){
                blackRook1IV.setDeleted();
                pane.getChildren().remove(blackRook1IV);
                recordBlack.set(recordBlack.getValue() + 1);
                blackOut.add(blackRook1IV, recordBlack.get().intValue() % 2, recordBlack.get().intValue() / 2);
            }
            if (blackRook2IV.getX() / widthRatio == x && blackRook2IV.getY() / heightRatio == y  
                    && !blackRook2IV.getDeleted()){
                blackRook2IV.setDeleted();
                pane.getChildren().remove(blackRook2IV);
                recordBlack.set(recordBlack.getValue() + 1);
                blackOut.add(blackRook2IV, recordBlack.get().intValue() % 2, recordBlack.get().intValue() / 2);
            }           
            if (whiteRook1IV.getX() / widthRatio == x && whiteRook1IV.getY() / heightRatio == y  
                    && !whiteRook1IV.getDeleted()){
                whiteRook1IV.setDeleted();
                pane.getChildren().remove(whiteRook1IV);
                recordWhite.set(recordWhite.getValue() + 1);
                whiteOut.add(whiteRook1IV, recordWhite.get().intValue() % 2, recordWhite.get().intValue() / 2);
            }
            if (whiteRook2IV.getX() / widthRatio == x && whiteRook2IV.getY() / heightRatio == y  
                    && !whiteRook2IV.getDeleted()){
                whiteRook2IV.setDeleted();
                pane.getChildren().remove(whiteRook2IV);
                recordWhite.set(recordWhite.getValue() + 1);
                whiteOut.add(whiteRook2IV, recordWhite.get().intValue() % 2, recordWhite.get().intValue() / 2);
            }
            
            if (blackKnight1IV.getX() / widthRatio == x && blackKnight1IV.getY() / heightRatio == y  
                    && !blackKnight1IV.getDeleted()){
                blackKnight1IV.setDeleted();
                pane.getChildren().remove(blackKnight1IV);
                recordBlack.set(recordBlack.getValue() + 1);
                blackOut.add(blackKnight1IV, recordBlack.get().intValue() % 2, recordBlack.get().intValue() / 2);
            }
            if (blackKnight2IV.getX() / widthRatio == x && blackKnight2IV.getY() / heightRatio == y  
                    && !blackKnight2IV.getDeleted()){
                blackKnight2IV.setDeleted();
                pane.getChildren().remove(blackKnight2IV);
                recordBlack.set(recordBlack.getValue() + 1);
                blackOut.add(blackKnight2IV, recordBlack.get().intValue() % 2, recordBlack.get().intValue() / 2);
            } 
            if (whiteKnight1IV.getX() / widthRatio == x && whiteKnight1IV.getY() / heightRatio == y  
                    && !whiteKnight1IV.getDeleted()){
                whiteKnight1IV.setDeleted();
                pane.getChildren().remove(whiteKnight1IV);
                recordWhite.set(recordWhite.getValue() + 1);
                whiteOut.add(whiteKnight1IV, recordWhite.get().intValue() % 2, recordWhite.get().intValue() / 2);
            }
            if (whiteKnight2IV.getX() / widthRatio == x && whiteKnight2IV.getY() / heightRatio == y  
                    && !whiteKnight2IV.getDeleted()){
                whiteKnight2IV.setDeleted();
                pane.getChildren().remove(whiteKnight2IV);
                recordWhite.set(recordWhite.getValue() + 1);    
                whiteOut.add(whiteKnight2IV, recordWhite.get().intValue() % 2, recordWhite.get().intValue() / 2);
            }

            if (blackQueenIV.getX() / widthRatio == x && blackQueenIV.getY() / heightRatio == y  
                    && !blackQueenIV.getDeleted()){
                blackQueenIV.setDeleted();
                pane.getChildren().remove(blackQueenIV);
                recordBlack.set(recordBlack.getValue() + 1);
                blackOut.add(blackQueenIV, recordBlack.get().intValue() % 2, recordBlack.get().intValue() / 2);
            }
            if (whiteQueenIV.getX() / widthRatio == x && whiteQueenIV.getY() / heightRatio == y  
                    && !whiteQueenIV.getDeleted()){
                whiteQueenIV.setDeleted();
                pane.getChildren().remove(whiteQueenIV);
                recordWhite.set(recordWhite.getValue() + 1);
                whiteOut.add(whiteQueenIV, recordWhite.get().intValue() % 2, recordWhite.get().intValue() / 2);
            }
            
            if (blackKingIV.getX() / widthRatio == x && blackKingIV.getY() / heightRatio == y && !blackKingIV.getDeleted()){
                blackKingIV.setDeleted();
                pane.getChildren().remove(blackKingIV);
                recordBlack.set(recordBlack.getValue() + 1);
                blackOut.add(blackKingIV, recordBlack.get().intValue() % 2, recordBlack.get().intValue() / 2);

                FadeTransition ft = new FadeTransition(Duration.millis(3000), mainPane);
                ImageView whiteWins = new ImageView(new Image("chessboard/images/whiteWins.jpg"));
                whiteWins.fitWidthProperty().bind(mainPane.widthProperty());
                whiteWins.fitHeightProperty().bind(mainPane.heightProperty());
                ft.setFromValue(1.0);
                ft.setToValue(0);
                ft.setCycleCount(1);
                ft.setAutoReverse(false);
                ft.play();


                ft.setOnFinished(e -> {
                    mainPane.getChildren().clear();
                    mainPane.getChildren().add(whiteWins);
                    
                    FadeTransition ft1 = new FadeTransition(Duration.millis(3000), mainPane);
                    ft1.setFromValue(0);
                    ft1.setToValue(1.0);
                    ft1.setCycleCount(1);
                    ft1.setAutoReverse(false);
                    ft1.play();
                });
            }
            if (whiteKingIV.getX() / widthRatio == x && whiteKingIV.getY() / heightRatio == y && !whiteKingIV.getDeleted()){
                whiteKingIV.setDeleted();
                pane.getChildren().remove(whiteKingIV);
                recordWhite.set(recordWhite.getValue() + 1);
                whiteOut.add(whiteKingIV, recordWhite.get().intValue() % 2, recordWhite.get().intValue() / 2);
                
                FadeTransition ft = new FadeTransition(Duration.millis(3000), mainPane);
                ImageView blackWins = new ImageView(new Image("chessboard/images/blackWins.jpg"));
                blackWins.fitWidthProperty().bind(mainPane.widthProperty());
                blackWins.fitHeightProperty().bind(mainPane.heightProperty());
                
                ft.setFromValue(1.0);
                ft.setToValue(0);
                ft.setCycleCount(1);
                ft.setAutoReverse(false);
                ft.play();

                ft.setOnFinished(e -> {
                    mainPane.getChildren().clear();
                    mainPane.getChildren().add(blackWins);

                    FadeTransition ft1 = new FadeTransition(Duration.millis(3000), mainPane);
                    ft1.setFromValue(0);
                    ft1.setToValue(1.0);
                    ft1.setCycleCount(1);
                    ft1.setAutoReverse(false);
                    ft1.play();
                });
                

            }
        }

        public void removeBlackFocus(){
            if(blackPawnIV1.getDeleted()   == false)      blackPawnIV1.setDisable(true);
            if(blackPawnIV2.getDeleted()   == false)      blackPawnIV2.setDisable(true);
            if(blackPawnIV3.getDeleted()   == false)      blackPawnIV3.setDisable(true);
            if(blackPawnIV4.getDeleted()   == false)      blackPawnIV4.setDisable(true);
            if(blackPawnIV5.getDeleted()   == false)      blackPawnIV5.setDisable(true);
            if(blackPawnIV6.getDeleted()   == false)      blackPawnIV6.setDisable(true);
            if(blackPawnIV7.getDeleted()   == false)      blackPawnIV7.setDisable(true);
            if(blackPawnIV8.getDeleted()   == false)      blackPawnIV8.setDisable(true);
            if(blackBishop1IV.getDeleted() == false)      blackBishop1IV.setDisable(true);
            if(blackBishop2IV.getDeleted() == false)      blackBishop2IV.setDisable(true);
            if(blackRook1IV.getDeleted()   == false)      blackRook1IV.setDisable(true);
            if(blackRook2IV.getDeleted()   == false)      blackRook2IV.setDisable(true);
            if(blackKnight1IV.getDeleted() == false)      blackKnight1IV.setDisable(true);
            if(blackKnight2IV.getDeleted() == false)      blackKnight2IV.setDisable(true);
            if(blackQueenIV.getDeleted()   == false)      blackQueenIV.setDisable(true);
            if(blackKingIV.getDeleted()    == false)      blackKingIV.setDisable(true);
        }
        
        public void removeWhiteFocus(){
            if(whitePawnIV1.getDeleted()   == false)      whitePawnIV1.setDisable(true);
            if(whitePawnIV2.getDeleted()   == false)      whitePawnIV2.setDisable(true);
            if(whitePawnIV3.getDeleted()   == false)      whitePawnIV3.setDisable(true);
            if(whitePawnIV4.getDeleted()   == false)      whitePawnIV4.setDisable(true);
            if(whitePawnIV5.getDeleted()   == false)      whitePawnIV5.setDisable(true);
            if(whitePawnIV6.getDeleted()   == false)      whitePawnIV6.setDisable(true);
            if(whitePawnIV7.getDeleted()   == false)      whitePawnIV7.setDisable(true);
            if(whitePawnIV8.getDeleted()   == false)      whitePawnIV8.setDisable(true);
            if(whiteBishop1IV.getDeleted() == false)      whiteBishop1IV.setDisable(true);
            if(whiteBishop2IV.getDeleted() == false)      whiteBishop2IV.setDisable(true);
            if(whiteRook1IV.getDeleted()   == false)      whiteRook1IV.setDisable(true);
            if(whiteRook2IV.getDeleted()   == false)      whiteRook2IV.setDisable(true);
            if(whiteKnight1IV.getDeleted() == false)      whiteKnight1IV.setDisable(true);
            if(whiteKnight2IV.getDeleted() == false)      whiteKnight2IV.setDisable(true);
            if(whiteQueenIV.getDeleted()   == false)      whiteQueenIV.setDisable(true);
            if(whiteKingIV.getDeleted()    == false)      whiteKingIV.setDisable(true);
        }
        
        public void retrieveBlackFocus(){
            if(blackPawnIV1.getDeleted()    == false)      blackPawnIV1.setDisable(false);
            if(blackPawnIV2.getDeleted()    == false)      blackPawnIV2.setDisable(false);
            if(blackPawnIV3.getDeleted()    == false)      blackPawnIV3.setDisable(false);
            if(blackPawnIV4.getDeleted()    == false)      blackPawnIV4.setDisable(false);
            if(blackPawnIV5.getDeleted()    == false)      blackPawnIV5.setDisable(false);
            if(blackPawnIV6.getDeleted()    == false)      blackPawnIV6.setDisable(false);
            if(blackPawnIV7.getDeleted()    == false)      blackPawnIV7.setDisable(false);
            if(blackPawnIV8.getDeleted()    == false)      blackPawnIV8.setDisable(false);
            if(blackBishop1IV.getDeleted()  == false)      blackBishop1IV.setDisable(false);
            if(blackBishop2IV.getDeleted()  == false)      blackBishop2IV.setDisable(false);
            if(blackRook1IV.getDeleted()    == false)      blackRook1IV.setDisable(false);
            if(blackRook2IV.getDeleted()    == false)      blackRook2IV.setDisable(false);
            if(blackKnight1IV.getDeleted()  == false)      blackKnight1IV.setDisable(false);
            if(blackKnight2IV.getDeleted()  == false)      blackKnight2IV.setDisable(false);
            if(blackQueenIV.getDeleted()    == false)      blackQueenIV.setDisable(false);
            if(blackKingIV.getDeleted()     == false)      blackKingIV.setDisable(false);
        }
        
        public void retrieveWhiteFocus(){
            if(whitePawnIV1.getDeleted()    == false)      whitePawnIV1.setDisable(false);
            if(whitePawnIV2.getDeleted()    == false)      whitePawnIV2.setDisable(false);
            if(whitePawnIV3.getDeleted()    == false)      whitePawnIV3.setDisable(false);
            if(whitePawnIV4.getDeleted()    == false)      whitePawnIV4.setDisable(false);
            if(whitePawnIV5.getDeleted()    == false)      whitePawnIV5.setDisable(false);
            if(whitePawnIV6.getDeleted()    == false)      whitePawnIV6.setDisable(false);
            if(whitePawnIV7.getDeleted()    == false)      whitePawnIV7.setDisable(false);
            if(whitePawnIV8.getDeleted()    == false)      whitePawnIV8.setDisable(false);
            if(whiteBishop1IV.getDeleted()  == false)      whiteBishop1IV.setDisable(false);
            if(whiteBishop2IV.getDeleted()  == false)      whiteBishop2IV.setDisable(false);
            if(whiteRook1IV.getDeleted()    == false)      whiteRook1IV.setDisable(false);
            if(whiteRook2IV.getDeleted()    == false)      whiteRook2IV.setDisable(false);
            if(whiteKnight1IV.getDeleted()  == false)      whiteKnight1IV.setDisable(false);
            if(whiteKnight2IV.getDeleted()  == false)      whiteKnight2IV.setDisable(false);
            if(whiteQueenIV.getDeleted()    == false)      whiteQueenIV.setDisable(false);
            if(whiteKingIV.getDeleted()     == false)      whiteKingIV.setDisable(false);
        }
    
        public boolean isWhite(int code){
            if (code > 0)
                return true;
            return false;
        }
        
        public boolean isBlack(int code){
            if (code < 0)
                return true;
            return false;
        }
    
        public void resetColors(){
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++){
                    if ((i + j) % 2 == 0)
                        rectangles[i][j].setFill(whiteBlock);
                    else
                        rectangles[i][j].setFill(blackBlock);
                }

        }

        public boolean isNotEnemy(int code, int isOccupied){
            if (code < 0 && isOccupied == black || code > 0 && isOccupied == white){
                return true;
            }
            return false;
        }

        public boolean isEnemy(int code, int isOccupied){
            if (code < 0 && isOccupied == white || code > 0 && isOccupied == black){
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}