package org.unicome.oauth.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.unicome.oauth.security.constant.UrlConstants;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserDetailsServiceImpl {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Autowired
    RestTemplate restTemplate;

    public UserDetails loadUserBy(String type, String value) throws UsernameNotFoundException {
        String url = UrlConstants.USER_URL + "?" + type + "=" + value + "";
        Map user = restTemplate.getForObject(url, Map.class, new HashMap<String, String>() {{
            put(type, value);
        }});
        if (CollectionUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("No this user");
        }
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .username((String)user.get("username"))
                .passsword((String)user.get("password"))
                .accountNonExpired((Boolean)user.get("accountNonExpired"))
                .accountNonLocked((Boolean)user.get("accountNonLocked"))
                .credentialsNonExpired((Boolean)user.get("credentialsNonExpired"))
                .enabled((Boolean)user.get("enabled"))
                .build();
        return userDetails;
    }
}
