package com.jeahn.skyscanner.src.selectDate;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;
import com.kizitonwose.calendarview.CalendarView;
import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.ui.DayBinder;
import com.kizitonwose.calendarview.ui.ViewContainer;

import org.jetbrains.annotations.NotNull;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.YearMonth;
import org.threeten.bp.temporal.WeekFields;

import java.util.Locale;

public class SelectDateActivity extends BaseActivity {
    private CalendarView mCalendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);

        mCalendar = findViewById(R.id.select_date_calendarView);

        YearMonth currentMonth = YearMonth.now();
        YearMonth firstMonth = currentMonth.minusMonths(10);
        YearMonth lastMonth = currentMonth.plusMonths(10);
        DayOfWeek firstDayOfWeek = WeekFields.of(Locale.KOREA).getFirstDayOfWeek();
        mCalendar.setup(firstMonth, lastMonth, firstDayOfWeek);
        mCalendar.scrollToMonth(currentMonth);

        mCalendar.setDayBinder(new DayBinder<DayViewContainer>() {
            @NotNull
            @Override
            public DayViewContainer create(@NotNull View view) {
                return new DayViewContainer(view);
            }

            @Override
            public void bind(@NotNull DayViewContainer viewContainer, @NotNull CalendarDay calendarDay) {
                viewContainer.textView.setText(calendarDay.getDate().getDayOfMonth() + "");
            }
        });
    }

    class DayViewContainer extends ViewContainer{
        TextView textView;
        public DayViewContainer(@NotNull View view) {
            super(view);
            textView = view.findViewById(R.id.calendarDayText);
        }
    }
}
