package fr.elive.android.app.fragment;

/**
 * Created by chriis on 03/01/2016.
 */
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import fr.elive.android.app.R;

public class nfcFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.nfcfragment, container, false);
        Button button = (Button) v.findViewById(R.id.btnNFC);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                android.nfc.NfcAdapter mNfcAdapter = android.nfc.NfcAdapter.getDefaultAdapter(v.getContext());

                AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getContext());

                if (mNfcAdapter == null) {
                    alertbox.setTitle("Info");
                    alertbox.setMessage(getString(R.string.msg_nonfc));
                    alertbox.show();
                    return;
                }

                if (!mNfcAdapter.isEnabled()) {


                    alertbox.setTitle("Info");
                    alertbox.setMessage(getString(R.string.msg_nfcoff));
                    alertbox.setPositiveButton("Paramètres NFC", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                startActivity(intent);
                            }
                        }
                    });
                    alertbox.setNegativeButton("Close", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertbox.show();

                }else{
                    alertbox.setTitle("Info");
                    alertbox.setMessage(getString(R.string.msg_nfcon));
                    alertbox.show();
                }
            }
        });

        return v;
    }


}
