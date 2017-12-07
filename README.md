# GameOfLifeView
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-GameOfLifeView-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/1672)

This is a custom Android view which displays Conway's Game of Life. I've done to display the results of my learnings of Test Driven Development.

I've done this basically to learn how the process of Test Driven Development works. The custom view itself is just 

![Screenshot](https://raw.githubusercontent.com/thiagokimo/GameOfLifeView/master/images/default.jpg)
![Screenshot](https://raw.githubusercontent.com/thiagokimo/GameOfLifeView/master/images/custom.jpg)

## Setup
Gradle:

``` groovy
dependencies {
    compile 'com.github.thiagokimo:gameoflifeview:1.0.2'
}
```

Maven:

``` xml
<dependency>
    <groupId>com.github.thiagokimo</groupId>
    <artifactId>gameoflifeview</artifactId>
    <version>1.0.2</version>
</dependency>
```

## Usage

### Through XML

``` xml
<io.kimo.gameoflifeview.view.GameOfLifeView
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  game_of_life_view:proportion="20"
  game_of_life_view:aliveCellColor="#FF0000"
  game_of_life_view:deadCellColor="#0000FF"/>
```

### Through Code

Initialize the view:

``` java
@Override 
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  gameOfLifeView = new GameOfLifeView(this);
  gameOfLifeView.setProportion(20);
  gameOfLifeView.setDeadColor(Color.RED);
  gameOfLifeView.setAliveColor(Color.BLUE);
  setContentView(gameOfLifeView);
}
```

...and run the game with:

``` java
  gameOfLifeView.begin();
```

## Custom params

From now you can customize the attributes:

- Proportion: The value which the view will use to calculate the cell size and the number of columns and rows the game will have.
- Colors: Dead and alive cells can have any given color.

## License

    Copyright 2011, 2012 Thiago Rocha

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
