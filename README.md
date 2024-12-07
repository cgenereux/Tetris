## How to run Program:
1. open IDE of choice(preferable intellij)
2. open _src_ folder, and go to the **Main.java** file
3. compile and run on IDE `(shift+f10)`

***

## Game Objective:
To reach the _highest score_ you can, <br/>
without letting the blocks pile up to the top of the grid screen:<br/>
Which will result in a **Game Over**

*** 
## Controls 
&uarr; `rotation (clockwise)`<br/>
&larr; `move left` <br/>
&rarr; `move right` <br/>
&darr; `speed downwards`<br/>
space `drop block` 
***

## Project Structure:
There will be multiple **classes** for each of the aspects of the game:<br/>
######  Note - Attributes -> `variable -> class/primative`

***

### Game.java
###### main loop of game

#### Attributes
`grid -> Grid`
`score -> Score`
`gameSpeed -> gameSpeed`
`currentBlock -> Block`
`nextBLock -> Block`
`gameTimer -> Timer`
<br/> <br/>
`onStartScreen -> boolean`
`gridImage, backGroundImage -> BufferedImage`
<br/> <br/>
`o/t/i/l/j/s/zImage -> BufferedImage`


<br/>

#### methods
`startGame()`
`main()`
`rotateBlock()`
`dropBlock()`
`loadImages()`
`paintComponent()`
`getBlockImage()`
`loadImage()`

``



___

<br/>

### Grid.java
###### manage 2D game grid for placement, manipulation and removal
#### attributes
`width -> int`
`height -> int`

#### methods
`placeBlock()`
`getCell()`
`isWithinBounds()`
`canPlaceBlocks()`
`removeBlock()`
`clearFullRows()`
`isFullRow()`
`clearRow()`
`getHeight()`
`getWidth()`



### Controls
###### the user input: for rotating, left and right, speeding downwards, and 'dropping' (instantly translating a block to the bottom)
#### attributes

`game -> Game`

<br/>

#### methods
`Controls()`
`KeyPreseed()`
___
<br/>

### Score
###### tracks the score, lines cleared, and level
#### attributes
`score`
#### methods
`addPoints()`
`getScore()`

### Render
###### imports the pngs into the JavaFX to use, and 'draws' the score, lines cleared, and level
#### methods
`importPNG()`
___

### GameSpeed
###### determines how fast the game goes, will increase based on score
#### attributes
`baseSpeed -> int`
`currentSpeed -> int`
`scoreThreshold -> int`
`speedIncrement -> int`

<br/>

#### methods
`GameSpeed()`
`updateSpeed()`
`getCurrentSpeed()`
***

### BlockController

#### attributes
`grid -> Grid`
`score -> score`
`gameSpeed -> GameSpeed`
`game -> Game`
`currentBlock -> Block`
`nextBlock Block -> Block`
`gameTimer -> Timer`

<br/>
#### methods
`BlockController()`
`shiftBlock()`
`setGameTimer()`
`spawnNewBlock()`
`rotateBlock()`
`dropBlock()`
`getCurrentBlock()`
`getNextBlock()`
***
<br/>


## **Blocks**
### Block.java
###### To define the behaviors of the blocks
#### attributes
`shape -> double array`
`uniqueId -> int`
`typeId -> char`
`uniqueIdCounter -> int`
`currentX/Y -> int`
`pivotX/Y -> int`

<br/>

#### methods
`getShape()`
`getTypeID()`
`getCurrentX/Y()`
`setCurrentPostion()`
`rotateOnceClockwise()`
`setCurrentPosition()`
`rotateOnceCLockwise()`
`setShape()`
___
<br/>

### RandomBlock
###### to choose a random block to spawn when it starts falling
#### attributes
`random -> Random`

<br/>

#### methods
`generateBlock()`
___
<br/>

### I/J/L/O/S/T/ZBlock

###### Inherited class of Block that defines the typeId and positions of block

#### methods
`I/J/L/O/S/T/ZBlock()`
___