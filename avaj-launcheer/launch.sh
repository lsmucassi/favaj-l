find . -name "*.java" > sources.txt
javac -sourcepath . @sources.txt
java -cp src com.avaj.launcher.Simulator $1