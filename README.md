## How to run Program:
1. open IDE of choice(preferable intellij)
2. open _src_ folder, and go to the **Main.java** file
3. compile and run on IDE `(shift+f10)`

## Game Objective:
To reach the _highest score_ you can, <br/>
without letting the blocks pile up to the top of the grid screen:<br/>
Which will result in a **Game Over**


## Project Structure:
There will be multiple **classes** for each of the aspects of the game:<br/>

### tetrisGame
###### main loop of game
#### attributes
`game window`
`score window` <br/>
`level window` <br/>
`game status(gameOver, playing, pause)` 
#### methods <br/>
`startGame()`
`pauseGame()`


### Grid
###### to display the grid and store values in the grid
#### attributes
`2D array (width height)`
#### methods
`Collision()`
`clearLine()`
`gameOver()`
`spawnPiece()`

### Block
###### To define the behaviors of the blocks
#### attributes
`position(x,y)`
#### methods
`rotate()`
`moveLeft()`
`moveRight()`
`moveDown()`
`drop()`


### TetrisRandom
###### to choose a random block to spawn when it starts falling
#### attributes
#### methods
`spawnRandomPiece()`


### Controls
###### the user input: for rotating, left and right, speeding downwards, and 'dropping' (instantly translating a block to the bottom)
#### attributes
###### will reference to the grid and Block
#### methods
`movePiece()`
`rotatePiece`
`dropPiece`

### Score
###### tracks the score, lines cleared, and level
#### attributes
`score`
`lines cleared`
#### methods
`incrementscore()`

### Render
###### imports the pngs into the JavaFX to use, and 'draws' the score, lines cleared, and level
#### methods
`importPNG()`

### gameSpeed
###### determines how fast the game goes, will increase based on score
#### attributes
`Time interval`
#### methods
`adjustSpeed()`