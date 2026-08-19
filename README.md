# Compiler Design — B.Tech 3rd Year

A collection of Java programs implementing core concepts from the Compiler Design course, covering lexical analysis, finite automata, parsing techniques, and code optimization.

## 📋 Contents

| Program | Description |
|---|---|
| `LexicalAnalyzer.java` | Design and implementation of a lexical analyzer to tokenize source code into identifiers, keywords, operators, constants, etc. |
| `EClosureNFA.java` | Computes the ε-closure of states in a Non-deterministic Finite Automaton (NFA). |
| `NFAtoDFA.java` | Converts a given NFA into an equivalent DFA using the subset construction method. |
| `DFAMinimization.java` | Minimizes a given DFA by merging equivalent states. |
| `FirstFollow.java` | Computes the FIRST and FOLLOW sets for a given context-free grammar. |
| `OperatorPrecedenceParser.java` | Implements an operator precedence parser using the shift-reduce parsing technique. |
| `IntermediateCodeGeneration.java` | Generates intermediate code (e.g., three-address code) using operator precedence parsing. |
| `ConstantPropagation.java` | Implements the constant propagation code optimization technique with expression evaluation. |
| `LoopUnrollingBits.java` | Demonstrates loop rolling vs. loop unrolling optimization techniques, applied to counting set bits. |
| `program.txt` | Sample input program/source code used for testing the lexical analyzer. |

## 🚀 Getting Started

### Prerequisites
- Java JDK 8 or above installed on your system.

### Running a Program
1. Clone the repository:
   ```bash
   git clone https://github.com/prashantpiyush1111/CompilerDesign_B.tech_3rd_Year.git
   cd CompilerDesign_B.tech_3rd_Year
   ```
2. Compile the desired `.java` file:
   ```bash
   javac LexicalAnalyzer.java
   ```
3. Run the compiled program:
   ```bash
   java LexicalAnalyzer
   ```

> Some programs (like `LexicalAnalyzer.java`) may read input from `program.txt` — make sure it's in the same directory when running.

## 📚 Topics Covered

- **Lexical Analysis** — Tokenization and scanning
- **Finite Automata** — NFA construction, ε-closure, NFA → DFA conversion, DFA minimization
- **Syntax Analysis** — FIRST/FOLLOW sets, Operator Precedence Parsing (shift-reduce)
- **Intermediate Code Generation** — Three-address code generation
- **Code Optimization** — Constant propagation, loop unrolling vs. loop rolling

## 👤 Author

**Prashant Maurya** ([@prashantpiyush1111](https://github.com/prashantpiyush1111))

## 📄 License

This repository is intended for academic/educational purposes as part of a B.Tech Compiler Design course.
