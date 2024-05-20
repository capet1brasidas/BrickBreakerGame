Tech Stack:  

LibGDx  
Gradle  
Design Patterns  

Game Design：
Components
1.Paddle: The player-controlled object that bounces the ball.    
2.Ball: The object that bounces around the screen, breaking bricks.
3.Bricks: The targets that the ball breaks to earn points.
4.Power-ups: Special items that modify gameplay when collected.

Architecture：
1.Game Assets:
  a.Use a debug-camera.json to configure the debug camera, the debug util is reusable
  b.Pack the game elements pictures into a gameplay.atlas file
  c.Deployed sounds and animation effects
2.Game controller responsible for the game logic and Game renderer responsible for rendering the interface.
3.Add script classes for different power ups.



Game Demo:

![image](https://github.com/capet1brasidas/BrickBreakerGame/assets/141989335/3efbe4d0-ed27-49e4-ad69-96f128fc80a3)

![image](https://github.com/capet1brasidas/BrickBreakerGame/assets/141989335/ae9dca2a-e4e4-4a79-8a1b-799936af5ffb)



How to Play:
Start the Game: Launch the game from your IDE or command line.
Objective: Use the paddle to bounce the ball and break all the bricks.
Lives: You start with three lives. Losing the ball costs a life.
Levels: Clear all bricks to progress to the next level.
Controls
Keyboard:
Left Arrow Key: Move paddle left.
Right Arrow Key: Move paddle right.
Spacebar: Launch the ball (if available).
Scoring
Brick Break: Earn points for each brick you break.
Power-ups: Some power-ups may grant additional points.
High Scores: Your highest score is saved locally.

