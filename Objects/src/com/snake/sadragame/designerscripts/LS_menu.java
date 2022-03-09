package com.snake.sadragame.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_menu{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("lbl_title").vw.setLeft((int)((30d / 100 * width)));
views.get("lbl_title").vw.setWidth((int)((70d / 100 * width) - ((30d / 100 * width))));
views.get("lbl_title").vw.setTop((int)((2d / 100 * height)));
views.get("lbl_title").vw.setHeight((int)((25d / 100 * height) - ((2d / 100 * height))));
views.get("img_s").vw.setLeft((int)((35d / 100 * width)));
views.get("img_s").vw.setWidth((int)((65d / 100 * width) - ((35d / 100 * width))));
views.get("img_s").vw.setTop((int)((25d / 100 * height)));
views.get("img_s").vw.setHeight((int)((45d / 100 * height) - ((25d / 100 * height))));
views.get("btn_start_game").vw.setLeft((int)((30d / 100 * width)));
views.get("btn_start_game").vw.setWidth((int)((70d / 100 * width) - ((30d / 100 * width))));
views.get("btn_start_game").vw.setTop((int)((60d / 100 * height)));
views.get("btn_start_game").vw.setHeight((int)((80d / 100 * height) - ((60d / 100 * height))));
views.get("help").vw.setLeft((int)((5d / 100 * width)));
views.get("help").vw.setWidth((int)((20d / 100 * width) - ((5d / 100 * width))));
views.get("help").vw.setTop((int)((15d / 100 * height)));
views.get("help").vw.setHeight((int)((35d / 100 * height) - ((15d / 100 * height))));
views.get("btn_exit").vw.setLeft((int)((5d / 100 * width)));
views.get("btn_exit").vw.setWidth((int)((20d / 100 * width) - ((5d / 100 * width))));
views.get("btn_exit").vw.setTop((int)((70d / 100 * height)));
views.get("btn_exit").vw.setHeight((int)((90d / 100 * height) - ((70d / 100 * height))));
views.get("about").vw.setLeft((int)((80d / 100 * width)));
views.get("about").vw.setWidth((int)((95d / 100 * width) - ((80d / 100 * width))));
views.get("about").vw.setTop((int)((15d / 100 * height)));
views.get("about").vw.setHeight((int)((35d / 100 * height) - ((15d / 100 * height))));
views.get("dev").vw.setLeft((int)((20d / 100 * width)));
views.get("dev").vw.setWidth((int)((80d / 100 * width) - ((20d / 100 * width))));
views.get("dev").vw.setTop((int)((90d / 100 * height)));
views.get("dev").vw.setHeight((int)((98d / 100 * height) - ((90d / 100 * height))));

}
}