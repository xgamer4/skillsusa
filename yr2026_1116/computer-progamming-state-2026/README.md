# CSA: OceanDestruction 2 Game

* Author: Gideon Hammond

## Overview

This Java application displays an a CLI of a OceanDestruction 2 game it guides you
and corrects you if you are wrong.

## Implementation

I went for an OOP programming style breaking up any part I could into separate
files. The logic of the attacking is not as polished as I would have liked,
I.E. I would have liked for the CLI to print more for that part. I also wish 

## Compiling and Using

To compile, execute the following command in the main project directory:
```
$ javac Driver.java
```

Run the compiled class with the command:
```
$ java Driver
```

You can play by inputting Train, Expand, or Attack. Training will give you
the number of units per bases you have. Expanding will get a new base if one is avaliable
otherwise you lose a turn. Attacking will initiate combat but if you have one
unit instead is a taunt and you lose a turn.

## Discussion

Overall this was a fun project, I enjoyed making it and thinking through the logic.
I believe that the finished project is quite good. If I had more time I likely would
have implemented a better visual model to see the bases and make some of the CLI better.
I also would have worked through the Game code to slim down the Initiate Attack function

One issue I had was fighting through the spaghetti code of the attack function. 
That by far is the worst part of this project. there was several logic issues that caused
flaws. I believe now all of those work.

One of the vital strategies I used in this was OOP. I wanted to make sure that not everything
was in the same exact file Instead I call multiple files and break them apart.
Visually I like the project and I don't think that it looks bad. Although
If I was spending a weekend on this I would've done a GUI.

I hope you enjoy the game.