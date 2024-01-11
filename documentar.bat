javadoc -author ^
	-version ^
	-private ^
	-encoding UTF-8 ^
	-charset UTF-8 ^
	-sourcepath .\src ^
		    .\src\brandubh\control\*.java ^
		    .\src\brandubh\modelo\*.java ^
		    .\src\brandubh\textui\*.java ^
		    .\src\brandubh\util\*.java ^
	-d doc ^
	-classpath .\lib\*;.\bin ^
	-docencoding UTF-8 ^
	-link https://docs.oracle.com/en/java/javase/20/docs/api/ ^
	-subpackages brandubh ^
	-overview .\src\overview.html