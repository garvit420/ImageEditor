# Image Editor CUI

Image Editor CUI is a simple command-line program that allows you to perform various image processing operations on images in the JPEG format.

## Features

- **Grayscale Conversion:** Convert a color image to grayscale.

- **Rotate:** Rotate an image 90 degrees clockwise or anticlockwise.

- **Invert:** Flip an image horizontally or vertically.

- **Brightness Adjustment:** Change the brightness of an image by a given percentage.

- **Pixel Blurring:** Apply pixel-level blurring to an image.

- **Smooth Blurring:** Apply smooth blurring to an image.

- **Contrast Adjustment:** Change the contrast of an image by a given percentage.

- **Saturation Adjustment:** Change the saturation of an image by a given percentage.

- **Negative Image:** Create the negative of an image.

- **Filter Application:** Apply filters (Red, Blue, Green) to an image.

- **Edge Detection:** Detect edges in an image.

## Usage

1. Clone the repository:
   ```sh
   git clone https://github.com/garvit420/ImageEditor.git
   ```

2. Compile the program:
   ```sh
   javac imageEditorCui.java
   ```

3. Run the program:
   ```sh
   java imageEditorCui
   ```

4. Follow the on-screen prompts to select the operation you want to perform and provide the required inputs.

5. The modified image will be saved as a new file.

## Requirements

- Java Development Kit (JDK)
- Input image in JPEG format

## Examples

- Grayscale Conversion:
  ```sh
  Select 1 for grayscaling the image.
  ```

- Rotate Image:
  ```sh
  Select 2 to rotate 90 degrees clockwise.
  Select 3 to rotate 90 degrees anticlockwise.
  ```

- Change Brightness:
  ```sh
  Provide the percentage by which you want to change brightness.
  ```

- Pixel Blurring and Smooth Blurring:
  ```sh
  Provide the number of pixels for blurring.
  ```

- Change Contrast and Saturation:
  ```sh
  Provide the percentage by which you want to change contrast or saturation.
  ```

- Create Negative Image:
  ```sh
  Select 11 to create the negative of the image.
  ```

- Apply Filters:
  ```sh
  Select 12 and choose the filter (1 for Red, 2 for Blue, 3 for Green).
  ```

- Edge Detection:
  ```sh
  Select 13 to perform edge detection on the image.
  ```

## Author

- [Garvit jain](https://github.com/garvit420)

## License

This project is open-source and available under the [MIT License](LICENSE).
