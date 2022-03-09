package com.snake.sadragame.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_game_voer{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("label1").vw.setLeft((int)((55d / 100 * width)));
views.get("label1").vw.setWidth((int)((95d / 100 * width) - ((55d / 100 * width))));
//BA.debugLineNum = 5;BA.debugLine="Label1.SetTopAndBottom(10%y,30%y)"[Game_Voer/General script]
views.get("label1").vw.setTop((int)((10d / 100 * height)));
views.get("label1").vw.setHeight((int)((30d / 100 * height) - ((10d / 100 * height))));
//BA.debugLineNum = 7;BA.debugLine="Label2.SetLeftAndRight(70%x,98%x)"[Game_Voer/General script]
views.get("label2").vw.setLeft((int)((70d / 100 * width)));
views.get("label2").vw.setWidth((int)((98d / 100 * width) - ((70d / 100 * width))));
//BA.debugLineNum = 8;BA.debugLine="Label2.SetTopAndBottom(40%y,55%y)"[Game_Voer/General script]
views.get("label2").vw.setTop((int)((40d / 100 * height)));
views.get("label2").vw.setHeight((int)((55d / 100 * height) - ((40d / 100 * height))));
//BA.debugLineNum = 10;BA.debugLine="Label3.SetLeftAndRight(70%x,98%x)"[Game_Voer/General script]
views.get("label3").vw.setLeft((int)((70d / 100 * width)));
views.get("label3").vw.setWidth((int)((98d / 100 * width) - ((70d / 100 * width))));
//BA.debugLineNum = 11;BA.debugLine="Label3.SetTopAndBottom(60%y,75%y)"[Game_Voer/General script]
views.get("label3").vw.setTop((int)((60d / 100 * height)));
views.get("label3").vw.setHeight((int)((75d / 100 * height) - ((60d / 100 * height))));
//BA.debugLineNum = 13;BA.debugLine="lbl_Score.SetLeftAndRight(55%x,70%x)"[Game_Voer/General script]
views.get("lbl_score").vw.setLeft((int)((55d / 100 * width)));
views.get("lbl_score").vw.setWidth((int)((70d / 100 * width) - ((55d / 100 * width))));
//BA.debugLineNum = 14;BA.debugLine="lbl_Score.SetTopAndBottom(40%y,55%y)"[Game_Voer/General script]
views.get("lbl_score").vw.setTop((int)((40d / 100 * height)));
views.get("lbl_score").vw.setHeight((int)((55d / 100 * height) - ((40d / 100 * height))));
//BA.debugLineNum = 16;BA.debugLine="lbl_Timer.SetLeftAndRight(55%x,70%x)"[Game_Voer/General script]
views.get("lbl_timer").vw.setLeft((int)((55d / 100 * width)));
views.get("lbl_timer").vw.setWidth((int)((70d / 100 * width) - ((55d / 100 * width))));
//BA.debugLineNum = 17;BA.debugLine="lbl_Timer.SetTopAndBottom(60%y,75%y)"[Game_Voer/General script]
views.get("lbl_timer").vw.setTop((int)((60d / 100 * height)));
views.get("lbl_timer").vw.setHeight((int)((75d / 100 * height) - ((60d / 100 * height))));
//BA.debugLineNum = 19;BA.debugLine="btn_restart.SetLeftAndRight(10%x,35%x)"[Game_Voer/General script]
views.get("btn_restart").vw.setLeft((int)((10d / 100 * width)));
views.get("btn_restart").vw.setWidth((int)((35d / 100 * width) - ((10d / 100 * width))));
//BA.debugLineNum = 20;BA.debugLine="btn_restart.SetTopAndBottom(40%y,60%y)"[Game_Voer/General script]
views.get("btn_restart").vw.setTop((int)((40d / 100 * height)));
views.get("btn_restart").vw.setHeight((int)((60d / 100 * height) - ((40d / 100 * height))));
//BA.debugLineNum = 22;BA.debugLine="btn_menu.SetLeftAndRight(10%x,35%x)"[Game_Voer/General script]
views.get("btn_menu").vw.setLeft((int)((10d / 100 * width)));
views.get("btn_menu").vw.setWidth((int)((35d / 100 * width) - ((10d / 100 * width))));
//BA.debugLineNum = 23;BA.debugLine="btn_menu.SetTopAndBottom(70%y,90%y)"[Game_Voer/General script]
views.get("btn_menu").vw.setTop((int)((70d / 100 * height)));
views.get("btn_menu").vw.setHeight((int)((90d / 100 * height) - ((70d / 100 * height))));

}
}