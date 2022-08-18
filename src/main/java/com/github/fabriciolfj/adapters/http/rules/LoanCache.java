package com.github.fabriciolfj.adapters.http.rules;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.redis.client.reactive.ReactiveRedisClient;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cache.CacheException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Arrays;

@ApplicationScoped
@Slf4j
public class LoanCache {

    private static final String HASH_PREFIX = "loan:";

    @Inject
    private ReactiveRedisClient reactiveRedisClient;

    @Inject
    private ObjectMapper objectMapper;

    public Uni<BigDecimal> setValue(final BigDecimal key, final BigDecimal value) {
        return reactiveRedisClient.hmset(Arrays.asList(HASH_PREFIX + key.toString(), "value", value.toString()))
                .onItem()
                .transform(v -> {
                    log.info("add cache: {}", v);
                    return value;
                });
    }

    public Uni<BigDecimal> getValue(final BigDecimal value) {
        return reactiveRedisClient.hgetall(HASH_PREFIX + value.toString())
                .map(c -> {
                    if (c.size() > 0) {
                        var result = c.get("value").toString();
                        log.info("Return size cache: {}", result);

                        return new BigDecimal(result);
                    }

                    return null;
                }).onItem()
                .ifNull().failWith(new CacheException("Cache empty"));
    }
}
