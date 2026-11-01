Remove-Item -Recurse -Force .\target\dest
mvn clean package
mvn jpackage:jpackage
./target/dest/EntertainmentTracker/EntertainmentTracker.exe