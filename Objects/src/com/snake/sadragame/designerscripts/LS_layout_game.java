package com.snake.sadragame.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_layout_game{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("panel").vw.setLeft((int)((0d / 100 * width)));
views.get("panel").vw.setWidth((int)((60d / 100 * width) - ((0d / 100 * width))));
views.get("panel").vw.setTop((int)((0d / 100 * height)));
views.get("panel").vw.setHeight((int)((100d / 100 * height) - ((0d / 100 * height))));
views.get("btn_up").vw.setLeft((int)((74d / 100 * width)));
views.get("btn_up").vw.setWidth((int)((84d / 100 * width) - ((74d / 100 * width))));
views.get("btn_up").vw.setTop((int)((16d / 100 * height)));
views.get("btn_up").vw.setHeight((int)((31d / 100 * height) - ((16d / 100 * height))));
views.get("btn_down").vw.setLeft((int)((74d / 100 * width)));
views.get("btn_down").vw.setWidth((int)((84d / 100 * width) - ((74d / 100 * width))));
views.get("btn_down").vw.setTop((int)((49d / 100 * height)));
views.get("btn_down").vw.setHeight((int)((64d / 100 * height) - ((49d / 100 * height))));
views.get("btn_left").vw.setLeft((int)((65d / 100 * width)));
views.get("btn_left").vw.setWidth((int)((75d / 100 * width) - ((65d / 100 * width))));
views.get("btn_left").vw.setTop((int)((32d / 100 * height)));
views.get("btn_left").vw.setHeight((int)((47d / 100 * height) - ((32d / 100 * height))));
views.get("btn_right").vw.setLeft((int)((83d / 100 * width)));
views.get("btn_right").vw.setWidth((int)((93d / 100 * width) - ((83d / 100 * width))));
views.get("btn_right").vw.setTop((int)((32d / 100 * height)));
views.get("btn_right").vw.setHeight((int)((47d / 100 * height) - ((32d / 100 * height))));
views.get("label1").vw.setLeft((int)((80d / 100 * width)));
views.get("label1").vw.setWidth((int)((100d / 100 * width) - ((80d / 100 * width))));
views.get("label1").vw.setTop((int)((2d / 100 * height)));
views.get("label1").vw.setHeight((int)((12d / 100 * height) - ((2d / 100 * height))));
views.get("lbl_score").vw.setLeft((int)((60d / 100 * width)));
views.get("lbl_score").vw.setWidth((int)((80d / 100 * width) - ((60d / 100 * width))));
views.get("lbl_score").vw.setTop((int)((2d / 100 * height)));
views.get("lbl_score").vw.setHeight((int)((12d / 100 * height) - ((2d / 100 * height))));
//BA.debugLineNum = 22;BA.debugLine="btn_Menu.SetLeftAndRight(80%x,95%x)"[Layout_Game/General script]
views.get("btn_menu").vw.setLeft((int)((80d / 100 * width)));
views.get("btn_menu").vw.setWidth((int)((95d / 100 * width) - ((80d / 100 * width))));
//BA.debugLineNum = 23;BA.debugLine="btn_Menu.SetTopAndBottom(73%y,88%y)"[Layout_Game/General script]
views.get("btn_menu").vw.setTop((int)((73d / 100 * height)));
views.get("btn_menu").vw.setHeight((int)((88d / 100 * height) - ((73d / 100 * height))));
//BA.debugLineNum = 25;BA.debugLine="btn_Stop_Game.SetLeftAndRight(64%x,78%x)"[Layout_Game/General script]
views.get("btn_stop_game").vw.setLeft((int)((64d / 100 * width)));
views.get("btn_stop_game").vw.setWidth((int)((78d / 100 * width) - ((64d / 100 * width))));
//BA.debugLineNum = 26;BA.debugLine="btn_Stop_Game.SetTopAndBottom(73%y,88%y)"[Layout_Game/General script]
views.get("btn_stop_game").vw.setTop((int)((73d / 100 * height)));
views.get("btn_stop_game").vw.setHeight((int)((88d / 100 * height) - ((73d / 100 * height))));
//BA.debugLineNum = 28;BA.debugLine="Label3.SetLeftAndRight(80%x,100%x)"[Layout_Game/General script]
views.get("label3").vw.setLeft((int)((80d / 100 * width)));
views.get("label3").vw.setWidth((int)((100d / 100 * width) - ((80d / 100 * width))));
//BA.debugLineNum = 29;BA.debugLine="Label3.SetTopAndBottom(88%y,98%y)"[Layout_Game/General script]
views.get("label3").vw.setTop((int)((88d / 100 * height)));
views.get("label3").vw.setHeight((int)((98d / 100 * height) - ((88d / 100 * height))));
//BA.debugLineNum = 31;BA.debugLine="lbl_Timer.SetLeftAndRight(60%x,80%x)"[Layout_Game/General script]
views.get("lbl_timer").vw.setLeft((int)((60d / 100 * width)));
views.get("lbl_timer").vw.setWidth((int)((80d / 100 * width) - ((60d / 100 * width))));
//BA.debugLineNum = 32;BA.debugLine="lbl_Timer.SetTopAndBottom(88%y,98%y)"[Layout_Game/General script]
views.get("lbl_timer").vw.setTop((int)((88d / 100 * height)));
views.get("lbl_timer").vw.setHeight((int)((98d / 100 * height) - ((88d / 100 * height))));

}
}