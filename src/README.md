# Assignment 1: Converter and Pixel System

**Name:** Isabella Inio  
**Course:** CS240 Section 02  
**Date:** September 6, 2026

## Description

This Java program represents information using characters, number bases,
binary values, and image pixels.

The program:

1. Converts characters into decimal character values.
2. Converts numbers among binary, decimal, octal, and hexadecimal.
3. Reads `smiley.png` and writes its pixels as color symbols to `output.txt`.
4. Reads pixel symbols from `input.txt` and creates
   `reconstructed_smiley.png`.
5. Tests zero, the largest unsigned 8-bit value, and a negative
   two's-complement value.

## Files

- `AssignmentOne.java`: Java source code
- `smiley.png`: Original image
- `input.txt`: Pixel symbols used to create an image
- `output.txt`: Pixel symbols produced from the original image
- `reconstructed_smiley.png`: Image created from `input.txt`

## Color Symbols

- `R` represents RGB `(237, 28, 36)`
- `B` represents RGB `(0, 0, 0)`
- `Y` represents RGB `(255, 242, 0)`
- `W` represents RGB `(255, 255, 255)`

## Eight-Bit Encoding

The program uses an 8-bit representation.

- Unsigned range: 0 through 255
- Signed two's-complement range: -128 through 127
- Zero: `00000000`
- Largest unsigned value: `11111111`
- Negative five: `11111011`

## How to Run

1. Place all project files in the same folder.
2. Compile the program using `javac AssignmentOne.java`.
3. Run it using `java AssignmentOne`.
4. Follow the prompts in the terminal.
5. Open `output.txt` and `reconstructed_smiley.png` to view the results.
