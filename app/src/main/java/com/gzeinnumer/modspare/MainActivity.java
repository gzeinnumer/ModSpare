package com.gzeinnumer.modspare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "safaasfaagagags";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<UOMConvert> list = new ArrayList<>();
        list.add(new UOMConvert(0, 120, "CTN"));
        list.add(new UOMConvert(0, 6, "HGR"));
        list.add(new UOMConvert(110, 1, "PCS"));

        List<UOMConvertResult> res = uomConverter(list);

        String msg = "";
        String clearMsg = "";
        for (UOMConvertResult s : res) {
            msg += s.name;
            if (s.newValue != 0)
                clearMsg += s.name;
        }
        Log.d(TAG, "onCreate: " + msg);
        Log.d(TAG, "onCreate: " + clearMsg);
    }

    private List<UOMConvertResult> uomConverter(List<UOMConvert> list) {
        int[] arrayPcs = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            UOMConvert d = list.get(i);
            int buy = d.buy;
            int inPcs = d.inPcs;
            arrayPcs[i] = buy * inPcs;
        }

        int allInPcs = 0;
        for (int d : arrayPcs) {
            allInPcs += d;
        }

        List<UOMConvertResult> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            UOMConvert d = list.get(i);
            int total = allInPcs / d.inPcs;
            res.add(new UOMConvertResult(total + " " + d.name + " ", total));
            allInPcs = allInPcs - (total * d.inPcs);
        }

        return res;
    }

    //ini basicnya uomConverter
    private void initV2() {
        int buyCtn = 5;
        int buyHgr = 66;
        int buyPcs = 2;

        int uomCtn = 12;
        int uomHgr = 6;
        int uomPcs = 1;

        int ctnInPcs = buyCtn * uomCtn;
        int hgrInPcs = buyHgr * uomHgr;
        int pcsInPcs = buyPcs * uomPcs;

        Log.d(TAG, "initV2: " + ctnInPcs + "_" + hgrInPcs + "_" + pcsInPcs);

        int inPcs = ctnInPcs + hgrInPcs + pcsInPcs;
        Log.d(TAG, "initV2: inPcs " + inPcs);

        int totalCtn = inPcs / uomCtn;
        Log.d(TAG, "initV2: totalCtn " + totalCtn);
        inPcs = inPcs - (totalCtn * uomCtn);

        int totalHgr = inPcs / uomHgr;
        Log.d(TAG, "initV2: totalHgr " + totalHgr);
        inPcs = inPcs - (totalHgr * uomHgr);

        int totalPcs = inPcs;
        Log.d(TAG, "initV2: totalPcs " + totalPcs);
    }
}