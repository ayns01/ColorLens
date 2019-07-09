# ColorLens
Photo Editor App 📷<br>

![cameraLens2](https://user-images.githubusercontent.com/18627505/60787909-27281e00-a110-11e9-812f-1d9359dd0c62.png)

## About
This is photo editor app for Android.
It can make photo like CHEKI photo. You can decorate your photo with colorlens filter, photo frame and doodle by finger.

## Development
I am using Java for this app. I was new to use Java in project. That is why I decide to use this language. 
However on I/O 2019 event, Google announced Android development will become increasingly Kotlin-first. So I want to make Android app using Kotlin next time.
Also they announced new Android Jetpack. I didn't use any function in this project. But I need to catch up on all feature of Jetpack.

### Core function of ColorLens
**✂︎Cropping**<br>
It is implemented by SimpleCropView library. I just set cropMode, frameScale and ShowMode. It was super easy and beauty!
The code is like below.
```
mCropView = findViewById(R.id.cropImageView);
mCropView.setCropMode(CropImageView.CropMode.SQUARE);
mCropView.setInitialFrameScale(0.75f);
mCropView.setGuideShowMode(CropImageView.ShowMode.SHOW_ON_TOUCH);
```
![colorlensCropping](https://user-images.githubusercontent.com/18627505/60845784-423b7200-a192-11e9-9402-0bb95b05fa6e.png)

**📸Colorlens Filter**<br>
I could say this is the main function in this app. To make this colorfilter, I used matrix of rgba.
For example, when turning color of photo into red, code is like below.
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
I was particular about this frames. I wanted to bring this frame close to real CHEKI frame.
I made these frames using Sketch. Then I exported them for any density (1x, 1.5x, 2x, 3x, 4x).
Then I set them to each density folder.Drawable directory is like below.<br>
<img width="209" alt="drawableDir" src="https://user-images.githubusercontent.com/18627505/60847703-83368500-a198-11e9-83f0-f6c96ee65405.png">
<br>![colorlensFrame](https://user-images.githubusercontent.com/18627505/60847552-d956f880-a197-11e9-912c-aa0aab27119e.png)

**👇Doodle**<br>
I wanted photo editor app which can doodle by finger.
It was not hard to implement but a little bit complicated.
To draw some line on device by finger, I needed to override onTouchEvent method (It can detect 3 actions of finger: down, move and up).
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
