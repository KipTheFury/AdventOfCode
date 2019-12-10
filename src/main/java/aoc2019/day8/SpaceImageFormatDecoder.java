package aoc2019.day8;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpaceImageFormatDecoder {

    private static final Logger log = LoggerFactory.getLogger(SpaceImageFormatDecoder.class);

    public Image decodeImage(String input, int height, int width) {

        int inputLength = input.length();
        int layerCount = inputLength / (height * width);

        if (inputLength % layerCount != 0) {
            log.error("Input length does not fid the dimensions");
        }

        Image image = new Image();

        char[] chars = input.toCharArray();

        int startIndex = 0;
        for (int i = 0; i < layerCount; i++) {

            Layer layer = new Layer();
            char[] subarray = ArrayUtils.subarray(chars, startIndex, startIndex + (width * height));

            Row rowOne = new Row();

            // Populate top row
            for (int j = 0; j < width; j++) {
                rowOne.characters.add(subarray[j]);
            }
            layer.rows.put(0, rowOne);

            int rowIndex = 1;

            for (int k = width; k < (width * height); k++) {
                Row row = layer.rows.getOrDefault(rowIndex, new Row());
                row.characters.add(subarray[k]);
                layer.rows.put(rowIndex, row);

                rowIndex++;
                if (rowIndex > height - 1) {
                    rowIndex = 1;
                }
            }

            image.layers.put(i, layer);
            startIndex += width * height;
        }
        return image;
    }

    public class Image {
        Map<Integer, Layer> layers = new HashMap<>();
    }

    public class Layer {
        Map<Integer, Row> rows = new HashMap<>();
    }

    public class Row {
        List<Character> characters = new ArrayList<>();
    }

}
