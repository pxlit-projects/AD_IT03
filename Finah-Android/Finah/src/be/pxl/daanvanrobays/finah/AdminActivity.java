package be.pxl.daanvanrobays.finah;

import be.pxl.daanvanrobays.fragments.*;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class AdminActivity extends Activity implements
		AdminListFrag.onStringSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);

		if (findViewById(R.id.fragment_container) != null) {
			if (savedInstanceState == null) {
				AdminListFrag admListFrag = new AdminListFrag();
				FragmentTransaction fragTrans = getFragmentManager()
						.beginTransaction();
				fragTrans.add(R.id.fragment_container, admListFrag);
				fragTrans.commit();
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
		case "Questionlists":
			showQuestionLists();
			break;
		case "Answers":
			showAnswers();
			break;
		case "Answerslists":
			showAnswerLists();
			break;
		case "Hashes":
			break;
		}
	}

	public void showUsers() {
		UsersFrag usersFrag = (UsersFrag) getFragmentManager()
				.findFragmentById(R.id.overview_fragment);
		if (usersFrag != null && usersFrag.isInLayout()) { // two pane layout
			usersFrag.updateUsersView();

		} else {
			usersFrag = new UsersFrag();
			Bundle args = new Bundle();
			FragmentTransaction fragTrans = getFragmentManager()
					.beginTransaction();
			fragTrans.replace(R.id.fragment_container, usersFrag);
			fragTrans.addToBackStack(null).commit();
		}
	}

	public void showUsertypes() {
		UsertypesFrag usertypesFrag = (UsertypesFrag) getFragmentManager()
				.findFragmentById(R.id.overview_fragment);
		if (usertypesFrag != null && usertypesFrag.isInLayout()) { // two pane
																	// layout
			usertypesFrag.updateUsertypesView();

		} else {
			usertypesFrag = new UsertypesFrag();
			Bundle args = new Bundle();
			FragmentTransaction fragTrans = getFragmentManager()
					.beginTransaction();
			fragTrans.replace(R.id.fragment_container, usertypesFrag);
			fragTrans.addToBackStack(null).commit();
		}
	}

	public void showThemes() {
		ThemesFrag themesFrag = (ThemesFrag) getFragmentManager()
				.findFragmentById(R.id.overview_fragment);
		if (themesFrag != null && themesFrag.isInLayout()) { // two pane layout
			themesFrag.updateThemesView();

		} else {
			themesFrag = new ThemesFrag();
			Bundle args = new Bundle();
			FragmentTransaction fragTrans = getFragmentManager()
					.beginTransaction();
			fragTrans.replace(R.id.fragment_container, themesFrag);
			fragTrans.addToBackStack(null).commit();
		}
	}
	public void showQuestions() {
		QuestionsFrag questionsFrag = (QuestionsFrag) getFragmentManager()
				.findFragmentById(R.id.overview_fragment);
		if (questionsFrag != null && questionsFrag.isInLayout()) { // two pane layout
			questionsFrag.updateQuestionsView();

		} else {
			questionsFrag = new QuestionsFrag();
			Bundle args = new Bundle();
			FragmentTransaction fragTrans = getFragmentManager()
					.beginTransaction();
			fragTrans.replace(R.id.fragment_container, questionsFrag);
			fragTrans.addToBackStack(null).commit();
		}
	}
	public void showQuestionLists() {
		QuestionListsFrag questionlistsFrag = (QuestionListsFrag) getFragmentManager()
				.findFragmentById(R.id.overview_fragment);
		if (questionlistsFrag != null && questionlistsFrag.isInLayout()) { // two pane layout
			questionlistsFrag.updateQuestionListsView();

		} else {
			questionlistsFrag = new QuestionListsFrag();
			Bundle args = new Bundle();
			FragmentTransaction fragTrans = getFragmentManager()
					.beginTransaction();
			fragTrans.replace(R.id.fragment_container, questionlistsFrag);
			fragTrans.addToBackStack(null).commit();
		}
	}
	public void showAnswers() {
		AnswersFrag answersFrag = (AnswersFrag) getFragmentManager()
				.findFragmentById(R.id.overview_fragment);
		if (answersFrag != null && answersFrag.isInLayout()) { // two pane layout
			answersFrag.updateAnswersView();

		} else {
			answersFrag = new AnswersFrag();
			Bundle args = new Bundle();
			FragmentTransaction fragTrans = getFragmentManager()
					.beginTransaction();
			fragTrans.replace(R.id.fragment_container, answersFrag);
			fragTrans.addToBackStack(null).commit();
		}
	}
	public void showAnswerLists() {
		AnswerListsFrag answerlistsFrag = (AnswerListsFrag) getFragmentManager()
				.findFragmentById(R.id.overview_fragment);
		if (answerlistsFrag != null && answerlistsFrag.isInLayout()) { // two pane layout
			answerlistsFrag.updateAnswerListsView();

		} else {
			answerlistsFrag = new AnswerListsFrag();
			Bundle args = new Bundle();
			FragmentTransaction fragTrans = getFragmentManager()
					.beginTransaction();
			fragTrans.replace(R.id.fragment_container, answerlistsFrag);
			fragTrans.addToBackStack(null).commit();
		}
	}
}
