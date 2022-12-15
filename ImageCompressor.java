/*
This is the client of the KmeansPicture class, which includes a KmeansPicture
ADT and a KmeansPixel ADT. This client intakes an image in the format of a
png file, and it outputs the compressed image.
 */

import java.awt.Color;

public class ImageCompressor {

    // Experimental Performance Analysis - for extra credit :)
    public static String percentCompression(int numClassifiers, Picture originalPicture) {
        int r;
        int g;
        int b;
        int distinctVal;
        int counter = 0;
        boolean[] arr = new boolean[256 * 256 * 256];
        for (int col = 0; col < originalPicture.width(); col++) {
            for (int row = 0; row < originalPicture.height(); row++) {
                Color color = originalPicture.get(col, row);
                r = color.getRed();
                b = 256 * 256 * color.getBlue();
                g = 256 * color.getGreen();
                distinctVal = r + b + g;
                if (!arr[distinctVal]) {
                    counter++;
                    arr[distinctVal] = true;
                }
            }
        }
        double out = (double) (counter - numClassifiers) / counter * 100;
        return "Congrats! You have reduced the memory requirements of this image by " + out +
                "% by using this data type."
                + "You started with " + counter + " distinct colors, and now your image"
                + " only requires " + numClassifiers + ". Way to go!";


    }


    public static void main(String[] args) {
        String picturename = args[0];
        int numberClassifiers = Integer.parseInt(args[1]);
        Picture pic = new Picture(picturename);
        int height = pic.height();
        int width = pic.width();

        int numPixels = width * height;

        // create new array to store our pixels
        // corresponds to our original picture
        KmeansPicture newPicture = new KmeansPicture(numPixels, width, height, numberClassifiers);

        // assign original colors to the pictures
        newPicture.initialImage(pic, newPicture);

        // test
        Picture returned = new Picture(newPicture.output(newPicture));
        returned.show();


        // calculate random centroids
        newPicture.assignCentroid(newPicture);
        // assign classifiers to these centroids
        newPicture.assignClassifiers(newPicture);
        // calculate mean value of each cluseter
        newPicture.calculateMean2(newPicture);
        // set centroid to mean value
        newPicture.centroidToMean(newPicture);


        // assign pixels to their classifier
        newPicture.setPix(newPicture);


        // newPicture.setPix(newPicture);
        Picture output = new Picture(newPicture.output(newPicture));
        output.show();

        StdOut.println(percentCompression(numberClassifiers, pic));

    }
}
