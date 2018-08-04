ArchAngel - LibGDX (migrate from J2ME)
====================

  ArchAngel - Samsung Funclub

![snowballfight](https://github.com/Legio-Augusta/ArchAngel_LibGDX/blob/master/android/assets/intro_0.png)
![snowball-fight](https://github.com/Legio-Augusta/ArchAngel_LibGDX/blob/master/android/assets/screen_3.png)

SnowBallFight is a Samsung J2ME [arcade game by MCookie n Wait4U, NeoMnT](http://www.samsung.com/be/funclub/), which was ported to libgdx by [Dzung Ng](http://cheatortrick.blogspot.com/).

### Try it here
  * [Desktop/KEmulator](https://drive.google.com/file/d/0B9XwFe7bHCQ0OEJhVm1sNDFCRlk/view?usp=sharing)
  * [Desktop/J2ME](https://github.com/dungnv53/SnowBallFight)
  * Project contain many code sample from LibGDX ie. Pax Britannica. Source code J2ME from Samsung Funclub.
  * [Android](http://libgdx.badlogicgames.com/demos/paxbritannica/paxbritannica.apk)

### Running
* [Setup your development environment](https://github.com/libgdx/libgdx/wiki)
* Clone the repository or download and extract the ZIP file
* Import the project into your preferred development environment, run, debug and package it!
  * [Eclipse](https://github.com/libgdx/libgdx/wiki/Gradle-and-Eclipse)
  * [Intellij IDEA](https://github.com/libgdx/libgdx/wiki/Gradle-and-Intellij-IDEA)
  * [NetBeans](https://github.com/libgdx/libgdx/wiki/Gradle-and-NetBeans)
  * [Commandline|Gradle on the Commandline](https://github.com/libgdx/libgdx/wiki/Gradle-on-the-Commandline)
  * [CLDC Wireless Toolkit] http://www.oracle.com/technetwork/java/download-135801.html
  * [J2ME docs] (https://docs.oracle.com/javame/config/cldc/ref-impl/midp2.0/jsr118/javax/microedition/lcdui/Graphics.html)
# SnowBallFightLibGdx

# TODO
 * Apply design pattern, libGDX best practice to implement AI, Sprite batch, etc.
 * Object oriented approach to refactor old J2ME source.
 * Renfine sprite image, media quality.


# List J2ME functions require port to GDX
Port Java ME game to LibGDX
Graphics.draw() => SpriteBatch.draw()
keyHandle, getGameAction, keyPressed ... => implement a similar mechanism using touch, button area ...
Image           => Texture
Graphics        => SpriteBatch...
setClip, clipRect => Scissor ?
RecordStore     => Simple Preference or DB
Binary pack (~Sprite pack) => Texture-packer, use some python tool to extract original J2ME pack.
Thread
Graphics.fillRect  => implement similar method using color image (.png) and draw()
Character (often procedural) => Implement some Class for Hero, Enermy ...
Some game state: screen, state, school (lever) =>
