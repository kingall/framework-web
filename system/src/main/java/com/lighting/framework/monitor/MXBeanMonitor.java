package com.lighting.framework.monitor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

@Component
public class MXBeanMonitor {

    @Scheduled(cron="*/30 * * * * ?")
    public void monitor() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = threadMXBean.getAllThreadIds();
        ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadIds);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + "-" + threadInfo.getThreadName()+":"+threadInfo.getBlockedTime()+"("+threadInfo.getBlockedCount()+")");
            System.out.println(threadInfo.getThreadId() + "-" + threadInfo.getThreadName()+":"+threadInfo.getWaitedTime()+"("+threadInfo.getWaitedCount()+")");
        }
    }
}
