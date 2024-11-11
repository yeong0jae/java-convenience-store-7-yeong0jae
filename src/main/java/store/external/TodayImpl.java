package store.external;

import java.time.LocalDateTime;
import store.domain.promotion.Today;

public class TodayImpl implements Today {

    @Override
    public LocalDateTime getToday() {
        return LocalDateTime.now();
    }
}
