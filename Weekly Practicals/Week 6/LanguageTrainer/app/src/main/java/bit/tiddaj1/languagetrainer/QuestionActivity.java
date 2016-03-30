package bit.tiddaj1.languagetrainer;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity {

    //Global variable
    int score;
    int currQuestionNum;
    Question currQuestion;
    ArrayList<Question> questionList;
    AlertBuilderFragment feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //Reference to btnSubmitAnswer
        Button btnSubmit = (Button) findViewById(R.id.btnSubmitAnswer);
        btnSubmit.setOnClickListener(new buttonListener());

        score = 0;
        currQuestionNum = 0;
        currQuestion = null;

        loadQuestionList();
        shuffleList();
        loadNextQuestion();
    }

    //Loads questions into a list
    public void loadQuestionList()
    {
        questionList = new ArrayList<Question>();

        //Load list with instances of Question
        questionList.add(new Question("Apfel", "Der", R.drawable.der_apfel));
        questionList.add(new Question("Auto", "Das", R.drawable.das_auto));
        questionList.add(new Question("Baum", "Der", R.drawable.der_baum));
        questionList.add(new Question("Ente", "Die", R.drawable.die_ente));
        questionList.add(new Question("Haus", "Das", R.drawable.das_haus));
        questionList.add(new Question("Hexe", "Die", R.drawable.die_hexe));
        questionList.add(new Question("Kuh", "Die", R.drawable.die_kuh));
        questionList.add(new Question("Milch", "Die", R.drawable.die_milch));
        questionList.add(new Question("Schaf", "Das", R.drawable.das_schaf));
        questionList.add(new Question("Strasse", "Die", R.drawable.die_strasse));
        questionList.add(new Question("Stuhl", "Der", R.drawable.der_stuhl));
    }

    //Shuffle algorithm
    private void shuffleList()
    {
        Random rand = new Random();

        for(int i = 0; i < 100; i++)
        {
            int one = rand.nextInt(questionList.size());
            int two = rand.nextInt(questionList.size());

            Question questionOne = questionList.get(one);
            Question questionTwo = questionList.get(two);
            questionList.set(two, questionOne);
            questionList.set(one, questionTwo);
        }
    }

    //Loads next question
    //Sets image and question number
    public void loadNextQuestion()
    {
        currQuestion = questionList.get(currQuestionNum);
        ImageView ivNoun = (ImageView) findViewById(R.id.ivQuestionImage);
        TextView tvTitle = (TextView) findViewById(R.id.tvQuestionNumber);

        String questionTitle = "Question " + (currQuestionNum + 1) + "/" + questionList.size();
        tvTitle.setText(questionTitle);
        ivNoun.setImageResource(currQuestion.getImage());
    }

    //Button listener class
    public class buttonListener implements Button.OnClickListener
    {
        @Override
        //onClick method
        public void onClick(View v)
        {
            buildAlertBuilderFragment(checkAnswer());
        }
    }

    //Checks if the selected radiobutton is the correct answer
    //Returns a boolean
    public int checkAnswer()
    {
        int checkedRbBtnId = 0;
        RadioGroup rgArticles = (RadioGroup) findViewById(R.id.rgArticles);
        checkedRbBtnId = rgArticles.getCheckedRadioButtonId();

        if(checkedRbBtnId == -1)
            return -1;

        RadioButton checked = (RadioButton) findViewById(checkedRbBtnId);
        checked.setChecked(false);

        if(checked.getText().toString().equals(currQuestion.getArticle()))
            return 1;
        else
            return 0;
    }

    //Builds a DialogFragment and passes it a string correct or incorrect depending if the question
    //was answered correctly
    public void buildAlertBuilderFragment(int result)
    {
        if (result != -1)
        {
            feedback = new AlertBuilderFragment();
            Bundle bundle = new Bundle();

            //Puts a string into the bundle
            if(result == 1)
            {
                bundle.putString("data", "Correct");
                score++;
            }
            else
                bundle.putString("data", "Incorrect");

            //Passes bundle
            feedback.setArguments(bundle);
            FragmentManager fm = getFragmentManager();
            //Shows DialogFragment
            feedback.show(fm, "feedback");
        }
        else
            Toast.makeText(getApplicationContext(), "Please select an answer.",
                    Toast.LENGTH_SHORT).show();
    }

    //Called when the Next Question button on the DialogFragment is clicked
    public void getDialogData()
    {
        feedback.dismiss();
        //Increments so a new question will load
        currQuestionNum++;

        //Shows a new question if currQuestionNum is less than list size
        if(currQuestionNum < questionList.size())
        {
            loadNextQuestion();
        }
        //If all questions have been displayed show results
        else
        {
            Intent goToResults = new Intent(QuestionActivity.this, ResultActivity.class);
            //Passes the score to the intent
            goToResults.putExtra("score", score);
            startActivity(goToResults);
        }
    }

    //Overrides method, and does nothing...
    @Override
    public void onBackPressed() { }
}
