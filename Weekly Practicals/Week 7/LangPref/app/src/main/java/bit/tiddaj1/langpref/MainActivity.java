package bit.tiddaj1.langpref;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Global variables
    SharedPreferences prefs;
    SharedPreferences.Editor prefsEditor;
    TextView feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        feedback = (TextView) findViewById(R.id.tvFeedback);
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new SetLanguageClickHandler());

        prefs = getSharedPreferences("appPrefs", MODE_PRIVATE);
        prefsEditor = prefs.edit();

        setPrefs();
    }

    //Sets up prefs if the already exist
    public void setPrefs()
    {
        //Set language type
        String languagePref = prefs.getString("language", null);
        if (languagePref != null)
        {
            String greetingInChosenLanguage = getGreeting(languagePref);
            feedback.setText(greetingInChosenLanguage);
        }

        //Set text colour
        int textColourPref = prefs.getInt("text_colour", 0);
        if (textColourPref != 0)
        {
            feedback.setTextColor(textColourPref);
        }
    }

    //Button listener
    public class SetLanguageClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            getCheckedLanguage();
            getCheckedColour();
        }
    }

    //Gets checked language and sets it in the prefs. Also updates language at runtime
    public void getCheckedLanguage()
    {
        RadioGroup rg = (RadioGroup) findViewById(R.id.rgLanguage);
        int checkedId = rg.getCheckedRadioButtonId(); //Returns -1 if no radiobutton checked

        if(checkedId != -1)
        {
            RadioButton checkedButton = (RadioButton) findViewById(checkedId);
            String checkedLanguage = checkedButton.getText().toString();

            //Store new language in prefs
            prefsEditor.putString("language", checkedLanguage);
            prefsEditor.commit();

            String greetingInChosenLanguage = getGreeting(checkedLanguage);
            feedback.setText(greetingInChosenLanguage);
        }
    }

    //Gets checked language and sets it in the prefs. Also updates language at runtime
    public void getCheckedColour()
    {
        RadioGroup rg = (RadioGroup) findViewById(R.id.rgTextColour);
        int checkedId = rg.getCheckedRadioButtonId(); //Returns -1 if no radiobutton checked

        if(checkedId != -1)
        {
            RadioButton checkedButton = (RadioButton) findViewById(checkedId);
            String checkedColour = checkedButton.getText().toString();
            int checkedColourCode = getColour(checkedColour);

            //Store new colour in prefs
            prefsEditor.putInt("text_colour", checkedColourCode);
            prefsEditor.commit();

            //Set text colour
            feedback.setTextColor(checkedColourCode);
        }
    }

    //Takes a string language and returns "hello world" depending on language
    private String getGreeting(String language)
    {
        String greeting = "";

        switch (language)
        {
            case("French"):
                greeting = "Bonjour Le Monde";
                break;
            case("German"):
                greeting = "Hallo Welt";
                break;
            case("Spanish"):
                greeting = "Hola Mundi";
                break;
            default:
                break;
        }

        return greeting;
    }

    //Takes a string colour and returns its int value.
    private int getColour(String colour)
    {
        int textColour = 0;

        switch (colour)
        {
            case("Blue"):
                textColour = Color.BLUE;
                break;
            case("Red"):
                textColour = Color.RED;
                break;
            case("Green"):
                textColour = Color.GREEN;
                break;
            default:
                break;
        }

        return textColour;
    }
}
