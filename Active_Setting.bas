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
	

	Private Label1 As Label ' For Show Speed
	
	Private SeekBar_Speed As SeekBar
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("Layout_Setting")
	
	
	
End Sub

Sub Activity_Resume
	Game.Speed_Snake = 200
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Activity.Finish
End Sub


Sub Start_Game_Click
	
	StartActivity(Game)
End Sub

Sub SeekBar_Speed_ValueChanged (Value As Int, UserChanged As Boolean)
	If SeekBar_Speed.Value <= 66 Then
		SeekBar_Speed.Value = 65
		Game.Speed_Snake = 300
		Label1.Text = "آسان"
	Else if SeekBar_Speed.Value <= 133 Then
		SeekBar_Speed.Value = 130
		Game.Speed_Snake = 200
		Label1.Text = "معمولی"
	Else if SeekBar_Speed.Value >= 133 Then
		SeekBar_Speed.Value = 190
		Game.Speed_Snake = 100
		Label1.Text = "سخت"
	End If
	
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		Activity.Finish
	End If
End Sub