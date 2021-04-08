package psn.xiongfeng.tool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolApplicationTests {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRedisTemplate(){
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        vo.set("name","ted");
        System.out.println(vo.get("name"));
    }

}
