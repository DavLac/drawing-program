# Drawing app

```
------------------------------------
|      ____  ____  ___ _       __  |
|     / __ \/ __ \/   | |     / /  |
|    / / / / /_/ / /| | | /| / /   |
|   / /_/ / _, _/ ___ | |/ |/ /    |
|  /_____/_/ |_/_/  |_|__/|__/     |
|                                  |
------------------------------------
```

The drawing app create canvas and generate different shapes on it.

This is a console app. You need to run it in a console terminal.

## Readme links

- [Instructions and command lines to use the app](README-TASK.md)
- [How to run the app](README-RUN.md)

## Special cases
1. Canvas maximum size = **5000**.

Example : `C 5000 5000`

  With regular consoles UI (Windows cmd or Git bash) '200' size is the
  maximum to display a canvas correctly. IntelliJ can handle '5000' size.
  More than '5000', it is possible, but it takes too much time to process a bucket fill.
  
  - Bucket fill on 10000x10000 canvas takes 10 sec
  - Bucket fill on 20000x20000 canvas takes 70 sec
  - Bucket fill on 30000x30000 canvas create OutOfMemory error on 16Go RAM system

2. Command lines are 'trim-ed' and **blank** arguments are ignored.

  Example : `__C___20__4___` command will be parsed to `C_20_4` *(underscore '_' = space in this example)*


3. With the previous rule, it is impossible to use color = `' '` (space) to
  bucket-fill a canvas (it is ignored). To fill a canvas with blank char,
  use the keyword `blank`.

  Example : `B 1 2 blank` will bucket filled with **blank** color at (1,2)


## Jacoco test coverage
Command line :
```
mvn clean test
```
Jacoco report location :  `target/jacoco-ut`

### Test coverage :
- Overall : 99%
- Unit tests : 98%
- Integration tests : 97%
