package cn.czy15.zyweb.service;

import cn.czy15.zyweb.aop.annotation.MyLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Zhuoyu Chen
 * @project zyweb
 * @date 9:35
 */
@Component
@Slf4j
public class ScheduledServie {

    @Resource
    RedisService redisService;

    @PostConstruct
    @MyLog(title = "定时任务", action = "线程池")
    public void scheduledThreadService() {
        ScheduledThreadPoolExecutor executor;
        executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(new redisRrefreshRate(), 0, 1, TimeUnit.HOURS);
    }


    public class redisRrefreshRate implements Runnable {

        static final String preClickStr = "recent_rate_";
        Map<String, Double> allMap = new HashMap<String, Double>();

        @Override
        public void run() {
            log.info("热点排行榜开始刷新");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            Date nowTime = c.getTime();
            String nowTimeStr = preClickStr + dateFormat.format(nowTime);
            c.add(Calendar.DATE, -1);
            Date lastDay = c.getTime();
            String lastDayStr = preClickStr + dateFormat.format(lastDay);//前1天
            c.add(Calendar.DATE, -1);
            Date lastTwoDay = c.getTime();
            String lastTwoDayStr = preClickStr + dateFormat.format(lastTwoDay);//前2天
            log.info(lastDayStr);
            log.info(lastTwoDayStr);
            allMap.clear();
            try {
                readRedis(nowTimeStr);
                readRedis(lastDayStr);
                readRedis(lastTwoDayStr);
                for (Map.Entry<String, Double> entry : allMap.entrySet()) {
                    redisService.zadd(preClickStr + "lastThreeDay", entry.getValue(), entry.getKey());
//                    log.info("写入今日缓存榜Key: " + entry.getKey() + " Value: " + entry.getValue());
                }
                redisService.expire(preClickStr + "lastThreeDay", 1, TimeUnit.HOURS);
                log.info("热点排行榜刷新完成");
            } catch (Exception e) {
                log.info("刷新失败 : " + e.toString());
            }

        }

        public void readRedis(String nameStr) {
            if (redisService.hasKey(nameStr)) {
                log.info("开始读取redis : "+nameStr);
                Set<ZSetOperations.TypedTuple<Object>> allSet = redisService.reverseRangeWithScores(nameStr, 0, 10);
                Iterator it = allSet.iterator();
                while (it.hasNext()) {
                    ZSetOperations.TypedTuple tt = (ZSetOperations.TypedTuple) it.next();
//                    log.info(nameStr+ ": " +tt.getValue() + " " + tt.getScore().toString());
                    if (allMap.containsKey(tt.getValue()) != true) {
                        allMap.put((String) tt.getValue(), tt.getScore());
                    } else {
                        allMap.put((String) tt.getValue(), tt.getScore() + allMap.get(tt.getValue()));
                    }
                }
            }
        }
    }

}
