# Bubble Draw

## Introduction

I created this android application by following the tutorial in Bryson Payne's book, "Learn Java the Easy Way". I went through his book while I was taking courses in
Java in order to better familiarize myself with the langauge and to also learn how to develop my own Android applications. Additionally, 
completing this project made it a lot easier to understand object-oriented programming. I also got to work with Android Studio and learn how to design application
layouts with it. In the future, I would like to develop a game for the Android platform and publish it to the Google Play store. 

## Challenges and Improvements

The tutorial to create this app was mostly straightforward, and I didn't really have many problems while following it. The main issues came once the tutorial was
complete and I spent time playing with the project. I noticed that if you tried to create the "bubbles" too close to the edge's of the device's screen, they
would get stuck outside the boundaries of the main view. The solution I came up with for this was to check if the bubble was exiting the screen by looking
at its x and y coordinates as well as its size. If the user's touch input would result in a bubble being created out of bounds, then it would not be placed.

This is accomplished by this line of code:
```java
if (!(x < size || x > getWidth() - size || y < size || y > getHeight() - size)) {
```

Once I finished the application, I wanted to add some additional functionality which would allow the user to toggle a "Draw Mode". This mode would let the user
draw with the bubbles instead of them randomly floating around the screen. I did this by removing the physics from the bubbles in this mode. I also made it so 
exiting the mode would return the physics to the bubbles for a neat effect.

I also added a "Clear Screen" option that would remove all the bubbles from the screen so the user could start over without having to exit the application.

## Video
[![Watch the video](https://user-images.githubusercontent.com/32273966/89475774-e9ecf000-d756-11ea-8117-a583bbb9a77c.PNG)](https://www.youtube.com/watch?v=KVI5XeZlLUg)
