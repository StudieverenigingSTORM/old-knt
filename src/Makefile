SRCDIR=src
DSTDIR=classes
BINDIR=bin
CLASSPATH=

all: bindir dstdir classes

bindir:
	mkdir -p $(BINDIR)

dstdir:
	mkdir -p $(DSTDIR)

clean:
	test -d $(BINDIR) && rm -R $(BINDIR) || true
	test -d $(DSTDIR) && rm -R $(DSTDIR) || true

depend:
	sh updateclasses.sh

#BEGIN CLASSLIST

classes: $(DSTDIR)/vu/storm/administration/Category.class $(DSTDIR)/vu/storm/administration/IDatabase.class $(DSTDIR)/vu/storm/administration/LineItem.class $(DSTDIR)/vu/storm/administration/Order.class $(DSTDIR)/vu/storm/administration/Product.class $(DSTDIR)/vu/storm/administration/User.class $(DSTDIR)/vu/storm/administration/Image.class

$(DSTDIR)/vu/storm/administration/Category.class: $(SRCDIR)/vu/storm/administration/Category.java
	javac $(CLASSPATH) -sourcepath $(SRCDIR) -d $(DSTDIR) $<
$(DSTDIR)/vu/storm/administration/IDatabase.class: $(SRCDIR)/vu/storm/administration/IDatabase.java
	javac $(CLASSPATH) -sourcepath $(SRCDIR) -d $(DSTDIR) $<
$(DSTDIR)/vu/storm/administration/LineItem.class: $(SRCDIR)/vu/storm/administration/LineItem.java
	javac $(CLASSPATH) -sourcepath $(SRCDIR) -d $(DSTDIR) $<
$(DSTDIR)/vu/storm/administration/Order.class: $(SRCDIR)/vu/storm/administration/Order.java
	javac $(CLASSPATH) -sourcepath $(SRCDIR) -d $(DSTDIR) $<
$(DSTDIR)/vu/storm/administration/Product.class: $(SRCDIR)/vu/storm/administration/Product.java
	javac $(CLASSPATH) -sourcepath $(SRCDIR) -d $(DSTDIR) $<
$(DSTDIR)/vu/storm/administration/User.class: $(SRCDIR)/vu/storm/administration/User.java
	javac $(CLASSPATH) -sourcepath $(SRCDIR) -d $(DSTDIR) $<
$(DSTDIR)/vu/storm/administration/Image.class: $(SRCDIR)/vu/storm/administration/Image.java
	javac $(CLASSPATH) -sourcepath $(SRCDIR) -d $(DSTDIR) $<

#END CLASSLIST

jar: clean all
	jar cvf $(BINDIR)/TouchAdministration.jar -C $(DSTDIR) vu
