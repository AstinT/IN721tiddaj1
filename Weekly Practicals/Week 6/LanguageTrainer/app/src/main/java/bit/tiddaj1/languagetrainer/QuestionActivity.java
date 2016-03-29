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
import java.util.ArrayList;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity {

    int score;
    int currQuestionNum;
    Question currQuestion;
    ArrayList<Question> questionList;
    AlertBuilderFragment feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Button btnSubmit = (Button) findViewById(R.id.btnSubmitAnswer);
        btnSubmit.setOnClickListener(new buttonListener());

        score = 0;
        currQuestionNum = 0;
        currQuestion = null;

        loadQuestionList();
        shuffleList();
        loadNextQuestion();
    }

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

    public void loadNextQuestion()
    {
        currQuestion = questionList.get(currQuestionNum);
        ImageView ivNoun = (ImageView) findViewById(R.id.ivQuestionImage);
        TextView tvTitle = (TextView) findViewById(R.id.tvQuestionNumber);

        tvTitle.setText("Question " + (currQuestionNum + 1) + "/" + questionList.size());
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

    public boolean checkAnswer()
    {
        RadioGroup rgArticles = (RadioGroup) findViewById(R.id.rgArticles);
        int checkedRbBtnId = rgArticles.getCheckedRadioButtonId();
        RadioButton checked = (RadioButton) findViewById(checkedRbBtnId);

        if(checked.getText().toString().equals(currQuestion.getArticle()))
            return true;
        else
            return false;
    }

    public void buildAlertBuilderFragment(boolean result)
    {
        feedback = new AlertBuilderFragment();
        Bundle bundle = new Bundle();

        if(result)
        {
            bundle.putString("data", "Correct");
            score++;
        }
        else
            bundle.putString("data", "Incorrect");

        feedback.setArguments(bundle);
        FragmentManager fm = getFragmentManager();
        feedback.show(fm, "feedback");
    }

    public void getDialogData()
    {
        feedback.dismiss();
        currQuestionNum++;

        if(currQuestionNum < questionList.size())
        {
            loadNextQuestion();
        }
        else
        {
            Intent goToResults = new Intent(QuestionActivity.this, ResultActivity.class);
            goToResults.putExtra("score", score);
            startActivity(goToResults);
        }
    }
}
