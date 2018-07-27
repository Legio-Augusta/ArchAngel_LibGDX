# ArchAngel_LibGDX
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
