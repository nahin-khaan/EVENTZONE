package com.example.eventzone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCAdditionalInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCurrencyType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCTransactionResponseListener;

public class Payement extends AppCompatActivity implements SSLCTransactionResponseListener {
    SSLCommerzInitialization sslCommerzInitialization;
    SSLCAdditionalInitializer additionalInitializer;
    EditText payamountEdit;
    Button payBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payement);
        payamountEdit = findViewById(R.id.payAmountEdit);
        payBtn = findViewById(R.id.payBtnId);
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = payamountEdit.getText().toString().trim();
                if (!amount.isEmpty()) {
                    sslSetup(amount);

                } else {
                    payamountEdit.setError("Enter amount first");

                }

            }
        });
    }


    private void sslSetup(String amount) {
        SSLCommerzInitialization sslCommerzInitialization = new SSLCommerzInitialization(
                "nexde63e48d9521823", // Replace with your actual store ID
                "nexde63e48d9521823@ssl", // Replace with your actual store password
                Double.parseDouble(amount),
                SSLCCurrencyType.BDT,
                "transaction_id", // Replace with a unique transaction ID
                "eshop",
                SSLCSdkType.TESTBOX
        );

        SSLCAdditionalInitializer additionalInitializer = new SSLCAdditionalInitializer();
        additionalInitializer.setValueA("Value option 1");
        additionalInitializer.setValueB("Value option 2");
        additionalInitializer.setValueC("Value option 3");
        additionalInitializer.setValueD("Value option 4");

        IntegrateSSLCommerz.getInstance(this)
                .addSSLCommerzInitialization(sslCommerzInitialization)
                .addAdditionalInitializer(additionalInitializer)
                .buildApiCall(this);
    }


    @Override
    public void transactionSuccess(SSLCTransactionInfoModel sslcTransactionInfoModel) {
        Toast.makeText(Payement.this,"Payment Succesffuly",Toast.LENGTH_SHORT).show();

    }


    @Override
    public void transactionFail(String s) {
        Toast.makeText(Payement.this,s.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closed(String s) {
        Toast.makeText(Payement.this,s.toString(),Toast.LENGTH_SHORT).show();

    }
}