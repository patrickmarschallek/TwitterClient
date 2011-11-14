# Feedback
##Dokumentation
Die Dokumentation ist umfassend und gut geschrieben. Hier wäre natürlich wünschenswert gewesen, wenn eine Formatierung wie Markdown oder ähnliches verwendet worden wäre.


##Git- und Github-Nutzung
Die Nutzung von Git verzichtet auf viele Vorteile, die Git bereitstellt. So sind lokale Commits wichtig, um zu tracken, wie das Programm sich entwickelt hat. Auch monolithische Commits wider sprechen der gewünschten Verwendung. 

Bei mehreren Benutzern führen solche Commits grundsätzlich zu Merge-Konflikten.

Auch sollten niemals Credentials im Code stehen und schon gar nicht auf einem öffentlich zugänglichen Repository eingecheckt werden.

##Funktionalität
Das Programm verhält sich wie gefordert. Leider war dazu das erneute Kompilieren des Programms, nach Entfernung der Proxy-Einstellungen nötig. Ein Fehler tritt immer dann auf, wenn der reguläre Ausdruck, welcher die Antwort wählt mehrfach hintereinander greift, in diesem Fall nimmt Twitter das erneute Twittern des gleichen Texts nicht an.

##Code
Das Programm hat fest im Quellcode den Proxy der FH-Brandenburg eingetragen, der natürlich nur dort funktioniert. Solche Einstellungen gehören auf jeden Fall in eine externe .properties-Datei.
Auch die Credentials würden von der Nutzung einer solchen config-Datei profitieren.
Der Quelltext des Programms wirkt eher unsauber. 

Die Datei TwitterStatusListener überschreibt alle vorhandenen Methoden, der ableitenden Klasse. Die Verwendung der Method-Stubs entfernt natürlich die komplette Funktionalität des UserStreamListener, bis auf die Methode onStatus, die selbst implementiert wurde.

Die Main-Methode ist der Einstiegspunkt der Anwendung, hier sollte Code ausgelagert werden, da sich Main-Methoden besonders schwer testen lassen.

Die Klasse MessageLogic sollte ein switch/case-Block verwenden.

***
Bewertung: Ich bewerte die Applikation mit noch 4 Punkten.

