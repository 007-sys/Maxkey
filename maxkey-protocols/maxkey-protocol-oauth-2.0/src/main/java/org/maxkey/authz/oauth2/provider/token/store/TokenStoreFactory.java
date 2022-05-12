/*
 * Copyright [2021] [MaxKey of copyright http://www.maxkey.top]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 

package org.maxkey.authz.oauth2.provider.token.store;

import org.maxkey.authz.oauth2.provider.token.TokenStore;
import org.maxkey.constants.ConstsPersistence;
import org.maxkey.persistence.redis.RedisConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class TokenStoreFactory {
	private static final  Logger _logger = LoggerFactory.getLogger(TokenStoreFactory.class);
	
	 public TokenStore getTokenStore(
	            int persistence,
	            JdbcTemplate jdbcTemplate,
	            RedisConnectionFactory redisConnFactory) {
	        TokenStore tokenStore = null;
	        if (persistence == ConstsPersistence.INMEMORY) {
	            tokenStore = new InMemoryTokenStore();
	            _logger.debug("InMemoryTokenStore");
	        } else if (persistence == ConstsPersistence.JDBC) {
	            //tokenStore = new JdbcTokenStore(jdbcTemplate);
	            _logger.debug("JdbcTokenStore not support "); 
	        } else if (persistence == ConstsPersistence.REDIS) {
	            tokenStore = new RedisTokenStore(redisConnFactory);
	            _logger.debug("RedisTokenStore");
	        }
	        return tokenStore;
	    }
}
