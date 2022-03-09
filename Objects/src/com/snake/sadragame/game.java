package com.snake.sadragame;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class game extends Activity implements B4AActivity{
	public static game mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "com.snake.sadragame", "com.snake.sadragame.game");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (game).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "com.snake.sadragame", "com.snake.sadragame.game");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.snake.sadragame.game", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (game) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (game) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return game.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (game) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            game mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (game) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static int _score = 0;
public static int _timer = 0;
public static int _speed_snake = 0;
public anywheresoftware.b4a.objects.PanelWrapper _body1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _body2 = null;
public anywheresoftware.b4a.objects.PanelWrapper _head = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel = null;
public static int _x = 0;
public static int _y = 0;
public static char _location = '\0';
public anywheresoftware.b4a.objects.PanelWrapper[] _snake = null;
public static int _count = 0;
public static int _i = 0;
public anywheresoftware.b4a.objects.Timer _t1 = null;
public anywheresoftware.b4a.objects.Timer _t2 = null;
public static int _copy_count = 0;
public anywheresoftware.b4a.objects.PanelWrapper _apple = null;
public anywheresoftware.b4a.objects.PanelWrapper _p = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_score = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_timer = null;
public static int _tg = 0;
public static int _j = 0;
public com.snake.sadragame.main _main = null;
public com.snake.sadragame.activity_help _activity_help = null;
public com.snake.sadragame.layout_about _layout_about = null;
public com.snake.sadragame.layout_game_over _layout_game_over = null;
public com.snake.sadragame.starter _starter = null;
public com.snake.sadragame.active_setting _active_setting = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 42;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 43;BA.debugLine="Activity.LoadLayout(\"Layout_Game\")";
mostCurrent._activity.LoadLayout("Layout_Game",mostCurrent.activityBA);
 //BA.debugLineNum = 46;BA.debugLine="X = 60";
_x = (int) (60);
 //BA.debugLineNum = 47;BA.debugLine="Y = 60";
_y = (int) (60);
 //BA.debugLineNum = 50;BA.debugLine="T1.Initialize(\"T1\",Speed_Snake)";
mostCurrent._t1.Initialize(processBA,"T1",(long) (_speed_snake));
 //BA.debugLineNum = 51;BA.debugLine="T2.Initialize(\"T2\",1000)";
mostCurrent._t2.Initialize(processBA,"T2",(long) (1000));
 //BA.debugLineNum = 54;BA.debugLine="head.Left = 5*X";
mostCurrent._head.setLeft((int) (5*_x));
 //BA.debugLineNum = 55;BA.debugLine="head.Top = 2*Y";
mostCurrent._head.setTop((int) (2*_y));
 //BA.debugLineNum = 57;BA.debugLine="body1.Left = 3*X";
mostCurrent._body1.setLeft((int) (3*_x));
 //BA.debugLineNum = 58;BA.debugLine="body1.Top = 2*Y";
mostCurrent._body1.setTop((int) (2*_y));
 //BA.debugLineNum = 60;BA.debugLine="body2.Left = 1*X";
mostCurrent._body2.setLeft((int) (1*_x));
 //BA.debugLineNum = 61;BA.debugLine="body2.Top = 2*Y";
mostCurrent._body2.setTop((int) (2*_y));
 //BA.debugLineNum = 63;BA.debugLine="Apple.Left = 7*X";
mostCurrent._apple.setLeft((int) (7*_x));
 //BA.debugLineNum = 64;BA.debugLine="Apple.Top = 2*Y";
mostCurrent._apple.setTop((int) (2*_y));
 //BA.debugLineNum = 67;BA.debugLine="count = 2";
_count = (int) (2);
 //BA.debugLineNum = 70;BA.debugLine="Snake(0) = head";
mostCurrent._snake[(int) (0)] = mostCurrent._head;
 //BA.debugLineNum = 71;BA.debugLine="Snake(1) = body1";
mostCurrent._snake[(int) (1)] = mostCurrent._body1;
 //BA.debugLineNum = 72;BA.debugLine="Snake(2) = body2";
