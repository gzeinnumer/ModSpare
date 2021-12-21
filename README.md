# ModSpare

```java
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

        //onCreate: 0 CTN 18 HGR 2 PCS
        //onCreate: 18 HGR 2 PCS
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
}
```

---

```
Copyright 2021 M. Fadli Zein
```
