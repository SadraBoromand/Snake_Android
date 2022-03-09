B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=9.3
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: true
	#IncludeTitle: false
#End Region

Sub Process_Globals
	

End Sub

Sub Globals
	

End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("Over_Low")

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub close_Click
	Activity.Finish
End Sub