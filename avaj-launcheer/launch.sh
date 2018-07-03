find . -name "*.java" > sources.txt
javac -sourcepath . @sources.txt
java com.avaj.launcher.Simulator scenario.txt