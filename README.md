# Drawing program

The drawing program create canvas and generate different shapes on it

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

## How to run the program
### Generate the JAR
Command line :
```
mvn clean install
```
JAR location :  `target/drawingprogram-1.0.0.jar`

### Execute the JAR
Command line :
```
java -jar drawingprogram-1.0.0.jar
```

## Special cases
- Canvas maximum size = **5000**.

With regular consoles UI (Windows cmd or Git bash) '200' size is the
maximum to display a canvas correctly. IntelliJ can handle '5000' size.

- Command lines are 'trim-ed' and **blank** arguments are ignored.

`__C___20__4___` command will be parsed to `C_20_4`
*(underscore '_' = space in this example)*

- With the previous rule, it is impossible to use color = `' '` (space) to
  bucket-fill a canvas (it is ignored). To fill a canvas with blank char,
  use the keyword `blank`.

`B 1 2 blank` will bucket filled with **blank** color at (1,2)


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
