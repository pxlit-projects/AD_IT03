package be.pxl.daanvanrobays.finah;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

public class DateDialogFragment extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {

	private EditText et_date;
	private Calendar myCalendar = Calendar.getInstance();

	public DateDialogFragment(EditText et_date) {
		this.et_date = et_date;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		int year = myCalendar.get(Calendar.YEAR);
		int month = myCalendar.get(Calendar.MONTH);
		int day = myCalendar.get(Calendar.DAY_OF_MONTH);
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		myCalendar.set(Calendar.YEAR, year);
		myCalendar.set(Calendar.MONTH, monthOfYear);
		myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		updateLabel();
	}

	private void updateLabel() {
		// TODO Auto-generated method stub
		String myFormat = "yyyy-MM-dd"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		et_date.setText(sdf.format(myCalendar.getTime()));
	}
}
