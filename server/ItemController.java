package server;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import common.Commands;
import common.Item;
import common.Massage;

public class ItemController {

    private Item _item;
   @FXML
   TextField name;
   @FXML
    TextField price;
   @FXML
    ImageView my_image;




    public void setItem(Item item) {

    	_item=item;
        if (item == null) {
            name.setText(null);
            price.setText(null);
            my_image.setImage(null);
        } else {
            name.setText(item.getName());
            price.setText(String.valueOf(item.getPrice()));
            Massage m = new Massage(item,Commands.GETIMAGE);
            server.Main.send_toServer(m);
            m = server.Main.get_from_server();
            //if(m.getCommand()!=Commands.DBERROR);
		/*	byte[] immAsBytes = (byte[])m.getObject();
//			InputStream in = new ByteArrayInputStream(immAsBytes);*/
//            URL url=null;
//			try {
//				url = new URL("http://images.pexels.com/photos/60597/dahlia-red-blossom-bloom-60597.jpeg");
//			} catch (MalformedURLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//	        URL imageUrl;
//			BufferedImage imgFromDb;
//			try {
//				imageUrl = new URL("https://images.pexels.com/photos/60597/dahlia-red-blossom-bloom-60597.jpeg");
//	            HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
//	                    connection.setRequestProperty(
//	                            "User-Agent",
//	                            "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:37.0) Gecko/20100101 Firefox/37.0");
//				imgFromDb = ImageIO.read(connection.getInputStream());
//			//	imgFromDb = ImageIO.read(in);
//				BufferedImage resized = new BufferedImage(50, 50, imgFromDb.getType());
//				Graphics2D g = resized.createGraphics();
//				g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//				    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//				g.drawImage(imgFromDb, 0, 0,50, 50, 0, 0, imgFromDb.getWidth(),
//						imgFromDb.getHeight(), null);
//				g.dispose();
//	            Image image = SwingFXUtils.toFXImage(imgFromDb, null);
//	            my_image.setImage(image);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}



        }
    }
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//    }
//public void setItem(String name1,String price1,String image1 ) {
//        name.setText(name1);
//        price.setText(price1);
//        Image wayo = new Image(image1);
//        my_image.setImage(wayo);
//
//
//}
    @FXML
    public void setMy_image(MouseEvent event) {


         Stage primaryStage =new Stage();
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(Main.class.getResource("item1.fxml"));
         StackPane mainLayout;
		try {
			mainLayout = loader.load();
	        Item1 cvc = loader.getController(); // This did the "trick"

	         cvc.setItem(_item); // Passing the client-object to the ClientViewController

	         Scene scene = new Scene(mainLayout, 400, 400);
	         primaryStage.setScene(scene);
	         primaryStage.setResizable(true);
	         primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

 


         //AlertBox.display("Alert!","Do you want to add this item to cart ?");


        return;
    }
}