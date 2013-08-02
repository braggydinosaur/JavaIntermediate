
public class PixelEffects {

	public static int[][] copy(int[][] source) {
		return resize(source,source.length, source[0].length);
		
		

	}
	/**
	 * Resize the array image to the new width and height
	 * You are going to need to figure out how to map between a pixel
	 * in the destination image to a pixel in the source image
	 * @param source
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static int[][] resize(int[][] source, int newWidth, int newHeight) {
		int width = source.length;
		int height = source[0].length;
		int[][] output = new int[newWidth][newHeight];
		
		for (int x = 0 ; x < newWidth ; x++) {
			for (int y = 0 ; y < newHeight ; y++) {
				output[x][y] = source[x * width ]/[y * 2]; 
			}
		}
	}

	public static int[][] half(int[][] source) {
		int width = source. length;
		int height = source[0].length;
		return resize(source, width / 2, height / 2);
	
	}
	
	public static int[][] resize(int[][] source, int[][] reference) {
		int width = reference.length;
		int height = reference[0].length;
		return resize(source, width, height);
	}

	/**
	 *	Flips the image over the x-axis.
	 */
	public static int[][] flip(int[][] source) {
		int width = source.length;
		int height = source[0].length;
		int[][] output = new int[width][height];
		
		for (int x = 0 ; x < width ; x++) {
			for (int y = 0 ; y < height ; y++) {
				output[x][y] = source[width - x - 1][y];
			}
		}
		return output;
	}

	/**
	 *	Mirrors the image over the y-axis.
	 */
	public static int[][] mirror(int[][] source) {
		return source;
	}

	/**
	 *	Rotates the image left.
	 */
	public static int[][] rotateLeft(int[][] source) {
		return rotate(rotate(rotate(source)));
	}

	/**
	 *	Rotates the image right.
	 */
	public static int[][] rotate(int[][] source) {
		int width = source.length;
		int height = source[0].length;
		int[][] output = new int[height][width];
		
		for (int x = 0 ; x < width ; x++) {
			for (int y = 0 ; y < width ; y++) {
				output[x][y] = source[y][x];
			}
		}
		
		return flip(output);
	}
	
	/** 
	 * Merge the red,blue,green components from two images.
	 */
	public static int[][] merge(int[][] sourceA, int[][] sourceB) {
		int width = sourceA.length;
		int height = sourceA[0].length;
		int[][] output = new int[width][height];
		sourceB = resize(sourceB, sourceA);
		
		for (int x = 0 ; x < width ; x++) {
			for (int y = 0 ; y < height ; y++) {
				int pixelA = sourceA[x][y];
				int pixelB = sourceB[x][y];
				
				int redA = RGB.toRed(pixelA);
				int greenA = RGB.toGreen(pixelA);
				int blueA = RGB.toBlue(pixelA);
				
				int redB = RGB.toRed(pixelB);
				int greenB = RGB.toGreen(pixelB);
				int blueB = RGB.toBlue(pixelB);
				
				int redAverage = (redA + redB) / 2;
				int greenAverage = (greenA + greenB) / 2;
				int blueAverage = (blueA + blueB) / 2;
				
				output[x][y] = RGB.toRGB(redAverage, greenAverage, blueAverage) ;
			}
		}
		return output; 
	}
	/**
	 * Replace the green areas of the foreground image with parts of the back
	 * image.
	 */
	public static int[][] chromaKey(int[][] foreImage, int[][] backImage) {
		int width = foreImage.length;
		int height = foreImage[0].length;
		int [][] output = new int[width][height];
		backImage = resize(backImage, foreImage);
		
		for (int x = 0 ; x < width ; x++) {
			for (int y = 0 ; y < height ; y++) {
				int pixel = foreImage[x][y];
				int red = RGB.toRed(pixel);
				int green = RGB.toGreen(pixel);
				int blue = RGB.toBlue(pixel);
				
				//if (green >
				if (green > 200 && red < 150 && blue < 150) {
					output [x][y] = backImage[x][y];
				}
				else {
					output[x][y] = foreImage[x][y];
						
				
		

					
				}
			}
		}
		
		return output;
	}
	public static int[][] vignette(int[][] source) {
		int width = source.length;
		int height = source[0].length;
		int[][] output = new int[width][height];
		for(int x = 0 ; x < width ; x++) {
			for (int y = 0 ; y < height ; y++) {
				int pixel = source [x][y];
				int dx = x = x - width / 2;
				int dy = y - height / 2;
				int distance = (int) Math.sqrt(dx * dx + dy * dy) / 25;
				int red = RGB.toRed(pixel) - distance;
				if (red < 0) {
					red = 0;
				}
				int green = RGB.toGreen(pixel) - distance;
				if (green < 0) {
					green = 0;
				}
				int blue = RGB.toBlue(pixel) - distance;
				blue = (blue < 0) ? 0 : blue;
				
				output[x][y] = RGB.toRGB(red,  green,  blue);
			}
		}
		return output;
	}
				
					
				}
			}
		}
	}
 
	/** 
	 *	Remove redeye. Note that sourceB is not used.
	 */
	public static int[][] redeye(int[][] source, int[][] sourceB) {
		int width = source.length;
		int height = source[0].length;
		int[][] result = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = source[i][j];
				int red = RGB.toRed(rgb);
				int green = RGB.toGreen(rgb);
				int blue = RGB.toBlue(rgb);
				if (red > 4 * Math.max(green, blue) && red > 64) {
					red = green = blue = 0;
				}
				result[i][j] = RGB.toRGB(red, green, blue);
			}
		}
		return result;
	}

	/**
	 *	Add some funk to the image.
	 */
	public static int[][] funky(int[][] source, int[][] sourceB) {
		int width = source.length;
		int height = source[0].length;
		int[][] output = new int[width][height];
	
		for (int x = 0 ; x < width ; x++) {
			for (int y = 0 ; y < height ; y++) {
				int pixel = source[x][y] ;
				int red = RGB.toRed(pixel) ;
				int green = RGB.toGreen(pixel) ; 
				int blue = RGB.toBlue(pixel) ;
				output[x][y] = RGB.toRGB((red + green) / 2, (green + blue) /2, (blue + red) / 2); 
			}
		}
		return output;
	}
}
				
				