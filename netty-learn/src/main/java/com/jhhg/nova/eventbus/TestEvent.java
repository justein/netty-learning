package com.jhhg.nova.eventbus;

/***
 * @ClassName: TestEvent
 * @Description: guava中的eventbus学习
 * @Author: Lyn
 * @Date: 2020/2/26 上午10:14
 * @version : V1.0
 */
public class TestEvent {

    private String eventName;
    private Integer eventAge;

    public TestEvent(String eventName, Integer eventAge) {
        this.eventName = eventName;
        this.eventAge = eventAge;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getEventAge() {
        return eventAge;
    }

    public void setEventAge(Integer eventAge) {
        this.eventAge = eventAge;
    }
}
