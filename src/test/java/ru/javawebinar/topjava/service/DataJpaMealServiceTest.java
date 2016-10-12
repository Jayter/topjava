package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by Jayton on 10.10.2016.
 */
@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.DATAJPA})
public class DataJpaMealServiceTest extends MealServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(DataJpaMealServiceTest.class);
}
