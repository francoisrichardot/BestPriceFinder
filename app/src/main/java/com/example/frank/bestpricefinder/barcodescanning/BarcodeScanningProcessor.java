package com.example.frank.bestpricefinder.barcodescanning;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.frank.bestpricefinder.GlobalAppArgs;
import com.example.frank.bestpricefinder.MainActivity;
import com.example.frank.bestpricefinder.R;
import com.example.frank.bestpricefinder.ScanResultsFragment;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.example.frank.bestpricefinder.camerautility.CameraImageGraphic;
import com.example.frank.bestpricefinder.camerautility.FrameMetadata;
import com.example.frank.bestpricefinder.camerautility.GraphicOverlay;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;



/**
 * Barcode Detector Demo.
 */
public class BarcodeScanningProcessor extends VisionProcessorBase<List<FirebaseVisionBarcode>> {

    private static final String TAG = "BarcodeScanProc";

    private final FirebaseVisionBarcodeDetector detector;

    Fragment fragment = null;
    Context m_Context;







    public BarcodeScanningProcessor(Context context) {
        // Note that if you know which format of barcode your app is dealing with, detection will be
        // faster to specify the supported barcode formats one by one, e.g.
        // new FirebaseVisionBarcodeDetectorOptions.Builder()
        //     .setBarcodeFormats(FirebaseVisionBarcode.FORMAT_QR_CODE)
        //     .build();
        detector = FirebaseVision.getInstance().getVisionBarcodeDetector();
        this.m_Context = context;
    }

    @Override
    public void stop() {
        try {
            detector.close();
        } catch (IOException e) {
            Log.e(TAG, "Exception thrown while trying to close Barcode Detector: " + e);
        }
    }

    @Override
    protected Task<List<FirebaseVisionBarcode>> detectInImage(FirebaseVisionImage image) {
        return detector.detectInImage(image);
    }

    @Override
    protected void onSuccess(
            @Nullable Bitmap originalCameraImage,
            @NonNull List<FirebaseVisionBarcode> barcodes,
            @NonNull FrameMetadata frameMetadata,
            @NonNull GraphicOverlay graphicOverlay) {
        graphicOverlay.clear();
        if (originalCameraImage != null) {
            CameraImageGraphic imageGraphic = new CameraImageGraphic(graphicOverlay, originalCameraImage);
            graphicOverlay.add(imageGraphic);
        }
        for (int i = 0; i < barcodes.size(); ++i) {
            FirebaseVisionBarcode barcode = barcodes.get(i);
            BarcodeGraphic barcodeGraphic = new BarcodeGraphic(graphicOverlay, barcode);
            graphicOverlay.add(barcodeGraphic);
            Log.e(TAG, "Barcode detected: " + barcode.getRawValue());
            fragment = new ScanResultsFragment();
            Log.e("BARCODEPROCES context ", "" + m_Context);
            FragmentManager fragmentManager = ((AppCompatActivity)m_Context).getSupportFragmentManager();

           // Log.e("BARCODEPROCESf context ", "" + fragment.getContext().toString());

            int fragmentTransaction = fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

        }
        graphicOverlay.postInvalidate();




    }

    @Override
    protected void onFailure(@NonNull Exception e) {
        Log.e(TAG, "Barcode detection failed " + e);
    }


}

