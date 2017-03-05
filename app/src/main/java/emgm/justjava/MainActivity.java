package emgm.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.security.Principal;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    TextView quantity_text_view;

    TextView orderTextView;

    CheckBox add;
    CheckBox add2;

    EditText name;
    int quantity=0;

    int price=0;

    String message;

   String ingredientesAdicionales="";
    String ingredientesAdicionales2="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


               setContentView(R.layout.activity_main);


        quantity_text_view =(TextView)findViewById(R.id.quantity_text_view);

        orderTextView = (TextView) findViewById(R.id.order_summary_text_view);

      add = (CheckBox) findViewById(R.id.myCheckbox);

        add2 = (CheckBox) findViewById(R.id.myCheckbox2);
        name = (EditText) findViewById(R.id.name_editText);



    }
    // \


    public void increment(View view){

        quantity++;

        display(quantity);

    }

    public void decrement(View view){

        verificarCantidad(2);

    }

    public void submitOrder(View view){

        verificarCantidad(3);

    }

    public void verificarCantidad (int number){

         quantity = Integer.parseInt(quantity_text_view.getText().toString());

        if(quantity >0) {


            switch (number) {

                case 2:

                    quantity--;

                    display(quantity);

                    break;

                case 3:


                    int priceFinal = priceFinal(price);

                     message =  "Name: " + name.getText()  + "\n"

                                        + ingredientesAdicionales
                                        + ingredientesAdicionales2
                                        + "Quantity: " + quantity + "\n"
                                        + "Total is: $" + priceFinal + "\n"
                                        + "Thanks You!";

                    orderTextView.setText(message);
                    quantity=0;

                    quantity_text_view.setText("" + 0);

                    add.setChecked(false);
                    add2.setChecked(false);

                    ingredientesAdicionales="";
                    ingredientesAdicionales2="";

                    name.setText("");

                    break;
            }

        }
    }

    public void display(int quantity){

        price = quantity * 5;

        quantity_text_view.setText("" + quantity);

        orderTextView.setText(NumberFormat.getCurrencyInstance().format(price));

    }

    public int priceFinal(int price){

        int priceAdd=0;
        //value add

        if(add.isChecked()){

            priceAdd = (quantity * 1);
        }

        if(add2.isChecked()){

            priceAdd += + (quantity*2);

        }

        price +=  + priceAdd;

        //debo sumar price

        return price;

    }

    public void onClickCheckBox(View view){

          switch (view.getId()) {

              case R.id.myCheckbox:



                  ingredientesAdicionales = add.getText().toString();

                  ingredientesAdicionales = "Add : " + ingredientesAdicionales + "\n";

                  break;

              case R.id.myCheckbox2:

                  ingredientesAdicionales2 = add2.getText().toString();

                  ingredientesAdicionales2 = "Add : " + ingredientesAdicionales2 + "\n";


                  break;


          }



    }

    public void sendEmail(View view){


        String[] TO = {"emgonzalezm@ufpso.edu.co"};
        String[] CC = {"emgm2745@live.com"};

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();

        }

        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }


    }








}
