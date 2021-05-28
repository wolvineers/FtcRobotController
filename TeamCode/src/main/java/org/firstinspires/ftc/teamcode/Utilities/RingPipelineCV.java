package org.firstinspires.ftc.teamcode.Utilities;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class RingPipelineCV extends OpenCvPipeline {
    public int position = 0;

    // Color constants
    static final Scalar blue = new Scalar(0, 0, 255);
    static final Scalar green = new Scalar(0, 255, 0);

    // The core values which define the location and size of the sample regions
    static final Point region1TopleftAnchorPoint = new Point(220,140);

    static final int regionWidth = 35;
    static final int regionHeight = 25;

    final int fourTingThreshold = 150;
    final int oneRingThreshold = 135;

    Point region1_pointA = new Point(
            region1TopleftAnchorPoint.x,
            region1TopleftAnchorPoint.y);
    Point region1_pointB = new Point(
            region1TopleftAnchorPoint.x + regionWidth,
            region1TopleftAnchorPoint.y + regionHeight);

    // Working variables
    Mat region1_Cb;
    Mat YCrCb = new Mat();
    Mat Cb = new Mat();
    int avg1;

    // Function that takes de RGB frame, converts to YCrCB and extracts the CB channel
    void inputToCb(Mat input) {
        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
        Core.extractChannel(YCrCb, Cb, 1);
    }

    @Override
    public void init(Mat firstFrame)
    {
        inputToCb(firstFrame);

        region1_Cb = Cb.submat(new Rect(region1_pointA, region1_pointB));
    }

    @Override
    public Mat processFrame(Mat input)
    {
        inputToCb(input);

        avg1 = (int) Core.mean(region1_Cb).val[0];

        Imgproc.rectangle(
                input, // Buffer to draw on
                region1_pointA, // First point which defines the rectangle
                region1_pointB, // Second point which defines the rectangle
                blue, // The color the rectangle is drawn in
                2); // Thickness of the rectangle lines

        position = 0; // Record our analysis
        if(avg1 > fourTingThreshold)        position = 3;
        else if (avg1 > oneRingThreshold)   position = 2;
        else                                position = 1;

        Imgproc.rectangle(
                input, // Buffer to draw on
                region1_pointA, // First point which defines the rectangle
                region1_pointB, // Second point which defines the rectangle
                green, // The color the rectangle is drawn in
                -1); // Negative thickness means solid fill

        return input;
    }

    public int getPosition() {
        return position;
    }

    public int getResult() { return avg1; }
}
