echo "[PROCESS]  Compiling gui for USSD MAPPER system"
E:
cd E:\Pahappa\ussd-mapper\ussd-mapper
call mvn eclipse:clean
call mvn eclipse:eclipse -Dwtpversion=2.0
call mvn package
echo "[SUCCESSFUL]  Successfully Compiled All Guis
PAUSE