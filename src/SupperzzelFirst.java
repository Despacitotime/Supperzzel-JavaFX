import javafx.application.Application;
import java.util.ArrayList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.image.*;
import javafx.scene.media.*;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javax.swing.JOptionPane;

 
public class SupperzzelFirst extends Application {
    int counts;
    GridPane gridPane1 = new GridPane();
    //������ʼ�ͽ�����Ϸ��ť
    Button button1 = new Button("��ʼ��Ϸ");
    Button button2 = new Button("��Ϸ����");
    TextField text1 = new TextField("400");
    Image[] image = new Image[7];
    int[] iNumber = new int[1000];
	int[] jNumber = new int[1000];
	int[] numbers3[] = new int[8][5];
	boolean chosen = true;
    int m = 0,n = 0;
    int x1 = 0,y1 =0,x2 = 0,y2 = 0;
    //��������ͼƬ�������飬һ�洢���棬һ���洢����
    ImageView[][] imageview = new ImageView[8][5];
    ImageView[][] imageview5 = new ImageView[8][5];
    Clock clock = new Clock();
    Time time = new Time();	
    @Override 
    public void start(Stage primaryStage) {  
      BorderPane pane2 = new BorderPane();
      BorderPane pane = new BorderPane();
      Button btNS = new Button("��ʼ��Ϸ");
      pane2.setTop(getGridPane3());
      pane2.setCenter(btNS);
      Scene newScene = new Scene(pane2);
      Scene scene = new Scene(pane);
      primaryStage.setTitle("�Զ�����Ϸ");
      primaryStage.setScene(newScene);
      btNS.setOnMousePressed(e -> {
        primaryStage.setScene(scene);
      });
       
      
      pane.setTop(getGridPane());
      pane.setCenter(getGridPane2());
      
      initCountDown();
      initClock();
      initMouseEvent();
      primaryStage.setTitle("�Զ�����Ϸ");
     
  
      primaryStage.show();
    
  }
  
    public GridPane getGridPane(){
        gridPane1.setHgap(10);
        gridPane1.setPadding(new Insets(15,5,5,5));
        gridPane1.setStyle("-fx-background-color:white");
        gridPane1.add(new Label("ʱ�䣺"),0,0);
        
        gridPane1.add(new Label("������"),2,0);
        gridPane1.add(text1,3,0);
        text1.setAlignment(Pos.BOTTOM_RIGHT);
    
    
        gridPane1.add(clock,13,0);
        gridPane1.add(button1,15,0);
        gridPane1.add(button2,16,0);
    //-------------------------------------------------------------------------------
    return gridPane1;
  }
  
