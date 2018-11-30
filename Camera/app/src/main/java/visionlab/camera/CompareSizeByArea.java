package visionlab.camera;

import android.util.Size;

import java.util.Comparator;

/**
 * Created by Amrendu Kumar on 29,November,2018
 * vision Labs,
 * Bangalore, IN.
 */
public class CompareSizeByArea implements Comparator<Size> {
    @Override
    public int compare(Size lhs, Size rhs) {
        return Long.signum( (long)(lhs.getWidth() * lhs.getHeight()) -
                (long)(rhs.getWidth() * rhs.getHeight()));    }
}
