B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=9.3
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: False
#End Region

Sub Process_Globals
	
	Dim Score As Int ' For lbl_Score
	Dim Timer As Int ' For lbl_Timer
	Dim Speed_Snake As Int=200 ' For Speed_Snake

End Sub

Sub Globals
	

	Private body1 As Panel
	Private body2 As Panel
	Private head As Panel
	Private Panel As Panel ' Panel Plan
	
	Dim X,Y As Int ' For Width and Hight
	Dim Location As Char ' For char of Location
	
	Dim Snake(85) As Panel ' The Snake
	Dim count,i As Int ' count snake And i For Counter
	Dim T1,T2 As Timer ' timer game
	Dim copy_count As Int ' count - 1
	
	Private Apple As Panel ' Panel Apple
	
	Dim P As Panel ' For Add New Snake
	Private lbl_Score As Label ' For Score
	Private lbl_Timer As Label ' For Timer
	Dim TG As Int ' Temp Timer
	
	Dim j As Int ' For Game Over
	
	
 End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("Layout_Game")	
	
	' for width and height
	X = 60
	Y = 60
	
	' FOR TIMERS
	T1.Initialize("T1",Speed_Snake)
	T2.Initialize("T2",1000)

	' FOR LOCATIONS
	head.Left = 5*X
	head.Top = 2*Y
	
	body1.Left = 3*X
	body1.Top = 2*Y
	
	body2.Left = 1*X
	body2.Top = 2*Y
	
	Apple.Left = 7*X
	Apple.Top = 2*Y
	
	' For Snake Size
	count = 2
	
	' for snake
	Snake(0) = head
	Snake(1) = body1
	Snake(2) = body2
	
	
	
	
End Sub

' For Timer Game -----------------------------------
Sub T2_Tick
	
	TG = TG + 1
	lbl_Timer.Text = TG
	Timer = TG
		
End Sub

' For Timer Game -----------------------------------
Sub T1_Tick
	
	
	Move
	Eat_apple
	Game_Over
	Victory
	
	
End Sub

' For Victory
Sub Victory()
	If Score = 80 Then
		Dim Res As Int
		Res = Msgbox2("سازنده : محمد صدرا برومند","شما برنده شدید","بله","","",LoadBitmap(File.DirAssets,"icon.png"))
		If Res = DialogResponse.POSITIVE Then
			ToastMessageShow("Developer : Muhammad Sadra Burommad",False)
			Activity.Finish
		End If
	End If
End Sub

' For Move Snake -----------------------------------
Sub Move()
	
	For i=count To 1 Step -1
		Snake(i).Left = Snake(i-1).Left
		Snake(i).Top = Snake(i-1).Top
	Next
	
	' For Move Right -----------------------------------
	If Location = "R" Then
		If head.Left + X <> Snake(2).Left Then
			
			If head.Left >= Panel.Width Then
				head.Left = Panel.Left
			Else
				head.Left = head.Left + X
			End If
		Else
			Location = "L"
			head.Left = head.Left - X
		End If
		
	' For Move Left -----------------------------------
	Else If Location = "L" Then
		If head.Left - X <> Snake(2).Left Then
			If Snake(0).Left <= Panel.Left Then
				Snake(0).Left = Panel.Width
			Else
				head.Left = head.Left - X
			End If
		Else
			Location = "R"
			head.Left = head.Left + X
		End If
		
	' For Move Up -----------------------------------
	Else If Location = "U" Then
		If head.Top - Y <> Snake(2).Top Then
			
			If head.Top <= Panel.Top Then
				head.Top = Panel.Height
			Else
				head.Top = head.Top - Y
			End If
		Else
			Location = "D"
			head.Top = head.Top + Y
		End If
		
	' For Move Down -----------------------------------
	Else If Location = "D" Then
		If head.Top + Y <> Snake(2).Top Then

			If head.Top >= Panel.Height Then
				head.Top = Panel.Top
			Else
				head.Top = head.Top + Y
			End If
		Else
			Location = "U"
			head.Top = head.Top - Y
		End If
	End If
	
End Sub

' For Game Over -----------------------------------
Sub Game_Over()
	
	For i = count To 1 Step -1
		If head.Left == Snake(i).Left And head.Top == Snake(i).Top Then
			T1.Enabled = False
			T2.Enabled = False
			StartActivity(Layout_Game_Over)
		End If
	Next
	
End Sub

' For Eat Apple -----------------------------------
Sub Eat_apple()
	
	If head.Left - 5 <= Apple.Left And Apple.Left <= head.Left + 5 Then
		If head.Top - 5 <= Apple.Top And Apple.Top <= head.Top + 5 Then
			count = count + 1
			Score = Score + 1
			lbl_Score.Text = Score
			
			Snake(count).Initialize(Snake(count))
			Snake(count).SetBackgroundImage(LoadBitmap(File.DirAssets,"body.png"))
			
			Panel.AddView(Snake(count),Snake(count-1).Left,Snake(count-1).Top,30dip,30dip)

			Apple.Left = Rnd(2,(Panel.Width/X)-2)*X
			Apple.Top = Rnd(2,(Panel.Height/Y)-2)*Y

		End If
	End If
	
End Sub

Sub Activity_Resume
	
End Sub

' For Restart Game
Sub Activity_Pause (UserClosed As Boolean)
	Activity.Finish
End Sub

' For Enable Timer Game First Time
Sub Enable_T1()
	If T1.Enabled = False Then
		T1.Enabled = True
		T2.Enabled = True
	End If
End Sub

Sub btn_up_Click
	Location = "U"
	Enable_T1
End Sub

Sub btn_right_Click
	Location = "R"
	Enable_T1
End Sub

Sub btn_left_Click
	Location = "L"
	Enable_T1
End Sub

Sub btn_down_Click
	Location = "D"
	Enable_T1
End Sub

Sub btn_Menu_Click
	StartActivity(Main)
	T1.Enabled = False
	T2.Enabled = False
	Timer = 0
End Sub

' For Stop Game
Sub btn_Stop_Game_Click
	T1.Enabled = False
	T2.Enabled = False
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		Activity.Finish
		T1.Enabled = False
		T2.Enabled = False
		Timer = 0
	End If
End Sub

