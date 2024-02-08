#!/bin/sh

# Update the Makefile using ClassList

#$(DSTDIR)/vu/storm/touch/DemoProgram.class: $(SRCDIR)/vu/storm/touch/DemoProgram.java
#	javac $(CLASSPATH) -sourcepath $(SRCDIR) -d $(DSTDIR) $<

MAKEFILE="Makefile"

if [ -n "$1" ]
then
	MAKEFILE="$1"
fi


IFS=""
cp "$MAKEFILE" "${MAKEFILE}.bak"
exec >"$MAKEFILE"

doline() {
	path="`echo $1 | tr '.' '/'`"
	if [ -f "src/${path}.java" ]
	then
		echo "\$(DSTDIR)/${path}.class: \$(SRCDIR)/${path}.java"
		echo "	javac \$(CLASSPATH) -sourcepath \$(SRCDIR) -d \$(DSTDIR) \$<"
	else
		source="`echo $path | sed 's#/[^/]\+$##'`"
		class="`echo $path | sed 's#/\([^/]\+\)$#$\1#'`"
		if [ -f "src/${source}.java" ]
		then
			echo "\$(DSTDIR)/${class}.class: \$(SRCDIR)/${source}.java"
			echo "	javac \$(CLASSPATH) -sourcepath \$(SRCDIR) -d \$(DSTDIR) \$<"
		else
			echo "Error finding: $1" >&2
		fi
	fi
}
doclass() {
	path="`echo $1 | tr '.' '/'`"
	if [ -f "src/${path}.java" ]
	then
		echo -n " \$(DSTDIR)/${path}.class"
	else
		source="`echo $path | sed 's#/[^/]\+$##'`"
		class="`echo $path | sed 's#/\([^/]\+\)$#$\1#'`"
		if [ -f "src/${source}.java" ]
		then
			echo -n " \$(DSTDIR)/${class}.class"
		else
			echo "Error finding: $1" >&2
		fi
	fi
}

exec 5< "${MAKEFILE}.bak"

read -u5 line
while [ "$line" != "#BEGIN CLASSLIST" ]
do
	echo "$line"
	read -u5 line
done
echo "$line"
echo

exec 7< ClassList
echo -n "classes:"
read -u7 line
while [ -n "$line" ]
do
	doclass $line
	read -u7 line
done
echo
echo

exec  6< ClassList

read -u6 line
while [ -n "$line" ]
do
	doline $line
	read -u6 line
done


read -u5 line
while [ "$line" != "#END CLASSLIST" ]
do
	read -u5 line
done
echo
echo "$line"

read -u5 line
while [ "$?" = "0" ]
do
	echo "$line"
	read -u5 line
done
true
