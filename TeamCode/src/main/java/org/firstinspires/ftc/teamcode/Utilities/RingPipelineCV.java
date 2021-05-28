package org.firstinspires.ftc.teamcode.Utilities;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class RingPipelineCV extends OpenCvPipeline {
    // Enum to define how many rings are
    public enum RingPosition {
        FOUR,
        ONE,
        NONE
    }

    // Color constants
    static final Scalar blue = new Scalar(0, 0, 255);
    static final Scalar green = new Scalar(0, 255, 0);

    // The core values which define the location and size of the sample regions
    static final Point region1TopleftAnchorPoint = new Point(181,98);

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

    // Volatile since accessed by OpMode thread w/o synchronization
    private volatile RingPosition position = RingPosition.FOUR;

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

        position = RingPosition.FOUR; // Record our analysis
        if(avg1 > fourTingThreshold)        position = RingPosition.FOUR;
        else if (avg1 > oneRingThreshold)   position = RingPosition.ONE;
        else                                position = RingPosition.NONE;

        Imgproc.rectangle(
                input, // Buffer to draw on
                region1_pointA, // First point which defines the rectangle
                region1_pointB, // Second point which defines the rectangle
                green, // The color the rectangle is drawn in
                -1); // Negative thickness means solid fill

        return input;
    }

    public int getResult() { return avg1; }
}
