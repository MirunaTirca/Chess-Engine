JFLAGS = -g
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Game.java \
	MiniMax.java \
	Board.java \
	Pair.java \
	Piece.java \
	Pawns.java \
	Rooks.java \
	Bishops.java \
	Knight.java \
	King.java \
	Queen.java \
	Spot.java \
	Move.java

MAIN = Game

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) $(MAIN)

clean:
	$(RM) *.class
