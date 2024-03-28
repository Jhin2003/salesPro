package utils;

import javax.swing.*;
import java.awt.*;

public class GetScreenWidthAndHeight {
    int width;
    int height;

    public GetScreenWidthAndHeight(Component component){
        width = component.getWidth();
        height = component.getHeight();
    }

  public void ShowScreenWidthAndHeight(){
      System.out.println("Screen width is " + width);
      System.out.println("Screen height is " + height);
  }

}
