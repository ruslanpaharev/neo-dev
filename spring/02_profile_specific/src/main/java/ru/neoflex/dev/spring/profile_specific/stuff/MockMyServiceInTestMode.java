package ru.neoflex.dev.spring.profile_specific.stuff;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"mock & test"}) // our conditional //
public class MockMyServiceInTestMode implements MyService {

    @Override
    public String generateString() {
        return "In mock In test";
    }
}

