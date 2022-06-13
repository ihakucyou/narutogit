dim program
set Wshell=CreateObject("Wscript.Shell") 
WShell.Run "gettoken.bat" 
wscript.Sleep 4000
Wshell.SendKeys "{ENTER}"
wscript.sleep 4000
Wshell.SendKeys "{TAB}" 
Wshell.SendKeys "运维" 
wscript.Sleep 4000
Wshell.SendKeys "{TAB}" 
Wshell.SendKeys "super@glodon1" 
wscript.Sleep 4000
Wshell.SendKeys "{ENTER}"
Wscript.quit