THIS IS CLAIRE AND SOPHIA'S FINAL PROJECT FOR COS.

Here is the proposal:

3. Project Description
What is your tentative project title? Image Compressor Prototype

Please summarize your project in 1 paragraph.

	Our project uses rudimentary k-means clustering skills in order to provide clients with the ideal data type: an image compressor that decreases the memory storage each image requires. First this project will create a k-means API, in which we train a dataset to group pixels together based on similar coloration. Then, according to their groups, a client will use these classifications to reassign color values to decrease the amount of memory required. This will also use this existing color ADT. 

Why did you choose this project idea and what do you hope to learn with it?

	We were interested in applying the skills that we’ve learned through this semester to an entirely new type of simple machine learning. K-means clustering is a new theoretical framework to which we can apply the coding skills we’ve developed. We hope to further our basic machine learning skills to form a foundation for future machine learning classes and projects.

4. Features and Project Requirements
If approved, the three features you outline in your proposal are binding; any changes to them must be approved by your project advisor. When describing each feature, try to be as specific as possible.





Feature #1: Kmeans Part 1: Extraction
In 2-3 sentences, please describe your feature.

	In this feature, which will be the first part of our kmeans algorithm, we will read in an image using the Picture object and extract all of the pixels from it. 

In 1-2 sentences, please describe how you plan to implement your feature (e.g., is it going to be a class, a collection of methods, a single method, …).

	We’ll make a picture class to read in the image and extract all of its pixels. To do so we will use the existing picture ADT, including its RGB getter methods. 

In 1-2 sentences, please describe how you plan to test your feature.

	We would test this feature using StdDraw. After reading in a picture, we can use the image.show() method to display it. If the image matches our desired image, then we know that we have implemented this feature properly. 

How would you best categorize your feature using the buckets paradigm? (choose one)
(X) Standard
( ) Sprinkle
( ) Sparkle

Explain in 1-2 sentences why you think it fits the chosen bucket.

We’ve had experience using the Picture ADT in the Ed lesson from Precept 11. We won’t really be doing anything new with this data type; we just plan to use the methods from the Picture ADT that we’ve previously studied for our own purposes. Therefore, we can classify this feature as a standard feature.

Feature #2: Kmeans Part 2: The Algorithm
In 2-3 sentences, please describe your feature.

The second feature of our project will be the Kmeans algorithm. We plan to use the following generalized API:

Constructor for our “kmeans pixel” ADT, which has a classifier and a Color

Looks something like this
Randomly assign k centroids. Store in an array () 
Calculate the distance between each point and each centroid ()
 Assign each point to the centroid to which it is nearest. This method will call on method 3 to do so. ()
Calculate the mean of each cluster ()
Reassign the centroid to that mean point ()
Repeat until the centroid is equal to (or within a specified range of) the mean of the cluster.


In 1-2 sentences, please describe how you plan to implement your feature (e.g., is it going to be a class, a collection of methods, a single method, …).

The implementation of this feature will be a Kmeans class (that includes a KMeansPixel class with an ADT constructor) that has the methods outlined above. We wrote a sample KmeansPixel class too that can be found above. 
	
In 1-2 sentences, please describe how you plan to test your feature.

	We plan to write code in our main() method that tests our kmeans algorithm. We will use write testing txt files with simple pixels which have known centroids and create a Picture object in main using those txt files. Then, we will run the program using our testing Picture object to make sure that the algorithm outputs the expected centroids.

How would you best categorize your feature using the buckets paradigm? (choose one)
( ) Standard
( ) Sprinkle
(X) Sparkle

Explain in 1-2 sentences why you think it fits the chosen bucket.

	Though this feature is built on a foundation that we’ve created with our skills in this class, it goes beyond anything we’ve covered this semester. This algorithm reshapes the plot in order to fit our data, calling on multiple ADTs and methods. We are also considering using plotting as a tool to calculate our centroids, which is another skill never called on in class. 


Feature #3:
In 2-3 sentences, please describe your feature.

Feature 3 will be our client to the picture and Kmeans APIs: the image compressor. The imageCompressor will include a method that goes through the array of KmeansPixels and reassigns color values (there will be k color values) based on the classification of each pixel that we have assigned in the second feature. This will then use piping or another method in order to produce a new photo as an output, this time a photo that requires less memory. Finally we will use StdDraw to print the picture.

In 1-2 sentences, please describe how you plan to implement your feature (e.g., is it going to be a class, a collection of methods, a single method, …).

This class will call on the array produced by the Kmeans API to reassign the color values of each pixel based on its classification group. This will consist of reassigning all KmeansPixels in each classification group to the RGB values of its centroid. Then, it will take this array of pixels and output it as the new image that requires less memory. This will require something along the lines of the following methods
Reassign each KmeansPixel in a classification group to the RGB value of its centroid()
StdDraw.show() the new image to test it
Output new pixels in main or pipe it into new jpeg file as the product


In 1-2 sentences, please describe how you plan to test your feature.

	Testing this feature will be fairly simple – we will feed in different images and compress them with various k values. Since we will have already tested our kmeans algorithm for correctness, we’ll mostly be testing to make sure that the images visually look like they’re compressing correctly.

How would you best categorize your feature using the buckets paradigm? (choose one)
( ) Standard
(X) Sprinkle
( ) Sparkle


Explain in 1-2 sentences why you think it fits the chosen bucket.

	The image printing and visualization component calls on StdDraw skills that we’ve learned in class, but the pixel reassignment with the kmeans pixel object that we created goes a step further than the material that we’ve covered.

Other requirements:
How will your project accept user input?


The program will take a k value from the command line. That k value will determine how many clusters are created (the number of colors used for the final product).

How will your project produce output?

It will produce a new list of pixels that create a lower-quality version of the original picture to conserve memory/storage space for the client. 

Describe the .java files you plan to write. Describe the purpose and functionality of each .java file.

Picture.java - feature 1 (extracts features)

Kmeans.java - feature 2 (assigns classifier to clusters based on calculations)

imageCompressor.java - feature 3  (creates new image based on classifications)


In 1 short paragraph describe what functionality you would add to your project if you could continue to work on the project for one extra month. (you won’t have to implement any of this)
