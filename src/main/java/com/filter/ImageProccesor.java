package com.filter;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ImageProccesor {

    public File selectImageFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            if (isValidImageFile(selectedFile)) {
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                return selectedFile;
            } else {
                System.out.println("Error: Selected file is not a valid image.");

            }
        } else {
            System.out.println("No file selected.");
        }
        return null;
    }

        public boolean isValidImageFile(File file){
            try{
                BufferedImage image = ImageIO.read(file);
                return image != null;
            } catch (IOException e){
                return false;
            }
        }

        public File selectDestinationDirectory(){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select destination directory");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int result = fileChooser.showSaveDialog(null);

            if(result == JFileChooser.APPROVE_OPTION){
                File selectedDirectory = fileChooser.getSelectedFile();
                System.out.println("Selected directory" + selectedDirectory);
                return selectedDirectory;
            } else{
                System.out.println("No directory selected");
                return null;
            }
        }

    public static void processAndSaveAsBlackAndWhite(File inputImageFile, File outputDirectory) {
        try {
            // Read the original image
            BufferedImage originalImage = ImageIO.read(inputImageFile);

            // Process the image to black and white
            BufferedImage blackAndWhiteImage = convertToBlackAndWhite(originalImage);

            // Create the output directory if it doesn't exist
            if (!outputDirectory.exists()) {
                outputDirectory.mkdirs();
            }

            // Save the processed image to the specified directory
            File outputImageFile = new File(outputDirectory, "black_and_white_" + inputImageFile.getName());
            ImageIO.write(blackAndWhiteImage, "png", outputImageFile);

            System.out.println("Black and white image saved: " + outputImageFile.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public void selectAndProcessImage(){
            File inputImage = selectImageFile();

            if(inputImage != null){
                File outputDirectory = selectDestinationDirectory();

                if (outputDirectory != null){
                    processAndSaveAsBlackAndWhite(inputImage, outputDirectory);
                }
            }
        }

        public static BufferedImage convertToBlackAndWhite(BufferedImage originalImage) {
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();

            BufferedImage blackAndWhiteImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
            Graphics g = blackAndWhiteImage.getGraphics();
            g.drawImage(originalImage, 0, 0, null);
            g.dispose();

            return blackAndWhiteImage;
        }
    }
