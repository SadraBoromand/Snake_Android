B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=9.3
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: false
#End Region

Sub Process_Globals

End Sub

Sub Globals
	

	Private lbl_Score As Label
	Private lbl_Timer As Label
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("Game_Voer")
	
	lbl_Score.Text = Game.Score ' For Show Score
	lbl_Timer.Text = Game.Timer ' For Show Timer
	Game.Score = 0
	Game.Timer = 0

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Activity.Finish
End Sub


Sub btn_restart_Click
	StartActivity(Game)
End Sub

Sub btn_menu_Click
	StartActivity(Main)
End Sub