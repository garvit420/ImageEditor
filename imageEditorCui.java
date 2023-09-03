import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class imageEditorCui {
    public static BufferedImage convertToGray(BufferedImage inputImage){
        
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
 
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                outputImage.setRGB(j,i, inputImage.getRGB(j,i));
            }
        }
        return outputImage;
    }

    public static BufferedImage rotate90Clockwise(BufferedImage inputImage){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
 
        BufferedImage outputImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
    
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                outputImage.setRGB(i,j, inputImage.getRGB(j,i));
            }
        }
        int temp=0;
        for(int i=0; i<width; i++){
            for(int j=0; j<height/2; j++){
                temp = outputImage.getRGB(height-j-1, i);
                outputImage.setRGB(height-j-1, i, outputImage.getRGB(j,i));
                outputImage.setRGB(j, i, temp);
            }
        }
        return outputImage;
    }

    public static BufferedImage rotate90AntiClockwise(BufferedImage inputImage){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
 
        BufferedImage outputImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
    
        for(int i=height-1; i>=0; i--){
            for(int j=width-1; j>=0; j--){
                outputImage.setRGB(height-i-1,width-j-1, inputImage.getRGB(j,i));
            }
        }
        int temp=0;
        for(int i=0; i<width; i++){
            for(int j=0; j<height/2; j++){
                temp = outputImage.getRGB(height-j-1, i);
                outputImage.setRGB(height-j-1, i, outputImage.getRGB(j,i));
                outputImage.setRGB(j, i, temp);
            }
        }
        return outputImage;
    }

    public static BufferedImage horizInvert(BufferedImage inputImage){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();

        int temp=0;
        for(int i=0; i<height; i++){
            for(int j=0; j<width/2; j++){
                temp = inputImage.getRGB(width-j-1, i);
                inputImage.setRGB(width-j-1, i, inputImage.getRGB(j,i));
                inputImage.setRGB(j, i, temp);
            }
        }
        return inputImage;
    }

    public static BufferedImage vertInvert(BufferedImage inputImage){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();

        int temp=0;
        for(int i=0; i<width; i++){
            for(int j=0; j<height/2; j++){
                temp = inputImage.getRGB(i, height-j-1);
                inputImage.setRGB(i, height-j-1, inputImage.getRGB(i,j));
                inputImage.setRGB(i, j, temp);
            }
        }
        return inputImage;
    }

    public static BufferedImage changeBrightness(BufferedImage inputImage, int changePercentage){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
 
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        System.out.println("Please input percentage by which you want to change brightness : ");

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                Color pixel = new Color(inputImage.getRGB(j, i));
                int red = pixel.getRed() * (changePercentage+100)/100;
                int blue = pixel.getBlue() * (changePercentage+100)/100;
                int green = pixel.getGreen() * (changePercentage+100)/100;
                if(red>255) red=255;
                else if(red<0) red=0;
                if(blue>255) blue=255;
                else if(blue<0) blue=0;
                if(green>255) green=255;
                else if(green<0) green=0;
                Color newPixel = new Color(red, green, blue);
                outputImage.setRGB(j, i, newPixel.getRGB());
            }
        }
        return outputImage;
    }

    public static BufferedImage pixelBlur(BufferedImage inputImage, int pixelBlurAmount){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
 
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for(int i=0; i<height; i+=pixelBlurAmount){
            for(int j=0; j<width; j+=pixelBlurAmount){
                int avgR = 0;
                int avgG = 0;
                int avgB = 0;
                int count = 0;
                for(int k=i; k<i+pixelBlurAmount; k++){
                    for(int l=j; l<j+pixelBlurAmount; l++){
                        Color pixel = new Color(inputImage.getRGB(l, k));
                        avgR += pixel.getRed();
                        avgB += pixel.getBlue();
                        avgG += pixel.getGreen();
                        count++;
                    }
                }
                avgR = avgR/count;
                avgB = avgB/count;
                avgG = avgG/count; 
                Color newPixel = new Color(avgR, avgG, avgB);

                for(int k=i; k<i+pixelBlurAmount; k++){
                    for(int l=j; l<j+pixelBlurAmount; l++){
                        outputImage.setRGB(l, k, newPixel.getRGB());
                    }
                }
            }
        }
        return outputImage;
    }

    public static BufferedImage smoothBlur(BufferedImage inputImage, int smoothBlurAmount){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
 
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                int avgR = 0;
                int avgG = 0;
                int avgB = 0;
                int count = 0;
                for(int k=i-smoothBlurAmount/2; k<i+smoothBlurAmount/2 && k<height; k++){
                    for(int l=j-smoothBlurAmount/2; l<j+smoothBlurAmount/2 && l<width; l++){
                        if(k<0) k=0;
                        if(l<0) l=0;
                        Color pixel = new Color(inputImage.getRGB(l, k));
                        avgR += pixel.getRed();
                        avgB += pixel.getBlue();
                        avgG += pixel.getGreen();
                        count++;
                    }
                }
                avgR = avgR/count;
                avgB = avgB/count;
                avgG = avgG/count; 
                Color newPixel = new Color(avgR, avgG, avgB);
                
                outputImage.setRGB(j, i, newPixel.getRGB());
            }
        }
        return outputImage;
    }

    public static BufferedImage changeContrast(BufferedImage inputImage,int contrastPercentage){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
 
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                Color pixel = new Color(inputImage.getRGB(j, i));
                int red = pixel.getRed();
                int blue = pixel.getBlue();
                int green = pixel.getGreen();

                red = (int) (128 + ( (contrastPercentage+100) * (red - 128))/100);
                green = (int) (128 + ( (contrastPercentage+100) * (green - 128))/100);
                blue = (int) (128 + ( (contrastPercentage+100) * (blue - 128))/100);
                
                red = Math.max(0, Math.min(255, red));
                green = Math.max(0, Math.min(255, green));
                blue = Math.max(0, Math.min(255, blue));

                Color newPixel = new Color(red, green, blue);
                outputImage.setRGB(j, i, newPixel.getRGB());
            }
        }
        return outputImage;
    }

    public static BufferedImage changeSaturation(BufferedImage inputImage,int saturationChange){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
 
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                Color pixel = new Color(inputImage.getRGB(j, i));
                
                float[] hsl = Color.RGBtoHSB(pixel.getRed(), pixel.getGreen(), pixel.getBlue(), null);

                hsl[1] *= ( saturationChange+100 )/100;

                hsl[1] = Math.max(0, Math.min(1, hsl[1]));

                int rgb = Color.HSBtoRGB(hsl[0], hsl[1], hsl[2]);

                outputImage.setRGB(j, i, rgb);
            }
        }
        return outputImage;
    }

    public static BufferedImage createNegativeImage(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
 
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                Color pixel = new Color(inputImage.getRGB(j, i));
                int red = 255 - pixel.getRed();
                int green = 255 - pixel.getGreen();
                int blue = 255 - pixel.getBlue();

                Color newPixel = new Color(red, green, blue);
                outputImage.setRGB(j, i, newPixel.getRGB());
            }
        }
        return outputImage;
    }

    public static BufferedImage applyFilter(BufferedImage inputImage, int filterType) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(inputImage.getRGB(j, i));
                int red = pixel.getRed() ;
                int green = pixel.getGreen() ;
                int blue = pixel.getBlue() ;

                switch (filterType) {
                    case 1:
                        red = pixel.getRed() + 50;
                        green = pixel.getGreen() - 50;
                        blue = pixel.getBlue() - 50;
                        break;
                    case 2:
                        red = pixel.getRed() - 50;
                        green = pixel.getGreen() - 50;
                        blue = pixel.getBlue() + 50;
                        break;
                    case 3:
                        red = pixel.getRed() - 50;
                        green = pixel.getGreen() + 50;
                        blue = pixel.getBlue() - 50;
                        break;
                    default:
                }

                red = Math.max(0, Math.min(255, red));
                green = Math.max(0, Math.min(255, green));
                blue = Math.max(0, Math.min(255, blue));

                Color newPixel = new Color(red, green, blue);
                outputImage.setRGB(j, i, newPixel.getRGB());
            }
        }

        return outputImage;
    }

    public static BufferedImage imageEgdeDetection(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
 
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 2; i < height; i++) {
            for (int j = 2; j < width; j++) {
                Color pixel1 = new Color(inputImage.getRGB(j, i));
                Color pixel2 = new Color(inputImage.getRGB(j-2, i-2));

                int redDiff = (pixel1.getRed() - pixel2.getRed());
                int greenDiff = (pixel1.getGreen() - pixel2.getGreen());
                int blueDiff = (pixel1.getBlue() - pixel2.getBlue());

                redDiff = Math.max(0, Math.min(255, redDiff));
                greenDiff = Math.max(0, Math.min(255, greenDiff));
                blueDiff = Math.max(0, Math.min(255, blueDiff));

                Color diffColor = new Color(redDiff, greenDiff, blueDiff);

                outputImage.setRGB(j-2, i-2, diffColor.getRGB());
            }
        }
        return outputImage;
    }
    public static void main(String Args[])throws IOException{
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("\nPlease provide input image pathname : ");
            String inputPath = sc.next();

            if(!inputPath.toLowerCase().endsWith(".jpg")){ 
                System.out.println("\nGiven file is not of .jpg format!!");
                continue;
            }

            File inputFile = new File(inputPath);
            BufferedImage inputImage = ImageIO.read(inputFile);
            BufferedImage outputImage = inputImage;

            System.out.println("\nPlease select the operation to be performed : \n");
            System.out.println("Select 1 for grayscaling the image. ");
            System.out.println("Select 2 for rotating image by 90 degree clockwise. ");
            System.out.println("Select 3 for rotating image by 90 degree Anticlockwise. ");
            System.out.println("Select 4 for horizontal inverting of image. ");
            System.out.println("Select 5 for vertical inverting of image. ");
            System.out.println("Select 6 for changing brightness of image. ");
            System.out.println("Select 7 for pixel blurring the image. ");
            System.out.println("Select 8 for smooth blurring the image. ");
            System.out.println("Select 9 for changing contrast of image. ");
            System.out.println("Select 10 for changing saturation of image. ");
            System.out.println("Select 11 for creating negative image of given image. ");
            System.out.println("Select 12 for applying a filter(Red, Blue, Green) on image. ");
            System.out.println("Select 13 for edge detection of image. ");
            System.out.println("Select 14 to Exit ");
            int option = sc.nextInt();

            switch(option){
                case 1:
                    outputImage = convertToGray(inputImage);
                    break;
                case 2:
                    outputImage = rotate90Clockwise(inputImage);
                    break;
                case 3:
                    outputImage = rotate90AntiClockwise(inputImage);
                    break;
                case 4:
                    outputImage = horizInvert(inputImage);
                    break;
                case 5:
                    outputImage = vertInvert(inputImage);
                    break;
                case 6:
                    System.out.println("Please input percentage by which you want to change brightness : ");
                    int changePercentage = sc.nextInt();
                    outputImage = changeBrightness(inputImage, changePercentage);
                    break;
                case 7:
                    System.out.println("Please input pixels by which you want to blur the image : ");
                    int pixelBlurAmount = sc.nextInt();
                    outputImage = pixelBlur(inputImage, pixelBlurAmount);
                    break;
                case 8:
                    System.out.println("Please input pixels by which you want to blur the image : ");
                    int smoothBlurAmount = sc.nextInt();
                    outputImage = smoothBlur(inputImage, smoothBlurAmount);
                    break;
                case 9:
                    System.out.println("Please input percentage by which you want to change contrast : ");
                    int contrastPercentage = sc.nextInt();
                    outputImage = changeContrast(inputImage, contrastPercentage);
                    break;
                case 10:
                    System.out.println("Please input percentage by which you want to change saturation : ");
                    int saturationPercentage = sc.nextInt();
                    outputImage = changeSaturation(inputImage, saturationPercentage);
                    break;
                case 11:
                    outputImage = createNegativeImage(inputImage);
                    break;
                case 12:
                    System.out.println("\nSelect filter : \n1. Red \n2. Blue \n3. Green\n");
                    int filterType = sc.nextInt();
                    outputImage = applyFilter(inputImage, filterType);
                    break;
                case 13:
                    outputImage = imageEgdeDetection(inputImage);
                    break;
                case 14:
                    System.exit(0);
                default:
                    System.out.println("\n INVALID OPTION \n");
            }

            System.out.println("\nPlease enter output file path : ");
            String outputPath = sc.next();
            File outputFile = new File(outputPath);

            if(outputPath.toLowerCase().endsWith(".jpg")){ 
                System.out.println("\nYour file has been saved");
            }
            
            ImageIO.write(outputImage, "jpg", outputFile);
        }
    }
}