mostCurrent._snake[(int) (2)] = mostCurrent._body2;
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 263;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 264;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 265;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 266;BA.debugLine="T1.Enabled = False";
mostCurrent._t1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 267;BA.debugLine="T2.Enabled = False";
mostCurrent._t2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 268;BA.debugLine="Timer = 0";
_timer = (int) (0);
 };
 //BA.debugLineNum = 270;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 218;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 219;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 220;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 213;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 215;BA.debugLine="End Sub";
return "";
}
public static String  _btn_down_click() throws Exception{
 //BA.debugLineNum = 245;BA.debugLine="Sub btn_down_Click";
 //BA.debugLineNum = 246;BA.debugLine="Location = \"D\"";
_location = BA.ObjectToChar("D");
 //BA.debugLineNum = 247;BA.debugLine="Enable_T1";
_enable_t1();
 //BA.debugLineNum = 248;BA.debugLine="End Sub";
return "";
}
public static String  _btn_left_click() throws Exception{
 //BA.debugLineNum = 240;BA.debugLine="Sub btn_left_Click";
 //BA.debugLineNum = 241;BA.debugLine="Location = \"L\"";
_location = BA.ObjectToChar("L");
 //BA.debugLineNum = 242;BA.debugLine="Enable_T1";
_enable_t1();
 //BA.debugLineNum = 243;BA.debugLine="End Sub";
return "";
}
public static String  _btn_menu_click() throws Exception{
 //BA.debugLineNum = 250;BA.debugLine="Sub btn_Menu_Click";
 //BA.debugLineNum = 251;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 252;BA.debugLine="T1.Enabled = False";
mostCurrent._t1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 253;BA.debugLine="T2.Enabled = False";
mostCurrent._t2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 254;BA.debugLine="Timer = 0";
_timer = (int) (0);
 //BA.debugLineNum = 255;BA.debugLine="End Sub";
return "";
}
public static String  _btn_right_click() throws Exception{
 //BA.debugLineNum = 235;BA.debugLine="Sub btn_right_Click";
 //BA.debugLineNum = 236;BA.debugLine="Location = \"R\"";
_location = BA.ObjectToChar("R");
 //BA.debugLineNum = 237;BA.debugLine="Enable_T1";
_enable_t1();
 //BA.debugLineNum = 238;BA.debugLine="End Sub";
return "";
}
public static String  _btn_stop_game_click() throws Exception{
 //BA.debugLineNum = 258;BA.debugLine="Sub btn_Stop_Game_Click";
 //BA.debugLineNum = 259;BA.debugLine="T1.Enabled = False";
mostCurrent._t1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 260;BA.debugLine="T2.Enabled = False";
mostCurrent._t2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 261;BA.debugLine="End Sub";
return "";
}
public static String  _btn_up_click() throws Exception{
 //BA.debugLineNum = 230;BA.debugLine="Sub btn_up_Click";
 //BA.debugLineNum = 231;BA.debugLine="Location = \"U\"";
_location = BA.ObjectToChar("U");
 //BA.debugLineNum = 232;BA.debugLine="Enable_T1";
_enable_t1();
 //BA.debugLineNum = 233;BA.debugLine="End Sub";
return "";
}
public static String  _eat_apple() throws Exception{
 //BA.debugLineNum = 192;BA.debugLine="Sub Eat_apple()";
 //BA.debugLineNum = 194;BA.debugLine="If head.Left - 5 <= Apple.Left And Apple.Left <=";
if (mostCurrent._head.getLeft()-5<=mostCurrent._apple.getLeft() && mostCurrent._apple.getLeft()<=mostCurrent._head.getLeft()+5) { 
 //BA.debugLineNum = 195;BA.debugLine="If head.Top - 5 <= Apple.Top And Apple.Top <= he";
if (mostCurrent._head.getTop()-5<=mostCurrent._apple.getTop() && mostCurrent._apple.getTop()<=mostCurrent._head.getTop()+5) { 
 //BA.debugLineNum = 196;BA.debugLine="count = count + 1";
_count = (int) (_count+1);
 //BA.debugLineNum = 197;BA.debugLine="Score = Score + 1";
_score = (int) (_score+1);
 //BA.debugLineNum = 198;BA.debugLine="lbl_Score.Text = Score";
mostCurrent._lbl_score.setText(BA.ObjectToCharSequence(_score));
 //BA.debugLineNum = 200;BA.debugLine="Snake(count).Initialize(Snake(count))";
mostCurrent._snake[_count].Initialize(mostCurrent.activityBA,BA.ObjectToString(mostCurrent._snake[_count]));
 //BA.debugLineNum = 201;BA.debugLine="Snake(count).SetBackgroundImage(LoadBitmap(File";
mostCurrent._snake[_count].SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"body.png").getObject()));
 //BA.debugLineNum = 203;BA.debugLine="Panel.AddView(Snake(count),Snake(count-1).Left,";
