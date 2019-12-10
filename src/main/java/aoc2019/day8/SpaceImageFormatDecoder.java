package aoc2019.day8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpaceImageFormatDecoder {

    private static final Logger log = LoggerFactory.getLogger(SpaceImageFormatDecoder.class);

    public int[][][] decodeImage(String input, int layers, int img_height, int img_width) {

        int img_size = img_width * img_height;

        //Image array = layer, height, width
        int[][][] image = new int[layers][img_height][img_width];

        //Parse image to array
        for (int layer = 0; layer < layers; layer++) {
            for (int height = 0; height < img_height; height++) {
                for (int width = 0; width < img_width; width++) {
                    int pointer = (layer * img_size) + (height * img_width) + width;
                    image[layer][height][width] = Character.getNumericValue(input.charAt(pointer));
                }
            }
        }

        return image;
    }

    public int getImageChecksum(int[][][] image, int layers, int img_height, int img_width) {
        int minNumberOfZeroes = 99999;
        int layerWithFewestZeroes = -1;

        for (int layer = 0; layer < layers; layer++) {
            int zeroes = 0;
            for (int height = 0; height < img_height; height++) {
                for (int width = 0; width < img_width; width++) {
                    if (image[layer][height][width] == 0) {
                        zeroes++;
                    }
                }
            }

            if (zeroes < minNumberOfZeroes) {
                layerWithFewestZeroes = layer;
                minNumberOfZeroes = zeroes;
            }
        }

        int numberOfOnes = 0;
        int numberOfTwos = 0;

        for (int height = 0; height < img_height; height++) {
            for (int width = 0; width < img_width; width++) {
                if (image[layerWithFewestZeroes][height][width] == 1) {
                    numberOfOnes++;
                }
                if (image[layerWithFewestZeroes][height][width] == 2) {
                    numberOfTwos++;
                }
            }
        }

        return numberOfOnes * numberOfTwos;
    }

    public void renderImage(int[][][] image, int layers, int img_height, int img_width) {

        int[][] finalImage = new int[img_height][img_width];

        //Compress layers ignoring 2's
        for (int width = 0; width < img_width; width++) {
            for (int height = 0; height < img_height; height++) {
                for (int layer = 0; layer < layers; layer++) {
                    if (image[layer][height][width] != 2) {
                        finalImage[height][width] = image[layer][height][width];
                        break;
                    }
                }
            }
        }

        for (int height = 0; height < img_height; height++) {
            for (int width = 0; width < img_width; width++) {
                if (finalImage[height][width] == 0) {
                    System.out.print("  ");
                }
                if (finalImage[height][width] == 1) {
                    System.out.print("X ");
                }
            }
            System.out.print('\n');
        }
    }
}
