@echo off
echo "*********************************"
echo "* Starting Calculator CLI       *"
echo "* This requires Java 8+ to run. *"
echo "*********************************"

call mvnw -U clean package
call java -jar cli\target\cli.jar