mostCurrent._panel.AddView((android.view.View)(mostCurrent._snake[_count].getObject()),mostCurrent._snake[(int) (_count-1)].getLeft(),mostCurrent._snake[(int) (_count-1)].getTop(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 205;BA.debugLine="Apple.Left = Rnd(2,(Panel.Width/X)-2)*X";
mostCurrent._apple.setLeft((int) (anywheresoftware.b4a.keywords.Common.Rnd((int) (2),(int) ((mostCurrent._panel.getWidth()/(double)_x)-2))*_x));
 //BA.debugLineNum = 206;BA.debugLine="Apple.Top = Rnd(2,(Panel.Height/Y)-2)*Y";
mostCurrent._apple.setTop((int) (anywheresoftware.b4a.keywords.Common.Rnd((int) (2),(int) ((mostCurrent._panel.getHeight()/(double)_y)-2))*_y));
 };
 };
 //BA.debugLineNum = 211;BA.debugLine="End Sub";
return "";
}
public static String  _enable_t1() throws Exception{
 //BA.debugLineNum = 223;BA.debugLine="Sub Enable_T1()";
 //BA.debugLineNum = 224;BA.debugLine="If T1.Enabled = False Then";
if (mostCurrent._t1.getEnabled()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 225;BA.debugLine="T1.Enabled = True";
mostCurrent._t1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 226;BA.debugLine="T2.Enabled = True";
mostCurrent._t2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 228;BA.debugLine="End Sub";
return "";
}
public static String  _game_over() throws Exception{
 //BA.debugLineNum = 179;BA.debugLine="Sub Game_Over()";
 //BA.debugLineNum = 181;BA.debugLine="For i = count To 1 Step -1";
{
final int step1 = -1;
final int limit1 = (int) (1);
_i = _count ;
for (;_i >= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 182;BA.debugLine="If head.Left == Snake(i).Left And head.Top == Sn";
if (mostCurrent._head.getLeft()==mostCurrent._snake[_i].getLeft() && mostCurrent._head.getTop()==mostCurrent._snake[_i].getTop()) { 
 //BA.debugLineNum = 183;BA.debugLine="T1.Enabled = False";
mostCurrent._t1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 184;BA.debugLine="T2.Enabled = False";
mostCurrent._t2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 185;BA.debugLine="StartActivity(Layout_Game_Over)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._layout_game_over.getObject()));
 };
 }
};
 //BA.debugLineNum = 189;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 17;BA.debugLine="Private body1 As Panel";
mostCurrent._body1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private body2 As Panel";
mostCurrent._body2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private head As Panel";
mostCurrent._head = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private Panel As Panel ' Panel Plan";
mostCurrent._panel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Dim X,Y As Int ' For Width and Hight";
_x = 0;
_y = 0;
 //BA.debugLineNum = 23;BA.debugLine="Dim Location As Char ' For char of Location";
_location = '\0';
 //BA.debugLineNum = 25;BA.debugLine="Dim Snake(85) As Panel ' The Snake";
mostCurrent._snake = new anywheresoftware.b4a.objects.PanelWrapper[(int) (85)];
{
int d0 = mostCurrent._snake.length;
for (int i0 = 0;i0 < d0;i0++) {
mostCurrent._snake[i0] = new anywheresoftware.b4a.objects.PanelWrapper();
}
}
;
 //BA.debugLineNum = 26;BA.debugLine="Dim count,i As Int ' count snake And i For Counte";
_count = 0;
_i = 0;
 //BA.debugLineNum = 27;BA.debugLine="Dim T1,T2 As Timer ' timer game";
mostCurrent._t1 = new anywheresoftware.b4a.objects.Timer();
mostCurrent._t2 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 28;BA.debugLine="Dim copy_count As Int ' count - 1";
_copy_count = 0;
 //BA.debugLineNum = 30;BA.debugLine="Private Apple As Panel ' Panel Apple";
mostCurrent._apple = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Dim P As Panel ' For Add New Snake";
mostCurrent._p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private lbl_Score As Label ' For Score";
mostCurrent._lbl_score = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private lbl_Timer As Label ' For Timer";
mostCurrent._lbl_timer = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Dim TG As Int ' Temp Timer";
_tg = 0;
 //BA.debugLineNum = 37;BA.debugLine="Dim j As Int ' For Game Over";
_j = 0;
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return "";
}
public static String  _move() throws Exception{
 //BA.debugLineNum = 113;BA.debugLine="Sub Move()";
 //BA.debugLineNum = 115;BA.debugLine="For i=count To 1 Step -1";
{
final int step1 = -1;
final int limit1 = (int) (1);
_i = _count ;
for (;_i >= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 116;BA.debugLine="Snake(i).Left = Snake(i-1).Left";
mostCurrent._snake[_i].setLeft(mostCurrent._snake[(int) (_i-1)].getLeft());
 //BA.debugLineNum = 117;BA.debugLine="Snake(i).Top = Snake(i-1).Top";
mostCurrent._snake[_i].setTop(mostCurrent._snake[(int) (_i-1)].getTop());
 }
};
 //BA.debugLineNum = 121;BA.debugLine="If Location = \"R\" Then";
if (_location==BA.ObjectToChar("R")) { 
 //BA.debugLineNum = 122;BA.debugLine="If head.Left + X <> Snake(2).Left Then";
if (mostCurrent._head.getLeft()+_x!=mostCurrent._snake[(int) (2)].getLeft()) { 
 //BA.debugLineNum = 124;BA.debugLine="If head.Left >= Panel.Width Then";
if (mostCurrent._head.getLeft()>=mostCurrent._panel.getWidth()) { 
 //BA.debugLineNum = 125;BA.debugLine="head.Left = Panel.Left";
mostCurrent._head.setLeft(mostCurrent._panel.getLeft());
 }else {
 //BA.debugLineNum = 127;BA.debugLine="head.Left = head.Left + X";
mostCurrent._head.setLeft((int) (mostCurrent._head.getLeft()+_x));
 };
 }else {
 //BA.debugLineNum = 130;BA.debugLine="Location = \"L\"";
_location = BA.ObjectToChar("L");
 //BA.debugLineNum = 131;BA.debugLine="head.Left = head.Left - X";
mostCurrent._head.setLeft((int) (mostCurrent._head.getLeft()-_x));
 };
 }else if(_location==BA.ObjectToChar("L")) { 
 //BA.debugLineNum = 136;BA.debugLine="If head.Left - X <> Snake(2).Left Then";
if (mostCurrent._head.getLeft()-_x!=mostCurrent._snake[(int) (2)].getLeft()) { 
 //BA.debugLineNum = 137;BA.debugLine="If Snake(0).Left <= Panel.Left Then";
if (mostCurrent._snake[(int) (0)].getLeft()<=mostCurrent._panel.getLeft()) { 
 //BA.debugLineNum = 138;BA.debugLine="Snake(0).Left = Panel.Width";
mostCurrent._snake[(int) (0)].setLeft(mostCurrent._panel.getWidth());
 }else {
 //BA.debugLineNum = 140;BA.debugLine="head.Left = head.Left - X";
mostCurrent._head.setLeft((int) (mostCurrent._head.getLeft()-_x));
 };
 }else {
 //BA.debugLineNum = 143;BA.debugLine="Location = \"R\"";
_location = BA.ObjectToChar("R");
 //BA.debugLineNum = 144;BA.debugLine="head.Left = head.Left + X";
mostCurrent._head.setLeft((int) (mostCurrent._head.getLeft()+_x));
 };
 }else if(_location==BA.ObjectToChar("U")) { 
 //BA.debugLineNum = 149;BA.debugLine="If head.Top - Y <> Snake(2).Top Then";
if (mostCurrent._head.getTop()-_y!=mostCurrent._snake[(int) (2)].getTop()) { 
 //BA.debugLineNum = 151;BA.debugLine="If head.Top <= Panel.Top Then";
if (mostCurrent._head.getTop()<=mostCurrent._panel.getTop()) { 
 //BA.debugLineNum = 152;BA.debugLine="head.Top = Panel.Height";
mostCurrent._head.setTop(mostCurrent._panel.getHeight());
 }else {
 //BA.debugLineNum = 154;BA.debugLine="head.Top = head.Top - Y";
mostCurrent._head.setTop((int) (mostCurrent._head.getTop()-_y));
 };
 }else {
 //BA.debugLineNum = 157;BA.debugLine="Location = \"D\"";
_location = BA.ObjectToChar("D");
 //BA.debugLineNum = 158;BA.debugLine="head.Top = head.Top + Y";
mostCurrent._head.setTop((int) (mostCurrent._head.getTop()+_y));
 };
 }else if(_location==BA.ObjectToChar("D")) { 
 //BA.debugLineNum = 163;BA.debugLine="If head.Top + Y <> Snake(2).Top Then";
if (mostCurrent._head.getTop()+_y!=mostCurrent._snake[(int) (2)].getTop()) { 
 //BA.debugLineNum = 165;BA.debugLine="If head.Top >= Panel.Height Then";
if (mostCurrent._head.getTop()>=mostCurrent._panel.getHeight()) { 
 //BA.debugLineNum = 166;BA.debugLine="head.Top = Panel.Top";
mostCurrent._head.setTop(mostCurrent._panel.getTop());
 }else {
 //BA.debugLineNum = 168;BA.debugLine="head.Top = head.Top + Y";
mostCurrent._head.setTop((int) (mostCurrent._head.getTop()+_y));
 };
 }else {
 //BA.debugLineNum = 171;BA.debugLine="Location = \"U\"";
_location = BA.ObjectToChar("U");
 //BA.debugLineNum = 172;BA.debugLine="head.Top = head.Top - Y";
mostCurrent._head.setTop((int) (mostCurrent._head.getTop()-_y));
 };
 };
 //BA.debugLineNum = 176;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Dim Score As Int ' For lbl_Score";
_score = 0;
 //BA.debugLineNum = 9;BA.debugLine="Dim Timer As Int ' For lbl_Timer";
_timer = 0;
 //BA.debugLineNum = 10;BA.debugLine="Dim Speed_Snake As Int=200 ' For Speed_Snake";
_speed_snake = (int) (200);
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static String  _t1_tick() throws Exception{
 //BA.debugLineNum = 89;BA.debugLine="Sub T1_Tick";
 //BA.debugLineNum = 92;BA.debugLine="Move";
_move();
 //BA.debugLineNum = 93;BA.debugLine="Eat_apple";
_eat_apple();
 //BA.debugLineNum = 94;BA.debugLine="Game_Over";
_game_over();
 //BA.debugLineNum = 95;BA.debugLine="Victory";
_victory();
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return "";
}
public static String  _t2_tick() throws Exception{
 //BA.debugLineNum = 80;BA.debugLine="Sub T2_Tick";
 //BA.debugLineNum = 82;BA.debugLine="TG = TG + 1";
_tg = (int) (_tg+1);
 //BA.debugLineNum = 83;BA.debugLine="lbl_Timer.Text = TG";
mostCurrent._lbl_timer.setText(BA.ObjectToCharSequence(_tg));
 //BA.debugLineNum = 84;BA.debugLine="Timer = TG";
_timer = _tg;
 //BA.debugLineNum = 86;BA.debugLine="End Sub";
return "";
}
public static String  _victory() throws Exception{
int _res = 0;
 //BA.debugLineNum = 101;BA.debugLine="Sub Victory()";
 //BA.debugLineNum = 102;BA.debugLine="If Score = 80 Then";
if (_score==80) { 
 //BA.debugLineNum = 103;BA.debugLine="Dim Res As Int";
_res = 0;
 //BA.debugLineNum = 104;BA.debugLine="Res = Msgbox2(\"سازنده : محمد صدرا برومند\",\"شما ب";
_res = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("سازنده : محمد صدرا برومند"),BA.ObjectToCharSequence("شما برنده شدید"),"بله","","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"icon.png").getObject()),mostCurrent.activityBA);
 //BA.debugLineNum = 105;BA.debugLine="If Res = DialogResponse.POSITIVE Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 106;BA.debugLine="ToastMessageShow(\"Developer : Muhammad Sadra Bu";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Developer : Muhammad Sadra Burommad"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 107;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 };
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
}
