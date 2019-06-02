package com.ifrs.carlos.locusstudio;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ifrs.carlos.locusstudio.data.LocusBeacon;
import com.ifrs.carlos.locusstudio.data.LocusBeaconAdapter;
import com.ifrs.carlos.locusstudio.data.Position;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

public class ScanBeaconsActivity extends AppCompatActivity implements BeaconConsumer {

    private ListView listView;
    private BeaconManager beaconManager;
    private LocusBeaconAdapter lstAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        this.setTitle("Scan");

        beaconManager = BeaconManager.getInstanceForApplication(this);
        // To detect proprietary beacons, you must add a line like below corresponding to your beacon
        // type.  Do a web search for "setBeaconLayout" to get the proper expression.
        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(this);


        lstAdapter = new LocusBeaconAdapter(this,
                R.layout.lstview_beacon, LocusData.locusBeacons);

        listView = (ListView)findViewById(R.id.lstBeacons);

        listView.setAdapter(lstAdapter);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Util.PERMISSION_REQUEST_COARSE_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("ScanBeacon", "coarse location permission granted");
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Functionality limited");
                    builder.setMessage("Since location access has not been granted, this app will not be able to discover beacons when in the background.");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                        @Override
                        public void onDismiss(DialogInterface dialog) {
                        }

                    });
                    builder.show();
                }
                return;
            }
        }
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    for (Beacon i : beacons) {
                        LocusData.updateDistance(
                                i.getId1().toString(),
                                i.getId2().toString(),
                                i.getId3().toString(),
                                i.getDistance());
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        calcPosition();
                        notifyListView();
                    }
                });

            }
        });

        beaconManager.addMonitorNotifier(new MonitorNotifier() {

            @Override
            public void didEnterRegion(Region region) {
                LocusData.activeBeacon(
                        region.getId1().toString(),
                        region.getId2().toString(),
                        region.getId3().toString());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        calcPosition();
                        notifyListView();
                    }
                });

            }

            @Override
            public void didExitRegion(Region region) {
                LocusData.deactiveBeacon(
                        region.getId1().toString(),
                        region.getId2().toString(),
                        region.getId3().toString());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        calcPosition();
                        notifyListView();
                    }
                });

            }

            @Override
            public void didDetermineStateForRegion(int state, Region region) {
                //Log.i(TAG, "I have just switched from seeing/not seeing beacons: "+state);
            }
        });

        try {
            for (LocusBeacon i : LocusData.locusBeacons) {
                Log.i("Beacon:", i.getId());
                Region vlRegion = new Region(i.getId(),
                        Identifier.parse(i.getUUID()),
                        Identifier.fromInt(Integer.parseInt(i.getMajor())),
                        Identifier.fromInt(Integer.parseInt(i.getMinor())));

                beaconManager.startRangingBeaconsInRegion(vlRegion);
                beaconManager.startMonitoringBeaconsInRegion(vlRegion);
            }
        } catch (RemoteException e) {    }

    }

    private void notifyListView() {
        lstAdapter.notifyDataSetChanged();
    }

    private void calcPosition(){

        Position vlPosition = Util.calculaPosicao();

        TextView txtX = (TextView)findViewById(R.id.txtXMetros);
        TextView txtY = (TextView)findViewById(R.id.txtYMetros);

        txtX.setText(String.format("%.2f", vlPosition.getxMetros()));
        txtY.setText(String.format("%.2f", vlPosition.getyMetros()));
        //txtX.setText("Carlos");
        //txtY.setText("AAss");
    }

    public void cmdScan(View view) {

        lstAdapter.notifyDataSetChanged();

        //Intent intent =  new Intent(this, EstabelecimentosActivity.class);
        //startActivity(intent);
    }
}
