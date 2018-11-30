package visionlab.camera;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by Amrendu Kumar on 30,November,2018
 * vision Labs,
 * Bangalore, IN.
 */
public class ImageSaver implements Runnable {
    private final Image mImage;
    private String mImageFileName;
    private Activity mActivity;


    public ImageSaver(Image image,String imageFileName,Activity activity) {
        mImage = image;
        mImageFileName =imageFileName;
        mActivity=activity;
    }
    @Override
    public void run() {
        ByteBuffer byteBuffer = mImage.getPlanes()[0].getBuffer();
        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(mImageFileName);
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            mImage.close();

            Intent mediaStoreUpdateIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            mediaStoreUpdateIntent.setData(Uri.fromFile(new File(mImageFileName)));
            mActivity.sendBroadcast(mediaStoreUpdateIntent);

            if(fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
