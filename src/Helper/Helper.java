package Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void setLayout(){
        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(info.getName())){
                try{
                    UIManager.setLookAndFeel(info.getClassName());
                }catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    public static int screenCenter(String point, Dimension size){
        if(point.equals("x"))
            return (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
        else if(point.equals("y"))
            return (Toolkit.getDefaultToolkit().getScreenSize().height- size.height)/ 2;
        return 0;
    }

    public static boolean isEmpty(JTextField field) {
        if (field.getText().isEmpty())
            return true;
        return false;
    }

    public static void showMsg(String message) {
        String title;

        switch (message){
            case "fill":
                message = "Please fill all area!";
                title = "Error";
                break;
            case "included":
                message = "This number already in list!";
                title = "Check number";
                break;
            case "nInList":
                message = "This number not in list!";
                title = "Check number";
                break;
            case "done":
                message = "Your operation has done successfully!";
                title = "Done!";
                break;
            default:
                title = "Some Errors";
        }

        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);

    }

    public static boolean confirm(String text) {
        switch (text){
            case "sure":
                text = "Do you want to do this? ";
                break;
        }
        return JOptionPane.showConfirmDialog(null,text,"Confirm",JOptionPane.YES_NO_OPTION) == 0;
    }
}