    public GridPane getGridPane2(){
    	GridPane gridPane2 = new GridPane();
    	gridPane2.setPadding(new Insets(50,50,50,50));	
		
		//����һ������洢�߸�ͼƬ����
		image[0] = new Image("1.jpg");
		image[1] = new Image("2.jpg");
		image[2] = new Image("3.jpg");
		image[3] = new Image("4.jpg");
		image[4] = new Image("5.jpg");
		image[5] = new Image("6.jpg");
		image[6] = new Image("7.jpg");
        
		//����һ������洢�����20����������飬��0-6��������
			int[] numbers1 = new int[20];
    		for(int i = 0;i < 20;i++){
		    	int randomNumber = (int)(Math.random() * 7);
	    		numbers1[i] = randomNumber;

    		}
		
			//��������20����������������һ���������ڲ���20��ͼ�����
        	int[] numbers2 = new int[40];
			for(int j = 0;j < 40;j++){
				if(j < 20){
		    		numbers2[j] = numbers1[j];
				}else{

					numbers2[j] = numbers1[j - 20];
				}
			}
			
		
		
 		//�˴���������Ĵ���-------------------------------------------
		for(int k = 0;k < 10;k++){
	    		for(int j = 0;j < 40;j++){
	    			int n = (int)(Math.random() * 40);
	    			int temp = numbers2[j];
	    			numbers2[j] = numbers2[n];
	    			numbers2[n] = temp;
    			}
	    	}
		
		//------------------------------------------------------------
		
		//����һ��5*8���������ڴ洢numbers2����ֵ
		int r = 0;
		for(int i = 0;i < 8;i++){
			for(int j = 0;j < 5;j++){
				numbers3[i][j] = numbers2[r];
				r++;
			}
		}
		
		//��40���ֶ�������8*5��ImageView������ȥ
		int k = 0;
		for(int i = 0;i < 8;i++){
			for(int j = 0;j < 5;j++){
				imageview5[i][j] = new ImageView(image[numbers2[k]]);
				imageview5[i][j].setFitHeight(76);
				imageview5[i][j].setFitWidth(76);
				gridPane2.add(imageview5[i][j],i,j);
				k++;
			}
		}
		
		
		//��ͼƬ��������8*5�����������
		for(int i = 0;i < 8;i++){
			for(int j = 0;j < 5;j++){
				imageview[i][j] = new ImageView("haidao00.jpg");
				imageview[i][j].setFitHeight(76);
				imageview[i][j].setFitWidth(76);
				gridPane2.add(imageview[i][j],i,j);
			}
		}
		
        //����ͼƬ����괥���¼�������Ǳ��潻�������棬�ͷ��Ǳ��潻��������
  	    gridPane2.setOnMousePressed(e -> {
			int y = (int)e.getY();
			int x = (int)e.getX();
			storeIndex(getXIndex(x),getYIndex(y));
			countNumber();
			text1.setText(String.valueOf(400 - counts));
			
			gridPane2.getChildren().removeAll(imageview[getXIndex(x)][getYIndex(y)]);
			if(isCommon()){
		       if(iNumber[m - 2] != iNumber[m - 1] || jNumber[m - 2] != jNumber[m - 1]){
		    	  gridPane2.getChildren().
		    	  removeAll(imageview5[iNumber[m-2]][jNumber[m-2]],imageview5[iNumber[m-1]][jNumber[m-1]]);
		    	  numbers3[iNumber[m - 2]][jNumber[m - 2]] = 100;
		    	  numbers3[iNumber[m - 1]][jNumber[m - 1]] = 100;
                  m++;
                  n++;
                  chosen = false;
			   }
		    }  
			if(isNotCommon()){
		       gridPane2.add(imageview[iNumber[m-2]][jNumber[m-2]],iNumber[m-2],jNumber[m-2]);
		    }	
	    });	
		            	
         	
        gridPane2.setOnMouseReleased(e -> {
	 		int y = (int)e.getY();
			int x = (int)e.getX();
				
		  	gridPane2.add(imageview[getXIndex(x)][getYIndex(y)],getXIndex(x),getYIndex(y));
	        countNumber();
	        text1.setText(String.valueOf(400 - counts));	
        	});
	    		
   return gridPane2;
  }
  
    public GridPane getGridPane3(){
    	GridPane bkGround = new GridPane();
    	int[] num1 = new int[40];
    	ImageView bkImage = new ImageView("00.jpg");
    	Text txt = new Text("                   ����Զ���");
    	txt.setFont(Font.font("����",FontWeight.BOLD,36));
    	txt.setFill(new Color(Math.random(), Math.random(), Math.random(), Math.random()));
    	bkGround.add(txt,0,0);
    	bkGround.add(bkImage,0,1);
    	
    	return bkGround;
    }
  
    public int getXIndex(int x){
  	int i = 0;
  	if(x >=56 && x < 132){
			i = 0;
		}else if(x >= 132 && x < 208){
			i = 1;
		}else if(x >= 208 && x < 284){
			i = 2;
		}else if(x >= 284 && x < 360){
			i = 3;
		}else if(x >= 360 && x < 436){
			i = 4;
	    }else if(x >= 436 && x < 512){
			i = 5;
	    }else if(x >= 512 && x < 588){
			i = 6;
	    }else if(x >= 588 && x < 664){
			i = 7;
		}else{
			System.out.println("��û��ѡ���κ�ͼ��.");
		}	
  	
	return i;
  }
  	
