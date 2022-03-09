package com.snake.sadragame.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_over_low{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("close").vw.setLeft((int)((5d / 100 * width)));
views.get("close").vw.setWidth((int)((25d / 100 * width) - ((5d / 100 * width))));
views.get("close").vw.setTop((int)((80d / 100 * height)));
views.get("close").vw.setHeight((int)((95d / 100 * height) - ((80d / 100 * height))));

}
}