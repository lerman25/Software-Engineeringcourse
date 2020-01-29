package server;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import common.Commands;
import common.Item;
import common.Massage;

public class ItemController {

    private ItemGUI item;
   @FXML
   TextField name;
   @FXML
    TextField price;
   @FXML
    ImageView my_image;




    public void setItem(Item item) {


        if (item == null) {
            name.setText(null);
            price.setText(null);
            my_image.setImage(null);
        } else {
            name.setText(item.getName());
            price.setText(Double.toString(item.getPrice()));
            Massage m = new Massage(item,Commands.GETIMAGE);
            server.Main.send_toServer(m);
            m = server.Main.get_from_server();

			byte[] immAsBytes = (byte[])m.getObject();
			InputStream in = new ByteArrayInputStream(immAsBytes);
			BufferedImage imgFromDb;
			try {
				imgFromDb = ImageIO.read(in);
				BufferedImage resized = new BufferedImage(50, 50, imgFromDb.getType());
				Graphics2D g = resized.createGraphics();
				g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g.drawImage(imgFromDb, 0, 0,50, 50, 0, 0, imgFromDb.getWidth(),
						imgFromDb.getHeight(), null);
				g.dispose();
	            Image image = SwingFXUtils.toFXImage(imgFromDb, null);
	            my_image.setImage(image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



        }
    }
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//    }
public void setItem(String name1,String price1,String image1 ) {
        name.setText(name1);
        price.setText(price1);
        Image wayo = new Image(image1);
        my_image.setImage(wayo);


}
    @FXML
    public void setMy_image(MouseEvent event) {

        AlertBox.display("Alert!","Do you want to add this item to cart ?");
       return;
    }
}
