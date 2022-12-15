import java.awt.Color;

/*
This class extracts the pixels, transforming each into a Kmeanspixel object
to construct the KmeansPicture ADT.
 */
public class KmeansPicture {


    // array to store our kmeanspixels
    // length (numPixels) is width * height
    private KmeansPixel[] pixels;

    private int width; // width of the picture
    private int height; // height of the picture
    private Color[] centroids; // array of colors that are the centroids
    private Color[] means; // means of pixels assigned to each centroid
    private int numClassifiers; // number of clusters inputed from StdIn

    // Pixel ADT constructor
    private class KmeansPixel {
        private Color color; // color of pixel
        private int classifier; // classification of pixel

        // constructor method for pixel
        public KmeansPixel(Color a) {
            this.color = a; // assigning color
        }
    }


    // ADT for the picture that will hold all the KmeansPixels of the image
    public KmeansPicture(int numPixels, int width, int height, int numClassifiers) {
        this.pixels = new KmeansPixel[numPixels]; // array is image's width * height
        Color a = new Color(0, 0, 0);
        for (int i = 0; i < numPixels; i++) {
            pixels[i] = new KmeansPixel(a); // initialize all pixel's color to Black
        }
        this.width = width;
        this.height = height;

        this.numClassifiers = numClassifiers;
        this.centroids = new Color[numClassifiers]; // will be one centroid for each classifier
        this.means = new Color[numClassifiers]; // one mean for each cluster of pixels
    }


    // method to fill each pixel with its initial color
    public void initialImage(Picture pic, KmeansPicture picture) {
        Color colo;
        int counter = 0;
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                colo = new Color(pic.getRGB(col, row));
                picture.pixels[counter].color = colo;
                counter++;
            }
        }
    }


    // method to convert a KMeansPicture back to a Picture ADT
    // also our means of testing the output of our algorithms
    public Picture output(KmeansPicture picture) {
        Picture output = new Picture(picture.width, picture.height);
        // nested for loop to reconstruct rows and columns from the pixel array
        for (int col = 0; col < picture.width; col++) {
            for (int row = 0; row < picture.height; row++) {
                // set each pixel the appropriate color in the pixels array
                output.set(col, row, picture.pixels[col * picture.height + row].color);
            }
        }
        return output; // should output the reconstructed image

    }


    // K-MEANS BELOW - FEATURE 2

    // randomly assigning the centroid - ran once and not again
    public void assignCentroid(KmeansPicture picture) {
        for (int i = 0; i < numClassifiers; i++) {
            // randomly chooses and assigns a color for each centroid
            centroids[i] = new Color(StdRandom.uniformInt(0, 255),
                                     StdRandom.uniformInt(0, 255),
                                     StdRandom.uniformInt(0, 255));

        }
    }

    // calculate distances between each pix and centroids and assign pix to closest centroid
    public void assignClassifiers(KmeansPicture picture) {
        double sum = 0.0;
        double distance = 0.0;
        double mindist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < this.pixels.length; i++) { // iterate through each pixel
            for (int k = 0; k < numClassifiers; k++) { // iterate through each centroid

                // calculate squared sum between each pixel and each centroid
                sum = Math.pow(pixels[i].color.getRed() - centroids[k].getRed(), 2) +
                        Math.pow(pixels[i].color.getGreen() - centroids[k].getGreen(), 2) +
                        Math.pow(pixels[i].color.getBlue() - centroids[k].getBlue(), 2);

                // calculate distance
                distance = Math.sqrt(sum);

                // if closest centroid to the pixel, set the pixel's classifier to that centroid
                if (distance < mindist) {
                    mindist = distance; // update the minimum distance
                    pixels[i].classifier = k; // assign classifier to closest centroid

                }
            }
            mindist = Double.POSITIVE_INFINITY; // reset distance

        }
    }
    

    // calculates mean value of each cluster
    public void calculateMean2(KmeansPicture picture) {
        Color newColor;
        int r = 0;
        int g = 0;
        int b = 0;

        // iterates through each pixel averaging its values with the mean
        // of the cluster it belongs to
        for (int i = 0; i < pixels.length; i++) {
            // if a mean has been calculated for the cluster
            if (means[pixels[i].classifier] != null) {
                // average each RGB value with the current mean of the cluster
                g = (means[pixels[i].classifier].getGreen() + pixels[i].color.getGreen()) / 2;
                r = (means[pixels[i].classifier].getRed() + pixels[i].color.getRed()) / 2;
                b = (means[pixels[i].classifier].getBlue() + pixels[i].color.getBlue()) / 2;

                // create a new mean color using these averaged values
                newColor = new Color(r, g, b);

                // reassign the mean of that cluster to the new average
                means[pixels[i].classifier] = newColor;
            }

            // if there isn't already a mean of a cluster set the average to the pixel
            else {
                g = pixels[i].color.getGreen();
                r = pixels[i].color.getRed();
                b = pixels[i].color.getBlue();
                newColor = new Color(r, g, b);

                // if first pixel encountered from a cluster, set cluster's mean to the pixel's value
                means[pixels[i].classifier] = newColor;
            }
        }
    }


    // assigns centroid of each cluster to the mean of that cluster
    public void centroidToMean(KmeansPicture picture) {
        // the means should converge to the centroids
        for (int i = 0; i < numClassifiers; i++) {
            centroids[i] = means[i];
        }

    }


    // method to set each pixel to its final color
    public void setPix(KmeansPicture picture) {
        for (int i = 0; i < picture.pixels.length; i++) {
            pixels[i].color = this.centroids[pixels[i].classifier];
        }
    }


    // main method to test methods and implement the image compression
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


        Color colo;
        int counter = 0;
        newPicture.initialImage(pic, newPicture);
        Picture returned = new Picture(newPicture.output(newPicture));
        returned.show();


        newPicture.assignCentroid(newPicture);
        newPicture.assignClassifiers(newPicture);
        for (int i = 0; i < 4; i++) {
            newPicture.calculateMean2(newPicture);
            newPicture.centroidToMean(newPicture);
        }

        newPicture.setPix(newPicture);

        Picture newly = new Picture(newPicture.output(newPicture));
        newly.show();


    }
}
