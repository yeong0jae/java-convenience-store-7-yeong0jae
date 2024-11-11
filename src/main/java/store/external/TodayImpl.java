package store.external;

import java.time.LocalDate;
import store.domain.promotion.Today;

public class TodayImpl implements Today {

    @Override
    public LocalDate getToday() {
        return LocalDate.now();
    }
}
