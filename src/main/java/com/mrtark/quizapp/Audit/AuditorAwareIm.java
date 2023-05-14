package com.mrtark.quizapp.Audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
public class AuditorAwareIm implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("admin");
    }
}
