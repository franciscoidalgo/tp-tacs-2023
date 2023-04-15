package com.tacs.repository;

import com.tacs.model.DateOption;
import java.util.List;

public interface DateOptionRepository extends CrudRepository<DateOption, Long> {
  List<DateOption> findByEventId(Long eventId);
}
