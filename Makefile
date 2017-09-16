
JFLAGS = -g
JC = javac
JTEST = java SameGame
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	SameGame.java \
	ActionController.java \
	Menu.java \
	MouseController.java \
	MouseMotionController.java \
	Block.java \
	Grid.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

test:
		$(JTEST)
