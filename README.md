# FunInFunctional-Android
Rewriting my own map/filter/reduce functions in Java 7, for better understanding and/or insanity. Demo app from [AndroidListener](https://twitter.com/AndroidListener), October 2015. 

The functions are all in `FunctionalArray.java`, and are tested in `FunctionalArrayTests.java` and `SudoMakeMeASandwich.java` (with apologies to [xkcd](https://xkcd.com/149/))

Note: This is mostly to try and get my head around exactly what the hell map, reduce, and filter are doing. If you want to actually *use* any of these things, I highly recommend using actual vetted methods from [RxJava](https://github.com/ReactiveX/RxJava) (and with way less horrendous syntax if you use [Retrolambda](https://github.com/orfjackal/retrolambda)).