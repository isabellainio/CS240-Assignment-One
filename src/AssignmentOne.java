// Isabella Inio - CS240 Section 02
// Sep 6, 2026

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AssignmentOne {

    // 1. Build an ASCII-to-decimal converter.

    public static void asciiToDecimal(Scanner input) {
        System.out.print("Enter a string: ");
        String text = input.nextLine();

        for (int i = 0; i < text.length(); i++) {
            System.out.println((int) text.charAt(i));
        }
    }

    // 2. Build a number-base converter supporting binary, decimal, octal, and hexadecimal.

    public static void numberBaseConverter(Scanner input) {
        System.out.println("\n1. Binary");
        System.out.println("2. Decimal");
        System.out.println("3. Octal");
        System.out.println("4. Hexadecimal");
        System.out.print("\nSelect the input base: ");

        int choice;

        try {
            choice = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException exception) {
            System.out.println("Invalid selection.");
            return;
        }

        int base;

        if (choice == 1) {
            base = 2;
        } else if (choice == 2) {
            base = 10;
        } else if (choice == 3) {
            base = 8;
        } else if (choice == 4) {
            base = 16;
        } else {
            System.out.println("Invalid selection.");
            return;
        }

        System.out.print("Enter the number: ");
        String number = input.nextLine();

        try {
            int decimalValue = Integer.parseInt(number, base);

            System.out.println(
                    "Binary: " +
                            Integer.toBinaryString(decimalValue)
            );

            System.out.println(
                    "Decimal: " + decimalValue
            );

            System.out.println(
                    "Octal: " +
                            Integer.toOctalString(decimalValue)
            );

            System.out.println(
                    "Hexadecimal: " +
                            Integer.toHexString(decimalValue).toUpperCase()
            );
        } catch (NumberFormatException exception) {
            System.out.println("Invalid number.");
        }
    }

    // 3. Write a program that reads an image and prints its pixel values.

    public static String convert(Color color) {
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        if (red == 237 && green == 28 && blue == 36) {
            return "R";
        } else if (red == 0 && green == 0 && blue == 0) {
            return "B";
        } else if (red == 255 && green == 242 && blue == 0) {
            return "Y";
        } else {
            return "W";
        }
    }

    public static void imageToPixelValues(
            String imageName,
            String outputName
    ) throws IOException {

        BufferedImage image = ImageIO.read(new File(imageName));

        if (image == null) {
            throw new IOException("The file is not a valid image.");
        }

        PrintWriter outputFile = new PrintWriter(outputName);

        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                outputFile.print(convert(color) + " ");
            }

            outputFile.println();
        }

        outputFile.close();
    }

    // 4. Write a program that consumes pixel values and creates an image.

    public static void pixelValuesToImage(
            String inputName,
            String outputImageName
    ) throws IOException {

        Scanner pixelInput = new Scanner(new File(inputName));
        ArrayList<String[]> rows = new ArrayList<>();

        while (pixelInput.hasNextLine()) {
            String line = pixelInput.nextLine().trim();

            if (!line.isEmpty()) {
                rows.add(line.split("\\s+"));
            }
        }

        pixelInput.close();

        if (rows.isEmpty()) {
            throw new IOException("The input file is empty.");
        }

        int height = rows.size();
        int width = rows.get(0).length;

        BufferedImage newImage = new BufferedImage(
                width,
                height,
                BufferedImage.TYPE_INT_RGB
        );

        for (int y = 0; y < height; y++) {
            if (rows.get(y).length != width) {
                throw new IOException(
                        "Every row must contain the same number of pixels."
                );
            }

            for (int x = 0; x < width; x++) {
                String pixel = rows.get(y)[x];
                Color color;

                if (pixel.equals("R")) {
                    color = new Color(237, 28, 36);
                } else if (pixel.equals("B")) {
                    color = new Color(0, 0, 0);
                } else if (pixel.equals("Y")) {
                    color = new Color(255, 242, 0);
                } else {
                    color = new Color(255, 255, 255);
                }

                newImage.setRGB(x, y, color.getRGB());
            }
        }

        ImageIO.write(
                newImage,
                "png",
                new File(outputImageName)
        );
    }

    // 5. Test boundary cases, including zero, the largest supported unsigned value, and at least one negative two's-complement value.

    public static String toEightBitBinary(int number) {
        if (number < -128 || number > 255) {
            return "Value is outside the 8-bit range";
        }

        if (number < 0) {
            number = 256 + number;
        }

        return String.format(
                "%8s",
                Integer.toBinaryString(number)
        ).replace(' ', '0');
    }

    public static void testBoundaryCases() {
        int zero = 0;
        int largestUnsigned = 255;
        int negativeNumber = -5;

        System.out.println(
                "\nZero: " + zero + " = " +
                        toEightBitBinary(zero)
        );

        System.out.println();

        System.out.println(
                "Largest unsigned value: " +
                        largestUnsigned + " = " +
                        toEightBitBinary(largestUnsigned)
        );

        System.out.println();

        System.out.println(
                "Negative two's-complement value: " +
                        negativeNumber + " = " +
                        toEightBitBinary(negativeNumber)
        );
    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        // 1
        asciiToDecimal(input);

        // 2
        numberBaseConverter(input);

        // 3
        imageToPixelValues(
                "src/smiley.png",
                "output.txt"
        );

        // 4
        pixelValuesToImage(
                "output.txt",
                "reconstructed_smiley.png"
        );

        // 5
        testBoundaryCases();

        input.close();
    }
}