    public int getYIndex(int y){
  	int j = 0;
  	if(y >60 && y < 120){
		j = 0;
	}else if(y >= 120 && y < 196){
		j = 1;
	}else if(y >= 196 && y < 272){
		j = 2;
	}else if(y >= 272 && y < 348){
		j = 3;
	}else if(y >= 348 && y < 424){
		j = 4;
	}else{
		System.out.println("��û��ѡ���κ�ͼ��.");
	}
  	
  	return j;
  }
  
    public void initClock(){
	//��ʱ�Ӷ�����г�ʼ������
    EventHandler<ActionEvent> eventHandler = e -> {
    	clock.setCurrentTime();
	};
	
    Timeline animation = new Timeline(
    	new KeyFrame(Duration.millis(1000),eventHandler));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play();
  }
  
    public void countNumber(){
  	counts++;
  }
  
    public boolean isCommon(){
    	if(m > 1){
    			if(numbers3[iNumber[m - 2]][jNumber[m - 2]] == numbers3[iNumber[m - 1]][jNumber[m - 1]]){
    				chosen = true;
		    		return true;
		    	}else{
		    		if(!chosen){
		    	    	chosen = true;
		    	    	return false;
		    		}
		    	}
	    }return false;
	}
	
	public boolean isNotCommon(){
		if(m > 1){
    			if(numbers3[iNumber[m - 2]][jNumber[m - 2]] != numbers3[iNumber[m - 1]][jNumber[m - 1]]){
    				return true;
		    	}else{
		    		return false;
		    	}
	    }return false;
	}
	
	public void storeIndex(int x,int y){
        
        iNumber[m] = x;
    	jNumber[n] = y;
    	m++;
    	n++;
    }
    
    public void initMouseEvent(){
    //���ð�ť����괥���¼���ȷ���ڵ�����ǵ�ʱ��᷵��һ��ʱ��
        button1.setOnMousePressed(e -> {
    	for(int i = 0;i < 2;i++){
    		System.out.println(i);
    	}
    	int hour1 = clock.getHour();
    	int minute1 = clock.getMinute();
    	int second1 = clock.getSecond();
    	System.out.println(hour1 + ":" + minute1 + ":" + second1);

        });
        
        button2.setOnMousePressed(e -> {
    	int hour2 = clock.getHour();
    	int minute2 = clock.getMinute();
    	int second2 = clock.getSecond();
    	System.out.println(hour2 + ":" + minute2 + ":" + second2);
    	countsTimes();
    	});
    }
    
    public void countsTimes(){
    	int times = time.getHour() *  3600 + time.getMinute() * 60 + time.getSecond();
    	int totaltime = 300 - times;
    	int hour = totaltime / 3600;
    	int minute = (totaltime /60) % 60;
    	int second = totaltime % 60;
    	System.out.println("" + hour + ":" + minute + ":" + second);
    	String output1 ="������Ϸ����ʱ��Ϊ��" +String.valueOf(hour) + ":" + String.valueOf(minute)
  		                             + ":" + String.valueOf(second)
  		                             + "\nGameOver" 
  		                             + "\n�÷�Ϊ��" + String.valueOf(400 - counts);  
        JOptionPane.showMessageDialog(null,output1);

  }
    
    public static void main(String[] args) {
    launch(args);
  }
  
    public void initCountDown(){
    	    //��������ʱ��ʾ------------------------------------
        Text text = new Text("00:05:00");
  
        Timeline animation2 = new Timeline(
          new KeyFrame(Duration.millis(1000), e -> {
            time.increase();
            text.setText(time.toString());
        }));
        animation2.setCycleCount(Timeline.INDEFINITE);
    
        button1.setOnAction(e -> {
          if (button1.getText().equals("��ʼ��Ϸ")) {
         	button1.setText("��ͣ");
            animation2.play();
          } 
          else if (button1.getText().equals("��ͣ")) {
        	button1.setText("����");
            animation2.pause();
          } 
          else if (button1.getText().equals("����")) {
         	button1.setText("��ͣ");
            animation2.play();
          } 
         });
         
        button2.setOnAction(e -> {
          button1.setText("��ʼ��Ϸ");
          button2.setText("GameOver");
          animation2.pause();
        });
        text.setFont(Font.font("Times", 20));
    
        gridPane1.add(text,1,0);

    }
}

