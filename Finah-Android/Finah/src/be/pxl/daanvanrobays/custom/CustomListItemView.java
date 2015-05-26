package be.pxl.daanvanrobays.custom;

import java.text.SimpleDateFormat;
import java.util.Locale;

import be.pxl.daanvanrobays.finah.R;
import be.pxl.daanvanrobays.pojo.*;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomListItemView extends LinearLayout {
	private TextView tv_above_medium;
	private TextView tv_below_small;
	private User user;
	private UserType userType;
	private Theme theme;
	private Question question;
	private QuestionList questionList;
	private Answer answer;
	private AnswerList answerList;
	private Hashes hash;

	public CustomListItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CustomListItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater infl = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		infl.inflate(R.layout.rowlayout, this);
		this.tv_above_medium = (TextView) findViewById(R.id.tv_above_medium);
		this.tv_below_small = (TextView) findViewById(R.id.tv_below_small);
	}

	public <T> void SetInfo(T t) {

		if (t instanceof User) {
			this.user = (User) t;
			this.tv_above_medium.setText(user.getLogin());
			this.tv_below_small.setText(user.getFirstname() + " "
					+ user.getLastname());
		} else if (t instanceof UserType) {
			this.userType = (UserType) t;
			this.tv_above_medium.setText(userType.getTypeName());
			this.tv_below_small.setText(userType.getDescription());
		} else if (t instanceof Theme) {
			this.theme = (Theme) t;
			this.tv_above_medium.setText(theme.getId() + "");
			this.tv_below_small.setText(theme.getTitle());
		} else if (t instanceof Question) {
			this.question = (Question) t;
			this.tv_above_medium.setText(question.getId() + "");
			this.tv_below_small.setText(question.getTitle());
		} else if (t instanceof QuestionList) {
			this.questionList = (QuestionList) t;
			this.tv_above_medium.setText(questionList.getId() + "");
			this.tv_below_small.setText(questionList.getDescription());
		} else if (t instanceof Answer) {
			this.answer = (Answer) t;
			this.tv_above_medium.setText(answer.getId() + "");
			this.tv_below_small.setText(answer.getTitle());
		} else if (t instanceof AnswerList) {
			this.answerList = (AnswerList) t;
			this.tv_above_medium.setText("Question " + answerList.getQuestion());
			switch (answerList.getWorkpoint()) {
			case 0:
				this.tv_below_small.setText("No help needed");
				break;
			case 1:
				this.tv_below_small.setText("Help needed");
				break;
			default:
				break;
			}
		} else if (t instanceof Hashes) {
			this.hash = (Hashes) t;
			String myFormat = "yyyy-MM-dd HH:mm"; // In which you need put here
			SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
			this.tv_above_medium.setText(sdf.format(hash.getDate()));
			switch (hash.getStatus()) {
			case 0:
				this.tv_below_small.setText("Not answered");
				break;
			case 1:
				this.tv_below_small.setText("Answered");
				break;
			default:
				break;
			}
		}
	}
}