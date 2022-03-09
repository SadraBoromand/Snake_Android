package com.snake.sadragame.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_layout_setting{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel").vw.setLeft((int)((20d / 100 * width)));
views.get("panel").vw.setWidth((int)((80d / 100 * width) - ((20d / 100 * width))));
views.get("panel").vw.setTop((int)((15d / 100 * height)));
views.get("panel").vw.setHeight((int)((85d / 100 * height) - ((15d / 100 * height))));
views.get("label3").vw.setLeft((int)((15d / 100 * width)));
views.get("label3").vw.setWidth((int)((45d / 100 * width) - ((15d / 100 * width))));
views.get("label3").vw.setTop((int)((0d / 100 * height)));
views.get("label3").vw.setHeight((int)((15d / 100 * height) - ((0d / 100 * height))));
views.get("label2").vw.setLeft((int)((15d / 100 * width)));
views.get("label2").vw.setWidth((int)((45d / 100 * width) - ((15d / 100 * width))));
views.get("label2").vw.setTop((int)((15d / 100 * height)));
views.get("label2").vw.setHeight((int)((30d / 100 * height) - ((15d / 100 * height))));
views.get("seekbar_speed").vw.setLeft((int)((0d / 100 * width)));
views.get("seekbar_speed").vw.setWidth((int)((40d / 100 * width) - ((0d / 100 * width))));
views.get("seekbar_speed").vw.setTop((int)((30d / 100 * height)));
views.get("seekbar_speed").vw.setHeight((int)((45d / 100 * height) - ((30d / 100 * height))));
views.get("label1").vw.setLeft((int)((40d / 100 * width)));
views.get("label1").vw.setWidth((int)((60d / 100 * width) - ((40d / 100 * width))));
views.get("label1").vw.setTop((int)((30d / 100 * height)));
views.get("label1").vw.setHeight((int)((45d / 100 * height) - ((30d / 100 * height))));
views.get("start_game").vw.setLeft((int)((15d / 100 * width)));
views.get("start_game").vw.setWidth((int)((45d / 100 * width) - ((15d / 100 * width))));
views.get("start_game").vw.setTop((int)((50d / 100 * height)));
views.get("start_game").vw.setHeight((int)((65d / 100 * height) - ((50d / 100 * height))));

}
}