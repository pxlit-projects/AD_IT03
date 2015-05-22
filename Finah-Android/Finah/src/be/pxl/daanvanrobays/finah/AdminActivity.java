package be.pxl.daanvanrobays.finah;

import be.pxl.daanvanrobays.fragments.*;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class AdminActivity extends FragmentActivity implements
		AdminListFrag.onStringSelectedListener,
		UsersFrag.OnUserSelectedListener,
		UsertypesFrag.OnUsertypeSelectedListener,
		QuestionsFrag.OnQuestionSelectedListener,
		ThemesFrag.OnThemeSelectedListener,
		AnswersFrag.OnAnswerSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);

		if (findViewById(R.id.fragment_container) != null) {
			if (savedInstanceState == null) {
				AdminListFrag admListFrag = new AdminListFrag();

				getSupportFragmentManager().beginTransaction()
						.add(R.id.fragment_container, admListFrag).commit();
			}
		}
	}

	@Override
	public void onStringSelected(String value) {
		// TODO Auto-generated method stub
		switch (value) {
		case "Users":
			showUsers();
			break;
		case "Usertypes":
			showUsertypes();
			break;
		case "Themes":
			showThemes();
			break;
		case "Questions":
			showQuestions();
			break;
		case "Answers":
			showAnswers();
			break;
		}
	}

	public void showUsers() {
		UsersFrag usersFrag = (UsersFrag) getSupportFragmentManager()
				.findFragmentById(R.id.overview_fragment);
		if (usersFrag != null && usersFrag.isInLayout()) { // two pane layout
			usersFrag.updateUsersView();

		} else {
			usersFrag = new UsersFrag();
			Bundle args = new Bundle();
			FragmentTransaction fragTrans = getSupportFragmentManager()
					.beginTransaction();
			fragTrans.replace(R.id.fragment_container, usersFrag);
			fragTrans.addToBackStack(null).commit();
		}
	}

	public void showUsertypes() {
		UsertypesFrag usertypesFrag = (UsertypesFrag) getSupportFragmentManager()
				.findFragmentById(R.id.overview_fragment);
		if (usertypesFrag != null && usertypesFrag.isInLayout()) { // two pane
																	// layout
			usertypesFrag.updateUsertypesView();

		} else {
			usertypesFrag = new UsertypesFrag();
			Bundle args = new Bundle();
			FragmentTransaction fragTrans = getSupportFragmentManager()
					.beginTransaction();
			fragTrans.replace(R.id.fragment_container, usertypesFrag);
			fragTrans.addToBackStack(null).commit();
		}
	}

	public void showThemes() {
		ThemesFrag themesFrag = (ThemesFrag) getSupportFragmentManager()
				.findFragmentById(R.id.overview_fragment);
		if (themesFrag != null && themesFrag.isInLayout()) { // two pane layout
			themesFrag.updateThemesView();

		} else {
			themesFrag = new ThemesFrag();
			Bundle args = new Bundle();
			FragmentTransaction fragTrans = getSupportFragmentManager()
					.beginTransaction();
			fragTrans.replace(R.id.fragment_container, themesFrag);
			fragTrans.addToBackStack(null).commit();
		}
	}

	public void showQuestions() {
		QuestionsFrag questionsFrag = (QuestionsFrag) getSupportFragmentManager()
				.findFragmentById(R.id.overview_fragment);
		if (questionsFrag != null && questionsFrag.isInLayout()) { // two pane
																	// layout
			questionsFrag.updateQuestionsView();

		} else {
			questionsFrag = new QuestionsFrag();
			Bundle args = new Bundle();
			FragmentTransaction fragTrans = getSupportFragmentManager()
					.beginTransaction();
			fragTrans.replace(R.id.fragment_container, questionsFrag);
			fragTrans.addToBackStack(null).commit();
		}
	}

	public void showAnswers() {
		AnswersFrag answersFrag = (AnswersFrag) getSupportFragmentManager()
				.findFragmentById(R.id.overview_fragment);
		if (answersFrag != null && answersFrag.isInLayout()) { // two pane
																// layout
			answersFrag.updateAnswersView();

		} else {
			answersFrag = new AnswersFrag();
			Bundle args = new Bundle();
			FragmentTransaction fragTrans = getSupportFragmentManager()
					.beginTransaction();
			fragTrans.replace(R.id.fragment_container, answersFrag);
			fragTrans.addToBackStack(null).commit();
		}
	}

	@Override
	public void onUserSelected(int position) {
		// TODO Auto-generated method stub
		UserDetailsFrag userDetFrag = (UserDetailsFrag) getSupportFragmentManager()
				.findFragmentById(R.id.details_fragment);

		if (userDetFrag != null) {
			userDetFrag.updateUserView(position);
		} else {

			UserDetailsFrag newFragment = new UserDetailsFrag();
			Bundle args = new Bundle();
			args.putInt(UserDetailsFrag.ARG_POSITION, position);
			newFragment.setArguments(args);
			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();

			transaction.replace(R.id.fragment_container, newFragment);
			transaction.addToBackStack(null);

			transaction.commit();
		}
	}

	@Override
	public void onUserTypeSelected(int position) {
		// TODO Auto-generated method stub
		UsertypeDetailsFrag usertypeDetFrag = (UsertypeDetailsFrag) getSupportFragmentManager()
				.findFragmentById(R.id.details_fragment);

		if (usertypeDetFrag != null) {
			usertypeDetFrag.updateUsertypeView(position);
		} else {

			UsertypeDetailsFrag newFragment = new UsertypeDetailsFrag();
			Bundle args = new Bundle();
			args.putInt(UsertypeDetailsFrag.ARG_POSITION, position);
			newFragment.setArguments(args);
			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();

			transaction.replace(R.id.fragment_container, newFragment);
			transaction.addToBackStack(null);

			transaction.commit();
		}
	}
	
	

	@Override
	public void onQuestionSelected(int position) {
		// TODO Auto-generated method stub
		QuestionDetailsFrag questionDetFrag = (QuestionDetailsFrag) getSupportFragmentManager()
				.findFragmentById(R.id.details_fragment);

		if (questionDetFrag != null) {
			questionDetFrag.updateQuestionView(position);
		} else {

			QuestionDetailsFrag newFragment = new QuestionDetailsFrag();
			Bundle args = new Bundle();
			args.putInt(QuestionDetailsFrag.ARG_POSITION, position);
			newFragment.setArguments(args);
			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();

			transaction.replace(R.id.fragment_container, newFragment);
			transaction.addToBackStack(null);

			transaction.commit();
		}
	}

	@Override
	public void onThemeSelected(int position) {
		// TODO Auto-generated method stub
		ThemeDetailsFrag themeDetFrag = (ThemeDetailsFrag) getSupportFragmentManager()
				.findFragmentById(R.id.details_fragment);

		if (themeDetFrag != null) {
			themeDetFrag.updateThemeView(position);
		} else {

			ThemeDetailsFrag newFragment = new ThemeDetailsFrag();
			Bundle args = new Bundle();
			args.putInt(ThemeDetailsFrag.ARG_POSITION, position);
			newFragment.setArguments(args);
			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();

			transaction.replace(R.id.fragment_container, newFragment);
			transaction.addToBackStack(null);

			transaction.commit();
		}
	}

	@Override
	public void onAnswerSelected(int position) {
		// TODO Auto-generated method stub
		AnswerDetailsFrag answerDetFrag = (AnswerDetailsFrag) getSupportFragmentManager()
				.findFragmentById(R.id.details_fragment);

		if (answerDetFrag != null) {
			answerDetFrag.updateAnswerView(position);
		} else {

			AnswerDetailsFrag newFragment = new AnswerDetailsFrag();
			Bundle args = new Bundle();
			args.putInt(AnswerDetailsFrag.ARG_POSITION, position);
			newFragment.setArguments(args);
			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();

			transaction.replace(R.id.fragment_container, newFragment);
			transaction.addToBackStack(null);

			transaction.commit();
		}
	}
}
