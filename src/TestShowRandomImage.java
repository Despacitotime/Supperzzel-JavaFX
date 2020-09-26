import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.image.Image;

 
public class TestShowRandomImage extends Application{
    @Override 
    public void start(Stage primaryStage) { 
    	GridPane pane = new GridPane();
	pane.setPadding(new Insets(50,50,50,50));
	
		//����һ������洢�߸�ͼƬ����
		Image[] image = new Image[7];
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
		
		
		//��40���ֶ�������8*5��ImageView������ȥ
		ImageView[][] imageview5 = new ImageView[8][5];
		int k = 0;
		for(int i = 0;i < 8;i++){
			for(int j = 0;j < 5;j++){
				imageview5[i][j] = new ImageView(image[numbers2[k]]);
				imageview5[i][j].setFitHeight(76);
				imageview5[i][j].setFitWidth(76);
				pane.add(imageview5[i][j],i,j);
				k++;
			}
		}
		
	Scene scene = new Scene(pane);
    primaryStage.setTitle("���ͼƬ����");
    primaryStage.setScene(scene);  
    primaryStage.show();
    }
     public static void main(String[] args) {
    launch(args);
  }
}
