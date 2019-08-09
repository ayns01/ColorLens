# ColorLens

⬇️Download This App!<br>
https://play.google.com/store/apps/details?id=com.peenats.ayana.colorlens

![topdisplay](https://user-images.githubusercontent.com/18627505/62756705-a8374580-ba2d-11e9-8032-b32badb4e18b.png)

## About
This is a photo editor app for Android.
It can make photo like CHEKI photo. You can decorate your photo with colorlens's filter, photo frame and doodle by finger.

## Development
I am using Java for this app. I am new to Java language. So that as a challenge I decided to do a Java project. 
However on I/O 2019 event, Google announced Android development would become increasingly Kotlin based. So I decided to make Android app using Kotlin next time.
Also Google I/O announced new Android Jetpack. I didn't use any function in this project from Jetpack. But I need to catch up on all features of Jetpack.

### Core functions of ColorLens
**✂︎Cropping**<br>
It is implemented by SimpleCropView library. I just set cropMode, frameScale and ShowMode. It was super easy to use and beautiful!
This is the code.
```
mCropView = findViewById(R.id.cropImageView);
mCropView.setCropMode(CropImageView.CropMode.SQUARE);
mCropView.setInitialFrameScale(0.75f);
mCropView.setGuideShowMode(CropImageView.ShowMode.SHOW_ON_TOUCH);
```
![colorlensCropping](https://user-images.githubusercontent.com/18627505/60845784-423b7200-a192-11e9-9402-0bb95b05fa6e.png)

**📸Colorlens Filter**<br>
 This is the main function in this app. To make the colorfilter, I used matrix of rgba.
 For example, this is the code to change the color to red. 
```
float[] red_colour_matrix = {
    3f, 0, 0, 0, 0,
    0, 1, 0, 0, 0,
    0, 0, 1, 0, 0,
    0, 0, 0, 1, 0
};
```
![colorlensColorFilter](https://user-images.githubusercontent.com/18627505/60846710-208fba00-a195-11e9-9c99-3f5c9574cfd9.png)

**🔳Frame**<br>
I was particularly concerned about these frames. I wanted to bring these frames close to real CHEKI frame.
I made these frames using Sketch. Then I exported them for desired density (1x, 1.5x, 2x, 3x, 4x).
Then I set them to each density folder.Drawable directory looks like below image.<br>
<img width="209" alt="drawableDir" src="https://user-images.githubusercontent.com/18627505/60847703-83368500-a198-11e9-83f0-f6c96ee65405.png">
<br>![colorlensFrame](https://user-images.githubusercontent.com/18627505/60847552-d956f880-a197-11e9-912c-aa0aab27119e.png)

**👇Doodle**<br>
I wanted to make the photo editor app, can be doodled by finger.
It was not hard to implement but a little bit complicated.
To draw some line on device by finger, I had to override onTouchEvent method (It can detect 3 actions of finger: down, move and up).
I added the path and paint into ArrayList everytime being drown. 
Then I overrided onDraw method to draw paths inside ArrayList on Canvas. I called onDraw method whenever drawing each line.
```
@Override
public boolean onTouchEvent( MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startPath(x, y);
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            updatePath(x, y);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
        }
        invalidate();
        
    return true;
}
```
![colorlensDoodle](https://user-images.githubusercontent.com/18627505/60862674-21474100-a1d3-11e9-86d8-c263f9e45cca.png)

### Libraries that I used
**Glide**<br>
Fetching images fast and efficient, this app is using Glide.<br>
![glide_logo](https://user-images.githubusercontent.com/18627505/60791823-4b3c2d00-a119-11e9-8b1f-2dc9c7a68594.png)

**SimpleCropView**<br> 
Cropping an image from gallery, I am using this library. 
The reason why I decided to use this is that this library has a simple and nice view. Also It is easy to use.<br> 
![simpleCropView](https://user-images.githubusercontent.com/18627505/60791796-3b244d80-a119-11e9-8830-e83dd9d08f18.png)
