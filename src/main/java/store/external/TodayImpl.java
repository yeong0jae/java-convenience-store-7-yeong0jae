package store.external;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import store.domain.promotion.Today;

public class TodayImpl implements Today {

    @Override
    public LocalDateTime getToday() {
        return DateTimes.now();
    }
}
