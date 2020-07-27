package org.unicome.oauth.security.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.Date;

@Slf4j
public class MongoTokenRepositoryImpl implements PersistentTokenRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    private static final String PERSISTENT_LOGINS = "persistent_logins";

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        mongoTemplate.insert(token, PERSISTENT_LOGINS);
        log.debug("创建用户 [{}] token", token.getUsername());
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        Query query = new Query(Criteria.where("series").is(series));
        Update update = new Update();
        update.set("tokenValue", tokenValue);
        update.set("date", lastUsed);
        mongoTemplate.updateFirst(query, update, PERSISTENT_LOGINS);
        log.debug("更新用户 [{}] token", series);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String series) {
        Query query = new Query(Criteria.where("series").is(series));
        PersistentRememberMeToken token = mongoTemplate.findOne(query, PersistentRememberMeToken.class, PERSISTENT_LOGINS);
        log.debug("获取用户 [{}] token", series);
        return token;
    }

    @Override
    public void removeUserTokens(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        mongoTemplate.remove(query, PersistentRememberMeToken.class, PERSISTENT_LOGINS);
        log.debug("移除用户 [{}] token", username);
    }
}
