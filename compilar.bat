if not exist "bin" mkdir bin

javac -classpath .\bin;.\lib\* ^
      -encoding UTF-8 ^
      -d .\bin ^
      -sourcepath .\src;.\test ^
		  .\src\brandubh\control\*.java ^
		  .\src\brandubh\modelo\*.java ^
		  .\src\brandubh\textui\*.java ^
		  .\src\brandubh\util\*.java ^
		  .\test\brandubh\*.java ^
		  .\test\brandubh\control\*.java ^
		  .\test\brandubh\control\avanzado\*.java ^
		  .\test\brandubh\control\basico\*.java ^
		  .\test\brandubh\control\medio\*.java ^
		  .\test\brandubh\modelo\*.java ^
		  .\test\brandubh\util\*.java 